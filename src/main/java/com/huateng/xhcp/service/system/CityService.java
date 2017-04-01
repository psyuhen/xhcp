/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.City;

/**
 * 国家城市信息服务类
 * @author sam.pan
 *
 */
public interface CityService {
	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	List<City> queryCity(City city);
	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	List<City> queryBy(City city);
	/**
	 * 根据Key查询国家城市信息信息
	 * @param city_code
	 * @return
	 */
	City queryByKey(String city_code);
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
