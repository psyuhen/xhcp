package com.huateng.xhcp.web.page;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;
import com.github.pagehelper.SqlUtil;

/**
 * MyBatis - 通用分页
 * 
 * @author shijunkai
 *
 */
@SuppressWarnings({ "rawtypes" })
public class PageHelper {

	/**
	 * 开始分页
	 * 
	 * @param start 分页起始位置
	 * @param limit 分页大小
	 * @return
	 */
	public static Page startPage(int start, int limit) {
		return startPage(start, limit, true);
	}

	/**
	 * 开始分页
	 * 
	 * @param start 分页起始位置
	 * @param limit 分页大小
	 * @param count 是否进行count查询
	 * @return
	 */
	public static Page startPage(int start, int limit, boolean count) {
		RowBounds rowBounds = new RowBounds(start < 1 ? 0 : start - 1, limit);
		Page page = new Page(rowBounds, count);
		page.setPageNum(start / limit + 1);
		SqlUtil.setLocalPage(page);
		return page;
	}
}
