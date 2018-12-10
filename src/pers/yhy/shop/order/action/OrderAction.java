package pers.yhy.shop.order.action;

import java.io.IOException;
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
import pers.yhy.shop.utils.PaymentUtil;

/**
 * order manage
 * 
 * @author Hengyu Yue
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	private Integer page;
	private String pd_FrpId;// bank interface
	private String r6_Order;// pay success and get the back order id
	private String r3_Amt;// get the money when pay success

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

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
		order.setName(existUser.getName());
		order.setPhone(existUser.getPhone());
		order.setAddr(existUser.getAddr());
		orderService.save(order);
		// show orderItem in our webpage and clear our cart
		cart.clearCart();
		return "saveSuccess";
	}

	/**
	 * find order info by user id
	 * 
	 * @return
	 */
	public String findByUid() {
		// get user id
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// use service to dind
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	/**
	 * find order info by order id and pay the money
	 * 
	 * @return
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	/**
	 * pay money for user order
	 * 
	 * @return
	 * @throws IOException
	 */
	public String payOrder() throws IOException {
		Order currOrder = orderService.findByOid(order.getOid());
		// set payer info
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// pay money
		String p0_Cmd = "Buy";// business type
		String p1_MerId = "10001126856";// User key id
		String p2_Order = order.getOid().toString();// order number
		String p3_Amt = "0.01";// how much money need to pay
		String p4_Cur = "CNY";// money type
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://localhost:8080/SSH_shopping_website/order_callBack.action";// if pay money succ,whick
																							// webpage We will back
		String p9_SAF = "";// delivery address
		String pa_MP = "";
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1";// response :yes
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		// set these parameters to yibao payment interface
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		// redirect to yibao
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		return NONE;
	}

	/**
	 * paying success and then go back to our website
	 * 
	 * @return
	 */
	public String callBack() {
		// update order state to 2
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		// show succ msg in our webpage
		this.addActionMessage(
				"Successful order payment！Order number is " + r6_Order + ", the payment amount  " + r3_Amt);
		return "msg";
	}
	
	/**
	 * confirm receipt update state
	 * @return
	 */
	public String updateState() {
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
