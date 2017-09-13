package cn.imexue.ec.common.exception;


public class ExcelAppException extends AppChkException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 11232432531234L;

	private String name;
	
	public ExcelAppException(AppChkException e,String name) {
		super(1007,e.getMessage(), e.getParam());
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
