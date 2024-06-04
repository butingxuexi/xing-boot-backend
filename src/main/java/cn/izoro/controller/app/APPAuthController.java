package cn.izoro.controller.app;

import cn.izoro.common.CommonResult;
import cn.izoro.model.VO.LoginReqVO;
import cn.izoro.model.VO.UserRespVO;
import cn.izoro.model.VO.RegisterReqVO;
import cn.izoro.service.SystemUsersService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author zoro
 */
@RestController
@RequestMapping("/user")
public class APPAuthController {

    @Resource
    private SystemUsersService SystemUsersService;
    @PostMapping("/login")
    public CommonResult<UserRespVO> login(@Validated @RequestBody LoginReqVO authLoginReqVO, HttpServletRequest request) {
        UserRespVO authLoginRespVO = SystemUsersService.login(authLoginReqVO,request);
        return CommonResult.success(authLoginRespVO);
    }

    @PostMapping("/register")
    public CommonResult<Long> register(@Validated @RequestBody RegisterReqVO authLoginReqVO) {
        return CommonResult.success(SystemUsersService.register(authLoginReqVO));
    }
    @PostMapping("/logout")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        return CommonResult.success(SystemUsersService.logout(request));
    }

}
