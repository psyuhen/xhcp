/**
 * 
 */
package com.huateng.xhcp.web.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.security.SecurityContext;
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
import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 常用地址信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class FreqAddrController implements com.huateng.xhcp.service.upload.Validator<FreqAddr>{
	private static final Log LOGGER = LogFactory.getLog(FreqAddrController.class);
	private @Autowired @Setter @Getter FreqAddrService freqAddrService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/freqaddr/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "freqaddrmgr");
		return "product/FreqAddrList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/freqaddr/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "freqaddrmgr");
		return "product/FreqAddrList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/freqaddr/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "freqaddrmgr");
		return "product/FreqAddrAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/freqaddr/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "freqaddrmgr");
		return "product/FreqAddrAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/freqaddr/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "product/FreqAddrAdd";
	}
	
	/**
	 * 查询常用地址信息信息(分页)
	 * @param freqAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/queryFreqAddrPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryFreqAddr(FreqAddr freqAddr){
		Page<FreqAddr> list = (Page<FreqAddr>)this.freqAddrService.queryFreqAddr(freqAddr);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	/**
	 * 根据Key查询常用地址信息信息
	 * @param freq_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/queryByKey", method = RequestMethod.GET)
	public FreqAddr queryByKey(String freq_id){
		return this.freqAddrService.queryByKey(freq_id);
	}
	/**
	 * 新增常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/addFreqAddr", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addFreqAddr(FreqAddr freqAddr){
		try{
			ResponseInfo responseInfo = validate(freqAddr);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.freqAddrService.addFreqAddr(freqAddr);
			if(c==0){
				return HttpUtil.failure("新增常用地址信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增常用地址信息失败!");
		}
		
		return HttpUtil.success("新增常用地址信息成功!");
	}

	@ResponseBody
	@RequestMapping(value = "/freqaddr/addFreqAddr", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> insertFreqAddr(FreqAddr freqAddr,HttpServletRequest request){
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute(SecurityContext.FRONT_ACCOUNT);
		freqAddr.setAccount_id(account.getAccount_id());
		return addFreqAddr(freqAddr);
	}

	/*校验*/
	public ResponseInfo validate(FreqAddr freqAddr){
		if(freqAddr == null){
			return ResponseInfo.fail("常用地址信息信息为空!");
		}
		
		String freq_id = freqAddr.getFreq_id();
		int freq_idLength = com.huateng.xhcp.util.Validator.mysql(freq_id);
		if(freq_idLength > 10){
			return ResponseInfo.fail("常用地址id必须少于10 个字符!");
		}

		String account_id = freqAddr.getAccount_id();
		int account_idLength = com.huateng.xhcp.util.Validator.mysql(account_id);
		if(account_idLength > 96){
			return ResponseInfo.fail("用户id必须少于96 个字符!");
		}

		String province_code = freqAddr.getProvince_code();
		int province_codeLength = com.huateng.xhcp.util.Validator.mysql(province_code);
		if(province_codeLength > 10){
			return ResponseInfo.fail("省份编码必须少于10 个字符!");
		}

		String city_code = freqAddr.getCity_code();
		int city_codeLength = com.huateng.xhcp.util.Validator.mysql(city_code);
		if(city_codeLength > 10){
			return ResponseInfo.fail("城市编码必须少于10 个字符!");
		}

		String town_code = freqAddr.getTown_code();
		int town_codeLength = com.huateng.xhcp.util.Validator.mysql(town_code);
		if(town_codeLength > 10){
			return ResponseInfo.fail("城区编码必须少于10 个字符!");
		}

		String address = freqAddr.getAddress();
		int addressLength = com.huateng.xhcp.util.Validator.mysql(address);
		if(addressLength > 1000){
			return ResponseInfo.fail("详细地址必须少于1000 个字符!");
		}

		String mobile = freqAddr.getMobile();
		int mobileLength = com.huateng.xhcp.util.Validator.mysql(mobile);
		if(mobileLength > 11){
			return ResponseInfo.fail("手机号码必须少于11 个字符!");
		}

		String user_name = freqAddr.getUser_name();
		int user_nameLength = com.huateng.xhcp.util.Validator.mysql(user_name);
		if(user_nameLength > 50){
			return ResponseInfo.fail("联系人必须少于50 个字符!");
		}

		String default_addr = freqAddr.getDefault_addr();
		int default_addrLength = com.huateng.xhcp.util.Validator.mysql(default_addr);
		if(default_addrLength > 1){
			return ResponseInfo.fail("默认地址必须少于1 个字符!");
		}

		return ResponseInfo.success("校验成功");
	}
	/**
	 * 更新常用地址信息信息
	 * @param freq_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/freqaddr/changeDefault", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> changeDefault(String freq_id,HttpServletRequest request){
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute(SecurityContext.FRONT_ACCOUNT);

		FreqAddr freqAddr = new FreqAddr();
		freqAddr.setFreq_id(freq_id);
		freqAddr.setAccount_id(account.getAccount_id());
		try{
			int c = this.freqAddrService.updateDefaultAddr(freqAddr);
			if(c == 0){
				return HttpUtil.failure("设置默认地址失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("设置默认地址失败!");
		}

		return HttpUtil.success("设置默认地址成功!");
	}
	
	/**
	 * 更新常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/updateFreqAddr", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateFreqAddr(FreqAddr freqAddr){
		try{
			ResponseInfo responseInfo = validate(freqAddr);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.freqAddrService.updateFreqAddr(freqAddr);
			if(c == 0){
				return HttpUtil.failure("更新常用地址信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新常用地址信息失败!");
		}
		
		return HttpUtil.success("更新常用地址信息成功!");
	}

	/**
	 * 删除常用地址信息信息
	 * @param freq_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/freqaddr/deleteById", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteFreqAddr(String freq_id){
		FreqAddr freqAddr = new FreqAddr();
		freqAddr.setFreq_id(freq_id);

		return deleteFreqAddr(freqAddr);
	}

	/**
	 * 删除常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/deleteFreqAddr", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteFreqAddr(FreqAddr freqAddr){
		try{
			int c = this.freqAddrService.deleteFreqAddr(freqAddr);
			if(c == 0){
				return HttpUtil.failure("删除常用地址信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除常用地址信息失败!");
		}
		
		return HttpUtil.success("删除常用地址信息成功!");
	}
	/**
	 * 批量删除常用地址信息信息
	 * @param freqAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/freqaddr/deleteBatchFreqAddr", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchFreqAddr(List<FreqAddr> freqAddr){
		try{
			this.freqAddrService.deleteBatchFreqAddr(freqAddr);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除常用地址信息失败!");
		}
		
		return HttpUtil.success("批量删除常用地址信息成功!");
	}
}
