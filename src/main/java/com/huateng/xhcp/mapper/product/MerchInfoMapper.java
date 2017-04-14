/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.MerchInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 产品Mapper
 * @author sam.pan
 *
 */
public interface MerchInfoMapper {
	/**
	 * 查询产品信息
	 * @param keywords
	 * @return
	 */
	List<MerchInfo> searchKeyWord(@Param("keywords") String [] keywords);
	/**
	 * 查询产品信息
	 * @param merchInfo
	 * @return
	 */
	List<MerchInfo> queryMerchInfo(MerchInfo merchInfo);
	/**
	 * 查询产品信息
	 * @param classify_id 父节点
	 * @return
	 */
	List<MerchInfo> queryByPclsId(String classify_id);

	/**
	 * 新增产品信息
	 * @param merchInfo
	 */
	int addMerchInfo(MerchInfo merchInfo);
	/**
	 * 批量新增产品信息
	 * @param merchInfo
	 */
	int addBatchMerchInfo(List<MerchInfo> merchInfo);
	/**
	 * 更新产品信息
	 * @param merchInfo
	 */
	int updateMerchInfo(MerchInfo merchInfo);
	/**
	 * 更新点击数
	 * @param merch_id
	 */
	int updateHits(String merch_id);
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
