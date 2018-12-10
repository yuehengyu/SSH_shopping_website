package pers.yhy.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.xml.internal.utils.ObjectPool;

import pers.yhy.shop.order.vo.Order;
import pers.yhy.shop.order.vo.OrderItem;
import pers.yhy.shop.utils.PageBean;
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

	/**
	 * find order info by order id
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * update order's user info
	 * 
	 * @param currOrder
	 * @return
	 */
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	/**
	 * compute all the
	 * 
	 * @return
	 */
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null & list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * find all orders
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Order> findByPage(Integer begin, Integer limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if (list != null & list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * find order item by order id
	 * 
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null & list.size() > 0) {
			return list;
		}
		return null;
	}

}
