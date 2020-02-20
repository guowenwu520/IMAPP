package com.runapp.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.runapp.bean.Ad;
import com.runapp.bean.ShiPing;
import com.runapp.bean.TestQuestion;
import com.runapp.bean.Ti;
import com.runapp.bean.Village;

public interface TestQuestionMapper {

	ArrayList<TestQuestion> getTestQuestions();

	ArrayList<Ti> getTiTestions(@Param("testquestionsid")String testquestionsid);

	ArrayList<ShiPing> getShiPings();
	ArrayList<TestQuestion> getvillageSing(@Param("limit")int limit,@Param("status") int status,@Param("str") String str);

	void deletevillage(@Param("eid")String eid);
	ArrayList<ShiPing> getvillageSing2(@Param("limit")int limit,@Param("status") int status,@Param("str") String str);

	void deletevillage2(@Param("eid")String eid);
	 int insert(TestQuestion record);
	 int insertZShiping(ShiPing record);
		ArrayList<Ti> getTis(@Param("limit")int limit,@Param("status") int status,@Param("str") String str,@Param("testid") String testid);

		void deleteTi(@Param("eid")String eid);
		
	int findTotalRecord(@Param("str")String str);
	int findTotalRecord2(@Param("str")String str);
	int findTotalTi(@Param("str")String str,@Param("testid") String testid);
	
	void addTiJuan(Ti ti);
}
