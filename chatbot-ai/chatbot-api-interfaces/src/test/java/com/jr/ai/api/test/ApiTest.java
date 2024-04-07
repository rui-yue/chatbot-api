package com.jr.ai.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * author: JiangJiang
 * description 单元测试
 * date: 2024/4/4 19:57
 */
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51111145144484/topics?scope=unanswered_questions&count=20");

        get.addHeader("Cookie", "zsxq_access_token=1D3E7B1C-B96D-984B-E9E5-9A855A0944D2_69BC5D53F5E313C8; zsxqsessionid=86ef2cb97cdf27ac846f4e3711d5c8c6; abtest_env=beta");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4844811428485588/answer");

        post.addHeader("Cookie", "zsxq_access_token=1D3E7B1C-B96D-984B-E9E5-9A855A0944D2_69BC5D53F5E313C8; zsxqsessionid=86ef2cb97cdf27ac846f4e3711d5c8c6; abtest_env=beta");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"好的\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 调用ChatGPT APi
     */
    @Test
    public void test_chatGPT() throws IOException {
        // 创建Http连接
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建一个Post请求
        HttpPost post = new HttpPost("https://pro-share-aws-api.zcyai.com/");
        post.addHeader("Content-Type", "application/json");
        //$OPENAI_API_KEY 填写自己的ChatGPT的秘钥
        post.addHeader("Authorization", "Bearer sk-cipdkiDJP8ugP8L848A16c1dF3C4407fBf289a57EbFd092e");
        String paramJson = "{\n" +
                "     \"model\": \"gpt-4.0\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"帮我写一个Java冒泡排序\"}],\n" +
                "     \"temperature\": 0\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient. execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println("Error Code is：" + response.getStatusLine().getStatusCode());
        }
    }

}
