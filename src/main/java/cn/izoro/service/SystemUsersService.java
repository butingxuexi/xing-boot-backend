package cn.izoro.service;

import cn.izoro.model.vo.login.LoginReqVO;
import cn.izoro.model.vo.login.UserRespVO;
import cn.izoro.model.vo.login.RegisterReqVO;
import cn.izoro.model.entity.SystemUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author zoro
*/
public interface SystemUsersService extends IService<SystemUserDO> {

    /**
     * 用户登录
     * @param authLoginReqVO
     * @return
     */
    UserRespVO login(LoginReqVO authLoginReqVO, HttpServletRequest request);

    /**
     * 用户注册
     * @param authLoginReqVO
     * @return
     */
    Long register( RegisterReqVO authLoginReqVO);

    Boolean logout(HttpServletRequest request);

    /**
     * 获取登录用户
     * @param request
     * @return
     */
    UserRespVO getLoginUser(HttpServletRequest request);
}
