package com.superhakce.avengers.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author           echelon
 * @email            2954632969@qq.com
 * @created_time     2018/11/22 16:11
 * @description      业务码
 */
@Getter
@AllArgsConstructor
public enum BusinessCode {
    SUCCESS("0000","成功"),
    FAILED("1000", "失败"),
    THIRD_CALL_FAIL("1001","第三方服务调用失败"),

    /**
     * 失败
     */
    REPEAT_REQUEST("1002", "验证码有效时间内不需重复请求！"),
    USER_NOT_EXISTS("1003", "用户不存在！"),
    SEND_FAIL("1004", "发送验证码失败！请稍后重试！"),
    CODE_ERROR("1005", "验证码不正确！"),
    CODE_INVALID("1006", "验证码已失效！请重新请求验证码！"),
    PASSWORD_ERROR("1007", "密码不正确！"),
    IMG_UPLOAD_FAILED("1008", "图片处理失败！"),
    NOT_LOGIN("1009", "未登录！"),
    NICKNAME_REPEAT("1010", "昵称重复！"),
    NO_MORE_PAGE("1011", "没有下一页了！"),
    PASSAGE_NOT_EXISTS("1012", "文章不存在！"),
    COMMENT_INSERT_FAILD("1013", "评论失败！"),
    FOLLOW_FAILED("1014", "关注失败！"),
    UNFOLLOW_FAILED("1015", "取消关注失败！"),
    COMMENT_DELETE_FAILED("1016", "删除评论失败！"),
    USER_EXISTS("1017", "用户已存在！"),
    PHONE_HAS_SIGNED("1018", "该电话号码已注册！"),
    PHONE_FORMAT_INCORRECT("1019", "手机号格式不正确！"),
    USER_WAITING_SIGN_UP("1020", "用户需注册"),



    PARAMETER_CANNOT_BE_NULL("5011", "参数不能为空"),
    NOT_FIND_RESULT("5012", "未查询到结果"),
    OVER_QUOTA_AMOUNT("5013", "超过客户限额，不能新增"),
    SINCERITY_EXCEPTION("5014", "业务系统自定义异常"),
    MESSAGE_SENT_FAIL("5015", "短信发生失败"),

    PARAMETER_IS_INCORRECT("5024", "参数不正确"),
    FEIGN_FAIL("5027", "feign接口调用失败"),

    FILE_NOT_EXIST("5030", "文件不存在"),
    ;



    private String code;

    private String msg;


}
