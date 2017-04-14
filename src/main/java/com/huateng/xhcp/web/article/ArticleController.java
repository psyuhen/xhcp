/**
 * 
 */
package com.huateng.xhcp.web.article;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.huateng.xhcp.model.kindeditor.KEMsg;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.util.DateUtil;
import com.huateng.xhcp.util.PropertiesReader;
import com.huateng.xhcp.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.service.article.ArticleService;
import com.huateng.xhcp.util.HttpUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 资讯信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class ArticleController implements com.huateng.xhcp.service.upload.Validator<Article>{
	private static final Log LOGGER = LogFactory.getLog(ArticleController.class);
	private @Autowired @Setter @Getter ArticleService articleService;

    /**
     * 资讯详情
     * @return
     */
    @RequestMapping(value = "/article/news-{article_id}.html")
    public String toNewsDetailPage(HttpServletRequest request, @PathVariable String article_id){
        if(StringUtils.isBlank(article_id)){
            return "common/404";
        }

        Article article = this.articleService.queryByKey(article_id);
        if(article == null){
            return "common/404";
        }

		List<Article> pAndN = this.articleService.queryPAndN(article_id);

        this.articleService.updateHits(article_id);

		request.setAttribute("articleNew", article);
		request.setAttribute("articlePAndN", pAndN);
        return "article/newsdetail";
    }
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping(value = "/article/allnews.html")
    public String toAllNewsPage(HttpServletRequest request){
        List<String> list = DateUtil.getYears(6);

        request.setAttribute("newsYears", list);
        return "article/news";
    }
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/article/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "newmgr");
		return "article/ArticleList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/article/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "newmgr");
		return "article/ArticleList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/article/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "newmgr");
		return "article/ArticleAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/article/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "newmgr");
		return "article/ArticleAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/article/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "article/ArticleAdd";
	}
	
	/**
	 * 查询资讯信息信息(分页)
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/queryArticlePage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryArticle(Article article){
		Page<Article> list = (Page<Article>)this.articleService.queryArticle(article);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}

    /**
     * 首页查新闻
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/article/news", method = RequestMethod.POST)
    public List<Article> queryByPage(String page,String year,String month){
        int pageNum = 5;
        Article article = new Article();
        if(!StringUtils.isBlank(page)){ // 根据页数查数据
            pageNum = 3;
            int p = StringUtils.isBlank(page) ? 1 : Integer.parseInt(page);
            int start = p * pageNum - 2;

            article.setLimit(3);
            article.setStart(start);
        }else{
            article.setLimit(15);
            article.setStart(1);

            if(!StringUtils.isBlank(year)){
                article.setArticle_y(year);
            }

            if(!StringUtils.isBlank(month)){
                article.setArticle_m(month);
            }
        }

        List<Article> articles = articleService.queryArticle(article);
		StringUtil.rmContentsHtml(articles);
        return articles;
    }
	/**
	 * 查上一篇与下一篇文章
	 * @param article_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/queryPAndN", method = RequestMethod.GET)
	public List<Article> queryPAndN(String article_id){
		return this.articleService.queryPAndN(article_id);
	}
	/**
	 * 根据Key查询资讯信息信息
	 * @param article_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/queryByKey", method = RequestMethod.GET)
	public Article queryByKey(String article_id){
		return this.articleService.queryByKey(article_id);
	}
	/**
	 * 新增资讯信息信息
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/addArticle", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addArticle(Article article){
		try{
			ResponseInfo responseInfo = validate(article);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.articleService.addArticle(article);
			if(c==0){
				return HttpUtil.failure("新增资讯信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增资讯信息失败!");
		}
		
		return HttpUtil.success("新增资讯信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(Article article){
		if(article == null){
			return ResponseInfo.fail("资讯信息信息为空!");
		}
		
		String title = article.getTitle();
		int titleLength = com.huateng.xhcp.util.Validator.mysql(title);
		if(titleLength > 200){
			return ResponseInfo.fail("标题必须少于200 个字符!");
		}

		String article_date = article.getArticle_date();
		int article_dateLength = com.huateng.xhcp.util.Validator.mysql(article_date);
		if(article_dateLength > 8){
			return ResponseInfo.fail("标题必须少于8 个字符!");
		}

		String contents = article.getContents();
		int contentsLength = com.huateng.xhcp.util.Validator.mysql(contents);
		if(contentsLength > 10000){
			return ResponseInfo.fail("内容必须少于10000 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新资讯信息信息
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/updateArticle", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateArticle(Article article){
		try{
			ResponseInfo responseInfo = validate(article);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.articleService.updateArticle(article);
			if(c == 0){
				return HttpUtil.failure("更新资讯信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新资讯信息失败!");
		}
		
		return HttpUtil.success("更新资讯信息成功!");
	}
	
	/**
	 * 删除资讯信息信息
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/deleteArticle", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteArticle(Article article){
		try{
			int c = this.articleService.deleteArticle(article);
			if(c == 0){
				return HttpUtil.failure("删除资讯信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除资讯信息失败!");
		}
		
		return HttpUtil.success("删除资讯信息成功!");
	}
	/**
	 * 批量删除资讯信息信息
	 * @param article
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/article/deleteBatchArticle", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchArticle(List<Article> article){
		try{
			this.articleService.deleteBatchArticle(article);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除资讯信息失败!");
		}
		
		return HttpUtil.success("批量删除资讯信息成功!");
	}

    /**
     * 上传文件
     * @param request 请求服务对象
     * @return
     */
	@ResponseBody
    @RequestMapping(value = "/mgr/article/uploadFile", method = RequestMethod.POST)
    public KEMsg uploadFile(HttpServletRequest request) {
        InputStream is = null;
        try {

			String realPath = request.getServletContext().getRealPath("upfiles/");
			LOGGER.info("readlPath =>" + realPath);

			//文件保存目录URL
			String saveUrl  = request.getContextPath() + "/upfiles/";

			if (!ServletFileUpload.isMultipartContent(request)) {
				LOGGER.error("no file to upload");
				return StringUtil.error("请选择目录");
			}

			//检查目录
			File uploadDir = new File(realPath);
			if (!uploadDir.isDirectory()) {
				LOGGER.error("upload file dir is not exists");
				return StringUtil.error("上传的目录不存在");
			}
			//检查目录写权限
			if (!uploadDir.canWrite()) {
				LOGGER.error("upload file dir cannot write");
				return StringUtil.error("上传的目录没有写权限");
			}

			String today = DateUtil.today();
			realPath += "images/" + today + "/";
			saveUrl += "images/" + today + "/";
			File dirFile = new File(realPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			//定义允许上传的文件扩展名
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			mrequest.setCharacterEncoding("UTF-8");

			String []formats = PropertiesReader.getStringArray("imgformat");
			long maxSize = PropertiesReader.getLong("maxUploadSize", 104857600l);
			Map<String, MultipartFile> fileMap = mrequest.getFileMap();
			Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
			Iterator<Map.Entry<String, MultipartFile>> iterator = entries.iterator();
			while(iterator.hasNext()){
				Map.Entry<String, MultipartFile> next = iterator.next();
				MultipartFile value = next.getValue();
				if (value != null){
					long fileSize = value.getSize();
					String fileName = value.getOriginalFilename();
					if(StringUtils.isBlank(fileName) && fileSize == 0l){
						continue;
					}

					//检查文件大小
					if (fileSize > maxSize) {
						LOGGER.error("上传文件大小超过限制。");
						return StringUtil.error("上传文件大小超过限制。");
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String>asList(formats).contains(fileExt)) {
						String format = StringUtils.join(formats, ",");
						LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
						return StringUtil.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
					}

					String newFileName = DateUtil.currentTime() + "_" + StringUtil.random(4) + "." + fileExt;
					try {
						File uploadedFile = new File(realPath, newFileName);
						value.transferTo(uploadedFile);

						KEMsg keMsg = StringUtil.success("上传成功!");
						keMsg.setUrl(saveUrl + newFileName);
						return keMsg;
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
						return StringUtil.error(e.getMessage());
					}

				}
			}
		}catch (Exception e){
			return StringUtil.error(e.getMessage());
		}

		return StringUtil.error("");
    }

}
