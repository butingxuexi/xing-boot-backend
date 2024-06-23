package cn.izoro.controller.app;

import cn.izoro.common.CommonResult;
import cn.izoro.model.vo.login.LoginReqVO;
import cn.izoro.model.vo.login.UserRespVO;
import cn.izoro.model.vo.login.RegisterReqVO;
import cn.izoro.service.SystemUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "APP登录注册模块")
public class APPAuthController {

    @Resource
    private SystemUsersService SystemUsersService;
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public CommonResult<UserRespVO> login(@Validated @RequestBody LoginReqVO authLoginReqVO, HttpServletRequest request) {
        UserRespVO authLoginRespVO = SystemUsersService.login(authLoginReqVO,request);
        return CommonResult.success(authLoginRespVO);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public CommonResult<Long> register(@Validated @RequestBody RegisterReqVO authLoginReqVO) {
        return CommonResult.success(SystemUsersService.register(authLoginReqVO));
    }
    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        return CommonResult.success(SystemUsersService.logout(request));
    }

}
