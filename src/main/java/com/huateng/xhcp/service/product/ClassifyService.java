/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.Classify;

/**
 * 产品分类信息服务类
 * @author sam.pan
 *
 */
public interface ClassifyService {
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
	List<Classify> queryBy(Classify classify);
	/**
	 * 查询产品分类信息信息
	 * @return
	 */
	List<Classify> queryRoot();
	/**
	 * 查询产品分类信息信息
	 * @return
	 */
	List<Classify> querySub();
	/**
	 * 根据Key查询产品分类信息信息
	 * @param classify_id
	 * @return
	 */
	Classify queryByKey(String classify_id);
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
