package com.jsen.test.mapper;

import com.jsen.test.entity.HcModel;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-04-16
 */
@Service
public interface HcModelMapper extends BaseMapper<HcModel> {

    int saveModel(HcModel hcModel);

    int updateModel(HcModel hcModel);

    int clearAllDataByUserId(Integer uId);

    List<HcModel> findModelByUserId(int userId);

    HcModel findModelByModelIdAndCreatorId(Integer id, Integer creator);

    HcModel findModelByName(String modelName);

    int countByName(String name);

    HcModel findModelDataById(Integer id);

    int updateModelNameByName(String oldName, String newName);

}
