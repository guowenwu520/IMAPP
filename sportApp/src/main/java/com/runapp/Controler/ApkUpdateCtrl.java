package com.runapp.Controler;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runapp.bean.Updateinfo;
import com.runapp.mapper.UpdateinfoMapper;
import com.runapp.utils.ResultData;



/**
 * 版本更新
 */
@Controller
@RequestMapping(value="updateapp",produces="text/plain;charset=UTF-8")
public class ApkUpdateCtrl {

	@Resource
	private UpdateinfoMapper updateinfoMapper;
	
		/**
		 * 版本更新
		 */
		@RequestMapping(value = "updataApk", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
		@ResponseBody
		public ResultData<Updateinfo> updataApk() throws Exception {
			ResultData<Updateinfo> resultData = new ResultData<>();
			Updateinfo model = updateinfoMapper.selectByPrimaryKey(1);
			resultData.setData(model);
			resultData.setCode(200);
			resultData.setMsg("版本更新");
			return resultData;
		}
		
		
}
