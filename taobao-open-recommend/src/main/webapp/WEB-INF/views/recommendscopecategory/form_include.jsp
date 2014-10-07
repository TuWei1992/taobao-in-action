<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${recommendScopeCategory.id}"/>

	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendScopeCategory.ALIAS_SHOP_ID%>:
		</td>		
		<td>
		<form:input path="shopId" id="shopId" cssClass="required " maxlength="10" />
		<font color='red'><form:errors path="shopId"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendScopeCategory.ALIAS_CAT_ID%>:
		</td>		
		<td>
		<form:input path="catId" id="catId" cssClass="required " maxlength="10" />
		<font color='red'><form:errors path="catId"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendScopeCategory.ALIAS_CAT_NAME%>:
		</td>		
		<td>
		<form:input path="catName" id="catName" cssClass="required " maxlength="30" />
		<font color='red'><form:errors path="catName"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_TIME%>:
		</td>		
		<td>
		<input value="${recommendScopeCategory.lastModifiedTimeString}" onclick="WdatePicker({dateFmt:'<%=RecommendScopeCategory.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeString" name="lastModifiedTimeString"  maxlength="0" class="required " />
		<font color='red'><form:errors path="lastModifiedTime"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendScopeCategory.ALIAS_LAST_MODIFIED_BY%>:
		</td>		
		<td>
		<form:input path="lastModifiedBy" id="lastModifiedBy" cssClass="validate-integer " maxlength="19" />
		<font color='red'><form:errors path="lastModifiedBy"/></font>
		</td>
	</tr>	
	
		