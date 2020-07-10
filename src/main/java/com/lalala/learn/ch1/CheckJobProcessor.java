package com.lalala.learn.ch1;

import com.lalala.learn.ch1.vo.ItemVo;
import com.lalala.learn.ch1.vo.JobInfo;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * @ClassName CheckJobProcessor
 * @Description: TODO 任务完成后，在一定的时间供查询结果,
 * <p>之后为释放资源节约内存，需要定期处理过期的任务</p>
 * @Author zhiyou.huang
 * @Date 2020-07-10 10:59
 * @Version V1.0.0
 */
public class CheckJobProcessor {
    /*存放任务的队列*/
    private static DelayQueue<ItemVo<String>> queue
            = new DelayQueue<ItemVo<String>>();

    /*单例化*/
    private static class ProcessorHolder {
        public static CheckJobProcessor processor = new CheckJobProcessor();
    }


    public static CheckJobProcessor getInstance() {
        return ProcessorHolder.processor;
    }

    private CheckJobProcessor() {
    }

    ///*处理队列中到期任务*/
    private static class FetchJob implements Runnable {
        private static DelayQueue<ItemVo<String>> queue
                = CheckJobProcessor.queue;

        //缓存的工作信息
        private static Map<String, JobInfo<?>> jobInfoMap
                = PendingJobPool.getMap();

        @Override
        public void run() {
            while (true) {
                try {
                    ItemVo<String> item = queue.take();
                    String jobName = item.getData();
                    jobInfoMap.remove(jobName);

                    System.out.println(jobName + "过期了，从缓存中清除");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 任务完成后，放入队列，经过expireTime时间后，会从整个框架中移除
     */
    public void putJob(String jobName, long expireTime) {

        ItemVo<String> item = new ItemVo<String>(expireTime, jobName);

        queue.offer(item);
        System.out.println(jobName + "已经放入过期检查缓存，时长：" + expireTime);
    }

    static {
        Thread thread = new Thread(new FetchJob());
        //开启守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("开启过期检查的守护线程");
    }
}
