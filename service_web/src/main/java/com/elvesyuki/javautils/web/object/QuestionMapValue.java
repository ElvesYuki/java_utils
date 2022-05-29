package com.elvesyuki.javautils.web.object;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 18:39
 * @description：
 * @modified By：
 */
public class QuestionMapValue {

    private String questionNum;
    private String examineeSeatNum;

    private String examQuestionCode;

    private String examQuestionName;

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public String getExamineeSeatNum() {
        return examineeSeatNum;
    }

    public void setExamineeSeatNum(String examineeSeatNum) {
        this.examineeSeatNum = examineeSeatNum;
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

    @Override
    public String toString() {
        return "QuestionMapValue{" +
                "questionNum='" + questionNum + '\'' +
                ", examineeSeatNum='" + examineeSeatNum + '\'' +
                ", examQuestionCode='" + examQuestionCode + '\'' +
                ", examQuestionName='" + examQuestionName + '\'' +
                '}';
    }
}
