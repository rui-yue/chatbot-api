package com.jr.ai.api.domain.ai.service;

import com.alibaba.fastjson.JSON;
import com.jr.ai.api.domain.ai.IOpenAI;
import com.jr.ai.api.domain.ai.common.Constants;
import com.jr.ai.api.domain.ai.model.aggregates.AIAnswer;
import com.jr.ai.api.domain.ai.model.chat.ChatCompletionRequest;
import com.jr.ai.api.domain.ai.model.chat.Message;
import com.jr.ai.api.domain.ai.model.vo.Choices;
import com.jr.ai.api.domain.ai.session.OpenAiSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * author: JiangJiang
 * description ChatGPT OpenAI实现
 * date: 2024/4/7 14:48
 */
@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot-ai.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGPT(String question) throws IOException {
        // 创建Http连接
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建一个Post请求
        HttpPost post = new HttpPost("https://pro-share-aws-api.zcyai.com/");
        post.addHeader("Content-Type", "application/json");
        //$OPENAI_API_KEY 填写自己的ChatGPT的秘钥
        post.addHeader("Authorization", "Bearer " + openAiKey);
        String paramJson = "{\n" +
                "     \"model\": \"" + ChatCompletionRequest.Model.GPT_4.getCode() + "\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}],\n" +
                "     \"temperature\": 0\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answer = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answer.append(choice.getText());
            }
            return answer.toString();
        } else {
            throw new RuntimeException("Error Code is：" + response.getStatusLine().getStatusCode());
        }
    }
}
//
// private Logger logger = LoggerFactory.getLogger(OpenAI.class);
//
// @Resource
// private OpenAiSession openAiSession;
//
// @Override
// public String doChatGPT(String question) {
//     // 1. 创建参数
//     ChatCompletionRequest chatCompletion = ChatCompletionRequest
//             .builder()
//             .stream(true)
//             .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("java 冒泡排序").build()))
//             .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
//             .maxTokens(1024)
//             .build();
//
//     log.info("测试开始：请等待调用结果");
//
//     // 2. 请求应答
//     CompletableFuture<String> future = openAiSession.chatCompletions(chatCompletion);
//
//     log.info("测试结果：{}", future.get());
//     return null;
// }