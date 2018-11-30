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
}
