package cn.doper.mybatis.service.impl;

import cn.doper.mybatis.entity.Tag;
import cn.doper.mybatis.mapper.BlogMapper;
import cn.doper.mybatis.mapper.TagMapper;
import cn.doper.mybatis.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public boolean insertTags(List<Tag> tags) {
        return this.saveBatch(tags);
    }

    @Override
    public boolean deleteTags(Long blogId) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getBlogId, blogId);
        return this.remove(queryWrapper);
    }

}
