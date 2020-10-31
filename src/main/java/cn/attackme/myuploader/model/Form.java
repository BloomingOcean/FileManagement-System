package cn.attackme.myuploader.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ApiModel
public class Form {

//    @Id
//    @Column(name = "user_id")
//    @ApiModelProperty(value = "userId")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer userId;

    @Column(name = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Column(name = "pass")
    @ApiModelProperty(value = "密码")
    private String pass;

    @Column(name = "id_number")
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @Column(name = "gender")
    @ApiModelProperty(value = "性别")
    private String gender;

    @Column(name = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "hobby")
    @ApiModelProperty(value = "爱好")
    private String hobby;

    @Column(name = "education")
    @ApiModelProperty(value = "学历")
    private String education;

    public Form(){}

    public Form(String name,String pass,String idNumber,String gender,String address,String hobby,String education){
        this.name = name;
        this.pass = pass;
        this.idNumber = idNumber;
        this.gender = gender;
        this.address = address;
        this.hobby = hobby;
        this.education = education;
    }
}
