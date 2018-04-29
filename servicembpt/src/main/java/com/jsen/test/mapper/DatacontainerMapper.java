package com.jsen.test.mapper;

import com.jsen.test.entity.Datacontainer;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
public interface DatacontainerMapper extends BaseMapper<Datacontainer> {

    int insertBatch(List<Datacontainer> datacontainerList);
    int insertOne(String data, String tip);


    int updateDataById(int id, String data);

    List<Datacontainer> listAll();

    int deleteById(int id);
}
