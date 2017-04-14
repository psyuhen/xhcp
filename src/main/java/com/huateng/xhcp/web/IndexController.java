package com.huateng.xhcp.web;

import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.model.kindeditor.KEMsg;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.service.article.ArticleService;
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

    @RequestMapping("/index")
    public String mainPage(HttpServletRequest request){
        Article article = new Article();
        article.setLimit(3);
        article.setStart(1);
        List<Article> articles = articleService.queryArticle(article);
        StringUtil.rmContentsHtml(articles);
        request.setAttribute("newArticleList", articles);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFile_type("1");
        fileInfo.setFile_suffix("0");
        List<FileInfo> photos = this.fileInfoService.queryBy(fileInfo);
        request.setAttribute("photosList", photos);

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
        /*String realPath = request.getServletContext().getRealPath("upfiles/");
        LOGGER.info("readlPath =>" + realPath);

        try{
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

            long maxSize = 104857600;//最大文件大小
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> itr = items.iterator();

            //找出其他字段
            String fileType = "";
            String fileSuffix = "";
            FileItem fileItem = null;
            while(itr.hasNext()){
                FileItem item = itr.next();
                if(item.isFormField()){
                    String fieldName = item.getFieldName();
                    String fieldVal = item.getString();
                    if("file_type".equals(fieldName)){
                        fileType = fieldVal;
                    }else if("file_suffix".equals(fieldName)){
                        fileSuffix = fieldVal;
                    }
                }else{
                    fileItem = item;
                }
            }

            //定义允许上传的文件扩展名
            HashMap<String, String> extMap = new HashMap<String, String>();
            String filePath = "upfiles/";
            if("0".equals(fileSuffix)){//图片
                String path = "";
                if("0".equals(fileType)){
                    path = PropertiesReader.getString("logopath", "indeximg/");
                }else if("1".equals(fileType)){
                    path = PropertiesReader.getString("indexphoto", "indeximg/");
                }else if("3".equals(fileType)){
                    path = PropertiesReader.getString("indexnews", "onepage/");
                }else{
                    LOGGER.error("no dir to write");
                    return StringUtil.error("上传的目录找不到");
                }
                path = StringUtils.endsWith(path, "/") ? path : (path + "/");
                realPath += path;
                saveUrl += path;
                filePath += path;
                extMap.put("format", "gif,jpg,jpeg,png,bmp");
            }else if("1".equals(fileSuffix)){
                if("2".equals(fileType)){//首页视频
                    String indexvideo = PropertiesReader.getString("indexvideo", "onepage/");
                    indexvideo = StringUtils.endsWith(indexvideo, "/") ? indexvideo : (indexvideo + "/");
                    realPath += indexvideo;
                    saveUrl += indexvideo;
                    filePath += indexvideo;
                    extMap.put("format", "mp4");
                }else{
                    LOGGER.error("no dir to write");
                    return StringUtil.error("上传的目录找不到");
                }
            }else {
                LOGGER.error("no dir to write");
                return StringUtil.error("上传的目录找不到");
            }


            File dirFile = new File(realPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            if(fileItem == null){
                LOGGER.error("没有文件需要上传！");
                return StringUtil.error("没有文件需要上传！");
            }

            String fileName = fileItem.getName();
            long fileSize = fileItem.getSize();
            //检查文件大小
            if (fileSize > maxSize) {
                LOGGER.error("上传文件大小超过限制。");
                return StringUtil.error("上传文件大小超过限制。");
            }

            //检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            String format = extMap.get("format");
            if (!Arrays.<String>asList(format.split(",")).contains(fileExt)) {
                LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
                return StringUtil.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
            }

            String prefix = "2".equals(fileType) ? "video" :"image";

            String newFileName = prefix + DateUtil.currentTime() + StringUtil.random(6) + "." + fileExt;
            try {
                File uploadedFile = new File(realPath, newFileName);
                fileItem.write(uploadedFile);

                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(newFileName);
                fileInfo.setFile_name(fileName);
                fileInfo.setFile_path(filePath);
                fileInfo.setFile_type(fileType);
                fileInfo.setFile_suffix(fileSuffix);
                int c = this.fileInfoService.addFileInfo(fileInfo);
                if(c==0){
                    uploadedFile.deleteOnExit();
                    return StringUtil.error("插入文件信息失败");
                }else{
                    KEMsg keMsg = StringUtil.success("上传成功!");
                    keMsg.setData(fileInfo.getFile_id());
                    keMsg.setUrl(saveUrl + newFileName);
                    return keMsg;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return StringUtil.error(e.getMessage());
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return StringUtil.error("");*/

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
