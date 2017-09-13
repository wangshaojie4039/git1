package cn.imexue.ec.web.web.excel;

import cn.imexue.ec.common.exception.AppChkException;

public class ExcelException extends AppChkException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3706222398014241091L;

	public ExcelException( String message, Object... params) {
		super(1007, message, params);
	}
	
}
