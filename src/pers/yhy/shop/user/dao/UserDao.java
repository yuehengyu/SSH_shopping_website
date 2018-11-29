package pers.yhy.shop.user.dao;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pers.yhy.shop.user.vo.User;

/**
 * userDao
 * 
 * @author yuehe
 *
 */
public class UserDao extends HibernateDaoSupport {
	/**
	 * check username by name
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		String hql = "from User where username =?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * insert user
	 * 
	 * @param user
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * find user by code
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		String hql = "from User where code=?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if (list.size() > 0 && list != null) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * update user state
	 * 
	 * @param existUser
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/**
	 * user login
	 * 
	 * @param user
	 */
	public User login(User user) {
		String hql = "from User where username=? and password=? and state=?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
