/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.imexue.ec.common.model.page;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分页类
 * 
 * @author ThinkGem
 * @version 2013-7-2
 * @param <T>
 */
public final class Page<T> {

	private int	pageNo = 1;	// 当前页码
	private int	pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）

	public static final String PAGEKEY = "page";

	@JsonBackReference
	private Map<String, Object>	search = new HashMap<String, Object>();

	private long count; // 总记录数，设置为“-1”表示不查询总数

	private int first; // 首页索引
	private int last; // 尾页索引

	private boolean firstPage; // 是否是第一页
	private boolean lastPage; // 是否是最后一页

	private List<T>	result;

	private String orderBy;
	private OrderType orderType	= OrderType.DESC;

	public Page() {

		this.search.put(PAGEKEY, this);
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Page(int pageNo) {

		this.pageNo = pageNo;
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Page(int pageNo, int pageSize) {

		this.setPageNo(pageNo);
		this.pageSize = pageSize;
	}

	/**
	 * 初始化参数
	 */
	public void initialize() {

		// 1
		this.first = 1;

		this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

		if (this.count % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		// 2
		if (this.pageNo < this.first) {// 如果当前页小于首页
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last) {// 如果当前页大于尾页
			this.pageNo = this.last;
		}

	}

	/**
	 * 获取设置总数
	 * 
	 * @return
	 */
	public long getCount() {

		return count;
	}

	public long getTotal() {

		return count;
	}

	/**
	 * 设置数据总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {

		this.count = count;
		if (pageSize >= count) {
			pageNo = 1;
		}
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

		this.pageSize = pageSize <= 0 ? 10 : pageSize;// > 500 ? 500 : pageSize;
	}

	/**
	 * 首页索引
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getFirst() {

		return first;
	}

	/**
	 * 尾页索引
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getLast() {

		return last;
	}

	/**
	 * 获取页面总数
	 * 
	 * @return getLast();
	 */
	public int getTotalPage() {

		return getLast();
	}

	/**
	 * 是否为第一页
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isFirstPage() {

		return firstPage;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isLastPage() {

		return lastPage;
	}

	/**
	 * 上一页索引值
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getPrev() {

		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 下一页索引值
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getNext() {

		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult() {

		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getCount()) {
			int f = getCount() % getPageSize() == 0 ? 1 : 0;
			int p = (int) (getCount() / getPageSize() - f);
			firstResult = p * getPageSize();
		}
		return firstResult;
	}

	public Map<String, Object> getSearch() {

		return search;
	}

	public void setSearch(Map<String, Object> search) {

		Assert.notNull(search, "传入map不能为null");
		this.search = search;
		this.search.put(PAGEKEY, this);
	}

	public List<T> getList() {

		return result;
	}

	public void setList(List<T> list) {

		this.result = list;
		initialize();
	}

	public String getOrderBy() {

		return orderBy;
	}

	public void setOrderBy(String orderBy) throws Exception {

		checkSQLInjection(orderBy);
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {

		return orderType;
	}

	public void setOrderType(OrderType orderType) {

		this.orderType = orderType;
	}

	/**
	 * 检查order列，防止sql注入
	 * 
	 * @param clazz
	 */
	public void checkOrder(Class<T> clazz) {

		DefaultOrderBy annotation = AnnotationUtils.findAnnotation(clazz, DefaultOrderBy.class);
		// 没有注解就用默认的
		if (annotation == null) {
			if (StringUtil.isEmpty(orderBy)) {
				orderBy = (String) AnnotationUtils.getDefaultValue(DefaultOrderBy.class);
			}
			if (orderType == null) {
				orderType = (OrderType) AnnotationUtils.getDefaultValue(DefaultOrderBy.class, "orderType");
			}
		} else {
			if (StringUtil.isEmpty(this.orderBy)) {
				this.orderBy = annotation.value();
			}
			if (orderType == null) {
				orderType = annotation.orderType();
			}
		}
		// orderBy需要对应具体列
		Field field = FieldUtils.getField(clazz, orderBy, true);
		if (field == null) {
			this.orderBy = null;
		} else {
			this.search.put("orderBy", StringUtil.humpToLine(orderBy));
			this.search.put("orderType", orderType.name());
		}

	}

	public enum OrderType {
		ASC, DESC;
	}

	/**
	 * 检查sql注入
	 * 
	 * @throws Exception
	 */
	private void checkSQLInjection(String sql) throws Exception {

		if (StringUtil.isEmpty(sql)) {
			return;
		}
		if (sql.matches("(\\w)+")) {
			return;
		} else {
			throw new AppChkException(1005, "app.page.sort.err", sql);
		}
	}

}
