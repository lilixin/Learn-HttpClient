package top.lilixin.maintest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Project: Learn-HttpClient
 * @Author: lilixin
 * @Date: 2016年8月18日
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class HttpPostTest2 {
	/**
	 * post 上传资源
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			// 创建httppost
			HttpPost httpPost = new HttpPost("http://localhost:8080/upload");
			// 创建一个ContentBody
			FileBody body = new FileBody(new File("D:/dev/nginx-1.2.0.rar"));
			// InputStreamBody body = new InputStreamBody(new
			// FileInputStream(file), "myfile");
			// 创建httpentity
			HttpEntity entity = MultipartEntityBuilder.create().addPart("myfile", body).build();
			httpPost.setEntity(entity);
			// 执行post请求
			CloseableHttpResponse response = httpClient.execute(httpPost);
			System.out.println("request post uri :" + httpPost.getURI());
			try {
				HttpEntity entity2 = response.getEntity();
				if (entity2 != null) {
					System.out.println("response content : " + EntityUtils.toString(entity2));
				}
				EntityUtils.consume(entity2);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
