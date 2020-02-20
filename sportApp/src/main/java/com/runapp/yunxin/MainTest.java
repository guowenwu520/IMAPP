package com.runapp.yunxin;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Firrela
 * @time 2016/3/7.
 */
public class MainTest {

    private static Logger logger = LoggerFactory.getLogger(MainTest.class);

    private static String APPKEY = "5f4436bfeaba7650d75e663cdc6e8073";  //AppKey
    private static String SECRET = "5f174764cd39";  //AppSecret

    public static final void main(String[] args) throws IOException {
        String res = createUser("HelloWorld", "名字", UUIDUtil.getUUID(),"");

        System.out.println(res);
        //TODO: 对结果的业务处理，如解析返回结果，并保存成功注册的用�?
    }

    public static String createUser(String accid, String name, String token,String icon) throws IOException {
    	String url = "https://api.netease.im/nimserver/user/create.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", accid));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("icon", "http://192.168.43.162:8080"+icon));

        //UTF-8编码,解决中文问题
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

        String res = NIMPost.postNIMServer(url, entity, APPKEY, SECRET);
        logger.info("createUser httpRes: {}", res);
        return res;
    }
    
    public static String updateUserPass(String accid, String token) throws IOException {
    	String url = "https://api.netease.im/nimserver/user/update.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("accid", accid));
        params.add(new BasicNameValuePair("token", token));

        //UTF-8编码,解决中文问题
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

        String res = NIMPost.postNIMServer(url, entity, APPKEY, SECRET);
        logger.info("createUser httpRes: {}", res);
        return res;
    }
}
