package com.runapp.utils;

import com.runapp.bean.Page;

/**
 * Description: 分页信息辅助�?
 * Company: youjiang
 * @author lin
 * @date 2016�?10�?21�? 上午10:32:30
 * @version 1.0
 */
public class PageUtils {
	
	/**
	 * 十个条目�?�?
	 */
	private static final int TENITEMSINONEPAGE = 10;
	
	/**
	 * 二十个条目一�?
	 */
	private static final int TWENTYITEMSINONEPAGE = 20;
	
	/**
	 * 五个条目�?�?
	 */
	private static final int FIVEITEMSINONEPAGE = 5;
	
	/**
	 * 构�?�分�?
	 * @param everyPage 每一页记录数
	 * @param totalCount 总记录数
	 * @param currentPage 当前页数
	 * @return
	 */
	public static Page createPage(int everyPage, int totalCount, int currentPage) {
		everyPage = getEveryPage(everyPage);
		int totalPage = getTotalPage(everyPage, totalCount);
		currentPage = getCurrentPage(currentPage, totalPage);
		int beginIndex = getBeginIndex(everyPage, currentPage);
		boolean hasPrePage = getHasPrePage(currentPage);
		boolean hasNextPage = getHasNextPage(totalPage, currentPage);
		return new Page(everyPage, totalCount, totalPage, currentPage,
				beginIndex, hasPrePage, hasNextPage);
	}
	
	public static Page createPage(int everyPage, int currentPage) {
		int beginIndex = getBeginIndex(everyPage, currentPage);
		return new Page(beginIndex, everyPage);
	}
	
	private static int defaultEveryPage = 10;
	/**
	 * 获取起始�?
	 * @param everyPage 每一页多少条
	 * @param currentPage 当前页数
	 * @return 起始�?
	 * @author lin
	 */
	public static int getBeginIndex(int everyPage,int currentPage){
		if(currentPage == 0){
			return currentPage;
		}
		return (currentPage - 1) * everyPage;
	}
	/**
	 * 设置当前�?
	 * @param currentPage 当前�?
	 * @param totalPage 总页�?
	 * @return 当前�?
	 * @author lin
	 */
	public static int getCurrentPage(int currentPage,int totalPage){
		if(currentPage >= totalPage){
			return totalPage;
		}
		return currentPage <= 0 ? 1 : currentPage;
	}
	/**
	 * 获取每一页条�?
	 * @param everyPage 每一页条�?
	 * @return 每一页条�?
	 * @author lin
	 */
	public static int getEveryPage(int everyPage){
		return everyPage <=0 ? defaultEveryPage :everyPage;
	}
	/**
	 * 是否有上�?�?
	 * @param currentPage 当前�?
	 * @return 布尔类型
	 * @author lin
	 */
	public static boolean getHasPrePage(int currentPage){
		return currentPage == 1 ? false : true;
	}
	
	/**
	 * 是否有下�?�?
	 * @param currentPage 当前�?
	 * @param totalPage 总页�?
	 * @return 布尔类型
	 * @author lin
	 */
	public static boolean getHasNextPage(int totalPage, int currentPage){
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
	
	/**
	 * 设置总页�?
	 * @param everyPage 每一页条�?
	 * @param totalCount 总条�?
	 * @return 总页�?
	 * @author lin
	 */
	public static int getTotalPage(int everyPage,int totalCount){
		int totalPage = 1;
		if(totalCount % everyPage == 0){
			 totalPage = (totalCount/everyPage);
		}else{
			totalPage = (totalCount / everyPage + 1);
		}
		return  totalPage;
	}

	/**
	 * 十条目一�?
	 * @return 10
	 * @author kwum
	 */
	public static int getTenItems() {
		return TENITEMSINONEPAGE;
	}

	/**
	 * 二十条目�?�?
	 * @return 20
	 * @author kwum
	 */
	public static int getTwentyItems() {
		return TWENTYITEMSINONEPAGE;
	}

	/**
	 * 五条�?
	 * @return 5
	 * @author kwum
	 */
	public static int getFiveItems() {
		return FIVEITEMSINONEPAGE;
	}
}
