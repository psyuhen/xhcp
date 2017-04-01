/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.CityMapper;
import com.huateng.xhcp.model.system.City;
import com.huateng.xhcp.service.system.CityService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 国家城市信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class CityServiceImp implements CityService {

	private @Autowired @Setter @Getter CityMapper cityMapper;
	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	public List<City> queryCity(City city){
		int start = city.getStart();
		int limit = city.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.cityMapper.queryCity(city);
	}

	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	public List<City> queryBy(City city){
		return this.cityMapper.queryCity(city);
	}
	/**
	 * 根据Key查询国家城市信息信息
	 * @param city_code
	 * @return
	 */
	public City queryByKey(String city_code){
		City city = new City();
		city.setCity_code(city_code);
		List<City> list = this.cityMapper.queryCity(city);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增国家城市信息信息
	 * @param city
	 */
	public int addCity(City city){
		return this.cityMapper.addCity(city);
	}
	/**
	 * 批量新增国家城市信息信息
	 * @param city
	 */
	public void addBatchCity(List<City> city){
		if(city == null || city.isEmpty()){
			return;
		}
		this.cityMapper.addBatchCity(city);
	}
	/**
	 * 更新国家城市信息信息
	 * @param city
	 */
	public int updateCity(City city){
		return this.cityMapper.updateCity(city);
	}
	/**
	 * 根据city_code删除国家城市信息信息
	 * @param city
	 */
	public int deleteCity(City city){
		return this.cityMapper.deleteCity(city);
	}
	/**
	 * 根据city_code批量删除国家城市信息信息
	 * @param city
	 */
	public void deleteBatchCity(List<City> city){
		if(city == null || city.isEmpty()){
			return;
		}
		this.cityMapper.deleteBatchCity(city);
	}
}
