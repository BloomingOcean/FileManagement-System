package cn.attackme.myuploader.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ClassInformation {

    @Column(name = "class_id")
    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @Column(name = "major_name")
    @ApiModelProperty(value = "专业名称")
    private String majorName;

    @Column(name = "class_sequence")
    @ApiModelProperty(value = "班级次序")
    private String classSequence;
}
