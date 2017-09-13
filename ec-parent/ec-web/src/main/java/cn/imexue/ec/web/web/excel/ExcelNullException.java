package cn.imexue.ec.web.web.excel;

public class ExcelNullException extends ExcelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521318936872353466L;

	/**
	 * 1007,第row+1行,第cell+1列单元格不能为空
	 * @param row
	 * @param cell
	 */
	public ExcelNullException(int row,int cell) {
		super("excel.cell.null",++row,++cell);
	}

}
