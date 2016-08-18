package top.lilixin.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RequestMapping("")
@Controller
public class httpController {
	
	/**
	 * 无参get请求
	 * 
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public String get() {
		return "success";
	}

	/**
	 * get 方式带参数请求
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "getWithParams", method = RequestMethod.GET)
	@ResponseBody
	public String getWithParams(String name, String password) {
		if ("lilixin".equals(name) && "123456".equals(password)) {
			return "login success";
		} else {
			return "name or password is wrong";
		}
	}

	/**
	 * post表单提交 模拟用户登陆
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public String post(String name, String password) {
		if ("lilixin".equals(name) && "123456".equals(password)) {
			return "login success";
		} else {
			return "name or password is wrong";
		}
	}
	
	/**
	 * post表单提交 上传文件
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String post(HttpServletRequest request ) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			Iterator<String> fileNames = req.getFileNames();
			if(fileNames.hasNext()){
				String filename = fileNames.next();
				MultipartFile file = req.getFile(filename);
				System.out.println(file.getOriginalFilename());
				
			}
		}
		
		return "upload success";
	}

	/**
	 * 测试主页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "success";
	}
}
