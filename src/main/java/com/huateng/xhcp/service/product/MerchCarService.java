/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.MerchCar;

/**
 * 购物车服务类
 * @author sam.pan
 *
 */
public interface MerchCarService {
	/**
	 * 查询购物车信息
	 * @param merchCar
	 * @return
	 */
	List<MerchCar> queryMerchCar(MerchCar merchCar);
	/**
	 * 查询购物车信息
	 * @param merchCar
	 * @return
	 */
	List<MerchCar> queryBy(MerchCar merchCar);
	/**
	 * 根据Key查询购物车信息
	 * @param car_id
	 * @return
	 */
	MerchCar queryByKey(String car_id);
	/**
	 * 新增购物车信息
	 * @param merchCar
	 */
	int addMerchCar(MerchCar merchCar);
	/**
	 * 批量新增购物车信息
	 * @param merchCar
	 */
	int addBatchMerchCar(List<MerchCar> merchCar);
	/**
	 * 更新购物车信息
	 * @param merchCar
	 */
	int updateMerchCar(MerchCar merchCar);
	/**
	 * 根据car_id删除购物车信息
	 * @param merchCar
	 */
	int deleteMerchCar(MerchCar merchCar);
	/**
	 * 根据car_id批量删除购物车信息
	 * @param merchCar
	 */
	int deleteBatchMerchCar(List<MerchCar> merchCar);
}
