/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.FreqAddrMapper;
import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 常用地址信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class FreqAddrServiceImp implements FreqAddrService {

	private @Autowired @Setter @Getter FreqAddrMapper freqAddrMapper;
	/**
	 * 查询常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	public List<FreqAddr> queryFreqAddr(FreqAddr freqAddr){
		int start = freqAddr.getStart();
		int limit = freqAddr.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.freqAddrMapper.queryFreqAddr(freqAddr);
	}
	/**
	 * 根据account_id查询常用地址信息信息
	 * @param account_id
	 * @return
	 */
	public List<FreqAddr> queryByAccountId(String account_id){
		return this.freqAddrMapper.queryByAccountId(account_id);
	}
	/**
	 * 根据Key查询常用地址信息信息
	 * @param freq_id
	 * @return
	 */
	public FreqAddr queryByKey(String freq_id){
		FreqAddr freqAddr = new FreqAddr();
		freqAddr.setFreq_id(freq_id);
		List<FreqAddr> list = this.freqAddrMapper.queryFreqAddr(freqAddr);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增常用地址信息信息
	 * @param freqAddr
	 */
	public int addFreqAddr(FreqAddr freqAddr){

		int c = this.freqAddrMapper.addFreqAddr(freqAddr);

		updateda(freqAddr);

		return c;
	}
	/**
	 * 批量新增常用地址信息信息
	 * @param freqAddr
	 */
	public void addBatchFreqAddr(List<FreqAddr> freqAddr){
		if(freqAddr == null || freqAddr.isEmpty()){
			return;
		}
		this.freqAddrMapper.addBatchFreqAddr(freqAddr);
	}

	private void updateda(FreqAddr freqAddr){
		//当前被设置为默认，要把其他的变为非默认
		if(StringUtils.equals(freqAddr.getDefault_addr(), "0")){
			FreqAddr fa = new FreqAddr();
			fa.setAccount_id(freqAddr.getAccount_id());
			fa.setFreq_id(freqAddr.getFreq_id());
			this.freqAddrMapper.updateDefaultAddr(fa);
		}
	}
	/**
	 * 设置默认的地址
	 * @param freqAddr
	 */
	public int updateDefaultAddr(FreqAddr freqAddr){

		int c = this.freqAddrMapper.updateDefaultAddr(freqAddr);
		updateda(freqAddr);

		return c;
	}
	/**
	 * 更新常用地址信息信息
	 * @param freqAddr
	 */
	public int updateFreqAddr(FreqAddr freqAddr){
		return this.freqAddrMapper.updateFreqAddr(freqAddr);
	}
	/**
	 * 根据freq_id删除常用地址信息信息
	 * @param freqAddr
	 */
	public int deleteFreqAddr(FreqAddr freqAddr){
		return this.freqAddrMapper.deleteFreqAddr(freqAddr);
	}
	/**
	 * 根据freq_id批量删除常用地址信息信息
	 * @param freqAddr
	 */
	public void deleteBatchFreqAddr(List<FreqAddr> freqAddr){
		if(freqAddr == null || freqAddr.isEmpty()){
			return;
		}
		this.freqAddrMapper.deleteBatchFreqAddr(freqAddr);
	}
}
