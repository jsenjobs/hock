package com.jsen.test.entity.union;

import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jsen.test.entity.Account;
import com.jsen.test.entity.Link;
import com.jsen.test.entity.Weibo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkJoinAll extends Model<LinkJoinAll> {
    private static final long serialVersionUID = 1L;


    private Link link;
    private Weibo weibo;
    private Account account;

    @Override
    protected Serializable pkVal() {
        return link.getId();
    }
}
