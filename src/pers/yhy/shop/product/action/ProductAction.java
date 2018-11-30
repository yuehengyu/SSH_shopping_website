package pers.yhy.shop.product.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.product.service.ProductService;
import pers.yhy.shop.product.vo.Product;

/**
 * product's object
 * @author yuehe
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	/**
	 * find product info by pid
	 * @return
	 */
	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
}
