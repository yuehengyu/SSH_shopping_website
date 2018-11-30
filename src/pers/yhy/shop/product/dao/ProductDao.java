package pers.yhy.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pers.yhy.shop.product.vo.Product;

/**
 * product's maintence 
 * @author Hengyu Yue
 *
 */
public class ProductDao extends HibernateDaoSupport{
	
	/**
	 * find hot products
	 * @return
	 */
	public List<Product> findHot() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		//get hot products--  is_hot==1
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		//descending order
		detachedCriteria.addOrder(Order.desc("pdate"));
		//execute search
		List<Product> list=this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return list;
	}
	
	/**
	 * find newest products
	 * @return
	 */
	public List<Product> findNew() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Product.class);
		detachedCriteria.addOrder(Order.desc("pdate"));
		//execute search
		List<Product> list=this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return list;
	}
	
	/**
	 * find prodyct info by pid
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
}
