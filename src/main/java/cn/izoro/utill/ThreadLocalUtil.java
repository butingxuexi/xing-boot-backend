package cn.izoro.utill;

import cn.izoro.enums.GlobalErrorCodeConstants;
import cn.izoro.model.VO.UserRespVO;
import cn.izoro.utill.exception.ServiceExceptionUtil;

/**
 * @author zoro
 */
public final class ThreadLocalUtil {
    private static final ThreadLocal<UserRespVO> LOGIN_USER = ThreadLocal.withInitial(UserRespVO::new);

    public static UserRespVO getLoginUser() {
        UserRespVO user = LOGIN_USER.get();
        if (user == null || user.getId() == null) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.FORBIDDEN);
        }
        return user;
    }

    public static void setLoginUser(UserRespVO user) {
        LOGIN_USER.set(user);
    }

    public static void removeLoginUser() {
        LOGIN_USER.remove();
    }

    /**
     * 清空当前线程的所有 ThreadLocal 变量
     */
    public static void removeAll() {
        removeLoginUser();
    }
}