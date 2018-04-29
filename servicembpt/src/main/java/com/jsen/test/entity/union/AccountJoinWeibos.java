package com.jsen.test.entity.union;

import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jsen.test.entity.Account;
import com.jsen.test.entity.Weibo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountJoinWeibos extends Model<AccountJoinWeibos> {

    private static final long serialVersionUID = 1L;

    private Account account;
    private List<Weibo> weiboList;


    @Override
    protected Serializable pkVal() {
        return this.account.getId();
    }
}
