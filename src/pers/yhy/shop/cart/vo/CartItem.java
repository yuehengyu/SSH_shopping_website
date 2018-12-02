package pers.yhy.shop.cart.vo;

import pers.yhy.shop.product.vo.Product;

/**
 * sve the product object that user want to buy
 * 
 * @author yuehe
 *
 */
public class CartItem {
	private Product product;
	private int count;// number of product
	private double subtotal;// total money

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	//auto compute total money
	public double getSubtotal() {
		return count * product.getShop_price();
	}
}
