/**
 * 
 */
package com.huateng.xhcp.service.system;

import com.huateng.xhcp.model.nav.NavInfo;
import com.huateng.xhcp.model.system.FileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pansen
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class FileInfoServiceTest {

	private @Autowired FileInfoService fileInfoService;
	
	@Test
	public void testAddBatch() {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		for(int i=0;i< 10;i++){
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFile_path("//"+i);
			fileInfo.setFile_type(""+i);
			fileInfo.setFile_suffix("0");
			fileInfos.add(fileInfo);
		}

		final int ints = this.fileInfoService.addBatchFileInfo(fileInfos);

		System.out.println(fileInfos);
	}

}
