package com.dream.rapid.jsp.taglib;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.common.model.AppCode;
import com.dream.common.model.AppCodeCriteria;
import com.dream.common.service.AppCodeService;

/**
 * 生成指定类型的应用参数的显示名称的JSPTAG实现类
 * 
 * @version 1.0 2008-07-09
 * @author 
 * @since 1.4
 */
public class AppCodeNameTag extends BasicTagSupport {

	private static final long serialVersionUID = -4051055328721685753L;
	private final Logger log = LoggerFactory.getLogger(AppCodeNameTag.class);

	/**
	 * 指定保存当前应用参数列表的key的名称，或者生成HTML元素的名称
	 */
	private String name;
	/**
	 * 指定保存当前应用参数列表的作用域范围，可选"request","session","application"
	 */
	private String scope;
	/**
	 * 指定应用参数的类型
	 */
	private String groupId;
	/**
	 * 指定应用参数的值
	 */
	private String codeValue;
	/**
	 * 空值
	 */
	private String emptyValue;

	/**
	 * Process the start of this taglib.
	 * 
	 * @return int status
	 * @exception JspException
	 *                if a JSP exception has occurred
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTagInternal() throws JspException {
		Locale userLocale = pageContext.getRequest().getLocale();
		AppCode appCode = null;
		if ((codeValue == null || codeValue.equals("")) && emptyValue != null) {
			try {
				write(emptyValue);
			} catch (IOException io) {
				throw new JspException(io);
			}
			return super.doStartTag();
		}
		try {
             AppCodeService appCodeService= (AppCodeService) getBean("appCodeService");
             AppCodeCriteria criteria = new AppCodeCriteria();
             criteria.createCriteria().andGroupIdEqualTo(groupId).andCodeValueEqualTo(codeValue);
             appCode = appCodeService.queryByCriteria(criteria);
			if (appCode == null) {
				try {
					write(codeValue);
				} catch (IOException io) {
					throw new JspException(io);
				}

				return SKIP_BODY;
			}
		} catch (Exception e) {
			log.error("Error when get appcode. " + e.getMessage());

			// 如果去应用参数失败则直接显示应用参数的值
			try {
				write(codeValue);
			} catch (IOException io) {
				throw new JspException(io);
			}

			return SKIP_BODY;
		}

		// 如果指定要保存作用域则进行保存，否则输出参数名称
		if (scope != null) {

			if (null == name || "".equals(name)) {
				throw new JspException(
						"Attribute 'name' can not be null or '' when attribute 'toScope' is not null");
			}

			if (scope.equals("page")) {
				pageContext.setAttribute(name, appCode.getCodeNameCn());
			} else if (scope.equals("request")) {
				pageContext.getRequest().setAttribute(name,
						appCode.getCodeNameCn());
			} else if (scope.equals("session")) {
				pageContext.getSession().setAttribute(name,
						appCode.getCodeNameCn());
			} else if (scope.equals("application")) {
				pageContext.getServletContext().setAttribute(name,
						appCode.getCodeNameCn());
			} else {
				throw new JspException(
						"Attribute 'scope' must be: page, request, session or application");
			}
		} else {
			try {
				write((String) appCode.getCodeNameCn());
			} catch (IOException io) {
				throw new JspException(io);
			}
		}

		return SKIP_BODY;
	}

	/**
	 * @param name
	 *            The name to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param scope
	 *            The scope to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @param codeType
	 *            The codeType to set
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @param codeValue
	 *            The codeValue to set
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	/**
	 * @return the emptyValue
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public String getEmptyValue() {
		return emptyValue;
	}

	/**
	 * @param emptyValue
	 *            The emptyValue to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setEmptyValue(String emptyValue) {
		this.emptyValue = emptyValue;
	}

}
