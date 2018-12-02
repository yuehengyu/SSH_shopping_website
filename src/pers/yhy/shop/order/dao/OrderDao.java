package pers.yhy.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.xml.internal.utils.ObjectPool;

import pers.yhy.shop.order.vo.Order;
import pers.yhy.shop.utils.PageHibernateCallback;

/**
 * order module layer dao
 * 
 * @author yuehe
 *
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * save order to our database
	 * 
	 * @param order
	 */
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	/**
	 * find total number of user's orders
	 * 
	 * @param uid
	 * @return
	 */
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * find all the orders info for this user
	 * 
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, new Object[] { uid }, begin, limit));
		if (list != null & list.size() > 0) {
			return list;
		}
		return null;
	}

}
