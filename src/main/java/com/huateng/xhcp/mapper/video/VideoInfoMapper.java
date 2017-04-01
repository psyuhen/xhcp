/**
 * 
 */
package com.huateng.xhcp.mapper.video;

import java.util.List;

import com.huateng.xhcp.model.video.VideoInfo;

/**
 * 视频信息Mapper
 * @author sam.pan
 *
 */
public interface VideoInfoMapper {
	/**
	 * 查询视频信息信息
	 * @param videoInfo
	 * @return
	 */
	List<VideoInfo> queryVideoInfo(VideoInfo videoInfo);
	/**
	 * 新增视频信息信息
	 * @param videoInfo
	 */
	int addVideoInfo(VideoInfo videoInfo);
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
