package cn.attackme.myuploader.dao;


import cn.attackme.myuploader.model.Form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FormDao {

    /**
     * 上传表单
     * @param name 姓名
     * @param pass 密码
     * @param idNumber 身份证号
     * @param gender 性别
     * @param address 地址
     * @param hobby 爱好
     * @param education 学历
     */
    @Insert("INSERT INTO form (name,pass,id_number,gender,address,hobby,education) " +
            "VALUES(#{name},#{pass},#{idNumber},#{gender},#{address},#{hobby},#{education});")
    void UnloadForm(@Param("name") String name,
                    @Param("pass") String pass,
                    @Param("idNumber") String idNumber,
                    @Param("gender") String gender,
                    @Param("address") String address,
                    @Param("hobby") String hobby,
                    @Param("education") String education);

    @Select("SELECT user_id FROM FORM " +
            "WHERE `name` = #{username} AND pass = #{password}")
    Integer checkLogin(@Param("username") String username,
                       @Param("password") String password);
}
