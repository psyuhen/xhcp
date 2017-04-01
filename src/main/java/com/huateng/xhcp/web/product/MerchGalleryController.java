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
import com.huateng.xhcp.model.product.MerchGallery;
import com.huateng.xhcp.service.product.MerchGalleryService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 产品图片 Controller类
 * @author sam.pan
 *
 */
@Controller
public class MerchGalleryController implements com.huateng.xhcp.service.upload.Validator<MerchGallery>{
	private static final Log LOGGER = LogFactory.getLog(MerchGalleryController.class);
	private @Autowired @Setter @Getter MerchGalleryService merchGalleryService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/gallery/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "gallerymgr");
		return "product/MerchGalleryList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/gallery/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "gallerymgr");
		return "product/MerchGalleryList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/gallery/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "gallerymgr");
		return "product/MerchGalleryAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/gallery/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "gallerymgr");
		return "product/MerchGalleryAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/gallery/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "product/MerchGalleryAdd";
	}
	
	/**
	 * 查询产品图片信息(分页)
	 * @param merchGallery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/queryMerchGalleryPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryMerchGallery(MerchGallery merchGallery){
		Page<MerchGallery> list = (Page<MerchGallery>)this.merchGalleryService.queryMerchGallery(merchGallery);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询产品图片信息
	 * @param gallery_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/queryByKey", method = RequestMethod.GET)
	public MerchGallery queryByKey(String gallery_id){
		return this.merchGalleryService.queryByKey(gallery_id);
	}
	/**
	 * 新增产品图片信息
	 * @param merchGallery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/addMerchGallery", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addMerchGallery(MerchGallery merchGallery){
		try{
			ResponseInfo responseInfo = validate(merchGallery);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.merchGalleryService.addMerchGallery(merchGallery);
			if(c==0){
				return HttpUtil.failure("新增产品图片失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增产品图片失败!");
		}
		
		return HttpUtil.success("新增产品图片成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(MerchGallery merchGallery){
		if(merchGallery == null){
			return ResponseInfo.fail("产品图片信息为空!");
		}
		
		String gallery_id = merchGallery.getGallery_id();
		int gallery_idLength = com.huateng.xhcp.util.Validator.mysql(gallery_id);
		if(gallery_idLength > 10){
			return ResponseInfo.fail("图片ID必须少于10 个字符!");
		}

		String merch_id = merchGallery.getMerch_id();
		int merch_idLength = com.huateng.xhcp.util.Validator.mysql(merch_id);
		if(merch_idLength > 10){
			return ResponseInfo.fail("商品ID必须少于10 个字符!");
		}

		String name = merchGallery.getName();
		int nameLength = com.huateng.xhcp.util.Validator.mysql(name);
		if(nameLength > 100){
			return ResponseInfo.fail("图片名称必须少于100 个字符!");
		}

		String file_name = merchGallery.getFile_name();
		int file_nameLength = com.huateng.xhcp.util.Validator.mysql(file_name);
		if(file_nameLength > 100){
			return ResponseInfo.fail("图片文件名称必须少于100 个字符!");
		}

		String path = merchGallery.getPath();
		int pathLength = com.huateng.xhcp.util.Validator.mysql(path);
		if(pathLength > 500){
			return ResponseInfo.fail("文件路径必须少于500 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新产品图片信息
	 * @param merchGallery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/updateMerchGallery", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateMerchGallery(MerchGallery merchGallery){
		try{
			ResponseInfo responseInfo = validate(merchGallery);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.merchGalleryService.updateMerchGallery(merchGallery);
			if(c == 0){
				return HttpUtil.failure("更新产品图片失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新产品图片失败!");
		}
		
		return HttpUtil.success("更新产品图片成功!");
	}
	
	/**
	 * 删除产品图片信息
	 * @param merchGallery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/deleteMerchGallery", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteMerchGallery(MerchGallery merchGallery){
		try{
			int c = this.merchGalleryService.deleteMerchGallery(merchGallery);
			if(c == 0){
				return HttpUtil.failure("删除产品图片失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除产品图片失败!");
		}
		
		return HttpUtil.success("删除产品图片成功!");
	}
	/**
	 * 批量删除产品图片信息
	 * @param merchGallery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/gallery/deleteBatchMerchGallery", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchMerchGallery(List<MerchGallery> merchGallery){
		try{
			this.merchGalleryService.deleteBatchMerchGallery(merchGallery);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除产品图片失败!");
		}
		
		return HttpUtil.success("批量删除产品图片成功!");
	}
}
