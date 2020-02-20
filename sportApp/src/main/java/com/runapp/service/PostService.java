package com.runapp.service;


import java.util.List;

import com.runapp.bean.Post;
import com.runapp.utils.ResultData;


public interface PostService {

	 int insert(Post record);
	 
	 int update(Post record);

	 ResultData<List<Post>> selectPosts(Integer page, Integer num, Integer homeid) throws Exception;

	 Post selectByPrimaryKey(Integer id);
	 
	 

	
}
