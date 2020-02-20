package com.runapp.service.impl;

import com.runapp.bean.*;
import com.runapp.bean.UserExample.Criteria;
import com.runapp.mapper.FriendMapper;
import com.runapp.mapper.UsertMapper;
import com.runapp.service.UserService;
import com.runapp.utils.ResultData;
import com.runapp.utils.StringUtils;
import com.runapp.utils.TimeUtil;
import com.runapp.yunxin.MainTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @TpmTestLogServiceImpl
 * @ServiceImpl
 * @version : Ver 1.0
 */
@Service("TpmTestLogService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsertMapper tpmTestLogMapper;

    @Override
    public void ceshi(User tpmTestLog) {

        tpmTestLogMapper.insertabb(tpmTestLog);
    }

    @Override
    public User isUser(User user) {
        return tpmTestLogMapper.isUser(user);
    }

    @Override
    public User isUserAndPass(User user) {
        return tpmTestLogMapper.isUserAndPass(user);
    }

    @Override
    public void updateUser(User user) {
         tpmTestLogMapper.upDateUser(user);
    }

    @Override
    public ArrayList<RunClass> getRunClass() {
        return tpmTestLogMapper.getUserClass();
    }

    @Override
    public void addAdmin(Admin admin) {
        tpmTestLogMapper.addAdmin(admin);
    }

    @Override
    public ArrayList<Admin> getAdmins(FenYe fenYe, String str) {
        return tpmTestLogMapper.getAdmins(fenYe.getLimit(),fenYe.getStatus(),str);
    }

    @Override
    public int findTotalRecord(String str) {
        return tpmTestLogMapper.findAll(str);
    }

    @Override
    public void deleAdmin(String adminid) {
        tpmTestLogMapper.deletAdmin(adminid);
    }

    @Override
    public void updateAdmin(Admin admin) {
        tpmTestLogMapper.updateAdmin(admin);
    }

    @Override
    public Admin getAdmin(String id) {
        return tpmTestLogMapper.getAdmin(id);
    }

    @Override
    public ArrayList<RunClass> getClasss(FenYe fenYe, String str) {
        return tpmTestLogMapper.getClasss(fenYe.getLimit(),fenYe.getStatus(),str);
    }

    @Override
    public void deleteClass(String deletClassid) {
        tpmTestLogMapper.deletClass(deletClassid);
    }

    @Override
    public void addClass(RunClass runClass) {
        tpmTestLogMapper.addClass(runClass);
    }

    @Override
    public RunClass getClasssing(String id) {
        return tpmTestLogMapper.getClasssing(id);
    }

    @Override
    public void updateRunClass(RunClass admin) {
        tpmTestLogMapper.updateClass(admin);
    }

    @Override
    public ArrayList<SportsDetail> getSportDetails() {
        return tpmTestLogMapper.getSportDetails();
    }

    @Override
    public ArrayList<Imgs> getIMGs(String getIMGSid) {
        return tpmTestLogMapper.getIMGS(getIMGSid);
    }

    @Override
    public void addSportDetail(SportsDetail sportsDetail) {
     tpmTestLogMapper.addSportDetail(sportsDetail);
    }

    @Override
    public void addIMgs(Imgs imgs) {
        tpmTestLogMapper.addImgs(imgs);
    }

    @Override
    public void insertCommon(Comment comment) {
        tpmTestLogMapper.insertComment(comment);
    }

    @Override
    public ArrayList<Comment> getallCommon(String sprrtsid) {
        return tpmTestLogMapper.getComment(sprrtsid);
    }

    @Override
    public ArrayList<SportsDetail> getSportsDtailUser(String id) {
        return tpmTestLogMapper.getSportDetailsUser(id);
    }

    @Override
    public Collection isCollent(Collection comment) {
        return tpmTestLogMapper.isCollent(comment);
    }

    @Override
    public void remoteCollection(Collection comment) {
           tpmTestLogMapper.remoteCollection(comment);
    }

    @Override
    public void insertCollection(Collection comment) {
        tpmTestLogMapper.insertCollection(comment);
    }

    @Override
    public ArrayList<Collection> getSportDetailsColltion(String id) {
        return tpmTestLogMapper.getSportDetailsColltion(id);
    }

    @Override
    public SportsDetail getSportDetailSing(String sprrtsid) {
        return tpmTestLogMapper.getSportDetailSing(sprrtsid);
    }

    @Override
    public ArrayList<SportsDetail> getSprtsDetailLike(String str) {
        return tpmTestLogMapper.getSportDetailsLike(str);
    }

    @Override
    public ArrayList<User> getUsers(FenYe fenYe, String str) {
        return tpmTestLogMapper.getUsers(fenYe.getLimit(),fenYe.getStatus(),str);
    }

    @Override
    public void deleteUser(String deleteUserid) {
        tpmTestLogMapper.deleteUser(deleteUserid);
    }

    @Override
    public int findTotalRecordUser(String str) {
        return tpmTestLogMapper.findUserAll(str);
    }

    @Override
    public void deleteSportsDetail(String deleteSportsDetailid) {
        tpmTestLogMapper.deleteSportDetail(deleteSportsDetailid);
    }

    @Override
    public ArrayList<SportsDetail> getSportDeails(FenYe fenYe, String str) {
        return tpmTestLogMapper.getSportDetailsy(fenYe.getLimit(),fenYe.getStatus(),str);
    }

    @Override
    public int findTotalRecordSport(String str) {
        return tpmTestLogMapper.findSportAll(str);
    }

    @Override
    public int findTotalClassRecord(String str) {
        return tpmTestLogMapper.findClassAll(str);
    }
    @Resource
	private UsertMapper userMapper;
	@Resource
	private FriendMapper friendMapper;

	@Override
	public ResultData<User> checkUsername(String username) throws Exception {
		ResultData<User> resultData = new ResultData<>();
		User user = getUser(username);
		if (user == null) {
			resultData.setCode(0);
			resultData.setMsg("鐢ㄦ埛鍚嶄笉瀛樺湪");
		} else {
			resultData.setCode(1);
			resultData.setMsg("鐢ㄦ埛鍚嶅凡瀛樺湪");
		}
		return resultData;
	}

//	@Override
//	public ResultData<List<User>> login(String username, String password) throws Exception {
//		ResultData<List<User>> resultData = new ResultData<>();
//		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//			resultData.setCode(-100);
//			resultData.setMsg("璇锋眰鍙傛暟涓嶈兘涓虹┖");
//			resultData.setSuccess(false);
//			return resultData;
//		}
//
//		User user = getUser(username);
//		
//		if (user == null) {
//			// 鐢ㄦ埛鍚嶄笉瀛樺湪
//			resultData.setCode(-2);
//			resultData.setMsg("鐢ㄦ埛鍚嶄笉瀛樺湪");
//			resultData.setSuccess(false);
//			return resultData;
//		}
//
//		if (!password.equals(user.getPassword())) {
//			// 瀵嗙爜閿欒
//			resultData.setCode(-1);
//			resultData.setMsg("瀵嗙爜涓嶆纭�");
//			resultData.setSuccess(false);
//			return resultData;
//		}
//
//		List<Friend> friends = getFriends(user.getId());
//		List<User> users = new ArrayList<>();
//		for (int i = 0; i < friends.size(); i++) {
//			User userinfo = userMapper.selectByPrimaryKey(friends.get(i).getId());
//			users.add(userinfo);
//		}
//		
//		resultData.setData(users);
//		resultData.setCode(1);
//		resultData.setMsg("鐧诲綍鎴愬姛");
//		resultData.setSuccess(true);
//		return resultData;
//	}
	@Override
	public ResultData<LoginReturnData> login(String accid, String password) throws Exception {
		ResultData<LoginReturnData> resultData = new ResultData<>();
		if (StringUtils.isEmpty(accid) || StringUtils.isEmpty(password)) {
			resultData.setCode(-100);
			resultData.setMsg("璇锋眰鍙傛暟涓嶈兘涓虹┖");
			resultData.setSuccess(false);
			return resultData;
		}

		User user = getUser(accid);
		
		if (user == null) {
			// 鐢ㄦ埛鍚嶄笉瀛樺湪
			resultData.setCode(-2);
			resultData.setMsg("鐢ㄦ埛鍚嶄笉瀛樺湪");
			resultData.setSuccess(false);
			return resultData;
		}

		if (!password.equals(user.getPassword())) {
			// 瀵嗙爜閿欒
			resultData.setCode(-1);
			resultData.setMsg("瀵嗙爜涓嶆纭�");
			resultData.setSuccess(false);
			return resultData;
		}

		List<Friend> friends = getFriends(user.getId());
		LoginReturnData logindata = new LoginReturnData();
		logindata.setMyselfInfo(getUser(accid));
		List<User> users = new ArrayList<>();
		for (int i = 0; i < friends.size(); i++) {
			User userinfo = userMapper.selectByPrimaryKey(friends.get(i).getId());
			users.add(userinfo);
		}
		logindata.setFriends(users);
		resultData.setData(logindata);
		resultData.setCode(1);
		resultData.setMsg("鐧诲綍鎴愬姛");
		resultData.setSuccess(true);
		return resultData;
	}

	

	@Override
	public ResultData<User> register(int homeid,String phone, String password,String accid, String username, String userphoto, String sex,
			String birthday, String address, String describe) throws Exception {
		ResultData<User> resultData = new ResultData<>();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			resultData.setCode(-100);
			resultData.setMsg("璇锋眰鍙傛暟涓嶈兘涓虹┖");
			resultData.setSuccess(false);
			return resultData;
		}
		User haveuser = getUser(username);
		if (haveuser != null) {
			// 鐢ㄦ埛鍚嶅凡缁忓瓨鍦�
			resultData.setCode(0);
			resultData.setMsg("鐢ㄦ埛鍚嶅凡缁忓瓨鍦�");
			resultData.setSuccess(false);
			return resultData;
		}
		
		//鍚屾椂娉ㄥ唽浜戜俊
		MainTest.createUser(accid, username, password,userphoto);  

//		//娉ㄥ唽鐜俊璐﹀彿
//		registInHuanxin(username,password,username);
		
		// 鎻掑叆鍒癮dmin琛�
		User user = new User();
		user.setHomeid(homeid);
		user.setPhone(phone);
		user.setPassword(password);
		user.setUsername(username);
		user.setAccid(accid);
		user.setToken(password);
		user.setUserPhoto(userphoto);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setSignDesc(describe);
		user.setCreateTime(TimeUtil.getCurrentTimeString());
		userMapper.insert(user);// 鎻掑叆鍒版暟鎹簱
		
		resultData.setData(user);
		resultData.setCode(1);
		resultData.setMsg("娉ㄥ唽鎴愬姛");
		resultData.setSuccess(true);
		return resultData;
	}

	private User getUser(String accid) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andAccidEqualTo(accid);
		List<User> list = userMapper.selectByExample(userExample);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	
	private List<Friend> getFriends(Integer id){
		FriendExample friendExample = new FriendExample();
		FriendExample.Criteria criteria = friendExample.createCriteria();
		criteria.andOwnidEqualTo(id);
		List<Friend> friends = friendMapper.selectByExample(friendExample);
		return friends;
	}

	@Override
	public Boolean isAlreadyRegistered(String accid) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andAccidEqualTo(accid);
		List<User> users =  userMapper.selectByExample(userExample);
		if(users.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int update(User user) {
		return userMapper.updateByPrimaryKey(user);
	}
	
//	private List<User> getFriendsInfo(Integer id) {
//		UserExample userExample = new UserExample();
//		com.lqm.home.po.UserExample.Criteria criteria = userExample.createCriteria();
//		criteria.andIdEqualTo(id);
//		List<User> users = userMapper.selectByExample(userExample);
//		return users;
//		
//	}
	@Override
	public Admin getAdminSing(String name, String pass) {
		// TODO Auto-generated method stub
		return userMapper.getAdminSing(name,pass);
	}
}