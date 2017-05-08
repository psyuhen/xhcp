/**
 *
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import com.huateng.xhcp.mapper.product.MerchGalleryMapper;
import com.huateng.xhcp.model.product.MerchGallery;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.MerchInfoMapper;
import com.huateng.xhcp.model.product.MerchInfo;
import com.huateng.xhcp.service.product.MerchInfoService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 产品服务实现类
 *
 * @author sam.pan
 */
@Service
public class MerchInfoServiceImp implements MerchInfoService {

    private @Autowired @Setter @Getter MerchInfoMapper merchInfoMapper;
    private @Autowired @Setter @Getter MerchGalleryMapper merchGalleryMapper;

    /**
     * 查询产品信息
     * @param keywords
     * @return
     */
    public List<MerchInfo> searchKeyWord(String [] keywords){
        return this.merchInfoMapper.searchKeyWord(keywords);
    }
    /**
     * 查询产品信息
     *
     * @param merchInfo
     * @return
     */
    public List<MerchInfo> queryMerchInfo(MerchInfo merchInfo) {
        int start = merchInfo.getStart();
        int limit = merchInfo.getLimit();
        /* 分页  */
        PageHelper.startPage(start, limit);
        return this.merchInfoMapper.queryMerchInfo(merchInfo);
    }

    /**
     * 查询产品信息
     *
     * @param merchInfo
     * @return
     */
    public List<MerchInfo> queryBy(MerchInfo merchInfo) {
        return this.merchInfoMapper.queryMerchInfo(merchInfo);
    }

    /**
     * 查询产品信息
     * @param classify_id 父节点
     * @return
     */
    public List<MerchInfo> queryByPclsId(String classify_id){
        return this.merchInfoMapper.queryByPclsId(classify_id);
    }

    /**
     * 查询销售最多的商品信息
     * @return
     */
    public List<MerchInfo> queryHotMerch(){
        return this.merchInfoMapper.queryHotMerch();
    }
    /**
     * 查询销售最多的商品信息
     * @return
     */
    public List<MerchInfo> queryHotHitsMerch(){
        return this.merchInfoMapper.queryHotHitsMerch();
    }
    /**
     * 根据Key查询产品信息
     *
     * @param merch_id
     * @return
     */
    public MerchInfo queryByKey(String merch_id) {
        MerchInfo merchInfo = new MerchInfo();
        merchInfo.setMerch_id(merch_id);
        List<MerchInfo> list = this.merchInfoMapper.queryMerchInfo(merchInfo);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 新增产品信息
     *
     * @param merchInfo
     */
    public int addMerchInfo(MerchInfo merchInfo, List<MerchGallery> fileInfos) {
        int c = this.merchInfoMapper.addMerchInfo(merchInfo);
        if(c == 0){
            return 0;
        }

        //如果没有，不需要保存
        if(fileInfos == null || fileInfos.isEmpty()){
            return c;
        }

        //前端有提交图片的
        String merchId = merchInfo.getMerch_id();
        for(MerchGallery mg : fileInfos){
            mg.setMerch_id(merchId);
        }

        this.merchGalleryMapper.addBatchMerchGallery(fileInfos);

        return c;
    }

    /**
     * 批量新增产品信息
     *
     * @param merchInfo
     */
    public int addBatchMerchInfo(List<MerchInfo> merchInfo) {
        if (merchInfo == null || merchInfo.isEmpty()) {
            return 0;
        }
        return this.merchInfoMapper.addBatchMerchInfo(merchInfo);
    }

    /**
     * 更新产品信息
     *
     * @param merchInfo
     */
    public int updateMerchInfo(MerchInfo merchInfo, List<MerchGallery> fileInfos) {
        if(fileInfos != null && !fileInfos.isEmpty()){
            this.merchGalleryMapper.addBatchMerchGallery(fileInfos);
        }

        return this.merchInfoMapper.updateMerchInfo(merchInfo);
    }

    /**
     * 更新点击数
     * @param merch_id
     */
    public int updateHits(String merch_id){
        return this.merchInfoMapper.updateHits(merch_id);
    }
    /**
     * 根据merch_id删除产品信息
     *
     * @param merchInfo
     */
    public int deleteMerchInfo(MerchInfo merchInfo) {
        return this.merchInfoMapper.deleteMerchInfo(merchInfo);
    }

    /**
     * 根据merch_id批量删除产品信息
     *
     * @param merchInfo
     */
    public int deleteBatchMerchInfo(List<MerchInfo> merchInfo) {
        if (merchInfo == null || merchInfo.isEmpty()) {
            return 0;
        }
        return this.merchInfoMapper.deleteBatchMerchInfo(merchInfo);
    }
}
