package com.jsen.test.mapper;

import com.jsen.test.entity.HcModelGreat;
import com.jsen.test.entity.HcModelShare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Service
public interface HcModelGreatMapper {
    int saveGreat(HcModelGreat hcModelGreat);
    int deleteGreat(HcModelGreat hcModelGreat);
    int deleteGreatByMId(Integer mId);

    HcModelGreat findGreat(HcModelGreat hcModelGreat);
    List<HcModelGreat> findGreatByUid(Integer uId);
}
