package com.huateng.xhcp.service.upload;

import com.huateng.xhcp.model.system.FileInfo;

import java.util.List;

/**
 * Created by sam.pan on 2017/3/27.
 */
public interface UploadCallback<T> {

    UploadType callback(List<T> fileInfos);
}
