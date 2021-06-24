package com.elvesyuki.javautils.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.elvesyuki.javautils.oss.object.OssObject;
import com.elvesyuki.javautils.oss.util.OssUtils;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/6/21 下午3:16
 */
@Api(tags = "oss测试接口类")
@RestController
@RequestMapping("/oss/test")
public class OssController {

    @ApiOperation("上传文件测试")
    @PostMapping("/test/addUploadFile")
    public OssObject addUploadFile(@RequestParam("file") MultipartFile file) {

        OssObject temp = OssUtils.putObject(file, "temp");

        return temp;
    }

}
