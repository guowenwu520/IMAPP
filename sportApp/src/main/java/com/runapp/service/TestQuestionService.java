package com.runapp.service;

import java.util.ArrayList;

import com.runapp.bean.ShiPing;
import com.runapp.bean.TestQuestion;
import com.runapp.bean.Ti;

public interface TestQuestionService {

	ArrayList<TestQuestion> getTestService();

	ArrayList<Ti> getTiTestService(String testquestionsid);

	ArrayList<ShiPing> getShiPingService();

}
