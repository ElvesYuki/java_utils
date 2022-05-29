package com.elvesyuki.javautils.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.elvesyuki.javautils.web.object.LuoHuStudentExport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/29/029 22:12
 * @description：
 * @modified By：
 */
public class LuoHuExportVideoListener implements ReadListener<LuoHuStudentExport> {

    public List<LuoHuStudentExport> luoHuStudentExportList = new ArrayList<>();

    @Override
    public void invoke(LuoHuStudentExport data, AnalysisContext context) {
        luoHuStudentExportList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
