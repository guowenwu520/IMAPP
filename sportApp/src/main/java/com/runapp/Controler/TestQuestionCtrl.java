 package com.runapp.Controler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.runapp.bean.TestQuestion;
import com.runapp.bean.Comment;
import com.runapp.bean.FenYe;
import com.runapp.bean.ShiPing;
import com.runapp.bean.TestQuestion;
import com.runapp.bean.Ti;
import com.runapp.bean.Village;
import com.runapp.service.TestQuestionService;
import com.runapp.utils.ResultData;

@Controller
 @RequestMapping(value="testquestion",produces="text/plain;charset=UTF-8")
public class TestQuestionCtrl {
    
	@Autowired
	TestQuestionService testQuestionService;
	@Autowired
	com.runapp.mapper.TestQuestionMapper TestQuestionMapper;
	
	@RequestMapping(value = "getTestquestion", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getTestquestion() throws Exception {
		 ArrayList<TestQuestion> testQuestions=testQuestionService.getTestService();
		 Gson gson=new Gson();
		 return gson.toJson(testQuestions);
	}
	@RequestMapping(value = "getTi", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getTestquestion(@RequestParam(value = "testquestionsid", required = true) String testquestionsid) throws Exception {
		 ArrayList<Ti> testQuestions=testQuestionService.getTiTestService(testquestionsid);
		 Gson gson=new Gson();
		 return gson.toJson(testQuestions);
	}
	@RequestMapping(value = "getShiPings", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getShiPings() throws Exception {
		 ArrayList<ShiPing> testQuestions=testQuestionService.getShiPingService();
		 Gson gson=new Gson();
		 return gson.toJson(testQuestions);
	}
	
	 @RequestMapping(value="/addShiPing" ,method = RequestMethod.POST )
    public @ResponseBody String addShiPing(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "title",defaultValue = "0") String title,
            @RequestParam(value = "miaoshu",defaultValue = "0") String miaoshu,
            @RequestParam(value = "nicheng",defaultValue = "0") String nicheng,
            @RequestParam(value = "useraccount",defaultValue = "0") String useraccount) {
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
ShiPing shiPing=new ShiPing();
   shiPing.setMiaoshu(miaoshu);
   shiPing.setNicheng(nicheng);
   shiPing.setShiurl(originalFilename);
   shiPing.setTitle(title);
   shiPing.setUseraccount(useraccount);
   SimpleDateFormat s=new SimpleDateFormat("yyyy-HH-dd HH:mm");
   shiPing.setTimes(s.format(new Date()));
   shiPing.setShipingid(System.currentTimeMillis()+"");
    TestQuestionMapper.insertZShiping(shiPing);
        }
return "";
    }
	
	 @RequestMapping(value="/addShiJuan" ,method = RequestMethod.POST )
	    public @ResponseBody String TestQuestiondVoide(@RequestParam("file") MultipartFile file,
	            @RequestParam(value = "yuyan",defaultValue = "0") String yuyan,
	            @RequestParam(value = "miaoshi",defaultValue = "0") String miaoshi) {
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
    TestQuestion TestQuestion=new TestQuestion();
	    TestQuestion.setTestquestionsid(id);
	    TestQuestion.setImg(originalFilename);
	    TestQuestion.setYuyan(yuyan);
	    TestQuestion.setMiaoshi(miaoshi);
	    TestQuestionMapper.insert(TestQuestion);
	        }
  return "";
	    }
	 
	 @RequestMapping(value = "/addTi",method = RequestMethod.POST)
	    @ResponseBody
	    public  void addTi(@RequestBody Ti admin){
		   admin.setTiid(System.currentTimeMillis()+"");
		  TestQuestionMapper.addTiJuan(admin);
	    }
		@RequestMapping(value = "getTis", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  String getTis(@RequestParam("page")int pTestQuestione,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str,@RequestParam(value = "testquestionsid",defaultValue = "0") String testid){
	        FenYe fenYe=new FenYe((pTestQuestione-1)*limit,limit);
	        ArrayList<Ti> TestQuestionmins=TestQuestionMapper.getTis(fenYe.getLimit(),fenYe.getStatus(),str,testid);
	        Gson gson=new Gson();
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",0);
	        map.put("msg","");
	        map.put("count", TestQuestionMapper.findTotalTi(str,testid));
	        //  String str=gson.toJson(TestQuestionmins);
	        map.put("data",TestQuestionmins);
	        return gson.toJson(map);
	    }
		@RequestMapping(value = "deleteTi", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  void deleteTi(@RequestParam("tiid")String tiid){
			TestQuestionMapper.deleteTi(tiid);
	    }
		@RequestMapping(value = "getVillage", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  String getVillage(@RequestParam("page")int pTestQuestione,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
	        FenYe fenYe=new FenYe((pTestQuestione-1)*limit,limit);
	        ArrayList<TestQuestion> TestQuestionmins=TestQuestionMapper.getvillageSing(fenYe.getLimit(),fenYe.getStatus(),str);
	        Gson gson=new Gson();
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",0);
	        map.put("msg","");
	        map.put("count", TestQuestionMapper.findTotalRecord(str));
	        //  String str=gson.toJson(TestQuestionmins);
	        map.put("data",TestQuestionmins);
	        return gson.toJson(map);
	    }
		@RequestMapping(value = "deletevillage", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  void deletevillage(@RequestParam("testquestionsid")String testquestionsid){
			TestQuestionMapper.deletevillage(testquestionsid);
	    }
		@RequestMapping(value = "getVillage2", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  String getVillage2(@RequestParam("page")int pTestQuestione,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
	        FenYe fenYe=new FenYe((pTestQuestione-1)*limit,limit);
	        ArrayList<ShiPing> TestQuestionmins=TestQuestionMapper.getvillageSing2(fenYe.getLimit(),fenYe.getStatus(),str);
	        Gson gson=new Gson();
	        Map<String,Object> map=new HashMap<>();
	        map.put("code",0);
	        map.put("msg","");
	        map.put("count", TestQuestionMapper.findTotalRecord2(str));
	        //  String str=gson.toJson(TestQuestionmins);
	        map.put("data",TestQuestionmins);
	        return gson.toJson(map);
	    }
		@RequestMapping(value = "deletevillage2", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		 public  void deletevillage2(@RequestParam("shipingid")String shipingid){
			TestQuestionMapper.deletevillage2(shipingid);
	    }
}
