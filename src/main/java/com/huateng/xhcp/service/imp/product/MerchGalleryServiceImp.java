/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.MerchGalleryMapper;
import com.huateng.xhcp.model.product.MerchGallery;
import com.huateng.xhcp.service.product.MerchGalleryService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 产品图片服务实现类
 * @author sam.pan
 *
 */
@Service
public class MerchGalleryServiceImp implements MerchGalleryService {

	private @Autowired @Setter @Getter MerchGalleryMapper merchGalleryMapper;
	/**
	 * 查询产品图片信息
	 * @param merchGallery
	 * @return
	 */
	public List<MerchGallery> queryMerchGallery(MerchGallery merchGallery){
		int start = merchGallery.getStart();
		int limit = merchGallery.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.merchGalleryMapper.queryMerchGallery(merchGallery);
	}
	/**
	 * 查询产品图片信息
	 * @param merchGallery
	 * @return
	 */
	public List<MerchGallery> queryBy(MerchGallery merchGallery){
		return this.merchGalleryMapper.queryMerchGallery(merchGallery);
	}
	/**
	 * 根据Key查询产品图片信息
	 * @param gallery_id
	 * @return
	 */
	public MerchGallery queryByKey(String gallery_id){
		MerchGallery merchGallery = new MerchGallery();
		merchGallery.setGallery_id(gallery_id);
		List<MerchGallery> list = this.merchGalleryMapper.queryMerchGallery(merchGallery);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增产品图片信息
	 * @param merchGallery
	 */
	public int addMerchGallery(MerchGallery merchGallery){
		return this.merchGalleryMapper.addMerchGallery(merchGallery);
	}
	/**
	 * 批量新增产品图片信息
	 * @param merchGallery
	 */
	public int addBatchMerchGallery(List<MerchGallery> merchGallery){
		if(merchGallery == null || merchGallery.isEmpty()){
			return 0;
		}
		return this.merchGalleryMapper.addBatchMerchGallery(merchGallery);
	}
	/**
	 * 更新产品图片信息
	 * @param merchGallery
	 */
	public int updateMerchGallery(MerchGallery merchGallery){
		return this.merchGalleryMapper.updateMerchGallery(merchGallery);
	}
	/**
	 * 根据gallery_id删除产品图片信息
	 * @param merchGallery
	 */
	public int deleteMerchGallery(MerchGallery merchGallery){
		return this.merchGalleryMapper.deleteMerchGallery(merchGallery);
	}
	/**
	 * 根据gallery_id批量删除产品图片信息
	 * @param merchGallery
	 */
	public int deleteBatchMerchGallery(List<MerchGallery> merchGallery){
		if(merchGallery == null || merchGallery.isEmpty()){
			return 0;
		}
		return this.merchGalleryMapper.deleteBatchMerchGallery(merchGallery);
	}
}
