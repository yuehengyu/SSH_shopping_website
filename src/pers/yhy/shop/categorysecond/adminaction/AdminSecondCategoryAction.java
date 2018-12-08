package pers.yhy.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.category.service.CategoryService;
import pers.yhy.shop.category.vo.Category;
import pers.yhy.shop.categorysecond.service.CategorySecondService;
import pers.yhy.shop.categorysecond.vo.CategorySecond;
import pers.yhy.shop.utils.PageBean;

/**
 * second classification manage(admin)
 * 
 * @author yuehe
 *
 */
public class AdminSecondCategoryAction extends ActionSupport implements ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	private CategorySecondService categorySecondService;
	private Integer page;
	private CategoryService categoryService;
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	/**
	 * find all second classification
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * go to the jsp page that add second classification and search all the primary
	 * classification
	 * 
	 * @return
	 */
	public String addPage() {
		// find all primary classification
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}

	/**
	 * add second classification to databse
	 * 
	 * @return
	 */
	public String save() {
		Category category = categoryService.findByCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/**
	 * delete second category
	 * 
	 * @return
	 */
	public String delete() {
		// if second has a relative data, should be search first and then delete it,set
		// cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/**
	 * go to the jsp page for editing the second category
	 * 
	 * @return
	 */
	public String edit() {
		// get second category
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// get all the primary category
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}

	/**
	 * update second category info
	 * 
	 * @return
	 */
	public String update() {
		Category category = categoryService.findByCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
