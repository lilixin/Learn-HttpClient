package top.lilixin.maintest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Project: Learn-HttpClient
 * @Author: lilixin
 * @Date: 2016年8月18日
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class HttpPostTest1 {

	/**
	 * post方式提交表单(模拟用户登陆请求)
	 */
	public static void main(String[] args) {
		// 创建httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost("http://localhost:8080/post");
		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "lilixin"));
		params.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity uefEntity = null;
		try {
			uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("request post uri :" + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("respose content : " + EntityUtils.toString(entity));
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
