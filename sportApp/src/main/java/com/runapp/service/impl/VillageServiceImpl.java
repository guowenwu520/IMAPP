package com.runapp.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runapp.mapper.PostMapper;
import com.runapp.mapper.UsertMapper;
import com.runapp.mapper.VillageMapper;
import com.runapp.bean.FenYe;
import com.runapp.bean.PostExample;
import com.runapp.bean.User;
import com.runapp.bean.UserExample;
import com.runapp.bean.UserExample.Criteria;
import com.runapp.bean.Village;
import com.runapp.bean.VillageExample;
import com.runapp.service.VillageService;
import com.runapp.utils.ResultData;
import com.runapp.utils.TimeUtil;


@Transactional
@Service
public class VillageServiceImpl implements VillageService{

	@Resource
	private VillageMapper villageMapper;
	@Resource
	private PostMapper postMapper;
	@Resource
	private UsertMapper userMapper;
	
	@Override
	public Village selectVillageInfo(Integer homeid) {
		Village village = villageMapper.selectByPrimaryKey(homeid);
		village.setPostNum(countPostByHomeid(homeid));
		village.setAttentionNum(countUserByHomeid(homeid));
		return village;
	}

	
	//鄉吧發佈的帖子數
	public int countPostByHomeid(Integer homeid) {
		PostExample postExample = new PostExample();
		com.runapp.bean.PostExample.Criteria criteria = postExample.createCriteria();
		criteria.andVillageIdEqualTo(homeid);
		return postMapper.countByExample(postExample);
	}
	
	//鄉吧關注人數
		public int countUserByHomeid(Integer homeid) {
			UserExample userExample = new UserExample();
			Criteria criteria = userExample.createCriteria();
			criteria.andHomeidEqualTo(homeid);
			return userMapper.countByExample(userExample);
		}


		/**
		 * 根据地区获取乡吧列表
		 */
		@Override
		public List<Village> getVillageListByDistrict(String district) {
			VillageExample villageExample = new VillageExample();
			com.runapp.bean.VillageExample.Criteria criteria = villageExample.createCriteria();
			criteria.andDistrictEqualTo(district);
			return villageMapper.selectByExample(villageExample);
		}

		/**
		 * 根据名字查询乡吧
		 */
		@Override
		public Village searchByName(String villageName) {
			VillageExample villageExample = new VillageExample();
			com.runapp.bean.VillageExample.Criteria criteria = villageExample.createCriteria();
			criteria.andTitleEqualTo(villageName);
			if(villageMapper.selectByExample(villageExample).size()>0){
				return villageMapper.selectByExample(villageExample).get(0);
			}else{
				return null;
			}
		}


		/**
		 * �?查乡吧是否已经被创建
		 * @param villageName
		 * @return
		 */
		@Override
		public boolean isAlreadyCreated(String villageName) {
			VillageExample villageExample  = new VillageExample();
			com.runapp.bean.VillageExample.Criteria criteria = villageExample.createCriteria();
			criteria.andTitleEqualTo(villageName);
			List<Village> villages = villageMapper.selectByExample(villageExample);
			if(villages.size()>0){
				return true;
			}else{
				return false;
			}
		}


		@Override
		public ResultData<Village> insert(String villageIcon, String villageName, String villageDesc, int attentionNum,
				int postNum, String province, String city, String district) {
			Village village = new Village();
			village.setAttentionNum(attentionNum);
			village.setCity(city);
			village.setProvince(province);
			village.setVillageIcon(villageIcon);
			village.setDistrict(district);
			village.setTitle(villageName);
			village.setVillageDesc(villageDesc);
			village.setPostNum(postNum);
			village.setCreateTime(TimeUtil.getCurrentTimeString());
			
			int villageId = villageMapper.insert(village);
			Village mode = villageMapper.selectByPrimaryKey(villageId);
			
			ResultData<Village> resultData = new ResultData<>();
			resultData.setCode(200);
			resultData.setData(mode);
			resultData.setSuccess(true);
			resultData.setMsg("创建成功");
			return resultData;
		}
//表格操作

		@Override
		public Village getvillage(String id) {
			return villageMapper.getvillage(id);
		}


		@Override
		public ArrayList<Village> getVillage(FenYe fenYe, String str) {
			// TODO Auto-generated method stub
			return villageMapper.getvillageSing(fenYe.getLimit(),fenYe.getStatus(),str);
		}


		@Override
		public int findTotalRecord(String str) {
			// TODO Auto-generated method stub
			return villageMapper.findTotalRecord(str);
		}


		@Override
		public void deletevillage(String id) {
			villageMapper.deletevillage(id);
		}


		@Override
		public void updateViller(Village admin) {
			villageMapper.updateViller(admin);
		}
		
}
