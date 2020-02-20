package com.runapp.Controler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.runapp.bean.Ad;
import com.runapp.bean.AdExample;
import com.runapp.bean.Admin;
import com.runapp.bean.FenYe;
import com.runapp.bean.Imgs;
import com.runapp.bean.SportsDetail;
import com.runapp.bean.Village;
import com.runapp.mapper.AdMapper;
import com.runapp.utils.ResultData;



/**
 * 轮播广告
 * @author luqinmao
 *
 */
@Controller
@RequestMapping(value="ad",produces="text/plain;charset=UTF-8")
public class AdCtrl {

	@Resource
	private AdMapper adMapper;

	/***
	 * 获取广告列表
	 */
	@RequestMapping(value = "getAds", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@ResponseBody
	public ResultData<List<Ad>> getAds() throws Exception {
		List<Ad> ads = adMapper.selectByExample(new AdExample());
		ResultData<List<Ad>> resultData = new ResultData<>();
		resultData.setData(ads);
		resultData.setCode(200);
		resultData.setMsg("请求成功");
		return resultData;
	}
	
	  @RequestMapping(value="/addAdmin" ,method = RequestMethod.POST )
	    public @ResponseBody String addVoide(@RequestParam("file") MultipartFile file,
	            @RequestParam(value = "title",defaultValue = "0") String title,
	            @RequestParam(value = "content",defaultValue = "0") String content,
	            @RequestParam(value = "url",defaultValue = "0") String url) {
	        String id=System.currentTimeMillis()+"";
	        String str="";
	        if (file.isEmpty()) {
	            str="哈哈";
	        } else {
	            String originalFilename = file.getOriginalFilename();
	           try {
	                //鍒涘缓瑕佷笂浼犵殑璺緞
	                File fdir = new File("D:/file");
	                if (!fdir.exists()) {
	                    fdir.mkdirs();
	                }
	                //鏂囦欢涓婁紶鍒拌矾寰勪笅
	                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fdir, originalFilename));
	                //coding
	            } catch (Exception e) {
	                str = e.getMessage();
	            }
       Ad ad=new Ad();
	    ad.setId(id);
	    ad.setContent(content);
	    ad.setImg(originalFilename);
	    ad.setUrl(url);
	    ad.setTitle(title);
		 adMapper.insert(ad);
	        }
     return "";
	    }
		@RequestMapping(value = "getVillage", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  String getVillage(@RequestParam("page")int pade,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
	        FenYe fenYe=new FenYe((pade-1)*limit,limit);
	        ArrayList<Ad> admins=adMapper.getvillageSing(fenYe.getLimit(),fenYe.getStatus(),str);
	        Gson gson=new Gson();
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",0);
	        map.put("msg","");
	        map.put("count", adMapper.findTotalRecord(str));
	        //  String str=gson.toJson(admins);
	        map.put("data",admins);
	        return gson.toJson(map);
	    }
		@RequestMapping(value = "deletevillage", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  void deletevillage(@RequestParam("id")String id){
	        adMapper.deletevillage(id);
	    }
	
}
