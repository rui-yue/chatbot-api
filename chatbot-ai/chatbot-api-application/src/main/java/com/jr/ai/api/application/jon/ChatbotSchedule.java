package com.jr.ai.api.application.jon;

import com.alibaba.fastjson.JSON;
import com.jr.ai.api.domain.ai.IOpenAI;
import com.jr.ai.api.domain.zsxq.IZsxqApi;
import com.jr.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.jr.ai.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


/**
 * author: JiangJiang
 * description chat机器人
 * date: 2024/4/7 16:48
 */
@EnableScheduling
@Configurable
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class)；

    @Value("${chatbot-ai.groupId}")
    private String groupId;

    @Value("${chatbot-ai.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("随机打样中。。。");
                return;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打样时间不工作，AI下班了");
                return;
            }
            // 1、检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("测试结果：" + JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索为查询到待回答问题");
                return;
            }
            // 2、Ai回答问题
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());
            // 3、问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (IOException e) {
            logger.info("自动回答问题异常", e);
        }
    }

}
