package pers.yhy.shop.adminuser.service;

import pers.yhy.shop.adminuser.dao.AdminUserDao;
import pers.yhy.shop.adminuser.vo.AdminUser;

/**
 * admin user service layer
 * 
 * @author Hengyu Yue
 *
 */
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
}
