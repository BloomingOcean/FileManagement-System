package cn.attackme.myuploader.dao;

import cn.attackme.myuploader.model.ClassInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassInformationDao {
    /**
     *查询哪些班级在file_id那一期未交文件
     * @param fileId 上交期次
     * @return 未上交的所有班级
     */
//    @Select("SELECT c.class_id,m.major_name,c.class_sequence " +
//            "FROM class c,major m " +
//            "WHERE c.class_major_id = m.major_id " +
//            "AND c.class_id NOT IN " +
//            "(SELECT s.class_id " +
//            "FROM submission_situation s " +
//            "WHERE s.file_id = #{file_id})")
    List<ClassInformation> checkUnloadSituation(@Param("file_id") Integer fileId);
}
