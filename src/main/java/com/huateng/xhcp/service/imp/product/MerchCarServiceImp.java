/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.MerchCarMapper;
import com.huateng.xhcp.model.product.MerchCar;
import com.huateng.xhcp.service.product.MerchCarService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 购物车服务实现类
 * @author sam.pan
 *
 */
@Service
public class MerchCarServiceImp implements MerchCarService {

	private @Autowired @Setter @Getter MerchCarMapper merchCarMapper;
	/**
	 * 查询购物车信息
	 * @param merchCar
	 * @return
	 */
	public List<MerchCar> queryMerchCar(MerchCar merchCar){
		int start = merchCar.getStart();
		int limit = merchCar.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.merchCarMapper.queryMerchCar(merchCar);
	}
	/**
	 * 查询购物车信息
	 * @param merchCar
	 * @return
	 */
	public List<MerchCar> queryBy(MerchCar merchCar){
		return this.merchCarMapper.queryMerchCar(merchCar);
	}
	/**
	 * 根据Key查询购物车信息
	 * @param car_id
	 * @return
	 */
	public MerchCar queryByKey(String car_id){
		MerchCar merchCar = new MerchCar();
		merchCar.setCar_id(car_id);
		List<MerchCar> list = this.merchCarMapper.queryMerchCar(merchCar);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增购物车信息
	 * @param merchCar
	 */
	public int addMerchCar(MerchCar merchCar){
		return this.merchCarMapper.addMerchCar(merchCar);
	}
	/**
	 * 批量新增购物车信息
	 * @param merchCar
	 */
	public int addBatchMerchCar(List<MerchCar> merchCar){
		if(merchCar == null || merchCar.isEmpty()){
			return 0;
		}
		return this.merchCarMapper.addBatchMerchCar(merchCar);
	}
	/**
	 * 更新购物车信息
	 * @param merchCar
	 */
	public int updateMerchCar(MerchCar merchCar){
		return this.merchCarMapper.updateMerchCar(merchCar);
	}
	/**
	 * 根据car_id删除购物车信息
	 * @param merchCar
	 */
	public int deleteMerchCar(MerchCar merchCar){
		return this.merchCarMapper.deleteMerchCar(merchCar);
	}
	/**
	 * 根据car_id批量删除购物车信息
	 * @param merchCar
	 */
	public int deleteBatchMerchCar(List<MerchCar> merchCar){
		if(merchCar == null || merchCar.isEmpty()){
			return 0;
		}
		return this.merchCarMapper.deleteBatchMerchCar(merchCar);
	}
}
