package com.runapp.service;

import com.runapp.bean.*;
import com.runapp.utils.ResultData;

import java.util.ArrayList;

public interface UserService {
    void ceshi(User tpmTestLog);

    User isUser(User user);

    User isUserAndPass(User user);

    void updateUser(User user);

    ArrayList<RunClass> getRunClass();

    void addAdmin(Admin admin);

    ArrayList<Admin> getAdmins(FenYe fenYe, String str);

    int findTotalRecord(String str);

    void deleAdmin(String adminid);

    void updateAdmin(Admin admin);

    Admin getAdmin(String id);

    ArrayList<RunClass> getClasss(FenYe fenYe, String str);

    int findTotalClassRecord(String str);

    void deleteClass(String deletClassid);

    void addClass(RunClass admin);

    RunClass getClasssing(String id);

    void updateRunClass(RunClass admin);

    ArrayList<SportsDetail> getSportDetails();

    ArrayList<Imgs> getIMGs(String getIMGSid);

    void addSportDetail(SportsDetail sportsDetail);

    void addIMgs(Imgs imgs);

    void insertCommon(Comment comment);

    ArrayList<Comment> getallCommon(String sprrtsid);

    ArrayList<SportsDetail> getSportsDtailUser(String id);

    Collection isCollent(Collection comment);

    void remoteCollection(Collection comment);

    void insertCollection(Collection comment);

    ArrayList<Collection> getSportDetailsColltion(String id);

    SportsDetail getSportDetailSing(String sprrtsid);

    ArrayList<SportsDetail> getSprtsDetailLike(String str);

    ArrayList<User> getUsers(FenYe fenYe, String str);

    void deleteUser(String deleteUserid);

    int findTotalRecordUser(String str);

    void deleteSportsDetail(String deleteSportsDetailid);

    ArrayList<SportsDetail> getSportDeails(FenYe fenYe, String str);

    int findTotalRecordSport(String str);

	/**
	 * 妫�鏌ョ敤鎴峰悕鏄惁瀛樺湪
	 * @param username 鐢ㄦ埛鍚�
	 * @return
	 */
	ResultData<User> checkUsername(String username) throws Exception;

	/**
	 * 鐧诲綍
	 * @param session
	 * @param username 鐢ㄦ埛鍚�
	 * @param password 瀵嗙爜
	 * @param checkCode 楠岃瘉鐮�
	 * @return
	 * @throws Exception
	 */
	ResultData<LoginReturnData> login(String accid, String password) throws Exception;

	/**
	 * 娉ㄥ唽
	 * @param session
	 * @param username 鐢ㄦ埛鍚�
	 * @param password 瀵嗙爜
	 * @param nickName 鏄电О
	 * @return
	 */
	ResultData<User> register(int homeid,String phone, String password, String accid,String username, String userphoto, String sex, String birthday,
			String address, String describe) throws Exception;

	/**
	 * 鍒ゆ柇涔″惂鍙锋槸鍚﹀凡缁忚娉ㄥ唽浜�
	 * @param accid
	 * @return
	 */
	Boolean isAlreadyRegistered(String accid);
	
	int update(User user);

	Admin getAdminSing(String name, String pass);
}
