package com.runapp.bean;

import org.springframework.stereotype.Component;

/**
 * Description: 分页pojo�?
 * Company: youjiang
 * @author lin
 * @date 2016�?10�?21�? 上午10:06:14
 * @version 1.0
 */
@Component
public class Page {
	private int everyPage;//每页显示数量
	private int totalCount;//总记录数
	private int totalPage;//总页�?
	private int currentPage;//当前�?
	private int beginIndex;//起始�?
	private boolean hasPrePage;//是否有上�?�?
	private boolean hasNextPage;//是否有下�?�?
	private int endIndex;
	
	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public Page(int everyPage, int totalCount, int totalPage, int currentPage,  
            int beginIndex, boolean hasPrePage, boolean hasNextPage) {  
        this.everyPage = everyPage;  
        this.totalCount = totalCount;  
        this.totalPage = totalPage;  
        this.currentPage = currentPage;  
        this.beginIndex = beginIndex;  
        this.hasPrePage = hasPrePage;  
        this.hasNextPage = hasNextPage; 
        this.endIndex = everyPage;
    } 
	
	public Page(int beginIndex,int everyPage) {  
        this.beginIndex = beginIndex;  
        this.endIndex = everyPage;
    } 

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

}
