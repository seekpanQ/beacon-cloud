package com.mashibing.test.mapper;

import com.mashibing.test.entity.ClientBalance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientBalanceMapper {


    @Select("SELECT * FROM client_balance WHERE client_id = #{clientId}")
    ClientBalance findByClientId(@Param("clientId") Long clientId);
}
