package cn.attackme.myuploader.service;

import cn.attackme.myuploader.config.UploadConfig;
import cn.attackme.myuploader.dao.FileDao;
import cn.attackme.myuploader.model.File;
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
public class FileService {
    @Autowired
    private FileDao fileDao;


    /**
     * 上传文件
     * @param md5
     * @param file
     */
    public void upload(String md5,
                       MultipartFile file) throws IOException {
        // 获取文件名称
        String name = file.getOriginalFilename();
        String path = UploadConfig.path + file.getOriginalFilename();
        // 把文件流写入
        FileUtils.write(path, file.getInputStream());
        int id = getMaxId() + 1;
        fileDao.save(new File(id, name, md5, path, new Date()));
    }

    /**
     * 分块上传文件
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        String name = file.getOriginalFilename();
        if (isUploaded(md5)) {
            removeKey(md5);
            fileDao.save(new File(name, md5,UploadConfig.path + fileName, new Date()));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileDao.getByFile(file) == null;
    }

    /**
     * 获取数据表中最大的id
     * @return 最大id
     */
    public Integer getMaxId(){
        return fileDao.getMaxId();
    }
}
