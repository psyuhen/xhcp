/**
 * 
 */
package com.huateng.xhcp.util;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * 加密算法TEST
 * @author ps
 *
 */
public class SecureUtilTest {

	@Test
	public void testMd5Encode(){
		String md5 = SecureUtil.md5Encode("helloworld");
		
		System.err.println(md5);
		Assert.assertThat(md5, CoreMatchers.is("fc5e038d38a57032085441e7fe7010b0"));
	}
	
	@Test
	public void testShaEncode(){
		String sha = SecureUtil.shaEncode("helloworld");
		String sha1 = SecureUtil.shaEncode("testsaleapp");
		
		System.err.println(sha);
		System.err.println(sha1);
		Assert.assertThat(sha, CoreMatchers.is("6adfb183a4a2c94a2f92dab5ade762a47889a5a1"));
		Assert.assertThat(sha1, CoreMatchers.is("9ea87416f5a91d6ae18705fa194ef450ce790347"));
	}
}
