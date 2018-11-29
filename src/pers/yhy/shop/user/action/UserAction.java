package pers.yhy.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.user.service.UserService;
import pers.yhy.shop.user.vo.User;

/**
 * user module's action
 * 
 * @author Hengyu Yue
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// model driver will use it
	private User user = new User();
	private UserService userService;

	// receive verification code
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}

	/**
	 * go to regist page
	 * 
	 * @return
	 */
	public String registPage() {
		return "registPage";
	}

	/**
	 * go to login page
	 * 
	 * @return
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * check username whether contains in our database
	 * 
	 * @throws IOException
	 * @return
	 */
	public String findByName() throws IOException {
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();

		if (existUser != null) {
			response.getWriter().println("<font color='red'>username is exist</font>");
		} else {
			response.getWriter().println("<font color='green'>username can be used</font>");
		}
		return NONE;
	}

	/**
	 * user regist
	 */
	public String regist() {
		String code=(String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(code)) {
			this.addActionError("verification code is wrong!!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("Registration success!Pleast check your email box and activate your account");
		return "msg";
	}

	/**
	 * user regist
	 */
	public String login() {
		String code=(String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(code)) {
			this.addActionError("verification code is wrong!!");
			return "checkcodeLoginFail";
		}
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("Fail to login: username or password is not correct or user is not activated!");
			return "login";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * user logout
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

	/**
	 * active users
	 * 
	 * @return
	 */
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			this.addActionMessage("Activation fails:Activation code error!");
		} else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("Activation successful: please login!!!");
		}
		return "msg";
	}

}
