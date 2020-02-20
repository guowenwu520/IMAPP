package com.runapp.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.runapp.bean.Village;
import com.runapp.bean.VillageExample;

public interface VillageMapper {
    int countByExample(VillageExample example);

    int deleteByExample(VillageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Village record);

    int insertSelective(Village record);

    List<Village> selectByExample(VillageExample example);

    Village selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Village record, @Param("example") VillageExample example);

    int updateByExample(@Param("record") Village record, @Param("example") VillageExample example);

    int updateByPrimaryKeySelective(Village record);

    int updateByPrimaryKey(Village record);
////表格操作
	Village getvillage(@Param("villageid")String villageid);

	ArrayList<Village> getvillageSing(@Param("limit")int limit,@Param("status") int status,@Param("str") String str);

	void deletevillage(@Param("eid")String eid);

	int findTotalRecord(@Param("str")String str);

	void updateViller(@Param("admin") Village admin);
}