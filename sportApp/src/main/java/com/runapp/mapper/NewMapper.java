package com.runapp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.runapp.bean.New;
import com.runapp.bean.NewExample;

public interface NewMapper {
    int countByExample(NewExample example);

    int deleteByExample(NewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(New record);

    int insertSelective(New record);

    List<New> selectByExample(NewExample example);

    New selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") New record, @Param("example") NewExample example);

    int updateByExample(@Param("record") New record, @Param("example") NewExample example);

    int updateByPrimaryKeySelective(New record);

    int updateByPrimaryKey(New record);
}