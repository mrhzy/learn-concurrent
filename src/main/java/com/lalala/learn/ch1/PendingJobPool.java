package com.lalala.learn.ch1;

import com.lalala.learn.ch1.vo.ITaskProcessor;
import com.lalala.learn.ch1.vo.JobInfo;
import com.lalala.learn.ch1.vo.TaskResult;
import com.lalala.learn.ch1.vo.TaskResultType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName PendingJobPool
 * @Description: TODO 框架的主体类，也是调用者主要使用的类
 * @Author zhiyou.huang
 * @Date 2020-07-10 11:31
 * @Version V1.0.0
 */
public class PendingJobPool {
    /*框架运行时的线程数，与机器的CPU数相同*/
    private static final int THREAD_COUNTS
            = Runtime.getRuntime().availableProcessors();

    /*队列，线程池使用，用以存放待处理的任务*/
    private static BlockingQueue<Runnable> taskQueue
            = new ArrayBlockingQueue<Runnable>(5000);

    /*线程池，固定大小，有界队列*/
    private static ExecutorService taskExecutor
            = new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS,
            60, TimeUnit.SECONDS, taskQueue);

    /*工作信息的存放容器*/
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap
            = new ConcurrentHashMap<String, JobInfo<?>>();

    public static Map<String, JobInfo<?>> getMap() {

        return jobInfoMap;
    }

    /**
     * 以单例模式启动
     */
    private PendingJobPool() {
    }

    private static class JobPoolHolder {
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance() {
        return JobPoolHolder.pool;
    }

    private static class PendingTask<T, R> implements Runnable {
        private JobInfo<R> jobInfo;

        private T processData;

        public PendingTask(JobInfo<R> jobInfo, T processData) {
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run() {
            R r = null;
            ITaskProcessor<T, R> taskProcessor
                    = (ITaskProcessor<T, R>) jobInfo.getTaskProcessor();

            TaskResult<R> result = null;
            try {
                result = taskProcessor.taskExecute(processData);

                if (result == null) {
                    result = new TaskResult<R>(TaskResultType.Exception, r,
                            "result is null");
                }
                if (result.getResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, r,
                                "result is null");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, r,
                                "result is null, reason:" + result.getReason());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception, r
                        , e.getMessage());

            } finally {
                jobInfo.addTaskResult(result);
            }
        }
    }

    //调用者提交工作中的任务
    public <T, R> void putTask(String jobName, T t) {
        JobInfo<R> jobInfo = getJob(jobName);
        PendingTask<T, R> task = new PendingTask<T, R>(jobInfo, t);
        taskExecutor.execute(task);
    }

    public <R> void registerJob(String jobName, int jobLength,
                                ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        JobInfo<R> jobInfo = new JobInfo<R>(jobName, jobLength, taskProcessor, expireTime);

        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName+"已经注册！");
        }
    }

    /*根据工作名称检索工作*/
    private <R> JobInfo<R> getJob(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);

        if (null == jobInfo) {
            throw new RuntimeException(jobName + "是非法任务！");
        }

        return jobInfo;
    }

    /*获得工作的整体处理进度*/
    public <R> String getTaskProgess(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

    /*获取每个任务的处理详情*/
    public <R> List<TaskResult<R>> getTaskDetail(String jobName) {

        JobInfo<R> job = getJob(jobName);

        return job.getTaskDetail();
    }
}
