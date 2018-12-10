package pers.yhy.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pers.yhy.shop.categorysecond.vo.CategorySecond;
import pers.yhy.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * compute the total number of second classification
	 * 
	 * @return
	 */
	public Integer findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * find all the second classification from our database for each page
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<CategorySecond> findByPage(int begin, Integer limit) {
		String hql = "from CategorySecond order by  csid desc";
		List<CategorySecond> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * add second category to our database
	 * 
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * delete a second category
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * find second classification by csid
	 * 
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * update second category info
	 * 
	 * @return
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * find all second categories
	 * 
	 * @return
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list=this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
