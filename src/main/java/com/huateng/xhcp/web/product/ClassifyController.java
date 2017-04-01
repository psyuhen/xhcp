/**
 * 
 */
package com.huateng.xhcp.web.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.Classify;
import com.huateng.xhcp.service.product.ClassifyService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 产品分类信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class ClassifyController implements com.huateng.xhcp.service.upload.Validator<Classify>{
	private static final Log LOGGER = LogFactory.getLog(ClassifyController.class);
	private @Autowired @Setter @Getter ClassifyService classifyService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/classify/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "classifyList");
		return "product/ClassifyList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/classify/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "classifyList");
		return "product/ClassifyList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/classify/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "classifyList");
		request.setAttribute("PCLSIDS", this.classifyService.queryRoot());
		return "product/ClassifyAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/classify/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "classifyList");
		request.setAttribute("PCLSIDS", this.classifyService.queryRoot());
		return "product/ClassifyAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/classify/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		request.setAttribute("PCLSIDS", this.classifyService.queryRoot());
		return "product/ClassifyAdd";
	}
	
	/**
	 * 查询产品分类信息信息(分页)
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/queryClassifyPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryClassify(Classify classify){
		Page<Classify> list = (Page<Classify>)this.classifyService.queryClassify(classify);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}

	/**
	 * 查询产品分类信息信息(分页)
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/queryBy", method = RequestMethod.POST)
	public List<Classify> queryBy(Classify classify){
		List<Classify> list = this.classifyService.queryBy(classify);

		return list;
	}
	
	/**
	 * 根据Key查询产品分类信息信息
	 * @param classify_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/queryByKey", method = RequestMethod.GET)
	public Classify queryByKey(String classify_id){
		return this.classifyService.queryByKey(classify_id);
	}
	/**
	 * 新增产品分类信息信息
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/addClassify", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addClassify(Classify classify){
		try{
			ResponseInfo responseInfo = validate(classify);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}


			int c = this.classifyService.addClassify(classify);
            if (c == 0){
                return HttpUtil.failure("新增产品分类信息失败!");
            }
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增产品分类信息失败!");
		}
		
		return HttpUtil.success("新增产品分类信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(Classify classify){
		if(classify == null){
			return ResponseInfo.fail("产品分类信息信息为空!");
		}
		
		String name = classify.getName();
		int nameLength = com.huateng.xhcp.util.Validator.mysql(name);
		if(nameLength > 50){
			return ResponseInfo.fail("分类名称必须少于50 个字符!");
		}

		String desc = classify.getDesc();
		int descLength = com.huateng.xhcp.util.Validator.mysql(desc);
		if(descLength > 200){
			return ResponseInfo.fail("分类描述必须少于200 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新产品分类信息信息
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/updateClassify", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateClassify(Classify classify){
		try{
			ResponseInfo responseInfo = validate(classify);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.classifyService.updateClassify(classify);
            if(c == 0){
                return HttpUtil.failure("更新产品分类信息失败!");
            }
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新产品分类信息失败!");
		}
		
		return HttpUtil.success("更新产品分类信息成功!");
	}
	
	/**
	 * 删除产品分类信息信息
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/deleteClassify", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteClassify(Classify classify){
		try{
			int c = this.classifyService.deleteClassify(classify);
            if(c == 0){
                return HttpUtil.failure("删除产品分类信息失败!");
            }
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除产品分类信息失败!");
		}
		
		return HttpUtil.success("删除产品分类信息成功!");
	}
	/**
	 * 批量删除产品分类信息信息
	 * @param classify
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/classify/deleteBatchClassify", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchClassify(List<Classify> classify){
		try{
			this.classifyService.deleteBatchClassify(classify);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除产品分类信息失败!");
		}
		
		return HttpUtil.success("批量删除产品分类信息成功!");
	}
}
