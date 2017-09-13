package cn.imexue.ec.common.exception;

public class DataDuplicationException extends AppChkException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294865113641330578L;

	/**
	 * 1011
	 * @param params
	 */
	public DataDuplicationException(String params){
		super(1011, "system.data.duplication",params);
	}

}
