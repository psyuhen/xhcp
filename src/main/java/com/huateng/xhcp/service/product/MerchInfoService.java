/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.MerchGallery;
import com.huateng.xhcp.model.product.MerchInfo;

/**
 * 产品服务类
 * @author sam.pan
 *
 */
public interface MerchInfoService {
	/**
	 * 查询产品信息
	 * @param merchInfo
	 * @return
	 */
	List<MerchInfo> queryMerchInfo(MerchInfo merchInfo);
	/**
	 * 查询产品信息
	 * @param merchInfo
	 * @return
	 */
	List<MerchInfo> queryBy(MerchInfo merchInfo);
	/**
	 * 查询产品信息
	 * @param classify_id 父节点
	 * @return
	 */
	List<MerchInfo> queryByPclsId(String classify_id);
	/**
	 * 根据Key查询产品信息
	 * @param merch_id
	 * @return
	 */
	MerchInfo queryByKey(String merch_id);
	/**
	 * 新增产品信息
	 * @param merchInfo
	 */
	int addMerchInfo(MerchInfo merchInfo, List<MerchGallery> fileInfos);
	/**
	 * 批量新增产品信息
	 * @param merchInfo
	 */
	int addBatchMerchInfo(List<MerchInfo> merchInfo);
	/**
	 * 更新产品信息
	 * @param merchInfo
	 */
	int updateMerchInfo(MerchInfo merchInfo, List<MerchGallery> fileInfos);
	/**
	 * 根据merch_id删除产品信息
	 * @param merchInfo
	 */
	int deleteMerchInfo(MerchInfo merchInfo);
	/**
	 * 根据merch_id批量删除产品信息
	 * @param merchInfo
	 */
	int deleteBatchMerchInfo(List<MerchInfo> merchInfo);
}
