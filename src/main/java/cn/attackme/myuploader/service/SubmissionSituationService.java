package cn.attackme.myuploader.service;

import cn.attackme.myuploader.config.UploadConfig;
import cn.attackme.myuploader.dao.SubmissionSituationDao;
import cn.attackme.myuploader.model.SubmissionSituation;
import cn.attackme.myuploader.utils.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static cn.attackme.myuploader.utils.UploadUtils.*;

/**
 * 文件上传服务
 */
@Service
public class SubmissionSituationService {
    @Autowired
    private SubmissionSituationDao submissionSituationDao;

    /**
     * 上传文件
     */
    public void upload(String periodName, String grade, String major, String sequence, MultipartFile file) throws IOException {
        //对文文件的全名进行截取然后在后缀名进行删选。
        int begin = file.getOriginalFilename().indexOf(".");
        int last = file.getOriginalFilename().length();
        //获得文件后缀名
        String suffix = file.getOriginalFilename().substring(begin, last);
        System.out.println("后缀名：" + suffix);
        //获得专业名简称
        String shorthand = submissionSituationDao.getMajorShorthand(major);
        //合成文件名
        String name = periodName +"-"+ grade + "-" + shorthand + "-" + sequence + suffix;
        System.out.println("文件名：" + name);
        //文件存放地址
        String filePath = UploadConfig.path + name;
        FileUtils.write(filePath, file.getInputStream());
        //获取班级id
        Integer classId = submissionSituationDao.getClassId(grade, major, sequence);
        //获得期次
        Integer fileId = submissionSituationDao.getPeriodId(periodName);
        //判断数据库是否已经有此班级的数据
        Integer subId = submissionSituationDao.judgeUpload(fileId, classId);
        //如果在数据库中已经有此班级的数据，则把此班级的数据删除
        if(subId == null){
            subId = 0;
        }
        if(subId != 0){
            submissionSituationDao.deleteRepeatFile(subId);
        }
        //获取可插入的最大id
        Integer newId = getMaxId() + 1;
        submissionSituationDao.save(
                new SubmissionSituation(newId, fileId, classId, filePath, new Date()));
    }

    /**
     * 分块上传文件
     */
    public void uploadWithBlock(String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                Integer fileId,
                                Integer classId,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            //获取可插入的最大id
            Integer newId = getMaxId() + 1;
            submissionSituationDao.save(new SubmissionSituation(newId, fileId, classId, UploadConfig.path + fileName, new Date()));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
//    public boolean checkMd5(String md5) {
//        SubmissionSituation submissionSituation = new SubmissionSituation();
//        submissionSituation.setMd5(md5);
//        return submissionSituationDao.getByFile(submissionSituation) == null;
//    }

    /**
     * 获取数据表中最大的id
     * @return 最大id
     */
    public Integer getMaxId(){
        return submissionSituationDao.getMaxId();
    }

    /**
     * 获取所有期数
     * @return 所有期数
     */
    public List<String> getPeriod(){
        return submissionSituationDao.getPeriod();
    }

    /**
     * 通过年级、专业、班级联查出班级id
     * @param grade 年级
     * @param major 专业
     * @param sequence 班级
     * @return 班级id
     */
    public Integer getClassId(String grade, String major, String sequence){
        return submissionSituationDao.getClassId(grade, major, sequence);
    }

    /**
     * 获取grade年级的所有专业
     * @param grade 年级
     * @return 所有专业
     */
    public List<String> getMajor(String grade){
        return submissionSituationDao.getMajor(grade);
    }

    /**
     * 获取grade年级、major专业的所有班级
     * @param grade 年级
     * @param major 专业
     * @return 所有班级
     */
    public List<String> getAllClass(String grade, String major){
        return submissionSituationDao.getAllClass(grade,major);
    }

    /**
     * 判断是否上传了文件
     * @param fileId 期数id
     * @param classId 班级id
     * @return 是否已经上传文件
     */
    public Integer judgeUpload(Integer fileId, Integer classId){
        Integer subId = submissionSituationDao.judgeUpload(fileId, classId);
        if(subId instanceof Integer){
            return submissionSituationDao.judgeUpload(fileId, classId);
        }else {
            return 0;
        }
    }


}
