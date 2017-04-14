/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.MerchCar;

/**
 * 购物车Mapper
 * @author sam.pan
 *
 */
public interface MerchCarMapper {
	/**
	 * 查询购物车信息
	 * @param merchCar
	 * @return
	 */
	List<MerchCar> queryMerchCar(MerchCar merchCar);
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
	 * 根据account_id删除购物车信息
	 * @param account_id
	 */
	int deleteByAccount(String account_id);
	/**
	 * 根据car_id批量删除购物车信息
	 * @param merchCar
	 */
	int deleteBatchMerchCar(List<MerchCar> merchCar);
}
