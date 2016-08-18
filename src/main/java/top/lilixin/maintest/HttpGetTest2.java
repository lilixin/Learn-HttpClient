package top.lilixin.maintest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Project: Learn-HttpClient
 * @Author: lilixin
 * @Date: 2016年8月18日
 * @Copyright: 2016 www.lilixin.top Inc. All rights reserved.
 */
public class HttpGetTest2 {

	public static void main(String[] args) {
		// 创建httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget
			URI uri = new URIBuilder().setScheme("http").setHost("localhost:8080").setPath("/getWithParams")
					.addParameter("name", "lilixin").addParameter("password", "123456").build();
			HttpGet httpget = new HttpGet(uri);
			System.out.println("request uri : " + httpget.getURI());
			// 执行get请求
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("response content : " + EntityUtils.toString(entity));
			}
			EntityUtils.consume(entity);
		} catch (URISyntaxException e) {
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
