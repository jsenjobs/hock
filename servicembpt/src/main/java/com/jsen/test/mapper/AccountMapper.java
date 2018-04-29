package com.jsen.test.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jsen.test.entity.Account;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.Datacontainer;
import com.jsen.test.entity.union.AccountAll;
import com.jsen.test.entity.union.AccountFlat;
import com.jsen.test.entity.union.AccountJoinWeibos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
@Service
public interface AccountMapper extends BaseMapper<Account> {
    List<AccountJoinWeibos> listAccountJoinWeibos();
    List<AccountJoinWeibos> listAccountJoinWeibosUseJoin();
    Account getAccountById(int id);

    List<AccountAll> lisAccountAll();

    List<AccountAll> listAccountAllUseJoin();

    List<AccountFlat> listFlat();

    List<Account> listInView(@Param("name") String name);
    List<Account> listBetween(int id1, int id2);

    List<Account> listLimit(int offset, int page);

    int deleteDistinct();

    List<Account> listRandom();

    int updateId(int id, int newId);

    List<Account> listAll();

    List<Account> findByName(String name);

    int deleteByID(int id);

    int updateNameById(int id, String name);

    List<Account> listAccountList(Pagination pagination);


}
