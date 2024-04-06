package com.jr.ai.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.jr.ai.api.domain.zsxq.IZsxqApi;
import com.jr.ai.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.jr.ai.api.domain.zsxq.model.req.AnswerReq;
import com.jr.ai.api.domain.zsxq.model.req.ReqData;
import com.jr.ai.api.domain.zsxq.model.res.AnswerRes;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * author: JiangJiang
 * description 知识星球API实现类
 * date: 2024/4/6 13:16
 */
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("Cookie", cookie);
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取问题数据。groupId：{} jsonStr: {}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err code is " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpPost = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");

        post.addHeader("Cookie", cookie);
        post.addHeader("Content-Type", "application/json; charset=UTF-8");
        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

        // 测试数据
        // String paramJson = "{\n" +
        //         "  \"req_data\": {\n" +
        //         "    \"text\": \"好的\\n\",\n" +
        //         "    \"image_ids\": [],\n" +
        //         "    \"silenced\": true\n" +
        //         "  }\n" +
        //         "}";

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));

        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpPost.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} jsonStr: {}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("Answer Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
