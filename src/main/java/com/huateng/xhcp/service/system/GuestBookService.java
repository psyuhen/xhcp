/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.GuestBook;

/**
 * 访客留言簿信息服务类
 * @author sam.pan
 *
 */
public interface GuestBookService {
	/**
	 * 查询访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	List<GuestBook> queryGuestBook(GuestBook guestBook);
	/**
	 * 查询访客留言簿信息信息
	 * @param guestBook
	 * @return
	 */
	List<GuestBook> queryBy(GuestBook guestBook);
	/**
	 * 根据Key查询访客留言簿信息信息
	 * @param msg_id
	 * @return
	 */
	GuestBook queryByKey(String msg_id);
	/**
	 * 新增访客留言簿信息信息
	 * @param guestBook
	 */
	int addGuestBook(GuestBook guestBook);
	/**
	 * 批量新增访客留言簿信息信息
	 * @param guestBook
	 */
	int addBatchGuestBook(List<GuestBook> guestBook);
	/**
	 * 更新访客留言簿信息信息
	 * @param guestBook
	 */
	int updateGuestBook(GuestBook guestBook);
	/**
	 * 根据msg_id删除访客留言簿信息信息
	 * @param guestBook
	 */
	int deleteGuestBook(GuestBook guestBook);
	/**
	 * 根据msg_id批量删除访客留言簿信息信息
	 * @param guestBook
	 */
	int deleteBatchGuestBook(List<GuestBook> guestBook);
}
