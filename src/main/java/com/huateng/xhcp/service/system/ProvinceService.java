/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.Province;

/**
 * 国家省份信息服务类
 * @author sam.pan
 *
 */
public interface ProvinceService {
	/**
	 * 查询国家省份信息信息
	 * @param province
	 * @return
	 */
	List<Province> queryProvince(Province province);
	/**
	 * 查询国家省份信息信息
	 * @param province
	 * @return
	 */
	List<Province> queryBy(Province province);
	/**
	 * 根据Key查询国家省份信息信息
	 * @param province_code
	 * @return
	 */
	Province queryByKey(String province_code);
	/**
	 * 新增国家省份信息信息
	 * @param province
	 */
	int addProvince(Province province);
	/**
	 * 批量新增国家省份信息信息
	 * @param province
	 */
	void addBatchProvince(List<Province> province);
	/**
	 * 更新国家省份信息信息
	 * @param province
	 */
	int updateProvince(Province province);
	/**
	 * 根据province_code删除国家省份信息信息
	 * @param province
	 */
	int deleteProvince(Province province);
	/**
	 * 根据province_code批量删除国家省份信息信息
	 * @param province
	 */
	void deleteBatchProvince(List<Province> province);
}
