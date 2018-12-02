package pers.yhy.shop.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import pers.yhy.shop.category.vo.Category;
import pers.yhy.shop.product.vo.Product;

/**
 * second category object class
 * 
 * @author Hengyu YUe
 *
 */
public class CategorySecond {
	private Integer csid;
	private String csname;
	private Category category;
	private Set<Product> products=new HashSet<Product>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
