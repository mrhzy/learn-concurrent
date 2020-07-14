package com.lalala.learn.ch8b.service.question;

import com.lalala.learn.ch8b.assist.SL_QuestionBank;

/**
 * @ClassName SingleQstService
 * @Description: TODO 模拟解析题目文本，下载图片等操作，返回解析后的文本
 * @Author zhiyou.huang
 * @Date 2020-07-14 15:35
 * @Version V1.0.0
 */
public class SingleQstService {

    /**
     * 普通对题目进行处理
     *
     * @param questionId 题目ID
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId) {

        return QstService.makeQuestion(questionId,
                SL_QuestionBank.getQuestion(questionId).getDetail());
    }
}
