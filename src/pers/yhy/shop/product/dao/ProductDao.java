package pers.yhy.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pers.yhy.shop.product.vo.Product;
import pers.yhy.shop.utils.PageHibernateCallback;

/**
 * product's maintence
 * 
 * @author Hengyu Yue
 *
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * find hot products
	 * 
	 * @return
	 */
	public List<Product> findHot() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		// get hot products-- is_hot==1
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		// descending order
		detachedCriteria.addOrder(Order.desc("pdate"));
		// execute search
		List<Product> list = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return list;
	}

	/**
	 * find newest products
	 * 
	 * @return
	 */
	public List<Product> findNew() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		detachedCriteria.addOrder(Order.desc("pdate"));
		// execute search
		List<Product> list = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return list;
	}

	/**
	 * find prodyct info by pid
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * find product total number by cid
	 * 
	 * @param cid
	 * @return
	 */
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list.size() > 0 && list != null) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * find product list by cid,begin data and limit number
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		this.getHibernateTemplate().find(hql, cid);
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		if (list.size() > 0 && list != null) {
			return list;
		}
		return null;
	}

	/**
	 * find product by csid
	 * 
	 * @param csid
	 * @return
	 */
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list.size() > 0 && list != null) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * find product list by csid,begin data and limit number
	 * 
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=? ";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { csid }, begin, limit));
		if (list.size() > 0 && list != null) {
			return list;
		}
		return null;
	}

}
