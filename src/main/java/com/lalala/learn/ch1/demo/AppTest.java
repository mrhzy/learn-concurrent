package com.lalala.learn.ch1.demo;

import com.lalala.learn.ch1.PendingJobPool;
import com.lalala.learn.ch1.vo.TaskResult;
import com.lalala.learn.tools.SleepTools;

import java.util.List;
import java.util.Random;

/**
 * @ClassName AppTest
 * @Description: TODO: 模拟一个应用程序，提交工作和任务，并查询任务进度
 * @Author zhiyou.huang
 * @Date 2020-07-10 16:51
 * @Version V1.0.0
 */
public class AppTest {
    private final static String JOB_NAME = "计算数值";
    private final static int JOB_Length = 1000;

    /**
     * 查询任务进度的线程
     */
    private static class QueryResult implements Runnable {

        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            int i = 0;

            while (i < 350) {
                List<TaskResult<String>> taskResultList = pool.getTaskDetail(JOB_NAME);
                if (!taskResultList.isEmpty()) {
                    System.out.println(pool.getTaskProgess(JOB_NAME));
                    System.out.println(taskResultList);
                }

                SleepTools.ms(100);
                i++;
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        PendingJobPool pool = PendingJobPool.getInstance();
        pool.registerJob(JOB_NAME,JOB_Length,myTask,5);

        Random random = new Random();
        for (int i = 0; i < JOB_Length; i++) {
            pool.putTask(JOB_NAME,random.nextInt(1000));
        }

        Thread thread = new Thread(new QueryResult(pool));
        thread.start();
    }

}
