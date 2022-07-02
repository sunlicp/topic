package com.slcp.controller;

import com.slcp.api.Result;
import com.slcp.constant.DevOpsConstant;
import com.slcp.entity.Label;
import com.slcp.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Slcp
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/label")
@Api(value = "标签页查询接口", tags = "标签页查询接口")
public class LabelController {

    private final ILabelService labelService;

    @GetMapping("/loadList")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadLabelList", notes = "获取所有标签")
    public Result<List<Label>> loadLabelList() {
        return Result.data(labelService.list());
    }
}
