package cn.izoro.utill;

import cn.izoro.enums.GlobalErrorCodeConstants;
import cn.izoro.model.vo.login.UserRespVO;
import cn.izoro.utill.exception.ServiceExceptionUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zoro
 */
public final class ThreadLocalUtil {
    private static final ThreadLocal<UserRespVO> LOGIN_USER = ThreadLocal.withInitial(UserRespVO::new);

    public static UserRespVO getLoginUser() {

        UserRespVO user = LOGIN_USER.get();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUri = request.getRequestURI();
        // 如果请求为用户注册，则不进行数据查询用户信息
        if (requestUri.contains("register")) {
            return user;
        }
        // 判断用户是否登录
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