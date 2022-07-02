package com.slcp.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.api.Result;
import com.slcp.constant.DevOpsConstant;
import com.slcp.entity.Blog;
import com.slcp.entity.BlogStar;
import com.slcp.entity.Search;
import com.slcp.service.IBlogService;
import com.slcp.service.IStarService;
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
@RequestMapping("/content")
@Api(value = "主页接口查询", tags = "主页接口查询")
public class BlogController {

    private final IBlogService blogService;
    private final IStarService starService;

    @PostMapping("/save")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_POST, value = "saveContent", notes = "保存文章")
    public Result<Long> saveContent(@RequestBody Blog blog) {
        List<Blog> blogList = blogService.lambdaQuery().eq(Blog::getTitle, blog.getTitle()).list();
        if (ObjectUtil.isNotEmpty(blogList) || blogList.size() > 0) {
            return Result.fail("标题已存在");
        }
        blogService.saveOrUpdate(blog);
        return Result.data(blog.getId());
    }

    @GetMapping("/loadLabel")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadLabelList", notes = "获取已有标签")
    public Result<List<String>> loadLabelList(String username) {
        List<String> labelList = blogService.getLabelList(username);
        return Result.data(labelList);

    }

    @GetMapping("/loadHotLabel")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadHotLabelList", notes = "获取热门标签")
    public Result<List<String>> loadHotLabelList() {
        List<String> labelList = blogService.loadHotLabel();
        return Result.data(labelList);
    }

    @GetMapping("/loadTitle")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadTitleList", notes = "加载标题")
    public Result<List<String>> loadTitleList(String label, String username) {
        List<String> titleList = ListUtil.list(false);
        blogService.lambdaQuery().eq(Blog::getLabel, label).eq(Blog::getUserName, username)
                .list()
                .forEach(b -> titleList.add(b.getTitle()));
        return Result.data(titleList);

    }

    @GetMapping("/loadHotTitle")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadHotTitleList", notes = "获取热门标题")
    public Result<List<String>> loadHotTitleList() {
        List<String> titleList = blogService.loadHotTitle();
        return Result.data(titleList);

    }

    @GetMapping("/loadContent")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadContent", notes = "加载文章内容")
    public Result<String> loadContent(String title) {
        return Result.data(blogService.lambdaQuery().eq(Blog::getTitle, title).one().getContentText());
    }

    @GetMapping("/loadBlogOnUpd")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadBlogOnUpd", notes = "根据标题获取文章")
    public Result<Blog> loadBlogOnUpd(String title) {
        return Result.data(blogService.lambdaQuery().eq(Blog::getTitle, title).one());
    }

    @GetMapping("/loadBlogList")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadBlogList", notes = "查询文章接口")
    public Result<List<Blog>> loadBlogList() {
        List<Blog> blogList = blogService.loadBlogList();
        return Result.data(blogList);
    }

    @GetMapping("/loadBlogListByLabel")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadBlogListByLabel", notes = "根据标签，分页获取文章")
    public Result<IPage<Blog>> loadBlogListByLabel(@RequestParam("label") String label, Search search) {
        IPage<Blog> page = blogService.loadBlogListByLabel(label, search);
        return Result.data(page);
    }

    @GetMapping("/judgeStar")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "judgeExistStar", notes = "查看文章是否被用户收藏")
    public Result<Boolean> judgeExistStar(String title, String username) {
        //判断是否收藏
        return Result.data(starService.existStar(title, username));
    }

    @GetMapping("/handleStar")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "handleStar", notes = "收藏")
    public Result<BlogStar> handleStar(BlogStar star) {
        String title = star.getBlogTitle();
        String username = star.getUsername();
        boolean flag = starService.existStar(title, username);
        if (flag) {
            starService.lambdaUpdate().eq(BlogStar::getBlogTitle, title).eq(BlogStar::getUsername, username).remove();
        } else {
            starService.save(star);
        }
        return Result.data(star);

    }

    @GetMapping("/loadStarBlog")
    @ApiOperation(httpMethod = DevOpsConstant.METHOD_GET, value = "loadStarBlog", notes = "获取用户收藏文章")
    public Result<IPage<Blog>> loadStarBlog(String username, Search search) {
        return Result.data(blogService.loadStarBlog(username, search));
    }
}
