package com.jsen.test.service.impl;

import com.google.common.collect.Lists;
import com.jsen.test.entity.Datacontainer;
import com.jsen.test.mapper.DatacontainerMapper;
import com.jsen.test.service.DatacontainerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import lombok.Cleanup;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-29
 */
@Service
public class DatacontainerServiceImpl extends ServiceImpl<DatacontainerMapper, Datacontainer> implements DatacontainerService {

    //从spring注入原有的sqlSessionTemplate
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    @Transactional
    public ResponseBase insertBatch(int num, boolean trans) {
        long pStart = System.currentTimeMillis();
        List<Datacontainer> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            datas.add(new Datacontainer().setData(i + "").setTip(i + ""));
        }
        long start = System.currentTimeMillis();
        int eff = baseMapper.insertBatch(datas);
        long end = System.currentTimeMillis();
        if (trans) {
            throw new RuntimeException("trans from 批量插入");
        }
        return ResponseBase.create().code(0).msg("批量插入").add("eff", eff).add("duration", end - start).add("totalDuration", end - pStart);
    }


    @Override
    public ResponseBase insertBatch2(int num) {
        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        @Cleanup
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        //通过新的session获取mapper
        DatacontainerMapper datacontainerMapper = session.getMapper(DatacontainerMapper.class);
        int eff = 0;
        try{
            long start = System.currentTimeMillis();
            for(int i = 0; i < num; i++) {
                eff += datacontainerMapper.insertOne(i+"", i+"");
                if(i % 1000 == 0 || i == num - 1) {
                    //手动每1000个一提交，提交后无法回滚
                    session.commit();
                    //清理缓存，防止溢出
                    session.clearCache();
                }
            }
            long end = System.currentTimeMillis();
            return ResponseBase.create().code(0).msg("mybatis ExecutorType.BATCH 成功").add("eff", eff).add("duration", end - start);
        } catch (Exception e) {
            //没有提交的数据可以回滚
            session.rollback();
            return ResponseBase.create().code(1).msg("mybatis ExecutorType.BATCH 失败").add("eff", eff);
        }
    }


    @Override
    public ResponseBase insertBatch3(int num) {
        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        @Cleanup
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        String name = this.getClass().getName();
        int eff = 0;
        try{
            long start = System.currentTimeMillis();
            for(int i = 0; i < num; i++) {
                eff += session.insert(name, new Datacontainer().setData(i + "").setTip(i + ""));
                if(i % 1000 == 0 || i == num - 1) {
                    //手动每1000个一提交，提交后无法回滚
                    session.commit();
                    //清理缓存，防止溢出
                    session.clearCache();
                }
            }
            long end = System.currentTimeMillis();
            return ResponseBase.create().code(0).msg("session ExecutorType.BATCH 成功").add("eff", eff).add("duration", end - start);
        } catch (Exception e) {
            //没有提交的数据可以回滚
            session.rollback();
            return ResponseBase.create().code(1).msg("session ExecutorType.BATCH 失败").add("eff", eff);
        }
    }

    @Override
    @Transactional
    public ResponseBase insert(int num, boolean trans) {
        int eff = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            eff += baseMapper.insertOne(i + "", i + "");
        }
        if (trans) {
            throw new RuntimeException("trans from 单条插入");
        }
        long end = System.currentTimeMillis();
        return ResponseBase.create().code(0).msg("单条插入").add("eff", eff).add("duration", end - start);
    }

    @Override
    @Transactional(timeout = 1, propagation = Propagation.MANDATORY)
    public ResponseBase transTimeout(int num, long sleep) {
        long pStart = System.currentTimeMillis();
        List<Datacontainer> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            datas.add(new Datacontainer().setData(i + "").setTip(i + ""));
        }
        long start = System.currentTimeMillis();
        int eff = baseMapper.insertBatch(datas);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eff += baseMapper.insertBatch(datas);
        long end = System.currentTimeMillis();
        return ResponseBase.create().code(0).msg("批量插入").add("eff", eff).add("duration", end - start).add("totalDuration", end - pStart);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBase transReadOnly(int num) {
        // 调用不会插入数据
        long pStart = System.currentTimeMillis();
        List<Datacontainer> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            datas.add(new Datacontainer().setData(i + "").setTip(i + ""));
        }
        long start = System.currentTimeMillis();
        int eff = baseMapper.insertBatch(datas);
        long end = System.currentTimeMillis();
        return ResponseBase.create().code(0).msg("批量插入").add("eff", eff).add("duration", end - start).add("totalDuration", end - pStart);
    }

    @Override
    @Transactional(noRollbackFor = {RuntimeException.class})
    public ResponseBase transNoRollBackException(int num) {
        // 调用会插入数据
        long pStart = System.currentTimeMillis();
        List<Datacontainer> datas = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            datas.add(new Datacontainer().setData(i + "").setTip(i + ""));
        }
        long start = System.currentTimeMillis();
        int eff = baseMapper.insertBatch(datas);
        long end = System.currentTimeMillis();
        if (true) {
            throw new RuntimeException("trans from 批量插入");
        }
        return ResponseBase.create().code(0).msg("批量插入").add("eff", eff).add("duration", end - start).add("totalDuration", end - pStart);
    }

    @Override
    public ResponseBase wrInserts(int num) {
        List<Datacontainer> list = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            list.add(new Datacontainer().setData("data" + i).setTip("tip" + i));
        }
        int eff =  baseMapper.insertBatch(list);
        return ResponseBase.create().code(0).msg("批量插入-wr").add("eff", eff);
    }

    @Override
    public ResponseBase wrUpdate(int id, String data) {
        int eff =  baseMapper.updateDataById(id, data);
        return ResponseBase.create().code(0).msg("更新-wr").add("eff", eff);
    }

    @Override
    public ResponseBase wrDelete(int id) {
        int eff =  baseMapper.deleteById(id);
        return ResponseBase.create().code(0).msg("删除-wr").add("eff", eff);
    }

    @Override
    public ResponseBase wrList() {
        return ResponseBase.create().code(0).msg("list-wr").add("data", baseMapper.listAll());
    }
}
