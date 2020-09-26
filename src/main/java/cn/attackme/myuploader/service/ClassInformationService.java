package cn.attackme.myuploader.service;

import cn.attackme.myuploader.dao.ClassInformationDao;
import cn.attackme.myuploader.model.ClassInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInformationService {

    @Autowired
    private ClassInformationDao classInformationDao;

    /**
     * 获取某期次未上传的所有班级
     * @param fileId 文件期次
     * @return 所有未上交班级
     */
    public List<ClassInformation> checkUnloadSituation(Integer fileId) {
        return classInformationDao.checkUnloadSituation(fileId);
    }

}
