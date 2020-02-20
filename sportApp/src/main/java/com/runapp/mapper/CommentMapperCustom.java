package com.runapp.mapper;

import java.util.List;

import com.runapp.bean.Comment;
import com.runapp.bean.CommentCustom;



public interface CommentMapperCustom {
	
	List<Comment> selectCommentsPageByPostId(CommentCustom custom) throws Exception;
	
	void insertCommentGetCommentId(Comment record);  //返回评论id


}
