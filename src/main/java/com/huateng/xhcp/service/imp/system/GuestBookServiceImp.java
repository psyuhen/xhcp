/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.GuestBookMapper;
import com.huateng.xhcp.model.system.GuestBook;
import com.huateng.xhcp.service.system.GuestBookService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 访客留言簿信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class GuestBookServiceImp implements GuestBookService {

	private @Autowired @Setter @Getter GuestBookMapper guestBookMapper;
	/**
	 * 查询访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	public List<GuestBook> queryGuestBook(GuestBook guestBook){
		int start = guestBook.getStart();
		int limit = guestBook.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.guestBookMapper.queryGuestBook(guestBook);
	}
	/**
	 * 查询访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	public List<GuestBook> queryBy(GuestBook guestBook){
		return this.guestBookMapper.queryGuestBook(guestBook);
	}
	/**
	 * 根据Key查询访客留言簿信息信息
	 * @param msg_id
	 * @return
	 */
	public GuestBook queryByKey(String msg_id){
		GuestBook guestBook = new GuestBook();
		guestBook.setMsg_id(msg_id);
		List<GuestBook> list = this.guestBookMapper.queryGuestBook(guestBook);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增访客留言簿信息信息
	 * @param guestBook
	 */
	public int addGuestBook(GuestBook guestBook){
		return this.guestBookMapper.addGuestBook(guestBook);
	}
	/**
	 * 批量新增访客留言簿信息信息
	 * @param guestBook
	 */
	public int addBatchGuestBook(List<GuestBook> guestBook){
		if(guestBook == null || guestBook.isEmpty()){
			return 0;
		}
		return this.guestBookMapper.addBatchGuestBook(guestBook);
	}
	/**
	 * 更新访客留言簿信息信息
	 * @param guestBook
	 */
	public int updateGuestBook(GuestBook guestBook){
		return this.guestBookMapper.updateGuestBook(guestBook);
	}
	/**
	 * 根据msg_id删除访客留言簿信息信息
	 * @param guestBook
	 */
	public int deleteGuestBook(GuestBook guestBook){
		return this.guestBookMapper.deleteGuestBook(guestBook);
	}
	/**
	 * 根据msg_id批量删除访客留言簿信息信息
	 * @param guestBook
	 */
	public int deleteBatchGuestBook(List<GuestBook> guestBook){
		if(guestBook == null || guestBook.isEmpty()){
			return 0;
		}
		return this.guestBookMapper.deleteBatchGuestBook(guestBook);
	}
}
