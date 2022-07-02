package com.slcp.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.slcp.api.Result;
import com.slcp.constant.DevOpsConstant;
import com.slcp.entity.User;
import com.slcp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Slcp
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@Api(value = "图片页查询接口", tags = "图片页查询接口")
public class PictureController {

    private final IUserService userService;

    @PostMapping("/uploadImg")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "imgUpload", notes = "图片上传")
    public Result<String> imgUpload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        String username = request.getParameter("username");
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在");
            filePath.mkdir();
        }
        String originalFilename = picture.getOriginalFilename();
        if (StrUtil.isEmpty(originalFilename)) {
            originalFilename = UUID.fastUUID() + ".jpg";
        }
        //获取文件类型，以最后一个`.`为标识
        String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String newFileName = date + "." + type;

        //在指定路径下创建一个文件
        File targetFile = new File(path, newFileName);
        //将文件保存到服务器指定位置
        String url = "http://localhost:8080/blog/upload/" + newFileName;
        userService.lambdaUpdate().eq(User::getUsername, username).set(User::getUsername, username).update();

        try {
            picture.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return Result.data(url);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败");
        }
    }
}
