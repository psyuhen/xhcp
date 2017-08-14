/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.Store;

/**
 * 店铺信息服务类
 * @author sam.pan
 *
 */
public interface StoreService {
	/**
	 * 查询店铺信息信息
	 * @param store
	 * @return
	 */
	List<Store> queryStore(Store store);
	/**
	 * 查询店铺信息信息
	 * @param store
	 * @return
	 */
	List<Store> queryBy(Store store);
	/**
	 * 根据Key查询店铺信息信息
	 * @param store_id
	 * @return
	 */
	Store queryByKey(String store_id);
	/**
	 * 新增店铺信息信息
	 * @param store
	 */
	int addStore(Store store);
	/**
	 * 批量新增店铺信息信息
	 * @param store
	 */
	int addBatchStore(List<Store> store);
	/**
	 * 更新店铺信息信息
	 * @param store
	 */
	int updateStore(Store store);
	/**
	 * 根据store_id删除店铺信息信息
	 * @param store
	 */
	int deleteStore(Store store);
	/**
	 * 根据store_id批量删除店铺信息信息
	 * @param store
	 */
	int deleteBatchStore(List<Store> store);
}
