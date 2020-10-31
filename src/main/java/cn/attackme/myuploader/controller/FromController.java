package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.model.Form;
import cn.attackme.myuploader.service.FormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "注册表单上传api")
@RequestMapping("/form")
@RestController
@CrossOrigin
public class FromController {

    @Autowired
    private FormService formService;

    @ApiOperation(value = "上传表单")
    @RequestMapping(value = "/unloadForm", method = RequestMethod.POST)
    public void unloadForm(Form form){
        formService.unloadForm(form);
    }

    @ApiOperation(value = "检验登录")
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public Integer checkLogin(String username,String password){
        return formService.checkLogin(username,password);
    }

}
