package com.lalala.learn.ch8b.service;

import com.lalala.learn.ch8b.assist.SL_Busi;
import com.lalala.learn.ch8b.service.question.SingleQstService;
import com.lalala.learn.ch8b.vo.SrcDocVo;

import java.util.Random;

/**
 * @ClassName ProduceDocService
 * @Description: TODO 处理文档的服务
 * @Author zhiyou.huang
 * @Date 2020-07-14 15:30
 * @Version V1.0.0
 */
public class ProduceDocService {

    /**
     * 上传文档到网络
     *
     * @param docFileName 实际文档在本地的存储位置
     * @return 上传后的网络存储地址
     */
    public static String upLoadDoc(String docFileName) {

        Random r = new Random();

        SL_Busi.buisness(9000 + r.nextInt(400));
        return "http://www.xxxx.com/file/upload/" + docFileName;
    }

    /**
     * 将待处理文档处理为本地实际文档
     *
     * @param pendingDocVo 待处理文档
     * @return 实际文档在本地存储位置
     */
    public static String makeDoc(SrcDocVo pendingDocVo) {
        System.out.println("开始处理文档：" + pendingDocVo.getDocName());

        StringBuffer sb = new StringBuffer();

        for (Integer questionId : pendingDocVo.getQuestionList()) {
            sb.append(SingleQstService.makeQuestion(questionId));
        }

        return "complete_" + System.currentTimeMillis() + "_"
                + pendingDocVo.getDocName() + ".pdf";
    }
}
