package com.dream.rapid.jsp.taglib;

import java.io.IOException;
import java.util.Locale;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

/**
 * JSP标签实现类的基础类，提供一些公共的方法
 * 
 * @version 1.0 2008-07-08
 * @author
 * @since 1.4
 */
public abstract class BasicTagSupport extends HtmlEscapingAwareTag {

	private static final long serialVersionUID = -4409512824645947645L;

	/**
	 * 指定保存当前应用参数列表的key的名称，或者生成HTML元素的名称
	 */
	protected String name;
	/**
	 * 用户自定义属性，将直接作为select的属性输出
	 */
	protected String customerAtt;

	/**
	 * @param str
	 * @throws IOException
	 */
	protected void write(String str) throws IOException {
		pageContext.getOut().write(str);
	}

	/**
	 * 根据名字取得Spring配置中的Bean
	 * 
	 * @param name
	 * @return bean
	 */
	protected Object getBean(String name) {

		return this.getRequestContext().getWebApplicationContext()
				.getBean(name);
	}

	/**
	 * 根据名字取得Spring配置中的Bean
	 * 
	 * @param name
	 * @return bean
	 */
	@SuppressWarnings("unchecked")
	protected Object getBean(String name, Class requiredType) {
		return this.getRequestContext().getWebApplicationContext()
				.getBean(name, requiredType);
	}

	protected String getText(String code) {
		return this
				.getRequestContext()
				.getWebApplicationContext()
				.getMessage(code, null,
						this.pageContext.getRequest().getLocale());
	}

	protected String getText(String code, Locale locale) {
		return this.getRequestContext().getWebApplicationContext()
				.getMessage(code, null, locale);
	}

	protected String getText(String code, Object[] args, Locale locale) {
		return this.getRequestContext().getWebApplicationContext()
				.getMessage(code, args, locale);
	}

	protected String getText(String code, Object[] args, String defaultMessage,
			Locale locale) {
		return this.getRequestContext().getWebApplicationContext()
				.getMessage(code, args, defaultMessage, locale);
	}

	/**
	 * 将Locale对象转换成如"zh_CN"、"en"、"zh_TW"等的字符串
	 * 
	 * @param locale
	 * @return
	 */
	protected String getLocaleStr(Locale locale) {
		String language = locale.getLanguage();
		String country = locale.getCountry();

		if ("".equals(country)) {
			return language;
		}

		return language + "_" + country;
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
	 * @param customerAtt
	 *            The customerAtt to set
	 * @jsp.attribute required="false" rtexprvalue="false"
	 */
	public void setCustomerAtt(String customerAtt) {
		this.customerAtt = customerAtt;
	}

	/**
	 * Release aquired resources to enable tag reusage.
	 * 
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release() {
		super.release();
	}

	public boolean isEmpty(String str) {
		return str == null || "str".equals(str);
	}

	public boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
