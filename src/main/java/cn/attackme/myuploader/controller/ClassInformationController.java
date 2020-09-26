package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.model.ClassInformation;
import cn.attackme.myuploader.service.ClassInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RequestMapping("/classInfo")
@RestController
@CrossOrigin
public class ClassInformationController {

    @Autowired
    private ClassInformationService classInformationService;

    @ApiOperation(value = "获取所有未上交班级")
    @RequestMapping(value = "/UnloadClass", method = RequestMethod.GET)
    public List<ClassInformation> checkUnloadSituation(Integer FiledId){
        return classInformationService.checkUnloadSituation(FiledId);
    }

}
