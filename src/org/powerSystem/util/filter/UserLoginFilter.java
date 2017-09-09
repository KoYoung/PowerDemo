package org.powerSystem.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.powerSystem.entity.ShareAdmin;

public class UserLoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		ShareAdmin admin = (ShareAdmin) session.getAttribute("user");
		if(admin!=null){
			System.out.println("-------------session is not null----------");
			arg2.doFilter(arg0, arg1);
		}else{
			System.out.println();
			System.out.println("-------------session is null----------");
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.setContentType("text/html;charset=utf-8");
			//response.setCharacterEncoding("utf-8");
			String path = request.getServletPath();
			path = path.substring(path.lastIndexOf("/"));
			if(path.equals("/main.jsp") || path.equals("/checkUser.action")){
				arg2.doFilter(arg0, arg1);
			}else{
				response.getWriter().write("<script>window.top.location.href='"+request.getContextPath()+"'</script>");
			}
			//response.sendRedirect(request.getContextPath()+"/main.jsp");
			return ;
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
