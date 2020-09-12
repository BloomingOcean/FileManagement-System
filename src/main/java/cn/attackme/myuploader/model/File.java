package cn.attackme.myuploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * File表存储上传的文件信息
 */
@Data
@ToString
@ApiModel
@AllArgsConstructor
@Table(name = "file")
public class File implements Serializable {

    private static final long serialVersionUID = -6956947981866795431L;

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @ApiModelProperty(value = "文件名称")
    private String name;

    @Column(name = "md5")
    @ApiModelProperty(value = "md5")
    private String md5;

    @Column(name = "path")
    @ApiModelProperty(value = "文件地址")
    private String path;

    @Column(name = "upload_time")
    @ApiModelProperty(value = "上传时的时间")
    private Date uploadTime;

    public File(){}

    public File(String name, String md5, String path, Date uploadTime){
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.uploadTime = uploadTime;
    }
}
