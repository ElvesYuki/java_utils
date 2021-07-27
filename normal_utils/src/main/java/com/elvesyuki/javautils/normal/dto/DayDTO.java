package com.elvesyuki.javautils.normal.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/27 下午3:36
 */
public class DayDTO implements Serializable {

    @ApiModelProperty(value = "日")
    private Integer day;

    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "年")
    private Integer year;

    @ApiModelProperty(value = "周几-数字")
    private Integer weekValue;

    @ApiModelProperty(value = "周几")
    private String week;

    @ApiModelProperty(value = "当天00:00:00时间")
    private Long startTime;

    @ApiModelProperty(value = "当天23:59:59点时间")
    private Long endTime;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeekValue() {
        return weekValue;
    }

    public void setWeekValue(Integer weekValue) {
        this.weekValue = weekValue;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DayDTO{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", weekValue=" + weekValue +
                ", week='" + week + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
