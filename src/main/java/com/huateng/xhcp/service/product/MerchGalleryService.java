/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.MerchGallery;

/**
 * 产品图片服务类
 * @author sam.pan
 *
 */
public interface MerchGalleryService {
	/**
	 * 查询产品图片信息
	 * @param merchGallery
	 * @return
	 */
	List<MerchGallery> queryMerchGallery(MerchGallery merchGallery);
	/**
	 * 查询产品图片信息
	 * @param merchGallery
	 * @return
	 */
	List<MerchGallery> queryBy(MerchGallery merchGallery);
	/**
	 * 根据Key查询产品图片信息
	 * @param gallery_id
	 * @return
	 */
	MerchGallery queryByKey(String gallery_id);
	/**
	 * 新增产品图片信息
	 * @param merchGallery
	 */
	int addMerchGallery(MerchGallery merchGallery);
	/**
	 * 批量新增产品图片信息
	 * @param merchGallery
	 */
	int addBatchMerchGallery(List<MerchGallery> merchGallery);
	/**
	 * 更新产品图片信息
	 * @param merchGallery
	 */
	int updateMerchGallery(MerchGallery merchGallery);
	/**
	 * 根据gallery_id删除产品图片信息
	 * @param merchGallery
	 */
	int deleteMerchGallery(MerchGallery merchGallery);
	/**
	 * 根据gallery_id批量删除产品图片信息
	 * @param merchGallery
	 */
	int deleteBatchMerchGallery(List<MerchGallery> merchGallery);
}
