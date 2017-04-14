/**
 * 
 */
package com.huateng.xhcp.service.imp.article;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.article.ArticleMapper;
import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.service.article.ArticleService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 资讯信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class ArticleServiceImp implements ArticleService {

	private @Autowired @Setter @Getter ArticleMapper articleMapper;
	/**
	 * 查询资讯信息信息
	 * @param keywords
	 * @return
	 */
	public List<Article> searchKeyWord(String [] keywords){
		return this.articleMapper.searchKeyWord(keywords);
	}
	/**
	 * 查询资讯信息信息
	 * @param article
	 * @return
	 */
	public List<Article> queryArticle(Article article){
		int start = article.getStart();
		int limit = article.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.articleMapper.queryArticle(article);
	}
	/**
	 * 查上一篇与下一篇文章
	 * @param article_id
	 * @return
	 */
	public List<Article> queryPAndN(String article_id){
		return this.articleMapper.queryPAndN(article_id);
	}
	/**
	 * 根据Key查询资讯信息信息
	 * @param article_id
	 * @return
	 */
	public Article queryByKey(String article_id){
		Article article = new Article();
		article.setArticle_id(article_id);
		List<Article> list = this.articleMapper.queryArticle(article);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增资讯信息信息
	 * @param article
	 */
	public int addArticle(Article article){
		return this.articleMapper.addArticle(article);
	}
	/**
	 * 批量新增资讯信息信息
	 * @param article
	 */
	public void addBatchArticle(List<Article> article){
		if(article == null || article.isEmpty()){
			return;
		}
		this.articleMapper.addBatchArticle(article);
	}
	/**
	 * 更新资讯信息信息
	 * @param article
	 */
	public int updateArticle(Article article){
		return this.articleMapper.updateArticle(article);
	}

	/**
	 * 更新点击数
	 * @param article_id
	 */
	public int updateHits(String article_id){
		return this.articleMapper.updateHits(article_id);
	}
	/**
	 * 根据article_id删除资讯信息信息
	 * @param article
	 */
	public int deleteArticle(Article article){
		return this.articleMapper.deleteArticle(article);
	}
	/**
	 * 根据article_id批量删除资讯信息信息
	 * @param article
	 */
	public void deleteBatchArticle(List<Article> article){
		if(article == null || article.isEmpty()){
			return;
		}
		this.articleMapper.deleteBatchArticle(article);
	}
}
