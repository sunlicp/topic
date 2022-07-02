package com.slcp.controller;

import cn.hutool.core.util.ObjectUtil;
import com.slcp.api.Result;
import com.slcp.constant.DevOpsConstant;
import com.slcp.entity.User;
import com.slcp.service.IUserService;
import com.slcp.utils.MD5Util;
import io.swagger.annotations.Api;
import java.util.Objects;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Slcp
 */
@RestController
@RequestMapping({"/user"})
@AllArgsConstructor
@Api(
        value = "用户信息查询接口",
        tags = {"用户信息查询接口"}
)
@Slf4j
public class UserController {

    private final IUserService userService;

    @PostMapping({"/register"})
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "register", notes = "注册")
    public Result<String> register(@RequestBody User user) {
        try {
            User u = userService.getUser(user.getUsername());
            if (ObjectUtil.isNotEmpty(u)) {
                return Result.fail("用户名已存在");
            } else {
                user.setPassword(MD5Util.code(user.getPassword()));
                this.userService.saveOrUpdate(user);
                return Result.success("注册成功");
            }
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return Result.fail("出错了，请稍后尝试");
        }
    }

    @PostMapping({"/login"})
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "login", notes = "登录")
    public Result<User> login(@RequestBody User user) {
        try {
            User u = this.userService.getUser(user.getUsername());
            if (ObjectUtil.isEmpty(u)) {
                return Result.fail("用户名不存在");
            } else if (!u.getPassword().equals(MD5Util.code(user.getPassword()))) {
                return Result.fail("密码错误");
            } else {
                u.setPassword(null);
                if (u.getPicture() == null || Objects.equals(u.getPicture(), "")) {
                    u.setPicture("/static/user.png");
                }
                return Result.data(u);
            }
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return Result.fail("出错了，请稍后重试");
        }
    }

    @PostMapping({"/update"})
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "updatePassword", notes = "修改密码")
    public Result<String> updatePassword(@RequestBody User user) {
        try {
            userService.lambdaUpdate().set(User::getPassword, MD5Util.code(user.getPassword())).eq(User::getUsername, user.getUsername()).update();
            return Result.success("OK");
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return Result.fail("出错了，请稍后重试");
        }
    }

    @GetMapping({"/getName"})
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadLabelList", notes = "根据标题获取用户")
    public Result<User> getNameByTitle(String title) {
        try {
            User user = this.userService.getUserByTitle(title);
            if (user.getPicture() == null || Objects.equals(user.getPicture(), "")) {
                user.setPicture("/static/user.png");
            }
            return Result.data(user);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return Result.fail("出错了，请稍后重试");
        }
    }

    @GetMapping({"/loadUser"})
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadUserAndAvatar", notes = "根据用户名获取用户")
    public Result<User> loadUserAndAvatar(String username) {
        try {
            User user = this.userService.getUser(username);
            return Result.data(user);
        } catch (Exception var3) {
            log.error(var3.getMessage());
            return Result.fail("出错了，请稍后重试");
        }
    }

}
