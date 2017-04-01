/**
 * 
 */
package com.huateng.xhcp.web.video;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.model.video.VideoInfo;
import com.huateng.xhcp.service.system.FileInfoService;
import com.huateng.xhcp.service.upload.UploadCallback;
import com.huateng.xhcp.service.upload.UploadType;
import com.huateng.xhcp.service.video.VideoInfoService;
import com.huateng.xhcp.util.HttpUtil;
import com.huateng.xhcp.util.UploadFileUtil;
import lombok.Getter;
import lombok.Setter;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 视频信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class VideoInfoController implements com.huateng.xhcp.service.upload.Validator<VideoInfo>{
	private static final Log LOGGER = LogFactory.getLog(VideoInfoController.class);
	private @Autowired @Setter @Getter VideoInfoService videoInfoService;
	private @Autowired FileInfoService fileInfoService;

	@RequestMapping(value = "/video.html")
	public String toVideoCenter(HttpServletRequest request){
		List<VideoInfo> videoInfos = videoInfoService.queryBy(null);

		request.setAttribute("videocenter", videoInfos);
		return "video";
	}

	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/video/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "videomgr");
		return "video/VideoInfoList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/video/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "videomgr");
		return "video/VideoInfoList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/video/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "videomgr");
		return "video/VideoInfoAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/video/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "videomgr");
		return "video/VideoInfoAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/video/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "video/VideoInfoAdd";
	}
	
	/**
	 * 查询视频信息信息(分页)
	 * @param videoInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/queryVideoInfoPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryVideoInfo(VideoInfo videoInfo){
		Page<VideoInfo> list = (Page<VideoInfo>)this.videoInfoService.queryVideoInfo(videoInfo);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询视频信息信息
	 * @param video_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/queryByKey", method = RequestMethod.GET)
	public VideoInfo queryByKey(String video_id){
		return this.videoInfoService.queryByKey(video_id);
	}
	/**
	 * 新增视频信息信息
	 * @param videoInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/addVideoInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addVideoInfo(VideoInfo videoInfo,HttpServletRequest request){
		try{
			ResponseInfo responseInfo = validate(videoInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}

			final List<FileInfo> list = new ArrayList<FileInfo>();
            UploadType uploadType = upload(list, request);
			if(uploadType == UploadType.SUCCESS){
				int c = this.videoInfoService.addVideoInfo(videoInfo, list);
				if(c==0){
                    this.fileInfoService.deleteBatchFileInfo(list);
                    deleteFile(videoInfo.getImg_file_id(), videoInfo.getVideo_file_id(), request);
					return HttpUtil.failure("新增视频信息失败!");
				}
			}else{
				return HttpUtil.failure(uploadType.getReasonPhrase());
			}

		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增视频信息失败!");
		}
		
		return HttpUtil.success("新增视频信息成功!");
	}

	private UploadType upload(final List<FileInfo> list, HttpServletRequest request){
        UploadType uploadType = UploadFileUtil.upload(request, new UploadCallback<FileInfo>() {
            @Override
            public UploadType callback(List<FileInfo> fileInfos) {
                if(fileInfos == null || fileInfos.isEmpty()){//没有上传文件的时候也返回成功
                    return UploadType.SUCCESS;
                }
                //保存文件到数据库
                list.addAll(fileInfos);
                return UploadType.SUCCESS;
            }
        });
        return uploadType;
    }
	
	/*校验*/
	public ResponseInfo validate(VideoInfo videoInfo){
		if(videoInfo == null){
			return ResponseInfo.fail("视频信息信息为空!");
		}
		
		String video_id = videoInfo.getVideo_id();
		int video_idLength = com.huateng.xhcp.util.Validator.mysql(video_id);
		if(video_idLength > 10){
			return ResponseInfo.fail("视频信息id必须少于10 个字符!");
		}

		String title = videoInfo.getTitle();
		int titleLength = com.huateng.xhcp.util.Validator.mysql(title);
		if(titleLength > 50){
			return ResponseInfo.fail("标题必须少于50 个字符!");
		}

		String img_file_id = videoInfo.getImg_file_id();
		int img_file_idLength = com.huateng.xhcp.util.Validator.mysql(img_file_id);
		if(img_file_idLength > 10){
			return ResponseInfo.fail("背景图片ID必须少于10 个字符!");
		}

		String video_file_id = videoInfo.getVideo_file_id();
		int video_file_idLength = com.huateng.xhcp.util.Validator.mysql(video_file_id);
		if(video_file_idLength > 10){
			return ResponseInfo.fail("视频文件ID必须少于10 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}



    /**
     * 更新视频信息信息
     * @param video_id
     * @param type
     * @param file_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mgr/video/deleteAndUpdateVideoInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseInfo> deleteAndUpdateVideoInfo(String video_id,String type, String file_id){
        String typeDesc = "图片";
        if(StringUtils.equals(type,"img")){
            typeDesc = "图片";
        }else if(StringUtils.equals(type,"video")){
            typeDesc = "视频";
        }
        try{
            if(StringUtils.isBlank(video_id) || StringUtils.isBlank(file_id)){
                return HttpUtil.failure("删除" + typeDesc + "失败");
            }


            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setVideo_id(video_id);
            if(StringUtils.equals(type,"img")){
                videoInfo.setImg_file_id(file_id);
            }else if(StringUtils.equals(type,"video")){
                videoInfo.setVideo_file_id(file_id);
            }

            int c = this.videoInfoService.updateVideoInfo(videoInfo);
            if(c == 0){
                return HttpUtil.failure("删除" + typeDesc + "失败!");
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            return HttpUtil.failure("删除" + typeDesc + "失败!");
        }

        return HttpUtil.success("删除" + typeDesc + "成功!");
    }

	/**
	 * 更新视频信息信息
	 * @param videoInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/updateVideoInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateVideoInfo(final VideoInfo videoInfo,HttpServletRequest request){
		try{
			ResponseInfo responseInfo = validate(videoInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}

            final List<FileInfo> list = new ArrayList<FileInfo>();
            UploadType uploadType = upload(list, request);

            if(uploadType == UploadType.SUCCESS){
                int c = this.videoInfoService.updateVideoInfo(videoInfo, list);
                if(c == 0){
                    return HttpUtil.failure("更新视频信息失败!");
                }
            }
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新视频信息失败!");
		}
		
		return HttpUtil.success("更新视频信息成功!",videoInfo);
	}

	private void deleteFile(String img_file, String video_file, HttpServletRequest request){
        //删除文件
        String realPath = request.getServletContext().getRealPath("/");
        if(StringUtils.isNotBlank(img_file)){
            File file = new File(realPath + img_file);
            file.delete();
        }
        if(StringUtils.isNotBlank(video_file)){
            File file = new File(realPath + video_file);
            file.delete();
        }
    }
	/**
	 * 删除视频信息信息
	 * @param videoInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/deleteVideoInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteVideoInfo(VideoInfo videoInfo, HttpServletRequest request){
		try{
            VideoInfo v =  this.videoInfoService.queryByKey(videoInfo.getVideo_id());
            if(v == null){
                return HttpUtil.failure("删除视频信息失败!");
            }

			int c = this.videoInfoService.deleteVideoInfo(v);
			if(c == 0){
				return HttpUtil.failure("删除视频信息失败!");
			}

			//删除文件
            String img_file = v.getImg_file_url();
            String video_file = v.getVideo_file_url();
            deleteFile(img_file, video_file, request);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除视频信息失败!");
		}
		
		return HttpUtil.success("删除视频信息成功!");
	}
	/**
	 * 批量删除视频信息信息
	 * @param videoInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/video/deleteBatchVideoInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchVideoInfo(List<VideoInfo> videoInfo){
		try{
			this.videoInfoService.deleteBatchVideoInfo(videoInfo);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除视频信息失败!");
		}
		
		return HttpUtil.success("批量删除视频信息成功!");
	}
}
