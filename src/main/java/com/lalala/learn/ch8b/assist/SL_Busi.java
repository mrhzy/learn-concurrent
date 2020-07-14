package com.lalala.learn.ch8b.assist;

/**
 * @ClassName SL_Busi
 * @Description: TODO 用Sleep模拟实际的业务操作
 * @Author zhiyou.huang
 * @Date 2020-07-14 14:36
 * @Version V1.0.0
 */
public class SL_Busi {

    public static void buisness(int sleepTime){

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
