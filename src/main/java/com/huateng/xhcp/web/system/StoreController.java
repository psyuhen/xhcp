/**
 * 
 */
package com.huateng.xhcp.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
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
import com.huateng.xhcp.model.system.Store;
import com.huateng.xhcp.model.system.StoreStatus;
import com.huateng.xhcp.service.system.StoreService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 店铺信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class StoreController implements com.huateng.xhcp.service.upload.Validator<Store>{
	private static final Log LOGGER = LogFactory.getLog(StoreController.class);
	private @Autowired @Setter @Getter StoreService storeService;
	
	/**
	 * 店铺页面
	 * @return
	 */
	@RequestMapping(value = "/store.html")
	public String toStorePage(HttpServletRequest request){
		Store store = new Store();
		store.setIs_use(StoreStatus.YES.toString());
		store.setStart(1);
		store.setLimit(1);
		List<Store> queryStore = storeService.queryStore(store);
		
		if(queryStore != null && !queryStore.isEmpty()){
			request.setAttribute("store", queryStore.get(0));
		}else{
			request.setAttribute("store", null);
		}
		
		return "band";
	}
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/store/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "storemgr");
		return "system/StoreList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/store/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "storemgr");
		return "system/StoreList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/store/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "storemgr");
		return "system/StoreAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/store/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "storemgr");
		return "system/StoreAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/store/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/StoreAdd";
	}
	
	/**
	 * 查询店铺信息信息(分页)
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/queryStorePage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryStore(Store store){
		Page<Store> list = (Page<Store>)this.storeService.queryStore(store);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询店铺信息信息
	 * @param store_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/queryByKey", method = RequestMethod.GET)
	public Store queryByKey(String store_id){
		return this.storeService.queryByKey(store_id);
	}
	/**
	 * 新增店铺信息信息
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/addStore", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addStore(Store store){
		try{
			ResponseInfo responseInfo = validate(store);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.storeService.addStore(store);
			if(c==0){
				return HttpUtil.failure("新增店铺信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增店铺信息失败!");
		}
		
		return HttpUtil.success("新增店铺信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(Store store){
		if(store == null){
			return ResponseInfo.fail("店铺信息信息为空!");
		}
		
		String store_id = store.getStore_id();
		int store_idLength = com.huateng.xhcp.util.Validator.mysql(store_id);
		if(store_idLength > 10){
			return ResponseInfo.fail("店铺ID必须少于10 个字符!");
		}

		String store_name = store.getStore_name();
		int store_nameLength = com.huateng.xhcp.util.Validator.mysql(store_name);
		if(store_nameLength > 200){
			return ResponseInfo.fail("店铺名称必须少于200 个字符!");
		}

		String contents = store.getContents();
		int contentsLength = com.huateng.xhcp.util.Validator.mysql(contents);
		if(contentsLength > 10000){
			return ResponseInfo.fail("店铺内容必须少于10000 个字符!");
		}

		String is_use = store.getIs_use();
		int is_useLength = com.huateng.xhcp.util.Validator.mysql(is_use);
		if(is_useLength > 1){
			return ResponseInfo.fail("是否使用必须少于1 个字符!");
		}


		//默认为1
		if(StringUtils.isBlank(is_use)){
			store.setIs_use(StoreStatus.NO.toString());
		}
		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新店铺信息信息
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/updateStore", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateStore(Store store){
		try{
			ResponseInfo responseInfo = validate(store);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.storeService.updateStore(store);
			if(c == 0){
				return HttpUtil.failure("更新店铺信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新店铺信息失败!");
		}
		
		return HttpUtil.success("更新店铺信息成功!");
	}
	
	/**
	 * 删除店铺信息信息
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/deleteStore", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteStore(Store store){
		try{
			int c = this.storeService.deleteStore(store);
			if(c == 0){
				return HttpUtil.failure("删除店铺信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除店铺信息失败!");
		}
		
		return HttpUtil.success("删除店铺信息成功!");
	}
	/**
	 * 批量删除店铺信息信息
	 * @param store
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/store/deleteBatchStore", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchStore(List<Store> store){
		try{
			this.storeService.deleteBatchStore(store);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除店铺信息失败!");
		}
		
		return HttpUtil.success("批量删除店铺信息成功!");
	}
}
