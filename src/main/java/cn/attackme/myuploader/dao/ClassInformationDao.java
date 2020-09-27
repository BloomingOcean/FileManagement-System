package cn.attackme.myuploader.dao;

import cn.attackme.myuploader.model.ClassInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassInformationDao {
    /**
     *查询哪些班级在file_name那一期未交文件
     * @param fileName 上交期次名称
     * @return 未上交的所有班级
     */
    List<ClassInformation> UnloadSituation(@Param("file_name") String fileName);

    /**
     *查询哪些班级在file_name那一期已交文件
     * @param fileName 上交期次名称
     * @return 未上交的所有班级
     */
    List<ClassInformation> UploadedSituation(@Param("file_name") String fileName);

    /**
     * 获得最新期次的名称
     * @return 最新期次的名称
     */
    String getNewPeriodName();
}
