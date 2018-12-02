package pers.yhy.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shopping cart object
 * 
 * @author Hengyu YuE
 *
 */
public class Cart implements Serializable{

	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	//cart obect has a attribute cartItems
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	private double total;// total money

	// function of our cart

	/**
	 * clear the cart
	 */
	public void clearCart() {
		// clear all the items and set the totalmoney is 0
		map.clear();
		total = 0;
	}

	/**
	 * remove a product from our cart
	 * 
	 * @param pid:product
	 *            Id
	 */
	public void removrCart(Integer pid) {
		// remove a item from our map list
		CartItem cartItem = map.remove(pid);
		// total money - remove item's money=total money
		total -= cartItem.getSubtotal();
	}

	/**
	 * add a product to our product
	 */
	public void addCart(CartItem cartItem) {
		// judge our cart if contain this item
		// if yes count++,totalmoney++
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			// contain this item
			CartItem oldCartItem = map.get(pid);
			oldCartItem.setCount(oldCartItem.getCount() + cartItem.getCount());

		} else {
			// if no:add this item to our cart
			map.put(pid, cartItem);
		}
		// change total money
		total += cartItem.getSubtotal();
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
