package com.lalala.learn.vo;

import com.lalala.learn.CheckJobProcessor;
import jdk.nashorn.internal.objects.annotations.Where;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName JobInfo
 * @Description: TODO 提交给框架执行的工作实体类
 * <p> 工作：表示本批次需要处理的同性质任务(Task)的一个集合 <p/>
 * @Author zhiyou.huang
 * @Date 2020-07-10 9:45
 * @Version V1.0.0
 */
public class JobInfo<R> {

    /*工作名，用以区分框架中唯一的工作*/
    private final String jobName;

    /*工作中任务的长度*/
    private final int jobLength;

    /*处理工作中任务的处理器*/
    private final ITaskProcessor<?, ?> taskProcessor;

    /*任务的成功次数*/
    private AtomicInteger successCount;
    /*工作中任务目前已经处理的次数*/
    private AtomicInteger taskProcessCount;

    /*存放每个任务的处理结果，供查询用*/
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueues;


    /*保留工作的结果信息，供查询的时长*/
    private final long expireTime;

    private static CheckJobProcessor checkJob = CheckJobProcessor.getInstance();

    public JobInfo(String jobName, int jobLength,
                   ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.successCount = new AtomicInteger(0);
        this.taskProcessCount = new AtomicInteger(0);
        this.taskProcessor = taskProcessor;
        this.taskDetailQueues = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
        this.expireTime = expireTime;
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }

    //提供工作中失败的次数
    public int getFailCount() {
        return taskProcessCount.get() - successCount.get();
    }

    public int getJobLength() {
        return jobLength;
    }

    public ITaskProcessor<?, ?> getTaskProcessor() {
        return taskProcessor;
    }

    /*提供工作的整体进度信息*/
    public String getTotalProcess() {
        return "Success[" + successCount.get() + "]/Current[" + taskProcessCount.get()
                + "] Total[" + jobLength + "]";
    }

    /**
     * 提供工作中每个任务的处理结果
     */
    public List<TaskResult<R>> getTaskDetail() {
        List<TaskResult<R>> taskResultList = new LinkedList<TaskResult<R>>();

        TaskResult<R> taskResult = null;

        while ((taskResult = taskDetailQueues.pollFirst()) != null) {
            taskResultList.add(taskResult);
        }

        return taskResultList;
    }

    public void addTaskResult(TaskResult<R> taskResult) {
        if (TaskResultType.Success.equals(taskResult.getResultType())) {
            successCount.incrementAndGet();
        }

        taskProcessCount.incrementAndGet();
        taskDetailQueues.addLast(taskResult);

        if (taskProcessCount.get() == jobLength) {
            checkJob.putJob(jobName, expireTime);
        }
    }
}
