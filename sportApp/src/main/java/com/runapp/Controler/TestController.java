package com.runapp.Controler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.runapp.bean.*;
import com.runapp.service.UserService;
import com.runapp.service.VillageService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class TestController {
	@Autowired
	private VillageService villageService;
    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    @ResponseBody
    public  void test(@RequestParam(value = "id",defaultValue = "0") String ids,
                       @RequestParam(value = "name",defaultValue = "0") String name,
                       @RequestParam(value = "nickname",defaultValue = "0") String nickname,
                       @RequestParam(value = "pass",defaultValue = "0") String pass,
                       @RequestParam(value = "phone",defaultValue = "0") String phone,
                       @RequestParam(value = "urlimg",defaultValue = "0") String urlimg) {
      //  String id=


//        User user = new User();
//        user.setUserid(ids);
//        user.setMyname(name);
//        user.setNickname(nickname);
//        user.setPass(pass);
//        user.setPhone(phone);
//        user.setUrlimg(urlimg);
      //  userService.ceshi(user);

      //  jsonObject.put("title", "娴嬭瘯鎴愬姛" + user);
     //  return "index";
    }
    @RequestMapping(value = "/getUser"  , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  String getUser(@RequestParam(value = "name",defaultValue = "0") String name,
                      @RequestParam(value = "pass",defaultValue = "0") String pass) {
        //  String id=
       // JSONObject jsonObject = new JSONObject();

        User user = new User();
//        user.setMyname(name);
//        user.setPass(pass);
        User user2=userService.isUserAndPass(user);
         if (user2!=null){
             Gson  gson=new Gson();
            return gson.toJson(user2);
        }
        return "0";
    }
    @RequestMapping("/isUser")
    @ResponseBody
    public  String test(@RequestParam(value = "name",defaultValue = "0") String name,
                        @RequestParam(value = "pass",defaultValue = "0") String pass) {
        //  String id=
       // JSONObject jsonObject = new JSONObject();

        User user = new User();
//        user.setMyname(name);
//        user.setPass(pass);
        User user2=userService.isUserAndPass(user);
        User user1=userService.isUser(user);

        if(user1!=null&&user2!=null){
            return "1";
        }else if(user1==null){
            return "0";
        }else if (user2==null){
            return "2";
        }
        return "0";
    }
    @RequestMapping(value="/updateUser" ,method = RequestMethod.POST )
    public @ResponseBody String importPicFile1(
            @RequestParam("file") MultipartFile file1,
            @RequestParam(value = "id",defaultValue = "0") String ids,
    @RequestParam(value = "name",defaultValue = "0") String name,
    @RequestParam(value = "nickname",defaultValue = "0") String nickname,
    @RequestParam(value = "pass",defaultValue = "0") String pass,
    @RequestParam(value = "phone",defaultValue = "0") String phone) {

         String str;
        if (file1.isEmpty()) {
            str="涓婁紶鏂囦欢涓嶈兘涓虹┖";
        } else {
            String originalFilename = file1.getOriginalFilename();

            try {
                //鍒涘缓瑕佷笂浼犵殑璺緞
                File fdir = new File("D:/file");
                if (!fdir.exists()) {
                    fdir.mkdirs();
                }
                //鏂囦欢涓婁紶鍒拌矾寰勪笅
                FileUtils.copyInputStreamToFile(file1.getInputStream(), new File(fdir, originalFilename));
                //coding

                User user = new User();
//                user.setMyname(name);
//                user.setPass(pass);
//                user.setUrlimg(originalFilename);
//                user.setPhone(phone);
//                user.setUserid(ids);
//                user.setNickname(nickname);
                userService.updateUser(user);
                Gson  gson=new Gson();
                str= gson.toJson(user);
            } catch (Exception e) {
                str=e.getMessage();
            }
        }
        return str;
    }
    @RequestMapping("/Imgs")
    @ResponseBody
    public void showEInvoice(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "name",defaultValue = "0") String name){
        FileInputStream fis = null;
        OutputStream os = null;
        String filepath = "D:/file/"+name;     //path鏄綘鏈嶅姟鍣ㄤ笂鍥剧墖鐨勭粷瀵硅矾寰�
        File file = new File(filepath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                if(name.contains("img")) {
                    response.setContentType("image/png");
                }
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("/playMp4")
    @ResponseBody
    public void playMp4(HttpServletResponse response,@RequestParam(value = "name",defaultValue = "0") String name) throws Exception {

        // TODO 娴嬭瘯鑱旇皟缁撴潫鍚庨渶鍒犻櫎
        String filepath = "D:/file/"+name;     //path鏄綘鏈嶅姟鍣ㄤ笂鍥剧墖鐨勭粷瀵硅矾寰�
        try {// 瑙ｅ瘑杩囧悗鐨勪复鏃舵枃浠惰矾寰�
            File file = new File(filepath);
                     long size = file.length();
                    byte[] temp = new byte[(int) size];
                      FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(temp, 0, (int) size);
            inputStream.close();
            byte[] data = temp;
            String diskfilename = "final.mp4";
            response.setContentType("video/mp4");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"" );
            System.out.println("data.length " + data.length);
            response.setContentLength(data.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(data.length-1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            OutputStream os = response.getOutputStream();

            os.write(data);
            //鍏堝０鏄庣殑娴佸悗鍏虫帀锛�
            os.flush();
            os.close();
            inputStream.close();

        }catch (Exception e){
         }finally {
          }

    }
    @RequestMapping(value="/updates" ,method = RequestMethod.POST )
    public @ResponseBody String addDate(@RequestParam("file") MultipartFile file,@RequestParam("id")String ids) {
        String str=null;
        if (file.isEmpty()) {
            str="涓婁紶鏂囦欢涓嶈兘涓虹┖";
        } else {
            String originalFilename = file.getOriginalFilename();
            Imgs imgs = new Imgs(System.currentTimeMillis() + "", originalFilename, ids);
            userService.addIMgs(imgs);
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
        }
        return str;
    }
        @RequestMapping(value="/addVoide" ,method = RequestMethod.POST )
    public @ResponseBody String addVoide(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "time",defaultValue = "0") String times,
            @RequestParam(value = "titile",defaultValue = "0") String title,
            @RequestParam(value = "address",defaultValue = "0") String address,
            @RequestParam(value = "xiang",defaultValue = "0") String xiang,
                                         @RequestParam(value = "id",defaultValue = "0") String ids,
            @RequestParam(value = "classid",defaultValue = "0") String classis,
            @RequestParam(value = "userid",defaultValue = "0") String userid,
            @RequestParam(value = "lat",defaultValue = "0") String lat,
                                         @RequestParam(value = "phone",defaultValue = "0") String phone,
            @RequestParam(value = "lon",defaultValue = "0") String lon) {
        String str=null;
        if (file.isEmpty()) {
            str="涓婁紶鏂囦欢涓嶈兘涓虹┖";
        } else {
            String originalFilename = file.getOriginalFilename();
            Imgs imgs = new Imgs(System.currentTimeMillis() + "", originalFilename, ids);
            userService.addIMgs(imgs);
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
        }

if(!address.equals("0")) {
    SportsDetail sportsDetail = new SportsDetail();
    sportsDetail.setAddress(address);
    sportsDetail.setBriefIntroduction(xiang);
    sportsDetail.setClassid(classis);
    sportsDetail.setSportsDetailid(ids);
    sportsDetail.setLat(lat);
    sportsDetail.setLon(lon);
    sportsDetail.setTiem(times);
    sportsDetail.setUserid(userid);
    sportsDetail.setTitle(title);
    sportsDetail.setPhone(phone);
    userService.addSportDetail(sportsDetail);
}
        return str;
    }
    @RequestMapping("/getRunClass")
    @ResponseBody
   public  String getRunClass(){
        ArrayList<RunClass> datas=userService.getRunClass();
        Gson gson=new Gson();
        return  gson.toJson(datas);
   }

    @RequestMapping("/getSportsDetailUser")
    @ResponseBody
    public  String getSportsDtailUser(@RequestParam("id")String id){
        ArrayList<SportsDetail> datas=userService.getSportsDtailUser(id);
        for(int i=0;i<datas.size();i++){
            ArrayList<Imgs> img=userService.getIMGs(datas.get(i).getSportsDetailid());
            datas.get(i).setStrings(img);
        }
        Gson gson=new Gson();
        return  gson.toJson(datas);
    }
    @RequestMapping("/getSportsDetail")
    @ResponseBody
    public  String getSportsDetail(){
        ArrayList<SportsDetail> datas=userService.getSportDetails();
        for(int i=0;i<datas.size();i++){
            ArrayList<Imgs> img=userService.getIMGs(datas.get(i).getSportsDetailid());
            datas.get(i).setStrings(img);
        }
        Gson gson=new Gson();
        return  gson.toJson(datas);
    }

   //鍚庡彴
   @RequestMapping("/addZhuAfter")
   public  ModelAndView getAfter(ModelAndView mv){
       mv.setViewName("index");
       return mv;
   }
    @RequestMapping("/ZhongZhuan")
    public  ModelAndView ZhongZhuan(ModelAndView mv,@RequestParam(value = "key",defaultValue = "0") String key){
        mv.setViewName(key);
        return mv;
    }
    @RequestMapping("/getImgss")
    public  String getImgss(@RequestParam(value = "id",defaultValue = "0") String id){
       ArrayList<Imgs> dat=userService.getIMGs(id);
        return new Gson().toJson(dat);
    }

    @RequestMapping("/getAdmin")
    public  String getAdmin(@RequestParam(value = "name",defaultValue = "0") String name,@RequestParam(value = "pass",defaultValue = "0") String pass){
    	  Admin admin=userService.getAdminSing(name,pass);
    	  if(admin!=null) {
    		  Gson gson=new Gson();
    	        return  gson.toJson(admin);
    	  }
        return "1"+name+" "+pass;
    }
    
    @RequestMapping("/ZhongZhuanId")
    public  ModelAndView ZhongZhuanId(ModelAndView mv,@RequestParam(value = "key",defaultValue = "0") String key,@RequestParam(value = "adminid",defaultValue = "0") String adminid,@RequestParam(value = "classtable",defaultValue = "0") String classtable){

        mv.setViewName(key);
        Map<String ,String> map=new HashMap<>();
          switch (classtable){
            case "admin":
            	//System.out.println(id);
                Admin admin=userService.getAdmin(adminid);
                map.put("adminid",admin.getAdminid());
                map.put("email",admin.getEmail());
                map.put("phone",admin.getPhone());
                map.put("username",admin.getUsername());
                mv.addAllObjects(map);
                break;
            case "runclass":
                RunClass runClass=userService.getClasssing(adminid);
                map.put("id",runClass.getRunClassid());
                map.put("classname",runClass.getClassname());
                map.put("classs",runClass.getClasss());
                map.put("classnumber",runClass.getClassnumber());
                mv.addAllObjects(map);
                break;
            case "village":
                Village village=villageService.getvillage(adminid);
                map.put("id",village.getId()+"");
                map.put("title",village.getTitle());
                map.put("villageDesc",village.getVillageDesc());
                map.put("district",village.getDistrict());
                map.put("createTime",village.getCreateTime());
                  mv.addAllObjects(map);
                break;
            case "addds":
            	    map.put("username",adminid);
                  mv.addAllObjects(map);
            	break;
            case "addshiti":
            	 map.put("shitiid",adminid);
                 mv.addAllObjects(map);
            default:
                break;
        }
        return mv;
    }
    @RequestMapping("/deleteAdmin")
    public  void deleteAdmin(@RequestParam(value = "adminid",defaultValue = "0") String adminid){
        userService.deleAdmin(adminid);
    }
    @RequestMapping("/deleteUser")
    public  void deleteUser(@RequestParam(value = "deleteUserid",defaultValue = "0") String deleteUserid){
        userService.deleteUser(deleteUserid);
    }
    @RequestMapping("/deleteSportsDetail")
    public  void deleteSportsDetail(@RequestParam(value = "id",defaultValue = "0") String id){
        userService.deleteSportsDetail(id);
    }
    @RequestMapping("/deleteClass")
    public  void deleteClass(@RequestParam(value = "id",defaultValue = "0") String id){
        userService.deleteClass(id);
    }
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    @ResponseBody
    public  String addAdmin(@RequestBody Admin admin){
       // Admin admin=new Admin();
        admin.setAdminid(System.currentTimeMillis()+"");
//        admin.setEmail(object.get("email").getAsString());
//        admin.setPass(object.get("pass").getAsString());
//        admin.setPhone(object.get("phone").getAsString());
//        admin.setUsername(object.get("username").getAsString());
       userService.addAdmin(admin);
        return admin.toString();
    }
    @RequestMapping(value = "/addClass",method = RequestMethod.POST)
    @ResponseBody
    public  String addClass(@RequestBody RunClass admin){
         admin.setRunClassid(System.currentTimeMillis()+"");
      userService.addClass(admin);
        return admin.toString();
    }

    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    @ResponseBody
    public  void updateAdmin(@RequestBody Admin admin){
        userService.updateAdmin(admin);
    }
    @RequestMapping(value = "/updateClass",method = RequestMethod.POST)
    @ResponseBody
    public  void updateClass(@RequestBody RunClass admin){
        userService.updateRunClass(admin);
    }

    @RequestMapping(value = "/getAdmins")
    @ResponseBody
    public  String getAdmins(@RequestParam("page")int pade,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
        FenYe fenYe=new FenYe((pade-1)*limit,limit);
        ArrayList<Admin> admins=userService.getAdmins(fenYe,str);
        Gson gson=new Gson();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count", userService.findTotalRecord(str));
        //  String str=gson.toJson(admins);
        map.put("data",admins);
        return gson.toJson(map);
    }
    @RequestMapping(value = "/getSportDeails")
    @ResponseBody
    public  String getSportDeails(@RequestParam("page")int pade,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
        FenYe fenYe=new FenYe((pade-1)*limit,limit);
        ArrayList<SportsDetail> admins=userService.getSportDeails(fenYe,str);
        Gson gson=new Gson();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count", userService.findTotalRecordSport(str));
        //  String str=gson.toJson(admins);
        map.put("data",admins);
        return gson.toJson(map);
    }

    @RequestMapping(value = "/getClasss")
    @ResponseBody
    public  String getClasss(@RequestParam("page")int pade,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
        FenYe fenYe=new FenYe((pade-1)*limit,limit);
        ArrayList<RunClass> admins=userService.getClasss(fenYe,str);
        Gson gson=new Gson();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count", userService.findTotalClassRecord(str));
        //  String str=gson.toJson(admins);
        map.put("data",admins);
        return gson.toJson(map);
    }
    @RequestMapping(value = "/getUsers")
    @ResponseBody
    public  String getUsers(@RequestParam("page")int pade,@RequestParam("limit")int limit,@RequestParam(value = "keyword",defaultValue = "8976087") String str){
        FenYe fenYe=new FenYe((pade-1)*limit,limit);
        ArrayList<User> admins=userService.getUsers(fenYe,str);
        Gson gson=new Gson();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count", userService.findTotalRecordUser(str));
        //  String str=gson.toJson(admins);
        map.put("data",admins);
        return gson.toJson(map);
    }
    @RequestMapping(value = "/addCommon")
    @ResponseBody
    public  void addCommon(@RequestParam("id")String id,
                             @RequestParam("sprrtsid")String sprrtsid,
                             @RequestParam("userid")String userid,
                             @RequestParam("imgurl")String imgurl,
                             @RequestParam("name")String name,
                             @RequestParam("time")String time,
                             @RequestParam("msg")String msg){
    //  Comment comment=new Comment(id,sprrtsid,userid,msg,time,name,imgurl);
    //   userService.insertCommon(comment);
    }
    @RequestMapping(value = "/getCommoent")
    @ResponseBody
    public  String getCommoent( @RequestParam("sprrtsid")String sprrtsid){
        ArrayList<Comment> datas=userService.getallCommon(sprrtsid);
        Gson gson=new Gson();
        return gson.toJson(datas);
    }

    @RequestMapping(value = "/addCollection")
    @ResponseBody
    public  void addCollection(@RequestParam("id")String id,
                               @RequestParam("sprrtsid")String sprrtsid,
                               @RequestParam("userid")String userid){
        Collection comment=new Collection(id,userid,sprrtsid);
        userService.insertCollection(comment);
    }
    @RequestMapping(value = "/remoteCollection")
    @ResponseBody
    public  void remoteCollection(@RequestParam("id")String id,
                               @RequestParam("sprrtsid")String sprrtsid,
                               @RequestParam("userid")String userid){
        Collection comment=new Collection(id,userid,sprrtsid);
        userService.remoteCollection(comment);
    }
    @RequestMapping(value = "/isCollent")
    @ResponseBody
    public  String  isCollent(@RequestParam("id")String id,
                                  @RequestParam("sprrtsid")String sprrtsid,
                                  @RequestParam("userid")String userid){
        Collection comment=new Collection(id,userid,sprrtsid);
        String str="0";
        Collection collection=userService.isCollent(comment);
        if(collection!=null){
            str="1";
        }
        return  str;
    }
    @RequestMapping("/getCollteions")
    @ResponseBody
    public  String getCollteions(@RequestParam("id")String id){
        ArrayList<Collection> datas=userService.getSportDetailsColltion(id);
       ArrayList<SportsDetail> data=new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            data.add(userService.getSportDetailSing(datas.get(i).getSprrtsid()));
        }
        for(int i=0;i<data.size();i++){
            ArrayList<Imgs> img=userService.getIMGs(data.get(i).getSportsDetailid());
            data.get(i).setStrings(img);
        }
        Gson gson=new Gson();
        return  gson.toJson(data);
    }
    @RequestMapping("/getSprtsDetailLike")
    @ResponseBody
    public  String getSprtsDetailLike(@RequestParam("str")String str){
        ArrayList<SportsDetail> datas=userService.getSprtsDetailLike(str);
        for(int i=0;i<datas.size();i++){
            ArrayList<Imgs> img=userService.getIMGs(datas.get(i).getSportsDetailid());
            datas.get(i).setStrings(img);
        }
        Gson gson=new Gson();
        return  gson.toJson(datas);
    }

//    @RequestMapping(value="/download",method= RequestMethod.GET)
//    public void download(@RequestParam(value="filename")String filename,HttpServletResponse response) throws IOException {
//        //妯℃嫙鏂囦欢锛宮yfile.txt涓洪渶瑕佷笅杞界殑鏂囦欢
//        String path = "D:\\file"+"\\"+filename;
//        //鑾峰彇杈撳叆娴�
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
//        //杞爜锛屽厤寰楁枃浠跺悕涓枃涔辩爜
//        filename = URLEncoder.encode(filename,"UTF-8");
//        //璁剧疆鏂囦欢涓嬭浇澶�
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
//        //1.璁剧疆鏂囦欢ContentType绫诲瀷锛岃繖鏍疯缃紝浼氳嚜鍔ㄥ垽鏂笅杞芥枃浠剁被鍨�
//        response.setContentType("multipart/form-data");
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//        int len = 0;
//        while((len = bis.read()) != -1){
//            out.write(len);
//            out.flush();
//        }
//        out.close();
//    }
    }