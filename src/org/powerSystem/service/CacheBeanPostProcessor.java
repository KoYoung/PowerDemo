package org.powerSystem.service;

import hotelService.StartWebservice;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;
import org.powerSystem.service.role.impl.ShareActioninfoServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
@Component
public class CacheBeanPostProcessor implements BeanPostProcessor {
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg0 instanceof ShareDataBaseServiceImpl){
			System.out.println("------------------------开始加载数据字典-------------------------------------------");
			ShareDataBaseServiceImpl data = (ShareDataBaseServiceImpl) arg0;
			data.queryData();
		}
		if(arg0 instanceof ShareActioninfoServiceImpl){
			System.out.println("------------------------开始加载菜单-------------------------------------------");
			ShareActioninfoServiceImpl data = (ShareActioninfoServiceImpl)arg0;
			data.queryTostatic();
		}
		if(arg0 instanceof StartWebservice){
			/*System.out.println("----------------------StartWebservice-------------------------");
			StartWebservice service = (StartWebservice) arg0;
			service.run();*/
			
		}
		return arg0;
	}

}
