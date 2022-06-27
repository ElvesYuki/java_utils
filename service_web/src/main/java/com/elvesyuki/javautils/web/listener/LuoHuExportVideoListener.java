package com.elvesyuki.javautils.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.elvesyuki.javautils.web.object.LuoHuStudentExport;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 22:12
 * @description：
 * @modified By：
 */
public class LuoHuExportVideoListener implements ReadListener<LuoHuStudentExport> {

    private static final Logger logger = LoggerFactory.getLogger(LuoHuExportVideoListener.class);

    public List<LuoHuStudentExport> luoHuStudentExportList = new ArrayList<>();

    public Map<String, File> questionPdf = new HashMap<>();

    public String basePath = "E:\\excel\\luohu\\罗湖区_LH_罗湖外国语学校_010";

    @Override
    public void invoke(LuoHuStudentExport data, AnalysisContext context) {
        luoHuStudentExportList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        initQuestionPdfMap();



        luoHuStudentExportList.forEach(luoHuStudentExport -> {

            String key = luoHuStudentExport.getExamSubjectCode() + "_" + luoHuStudentExport.getExamQuestionCode();

            if (questionPdf.containsKey(key)) {
                File destFile = new File(basePath + "\\" + luoHuStudentExport.getExamineeId() + "_" + key + ".pdf");
                try {
                    FileUtils.copyFile(questionPdf.get(key), destFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                logger.error("出现错误==>{}",key);
            }

        });



    }

    public void initQuestionPdfMap() {

        questionPdf.put("01_A", new File("E:\\excel\\物理_A组：用弹簧测力计测量力的大小-现考现评.pdf"));
        questionPdf.put("01_B", new File("E:\\excel\\物理_B组：测量液体的密度-现考现评.pdf"));
        questionPdf.put("01_C", new File("E:\\excel\\物理_C组：探究什么情况下磁可以生电-现考现评.pdf"));
        questionPdf.put("02_A", new File("E:\\excel\\化学_A组：配制100g 3%的NaCl溶液-现考现评.pdf"));
        questionPdf.put("02_B", new File("E:\\excel\\化学_B组：二氧化碳的实验室制取与性质-现考现评.pdf"));
        questionPdf.put("02_C", new File("E:\\excel\\化学_C组：谁是碳酸钠-现考现评.pdf"));
        questionPdf.put("03_A", new File("E:\\excel\\生物_A组：观察青霉永久装片-现考现评.pdf"));
        questionPdf.put("03_B", new File("E:\\excel\\生物_B组：制作叶片下表皮临时装片-现考现评.pdf"));
        questionPdf.put("03_C", new File("E:\\excel\\生物_C组：探究花生果实大小的变异-现考现评.pdf"));


    }

}
