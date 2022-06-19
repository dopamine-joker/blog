package cn.doper.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 7594452438841310431L;
    @TableId("id")
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String cover;
    private Integer viewCount;
    private Date createTime;
    private Date publishTime;
    private Date updateTime;
    @TableField(value = "is_top")
    private Boolean top;
    private Integer status;
}
