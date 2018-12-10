package pers.yhy.shop.categorysecond.service;

import java.util.List;

import pers.yhy.shop.categorysecond.dao.CategorySecondDao;
import pers.yhy.shop.categorysecond.vo.CategorySecond;
import pers.yhy.shop.utils.PageBean;

public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * find all second classification
	 * 
	 * @param page
	 * @return
	 */
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		Integer totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * add second category to our database
	 * 
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	/**
	 * delete a second category
	 * 
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * find second classification by csid
	 * 
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	
	/**
	 * update second category info
	 * @return
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	
	/**
	 * find all second categories
	 */
	public List<CategorySecond> findAll() {
		return  categorySecondDao.findAll();
	}
}
