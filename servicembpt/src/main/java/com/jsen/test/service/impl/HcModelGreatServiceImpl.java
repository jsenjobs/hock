package com.jsen.test.service.impl;

import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModelGreat;
import com.jsen.test.entity.HcModelShare;
import com.jsen.test.mapper.HcModelGreatMapper;
import com.jsen.test.mapper.HcModelShareMapper;
import com.jsen.test.service.HcModelGreatService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/26
 */
@Service
public class HcModelGreatServiceImpl implements HcModelGreatService {

    HcModelGreatMapper hcModelGreatMapper;
    HcModelShareMapper hcModelShareMapper;
    @Autowired
    public HcModelGreatServiceImpl(HcModelGreatMapper hcModelGreatMapper, HcModelShareMapper hcModelShareMapper) {
        this.hcModelGreatMapper = hcModelGreatMapper;
        this.hcModelShareMapper = hcModelShareMapper;
    }


    @Override
    public ResponseBase great(Integer uId, Integer mId) {
        HcModelGreat query = new HcModelGreat().setUId(uId).setMId(mId);
        HcModelGreat hcModelGreat = hcModelGreatMapper.findGreat(query);
        HcModelShare hcModelShare = hcModelShareMapper.findShareModelById(mId);
        if (hcModelShare == null) {
            return ConstantResponse.FAIL.msg("无法找到共享模型");
        }
        if (hcModelGreat == null) {
            // 模型 点赞加一
            hcModelShareMapper.updateShareModelCollectLookById(mId, hcModelShare.getCollect() + 1, hcModelShare.getLook());
            return ResponseBase.create().code(0).msg("点赞成功").add("eff", hcModelGreatMapper.saveGreat(query));
        } else {
            // 模型 点赞减一
            int collect = hcModelShare.getCollect() - 1;
            if (collect < 0) {
                collect = 0;
            }
            hcModelShareMapper.updateShareModelCollectLookById(mId, collect, hcModelShare.getLook());
            return ResponseBase.create().code(0).msg("取消点赞成功").add("eff", hcModelGreatMapper.deleteGreat(query));
        }
    }
}
