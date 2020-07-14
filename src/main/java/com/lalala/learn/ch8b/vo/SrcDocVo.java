package com.lalala.learn.ch8b.vo;

import java.util.List;

/**
 * @ClassName SrcDocVo
 * @Description: TODO 待处理文档实体类
 * @Author zhiyou.huang
 * @Date 2020-07-14 13:31
 * @Version V1.0.0
 */
public class SrcDocVo {
    //待处理文档名称
    private final String docName;

    //待处理文档中题目ID列表
    private final List<Integer> questionList;

    public SrcDocVo(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
