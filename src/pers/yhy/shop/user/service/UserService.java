package pers.yhy.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import pers.yhy.shop.user.dao.UserDao;
import pers.yhy.shop.user.vo.User;
import pers.yhy.shop.utils.MailUtils;
import pers.yhy.shop.utils.UUIDUtils;

/**
 * user service class
 * 
 * @author yuehe
 *
 */
@Transactional
public class UserService {
	// injection userdao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * check user by username
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * insert function
	 */
	public void save(User user) {
		user.setState(0);// 0:inactivated 1:activated
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// send activation email
		MailUtils.sendMail(user.getEmail(), code);
	}

	/**
	 * find user by code
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	/**
	 * change user's state
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		userDao.update(existUser);
	}
	
	/**
	 * user login
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}
}
