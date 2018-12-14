# SSH_shopping_website
`I used the SSH（struts+spring+hibernate+mysql） to develop this website by myself. I will brief introduce what features I implemented.`

## Build a development environment(I will tell you which packages and files you need to import.)
1.For Struts2:<br>
`struts-2.3.15.3\apps\struts2-blank.war\WEB-INF\lib\*.jar`<br>
`struts-2.3.15.3\lib\struts2-json-plugin-2.3.15.3.jar`<br>
`struts-2.3.15.3\lib\struts2-spring-plugin-2.3.15.3.jar`<br>
2.web.xml---(struts2 core filter) <br>
```xml
<filter>
 	<filter-name>struts2</filter-name>
 	<filter-class>
		org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
	</filter-class>
 </filter>
 <filter-mapping>
 	<filter-name>struts2</filter-name>
 	<url-pattern>/*</url-pattern>
 </filter-mapping>
```
3.For Spring(spring 3.2,there are so many jars you need to import for spring AOP,Spring JDBC,Spring transaction and so on):<br>
*jar<br>
`spring-beans-3.2.0.RELEASE.jar`<br>
`spring-context-3.2.0.RELEASE.jar`<br>
`spring-core-3.2.0.RELEASE.jar`<br>
`spring-expression-3.2.0.RELEASE.jar`<br>
`com.springsource.org.apache.commons.logging-1.1.1.jar`<br>
`com.springsource.org.apache.log4j-1.2.15.jar`<br>
*AOP<br>
`spring-aop-3.2.0.RELEASE.jar`<br>
`spring-aspects-3.2.0.RELEASE.jar`<br>
`com.springsource.org.aopalliance-1.0.0.jar`<br>
`com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar`<br>
*Spring Jdbc <br>
`spring-jdbc-3.2.0.RELEASE.jar`<br>
`spring-tx-3.2.0.RELEASE.jar`<br>
*Spring Transaction
`spring-tx-3.2.0.RELEASE.jar`<br>
*Spring integrates with other ORM frameworks <br>
`spring-orm-3.2.0.RELEASE.jar`<br>
*Spring for web<br>
`spring-web-3.2.0.RELEASE.jar`<br>
*String for unit<br>
`spring-test-3.2.0.RELEASE.jar`<br>
4.For Hibernate<br>
`hibernate-distribution-3.6.10.Final\hibernate3.jar`<br>
`hibernate-distribution-3.6.10.Final\lib\required\*.jar`<br>
`hibernate-distribution-3.6.10.Final\lib\jpa\*.jar`<br>
`slf4j-log4j.jar`<br>
5.Basic configuration information<br>
you can see me code directly:struts.xml,jdbc.properties,log4j.properties,applicationContext.xml <br>

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/user_home.png)<br>

## 1.1.1 user modules
### Register

1. Front desk JS check. <br>
2. Use AJAX to complete the asynchronous check of the username.<br>
3. Back-end Struts2 verification.<br>
4. Verification code.<br>
5. Send an activation email.<br>
6. Save user information to the database.<br>
![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/user_register.png) <br>

### Activation

1. Click on the link to complete the activation: <br>
* Query the database for the user based on the activation code.<br>
* If there is: Activate. (Clear the activation code field.
* Modify the user's status.<br>

### Login and Log out
1. Enter the username and password.----(need data check.)<br>
2. The username and password are correct and the user status must be active.<br>
3. Destroy the session.<br>
![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/user_login.png)<br>

### Vefification code function
The code for generating the random verification is shown as below:`(note:You also need to configure your file)`<br>
```java
package pers.yhy.shop.user.action;

import com.opensymphony.xwork2.ActionSupport;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

/**
 * generate verification code
 * 
 * @author Hengyu Yue
 *
 */
public class CheckImgAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		int width = 120;
		int height = 30;

		// first: draw a image
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// second set background color ---operate the object
		Graphics graphics = bufferedImage.getGraphics();// get board object --- pen
		// we must choose a color before we draw a img
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);

		// third --draw the border
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// fourth: generate foor random number
		Graphics2D graphics2d = (Graphics2D) graphics;
		// set output font 
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));

		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		// String words =
		// "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";
		Random random = new Random();
		// define StringBuffer
		StringBuffer sb = new StringBuffer();
		// define x coordinate
		int x = 10;
		for (int i = 0; i < 4; i++) {
			// random color
			graphics2d
					.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// rotate -30 degree--- 30 degree
			int jiaodu = random.nextInt(60) - 30;
			// Conversion radians
			double theta = jiaodu * Math.PI / 180;

			// generate a random number
			int index = random.nextInt(words.length()); // generate a random num from  0 to length - 1
			// Get alphanumeric or characters
			char c = words.charAt(index);
			sb.append(c);
			// input c to a image
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}

		// Save the generated letters into the session
		ServletActionContext.getRequest().getSession().setAttribute("checkcode", sb.toString());

		// Step 5 Draw the interference line
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}

		// Output the above image to the browser Image IO
		graphics.dispose();// release memory
		ImageIO.write(bufferedImage, "jpg", ServletActionContext.getResponse().getOutputStream());

		return NONE;
	}

	/**
	 * Take a certain range of colors
	 * 
	 * @param fc
	 *            int scope parameter 1
	 * @param bc
	 *            int scope parameter 2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// get a random color
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}


```

## 1.1.2 Primary classification module

### Query primary classification
Store the primary classification into the session scope. (There is a primary classification data in each page).<br>

### Quert each primary classification 
At the same time, query the secondary classification to which each primary classification belongs.<br>

## 1.1.3 Products module

### Query hot Products
Query hot products: (limited number of 10)<br>

### Query lastest product
Query lastest products: (limited number of 10)<br>

### Query product by categoryId
Find product info by different categoryId.<br>

### Query procduct in some second classification<br>
Find product info in second classification by categorySecond id<br>

### Query Product info by product id<br>

## 1.1.4 Cart Module<br>

### add product to cart<br>
### remove the product from cart by using product id<br>
### clear the cart<br>

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/user_shopcart.png) <br>

## 1.1.5 Orders Module<br>

### generate a new order<br>
Add the order info to our database and clear the cart<br>

### Pay the money for your order<br>
1.online payment function(used yibao payment interface--but only support chinese banks)<br>
2.update the order state(1:not pay the money 2: have been paid the money <br>
			 3:have been delivery,but user does not received 4: transaction success)<br>
3.update the order info(set users' name,address and phone number)<br>

### query my orders<br>
Find all the orders by user id<br>

### See the order detail info
Find the order detail info by order id

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/user_orders.png) <br>

## 2.1.1 Admin Modules<br>

### Manage User<br>
I do not design this module. It is similar with the other modules.<br>

### Manage Primary Category<br>
1.Add Primary Category<br>
2.Edit Primary Category<br>
3.Delete Primary Category and also delete related second category.<br>
4.Find all primary category info.<br>

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/admin_category.png) <br>

### Manage Second Category <br>
1.Add Second Category<br>
2.Edit Second Category,we need to search all the primary category first.<br>
3.Delete Second Category .<br>
4.Find all Second category info,Paging query .<br>

### Manage products<br>
1.Add products and save related second category,upload image.<br>
2.Edit product info,edit image.<br>
3.Delete products<br>
4.Find All products,paging query.<br>

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/admin_product.png) <br>

### Manage Orders <br>
1.Find all orders and load line items asynchronously.

![](https://github.com/yuehengyu/SSH_shopping_website/blob/master/img/admin_order.png) <br>

### Filter
Set the interceptor, we need to log in to operate the background.

