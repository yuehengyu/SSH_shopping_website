package pers.yhy.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pers.yhy.shop.product.dao.ProductDao;
import pers.yhy.shop.product.vo.Product;

/**
 * product's business service
 * @author Hengyu Yue
 *
 */

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/**
	 * find hot product
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}
	
	/**
	 * find newest products
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}
	
	/**
	 * find product info by pid
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	
	
}
