package cn.attackme.myuploader.service;

import cn.attackme.myuploader.dao.FormDao;
import cn.attackme.myuploader.model.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormDao formDao;

    /**
     * 上传表单文件
     * @param form 表单
     */
    public void unloadForm(Form form){
        formDao.UnloadForm(form.getName(),form.getPass(),form.getIdNumber(),form.getGender(),form.getAddress(),form.getHobby(),form.getEducation());
    }

    /**
     * 检验登录
     * @param username 用户名
     * @param password 密码
     */
    public Integer checkLogin(String username,String password){
        return formDao.checkLogin(username, password);
    }
}
