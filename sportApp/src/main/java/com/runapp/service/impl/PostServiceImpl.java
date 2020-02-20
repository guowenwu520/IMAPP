package com.runapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runapp.mapper.CommentMapper;
import com.runapp.mapper.CommentMapperCustom;
import com.runapp.mapper.PostMapper;
import com.runapp.mapper.PostMapperCustom;
import com.runapp.mapper.VillageMapper;
import com.runapp.bean.Comment;
import com.runapp.bean.CommentExample;
import com.runapp.bean.CommentExample.Criterion;
import com.runapp.bean.Post;
import com.runapp.bean.PostExample;
import com.runapp.bean.PostExample.Criteria;
import com.runapp.bean.Village;
import com.runapp.bean.CommentCustom;
import com.runapp.bean.Page;
import com.runapp.bean.PostCustom;
import com.runapp.service.CommentService;
import com.runapp.service.PostService;
import com.runapp.service.VillageService;
import com.runapp.utils.PageUtils;
import com.runapp.utils.ResultData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Transactional
@Service
public class PostServiceImpl implements PostService{

	@Resource
	private PostMapper postMapper;
	@Resource
	private PostMapperCustom postMapperCustom;


	public int deleteByPrimaryKey(Integer id) {
		return postMapper.deleteByPrimaryKey(id);
	}

	public int insert(Post record) {
		return postMapper.insert(record);
	}
	
	public int update(Post record){
		return postMapper.updateByPrimaryKey(record);
	}
		

	public Post selectByPrimaryKey(Integer id) {
		return postMapper.selectByPrimaryKey(id);
	}

	@Override
	public ResultData<List<Post>> selectPosts(Integer currentPage, Integer pageCount, Integer homeid) throws Exception {
        PostCustom custom=new PostCustom();
        custom.setVillageId(homeid);
		Page page;
		ResultData<List<Post>> resultData = new ResultData<>();
		
		if(currentPage>=2){
			page = PageUtils.createPage(pageCount, currentPage);
		}else{
			int count = countByHomeid(homeid);
			page = PageUtils.createPage(pageCount, count, currentPage);
			if(count == 0){
//				json.put("pageNum", count);
				resultData.setData(null);
			}else{
//				json.put("pageNum", page.getTotalPage());
				page = PageUtils.createPage(pageCount, currentPage);
			}
		}	
		custom.setPage(page);
		List<Post> list = postMapperCustom.selectPostpage(custom);
		resultData.setData(list);
		return resultData;
	}

	
	public int countByHomeid(Integer homeid) {
		PostExample postExample = new PostExample();
		Criteria criteria = postExample.createCriteria();
		criteria.andVillageIdEqualTo(homeid);
		return postMapper.countByExample(postExample);
	}
	
}
