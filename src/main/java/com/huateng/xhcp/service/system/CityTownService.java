/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.CityTown;

/**
 * 城市城区信息服务类
 * @author sam.pan
 *
 */
public interface CityTownService {
	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	List<CityTown> queryCityTown(CityTown cityTown);
	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	List<CityTown> queryBy(CityTown cityTown);
	/**
	 * 根据Key查询城市城区信息信息
	 * @param town_code
	 * @return
	 */
	CityTown queryByKey(String town_code);
	/**
	 * 新增城市城区信息信息
	 * @param cityTown
	 */
	int addCityTown(CityTown cityTown);
	/**
	 * 批量新增城市城区信息信息
	 * @param cityTown
	 */
	void addBatchCityTown(List<CityTown> cityTown);
	/**
	 * 更新城市城区信息信息
	 * @param cityTown
	 */
	int updateCityTown(CityTown cityTown);
	/**
	 * 根据town_code删除城市城区信息信息
	 * @param cityTown
	 */
	int deleteCityTown(CityTown cityTown);
	/**
	 * 根据town_code批量删除城市城区信息信息
	 * @param cityTown
	 */
	void deleteBatchCityTown(List<CityTown> cityTown);
}
