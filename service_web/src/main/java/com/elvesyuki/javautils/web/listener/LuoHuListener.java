package com.elvesyuki.javautils.web.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.elvesyuki.javautils.web.object.LuoHuStudent;
import com.elvesyuki.javautils.web.object.LuoHuStudentTwo;
import com.elvesyuki.javautils.web.object.LuoHuStudentExport;
import com.elvesyuki.javautils.web.object.QuestionMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 17：07
 * @description：
 * @modified By：
 */
public class LuoHuListener implements ReadListener<LuoHuStudentTwo> {

    public static final Logger logger = LoggerFactory.getLogger(LuoHuListener.class);

    public List<LuoHuStudent> luoHuStudentList = new ArrayList<>();
    public List<LuoHuStudentTwo> luoHuStudentTwoList = new ArrayList<>();

    public List<LuoHuStudentExport> luoHuStudentExportList = new ArrayList<>();

    public HashMap<String, QuestionMapValue> questionMapValueHashMap = new HashMap<>();
    
    public HashMap<String, QuestionMapValue> questionBiologyMapValueHashMap = new HashMap<>();

    @Override
    public void invoke(LuoHuStudentTwo luoHuStudentTwo, AnalysisContext analysisContext) {

        // logger.info("解析到一条数据==>{}",luoHuStudent);
        if (!ObjectUtils.isEmpty(luoHuStudentTwo) && !ObjectUtils.isEmpty(luoHuStudentTwo.getClassName())
                && !luoHuStudentTwo.getClassName().contains("班级")
                && !luoHuStudentTwo.getClassName().contains("考场")) {
            // logger.info("解析到一条数据==>{}", luoHuStudentTwo);
            luoHuStudentTwoList.add(luoHuStudentTwo);

            LuoHuStudent luoHuStudent1 = new LuoHuStudent();
            luoHuStudent1.setClassName(luoHuStudentTwo.getClassName());
            luoHuStudent1.setStudentCode(luoHuStudentTwo.getStudentCode());
            luoHuStudent1.setStudentName(luoHuStudentTwo.getStudentName());
            luoHuStudent1.setGender(luoHuStudentTwo.getGender());
            luoHuStudent1.setBatchNum(luoHuStudentTwo.getBatchNum());
            luoHuStudent1.setPlaceNum(luoHuStudentTwo.getPlaceNum());
            luoHuStudent1.setQuestionNum(luoHuStudentTwo.getQuestionNum());

            luoHuStudentList.add(luoHuStudent1);

            LuoHuStudent luoHuStudent2 = new LuoHuStudent();
            luoHuStudent2.setClassName(luoHuStudentTwo.getClassNameSecond());
            luoHuStudent2.setStudentCode(luoHuStudentTwo.getStudentCodeSecond());
            luoHuStudent2.setStudentName(luoHuStudentTwo.getStudentNameSecond());
            luoHuStudent2.setGender(luoHuStudentTwo.getGenderSecond());
            luoHuStudent2.setBatchNum(luoHuStudentTwo.getBatchNumSecond());
            luoHuStudent2.setPlaceNum(luoHuStudentTwo.getPlaceNumSecond());
            luoHuStudent2.setQuestionNum(luoHuStudentTwo.getQuestionNumSecond());

            if (!ObjectUtils.isEmpty(luoHuStudent2) && !ObjectUtils.isEmpty(luoHuStudent2.getClassName())) {
                luoHuStudentList.add(luoHuStudent2);
            }

        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        initQuestionMap();

        luoHuStudentList.stream().sorted(Comparator.comparing(LuoHuStudent::getBatchNum)).sorted(Comparator.comparing(LuoHuStudent::getStudentCode)).forEach(luoHuStudent -> {
            logger.info("解析到一条数据==>{}", luoHuStudent);
            // 物理
            LuoHuStudentExport luoHuStudentExport01 = new LuoHuStudentExport();
            luoHuStudentExport01.setAreaName("罗湖区");
            luoHuStudentExport01.setAreaCode("LH");
            luoHuStudentExport01.setSchoolName("罗湖外国语学校");
            luoHuStudentExport01.setSchoolCode("010");
            luoHuStudentExport01.setExamineeId(luoHuStudent.getStudentCode());
            luoHuStudentExport01.setExamineeName(luoHuStudent.getStudentName());

            String gender01 = null;
            String className01 = luoHuStudent.getClassName();
            if (!className01.equals("10班") && className01.contains("0")) {
                gender01 = className01.replace("0", "");
            }

            luoHuStudentExport01.setExamineeGender(gender01);

            String examineePlaceNum01 = luoHuStudent.getPlaceNum();
            luoHuStudentExport01.setExamineePlaceCode("00" + examineePlaceNum01 + "物理考场");

            luoHuStudentExport01.setExamSubjectCode("01");
            String questionNum01 = luoHuStudent.getQuestionNum();
            if (questionMapValueHashMap.containsKey(questionNum01)) {
                QuestionMapValue questionMapValue01 = questionMapValueHashMap.get(questionNum01);
                luoHuStudentExport01.setExamineeSeatNum(questionMapValue01.getExamineeSeatNum());
                luoHuStudentExport01.setExamQuestionCode(questionMapValue01.getExamQuestionCode());
                luoHuStudentExport01.setExamQuestionName(questionMapValue01.getExamQuestionName());
            }
            luoHuStudentExport01.setExamineeBatch(luoHuStudent.getBatchNum());
            luoHuStudentExportList.add(luoHuStudentExport01);

            // 化学
            LuoHuStudentExport luoHuStudentExport02 = new LuoHuStudentExport();
            luoHuStudentExport02.setAreaName("罗湖区");
            luoHuStudentExport02.setAreaCode("LH");
            luoHuStudentExport02.setSchoolName("罗湖外国语学校");
            luoHuStudentExport02.setSchoolCode("010");
            luoHuStudentExport02.setExamineeId(luoHuStudent.getStudentCode());
            luoHuStudentExport02.setExamineeName(luoHuStudent.getStudentName());

            String gender02 = null;
            String className02 = luoHuStudent.getClassName();
            if (!className02.equals("10班") && className02.contains("0")) {
                gender02 = className02.replace("0", "");
            }

            luoHuStudentExport02.setExamineeGender(gender02);

            String examineePlaceNum02 = luoHuStudent.getPlaceNum();
            luoHuStudentExport02.setExamineePlaceCode("00" + examineePlaceNum02 + "化学考场");

            luoHuStudentExport02.setExamSubjectCode("02");
            String questionNum02 = luoHuStudent.getQuestionNum() + luoHuStudent.getQuestionNum();
            if (questionMapValueHashMap.containsKey(questionNum02)) {
                QuestionMapValue questionMapValue02 = questionMapValueHashMap.get(questionNum02);
                luoHuStudentExport02.setExamineeSeatNum(questionMapValue02.getExamineeSeatNum());
                luoHuStudentExport02.setExamQuestionCode(questionMapValue02.getExamQuestionCode());
                luoHuStudentExport02.setExamQuestionName(questionMapValue02.getExamQuestionName());
            }
            luoHuStudentExport02.setExamineeBatch(luoHuStudent.getBatchNum());
            luoHuStudentExportList.add(luoHuStudentExport02);
        });

        logger.info("解析到学生数==>{}", luoHuStudentList.size());
        logger.info("解析到学生数理化总行数==>{}", luoHuStudentExportList.size());

        luoHuStudentExportList.forEach(luoHuStudentExport -> {
            logger.info("解析到学生导出==>{}", luoHuStudentExport);
        });

        String fileName0102 = "E:\\excel\\luohu\\罗湖区_LH_罗湖外国语学校_010_理化.xlsx";
        EasyExcel.write(fileName0102, LuoHuStudentExport.class).sheet().doWrite(luoHuStudentExportList);

    }


    public void initQuestionMap() {

        QuestionMapValue A1 = new QuestionMapValue();
        A1.setQuestionNum("A1");
        A1.setExamineeSeatNum("1");
        A1.setExamQuestionCode("A");
        A1.setExamQuestionName("A组：用弹簧测力计测量力的大小-现考现评");
        questionMapValueHashMap.put(A1.getQuestionNum(), A1);

        QuestionMapValue A1A1 = new QuestionMapValue();
        A1A1.setQuestionNum("A1A1");
        A1A1.setExamineeSeatNum("7");
        A1A1.setExamQuestionCode("A");
        A1A1.setExamQuestionName("A组：配制100g 3%的NaCl溶液-现考现评");
        questionMapValueHashMap.put(A1A1.getQuestionNum(), A1A1);

        QuestionMapValue A2 = new QuestionMapValue();
        A2.setQuestionNum("A2");
        A2.setExamineeSeatNum("13");
        A2.setExamQuestionCode("A");
        A2.setExamQuestionName("A组：用弹簧测力计测量力的大小-现考现评");
        questionMapValueHashMap.put(A2.getQuestionNum(), A2);

        QuestionMapValue A2A2 = new QuestionMapValue();
        A2A2.setQuestionNum("A2A2");
        A2A2.setExamineeSeatNum("19");
        A2A2.setExamQuestionCode("A");
        A2A2.setExamQuestionName("A组：配制100g 3%的NaCl溶液-现考现评");
        questionMapValueHashMap.put(A2A2.getQuestionNum(), A2A2);

        QuestionMapValue A3 = new QuestionMapValue();
        A3.setQuestionNum("A3");
        A3.setExamineeSeatNum("2");
        A3.setExamQuestionCode("A");
        A3.setExamQuestionName("A组：用弹簧测力计测量力的大小-现考现评");
        questionMapValueHashMap.put(A3.getQuestionNum(), A3);

        QuestionMapValue A3A3 = new QuestionMapValue();
        A3A3.setQuestionNum("A3A3");
        A3A3.setExamineeSeatNum("8");
        A3A3.setExamQuestionCode("A");
        A3A3.setExamQuestionName("A组：配制100g 3%的NaCl溶液-现考现评");
        questionMapValueHashMap.put(A3A3.getQuestionNum(), A3A3);

        QuestionMapValue A4 = new QuestionMapValue();
        A4.setQuestionNum("A4");
        A4.setExamineeSeatNum("14");
        A4.setExamQuestionCode("A");
        A4.setExamQuestionName("A组：用弹簧测力计测量力的大小-现考现评");
        questionMapValueHashMap.put(A4.getQuestionNum(), A4);

        QuestionMapValue A4A4 = new QuestionMapValue();
        A4A4.setQuestionNum("A4A4");
        A4A4.setExamineeSeatNum("20");
        A4A4.setExamQuestionCode("A");
        A4A4.setExamQuestionName("A组：配制100g 3%的NaCl溶液-现考现评");
        questionMapValueHashMap.put(A4A4.getQuestionNum(), A4A4);

        QuestionMapValue B1 = new QuestionMapValue();
        B1.setQuestionNum("B1");
        B1.setExamineeSeatNum("3");
        B1.setExamQuestionCode("B");
        B1.setExamQuestionName("B组：测量液体的密度-现考现评");
        questionMapValueHashMap.put(B1.getQuestionNum(), B1);

        QuestionMapValue B1B1 = new QuestionMapValue();
        B1B1.setQuestionNum("B1B1");
        B1B1.setExamineeSeatNum("9");
        B1B1.setExamQuestionCode("B");
        B1B1.setExamQuestionName("B组：二氧化碳的实验室制取与性质-现考现评");
        questionMapValueHashMap.put(B1B1.getQuestionNum(), B1B1);

        QuestionMapValue B2 = new QuestionMapValue();
        B2.setQuestionNum("B2");
        B2.setExamineeSeatNum("15");
        B2.setExamQuestionCode("B");
        B2.setExamQuestionName("B组：测量液体的密度-现考现评");
        questionMapValueHashMap.put(B2.getQuestionNum(), B1);

        QuestionMapValue B2B2 = new QuestionMapValue();
        B2B2.setQuestionNum("B2B2");
        B2B2.setExamineeSeatNum("21");
        B2B2.setExamQuestionCode("B");
        B2B2.setExamQuestionName("B组：二氧化碳的实验室制取与性质-现考现评");
        questionMapValueHashMap.put(B2B2.getQuestionNum(), B2B2);

        QuestionMapValue B3 = new QuestionMapValue();
        B3.setQuestionNum("B3");
        B3.setExamineeSeatNum("4");
        B3.setExamQuestionCode("B");
        B3.setExamQuestionName("B组：测量液体的密度-现考现评");
        questionMapValueHashMap.put(B3.getQuestionNum(), B3);

        QuestionMapValue B3B3 = new QuestionMapValue();
        B3B3.setQuestionNum("B3B3");
        B3B3.setExamineeSeatNum("10");
        B3B3.setExamQuestionCode("B");
        B3B3.setExamQuestionName("B组：二氧化碳的实验室制取与性质-现考现评");
        questionMapValueHashMap.put(B3B3.getQuestionNum(), B3B3);

        QuestionMapValue B4 = new QuestionMapValue();
        B4.setQuestionNum("B4");
        B4.setExamineeSeatNum("16");
        B4.setExamQuestionCode("B");
        B4.setExamQuestionName("B组：测量液体的密度-现考现评");
        questionMapValueHashMap.put(B4.getQuestionNum(), B4);

        QuestionMapValue B4B4 = new QuestionMapValue();
        B4B4.setQuestionNum("B4B4");
        B4B4.setExamineeSeatNum("22");
        B4B4.setExamQuestionCode("B");
        B4B4.setExamQuestionName("B组：二氧化碳的实验室制取与性质-现考现评");
        questionMapValueHashMap.put(B4B4.getQuestionNum(), B4B4);

        QuestionMapValue C1 = new QuestionMapValue();
        C1.setQuestionNum("C1");
        C1.setExamineeSeatNum("5");
        C1.setExamQuestionCode("C");
        C1.setExamQuestionName("C组：探究什么情况下磁可以生电-现考现评");
        questionMapValueHashMap.put(C1.getQuestionNum(), C1);

        QuestionMapValue C1C1 = new QuestionMapValue();
        C1C1.setQuestionNum("C1C1");
        C1C1.setExamineeSeatNum("11");
        C1C1.setExamQuestionCode("C");
        C1C1.setExamQuestionName("C组：谁是碳酸钠-现考现评");
        questionMapValueHashMap.put(C1C1.getQuestionNum(), C1C1);

        QuestionMapValue C2 = new QuestionMapValue();
        C2.setQuestionNum("C2");
        C2.setExamineeSeatNum("17");
        C2.setExamQuestionCode("C");
        C2.setExamQuestionName("C组：探究什么情况下磁可以生电-现考现评");
        questionMapValueHashMap.put(C2.getQuestionNum(), C1);

        QuestionMapValue C2C2 = new QuestionMapValue();
        C2C2.setQuestionNum("C2C2");
        C2C2.setExamineeSeatNum("23");
        C2C2.setExamQuestionCode("C");
        C2C2.setExamQuestionName("C组：谁是碳酸钠-现考现评");
        questionMapValueHashMap.put(C2C2.getQuestionNum(), C2C2);

        QuestionMapValue C3 = new QuestionMapValue();
        C3.setQuestionNum("C3");
        C3.setExamineeSeatNum("6");
        C3.setExamQuestionCode("C");
        C3.setExamQuestionName("C组：探究什么情况下磁可以生电-现考现评");
        questionMapValueHashMap.put(C3.getQuestionNum(), C3);

        QuestionMapValue C3C3 = new QuestionMapValue();
        C3C3.setQuestionNum("C3C3");
        C3C3.setExamineeSeatNum("12");
        C3C3.setExamQuestionCode("C");
        C3C3.setExamQuestionName("C组：谁是碳酸钠-现考现评");
        questionMapValueHashMap.put(C3C3.getQuestionNum(), C3C3);

        QuestionMapValue C4 = new QuestionMapValue();
        C4.setQuestionNum("C4");
        C4.setExamineeSeatNum("18");
        C4.setExamQuestionCode("C");
        C4.setExamQuestionName("C组：探究什么情况下磁可以生电-现考现评");
        questionMapValueHashMap.put(C4.getQuestionNum(), C4);

        QuestionMapValue C4C4 = new QuestionMapValue();
        C4C4.setQuestionNum("C4C4");
        C4C4.setExamineeSeatNum("24");
        C4C4.setExamQuestionCode("C");
        C4C4.setExamQuestionName("C组：谁是碳酸钠-现考现评");
        questionMapValueHashMap.put(C4C4.getQuestionNum(), C4C4);


    }

    public void initQuestionBioLogyMap() {

        QuestionMapValue A1 = new QuestionMapValue();
        A1.setQuestionNum("A1");
        A1.setExamineeSeatNum("1");
        A1.setExamQuestionCode("A");
        A1.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A1.getQuestionNum(), A1);

        QuestionMapValue A1A1 = new QuestionMapValue();
        A1A1.setQuestionNum("A1A1");
        A1A1.setExamineeSeatNum("7");
        A1A1.setExamQuestionCode("A");
        A1A1.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A1A1.getQuestionNum(), A1A1);

        QuestionMapValue A2 = new QuestionMapValue();
        A2.setQuestionNum("A2");
        A2.setExamineeSeatNum("13");
        A2.setExamQuestionCode("A");
        A2.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A2.getQuestionNum(), A2);

        QuestionMapValue A2A2 = new QuestionMapValue();
        A2A2.setQuestionNum("A2A2");
        A2A2.setExamineeSeatNum("19");
        A2A2.setExamQuestionCode("A");
        A2A2.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A2A2.getQuestionNum(), A2A2);

        QuestionMapValue A3 = new QuestionMapValue();
        A3.setQuestionNum("A3");
        A3.setExamineeSeatNum("2");
        A3.setExamQuestionCode("A");
        A3.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A3.getQuestionNum(), A3);

        QuestionMapValue A3A3 = new QuestionMapValue();
        A3A3.setQuestionNum("A3A3");
        A3A3.setExamineeSeatNum("8");
        A3A3.setExamQuestionCode("A");
        A3A3.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A3A3.getQuestionNum(), A3A3);

        QuestionMapValue A4 = new QuestionMapValue();
        A4.setQuestionNum("A4");
        A4.setExamineeSeatNum("14");
        A4.setExamQuestionCode("A");
        A4.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A4.getQuestionNum(), A4);

        QuestionMapValue A4A4 = new QuestionMapValue();
        A4A4.setQuestionNum("A4A4");
        A4A4.setExamineeSeatNum("20");
        A4A4.setExamQuestionCode("A");
        A4A4.setExamQuestionName("A组：观察青霉永久装片-现考现评");
        questionBiologyMapValueHashMap.put(A4A4.getQuestionNum(), A4A4);

        QuestionMapValue B1 = new QuestionMapValue();
        B1.setQuestionNum("B1");
        B1.setExamineeSeatNum("3");
        B1.setExamQuestionCode("B");
        B1.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B1.getQuestionNum(), B1);

        QuestionMapValue B1B1 = new QuestionMapValue();
        B1B1.setQuestionNum("B1B1");
        B1B1.setExamineeSeatNum("9");
        B1B1.setExamQuestionCode("B");
        B1B1.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B1B1.getQuestionNum(), B1B1);

        QuestionMapValue B2 = new QuestionMapValue();
        B2.setQuestionNum("B2");
        B2.setExamineeSeatNum("15");
        B2.setExamQuestionCode("B");
        B2.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B2.getQuestionNum(), B1);

        QuestionMapValue B2B2 = new QuestionMapValue();
        B2B2.setQuestionNum("B2B2");
        B2B2.setExamineeSeatNum("21");
        B2B2.setExamQuestionCode("B");
        B2B2.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B2B2.getQuestionNum(), B2B2);

        QuestionMapValue B3 = new QuestionMapValue();
        B3.setQuestionNum("B3");
        B3.setExamineeSeatNum("4");
        B3.setExamQuestionCode("B");
        B3.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B3.getQuestionNum(), B3);

        QuestionMapValue B3B3 = new QuestionMapValue();
        B3B3.setQuestionNum("B3B3");
        B3B3.setExamineeSeatNum("10");
        B3B3.setExamQuestionCode("B");
        B3B3.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B3B3.getQuestionNum(), B3B3);

        QuestionMapValue B4 = new QuestionMapValue();
        B4.setQuestionNum("B4");
        B4.setExamineeSeatNum("16");
        B4.setExamQuestionCode("B");
        B4.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B4.getQuestionNum(), B4);

        QuestionMapValue B4B4 = new QuestionMapValue();
        B4B4.setQuestionNum("B4B4");
        B4B4.setExamineeSeatNum("22");
        B4B4.setExamQuestionCode("B");
        B4B4.setExamQuestionName("B组：制作叶片下表皮临时装片-现考现评");
        questionBiologyMapValueHashMap.put(B4B4.getQuestionNum(), B4B4);

        QuestionMapValue C1 = new QuestionMapValue();
        C1.setQuestionNum("C1");
        C1.setExamineeSeatNum("5");
        C1.setExamQuestionCode("C");
        C1.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C1.getQuestionNum(), C1);

        QuestionMapValue C1C1 = new QuestionMapValue();
        C1C1.setQuestionNum("C1C1");
        C1C1.setExamineeSeatNum("11");
        C1C1.setExamQuestionCode("C");
        C1C1.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C1C1.getQuestionNum(), C1C1);

        QuestionMapValue C2 = new QuestionMapValue();
        C2.setQuestionNum("C2");
        C2.setExamineeSeatNum("17");
        C2.setExamQuestionCode("C");
        C2.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C2.getQuestionNum(), C1);

        QuestionMapValue C2C2 = new QuestionMapValue();
        C2C2.setQuestionNum("C2C2");
        C2C2.setExamineeSeatNum("23");
        C2C2.setExamQuestionCode("C");
        C2C2.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C2C2.getQuestionNum(), C2C2);

        QuestionMapValue C3 = new QuestionMapValue();
        C3.setQuestionNum("C3");
        C3.setExamineeSeatNum("6");
        C3.setExamQuestionCode("C");
        C3.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C3.getQuestionNum(), C3);

        QuestionMapValue C3C3 = new QuestionMapValue();
        C3C3.setQuestionNum("C3C3");
        C3C3.setExamineeSeatNum("12");
        C3C3.setExamQuestionCode("C");
        C3C3.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C3C3.getQuestionNum(), C3C3);

        QuestionMapValue C4 = new QuestionMapValue();
        C4.setQuestionNum("C4");
        C4.setExamineeSeatNum("18");
        C4.setExamQuestionCode("C");
        C4.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C4.getQuestionNum(), C4);

        QuestionMapValue C4C4 = new QuestionMapValue();
        C4C4.setQuestionNum("C4C4");
        C4C4.setExamineeSeatNum("24");
        C4C4.setExamQuestionCode("C");
        C4C4.setExamQuestionName("C组：探究花生果实大小的变异-现考现评");
        questionBiologyMapValueHashMap.put(C4C4.getQuestionNum(), C4C4);


    }

}
