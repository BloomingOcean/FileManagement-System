package cn.attackme.myuploader.service;

import cn.attackme.myuploader.config.UploadConfig;
import cn.attackme.myuploader.dao.SubmissionSituationDao;
import cn.attackme.myuploader.model.SubmissionSituation;
import cn.attackme.myuploader.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

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
    public void upload(Integer fileId, Integer classId, MultipartFile file) throws IOException {
        // 获取文件名称,并把文件流写入path
        String name = file.getOriginalFilename();
        String filePath = UploadConfig.path + name;
        FileUtils.write(filePath, file.getInputStream());
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
}
