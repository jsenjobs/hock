package com.jsen.test.entity.union;

import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jsen.test.entity.Link;
import com.jsen.test.entity.Weibo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeiboAll extends Model<WeiboAll> {

    private static final long serialVersionUID = 1L;

    private Weibo weibo;
    private List<Link> linkList;


    @Override
    protected Serializable pkVal() {
        return this.weibo.getId();
    }
}