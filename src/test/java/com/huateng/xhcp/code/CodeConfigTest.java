/**
 * 
 */
package com.huateng.xhcp.code;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 配置信息
 * @author sam.pan
 *
 */
public class CodeConfigTest {
	public static String moduleId = "gallerymgr";//module_id
	public static String modelSubpackage = "product";//包名
	public static String modelObject = "MerchGallery";//对象名称
	public static String tableName = "产品";//功能名称
	public static String tableKey = "gallery_id";//主键
	public static String tableUKey = StringUtils.capitalize(tableKey);
	public static String tableKeyName = "图片ID";//主键名称
	public static String table = "tbl_merch_gallery";//表名称
	public static String author = "sam.pan";//开发人
	public static String requestParam = "/mgr/product/gallery";//url路径
	public static String jspPath = "product";//文件存在路径，基于jsp/js下的
	public static String cfrmPath = "E:/work/workspace/idea/xhcp/src/main";
	
	//==========================查询表字段=============================//
	public static String []fieldNames = JdbcManager.queryColumns2(table).toArray(new String[]{});
	
	public static String conditionModel = "";
	
	//==========================查询条件中的=============================//
	public static String condition = "";
	public static String []conditions = {
			"gallery_id",
			"merch_id",
			"name",
			"file_name"
	};
	public static String []conditionNames = {
			"图片ID",
			"商品ID",
			"图片名称",
			"图片文件名称"
	};
	
	//==========================新增页面中的=============================//
	public static String add_fields = "";
	public static String[] addFields = {
			"gallery_id",
			"merch_id",
			"name",
			"file_name",
			"path"
	};
	
	public static String[] addFieldNames = {
			"图片ID",
			"商品ID",
			"图片名称",
			"图片文件名称",
			"文件路径"
	};
	//==========================查询表格显示列=============================//
	public static String []columnNames = {
			"gallery_id",
			"merch_id",
			"name",
			"file_name",
			"path"
	};
	public static String []tableHeaders = {
			"图片ID",
			"商品ID",
			"图片名称",
			"图片文件名称",
			"文件路径"
	};


	public static String validate = "";
	public static String jsvalidate = "";
	
	static{
		//==========================模板=============================//
		conditionModel += "			                	    <div class=\"col-md-6\">\r\n";
		conditionModel += "			                	    	<%-- {0} --%>\r\n";
		conditionModel += "			                		    <div class=\"form-group\">\r\n";
		conditionModel += "			                		        <label class=\"control-label\" for=\"{1}\"><spring:message code=\"{0}\" /></label>\r\n";
		conditionModel += "			                		        <input type=\"text\" name=\"{1}\" class=\"form-control\" id=\"{1}\" placeholder=\"<spring:message code=\"{0}\" />\">\r\n";
		conditionModel += "			                		    </div>\r\n";
		conditionModel += "			                		</div>\r\n";
		
		//==========================查询条件中的=============================//
		condition += "<div class=\"row\">\r\n";
		for (int i = 0; i < conditions.length; i++) {
			condition += StringUtils.replace(conditionModel, "{0}", conditionNames[i]);
			condition = StringUtils.replace(condition, "{1}", conditions[i]);
			
			if(i != 0 && i % 2 == 0){
				condition += "			                	</div>\r\n";
				condition += "			                	<div class=\"row\">\r\n";
			}
		}
		condition += "			                	</div>\r\n";
		
		//==========================新增页面中的=============================//
		add_fields += "<div class=\"row\">\r\n";
		for (int i = 0; i < addFields.length; i++) {
			add_fields += StringUtils.replace(conditionModel, "{0}", addFieldNames[i]);
			add_fields = StringUtils.replace(add_fields, "{1}", addFields[i]);
			
			if(i != 0 && i % 2 == 0){
				add_fields += "			                	</div>\r\n";
				add_fields += "			                	<div class=\"row\">\r\n";
			}
		}
		add_fields += "			                	</div>\r\n";
		
		//==========================Controller校验字段生成=============================//
		Map<String, Integer> columns = JdbcManager.queryColumns3(table);
		
		String m = "";
		m += "		String {0} = " + StringUtils.uncapitalize(modelObject) + ".get{3}();\r\n";
		m += "		int {0}Length = com.huateng.xhcp.util.Validator.mysql({0});\r\n";
		m += "		if({0}Length > {2}){\r\n";
		m += "			return ResponseInfo.fail(\"{1}必须少于{2} 个字符!\");\r\n";
		m += "		}\r\n";
		for (int i = 0; i < addFields.length; i++) {
			validate += StringUtils.replace(m, "{0}", addFields[i]);
			validate = StringUtils.replace(validate, "{1}", addFieldNames[i]);
			validate = StringUtils.replace(validate, "{2}", columns.get(addFields[i])+"");
			validate = StringUtils.replace(validate, "{3}", StringUtils.capitalize(addFields[i]));
			
			validate += "\r\n";
		}
		
		String m2 = "		_field.{0} = Validator.validate(false, {max:{1}});\r\n";
		for (int i = 0; i < addFields.length; i++) {
			jsvalidate += StringUtils.replace(m2, "{0}", addFields[i]);
			jsvalidate = StringUtils.replace(jsvalidate, "{1}", columns.get(addFields[i])+"");
		}
	}
	
}
