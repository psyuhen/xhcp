/**
 * 
 */
package com.huateng.xhcp.web.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huateng.xhcp.model.product.Classify;
import com.huateng.xhcp.model.product.MerchGallery;
import com.huateng.xhcp.service.product.ClassifyService;
import com.huateng.xhcp.service.product.MerchGalleryService;
import com.huateng.xhcp.service.upload.UploadCallback;
import com.huateng.xhcp.service.upload.UploadType;
import com.huateng.xhcp.util.UploadFileUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.MerchInfo;
import com.huateng.xhcp.service.product.MerchInfoService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 产品 Controller类
 * @author sam.pan
 *
 */
@Controller
public class MerchInfoController implements com.huateng.xhcp.service.upload.Validator<MerchInfo>{
	private static final Log LOGGER = LogFactory.getLog(MerchInfoController.class);
	private @Autowired @Setter @Getter MerchInfoService merchInfoService;
	private @Autowired @Setter @Getter MerchGalleryService merchGalleryService;
	private @Autowired @Setter @Getter ClassifyService classifyService;

	/**
	 * 产品详情
	 * @return
	 */
	@RequestMapping(value = "/products-{merch_id}.html")
	public String productsdetail(@PathVariable String merch_id, HttpServletRequest request){
		if(StringUtils.isBlank(merch_id)){
			return "common/404";
		}

		MerchInfo merchInfo = this.merchInfoService.queryByKey(merch_id);
		if(merchInfo == null){
			return "common/404";
		}

		request.setAttribute("merchInfo", merchInfo);

		MerchGallery gallery = new MerchGallery();
		gallery.setMerch_id(merch_id);
		gallery.setFile_type("1");
		final List<MerchGallery> galleries = this.merchGalleryService.queryBy(gallery);

		request.setAttribute("galleries", galleries);

		gallery.setFile_type("0");
		final List<MerchGallery> photos = this.merchGalleryService.queryBy(gallery);

		this.merchInfoService.updateHits(merch_id);

		request.setAttribute("photos", photos);
		return "product/products-detail";
	}
	/**
	 * 产品系列
	 * @return
	 */
	@RequestMapping(value = "/products-top-{classify_id}.html")
	public String productstop(@PathVariable String classify_id, HttpServletRequest request){
		if(StringUtils.isBlank(classify_id)){
			return "common/404";
		}

		List<Classify> root = this.classifyService.queryRoot();
		List<Classify> sub = this.classifyService.querySub();
		request.setAttribute("RootClassify", root);
		request.setAttribute("SubClassify", sub);


		for(Classify r : root){
			if(StringUtils.equals(r.getClassify_id(), classify_id)){
				request.setAttribute("curNavClassify", r.getName());
				break;
			}
		}


		MerchInfo merchInfo = new MerchInfo();
		merchInfo.setClassify_id(classify_id);
		List<MerchInfo> merchInfos = this.merchInfoService.queryByPclsId(classify_id);
		request.setAttribute("merchInfos", merchInfos);
		return "product/products-top";
	}
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/products-list-{classify_id}.html")
	public String productslist(@PathVariable String classify_id, HttpServletRequest request){
		if(StringUtils.isBlank(classify_id)){
			return "common/404";
		}

		List<Classify> root = this.classifyService.queryRoot();
		List<Classify> sub = this.classifyService.querySub();
		request.setAttribute("RootClassify", root);
		request.setAttribute("SubClassify", sub);

		String pclsId = "";
		for(Classify s : sub){
			if(StringUtils.equals(s.getClassify_id(), classify_id)){
				pclsId = s.getPcls_id();
				request.setAttribute("nextNavClassify", s.getName());
				break;
			}
		}
		for(Classify r : root){
			if(StringUtils.equals(r.getClassify_id(), pclsId)){
				request.setAttribute("curNavClassify", r.getName());
				break;
			}
		}

		MerchInfo merchInfo = new MerchInfo();
		merchInfo.setClassify_id(classify_id);
		List<MerchInfo> merchInfos = this.merchInfoService.queryBy(merchInfo);
		request.setAttribute("merchInfos", merchInfos);
		return "product/products-list";
	}

    /**
     * 初始化页面
     * @return
     */
    @RequestMapping(value = "/products.html")
    public String products(HttpServletRequest request){
		List<Classify> root = this.classifyService.queryRoot();
		List<Classify> sub = this.classifyService.querySub();
		request.setAttribute("RootClassify", root);
		request.setAttribute("SubClassify", sub);

		String classifyId = "";
		if(root != null && !root.isEmpty()){
			Classify r = root.get(0);
			request.setAttribute("curNavClassify", r.getName());
			for(Classify s : sub){
				if(StringUtils.equals(r.getClassify_id(), s.getPcls_id())){
					classifyId = s.getClassify_id();
					request.setAttribute("nextNavClassify", s.getName());
					break;
				}
			}
		}

		//查询三条记录
		MerchInfo merchInfo = new MerchInfo();
		merchInfo.setStart(1);
		merchInfo.setLimit(3);
		merchInfo.setClassify_id(classifyId);
		List<MerchInfo> merchInfos = this.merchInfoService.queryMerchInfo(merchInfo);

		if(merchInfos != null){
			for(MerchInfo mi : merchInfos){
				MerchGallery mg = new MerchGallery();
				mg.setMerch_id(mi.getMerch_id());
				List<MerchGallery> merchGalleries = this.merchGalleryService.queryBy(mg);
				if(merchGalleries == null || merchGalleries.isEmpty()){
					continue;
				}

				MerchGallery merchGallery = merchGalleries.get(0);
				mi.setMerch_photo(merchGallery.getPath() + merchGallery.getName());
			}
		}

		request.setAttribute("merchInfos", merchInfos);
		return "product/products";
    }

	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "productsmgr");
		return "product/MerchInfoList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "productsmgr");
		return "product/MerchInfoList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "productsmgr");
		request.setAttribute("RootClassify", this.classifyService.queryRoot());
		return "product/MerchInfoAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "productsmgr");
		request.setAttribute("RootClassify", this.classifyService.queryRoot());
		return "product/MerchInfoAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		request.setAttribute("RootClassify", this.classifyService.queryRoot());
		return "product/MerchInfoAdd";
	}
	
	/**
	 * 查询产品信息(分页)
	 * @param merchInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/queryMerchInfoPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryMerchInfo(MerchInfo merchInfo){
		Page<MerchInfo> list = (Page<MerchInfo>)this.merchInfoService.queryMerchInfo(merchInfo);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询产品信息
	 * @param merch_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/queryByKey", method = RequestMethod.GET)
	public MerchInfo queryByKey(String merch_id){
		return this.merchInfoService.queryByKey(merch_id);
	}
	/**
	 * 新增产品信息
	 * @param merchInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/addMerchInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addMerchInfo(MerchInfo merchInfo, HttpServletRequest request){
		try{
			ResponseInfo responseInfo = validate(merchInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}

			final List<MerchGallery> merchGalleries = new ArrayList<MerchGallery>();
			UploadType uploadType = UploadFileUtil.uploadPhoto(null, request, new UploadCallback<MerchGallery>() {
				@Override
				public UploadType callback(List<MerchGallery> fileInfos) {
					if(fileInfos == null || fileInfos.isEmpty()){//没有上传文件的时候也返回成功
						return UploadType.SUCCESS;
					}

					merchGalleries.addAll(fileInfos);
					return UploadType.SUCCESS;
				}
			});
			if(uploadType == UploadType.SUCCESS){
				int c = this.merchInfoService.addMerchInfo(merchInfo,merchGalleries);
				if(c==0){
					return HttpUtil.failure("新增产品失败!");
				}
			}else{
				return HttpUtil.failure(uploadType.getReasonPhrase());
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增产品失败!");
		}
		
		return HttpUtil.success("新增产品成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(MerchInfo merchInfo){
		if(merchInfo == null){
			return ResponseInfo.fail("产品信息为空!");
		}
		
		String merch_id = merchInfo.getMerch_id();
		int merch_idLength = com.huateng.xhcp.util.Validator.mysql(merch_id);
		if(merch_idLength > 10){
			return ResponseInfo.fail("商品ID必须少于10 个字符!");
		}

		String name = merchInfo.getName();
		int nameLength = com.huateng.xhcp.util.Validator.mysql(name);
		if(nameLength > 300){
			return ResponseInfo.fail("商品名称必须少于300 个字符!");
		}

		String desc = merchInfo.getDesc();
		int descLength = com.huateng.xhcp.util.Validator.mysql(desc);
		if(descLength > 1000){
			return ResponseInfo.fail("商品描述必须少于1000 个字符!");
		}

		String classify_id = merchInfo.getClassify_id();
		int classify_idLength = com.huateng.xhcp.util.Validator.mysql(classify_id);
		if(classify_idLength > 10){
			return ResponseInfo.fail("分类ID必须少于10 个字符!");
		}

		String price = merchInfo.getPrice();
		int priceLength = com.huateng.xhcp.util.Validator.mysql(price);
		if(priceLength > 11){
			return ResponseInfo.fail("单价必须少于11 个字符!");
		}

		String in_stock = merchInfo.getIn_stock();
		int in_stockLength = com.huateng.xhcp.util.Validator.mysql(in_stock);
		if(in_stockLength > 10){
			return ResponseInfo.fail("库存必须少于10 个字符!");
		}

		String published_date = merchInfo.getPublished_date();
		int published_dateLength = com.huateng.xhcp.util.Validator.mysql(published_date);
		if(published_dateLength > 14){
			return ResponseInfo.fail("上架时间必须少于14 个字符!");
		}

		String out_published = merchInfo.getOut_published();
		int out_publishedLength = com.huateng.xhcp.util.Validator.mysql(out_published);
		if(out_publishedLength > 1){
			return ResponseInfo.fail("是否下架必须少于1 个字符!");
		}

		String update_time = merchInfo.getUpdate_time();
		int update_timeLength = com.huateng.xhcp.util.Validator.mysql(update_time);
		if(update_timeLength > 14){
			return ResponseInfo.fail("修改时间必须少于14 个字符!");
		}

		String create_time = merchInfo.getCreate_time();
		int create_timeLength = com.huateng.xhcp.util.Validator.mysql(create_time);
		if(create_timeLength > 14){
			return ResponseInfo.fail("创建时间必须少于14 个字符!");
		}

		String sm_recommend = merchInfo.getSm_recommend();
		int sm_recommendLength = com.huateng.xhcp.util.Validator.mysql(sm_recommend);
		if(sm_recommendLength > 1){
			return ResponseInfo.fail("店长推荐必须少于1 个字符!");
		}

		String free_shipping = merchInfo.getFree_shipping();
		int free_shippingLength = com.huateng.xhcp.util.Validator.mysql(free_shipping);
		if(free_shippingLength > 1){
			return ResponseInfo.fail("是否包邮必须少于1 个字符!");
		}

		String unit = merchInfo.getUnit();
		int unitLength = com.huateng.xhcp.util.Validator.mysql(unit);
		if(unitLength > 10){
			return ResponseInfo.fail("单位必须少于10 个字符!");
		}

		String weight = merchInfo.getWeight();
		int weightLength = com.huateng.xhcp.util.Validator.mysql(weight);
		if(weightLength > 11){
			return ResponseInfo.fail("重量必须少于11 个字符!");
		}

		String standard = merchInfo.getStandard();
		int standardLength = com.huateng.xhcp.util.Validator.mysql(standard);
		if(standardLength > 10){
			return ResponseInfo.fail("规格必须少于10 个字符!");
		}

		String hits = merchInfo.getHits();
		int hitsLength = com.huateng.xhcp.util.Validator.mysql(hits);
		if(hitsLength > 10){
			return ResponseInfo.fail("点击数必须少于10 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新产品信息
	 * @param merchInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/updateMerchInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateMerchInfo(MerchInfo merchInfo, HttpServletRequest request){
		try{
			ResponseInfo responseInfo = validate(merchInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}

			final List<MerchGallery> merchGalleries = new ArrayList<MerchGallery>();
			String merchId = merchInfo.getMerch_id();
			UploadType uploadType = UploadFileUtil.uploadPhoto(merchId, request, new UploadCallback<MerchGallery>() {
				@Override
				public UploadType callback(List<MerchGallery> fileInfos) {
					if(fileInfos == null || fileInfos.isEmpty()){//没有上传文件的时候也返回成功
						return UploadType.SUCCESS;
					}

					merchGalleries.addAll(fileInfos);
					return UploadType.SUCCESS;
				}
			});
			if(uploadType == UploadType.SUCCESS){
				int c = this.merchInfoService.updateMerchInfo(merchInfo,merchGalleries);
				if(c==0){
					return HttpUtil.failure("更新产品失败!");
				}
			}else{
				return HttpUtil.failure(uploadType.getReasonPhrase());
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新产品失败!");
		}
		
		return HttpUtil.success("更新产品成功!");
	}
	
	/**
	 * 删除产品信息
	 * @param merchInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/deleteMerchInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteMerchInfo(MerchInfo merchInfo){
		try{
			int c = this.merchInfoService.deleteMerchInfo(merchInfo);
			if(c == 0){
				return HttpUtil.failure("删除产品失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除产品失败!");
		}
		
		return HttpUtil.success("删除产品成功!");
	}
	/**
	 * 批量删除产品信息
	 * @param merchInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/deleteBatchMerchInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchMerchInfo(List<MerchInfo> merchInfo){
		try{
			this.merchInfoService.deleteBatchMerchInfo(merchInfo);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除产品失败!");
		}
		
		return HttpUtil.success("批量删除产品成功!");
	}
}
