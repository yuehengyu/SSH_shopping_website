package pers.yhy.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import pers.yhy.shop.category.service.CategoryService;
import pers.yhy.shop.category.vo.Category;

/**
 * primary classification admin manage
 * 
 * @author Hengyu Yue
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	private Category category = new Category();
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}

	/**
	 * find all primary classifications
	 * 
	 * @return
	 */
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	/**
	 * save primary classification to our database
	 * 
	 * @return
	 */
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}

	/**
	 * delete a primary classification item from our database
	 * 
	 * @return
	 */
	public String delete() {
		// delete the primary classification ,we also need to the second classification
		// under the primary
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	/**
	 * edit the primary classification
	 * @return
	 */
	public String edit() {
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	/**
	 * update primary classification info 
	 * @return
	 */
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
