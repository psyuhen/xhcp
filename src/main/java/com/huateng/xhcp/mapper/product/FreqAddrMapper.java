/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.FreqAddr;

/**
 * 常用地址信息Mapper
 * @author sam.pan
 *
 */
public interface FreqAddrMapper {
	/**
	 * 查询常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	List<FreqAddr> queryFreqAddr(FreqAddr freqAddr);

	/**
	 * 根据account_id查询常用地址信息信息
	 * @param account_id
	 * @return
	 */
	List<FreqAddr> queryByAccountId(String account_id);
	/**
	 * 新增常用地址信息信息
	 * @param freqAddr
	 */
	int addFreqAddr(FreqAddr freqAddr);
	/**
	 * 批量新增常用地址信息信息
	 * @param freqAddr
	 */
	void addBatchFreqAddr(List<FreqAddr> freqAddr);
	/**
	 * 设置默认的地址
	 * @param freqAddr
	 */
	int updateDefaultAddr(FreqAddr freqAddr);
	/**
	 * 更新常用地址信息信息
	 * @param freqAddr
	 */
	int updateFreqAddr(FreqAddr freqAddr);
	/**
	 * 根据freq_id删除常用地址信息信息
	 * @param freqAddr
	 */
	int deleteFreqAddr(FreqAddr freqAddr);
	/**
	 * 根据freq_id批量删除常用地址信息信息
	 * @param freqAddr
	 */
	void deleteBatchFreqAddr(List<FreqAddr> freqAddr);
}
