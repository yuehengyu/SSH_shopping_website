package pers.yhy.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import pers.yhy.shop.cart.vo.Cart;
import pers.yhy.shop.cart.vo.CartItem;
import pers.yhy.shop.product.service.ProductService;
import pers.yhy.shop.product.vo.Product;

/**
 * cart object action
 * 
 * @author yuehe
 *
 */
public class CartAction {

	private Integer pid;
	private Integer count;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * we can get two parameters:pid and count add cartItem to our cart
	 * 
	 * @return
	 */
	public String addCart() {
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		// get cart from session
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}

	/**
	 * remove all the items from our cart
	 * 
	 * @return
	 */
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}

	/**
	 * delete a item from our cart
	 * 
	 * @return
	 */
	public String removeCart() {
		Cart cart = getCart();
		cart.removrCart(pid);
		return "removeCart";
	}

	/**
	 * get cart info from session
	 * 
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * visit my cart and see the cartItem info
	 * 
	 * @return
	 */
	public String myCart() {
		return "myCart";
	}
}
