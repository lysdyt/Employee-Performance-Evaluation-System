package com.epes.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * Description:
 * Date: 2018/4/2
 * Time: 9:52
 *
 * @Author lixingjie
 * @Modifice
 */

@Getter
@Setter
@Table(name = "demo_project")
public class Project extends BaseEntity {

    @Column(name = "id", type = MySqlTypeConstant.CHAR, isKey = true, length = 36)
    private String id;

    @Column(name = "name", type = MySqlTypeConstant.VARCHAR,isNull = false)
    private String name;

    @Column(name = "startdate", type = MySqlTypeConstant.DATETIME)
    private Date startdate;

    @Column(name = "enddate", type = MySqlTypeConstant.DATETIME)
    private Date enddate;

    @Column(name = "deptid", type = MySqlTypeConstant.VARCHAR)
    private String deptid;

    @Column(name = "createtime", type = MySqlTypeConstant.DATETIME)
    private Date createtime;

    @Column(name = "modifiedtime", type = MySqlTypeConstant.DATETIME)
    private Date modifiedtime;

    @Column(name = "dr",type = MySqlTypeConstant.INT)
    private Integer dr;

}
