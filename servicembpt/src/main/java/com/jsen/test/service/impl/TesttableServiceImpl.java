package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.jsen.test.entity.Testtable;
import com.jsen.test.mapper.TesttableMapper;
import com.jsen.test.service.TesttableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@Service
public class TesttableServiceImpl extends ServiceImpl<TesttableMapper, Testtable> implements TesttableService {

    @Override
    public List<Testtable> selectMyAll() {
        RowBounds rb = new RowBounds(0, 2);
        EntityWrapper<Testtable> ew = new EntityWrapper<>();
        return baseMapper.findMy(rb, ew);
    }

    @Override
    public List<Testtable> selectPart() {
        RowBounds rb = new RowBounds(0, 2);
        EntityWrapper<Testtable> ew = new EntityWrapper<>();
        ew.eq("testint", 7);
        return baseMapper.findMy(rb, ew);
    }

    @Override
    public List<Testtable> testSQL1() {
        // 1
        return null;
    }

    @Override
    public List<Testtable> testSQL2() {
        return null;
    }

    @Override
    public List<Testtable> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public Map<String, Object> delete(int id) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("effect", baseMapper.deleteById(id));
        return result;
    }

    @Override
    public List<Testtable> list() {
        EntityWrapper<Testtable> entityWrapper = new EntityWrapper<>();
        return baseMapper.selectList(entityWrapper);
    }
}
