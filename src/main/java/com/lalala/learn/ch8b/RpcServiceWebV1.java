package com.lalala.learn.ch8b;

import com.lalala.learn.ch8b.assist.Consts;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName RpcServiceWebV1
 * @Description: TODO 服务化
 * @Author zhiyou.huang
 * @Date 2020-07-14 13:21
 * @Version V1.0.0
 */
public class RpcServiceWebV1 {

    /*IO密集*/
    /*处理文档生成的线程池*/
    private static ExecutorService docMarkService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT * 2);

    /*处理文档上传的线程池*/
    private static ExecutorService docUploadService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT * 2);


    private static CompletionService<String> docCompletingService
            = new ExecutorCompletionService<String>(docMarkService);

    private static CompletionService<String> docUploadCompletingService
            = new ExecutorCompletionService<String>(docUploadService);

    public static void main(String[] args) {

    }
}
