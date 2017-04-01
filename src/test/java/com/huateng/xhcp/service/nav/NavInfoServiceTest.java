/**
 * 
 */
package com.huateng.xhcp.service.nav;

import com.huateng.xhcp.model.nav.NavInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author pansen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class NavInfoServiceTest {

//	private @Autowired NavInfoService navInfoService;
	
	@Test
	public void testQueryBy() {
		NavInfo navInfo = new NavInfo();
		navInfo.setPnav_id("-1");
		navInfo.setIs_out_link("0");
//		final List<NavInfo> navInfos = navInfoService.queryBy(navInfo);
//
//		System.err.println(navInfos);
//		System.err.println(navInfos.size());
	}

	@Test
	public void testAddAccount(){
		String[][]navs = {
				{"-1","brand.html","品牌","0","0","1"},
				{"-1","product.html","产品","0","0","2"},
				{"-1","public.html","顾客口碑","0","0","3"},
				{"-1","merchants.html","招商代理","0","0","4"},
				{"-1","xhcp.html","新会陈皮","0","0","5"}
		};

		String[][]first = {
				{"brand.html","公司概况","0","0","1"},
				{"product.html","企业新闻","0","0","2"},
				{"public.html","视频中心","0","0","3"},
		};
		String[][]second = {
				{"brand.html","碗燕系列","0","0","1"},
				{"product.html","干燕窝系列","0","0","2"},
				{"public.html","其它","0","0","3"},
		};
		String[][]third = {
				{"brand.html","代理优势","0","0","1"},
				{"product.html","门店展示","0","0","2"},
				{"public.html","代理商咨询","0","0","3"},
		};



		for (int i=0,len = navs.length;i<len;i++){
			NavInfo navInfo = new NavInfo();
			navInfo.setPnav_id(navs[i][0]);
			navInfo.setUrl(navs[i][1]);
			navInfo.setName(navs[i][2]);
			navInfo.setIs_out_link(navs[i][3]);
			navInfo.setIs_default(navs[i][4]);
			navInfo.setOrder_id(navs[i][5]);
//			String nav_id = navInfoService.addNavInfo(navInfo);
			/*System.out.println(nav_id);

			if(i==0){
				addSubNav(first, nav_id);
			}else if(i == 1){
				addSubNav(second, nav_id);
			}else if(i == 3){
				addSubNav(third, nav_id);
			}*/
		}
	}

	private void addSubNav(String[][] first,String nav_id){
		for (int j = 0,l = first.length; j < l; j++) {
			NavInfo n = new NavInfo();
			n.setPnav_id(nav_id);
			n.setUrl(first[j][0]);
			n.setName(first[j][1]);
			n.setIs_out_link(first[j][2]);
			n.setIs_default(first[j][3]);
			n.setOrder_id(first[j][4]);
//			String nav_id1 = navInfoService.addNavInfo(n);
//			System.out.println(nav_id1);
		}
	}

	@Test
	public void testAddNav(){
		String[][]first = {
				{"brand.html","公司概况","0","0","1"},
				{"product.html","企业新闻","0","0","2"},
				{"public.html","视频中心","0","0","3"},
		};
		addSubNav(first, "1");
	}

	@Test
	public void testDeleteByPNavId(){
//		this.navInfoService.deleteByPNavId("1");
	}
}
