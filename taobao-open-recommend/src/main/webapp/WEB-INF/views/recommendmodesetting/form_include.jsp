<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="shopId" name="shopId" value="${recommendModeSetting.shopId}"/>

	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendModeSetting.ALIAS_IS_SALED_FIRST%>:
		</td>		
		<td>
		<form:input path="isSaledFirst" id="isSaledFirst" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isSaledFirst"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendModeSetting.ALIAS_SALED_NUM%>:
		</td>		
		<td>
		<form:input path="saledNum" id="saledNum" cssClass="validate-integer max-value-2147483647" maxlength="10" />
		<font color='red'><form:errors path="saledNum"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendModeSetting.ALIAS_IS_STORAGE_FILTERED%>:
		</td>		
		<td>
		<form:input path="isStorageFiltered" id="isStorageFiltered" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isStorageFiltered"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendModeSetting.ALIAS_STORAGE_NUM%>:
		</td>		
		<td>
		<form:input path="storageNum" id="storageNum" cssClass="" maxlength="3" />
		<font color='red'><form:errors path="storageNum"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendModeSetting.ALIAS_IS_OFFSHELVES_FILTERED%>:
		</td>		
		<td>
		<form:input path="isOffshelvesFiltered" id="isOffshelvesFiltered" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isOffshelvesFiltered"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendModeSetting.ALIAS_OFFSHELVES_TIME%>:
		</td>		
		<td>
		<form:input path="offshelvesTime" id="offshelvesTime" cssClass="validate-integer max-value-2147483647" maxlength="10" />
		<font color='red'><form:errors path="offshelvesTime"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=RecommendModeSetting.ALIAS_IS_ONSHELVES_FILTERED%>:
		</td>		
		<td>
		<form:input path="isOnshelvesFiltered" id="isOnshelvesFiltered" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isOnshelvesFiltered"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendModeSetting.ALIAS_LAST_MODIFIED_BY%>:
		</td>		
		<td>
		<form:input path="lastModifiedBy" id="lastModifiedBy" cssClass="" maxlength="8" />
		<font color='red'><form:errors path="lastModifiedBy"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendModeSetting.ALIAS_LAST_MODIFIED_TIME%>:
		</td>		
		<td>
		<input value="${recommendModeSetting.lastModifiedTimeString}" onclick="WdatePicker({dateFmt:'<%=RecommendModeSetting.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeString" name="lastModifiedTimeString"  maxlength="0" class="" />
		<font color='red'><form:errors path="lastModifiedTime"/></font>
		</td>
	</tr>	
	
		