package com.jr.ai.api.domain.zsxq;

import com.jr.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * author: JiangJiang
 * description 知识星球API接口
 * date: 2024/4/6 11:05
 */
public interface IZsxqApi {

    /**
     * 查询未回答信息
     * @param groupId 问题分类
     * @param Cookie 浏览器cookie信息
     * @return 查询到的问回答问题信息
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId , String Cookie) throws IOException;

    /**
     * 回答问题接口
     * @param groupId 问题分类
     * @param cookie 浏览器cookie信息
     * @param topicId 问题ID
     * @param text 回答内容
     * @param silenced 知否只对全部人可见
     * @return 回答结果 true | false
     * @throws IOException
     */
    boolean answer(String groupId , String cookie , String topicId , String text , boolean silenced) throws IOException;
}
