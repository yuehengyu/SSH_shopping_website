package pers.yhy.shop.order.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.cart.vo.Cart;
import pers.yhy.shop.cart.vo.CartItem;
import pers.yhy.shop.order.service.OrderService;
import pers.yhy.shop.order.vo.Order;
import pers.yhy.shop.order.vo.OrderItem;
import pers.yhy.shop.user.vo.User;
import pers.yhy.shop.utils.PageBean;

/**
 * order manage
 * 
 * @author Hengyu Yue
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		return order;
	}

	/**
	 * generate a new order
	 * 
	 * @return
	 */
	public String save() {
		// save data to database: add info to our order object
		Date currDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = simpleDateFormat.format(currDate);
		order.setOrdertime(currTime);
		order.setState(1);// 1:not pay the money 2: have been paid the money 3:have been delivery,but user
							// does not received 4: transaction success
		// total money from our cart ,we need to get our cart first
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("Sorry, you do not buy anything!!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// get order's orderItem
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// set this order belong which user
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("You do not login!Please login first!!");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// show orderItem in our webpage and clear our cart
		cart.clearCart();
		return "saveSuccess";
	}

	public String findByUid() {
		// get user id
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// use service to dind
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
}
