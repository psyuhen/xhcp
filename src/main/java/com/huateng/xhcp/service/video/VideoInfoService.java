/**
 * 
 */
package com.huateng.xhcp.service.video;

import java.util.List;

import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.model.video.VideoInfo;

/**
 * 视频信息服务类
 * @author sam.pan
 *
 */
public interface VideoInfoService {
	/**
	 * 查询视频信息信息
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryVideoInfo(VideoInfo videoInfo);
	/**
	 * 查询视频信息信息
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryBy(VideoInfo videoInfo);
	/**
	 * 根据Key查询视频信息信息
	 * @param video_id
	 * @return
	 */
	VideoInfo queryByKey(String video_id);
	/**
	 * 新增视频信息信息
	 * @param videoInfo
	 */
	int addVideoInfo(VideoInfo videoInfo, List<FileInfo> fileInfos);
	/**
	 * 批量新增视频信息信息
	 * @param videoInfo
	 */
	void addBatchVideoInfo(List<VideoInfo> videoInfo);
	/**
	 * 更新视频信息信息
	 * @param videoInfo
	 */
	int updateVideoInfo(VideoInfo videoInfo);
	/**
	 * 更新视频信息信息
	 * @param videoInfo
	 * @param fileInfos
	 */
	int updateVideoInfo(VideoInfo videoInfo, List<FileInfo> fileInfos);
	/**
	 * 根据video_id删除视频信息信息
	 * @param videoInfo
	 */
	int deleteVideoInfo(VideoInfo videoInfo);
	/**
	 * 根据video_id批量删除视频信息信息
	 * @param videoInfo
	 */
	void deleteBatchVideoInfo(List<VideoInfo> videoInfo);
}
