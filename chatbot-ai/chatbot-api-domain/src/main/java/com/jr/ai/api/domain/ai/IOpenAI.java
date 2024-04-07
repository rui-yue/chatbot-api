package com.jr.ai.api.domain.ai;

import java.io.IOException;

/**
 * author: JiangJiang
 * description ChatGPT OpenAI接口
 * date: 2024/4/7 14:46
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;

    // String doChatGPT(String question);

}
