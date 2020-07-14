package com.lalala.learn.ch8b.assist;

import com.lalala.learn.ch8b.vo.SrcDocVo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName CreatePendingDocs
 * @Description: TODO 生成待处理文档
 * @Author zhiyou.huang
 * @Date 2020-07-14 13:30
 * @Version V1.0.0
 */
public class CreatePendingDocs {

    /**
     * 生成一批待处理的文档
     *
     * @param count
     * @return 待处理文档列表
     */
    public static List<SrcDocVo> makePendingDoc(int count) {

        Random r = new Random();
        //文档列表
        List<SrcDocVo> docList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            //文档中题目列表
            List<Integer> questionList = new LinkedList<>();
            int questNum = r.nextInt(60) + Consts.QUESTION_COUNT_IN_DOC;
            for (int j = 0; j < questNum; j++) {
                int questionId = r.nextInt(Consts.SIZE_OF_QUESTION_BANK);
                questionList.add(questionId);
            }

            SrcDocVo pendingDocVo = new SrcDocVo("pengding_" + i,
                    questionList);

            docList.add(pendingDocVo);
        }

        return docList;
    }
}
