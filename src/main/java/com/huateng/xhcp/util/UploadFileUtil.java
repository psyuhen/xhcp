package com.huateng.xhcp.util;

import com.huateng.xhcp.model.product.MerchGallery;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.service.upload.UploadCallback;
import com.huateng.xhcp.service.upload.UploadType;
import com.huateng.xhcp.web.IndexController;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by sam.pan on 2017/3/27.
 */
public class UploadFileUtil {
    private static final Log LOGGER = LogFactory.getLog(IndexController.class);

    public static UploadType upload(HttpServletRequest request, UploadCallback uploadCallback){
        final String realPath = request.getServletContext().getRealPath("upfiles/");
        LOGGER.info("readlPath =>" + realPath);

        try{
            //文件保存目录URL
            final String saveUrl  = request.getContextPath() + "/upfiles/";

            if (!ServletFileUpload.isMultipartContent(request)) {
                LOGGER.error("no file to upload");
                return UploadType.NO_FILE;
            }

            //检查目录
            File uploadDir = new File(realPath);
            if (!uploadDir.isDirectory()) {
                LOGGER.error("upload file dir is not exists");
                return UploadType.NO_UPLOAD_DIR;
            }
            //检查目录写权限
            if (!uploadDir.canWrite()) {
                LOGGER.error("upload file dir cannot write");
                return UploadType.CANNOT_WIRTE;
            }

            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
            mrequest.setCharacterEncoding("UTF-8");

            String []formats = PropertiesReader.getStringArray("imgformat");
            long maxSize = PropertiesReader.getLong("maxUploadSize", 104857600l);
            Map<String, MultipartFile> fileMap = mrequest.getFileMap();
            Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
            Iterator<Map.Entry<String, MultipartFile>> iterator = entries.iterator();
            List<FileInfo> fileInfos = new ArrayList<FileInfo>();
            while(iterator.hasNext()){
                Map.Entry<String, MultipartFile> next = iterator.next();
                String key = next.getKey();//对应文件控件的name
                MultipartFile value = next.getValue();
                if (value != null){

                    long size = value.getSize();
                    String fileName = value.getOriginalFilename();
                    if(StringUtils.isBlank(fileName) && size == 0l){
                        continue;
                    }


                    String name = value.getName();
                    String fileType = mrequest.getParameter(name + "_file_type");
                    String fileSuffix = mrequest.getParameter(name + "_file_suffix");

                    String filePath = "upfiles/";
                    if(!"0".equals(fileSuffix) && !"1".equals(fileSuffix)){
                        LOGGER.error("no dir to write");
                        return UploadType.NO_FILE_TYPE;
                    }

                    String path = "";
                    if("0".equals(fileType)){
                        path = PropertiesReader.getString("logopath", "indeximg/");
                    }else if("1".equals(fileType)){
                        path = PropertiesReader.getString("indexphoto", "indeximg/");
                    }else if("3".equals(fileType)){
                        path = PropertiesReader.getString("indexnews", "onepage/");
                    }else if("2".equals(fileType)) {//首页视频
                        path = PropertiesReader.getString("indexvideo", "onepage/");
                        formats = PropertiesReader.getStringArray("videoformat");
                    }else if("4".equals(fileType)) {//4、视频中心
                        path = PropertiesReader.getString("videocenter", "onepage/");
                        formats = PropertiesReader.getStringArray("videoformat");
                    }else if("5".equals(fileType)) {//视频中心背景图片
                        path = PropertiesReader.getString("videocenterimg", "onepage/");
                    }else{
                        LOGGER.error("no dir to write");
                        return UploadType.NO_FILE_TYPE;
                    }
                    path = StringUtils.endsWith(path, "/") ? path : (path + "/");
                    String realPathTmp = realPath + path;
                    String saveUrlTmp = saveUrl + path;
                    String filePathTmp = filePath + path;

                    File dirFile = new File(realPathTmp);
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }

                    if(size > maxSize){
                        LOGGER.error(key + "上传文件大小超过限制。");
                        return UploadType.EXCEED_SIZE;
                    }

                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!Arrays.<String>asList(formats).contains(fileExt)) {
                        String format = StringUtils.join(formats, ",");
                        LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
                        return UploadType.ERROR_EXT;
                    }

                    String prefix = "2".equals(fileType) ? "video" :"image";

                    String newFileName = prefix + DateUtil.currentTime() + StringUtil.random(6) + "." + fileExt;
                    try {
                        File uploadedFile = new File(realPathTmp, newFileName);
                        value.transferTo(uploadedFile);

                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setName(newFileName);
                        fileInfo.setFile_name(fileName);
                        fileInfo.setFile_path(filePathTmp);
                        fileInfo.setFile_type(fileType);
                        fileInfo.setFile_suffix(fileSuffix);
                        fileInfo.setUrl(saveUrlTmp + newFileName);
                        fileInfos.add(fileInfo);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                        return UploadType.OTHER_ERR;
                    }
                }
            }

            return uploadCallback.callback(fileInfos);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return UploadType.OTHER_ERR;
        }
    }

    public static UploadType uploadPhoto(String merchId, HttpServletRequest request, UploadCallback uploadCallback){
        final String realPath = request.getServletContext().getRealPath("upfiles/");
        LOGGER.info("readlPath =>" + realPath);

        try{
            //文件保存目录URL
            final String saveUrl  = request.getContextPath() + "/upfiles/";

            if (!ServletFileUpload.isMultipartContent(request)) {
                LOGGER.error("no file to upload");
                return UploadType.NO_FILE;
            }

            //检查目录
            File uploadDir = new File(realPath);
            if (!uploadDir.isDirectory()) {
                LOGGER.error("upload file dir is not exists");
                return UploadType.NO_UPLOAD_DIR;
            }
            //检查目录写权限
            if (!uploadDir.canWrite()) {
                LOGGER.error("upload file dir cannot write");
                return UploadType.CANNOT_WIRTE;
            }

            //
            String filePath = "upfiles/";
            String path = PropertiesReader.getString("productphoto", "onepage/");;
            path = StringUtils.endsWith(path, "/") ? path : (path + "/");

            String realPathTmp = realPath + path;
            String saveUrlTmp = saveUrl + path;
            String filePathTmp = filePath + path;

            File dirFile = new File(realPathTmp);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
            mrequest.setCharacterEncoding("UTF-8");

            String []formats = PropertiesReader.getStringArray("imgformat");
            long maxSize = PropertiesReader.getLong("maxUploadSize", 104857600l);
            Map<String, MultipartFile> fileMap = mrequest.getFileMap();
            Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
            Iterator<Map.Entry<String, MultipartFile>> iterator = entries.iterator();
            List<MerchGallery> fileInfos = new ArrayList<MerchGallery>();
            while(iterator.hasNext()){
                Map.Entry<String, MultipartFile> next = iterator.next();
                String key = next.getKey();//对应文件控件的name
                MultipartFile value = next.getValue();
                String file_type = "0";
                if (value != null){
                    long size = value.getSize();
                    String fileName = value.getOriginalFilename();
                    if(StringUtils.isBlank(fileName) && size == 0l){
                        continue;
                    }

                    if(size > maxSize){
                        LOGGER.error(key + "上传文件大小超过限制。");
                        return UploadType.EXCEED_SIZE;
                    }

                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!Arrays.<String>asList(formats).contains(fileExt)) {
                        String format = StringUtils.join(formats, ",");
                        LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + format + "格式。");
                        return UploadType.ERROR_EXT;
                    }

                    String newFileName = DateUtil.currentTime() + StringUtil.random(6) + "." + fileExt;
                    try {
                        File uploadedFile = new File(realPathTmp, newFileName);
                        value.transferTo(uploadedFile);

                        if(StringUtils.startsWith(key, "detail")){
                            file_type = "1";
                        }else if(StringUtils.startsWith(key, "photo")){
                            file_type = "0";
                        }

                        MerchGallery merchGallery = new MerchGallery();
                        merchGallery.setMerch_id(merchId);
                        merchGallery.setName(newFileName);
                        merchGallery.setFile_name(fileName);
                        merchGallery.setPath(filePathTmp);
                        merchGallery.setUrl(saveUrlTmp + newFileName);
                        merchGallery.setFile_type(file_type);

                        fileInfos.add(merchGallery);

                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                        return UploadType.OTHER_ERR;
                    }
                }
            }

            return uploadCallback.callback(fileInfos);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return UploadType.OTHER_ERR;
        }
    }
}
