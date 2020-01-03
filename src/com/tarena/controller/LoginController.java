package com.tarena.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.dao.AdminDao;
import com.tarena.entity.Admin;
import com.tarena.entity.Module;
import com.tarena.util.ImageUtil;


@RequestMapping("/login")
@Controller
public class LoginController extends BaseController {
	
	private final static int SUCCESS = 0;
	
	private final static int ADMIN_CODE_ERROR = 1;
	
	private final static int PASSWORD_ERROR = 2;
	
	private final static int IMAGE_CODE_ERROR = 3;
	
	@Resource
	private AdminDao adminDao;
	
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "main/login";
	}
	
	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "main/index";
	}
	
	@RequestMapping("/nopower.do")
	public String nopower() {
		return "main/nopower";
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(
			@RequestParam("adminCode") String adminCode,
			@RequestParam("password") String password,
			@RequestParam("code") String code,
			HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		 session.setAttribute("adminCode",adminCode);
		String imageCode = (String) session.getAttribute("imageCode");
		if(code == null
				|| !code.equalsIgnoreCase(imageCode)) {
			result.put("flag", IMAGE_CODE_ERROR);
			return result;
		}
		
		Admin admin = adminDao.findByCode(adminCode);
		if(admin == null) {
			result.put("flag", ADMIN_CODE_ERROR);
			return result;
		} else if (!admin.getPassword().equals(password)) {
			result.put("flag", PASSWORD_ERROR);
			return result;
		} else {
			session.setAttribute("admin", admin);
			List<Module> modules = 
				adminDao.findModulesByAdmin(admin.getAdmin_id());
			session.setAttribute("allModules", modules);
			
			result.put("flag", SUCCESS);
			return result;
		}
	}
	
	@RequestMapping("/createImage.do")
	public void createImage(
			HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, BufferedImage> imageMap = ImageUtil.createImage();
		String code = imageMap.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		
		BufferedImage image = imageMap.get(code);
		
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
		ops.close();
	}
	
}
