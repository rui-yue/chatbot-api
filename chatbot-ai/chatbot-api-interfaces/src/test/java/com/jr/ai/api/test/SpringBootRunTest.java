package com.jr.ai.api.test;

import com.alibaba.fastjson.JSON;
import com.jr.ai.api.domain.ai.IOpenAI;
import com.jr.ai.api.domain.zsxq.IZsxqApi;
import com.jr.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.jr.ai.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * author: JiangJiang
 * description SpringBoot测试类
 * date: 2024/4/6 13:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-ai.groupId}")
    private String groupId;

    @Value("${chatbot-ai.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        // 查询所有的提问问题
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicID：{} text：{}", topicId , text);
            // 回答问题
            zsxqApi.answer(groupId, cookie, topicId , text , false);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("帮我写一个冒泡排序");
        logger.info("测试结果：{}" , response);
    }
}
