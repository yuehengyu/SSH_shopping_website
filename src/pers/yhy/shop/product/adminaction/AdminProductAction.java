package pers.yhy.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import pers.yhy.shop.categorysecond.service.CategorySecondService;
import pers.yhy.shop.categorysecond.vo.CategorySecond;
import pers.yhy.shop.product.service.ProductService;
import pers.yhy.shop.product.vo.Product;
import pers.yhy.shop.utils.PageBean;

/**
 * product manage by admin user
 * 
 * @author yuehe
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	private Product product = new Product();

	@Override
	public Product getModel() {
		return product;
	}

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// upload image
	private File upload;// the file that we upload
	private String uploadFileName;// get the file name
	private String uploadContextType;// the type of our file---- Mime

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/**
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * go to add product page
	 * 
	 * @return
	 */
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}

	/**
	 * save the product to our database
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		CategorySecond categorySecond = categorySecondService.findByCsid(csid);
		product.setCategorySecond(categorySecond);
		Date currDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = simpleDateFormat.format(currDate);
		product.setPdate(currTime);
		if (upload != null) {
			// get the file path
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// create a file
			File diskFile = new File(realPath + "//" + uploadFileName);
			// upload file
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "save";
	}

	/**
	 * delete product item by pid
	 * 
	 * @return
	 */
	public String delete() {
		product = productService.findByPid(product.getPid());
		productService.delete(product);
		// also need to delete the image
		String path = product.getImage();
		if (path != null) {
			String realpath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realpath);
			file.delete();
		}
		return "deleteSuccess";
	}

	/**
	 * go to edit page
	 * 
	 * @return
	 */
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	/**
	 * edit info and save to database
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException {
		// edit category secind id
		CategorySecond categorySecond = categorySecondService.findByCsid(csid);
		product.setCategorySecond(categorySecond);
		// edit date
		Date currDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = simpleDateFormat.format(currDate);
		product.setPdate(currTime);
		// edit image
		if (upload != null) {
			// delete origianl image
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/" + path));
			file.delete();
			// get the file path
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// create a file
			File diskFile = new File(realPath + "//" + uploadFileName);
			// upload file
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}

}
