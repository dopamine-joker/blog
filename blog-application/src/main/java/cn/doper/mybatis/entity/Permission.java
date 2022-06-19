package cn.doper.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -5033378006775460084L;
    private Integer id;
    private String name;
    private String value;
    private String icon;
    private String uri;
    private Integer status;
    private Date timestamp;

}
