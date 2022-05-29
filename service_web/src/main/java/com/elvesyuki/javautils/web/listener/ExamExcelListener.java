package com.elvesyuki.javautils.web.listener;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elvesyuki.javautils.web.object.ExamData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianyu
 * @date 2022/05/29 18:13
 */
public class ExamExcelListener extends AnalysisEventListener<ExamData> {

    private static int COUNT = 1;

    List<ExamData> list = new ArrayList<>();

    @Override
    public void invoke(ExamData examData, AnalysisContext analysisContext) {
        //根据项目名去判断生成
        String newFileName = examData.getExamCode() + "_" + examData.getExamSubjectCode() + "_" + examData.getExamTitleCode();
        //第一个需要被拷贝的文件，第二个是拷贝到哪里
        try {
            FileUtils.copyFile(new File("D:\\首款具备.pdf"), new File("D:\\shengchengheji\\"+newFileName+ ".pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
