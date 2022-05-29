package com.elvesyuki.javautils.web.object;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author chenjianyu
 * @date 2022/05/29 17:57
 */
public class ExamData {

    @ExcelProperty("区域名称")
    private String area;
    @ExcelProperty("区域代码")
    private String areaCode;
    @ExcelProperty("学校名称")
    private String schoolName;
    @ExcelProperty("学校代码")
    private String schoolCode;
    @ExcelProperty("准考号")
    private String examCode;
    @ExcelProperty("姓名")
    private String stuName;
    @ExcelProperty("班级")
    private String className;
    @ExcelProperty("考场号")
    private String placeCode;
    @ExcelProperty("座位号")
    private String seatNum;
    @ExcelProperty("考试科目编码")
    private String examSubjectCode;
    @ExcelProperty("考题编号")
    private String examTitleCode;
    @ExcelProperty("考题")
    private String examTitleName;
    @ExcelProperty("批次号")
    private String batchNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getExamSubjectCode() {
        return examSubjectCode;
    }

    public void setExamSubjectCode(String examSubjectCode) {
        this.examSubjectCode = examSubjectCode;
    }

    public String getExamTitleCode() {
        return examTitleCode;
    }

    public void setExamTitleCode(String examTitleCode) {
        this.examTitleCode = examTitleCode;
    }

    public String getExamTitleName() {
        return examTitleName;
    }

    public void setExamTitleName(String examTitleName) {
        this.examTitleName = examTitleName;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    @Override
    public String toString() {
        return "ExamData{" +
                "area='" + area + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", examCode='" + examCode + '\'' +
                ", stuName='" + stuName + '\'' +
                ", className='" + className + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", seatNum='" + seatNum + '\'' +
                ", examSubjectCode='" + examSubjectCode + '\'' +
                ", examTitleCode='" + examTitleCode + '\'' +
                ", examTitleName='" + examTitleName + '\'' +
                ", batchNum='" + batchNum + '\'' +
                '}';
    }
}
