package com.jr.ai.api.domain.ai.session;

import com.jr.ai.api.domain.ai.model.chat.ChatCompletionRequest;

import java.util.concurrent.CompletableFuture;

/**
 * author: JiangJiang
 * description OpenAI配置类
 * date: 2024/4/7 16:07
 */
public interface OpenAiSession {
    CompletableFuture<String> chatCompletions(ChatCompletionRequest chatCompletion);
}
