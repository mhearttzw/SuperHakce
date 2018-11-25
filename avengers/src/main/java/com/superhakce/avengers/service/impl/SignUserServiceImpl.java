package com.superhakce.avengers.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.superhakce.avengers.Constants;
import com.superhakce.avengers.common.utils.IDUtil;
import com.superhakce.avengers.common.utils.MD5Util;
import com.superhakce.avengers.common.utils.MessageUtil;
import com.superhakce.avengers.common.utils.ValidatorRegUtil;
import com.superhakce.avengers.common.utils.ValidatorUtil;
import com.superhakce.avengers.entity.SignUser;
import com.superhakce.avengers.enums.BusinessCode;
import com.superhakce.avengers.exception.ParamException;
import com.superhakce.avengers.exception.ThirdCallFailException;
import com.superhakce.avengers.model.JsonResult;
import com.superhakce.avengers.model.common.AuthInfoModel;
import com.superhakce.avengers.model.userInfo.req.SignUpModel;
import com.superhakce.avengers.model.userInfo.res.SignUserResModel;
import com.superhakce.avengers.respository.SignUserRepository;
import com.superhakce.avengers.service.RedisService;
import com.superhakce.avengers.service.SignUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SignUserServiceImpl implements SignUserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SignUserRepository signUserRepository;

    @Autowired
    private ValidatorUtil validatorUtil;
    @Autowired
    private IDUtil idUtil;

    @Override
    @Transactional
    public JsonResult register(SignUpModel signUpModel) {
        validatorUtil.validate(signUpModel);
        String saveCode = redisService.getWithPrefix(Constants.MSG_STRING, signUpModel.getPhone());

        // 检查昵称是否重复
        if (isNicknameUnique(signUpModel.getNickname())) {
            throw new ParamException(BusinessCode.NICKNAME_REPEAT);
        }
        // 检查电话是否重复
        if (isPhoneExist(signUpModel.getPhone())) {
            throw new ParamException(BusinessCode.PHONE_HAS_SIGNED);
        }
        // 检查是否通过短信验证码
        if (!Constants.SIGN_UP_CHECK.equals(saveCode)) {
            throw new ParamException(BusinessCode.CODE_WAITING_VERIFY);
        }

        JsonResult jsonResult = new JsonResult();
        // 添加用户
        SignUser signUser = new SignUser();
        String salt = IDUtil.getUUID();
        String newPassword = MD5Util.md5WithSalt(signUpModel.getPassword(), salt);
        BeanUtils.copyProperties(signUpModel, signUser);
        signUser.setPassword(newPassword);
        signUser.setSalt(salt);

        signUser = signUserRepository.save(signUser);

        // 注册成功，在redis中生成token凭证
        String token = IDUtil.getUUID();
        redisService.set(token, signUser.getId().toString());

        SignUserResModel resModel = new SignUserResModel();
        BeanUtils.copyProperties(signUser, resModel);
        //TODO 图片地址
        resModel.setToken(token);
        resModel.setAvatar(getImgUrl(signUser));
        jsonResult.setSuccess(resModel);

        return jsonResult;
    }

    @Override
    public JsonResult sendMsg(String phone) {
        // 手机号格式校验
        if (!ValidatorRegUtil.isPhone(phone)) {
            throw new ParamException(BusinessCode.PHONE_FORMAT_INCORRECT);
        }
        String random = IDUtil.random(5) + "";
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = MessageUtil.sendSms(random, phone);
        } catch (ClientException e) {
            log.error("发送短信验证码失败={}", e);
            throw new ThirdCallFailException(BusinessCode.SEND_FAIL.getMsg());
        }
        log.info("===========:" + sendSmsResponse);
        log.info("===========code:" + sendSmsResponse.getCode());
        if (sendSmsResponse.getCode().equals("OK")) {
            // 验证码存入redis并设置过期时间
            redisService.set(Constants.MSG_STRING + phone, random, Constants.MSG_TIMEOUT, TimeUnit.MINUTES);
            return new JsonResult<>(BusinessCode.SUCCESS, sendSmsResponse);
        }
        return new JsonResult(BusinessCode.SEND_FAIL);

    }

    @Override
    public JsonResult smsLogin(String phone, String msgCode) {
        if (!ValidatorRegUtil.isPhone(phone)) {
            throw new ParamException(BusinessCode.PHONE_FORMAT_INCORRECT);
        }
        if (null == msgCode) {
            throw new ParamException(BusinessCode.CODE_ERROR);
        }

        JsonResult jsonResult = new JsonResult();

        // 用手机号取出保存的短信验证码
        String saveCode = redisService.getWithPrefix(Constants.MSG_STRING, phone);
        if (!saveCode.equals(msgCode)) {
            throw new ParamException(BusinessCode.CODE_ERROR);
        }

        // 判断该手机号是否已注册
        Optional<SignUser> signUser = signUserRepository.findByPhone(phone);
        if (!signUser.isPresent()) {
            // 标记通过短信验证
            redisService.set(Constants.MSG_STRING + phone, Constants.SIGN_UP_CHECK, Constants.MSG_TIMEOUT, TimeUnit.MINUTES);
            return new JsonResult<>(BusinessCode.USER_WAITING_SIGN_UP);
        }

        // 已注册逻辑处理
        setSignUser(jsonResult, signUser);
        return jsonResult;
    }

    @Override
    public JsonResult usernameLogin(String username, String password) {
        JsonResult jsonResult = new JsonResult();
        if (null == username || null == password) {
            throw new ParamException("用户名或密码不能为空！");
        }
        // 从数据库取salt验证用户名、密码是否匹配
        Optional<SignUser> signUser = signUserRepository.findByNickname(username);
        signUser.orElseThrow(() ->
                new ParamException(BusinessCode.USER_NOT_EXISTS)
        );

        signUser.ifPresent(
                t -> {
                    String salt = t.getSalt();
                    String pwd = MD5Util.md5WithSalt(password, salt);
                    if (username.equals(t.getNickname()) && pwd.equals(t.getPassword())) {
                        // 在redis中生成token凭证，已注册value设为用户id
                        String token = IDUtil.getUUID();
                        redisService.set(token, t.getId().toString());

                        SignUserResModel resModel = new SignUserResModel();
                        BeanUtils.copyProperties(t, resModel);
                        //TODO 图片地址
                        resModel.setAvatar(getImgUrl(t));
                        resModel.setToken(token);
                        jsonResult.setSuccess(resModel);
                    } else {
                        jsonResult.set(BusinessCode.PASSWORD_ERROR);
                    }
                }
        );
        return jsonResult;
    }

    @Override
    public JsonResult signOut(AuthInfoModel authInfoModel) {
        Boolean flag = redisService.delete(authInfoModel.getUserToken());
        if (!flag) {
            return new JsonResult(BusinessCode.FAILED);
        }
        return new JsonResult(BusinessCode.SUCCESS);

    }

    @Override
    @Transactional
    public JsonResult passwordChange(AuthInfoModel authInfoModel, String oldPwd, String newPwd) {
        String salt = authInfoModel.getSalt();
        String oldPwdMixSalt = MD5Util.md5WithSalt(oldPwd, salt);
        // 验证用户密码
        if (!oldPwdMixSalt.equals(authInfoModel.getPassword())) {
            throw new ParamException(BusinessCode.PASSWORD_ERROR);
        }

        String newPwdMixSalt = MD5Util.md5WithSalt(newPwd, salt);
        signUserRepository.updatePassword(authInfoModel.getUserId(), authInfoModel.getPassword(), newPwdMixSalt);
        return null;
    }


    /**
     * 添加token缓存，设置返回model
     */
    private void setSignUser(JsonResult jsonResult, Optional<SignUser> userOptional) {
        userOptional.ifPresent(
                t -> {
                    // 注册成功，在redis中生成token凭证
                    String token = IDUtil.getUUID();
                    redisService.set(token, t.getId().toString());

                    SignUserResModel resModel = new SignUserResModel();
                    BeanUtils.copyProperties(t, resModel);
                    //TODO 图片地址
                    resModel.setToken(token);
                    resModel.setAvatar(getImgUrl(t));
                    jsonResult.setSuccess(resModel);
                }
        );
    }

    /**
     * 获取图片地址
     *
     * @param t
     * @return
     */
    private String getImgUrl(SignUser t) {
        return Constants.IMG_IP + "/" + t.getAvatar();
    }


    /**
     * 判断昵称是否唯一
     *
     * @param nickname
     * @return
     */
    public boolean isNicknameUnique(String nickname) {
        Optional<SignUser> signUser = signUserRepository.findByNickname(nickname);
        return signUser.isPresent();
    }

    /**
     * 判断该电话号码是否已注册
     *
     * @param phone
     * @return
     */
    public boolean isPhoneExist(String phone) {
        Optional<SignUser> signUser = signUserRepository.findByPhone(phone);
        return signUser.isPresent();
    }

}
