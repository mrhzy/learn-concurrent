package com.lalala.learn.ch8b.assist;

/**
 * @ClassName Consts
 * @Description: TODO 系统常量类
 * @Author zhiyou.huang
 * @Date 2020-07-14 13:26
 * @Version V1.0.0
 */
public class Consts {

    // 取得本地机器cpu数量
    public final static int THREAD_COUNT
            = Runtime.getRuntime().availableProcessors();

    // 每个文档中题目的个数
    public final static int QUESTION_COUNT_IN_DOC  = 60;

    // 题库大小
    public final static int SIZE_OF_QUESTION_BANK  = 2000;
}
