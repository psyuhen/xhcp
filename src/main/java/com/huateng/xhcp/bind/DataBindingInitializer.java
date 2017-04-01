/**
 * 
 */
package com.huateng.xhcp.bind;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * @author sam.pan
 *
 */
@Service
public class DataBindingInitializer implements WebBindingInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.web.bind.support.WebBindingInitializer#initBinder(org.springframework.web.bind.WebDataBinder, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}

}
