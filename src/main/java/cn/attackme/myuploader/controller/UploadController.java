package cn.attackme.myuploader.controller;
import cn.attackme.myuploader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FileService fileService;

    @RequestMapping(value = "/SingleFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传单个文件至服务器")
    public void singleUpload(String md5,
                       MultipartFile file) throws IOException {
        fileService.upload(md5, file);
    }

    @RequestMapping(value = "/BigFile", method = RequestMethod.POST)
    @ApiOperation(value = "上传较大文件至服务器")
    public void bigUpload(String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            fileService.uploadWithBlock(md5,size,chunks,chunk,file);
        } else {
            fileService.upload(md5,file);
        }
    }

    @RequestMapping(value = "/QuickUpload" , method = RequestMethod.POST)
    @ApiOperation(value = "通过文件的md5快速上传文件")
    public boolean quickUpload(String md5) {
        return fileService.checkMd5(md5);
    }

    @ApiOperation(value = "获取最大id值")
    @RequestMapping(value = "/getMaxId", method = RequestMethod.GET)
    public Integer getMaxId(){
        return fileService.getMaxId();
    }
}









