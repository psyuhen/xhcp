/**
 * 
 */
package com.huateng.xhcp.mapper.article;

import java.util.List;

import com.huateng.xhcp.model.article.Article;
import org.apache.ibatis.annotations.Param;

/**
 * 资讯信息Mapper
 * @author sam.pan
 *
 */
public interface ArticleMapper {
	/**
	 * 查询资讯信息信息
	 * @param keywords
	 * @return
	 */
	List<Article> searchKeyWord(@Param("keywords") String [] keywords);
	/**
	 * 查询资讯信息信息
	 * @param article
	 * @return
	 */
	List<Article> queryArticle(Article article);

	/**
	 * 查上一篇与下一篇文章
	 * @param article_id
	 * @return
	 */
	List<Article> queryPAndN(String article_id);
	/**
	 * 新增资讯信息信息
	 * @param article
	 */
	int addArticle(Article article);
	/**
	 * 批量新增资讯信息信息
	 * @param article
	 */
	void addBatchArticle(List<Article> article);
	/**
	 * 更新资讯信息信息
	 * @param article
	 */
	int updateArticle(Article article);
	/**
	 * 更新点击数
	 * @param article_id
	 */
	int updateHits(String article_id);
	/**
	 * 根据article_id删除资讯信息信息
	 * @param article
	 */
	int deleteArticle(Article article);
	/**
	 * 根据article_id批量删除资讯信息信息
	 * @param article
	 */
	void deleteBatchArticle(List<Article> article);
}
