package cn.attackme.myuploader.controller;
import cn.attackme.myuploader.service.SubmissionSituationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件上传api")
@RestController
@RequestMapping("/")
@CrossOrigin
public class UploadController {
    @Autowired
    private SubmissionSituationService submissionSituationService;

    @RequestMapping(value = "/SingleFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传单个文件至服务器")
    public void singleUpload(Integer fileId, Integer classId, MultipartFile file) throws IOException {
        submissionSituationService.upload(fileId, classId, file);
    }

    @RequestMapping(value = "/BigFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传较大文件至服务器")
    public void bigUpload(String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       Integer fileId,
                       Integer classId,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            submissionSituationService.uploadWithBlock(md5, size, chunks, chunk, fileId, classId, file);
        } else {
            submissionSituationService.upload(fileId, classId, file);
        }
    }

//    @RequestMapping(value = "/QuickUpload" , method = RequestMethod.POST)
//    @ApiOperation(value = "通过md5判断文件是否已经上传")
//    public boolean quickUpload(String md5) {
//        return submissionSituationService.checkMd5(md5);
//    }

    @ApiOperation(value = "获取最大id值")
    @RequestMapping(value = "/getMaxId", method = RequestMethod.GET)
    public Integer getMaxId(){
        return submissionSituationService.getMaxId();
    }
}









