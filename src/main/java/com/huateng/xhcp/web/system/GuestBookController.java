/**
 * 
 */
package com.huateng.xhcp.web.system;

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
import com.huateng.xhcp.model.system.GuestBook;
import com.huateng.xhcp.service.system.GuestBookService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 访客留言簿信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class GuestBookController implements com.huateng.xhcp.service.upload.Validator<GuestBook>{
	private static final Log LOGGER = LogFactory.getLog(GuestBookController.class);
	private @Autowired @Setter @Getter GuestBookService guestBookService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/guest/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "msgmgr");
		return "system/GuestBookList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/guest/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "msgmgr");
		return "system/GuestBookList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/guest/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "msgmgr");
		return "system/GuestBookAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/guest/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "msgmgr");
		return "system/GuestBookAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/guest/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/GuestBookAdd";
	}
	
	/**
	 * 查询访客留言簿信息信息(分页)
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/queryGuestBookPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryGuestBook(GuestBook guestBook){
		Page<GuestBook> list = (Page<GuestBook>)this.guestBookService.queryGuestBook(guestBook);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询访客留言簿信息信息
	 * @param msg_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/queryByKey", method = RequestMethod.GET)
	public GuestBook queryByKey(String msg_id){
		return this.guestBookService.queryByKey(msg_id);
	}

	/**
	 * 新增访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/guest/addGuestBook", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> insertGuestBook(GuestBook guestBook){
		return addGuestBook(guestBook);
	}
	/**
	 * 新增访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/addGuestBook", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addGuestBook(GuestBook guestBook){
		try{
			ResponseInfo responseInfo = validate(guestBook);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.guestBookService.addGuestBook(guestBook);
			if(c==0){
				return HttpUtil.failure("新增访客留言簿信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增访客留言簿信息失败!");
		}
		
		return HttpUtil.success("新增访客留言簿信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(GuestBook guestBook){
		if(guestBook == null){
			return ResponseInfo.fail("访客留言簿信息信息为空!");
		}
		
		String msg_id = guestBook.getMsg_id();
		int msg_idLength = com.huateng.xhcp.util.Validator.mysql(msg_id);
		if(msg_idLength > 10){
			return ResponseInfo.fail("留言ID必须少于10 个字符!");
		}

		String name = guestBook.getName();
		int nameLength = com.huateng.xhcp.util.Validator.mysql(name);
		if(nameLength > 60){
			return ResponseInfo.fail("姓名必须少于60 个字符!");
		}

		String phone = guestBook.getPhone();
		int phoneLength = com.huateng.xhcp.util.Validator.mysql(phone);
		if(phoneLength > 60){
			return ResponseInfo.fail("电话必须少于60 个字符!");
		}

		String email = guestBook.getEmail();
		int emailLength = com.huateng.xhcp.util.Validator.mysql(email);
		if(emailLength > 96){
			return ResponseInfo.fail("邮箱必须少于96 个字符!");
		}

		String address = guestBook.getAddress();
		int addressLength = com.huateng.xhcp.util.Validator.mysql(address);
		if(addressLength > 200){
			return ResponseInfo.fail("地址必须少于200 个字符!");
		}

		String msg_info = guestBook.getMsg_info();
		int msg_infoLength = com.huateng.xhcp.util.Validator.mysql(msg_info);
		if(msg_infoLength > 500){
			return ResponseInfo.fail("留言必须少于500 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/updateGuestBook", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateGuestBook(GuestBook guestBook){
		try{
			ResponseInfo responseInfo = validate(guestBook);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.guestBookService.updateGuestBook(guestBook);
			if(c == 0){
				return HttpUtil.failure("更新访客留言簿信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新访客留言簿信息失败!");
		}
		
		return HttpUtil.success("更新访客留言簿信息成功!");
	}
	
	/**
	 * 删除访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/deleteGuestBook", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteGuestBook(GuestBook guestBook){
		try{
			int c = this.guestBookService.deleteGuestBook(guestBook);
			if(c == 0){
				return HttpUtil.failure("删除访客留言簿信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除访客留言簿信息失败!");
		}
		
		return HttpUtil.success("删除访客留言簿信息成功!");
	}
	/**
	 * 批量删除访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/guest/deleteBatchGuestBook", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchGuestBook(List<GuestBook> guestBook){
		try{
			this.guestBookService.deleteBatchGuestBook(guestBook);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除访客留言簿信息失败!");
		}
		
		return HttpUtil.success("批量删除访客留言簿信息成功!");
	}
}
