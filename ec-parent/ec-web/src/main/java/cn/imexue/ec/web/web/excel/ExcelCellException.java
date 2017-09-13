package cn.imexue.ec.web.web.excel;

public class ExcelCellException extends ExcelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521318936872353466L;

	/**
	 * 1007,第row+1行,第cell+1列单元格无法读取,请检查单元格格式
	 * @param row
	 * @param cell
	 */
	public ExcelCellException(int row,int cell) {
		super( "excel.cell.error",++row,++cell);
	}

}
