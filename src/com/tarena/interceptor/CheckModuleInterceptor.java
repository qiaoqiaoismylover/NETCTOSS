package com.tarena.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tarena.entity.Module;

public class CheckModuleInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		//获取登录用户有权限的所有模块
		List<Module> modules = (List<Module>) 
				request.getSession().getAttribute("allModules");
		//获取用户当前要访问的模块
		int currentModule = (Integer) 
				request.getSession().getAttribute("currentModule");
		//判断用户有权限的模块是否包含当前模块
		for (Module module : modules) {
			if (module.getModule_id() == currentModule) {
				//有当前访问模块的权限
				return true;
			}
		}
		//没有当前访问模块的权限
		response.sendRedirect(
				request.getContextPath() + "/login/nopower.do");
		return false;
	}

}
