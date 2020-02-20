package com.runapp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.runapp.bean.Public;
import com.runapp.bean.PublicExample;

public interface PublicMapper {
    int countByExample(PublicExample example);

    int deleteByExample(PublicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Public record);

    int insertSelective(Public record);

    List<Public> selectByExample(PublicExample example);

    Public selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Public record, @Param("example") PublicExample example);

    int updateByExample(@Param("record") Public record, @Param("example") PublicExample example);

    int updateByPrimaryKeySelective(Public record);

    int updateByPrimaryKey(Public record);
}