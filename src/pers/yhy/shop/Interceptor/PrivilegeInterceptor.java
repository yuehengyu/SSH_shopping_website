package pers.yhy.shop.Interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import pers.yhy.shop.adminuser.vo.AdminUser;

/**
 * admin manage filter :must admin and then go to the home.jsp
 * 
 * @author yuehe
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	/**
	 * execute filter
	 */
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// judge whether session save the admin user info
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession()
				.getAttribute("existAdminUser");
		if (existAdminUser == null) {
			// do not login
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("Please login first!!!");
			return "loginRequest";
		} else {
			return actionInvocation.invoke();
		}
	}
}
