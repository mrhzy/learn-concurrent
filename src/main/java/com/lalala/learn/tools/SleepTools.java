package com.lalala.learn.tools;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepTools
 * @Description: TODO 类说明：线程休眠辅助工具类
 * @Author zhiyou.huang
 * @Date 2020-07-10 16:50
 * @Version V1.0.0
 */
public class SleepTools {

    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}