package pers.yhy.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.order.service.OrderService;
import pers.yhy.shop.order.vo.Order;
import pers.yhy.shop.order.vo.OrderItem;
import pers.yhy.shop.utils.PageBean;

/**
 * order manage by admin user
 * 
 * @author yuehe
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * find all orders
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 *  find order item by order id
	 * @return
	 */
	public String findOrderItem() {
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	/**
	 * update order state info
	 * @return
	 */
	public String updateState() {
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
}
