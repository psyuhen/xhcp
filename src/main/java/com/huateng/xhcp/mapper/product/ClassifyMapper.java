/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.Classify;

/**
 * 产品分类信息Mapper
 * @author sam.pan
 *
 */
public interface ClassifyMapper {
	/**
	 * 查询产品分类信息信息
	 * @param classify
	 * @return
	 */
	List<Classify> queryClassify(Classify classify);
	/**
	 * 查询产品分类信息信息
	 * @return
	 */
	List<Classify> querySub();
	/**
	 * 新增产品分类信息信息
	 * @param classify
	 */
	int addClassify(Classify classify);
	/**
	 * 批量新增产品分类信息信息
	 * @param classify
	 */
	void addBatchClassify(List<Classify> classify);
	/**
	 * 更新产品分类信息信息
	 * @param classify
	 */
	int updateClassify(Classify classify);
	/**
	 * 根据classify_id删除产品分类信息信息
	 * @param classify
	 */
	int deleteClassify(Classify classify);
	/**
	 * 根据classify_id批量删除产品分类信息信息
	 * @param classify
	 */
	void deleteBatchClassify(List<Classify> classify);
}
