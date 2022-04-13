package com.ruoyi.iot.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.iot.model.IdAndName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.iot.domain.NewsCategory;
import com.ruoyi.iot.service.INewsCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻分类Controller
 * 
 * @author kerwincui
 * @date 2022-04-09
 */
@Api(tags = "新闻分类")
@RestController
@RequestMapping("/iot/newsCategory")
public class NewsCategoryController extends BaseController
{
    @Autowired
    private INewsCategoryService newsCategoryService;

    /**
     * 查询新闻分类列表
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:list')")
    @GetMapping("/list")
    @ApiOperation("新闻分类分页列表")
    public TableDataInfo list(NewsCategory newsCategory)
    {
        startPage();
        List<NewsCategory> list = newsCategoryService.selectNewsCategoryList(newsCategory);
        return getDataTable(list);
    }

    /**
     * 查询新闻分类简短列表
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:list')")
    @GetMapping("/newsCategoryShortList")
    @ApiOperation("分类简短列表")
    public AjaxResult newsCategoryShortList()
    {
        List<IdAndName> list = newsCategoryService.selectNewsCategoryShortList();
        return AjaxResult.success(list);
    }

    /**
     * 导出新闻分类列表
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:export')")
    @Log(title = "新闻分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsCategory newsCategory)
    {
        List<NewsCategory> list = newsCategoryService.selectNewsCategoryList(newsCategory);
        ExcelUtil<NewsCategory> util = new ExcelUtil<NewsCategory>(NewsCategory.class);
        util.exportExcel(response, list, "新闻分类数据");
    }

    /**
     * 获取新闻分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:query')")
    @GetMapping(value = "/{categoryId}")
    @ApiOperation("新闻分类详情")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(newsCategoryService.selectNewsCategoryByCategoryId(categoryId));
    }

    /**
     * 新增新闻分类
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:add')")
    @Log(title = "新闻分类", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("添加新闻分类")
    public AjaxResult add(@RequestBody NewsCategory newsCategory)
    {
        return toAjax(newsCategoryService.insertNewsCategory(newsCategory));
    }

    /**
     * 修改新闻分类
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:edit')")
    @Log(title = "新闻分类", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改新闻分类")
    public AjaxResult edit(@RequestBody NewsCategory newsCategory)
    {
        return toAjax(newsCategoryService.updateNewsCategory(newsCategory));
    }

    /**
     * 删除新闻分类
     */
    @PreAuthorize("@ss.hasPermi('iot:newsCategory:remove')")
    @Log(title = "新闻分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    @ApiOperation("删除新闻分类")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(newsCategoryService.deleteNewsCategoryByCategoryIds(categoryIds));
    }
}
