/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.ProvinceMapper;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 国家省份信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class ProvinceServiceImp implements ProvinceService {

	private @Autowired @Setter @Getter ProvinceMapper provinceMapper;
	/**
	 * 查询国家省份信息信息
	 * @param province
	 * @return
	 */
	public List<Province> queryProvince(Province province){
		int start = province.getStart();
		int limit = province.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.provinceMapper.queryProvince(province);
	}
	/**
	 * 查询国家省份信息信息
	 * @param province
	 * @return
	 */
	public List<Province> queryBy(Province province){
		return this.provinceMapper.queryProvince(province);
	}

	/**
	 * 根据Key查询国家省份信息信息
	 * @param province_code
	 * @return
	 */
	public Province queryByKey(String province_code){
		Province province = new Province();
		province.setProvince_code(province_code);
		List<Province> list = this.provinceMapper.queryProvince(province);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增国家省份信息信息
	 * @param province
	 */
	public int addProvince(Province province){
		return this.provinceMapper.addProvince(province);
	}
	/**
	 * 批量新增国家省份信息信息
	 * @param province
	 */
	public void addBatchProvince(List<Province> province){
		if(province == null || province.isEmpty()){
			return;
		}
		this.provinceMapper.addBatchProvince(province);
	}
	/**
	 * 更新国家省份信息信息
	 * @param province
	 */
	public int updateProvince(Province province){
		return this.provinceMapper.updateProvince(province);
	}
	/**
	 * 根据province_code删除国家省份信息信息
	 * @param province
	 */
	public int deleteProvince(Province province){
		return this.provinceMapper.deleteProvince(province);
	}
	/**
	 * 根据province_code批量删除国家省份信息信息
	 * @param province
	 */
	public void deleteBatchProvince(List<Province> province){
		if(province == null || province.isEmpty()){
			return;
		}
		this.provinceMapper.deleteBatchProvince(province);
	}
}
