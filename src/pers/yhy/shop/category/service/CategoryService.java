package pers.yhy.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pers.yhy.shop.category.dao.CategoryDao;
import pers.yhy.shop.category.vo.Category;

/**
 * Primary classification business layer
 * 
 * @author Hengyu Yue
 *
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 * find all primary category
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	/**
	 * save primary category to our database
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}
	
	/**
	 * according cid to find the primary classification
	 * @param cid
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	
	/**
	 * delete the primary classification 
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	
	/**
	 * update primary classification info
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
}
