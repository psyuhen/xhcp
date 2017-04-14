package com.huateng.xhcp.web.product;

import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.model.product.MerchInfo;
import com.huateng.xhcp.service.article.ArticleService;
import com.huateng.xhcp.service.product.MerchInfoService;
import com.huateng.xhcp.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by pansen on 2017/4/12.
 */
@Controller
public class SearchController{
    private static final Log LOGGER = LogFactory.getLog(SearchController.class);
    private @Autowired @Setter @Getter ArticleService articleService;
    private @Autowired @Setter @Getter MerchInfoService merchInfoService;
    /**
     * 初始化页面
     * @return
     */
    @RequestMapping(value = "/search.html", method = {RequestMethod.GET})
    public String toListPage(String keywords, HttpServletRequest request){

        if(isBlank(keywords)){
            request.setAttribute("NoKeyWord", true);
            return "product/search";
        }

        keywords = trimToEmpty(keywords);

        if(keywords.length() < 2){
            request.setAttribute("KeyWordShort", true);
            return "product/search";
        }

        final String[] strings = split(keywords);
        //搜索新闻
        final List<Article> articles = this.articleService.searchKeyWord(strings);
        StringUtil.rmContentsHtml(articles);
        request.setAttribute("news", articles);

        //产品
        final List<MerchInfo> merchInfos = this.merchInfoService.searchKeyWord(strings);
        request.setAttribute("products", merchInfos);

        return "product/search";
    }
}
