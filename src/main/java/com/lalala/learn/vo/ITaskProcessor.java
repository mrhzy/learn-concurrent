package com.lalala.learn.vo;

/**
 * @ClassName ITaskProcessor
 * @Description: TODO
 * @Author zhiyou.huang
 * @Date 2020-07-10 10:44
 * @Version V1.0.0
 */
public interface ITaskProcessor<T, R> {


    TaskResult<R> taskExecute(T data);
}
