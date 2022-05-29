package com.elvesyuki.javautils.web.object;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 17:07
 * @description：
 * @modified By：
 */
public class LuoHuStudent {

    public String className;
    public String studentCode;
    public String studentName;
    public String gender;
    public String batchNum;
    public String placeNum;
    public String questionNum;

    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(String placeNum) {
        this.placeNum = placeNum;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    @Override
    public String toString() {
        return "LuoHuStudent{" +
                "className='" + className + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", placeNum='" + placeNum + '\'' +
                ", questionNum='" + questionNum + '\'' +
                '}';
    }
}
