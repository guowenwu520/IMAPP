package com.runapp.service;

import java.util.List;

import com.runapp.bean.Comment;
import com.runapp.utils.ResultData;



public interface CommentService {


	ResultData<List<Comment>> selectComments(Integer page, Integer num, Integer postid) throws Exception;
	
	void insert(Comment record);  
	 
	Comment selectByPrimaryKey(Integer id);
	 
}
