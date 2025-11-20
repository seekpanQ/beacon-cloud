package com.mashibing.test.mapper;

import com.mashibing.test.entity.ClientSign;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ClientSignMapper {

    @Select(value = "select * from client_sign where client_id = #{clientId}")
    List<ClientSign> findByClientId(@RequestParam(value = "clientId") Long clientId);
}
