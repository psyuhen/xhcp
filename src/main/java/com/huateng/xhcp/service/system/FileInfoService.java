/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.FileInfo;

/**
 * 文件信息服务类
 * @author sam.pan
 *
 */
public interface FileInfoService {
	/**
	 * 查询文件信息信息(分页）
	 * @param fileInfo
	 * @return
	 */
	List<FileInfo> queryFileInfo(FileInfo fileInfo);
	/**
	 * 查询文件信息信息（不分页）
	 * @param fileInfo
	 * @return
	 */
	List<FileInfo> queryBy(FileInfo fileInfo);
	/**
	 * 根据Key查询文件信息信息
	 * @param file_id
	 * @return
	 */
	FileInfo queryByKey(String file_id);
	/**
	 * 新增文件信息信息
	 * @param fileInfo
	 */
	int addFileInfo(FileInfo fileInfo);
	/**
	 * 批量新增文件信息信息
	 * @param fileInfo
	 */
	int addBatchFileInfo(List<FileInfo> fileInfo);
	/**
	 * 更新文件信息信息
	 * @param fileInfo
	 */
	int updateFileInfo(FileInfo fileInfo);
	/**
	 * 根据file_id删除文件信息信息
	 * @param fileInfo
	 */
	int deleteFileInfo(FileInfo fileInfo);
	/**
	 * 根据file_id批量删除文件信息信息
	 * @param fileInfo
	 */
	int deleteBatchFileInfo(List<FileInfo> fileInfo);
}
