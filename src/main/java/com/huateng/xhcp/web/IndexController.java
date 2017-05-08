package com.huateng.xhcp.web;

import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.model.kindeditor.KEMsg;
import com.huateng.xhcp.model.product.MerchInfo;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.service.article.ArticleService;
import com.huateng.xhcp.service.product.MerchInfoService;
import com.huateng.xhcp.service.system.FileInfoService;
import com.huateng.xhcp.service.upload.UploadCallback;
import com.huateng.xhcp.service.upload.UploadType;
import com.huateng.xhcp.util.*;
import com.huateng.xhcp.web.article.ArticleController;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by sam.pan on 2017/2/28.
 */
@Controller
public class IndexController {
    private static final Log LOGGER = LogFactory.getLog(IndexController.class);
    private @Autowired ArticleService articleService;
    private @Autowired FileInfoService fileInfoService;
    private @Autowired MerchInfoService merchInfoService;


    @RequestMapping("/index")
    public String mainPage(HttpServletRequest request){
        //首页新闻
        Article article = new Article();
        article.setLimit(3);
        article.setStart(1);
        List<Article> articles = articleService.queryArticle(article);
        StringUtil.rmContentsHtml(articles);
        request.setAttribute("newArticleList", articles);

        //首页图片展示
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFile_type("1");
        fileInfo.setFile_suffix("0");
        List<FileInfo> photos = this.fileInfoService.queryBy(fileInfo);
        request.setAttribute("photosList", photos);

        //首页视频
        fileInfo.setFile_type("2");
        fileInfo.setFile_suffix("1");
        photos = this.fileInfoService.queryBy(fileInfo);
        if(!photos.isEmpty()){
            request.setAttribute("indexvideo", photos.get(0));
        }

        //首页资讯图片
        fileInfo.setFile_type("3");
        fileInfo.setFile_suffix("0");
        photos = this.fileInfoService.queryBy(fileInfo);
        if(!photos.isEmpty()){
            request.setAttribute("indexnewsphoto", photos.get(0));
        }

        //查询最火的商品
        final List<MerchInfo> hotMerchs = this.merchInfoService.queryHotMerch();
        if(hotMerchs == null || hotMerchs.isEmpty()){
            final List<MerchInfo> hotHitsMerchs = this.merchInfoService.queryHotHitsMerch();
            hotMerchs.addAll(hotHitsMerchs);
        }
        request.setAttribute("hotMerchs", hotMerchs);

        return "index";
    }

    /**
     * 跳转首页管理
     * @return
     */
    @RequestMapping(value = "/mgr/index/indexmgr")
    public String toAddPage(HttpServletRequest request){
        //LOGO
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFile_type("0");
        fileInfo.setFile_suffix("0");
        List<FileInfo> photos = this.fileInfoService.queryBy(fileInfo);
        if(!photos.isEmpty()){
            request.setAttribute("logofile", photos.get(0));
        }

        //首页图片
        fileInfo.setFile_type("1");
        fileInfo.setFile_suffix("0");
        photos = this.fileInfoService.queryBy(fileInfo);
        request.setAttribute("indexphotos", photos);
        request.setAttribute("indexphotoslength", 5 - photos.size());

        //视频
        fileInfo.setFile_type("2");
        fileInfo.setFile_suffix("1");
        photos = this.fileInfoService.queryBy(fileInfo);
        if(!photos.isEmpty()){
            request.setAttribute("videoinfo", photos.get(0));
        }

        //首页资讯图片
        fileInfo.setFile_type("3");
        fileInfo.setFile_suffix("0");
        photos = this.fileInfoService.queryBy(fileInfo);
        if(!photos.isEmpty()){
            request.setAttribute("newsphoto", photos.get(0));
        }

        request.setAttribute("module_id", "indexmgr");
        return "system/IndexMgr";
    }


    @ResponseBody
    @RequestMapping(value = "/index/photos", method = RequestMethod.GET)
    public List<FileInfo> queryPhoto(){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFile_type("1");
        fileInfo.setFile_suffix("0");
        return this.fileInfoService.queryBy(fileInfo);
    }

    /**
     * 删除图片信息
     * @param fileInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mgr/index/deletefile", method = RequestMethod.POST)
    public ResponseEntity<ResponseInfo> deleteLogo(HttpServletRequest request,FileInfo fileInfo){
        try{
            List<FileInfo> deleteFiles = this.fileInfoService.queryBy(fileInfo);
            int c = this.fileInfoService.deleteFileInfo(fileInfo);
            if(c == 0){
                return HttpUtil.failure("删除logo失败!");
            }
            //同时删除文件
            String realPath = request.getServletContext().getRealPath("upfiles/");
            for (FileInfo f: deleteFiles) {
                File file = new File(realPath + f.getFile_path() + f.getName());
                file.delete();
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            return HttpUtil.failure("删除logo失败!");
        }

        return HttpUtil.success("删除logo成功!");
    }

    /**
     * 上传文件
     * @param request 请求服务对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mgr/index/indexupload", method = RequestMethod.POST)
    public KEMsg uploadFile(HttpServletRequest request) {
        final List<FileInfo> list = new ArrayList<FileInfo>();
        UploadType uploadType =  UploadFileUtil.upload(request, new UploadCallback<FileInfo>() {
            @Override
            public UploadType callback(List<FileInfo> fileInfos) {
                int c = IndexController.this.fileInfoService.addBatchFileInfo(fileInfos);
                if(c == 0){
                    return UploadType.SAVE_DATA_ERR;
                }
                //保存文件到数据库
                list.addAll(fileInfos);
                return UploadType.SUCCESS;
            }
        });

        if(uploadType == UploadType.SUCCESS){
            final KEMsg ke = StringUtil.success("操作成功");
            ke.setData(JsonUtil.formatObject(list));
            return ke;
        }

        return StringUtil.error(uploadType.getReasonPhrase());
    }
}
