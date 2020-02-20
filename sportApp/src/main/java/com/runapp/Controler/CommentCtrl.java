package com.runapp.Controler;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.runapp.bean.Comment;
import com.runapp.bean.Post;
import com.runapp.service.CommentService;
import com.runapp.service.PostService;
import com.runapp.utils.ImgUtil;
import com.runapp.utils.ResultData;
import com.runapp.utils.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@RequestMapping(value="comment",produces="text/plain;charset=UTF-8")
public class CommentCtrl {
	
	@Resource
	private CommentService commentService;
	@Resource
	private PostService postService;

	/***
	 * 评论查询
	 */
	@RequestMapping(value = "getComments", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultData<List<Comment>> getComments(
			@RequestParam(value = "postid", required = true) Integer postid,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "num", required = true) Integer num
			) throws Exception {
		ResultData<List<Comment>> resultData = new ResultData<>();
		resultData = commentService.selectComments(page, 10, postid);
		resultData.setCode(200);
		resultData.setMsg("查询成功");
		return resultData;
		
	}
	
	/***
	 * 添加评论
	 */
	@RequestMapping(value = "createComment", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResultData<Comment> createComment(
			@RequestParam(value = "userid", required = false) int userid,
			@RequestParam(value = "useraccount", required = true) String useraccount,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "userphoto", required = true) String userphoto,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "postid", required = true) Integer postid,
			@RequestParam(value = "floor", required = true) Integer floor,
			HttpServletRequest request,
			@RequestParam(value="img",required = false)List<MultipartFile> img
			) throws Exception {
        Comment comment = new Comment();
        
        System.out.println("content----"+content);
        
        if(img != null){
			String path = "";
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject;
			int i = 1;
			for(MultipartFile f : img){
				jsonObject = new JSONObject();
				jsonObject.put("id", i);
				path = ImgUtil.saveImgInUserFolder(request, f, f.getOriginalFilename(), 
						"/upload/img/"+TimeUtil.getWeeksOneDate());
				jsonObject.put("path", path);
				jsonArray.add(jsonObject);
				i++;
			}
			System.out.println("图片路径"+jsonArray.toString());
			comment.setContentImg(jsonArray.toString());
		}
        
        //更新评论�?
        Post post = postService.selectByPrimaryKey(postid);
        String commentNumber = post.getCommentNum() ;
        int insertCommentNum = Integer.parseInt(commentNumber) +1;
        post.setCommentNum(insertCommentNum+"");
        postService.update(post);
        
        comment.setUserid(userid);
        comment.setUseraccount(useraccount);
        comment.setUsername(username);
        comment.setUserphoto(userphoto);
        comment.setContent(content);
        comment.setPostid(postid);
        comment.setFloor(insertCommentNum);
        comment.setCreateTime(TimeUtil.getCurrentTimeString());
		
		commentService.insert(comment);
		Comment returnComment = commentService.selectByPrimaryKey(comment.getId());
		
		ResultData<Comment> resultData = new ResultData<>();
		resultData.setData(returnComment);
		resultData.setCode(200);
		resultData.setMsg("评论成功");
		return resultData;
	}

}
