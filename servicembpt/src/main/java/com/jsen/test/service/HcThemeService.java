package com.jsen.test.service;

import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/11
 */
public interface HcThemeService {

    ResponseBase listAllAsTree(int id);

    ResponseBase listAll(int id);

    ResponseBase addTopic(String name, String comment);
    ResponseBase addSubTopic(String name, String comment, int parent);

    ResponseBase delTopic(Integer parentId);
    ResponseBase delSubTopic(Integer id);
}
