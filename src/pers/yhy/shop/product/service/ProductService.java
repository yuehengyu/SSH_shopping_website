package pers.yhy.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pers.yhy.shop.product.dao.ProductDao;
import pers.yhy.shop.product.vo.Product;
import pers.yhy.shop.utils.PageBean;

/**
 * product's business service
 * 
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
	 * 
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * find newest products
	 * 
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * find product info by pid
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// set current page
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// set current page
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
