package com.mashibing.test.mapper;

import com.mashibing.test.entity.ClientTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientTemplateMapper {

    @Select("SELECT * FROM client_template WHERE sign_id = #{signId}")
    List<ClientTemplate> findBySignId(@Param("signId") Long signId);
}
