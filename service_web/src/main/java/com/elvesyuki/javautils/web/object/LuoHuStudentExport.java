package com.elvesyuki.javautils.web.object;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 18:13
 * @description：
 * @modified By：
 */
public class LuoHuStudentExport {

    @ExcelProperty("区域名称")
    public String areaName;
    @ExcelProperty("区域代码")
    public String areaCode;
    @ExcelProperty("学校名称")
    public String schoolName;
    @ExcelProperty("学校代码")
    public String schoolCode;
    @ExcelProperty("准考号")
    public String examineeId;
    @ExcelProperty("姓名")
    public String examineeName;
    @ExcelProperty("班级")
    public String examineeGender;
    @ExcelProperty("考场号")
    public String examineePlaceCode;
    @ExcelProperty("座位号")
    public String examineeSeatNum;
    @ExcelProperty("考试科目编码")
    public String examSubjectCode;
    @ExcelProperty("考题编号")
    public String examQuestionCode;
    @ExcelProperty("考题")
    public String examQuestionName;
    @ExcelProperty("批次号")
    public String examineeBatch;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(String examineeId) {
        this.examineeId = examineeId;
    }

    public String getExamineeName() {
        return examineeName;
    }

    public void setExamineeName(String examineeName) {
        this.examineeName = examineeName;
    }

    public String getExamineeGender() {
        return examineeGender;
    }

    public void setExamineeGender(String examineeGender) {
        this.examineeGender = examineeGender;
    }

    public String getExamineePlaceCode() {
        return examineePlaceCode;
    }

    public void setExamineePlaceCode(String examineePlaceCode) {
        this.examineePlaceCode = examineePlaceCode;
    }

    public String getExamineeSeatNum() {
        return examineeSeatNum;
    }

    public void setExamineeSeatNum(String examineeSeatNum) {
        this.examineeSeatNum = examineeSeatNum;
    }

    public String getExamSubjectCode() {
        return examSubjectCode;
    }

    public void setExamSubjectCode(String examSubjectCode) {
        this.examSubjectCode = examSubjectCode;
    }

    public String getExamQuestionCode() {
        return examQuestionCode;
    }

    public void setExamQuestionCode(String examQuestionCode) {
        this.examQuestionCode = examQuestionCode;
    }

    public String getExamQuestionName() {
        return examQuestionName;
    }

    public void setExamQuestionName(String examQuestionName) {
        this.examQuestionName = examQuestionName;
    }

    public String getExamineeBatch() {
        return examineeBatch;
    }

    public void setExamineeBatch(String examineeBatch) {
        this.examineeBatch = examineeBatch;
    }

    @Override
    public String toString() {
        return "LuoHuStudentExport{" +
                "areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", examineeId='" + examineeId + '\'' +
                ", examineeName='" + examineeName + '\'' +
                ", examineeGender='" + examineeGender + '\'' +
                ", examineePlaceCode='" + examineePlaceCode + '\'' +
                ", examineeSeatNum='" + examineeSeatNum + '\'' +
                ", examSubjectCode='" + examSubjectCode + '\'' +
                ", examQuestionCode='" + examQuestionCode + '\'' +
                ", examQuestionName='" + examQuestionName + '\'' +
                ", examineeBatch='" + examineeBatch + '\'' +
                '}';
    }
}
