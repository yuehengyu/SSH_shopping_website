package pers.yhy.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.bcel.internal.generic.NEW;

import pers.yhy.shop.order.dao.OrderDao;
import pers.yhy.shop.order.vo.Order;
import pers.yhy.shop.utils.PageBean;

/**
 * order service module
 * 
 * @author yuehe
 *
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * save order to our datadase
	 * 
	 * @param order
	 */
	public void save(Order order) {
		orderDao.save(order);
	}

	/**
	 * find my orders by pid
	 * 
	 * @param uid
	 * @param page
	 * @return
	 */
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit = 5;
		pageBean.setLimit(limit);
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * find order info by order id
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	
	/**
	 * update order user info
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
}
