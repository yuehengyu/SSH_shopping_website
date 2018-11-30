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

}
