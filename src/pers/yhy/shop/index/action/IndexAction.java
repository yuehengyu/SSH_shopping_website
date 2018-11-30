package pers.yhy.shop.index.action;


import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import pers.yhy.shop.category.service.CategoryService;
import pers.yhy.shop.category.vo.Category;
import pers.yhy.shop.product.service.ProductService;
import pers.yhy.shop.product.vo.Product;

/**
 * action of visit the home page
 * 
 * @author Hengyu Yue
 *
 */
public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * visit home page function
	 */
	public String execute() {
		List<Category> clist=categoryService.findAll();
		ActionContext.getContext().getSession().put("clist", clist);
		List<Product> hList=productService.findHot();
		//save the list value to stack
		ActionContext.getContext().getValueStack().set("hList", hList);
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
}
