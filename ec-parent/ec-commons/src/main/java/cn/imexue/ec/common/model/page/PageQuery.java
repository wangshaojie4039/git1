package cn.imexue.ec.common.model.page;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import cn.imexue.ec.common.model.page.Page.OrderType;
import cn.imexue.ec.common.util.StringUtil;

public class PageQuery {

	public static final Integer	defaultPageSize	= 20;

	private Integer				pageNo			= 1;

	private Integer				pageSize		= defaultPageSize;

	private String				orderBy;

	private String				orderType		= OrderType.DESC.name();

	public Map<String, Object> getSearch() {

		Map<String, Object> map = new HashMap<>();
		Class<? extends PageQuery> class1 = this.getClass();
		Field[] fields = FieldUtils.getAllFields(class1);
		for (Field field : fields) {
			Object object = null;
			try {
				object = FieldUtils.readField(this, field.getName(), true);
			} catch (IllegalAccessException e) {
			}
			Class<?> type = field.getType();
			if (type.isAssignableFrom(String.class)) {
				if (object != null) {
					object = ((String) object).trim();
					if (StringUtil.isBlank((String) object)) {
						object = null;
					}
				}
			}

			map.put(field.getName(), object);
		}

		return map;
	}

	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public int getPageNo() {

		return pageNo;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {

		this.pageNo = pageNo;
	}

	/**
	 * 获取页面大小
	 * 
	 * @return
	 */
	public int getPageSize() {

		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {

		this.pageSize = pageSize <= 0 ? defaultPageSize : pageSize;// > 500 ? 500 : pageSize;
	}

	public String getOrderBy() {

		return orderBy;
	}

	public void setOrderBy(String orderBy) throws Exception {

		this.orderBy = orderBy;
	}

	public String getOrderType() {

		return orderType;
	}

	public void setOrderType(String orderType) {

		this.orderType = orderType;
	}
}
