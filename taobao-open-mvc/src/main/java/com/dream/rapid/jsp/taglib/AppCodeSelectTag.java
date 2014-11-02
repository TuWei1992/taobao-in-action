package com.dream.rapid.jsp.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dream.common.model.AppCode;

/**
 * 增加filter属性
 * 
 * @version 1.02 2010-12-24
 * @author huchao@finance.net
 * @since 1.4
 * 
 * 增加disabled属性
 * 
 * @version 1.01 2010-12-16
 * @author huchao@finance.net
 * @since 1.4
 * 
 * 生成指定类型的应用参数的下拉列表框的JSPTAG实现类，可将参数列表以select的形式显示或者将参数列表保存在指定的作用域中
 * 
 * @version 1.0 2008-07-08
 * @author
 * @since 1.4
 */
public class AppCodeSelectTag extends BasicSelectTagSupport {

	private static final long serialVersionUID = 1243014122577627230L;

	private final Log log = LogFactory.getLog(AppCodeSelectTag.class);

	/**
	 * 指定保存当前应用参数列表的作用域范围，可选"request","session","application"
	 */
	private String scope;

	/**
	 * 指定应用参数的类型
	 */
	private String groupId;

	/**
	 * 是否禁用
	 */
	private String disabled;

	/**
	 * 过滤option value列表，使用,分割
	 */
	private String filter;

	/**
	 * Process the start of this taglib.
	 * 
	 * @return int status
	 * @exception JspException
	 *                if a JSP exception has occurred
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTagInternal() throws JspException {
		List filterList = new ArrayList();
		String[] ft = null == filter ? new String[0] : filter.split(",");
		for (int i = 0; i < ft.length; i++) {
			filterList.add(ft[i]);
		}
//		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
//
//		if (selected != null) {
//			selected = eval.evalString("default", selected);
//		}

		Locale userLocale = pageContext.getRequest().getLocale();
		List<AppCode> appCodeList = null;

		try {
			appCodeList = getAppCodeList(groupId);
		} catch (Exception e) {
			log.error("Error when get appcode. " + e.getMessage());

			StringBuffer sb = new StringBuffer();
			sb
					.append("<select "
							+ ("disabled".equals(disabled)
									|| "true".equals(disabled) ? " disabled"
									: "") + " name=\"" + name + "\" id=\""
							+ name + "\" class=\"select\">\n");
			sb.append("<option value=\"\">Get app code error!</option>");
			sb.append("</select>");

			try {
				write(sb.toString());
			} catch (IOException io) {
				throw new JspException(io);
			}

			return super.doStartTag();
		}
		if (appCodeList == null){
		    appCodeList = new ArrayList();
		}
		
		// 如果指定要保存作用域则进行保存，否则输出下拉框代码
		if (scope != null) {

			if (null == name || "".equals(name)) {
				throw new JspException(
						"Attribute 'name' can not be null or '' when attribute 'toScope' is not null");
			}

			if (scope.equals("page")) {
				pageContext.setAttribute(name, appCodeList);
			} else if (scope.equals("request")) {
				pageContext.getRequest().setAttribute(name, appCodeList);
			} else if (scope.equals("session")) {
				pageContext.getSession().setAttribute(name, appCodeList);
			} else if (scope.equals("application")) {
				pageContext.getServletContext().setAttribute(name, appCodeList);
			} else {
				throw new JspException(
						"Attribute 'scope' must be: page, request, session or application");
			}
		} else {
			StringBuffer sb = new StringBuffer();
			sb
					.append("<select"
							+ ("disabled".equals(disabled)
									|| "true".equals(disabled) ? " disabled"
									: "") + " name=\"" + name + "\" id=\""
							+ name + "\" class=\"select\" "
							+ (null == customerAtt ? "" : customerAtt) + " >");

			if (prompt != null) {
				sb.append("<option value=\"\" selected=\"selected\">");
//				sb.append(eval.evalString("prompt", prompt) + "</option>");
			}

			for (int i = 0; i < appCodeList.size(); i++) {
				AppCode appCode = appCodeList.get(i);
				if (filterList.contains(appCode.getCodeValue()))
					continue;
				sb.append("    <option value=\"" + appCode.getCodeValue()
						+ "\"");

				if ((selected != null)
						&& selected.equals(appCode.getCodeValue())) {
					sb.append(" selected=\"selected\"");
				}

//				sb.append(">" + appCode.getCodeName() + "</option>");
			}

			sb.append("</select>");

			try {
				write(sb.toString());
			} catch (IOException io) {
				throw new JspException(io);
			}
		}

		return super.doStartTag();
	}

	/**
	 * @param scope
	 *            The scope to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setToScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @param codeType
	 *            The codeType to set
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setCodeType(String codeType) {
		this.groupId = codeType;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
