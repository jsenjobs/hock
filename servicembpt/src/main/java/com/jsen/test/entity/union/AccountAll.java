package com.jsen.test.entity.union;

import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jsen.test.entity.Account;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountAll extends Model<AccountAll> {

    private static final long serialVersionUID = 1L;

    private Account account;
    private List<WeiboAll> weiboAllList;


    @Override
    protected Serializable pkVal() {
        return this.account.getId();
    }
}
