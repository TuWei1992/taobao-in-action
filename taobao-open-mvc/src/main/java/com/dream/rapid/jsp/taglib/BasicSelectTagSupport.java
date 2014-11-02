package com.dream.rapid.jsp.taglib;

import java.util.List;

import com.dream.common.model.AppCode;
import com.dream.common.model.AppCodeCriteria;
import com.dream.common.service.AppCodeService;


/**
 * 生成SELECT的JSPTAG实现类的基础类，提供公共的属性和方法
 * 
 * @version 1.0 2008-07-08
 * @author Frank
 * @since 1.4
 */
public abstract class BasicSelectTagSupport extends BasicTagSupport {

	private static final long serialVersionUID = 7412692551357460078L;
	/**
	 * 指定下拉框的第一行提示语言
	 */
	protected String prompt;
	/**
	 * 指定选中的元素值
	 */
	protected String selected;

	/**
	 * @param prompt The prompt to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * @param selected The selected to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	
	protected List<AppCode> getAppCodeList(String groupId) {
		List<AppCode> appCodeList;
		AppCodeService appCodeService= (AppCodeService) getBean("appCodeService");
		AppCodeCriteria criteria = new AppCodeCriteria();
		criteria.createCriteria().andGroupIdEqualTo(groupId);
		appCodeList = appCodeService.queryAllByCriteria(criteria);
		return appCodeList;
	}
}
