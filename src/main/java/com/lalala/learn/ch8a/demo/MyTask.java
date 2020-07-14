package com.lalala.learn.ch8a.demo;

import com.lalala.learn.ch8a.vo.ITaskProcessor;
import com.lalala.learn.ch8a.vo.TaskResult;
import com.lalala.learn.ch8a.vo.TaskResultType;

import java.util.Random;

/**
 * @ClassName MyTask
 * @Description: TODO
 * @Author zhiyou.huang
 * @Date 2020-07-10 16:49
 * @Version V1.0.0
 */
public class MyTask implements ITaskProcessor<Integer, Integer> {

    @Override
    public TaskResult<Integer> taskExecute(Integer data) {

        Random random = new Random();

        int flag = random.nextInt(500);
        if (flag <= 300) { //正常情况
            int returnValue = data.intValue() + flag;

            return new TaskResult<Integer>(TaskResultType.Success, returnValue);
        } else if (flag > 300 && flag <= 400) {
            return new TaskResult<Integer>(TaskResultType.Failure, -1, "Failure");
        } else {

            try {
                throw new RuntimeException("异常发生了！！");
            } catch (Exception e) {

                return new TaskResult<Integer>(TaskResultType.Exception, -1, e.getMessage());
            }

        }
    }
}
