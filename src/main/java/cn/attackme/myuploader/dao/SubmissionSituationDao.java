package cn.attackme.myuploader.dao;

import cn.attackme.myuploader.model.SubmissionSituation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubmissionSituationDao {
    /**
     * 通过主键获取文件上传详情
     * @return
     */
    SubmissionSituation getById(int id);

    /**
     * 上传文件插入一条记录
     * @param submissionSituation
     * @return
     */
    int save(SubmissionSituation submissionSituation);

    /**
     * 更新一行数据
     * @param submissionSituation
     * @return
     */
    int update(SubmissionSituation submissionSituation);

    /**
     * 删除一行数据
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据一个或多个属性获取File
     * @param submissionSituation
     * @return
     */
    SubmissionSituation getByFile(SubmissionSituation submissionSituation);

    /**
     * 获得数据表中最大的id
     * @return 最大id
     */
    Integer getMaxId();
}
