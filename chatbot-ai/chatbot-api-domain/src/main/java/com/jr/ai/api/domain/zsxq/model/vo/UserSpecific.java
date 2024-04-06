package com.jr.ai.api.domain.zsxq.model.vo;
/**
 * author: JiangJiang
 * description 用户标签
 * date: 2024/4/6 11:05
 */
public class UserSpecific {

    private boolean liked;

    private boolean subscribed;

    public void setLiked(boolean liked){
        this.liked = liked;
    }
    public boolean getLiked(){
        return this.liked;
    }
    public void setSubscribed(boolean subscribed){
        this.subscribed = subscribed;
    }
    public boolean getSubscribed(){
        return this.subscribed;
    }

}
