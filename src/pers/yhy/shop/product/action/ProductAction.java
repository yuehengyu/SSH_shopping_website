package pers.yhy.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import pers.yhy.shop.category.service.CategoryService;
import pers.yhy.shop.category.vo.Category;
import pers.yhy.shop.product.service.ProductService;
import pers.yhy.shop.product.vo.Product;
import pers.yhy.shop.utils.PageBean;

/**
 * product's object
 * 
 * @author yuehe
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	private Product product = new Product();
	private ProductService productService;
	private Integer cid;
	private Integer csid;//second classification

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	

	public Integer getCsid() {
		return csid;
	}

	private CategoryService categoryService;
	private int page;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Integer getCid() {
		return cid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * find product info by pid:productId
	 * 
	 * @return
	 */
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	/**
	 * find product info by cid:categoryId
	 * 
	 * @return
	 */
	public String findByCid() {
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	public String findByCsid() {
		PageBean<Product> pageBean=productService.findByCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
