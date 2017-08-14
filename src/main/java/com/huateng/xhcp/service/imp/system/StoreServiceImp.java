/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.StoreMapper;
import com.huateng.xhcp.model.system.Store;
import com.huateng.xhcp.service.system.StoreService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 店铺信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class StoreServiceImp implements StoreService {

	private @Autowired @Setter @Getter StoreMapper storeMapper;
	/**
	 * 查询店铺信息信息
	 * @param store
	 * @return
	 */
	public List<Store> queryStore(Store store){
		int start = store.getStart();
		int limit = store.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.storeMapper.queryStore(store);
	}
	/**
	 * 查询店铺信息信息
	 * @param store
	 * @return
	 */
	public List<Store> queryBy(Store store){
		return this.storeMapper.queryStore(store);
	}
	/**
	 * 根据Key查询店铺信息信息
	 * @param store_id
	 * @return
	 */
	public Store queryByKey(String store_id){
		Store store = new Store();
		store.setStore_id(store_id);
		List<Store> list = this.storeMapper.queryStore(store);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增店铺信息信息
	 * @param store
	 */
	public int addStore(Store store){
		return this.storeMapper.addStore(store);
	}
	/**
	 * 批量新增店铺信息信息
	 * @param store
	 */
	public int addBatchStore(List<Store> store){
		if(store == null || store.isEmpty()){
			return 0;
		}
		return this.storeMapper.addBatchStore(store);
	}
	/**
	 * 更新店铺信息信息
	 * @param store
	 */
	public int updateStore(Store store){
		return this.storeMapper.updateStore(store);
	}
	/**
	 * 根据store_id删除店铺信息信息
	 * @param store
	 */
	public int deleteStore(Store store){
		return this.storeMapper.deleteStore(store);
	}
	/**
	 * 根据store_id批量删除店铺信息信息
	 * @param store
	 */
	public int deleteBatchStore(List<Store> store){
		if(store == null || store.isEmpty()){
			return 0;
		}
		return this.storeMapper.deleteBatchStore(store);
	}
}
