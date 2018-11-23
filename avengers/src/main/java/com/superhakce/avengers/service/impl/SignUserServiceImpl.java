package com.superhakce.avengers.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
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
import com.superhakce.avengers.model.userInfo.SignUpModel;
import com.superhakce.avengers.respository.SignUserRepository;
import com.superhakce.avengers.service.RedisService;
import com.superhakce.avengers.service.SignUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
        String saveCode = redisService.get(Constants.PHONE_STRING + signUpModel.getPhone());

        // 检查昵称是否重复
        if (isNicknameUnique(signUpModel.getNickname())) {
            throw new ParamException(BusinessCode.NICKNAME_REPEAT);
        }
        // 检查电话是否重复
        if (isPhoneExist(signUpModel.getPhone())) {
            throw new ParamException(BusinessCode.PHONE_HAS_SIGNED);
        }
        // 检查短信验证码是否有效
        if (!Constants.SIGN_UP_CHECK.equals(saveCode)) {
            throw new ParamException(BusinessCode.CODE_INVALID);
        }

        // 添加用户
        SignUser signUser = new SignUser();
        String salt = IDUtil.getUUID();
        String newPassword = MD5Util.md5WithSalt(signUpModel.getPassword(), salt);
        BeanUtils.copyProperties(signUpModel, signUser);
        signUser.setPassword(newPassword);
        signUser.setSalt(salt);
        signUser = signUserRepository.save(signUser);

        return new JsonResult<>(BusinessCode.SUCCESS, signUser);
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
        System.out.println("========:" + sendSmsResponse);
        System.out.println("===========code:" + sendSmsResponse.getCode());
        if (sendSmsResponse.getCode().equals("OK")) {
            redisService.set(Constants.PHONE_STRING, random, Constants.MSG_TIMEOUT, TimeUnit.MINUTES);
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

        // 用手机号取出保存的短信验证码
        String saveCode = redisService.get(Constants.PHONE_STRING);
        if (!saveCode.equals(msgCode)) {
            throw new ParamException(BusinessCode.CODE_ERROR);
        }

        // 在redis中生成token凭证
        String token = idUtil.genSysId(Constants.TOKEN_STRING, Constants.FORMAT_EIGHT);

        JSONObject result = new JSONObject();
        result.put("token", token);
        // 判断该手机号是否已注册
        Optional<SignUser> signUser = signUserRepository.findByPhone(phone);
        if (!signUser.isPresent()) {
            // 未注册value设为"check"
            redisService.set(token, Constants.SIGN_UP_CHECK, Constants.MSG_TIMEOUT, TimeUnit.MINUTES);
            return new JsonResult<>(BusinessCode.USER_WAITING_SIGN_UP, result);
        }
        signUser.ifPresent(
                t -> {
                    // 已注册value设为用户id
                    redisService.set(token, t.getId().toString());
                    result.put("id", t.getId());
                    result.put("phone", t.getPhone());
                    result.put("nickname", t.getNickname());
                    //TODO 图片地址
                    result.put("avatar", getImgUrl(t));
                }
        );

        return new JsonResult<>(BusinessCode.SUCCESS, result);
    }

    @Override
    public JsonResult usernameLogin(String username, String password) {
        JsonResult jsonResult = new JsonResult();
        if (null == username || null == password) {
            throw new ParamException("用户名或密码不能为空！");
        }
        // 从数据库取salt验证用户名、密码是否匹配
        Optional<SignUser> signUser = signUserRepository.findByNickname(username);
        signUser.orElseThrow(() -> {
            throw new ParamException(BusinessCode.USER_NOT_EXISTS);
        });

        signUser.ifPresent(
                t -> {
                    String salt = t.getSalt();
                    String pwd = MD5Util.md5WithSalt(password, salt);
                    if (username.equals(t.getNickname()) && pwd.equals(t.getPassword())) {
                        JSONObject result = new JSONObject();
                        // 在redis中生成token凭证
                        String token = idUtil.genSysId(Constants.TOKEN_STRING, Constants.FORMAT_EIGHT);
                        redisService.set(token, t.getId().toString());
                        result.put("token", token);
                        result.put("id", t.getId());
                        result.put("phone", t.getPhone());
                        result.put("nickname", t.getNickname());
                        result.put("avatar", getImgUrl(t));
                        jsonResult.set(BusinessCode.SUCCESS, result);
                    } else {
                        jsonResult.set(BusinessCode.PASSWORD_ERROR);
                    }
                }
        );
        return jsonResult;
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
