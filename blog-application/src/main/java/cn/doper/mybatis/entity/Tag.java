package cn.doper.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_tag")
public class Tag {
    @TableId("id")
    private Long id;
    private Long blogId;
    private String tag;
    private Date createTime;
    private Date updateTime;

    public Tag(Long blogId, String tag) {
        this.blogId = blogId;
        this.tag = tag;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
