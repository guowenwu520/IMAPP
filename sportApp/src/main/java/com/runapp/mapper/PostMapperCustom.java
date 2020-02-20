package com.runapp.mapper;

import java.util.List;

import com.runapp.bean.Post;
import com.runapp.bean.PostCustom;



public interface PostMapperCustom {
	
	List<Post> selectPostpage(PostCustom custom) throws Exception;

}
