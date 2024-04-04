package com.jr.ai.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/topics/8855814112118812/comments?sort=asc&count=30&with_sticky=true");

        get.addHeader("Cookie" , "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218e9f011236901-0e1d9173f47b548-4c657b58-2359296-18e9f011237259%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlOWYwMTEyMzY5MDEtMGUxZDkxNzNmNDdiNTQ4LTRjNjU3YjU4LTIzNTkyOTYtMThlOWYwMTEyMzcyNTkifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218e9f011236901-0e1d9173f47b548-4c657b58-2359296-18e9f011237259%22%7D; zsxq_access_token=8FF16D0A-5CD2-A6E4-BAF8-675C73A84BED_69BC5D53F5E313C8; zsxqsessionid=c0da1a265236db4d08343484648318aa; abtest_env=beta");
        get.addHeader("Content-Type" , "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer(){
        CloseableHttpClient httpPost = HttpClientBuilder.create().build();
        new HttpPost("");
    }
}
