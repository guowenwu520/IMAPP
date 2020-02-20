package com.runapp.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.bean.ShiPing;
import com.runapp.bean.TestQuestion;
import com.runapp.bean.Ti;
import com.runapp.mapper.TestQuestionMapper;
import com.runapp.service.TestQuestionService;

@Service
public class TestQuestionServiceImpl implements TestQuestionService{
	@Autowired
	TestQuestionMapper testQuestionMapper;
   @Override
public ArrayList<TestQuestion> getTestService() {
	return testQuestionMapper.getTestQuestions();
}
   @Override
public ArrayList<Ti> getTiTestService(String testquestionsid) {
	// TODO Auto-generated method stub
	return testQuestionMapper.getTiTestions(testquestionsid);
}
   @Override
public ArrayList<ShiPing> getShiPingService() {
	// TODO Auto-generated method stub
	   return testQuestionMapper.getShiPings();
}
}
