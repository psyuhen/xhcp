/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.CityTown;

/**
 * 城市城区信息Mapper
 * @author sam.pan
 *
 */
public interface CityTownMapper {
	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	List<CityTown> queryCityTown(CityTown cityTown);
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
