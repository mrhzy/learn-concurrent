package com.lalala.learn.ch8b.service.question;

import com.lalala.learn.ch8b.assist.SL_Busi;

import java.util.Random;

/**
 * @ClassName QstService
 * @Description: TODO 单个题目处理的服务类
 * @Author zhiyou.huang
 * @Date 2020-07-14 15:30
 * @Version V1.0.0
 */
public class QstService {

    /**
     * 对题目进行处理，如解析文本，下载图片等等工作
     *
     * @param questionId  题目ID
     * @param questionSrc 题目内容
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId, String questionSrc) {

        Random r = new Random();
        SL_Busi.buisness(450 + r.nextInt(100));
        return "CompleteQuestion[id=" + questionId
                + " content=:" + questionSrc + "]";
    }
}
