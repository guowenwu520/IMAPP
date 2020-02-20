package com.runapp.mapper;

import com.runapp.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @TpmTestLogMapper
 * @Mapper
 * @version : Ver 1.0
 */
public interface UsertMapper {

    void insertabb(User tpmTestLog);

    User isUser(User user);

    User isUserAndPass(User user);

    void upDateUser(User user);

    ArrayList<RunClass> getUserClass();

    void addAdmin(Admin admin);


    int findAll(@Param("str")String str);

    void deletAdmin(@Param("adminid")String adminid);

    void updateAdmin(Admin admin);

    ArrayList<Admin> getAdmins(@Param("limit")int limit, @Param("status")int status, @Param("str") String str);

    Admin getAdmin(@Param("adminid") String adminid);


    ArrayList<RunClass> getClasss(@Param("limit")int limit,@Param("status") int status, @Param("str")String str);

    int findClassAll(@Param("str")String str);

    void deletClass(@Param("deletClassid")String deletClassid);

    void addClass(RunClass runClass);

    RunClass getClasssing(@Param("ids")String ids);

    void updateClass(RunClass admin);

    ArrayList<SportsDetail> getSportDetails();

    ArrayList<Imgs> getIMGS(@Param("getIMGSid")String getIMGSid);

    void addSportDetail(SportsDetail sportsDetail);

    void addImgs(Imgs imgs);

    void insertComment(Comment comment);

    ArrayList<Comment> getComment(@Param("sprrtsid") String sprrtsid);

    ArrayList<SportsDetail> getSportDetailsUser(@Param("getSportDetailsUserid") String getSportDetailsUserid);

    void insertCollection(Collection comment);

    void remoteCollection(Collection comment);

    Collection isCollent(Collection comment);

    ArrayList<Collection> getSportDetailsColltion(@Param("getSportDetailsColltionid") String getSportDetailsColltionid);

    SportsDetail getSportDetailSing(@Param("sprrtsid")String sprrtsid);

    ArrayList<SportsDetail> getSportDetailsLike(@Param("str") String str);

    //ArrayList<User> getUsers(FenYe fenYe, String str);

    ArrayList<User> getUsers(@Param("limit") int limit, @Param("status") int status, @Param("str") String str);

    void deleteUser(@Param("deleteUserid") String deleteUserid);

    int findUserAll(@Param("str") String str);

    void deleteSportDetail(@Param("deleteSportDetailid")String deleteSportDetailid);

    ArrayList<SportsDetail> getSportDetailsy(@Param("limit") int limit, @Param("status") int status, @Param("str") String str);

    int findSportAll(@Param("str") String str);
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	Admin getAdminSing(@Param("username")String username, @Param("pass")String pass);
}