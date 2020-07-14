package com.lalala.learn.ch8a.vo;

/**
 * @ClassName ITaskProcessor
 * @Description: TODO: 要求框架使用者实现的任务接口，因为任务的性质在调用时才知道，
 * <p>所以传入的参数和方法返回值均使用泛型</p>
 * @Author zhiyou.huang
 * @Date 2020-07-10 10:44
 * @Version V1.0.0
 */
public interface ITaskProcessor<T, R> {


    /**
     * 框架任务接口
     *
     * @param data
     * @return
     */
    TaskResult<R> taskExecute(T data);
}
