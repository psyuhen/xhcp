/**
 * 
 */
package com.huateng.xhcp.mapper.article;

import java.util.List;

import com.huateng.xhcp.model.article.Article;

/**
 * 资讯信息Mapper
 * @author sam.pan
 *
 */
public interface ArticleMapper {
	/**
	 * 查询资讯信息信息
	 * @param article
	 * @return
	 */
	public List<Article> queryArticle(Article article);

	/**
	 * 查上一篇与下一篇文章
	 * @param article_id
	 * @return
	 */
	public List<Article> queryPAndN(String article_id);
	/**
	 * 新增资讯信息信息
	 * @param article
	 */
	public int addArticle(Article article);
	/**
	 * 批量新增资讯信息信息
	 * @param article
	 */
	public void addBatchArticle(List<Article> article);
	/**
	 * 更新资讯信息信息
	 * @param article
	 */
	public int updateArticle(Article article);
	/**
	 * 根据article_id删除资讯信息信息
	 * @param article
	 */
	public int deleteArticle(Article article);
	/**
	 * 根据article_id批量删除资讯信息信息
	 * @param article
	 */
	public void deleteBatchArticle(List<Article> article);
}
