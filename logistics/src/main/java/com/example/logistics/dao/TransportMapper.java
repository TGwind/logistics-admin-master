package com.example.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.logistics.entity.Transport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TransportMapper extends BaseMapper<Transport> {
    @Select("select * from transport where pid=#{pid}")
    List<Transport> selectBypid(String pid);
}
