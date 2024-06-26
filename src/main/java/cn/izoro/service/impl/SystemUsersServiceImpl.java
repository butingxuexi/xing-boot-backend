package cn.izoro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.izoro.convert.SystemUserConvert;
import cn.izoro.enums.GlobalErrorCodeConstants;
import cn.izoro.mapper.SystemUsersMapper;
import cn.izoro.model.entity.SystemUserDO;
import cn.izoro.model.vo.login.LoginReqVO;
import cn.izoro.model.vo.login.RegisterReqVO;
import cn.izoro.model.vo.login.UserRespVO;
import cn.izoro.service.SystemUsersService;
import cn.izoro.utill.mybatis.QueryWrapperX;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import static cn.izoro.enums.ServiceErrorCodeRange.*;
import static cn.izoro.enums.UserConstant.USER_LOGIN_STATE;
import static cn.izoro.utill.exception.ServiceExceptionUtil.exception;

/**
 * @author zoro
 */
@Service
@Slf4j
public class SystemUsersServiceImpl extends ServiceImpl<SystemUsersMapper, SystemUserDO>
        implements SystemUsersService {
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "xing";

    @Override
    public UserRespVO login(LoginReqVO authLoginReqVO, HttpServletRequest request) {
        // 加密密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + authLoginReqVO.getPassword()).getBytes());

        // 查询用户是否存在
        QueryWrapperX<SystemUserDO> queryWrapperX = new QueryWrapperX<>();
        queryWrapperX.eq("username", authLoginReqVO.getUsername())
                .eq("password", encryptPassword);
        SystemUserDO systemUserDO = this.baseMapper.selectOne(queryWrapperX);

        if (ObjectUtil.isNotEmpty(systemUserDO)) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 设置登录状态
        request.getSession().setAttribute(USER_LOGIN_STATE, systemUserDO);

        return SystemUserConvert.INSTANCE.toRespVO(systemUserDO);
    }

    @Override
    public Long register(RegisterReqVO registerReqVO) {
        // 密码和校验密码相同
        if (!registerReqVO.getPassword().equals(registerReqVO.getCheckPassword())) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST, "两次输入的密码不一致");
        }
        // 数据库中是否已经有相同的用户名
        QueryWrapper<SystemUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", registerReqVO.getUsername());
        Long count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw exception(AUTH_USERNAME_USED);
        }

        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + registerReqVO.getPassword()).getBytes());
        SystemUserDO systemUserDO = SystemUserConvert.INSTANCE.toDO(registerReqVO);
        systemUserDO.setPassword(encryptPassword);
        systemUserDO.setNickname(systemUserDO.getUsername());
        systemUserDO.setCreator("register");
        systemUserDO.setUpdater("register");
        int insert = this.baseMapper.insert(systemUserDO);
        if (insert <= 0) {
            throw exception(AUTH_USERNAME_USED);
        }
        return systemUserDO.getId();

    }

    @Override
    public Boolean logout(HttpServletRequest request) {
        if (ObjectUtil.isEmpty(request)) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        if (BeanUtil.isEmpty(request.getSession().getAttribute(USER_LOGIN_STATE))) {
            throw exception(GlobalErrorCodeConstants.UNAUTHORIZED);
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);

        return true;
    }

    @Override
    public UserRespVO getLoginUser(HttpServletRequest request) {
        // 判断用户是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        UserRespVO currentUser = (UserRespVO) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw exception(USER_NOT_LOGIN);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        SystemUserDO userDOById = this.getById(userId);
        currentUser = SystemUserConvert.INSTANCE.toRespVO(userDOById);
        if (currentUser == null) {
            throw exception(USER_NOT_EXISTS);
        }
        return currentUser;
    }
}




