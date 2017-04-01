/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.ClassifyMapper;
import com.huateng.xhcp.model.product.Classify;
import com.huateng.xhcp.service.product.ClassifyService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 产品分类信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class ClassifyServiceImp implements ClassifyService {

	private @Autowired @Setter @Getter ClassifyMapper classifyMapper;
	/**
	 * 查询产品分类信息信息
	 * @param classify
	 * @return
	 */
	public List<Classify> queryClassify(Classify classify){
		int start = classify.getStart();
		int limit = classify.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.classifyMapper.queryClassify(classify);
	}
	/**
	 * 查询产品分类信息信息
	 * @return
	 */
	public List<Classify> queryBy(Classify classify){
		return this.classifyMapper.queryClassify(classify);
	}
	/**
	 * 查询产品分类信息信息
	 * @return
	 */
	public List<Classify> queryRoot(){
		Classify classify = new Classify();
		classify.setPcls_id("-1");
		return this.classifyMapper.queryClassify(classify);
	}

    /**
     * 获取二级分类
     * @return
     */
	public List<Classify> querySub(){
        return this.classifyMapper.querySub();
    }
	/**
	 * 根据Key查询产品分类信息信息
	 * @param classify_id
	 * @return
	 */
	public Classify queryByKey(String classify_id){
		Classify classify = new Classify();
		classify.setClassify_id(classify_id);
		List<Classify> list = this.classifyMapper.queryClassify(classify);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增产品分类信息信息
	 * @param classify
	 */
	public int addClassify(Classify classify){
		return this.classifyMapper.addClassify(classify);
	}
	/**
	 * 批量新增产品分类信息信息
	 * @param classify
	 */
	public void addBatchClassify(List<Classify> classify){
		if(classify == null || classify.isEmpty()){
			return;
		}
		this.classifyMapper.addBatchClassify(classify);
	}
	/**
	 * 更新产品分类信息信息
	 * @param classify
	 */
	public int updateClassify(Classify classify){
		return this.classifyMapper.updateClassify(classify);
	}
	/**
	 * 根据classify_id删除产品分类信息信息
	 * @param classify
	 */
	public int deleteClassify(Classify classify){
		return this.classifyMapper.deleteClassify(classify);
	}
	/**
	 * 根据classify_id批量删除产品分类信息信息
	 * @param classify
	 */
	public void deleteBatchClassify(List<Classify> classify){
		if(classify == null || classify.isEmpty()){
			return;
		}
		this.classifyMapper.deleteBatchClassify(classify);
	}
}
