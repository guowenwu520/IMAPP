package com.runapp.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.runapp.bean.Ad;
import com.runapp.bean.AdExample;
import com.runapp.bean.Village;

public interface AdMapper {
    int countByExample(AdExample example);

    int deleteByExample(AdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    List<Ad> selectByExample(AdExample example);

    Ad selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByExample(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

	ArrayList<Ad> getvillageSing(@Param("limit")int limit,@Param("status") int status,@Param("str") String str);

	void deletevillage(@Param("eid")String eid);

	int findTotalRecord(@Param("str")String str);
}