package cn.imexue.ec.common.exception;

/**
 * @error 1010
 * @author hl
 *
 */
public class DataNoFountException extends AppChkException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294865113641330578L;

	/**
	 * @exception 1011
	 * @param params
	 */
	public DataNoFountException(String params){
		super(1010, "system.data.nofount",params);
	}

}
