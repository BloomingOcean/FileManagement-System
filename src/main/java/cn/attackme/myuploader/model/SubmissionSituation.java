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
 * submission_situation表存储上传的文件信息
 */
@Data
@ToString
@ApiModel
@AllArgsConstructor
@Table(name = "submission_situation")
public class SubmissionSituation implements Serializable {

    private static final long serialVersionUID = -6956947981866795431L;

    @Id
    @Column(name = "sub_id")
    @ApiModelProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subId;

    @Column(name = "file_id")
    @ApiModelProperty(value = "文件id")
    private Integer fileId;

    @Column(name = "class_id")
    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @Column(name = "situation")
    @ApiModelProperty(value = "提交情况")
    private String situation;

    @Column(name = "file_path")
    @ApiModelProperty(value = "文件保存路径")
    private String filePath;

    @Column(name = "upload_time")
    @ApiModelProperty(value = "上传时的时间")
    private Date uploadTime;

    public SubmissionSituation(){}

    public SubmissionSituation(Integer subId, Integer fileId, Integer classId, String filePath, Date uploadTime){
        this.subId = subId;
        this.fileId = fileId;
        this.classId = classId;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }
}
