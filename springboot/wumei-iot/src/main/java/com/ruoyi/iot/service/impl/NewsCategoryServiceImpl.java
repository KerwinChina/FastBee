package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.iot.model.IdAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.NewsCategoryMapper;
import com.ruoyi.iot.domain.NewsCategory;
import com.ruoyi.iot.service.INewsCategoryService;

/**
 * 新闻分类Service业务层处理
 * 
 * @author kerwincui
 * @date 2022-04-09
 */
@Service
public class NewsCategoryServiceImpl implements INewsCategoryService 
{
    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    /**
     * 查询新闻分类
     * 
     * @param categoryId 新闻分类主键
     * @return 新闻分类
     */
    @Override
    public NewsCategory selectNewsCategoryByCategoryId(Long categoryId)
    {
        return newsCategoryMapper.selectNewsCategoryByCategoryId(categoryId);
    }

    /**
     * 查询新闻分类列表
     * 
     * @param newsCategory 新闻分类
     * @return 新闻分类
     */
    @Override
    public List<NewsCategory> selectNewsCategoryList(NewsCategory newsCategory)
    {
        return newsCategoryMapper.selectNewsCategoryList(newsCategory);
    }

    /**
     * 查询新闻分类简短列表
     *
     * @return 新闻分类
     */
    @Override
    public List<IdAndName> selectNewsCategoryShortList()
    {
        return newsCategoryMapper.selectNewsCategoryShortList();
    }

    /**
     * 新增新闻分类
     * 
     * @param newsCategory 新闻分类
     * @return 结果
     */
    @Override
    public int insertNewsCategory(NewsCategory newsCategory)
    {
        newsCategory.setCreateTime(DateUtils.getNowDate());
        return newsCategoryMapper.insertNewsCategory(newsCategory);
    }

    /**
     * 修改新闻分类
     * 
     * @param newsCategory 新闻分类
     * @return 结果
     */
    @Override
    public int updateNewsCategory(NewsCategory newsCategory)
    {
        newsCategory.setUpdateTime(DateUtils.getNowDate());
        return newsCategoryMapper.updateNewsCategory(newsCategory);
    }

    /**
     * 批量删除新闻分类
     * 
     * @param categoryIds 需要删除的新闻分类主键
     * @return 结果
     */
    @Override
    public int deleteNewsCategoryByCategoryIds(Long[] categoryIds)
    {
        return newsCategoryMapper.deleteNewsCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除新闻分类信息
     * 
     * @param categoryId 新闻分类主键
     * @return 结果
     */
    @Override
    public int deleteNewsCategoryByCategoryId(Long categoryId)
    {
        return newsCategoryMapper.deleteNewsCategoryByCategoryId(categoryId);
    }
}
