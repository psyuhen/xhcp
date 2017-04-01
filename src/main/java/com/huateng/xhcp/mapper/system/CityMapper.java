/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.City;

/**
 * 国家城市信息Mapper
 * @author sam.pan
 *
 */
public interface CityMapper {
	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	List<City> queryCity(City city);
	/**
	 * 新增国家城市信息信息
	 * @param city
	 */
	int addCity(City city);
	/**
	 * 批量新增国家城市信息信息
	 * @param city
	 */
	void addBatchCity(List<City> city);
	/**
	 * 更新国家城市信息信息
	 * @param city
	 */
	int updateCity(City city);
	/**
	 * 根据city_code删除国家城市信息信息
	 * @param city
	 */
	int deleteCity(City city);
	/**
	 * 根据city_code批量删除国家城市信息信息
	 * @param city
	 */
	void deleteBatchCity(List<City> city);
}
