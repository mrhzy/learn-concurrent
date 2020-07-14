package com.lalala.learn.ch8b.vo;

/**
 * @ClassName QuestionInDBVo
 * @Description: TODO 题目实体类
 * @Author zhiyou.huang
 * @Date 2020-07-14 14:39
 * @Version V1.0.0
 */
public class QuestionInDBVo {
    //题目ID
    private final int id;

    // 题目详情，平均长度700字节
    private final String detail;

    private final String sha;

    public QuestionInDBVo(int id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }


    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }
}
