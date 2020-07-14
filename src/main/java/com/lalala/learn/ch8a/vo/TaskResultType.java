package com.lalala.learn.ch8a.vo;

/**
 * @ClassName TaskResultType
 * @Description: TODO 方法本身运行是否正确的结果类型
 * @Author zhiyou.huang
 * @Date 2020-07-10 10:45
 * @Version V1.0.0
 */
public enum TaskResultType {
    /*方法执行完成，业务结果也正确*/
    Success,

    /*方法执行完成，业务结果错误*/
    Failure,

    /*方法执行抛出了异常*/
    Exception
}
