package com.mashibing.test.mapper;

import com.mashibing.test.entity.Channel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChannelMapper {

    @Select("select * from channel where is_delete = 0")
    List<Channel> findAll();
}
