package com.want.wantworld.utils;

import org.springframework.util.StringUtils;

import com.want.wantworld.vo.WResult;

public class WResultUtil {

    private WResultUtil() {
    }

    private static <E> WResult<E> wrap(E o) {
        return new WResult<>(WResult.OK.value(), WResult.OK.getReasonPhrase(), o);
    }

    private static <E> WResult<E> wrap(String message, E o) {
        return new WResult<>(WResult.OK.value(), message, o);
    }

    private static <E> WResult<E> wrap(int code) {
        return wrap(code, null);
    }

    private static <E> WResult<E> wrap(int code, String message) {
        return wrap(code, message, null);
    }

    private static <E> WResult<E> wrap(int code, String message, E o) {
        return new WResult<>(code, message, o);
    }

    /**
     * 处理成功. default code =200
     * 如需定义更加细致的成功，每个业务各自定义，但是务必用四位数，2XXX格式
     */
    public static <E> WResult<E> ok() {
        return new WResult<>(WResult.OK);
    }

    public static <E> WResult<E> ok(E o) {
        return new WResult<>(WResult.OK.value(), WResult.OK.getReasonPhrase(), o);
    }

    public static <E> WResult<E> ok(String message, E o) {
        return new WResult<>(WResult.OK.value(), message, o);
    }

    public static <E> WResult<E> ok(int code, String message, E o) {
        return new WResult<>(code, message, o);
    }

    /**
     * 参数校验失败，default code = 4001
     *
     * @return the wrapper
     * 如果要区分更加细致的场景，每个业务需定义各自的参数校验错误码，但是务必用四位数，4XXX格式
     */
    public static <E> WResult<E> paramError() {
        return wrap(WResult.E6_ERROR_PARAM_ERROR_CODE, WResult.E6_ERROR_PARAM_ERROR_MSG);
    }

    public static WResult paramError(String message) {
        return wrap(WResult.E6_ERROR_PARAM_ERROR_CODE, message);
    }

    public static WResult paramError(int code, String message) {
        return wrap(code, message);
    }

    /**
     * 服务端代码异常. default code=500
     * 用error的情况：
     * 1.缺少权限校验参数 (需用203)
     * 2.无权限访问 (需用403)
     * 3.内部业务逻辑错误（可定义四位数错误码,6XXX格式）
     * 4.内部其他异常或错误（可定义四位数错误码,5XXX格式）
     */
    public static <E> WResult<E> error() {
        return new WResult(WResult.INTERNAL_SERVER_ERROR);
    }

    public static <E> WResult<E> error(Exception e) {
        return wrap(WResult.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }

    public static <E> WResult<E> error(int code, Exception e) {
        return wrap(code, e.getMessage(), null);
    }

    public static <E> WResult<E> error(String message) {
        return wrap(WResult.INTERNAL_SERVER_ERROR.value(), StringUtils.isEmpty(message) ? WResult.INTERNAL_SERVER_ERROR.getReasonPhrase() : message);
    }

    public static <E> WResult<E> error(Integer code, String message) {
        return wrap(code, message);
    }

    public static <E> WResult<E> error(String message, E o) {
        return wrap(WResult.INTERNAL_SERVER_ERROR.value(), StringUtils.isEmpty(message) ? WResult.INTERNAL_SERVER_ERROR.getReasonPhrase() : message, o);
    }

    public static <E> WResult<E> error(Integer code, String message, E o) {
        return wrap(code, message, o);
    }


}
