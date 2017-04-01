/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.FileInfoMapper;
import com.huateng.xhcp.model.system.FileInfo;
import com.huateng.xhcp.service.system.FileInfoService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 文件信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class FileInfoServiceImp implements FileInfoService {

	private @Autowired @Setter @Getter FileInfoMapper fileInfoMapper;
	/**
	 * 查询文件信息信息
	 * @param fileInfo
	 * @return
	 */
	public List<FileInfo> queryFileInfo(FileInfo fileInfo){
		int start = fileInfo.getStart();
		int limit = fileInfo.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.fileInfoMapper.queryFileInfo(fileInfo);
	}

	/**
	 * 查询文件信息
	 * @param fileInfo
	 * @return
	 */
	@Override
	public List<FileInfo> queryBy(FileInfo fileInfo) {
		return this.fileInfoMapper.queryFileInfo(fileInfo);
	}

	/**
	 * 根据Key查询文件信息信息
	 * @param file_id
	 * @return
	 */
	public FileInfo queryByKey(String file_id){
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFile_id(file_id);
		List<FileInfo> list = this.fileInfoMapper.queryFileInfo(fileInfo);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增文件信息信息
	 * @param fileInfo
	 */
	public int addFileInfo(FileInfo fileInfo){
		return this.fileInfoMapper.addFileInfo(fileInfo);
	}
	/**
	 * 批量新增文件信息信息
	 * @param fileInfo
	 */
	public int addBatchFileInfo(List<FileInfo> fileInfo){
		if(fileInfo == null || fileInfo.isEmpty()){
			return 0;
		}
		return this.fileInfoMapper.addBatchFileInfo(fileInfo);
	}
	/**
	 * 更新文件信息信息
	 * @param fileInfo
	 */
	public int updateFileInfo(FileInfo fileInfo){
		return this.fileInfoMapper.updateFileInfo(fileInfo);
	}
	/**
	 * 根据file_id删除文件信息信息
	 * @param fileInfo
	 */
	public int deleteFileInfo(FileInfo fileInfo){
		return this.fileInfoMapper.deleteFileInfo(fileInfo);
	}
	/**
	 * 根据file_id批量删除文件信息信息
	 * @param fileInfo
	 */
	public int deleteBatchFileInfo(List<FileInfo> fileInfo){
		if(fileInfo == null || fileInfo.isEmpty()){
			return 0;
		}
		return this.fileInfoMapper.deleteBatchFileInfo(fileInfo);
	}
}
