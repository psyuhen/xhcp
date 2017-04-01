/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.CityTownMapper;
import com.huateng.xhcp.model.system.CityTown;
import com.huateng.xhcp.service.system.CityTownService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 城市城区信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class CityTownServiceImp implements CityTownService {

	private @Autowired @Setter @Getter CityTownMapper cityTownMapper;
	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	public List<CityTown> queryCityTown(CityTown cityTown){
		int start = cityTown.getStart();
		int limit = cityTown.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.cityTownMapper.queryCityTown(cityTown);
	}

	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	public List<CityTown> queryBy(CityTown cityTown){
		return this.cityTownMapper.queryCityTown(cityTown);
	}
	/**
	 * 根据Key查询城市城区信息信息
	 * @param town_code
	 * @return
	 */
	public CityTown queryByKey(String town_code){
		CityTown cityTown = new CityTown();
		cityTown.setTown_code(town_code);
		List<CityTown> list = this.cityTownMapper.queryCityTown(cityTown);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增城市城区信息信息
	 * @param cityTown
	 */
	public int addCityTown(CityTown cityTown){
		return this.cityTownMapper.addCityTown(cityTown);
	}
	/**
	 * 批量新增城市城区信息信息
	 * @param cityTown
	 */
	public void addBatchCityTown(List<CityTown> cityTown){
		if(cityTown == null || cityTown.isEmpty()){
			return;
		}
		this.cityTownMapper.addBatchCityTown(cityTown);
	}
	/**
	 * 更新城市城区信息信息
	 * @param cityTown
	 */
	public int updateCityTown(CityTown cityTown){
		return this.cityTownMapper.updateCityTown(cityTown);
	}
	/**
	 * 根据town_code删除城市城区信息信息
	 * @param cityTown
	 */
	public int deleteCityTown(CityTown cityTown){
		return this.cityTownMapper.deleteCityTown(cityTown);
	}
	/**
	 * 根据town_code批量删除城市城区信息信息
	 * @param cityTown
	 */
	public void deleteBatchCityTown(List<CityTown> cityTown){
		if(cityTown == null || cityTown.isEmpty()){
			return;
		}
		this.cityTownMapper.deleteBatchCityTown(cityTown);
	}
}
