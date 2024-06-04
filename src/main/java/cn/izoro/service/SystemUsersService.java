package cn.izoro.service;

import cn.izoro.model.VO.LoginReqVO;
import cn.izoro.model.VO.userRespVO;
import cn.izoro.model.VO.RegisterReqVO;
import cn.izoro.model.dataobject.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author zoro
*/
public interface SystemUsersService extends IService<SystemUser> {

    /**
     * 用户登录
     * @param authLoginReqVO
     * @return
     */
    userRespVO login(LoginReqVO authLoginReqVO, HttpServletRequest request);

    /**
     * 用户注册
     * @param authLoginReqVO
     * @return
     */
    Long register( RegisterReqVO authLoginReqVO);

    Boolean logout(HttpServletRequest request);
}
