/**
 * 
 */
package com.huateng.xhcp.service.imp.video;

import java.util.LinkedList;
import java.util.List;

import com.huateng.xhcp.mapper.system.FileInfoMapper;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.service.upload.UploadType;
import com.huateng.xhcp.web.video.VideoInfoController;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.video.VideoInfoMapper;
import com.huateng.xhcp.model.video.VideoInfo;
import com.huateng.xhcp.service.video.VideoInfoService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 视频信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class VideoInfoServiceImp implements VideoInfoService {

	private @Autowired @Setter @Getter VideoInfoMapper videoInfoMapper;
	private @Autowired @Setter @Getter FileInfoMapper fileInfoMapper;
	/**
	 * 查询视频信息信息
	 * @param videoInfo
	 * @return
	 */
	public List<VideoInfo> queryVideoInfo(VideoInfo videoInfo){
		int start = videoInfo.getStart();
		int limit = videoInfo.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.videoInfoMapper.queryVideoInfo(videoInfo);
	}
    /**
     * 查询视频信息信息
     * @param videoInfo
     * @return
     */
    public List<VideoInfo> queryBy(VideoInfo videoInfo){
        return this.videoInfoMapper.queryVideoInfo(videoInfo);
    }
	/**
	 * 根据Key查询视频信息信息
	 * @param video_id
	 * @return
	 */
	public VideoInfo queryByKey(String video_id){
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setVideo_id(video_id);
		List<VideoInfo> list = this.videoInfoMapper.queryVideoInfo(videoInfo);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增视频信息信息
	 * @param videoInfo
	 */
	public int addVideoInfo(VideoInfo videoInfo, List<FileInfo> fileInfos){

		if(fileInfos != null && !fileInfos.isEmpty()){
			this.fileInfoMapper.addBatchFileInfo(fileInfos);
			for(FileInfo f : fileInfos){
				if("5".equals(f.getFile_type())){
					videoInfo.setImg_file_id(f.getFile_id());
				}else if("4".equals(f.getFile_type())){
					videoInfo.setVideo_file_id(f.getFile_id());
				}
			}
		}

		return this.videoInfoMapper.addVideoInfo(videoInfo);
	}
	/**
	 * 批量新增视频信息信息
	 * @param videoInfo
	 */
	public void addBatchVideoInfo(List<VideoInfo> videoInfo){
		if(videoInfo == null || videoInfo.isEmpty()){
			return;
		}
		this.videoInfoMapper.addBatchVideoInfo(videoInfo);
	}

	private int updateOrAdd(String file_id, FileInfo f){
		if(StringUtils.isNotBlank(file_id)){
			f.setFile_id(file_id);
			return this.fileInfoMapper.updateFileInfo(f);
		}else{
			return this.fileInfoMapper.addFileInfo(f);
		}
	}
	/**
	 * 更新视频信息信息并删除
	 * @param videoInfo
	 */
	public int updateVideoInfo(VideoInfo videoInfo){
		if(videoInfo != null){
			FileInfo fileInfo = new FileInfo();
			if(StringUtils.isNotBlank(videoInfo.getImg_file_id())){
				fileInfo.setFile_id(videoInfo.getImg_file_id());
				this.fileInfoMapper.deleteFileInfo(fileInfo);
				videoInfo.setImg_file_id("");
			}

			if(StringUtils.isNotBlank(videoInfo.getVideo_file_id())){
				fileInfo.setFile_id(videoInfo.getVideo_file_id());
				this.fileInfoMapper.deleteFileInfo(fileInfo);
				videoInfo.setVideo_file_id("");
			}
		}

		return this.videoInfoMapper.updateVideoInfo(videoInfo);
	}
	/**
	 * 更新视频信息信息
	 * @param videoInfo
	 */
	public int updateVideoInfo(VideoInfo videoInfo, List<FileInfo> fileInfos){

		if(fileInfos != null && !fileInfos.isEmpty()){
			for(FileInfo f : fileInfos){
				if("5".equals(f.getFile_type())){
					String img_file = videoInfo.getImg_file_id();
					updateOrAdd(img_file, f);
					videoInfo.setImg_file_id(f.getFile_id());
                    videoInfo.setImg_file_url(f.getFile_path()+f.getName());
				}else if("4".equals(f.getFile_type())){
					String video_file = videoInfo.getVideo_file_id();
					updateOrAdd(video_file, f);
					videoInfo.setVideo_file_id(f.getFile_id());
                    videoInfo.setVideo_file_url(f.getFile_path()+f.getName());
				}
			}
		}

		return this.videoInfoMapper.updateVideoInfo(videoInfo);
	}
	/**
	 * 根据video_id删除视频信息信息
	 * @param videoInfo
	 */
	public int deleteVideoInfo(VideoInfo videoInfo){

		if(videoInfo != null){
			List<FileInfo> list = new LinkedList<FileInfo>();
			String img_file = videoInfo.getImg_file_id();
			if(StringUtils.isNotBlank(img_file)){
				FileInfo f = new FileInfo();
				f.setFile_id(img_file);
				list.add(f);
			}

			String video_file = videoInfo.getVideo_file_id();
			if(StringUtils.isNotBlank(video_file)){
				FileInfo f = new FileInfo();
				f.setFile_id(video_file);
				list.add(f);
			}

			if(!list.isEmpty()){
				this.fileInfoMapper.deleteBatchFileInfo(list);
			}
		}

		return this.videoInfoMapper.deleteVideoInfo(videoInfo);
	}
	/**
	 * 根据video_id批量删除视频信息信息
	 * @param videoInfo
	 */
	public void deleteBatchVideoInfo(List<VideoInfo> videoInfo){
		if(videoInfo == null || videoInfo.isEmpty()){
			return;
		}
		this.videoInfoMapper.deleteBatchVideoInfo(videoInfo);
	}
}
