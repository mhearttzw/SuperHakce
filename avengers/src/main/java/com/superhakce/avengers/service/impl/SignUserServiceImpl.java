package com.superhakce.avengers.service.impl;

import com.superhakce.avengers.Constants;
import com.superhakce.avengers.entity.SignUser;
import com.superhakce.avengers.enums.BusinessCode;
import com.superhakce.avengers.exception.ParamException;
import com.superhakce.avengers.model.JsonResult;
import com.superhakce.avengers.model.userInfo.SignUpModel;
import com.superhakce.avengers.respository.SignUserRepository;
import com.superhakce.avengers.service.RedisService;
import com.superhakce.avengers.service.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SignUserServiceImpl implements SignUserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SignUserRepository signUserRepository;

    @Override
    public JsonResult register(SignUpModel signUpModel) {

        String saveCode = redisService.get(Constants.PHONE_STRING + signUpModel.getPhone());

        // 检查昵称是否重复
        if (isNicknameUnique(signUpModel.getNickname())) {
            throw new ParamException(BusinessCode.NICKNAME_REPEAT);
        }
        // 检查电话是否重复
        if (isPhoneUnique(signUpModel.getPhone())) {
            throw new ParamException(BusinessCode.PHONE_HAS_SIGNED);
        }




        else if (ucenterUserService.exists(phone)) {
            //1、用户已存在
            urs = new UcenterResult(UcenterResultConstant.USER_EXISTS, "");
        } else if ( !saveCode.equals("check") ) {
            //2、验证码不正确
            urs = new UcenterResult(UcenterResultConstant.CODE_ERROR, "");
        } else {
            // 生成用户uuid
            String userUuid = CommonUtil.getUUID();
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            String newPassword = MD5Util.md5(password + salt);
            // 用户信息插入数据库
            int v = ucenterUserService.insertSimple(phone, newPassword, salt, nickname, userUuid);
            if (v == 1) {
                // 如果注册通过，生成一个32位的token
                String token = ucenterUserService.saveToken1(userUuid);
                // 将这个token返回给前端
                Map<String, String> tokenMap = new HashMap<String, String>();
                tokenMap.put("token", token);
                //3、成功注册
                urs = new UcenterResult(UcenterResultConstant.SUCCESS, tokenMap);
            } else {
                //4、注册失败
                urs = new UcenterResult(UcenterResultConstant.FAILED, "注册失败");
            }
        }
        return urs;
        return null;
    }



    /**
     * 判断昵称是否唯一
     * @param nickname
     * @return
     */
    public boolean isNicknameUnique(String nickname) {
        List<SignUser> list = signUserRepository.findByNickname(nickname);
        if ( list != null && list.size() >= 1 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断该电话号码是否已注册
     * @param phone
     * @return
     */
    public boolean isPhoneUnique(String phone) {
        List<SignUser> list = signUserRepository.findByPhone(phone);
        if ( list != null && list.size() >= 1 ) {
            return true;
        } else {
            return false;
        }
    }

}
