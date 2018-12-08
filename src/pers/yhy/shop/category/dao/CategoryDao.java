package pers.yhy.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.xpath.internal.operations.And;

import pers.yhy.shop.category.vo.Category;

/**
 * Primary classification persistence layer
 * 
 * @author Hengyu Yue
 *
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * find all primary category
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		};
		return null; 
	}
	
	/**
	 * save primary category to our database
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	/**
	 * according cid to find the primary classification
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return this .getHibernateTemplate().get(Category.class, cid);
	}
	
	/**
	 * delete primary classification
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	
	/**
	 * update primary classification
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
