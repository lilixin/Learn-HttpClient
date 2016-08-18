package top.lilixin.maintest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Project: Learn-HttpClient
 * @Author: lilixin
 * @Date: 2016年8月18日
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class HttpGetTest1 {

	public static void main(String[] args) {
		// 创建httpclient
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			// 创建httpget
			HttpGet httpGet = new HttpGet("http://localhost:8080/get");

			System.out.println("excute request :" + httpGet.getURI());

			// 执行get请求
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet);
			try {
				// 打印响应状态
				System.out.println("响应状态 : " + response.getStatusLine());

				// 获取实体
				HttpEntity entity = response.getEntity();

				if (entity != null) {
					// 打印实体的长度
					System.out.println("entity length : " + entity.getContentLength());
					// 打印实体内容
					System.out.println("response content :" + EntityUtils.toString(entity));
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
