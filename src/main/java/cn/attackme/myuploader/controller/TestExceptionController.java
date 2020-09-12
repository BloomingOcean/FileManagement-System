package cn.attackme.myuploader.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static cn.attackme.myuploader.utils.LogUtils.logToFile;

/**
 * 测试日志功能
 */
@RestController
@RequestMapping("/Ex")
public class TestExceptionController {
    /**setPath
     * 测试日志切面
     * @return
     */
    @RequestMapping(value = "/aspect", method = RequestMethod.GET)
    @ApiOperation(tags = "测试切面",value = "测试日志切面")
    public int aspect() {
        int i = 1 / 0;
        return i;
    }

    /**
     * 测试日志util
     */
    @RequestMapping(value = "/util", method = RequestMethod.GET)
    @ApiOperation(tags = "测试util",value = "测试日志util")
    public void util() {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            logToFile(e);
        }
    }
}
