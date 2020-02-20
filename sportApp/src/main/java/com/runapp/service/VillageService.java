package com.runapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.runapp.bean.FenYe;
import com.runapp.bean.Village;
import com.runapp.utils.ResultData;


public interface VillageService {



	Village selectVillageInfo(Integer homeid);

	List<Village> getVillageListByDistrict(String district);

	Village searchByName(String villageName);

	boolean isAlreadyCreated(String villageName);

	ResultData<Village> insert(String villageIcon, String villageName, String villageDesc, int attentionNum, int postNum,
			String province, String city, String district);

	Village getvillage(String adminid);

	ArrayList<Village> getVillage(FenYe fenYe, String str);

	int findTotalRecord(String str);

	void deletevillage(String id);

	void updateViller(Village admin);
	
}
