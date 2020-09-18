package cn.attackme.myuploader.dao;

import cn.attackme.myuploader.model.SubmissionSituation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 获取所有期数
     * @return 期名list
     */
    @Select("SELECT file_name FROM required_files;")
    List<String> getPeriod();

    /**
     * 根据期名获得期次id
     * @return 期名list
     */
    @Select("SELECT file_id FROM required_files " +
            "WHERE file_name=#{file_name}")
    Integer getPeriodId(@Param("file_name") String fileName);

    /**
     * 获得选择班级的id
     * @return 班级id
     */
    @Select("select c.class_id from class c, major m " +
            "WHERE c.class_major_id=m.major_id " +
            "AND c.class_grade=#{grade} " +
            "AND m.major_name=#{major} " +
            "AND c.class_sequence=#{sequence}")
    Integer getClassId(@Param("grade") String grade,
                       @Param("major") String major,
                       @Param("sequence") String sequence);

    /**
     * 获取grade年级的所有专业
     * @param grade 年级
     * @return 所有专业
     */
    @Select("SELECT m.major_name from class c,major m " +
            "WHERE c.class_major_id=m.major_id " +
            "AND c.class_grade=#{grade} " +
            "GROUP BY m.major_name; ")
    List<String> getMajor(@Param("grade") String grade);

    /**
     * 根据专业全称获取简称
     * @return 专业简称
     */
    @Select("SELECT major_shorthand FROM major " +
            "WHERE major_name=#{major_name}")
    String getMajorShorthand(@Param("major_name") String majorName);

    /**
     *获取某年级、某专业的所有班级
     * @param grade 年级
     * @param major 专业
     * @return 所有班级
     */
    @Select("select c.class_sequence from class c, major m " +
            "WHERE c.class_major_id=m.major_id " +
            "AND c.class_grade=#{grade} " +
            "AND m.major_name=#{major} " +
            "GROUP BY c.class_sequence;")
    List<String> getAllClass(@Param("grade") String grade,
                          @Param("major") String major);

    /**
     * 判断是否上传了文件
     * @param fileId 期数id
     * @param classId 班级id
     * @return 是否已经上传文件
     */
    @Select("SELECT sub_id FROM submission_situation " +
            "WHERE file_id=#{file_id} AND class_id=#{class_id}")
    Integer judgeUpload(@Param("file_id") Integer fileId,
                        @Param("class_id") Integer classId);

    @Delete("DELETE FROM submission_situation " +
            "WHERE sub_id = #{sub_id}; ")
    void deleteRepeatFile(@Param("sub_id") Integer subId);

}
