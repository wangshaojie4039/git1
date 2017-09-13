package cn.imexue.ec.web.web.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.util.StringUtil;

@Component
public class ExcelUtils {

	private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	
	private static final Integer MAX_ROW = 1000;
	
	private static Validator validator2;
	
	@Resource
	private Validator validator;
	
	public static <T> List<T> excelUpload(MultipartFile file,Class<T> clazz,int firstLine){
		String path = FileUtils.getTempDirectoryPath();
		path = path + File.separator + UUID.randomUUID().toString();
		File file2 = new File(path);
		try {
			file.transferTo(file2);
			return excelUpload(file2, clazz,firstLine);
		} catch (IllegalStateException | IOException | IllegalAccessException e) {
			log.error("解析错误");
		}finally{
			file2.deleteOnExit();
		}
		return null;
	}
	
	private static <T> List<T> excelUpload(File file,Class<T> clazz,int firstLine) throws IOException, IllegalAccessException{
		List<T> result = new ArrayList<T>();
		//获取所有有用列
		List<Row> rows = getRows(file, firstLine);
		if(CollectionUtils.isEmpty(rows)){
			throw new ExcelException("excel.no.data");
		}
		//获取第一列（头）
		Row r = rows.get(0);
		//获取有注解的字段
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, ExcelImportProperty.class);
		Map<Field, Integer> columnMap = new HashMap<>();
		for(Field f : fields){
			ExcelImportProperty columnName = getColumnName(f);
			if(columnMap.get(columnName.columnName())!=null){
				//判重，防止误写
				throw new RuntimeException(columnName.columnName()+"重复了");
			}
			//把列头和注解对应的name对应
			for(Cell cell: r){
				CellType cellTypeEnum = cell.getCellTypeEnum();
				if(cellTypeEnum.equals(CellType.STRING)){
					//如果是string则可以对应
					String stringCellValue = cell.getStringCellValue();
					if(stringCellValue.equalsIgnoreCase(columnName.columnName())){
						//放入map中
						columnMap.put(f, cell.getColumnIndex());
					}
				}
			}
		}
		if(columnMap.size()!=fields.size()){
			throw new ExcelException("excel.column.error");
		}
		log.debug("导入数据，列：{}",columnMap);
		//移除头
		rows.remove(0);
		//逐行解析
		for(Row row : rows){
			T instantiate = BeanUtils.instantiate(clazz);
			for(Field f : fields){
				Integer integer = columnMap.get(f);
				if(integer==null){
					throw new ExcelException("excel.error");
				}
				Object value = getValueFromRow(row, integer, f.getType());
				if(value!=null){
					//不为null则
					FieldUtils.writeField(f, instantiate, value,true);
				}else{
					ExcelImportProperty annotation = f.getAnnotation(ExcelImportProperty.class);
					if(!annotation.nullable()){
						//不能为null
						throw new ExcelNullException(row.getRowNum(), integer);
					}
				}
			}
			if(instantiate instanceof ExcelValidate){
				((ExcelValidate) instantiate).validate();
			}
			if(validator2 != null){
				try{
					Set<ConstraintViolation<T>> validate = validator2.validate(instantiate);
					Optional<ConstraintViolation<T>> findFirst = validate.stream().findFirst();
					if(findFirst.isPresent()){
						throw new ExcelException("excel.validate.error",row.getRowNum()+1, findFirst.get().getMessage());
					}
				}catch(ValidationException e){
					String message = e.getMessage();
					throw new ExcelException("excel.validate.error",row.getRowNum()+1, message);
				}
			}
			result.add(instantiate);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @exception 1007
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValueFromRow(Row row,int index,Class<T> type){
		Cell cell = row.getCell(index);
		if(cell==null){
			return null;
		}
		CellType cellTypeEnum = cell.getCellTypeEnum();
		if(CellType._NONE.equals(cellTypeEnum)){
			return null;
		}else if(CellType.BLANK.equals(cellTypeEnum)){
			return null;
		}else if(CellType.BOOLEAN.equals(cellTypeEnum)){
			if(type.equals(Boolean.class)){
				return (T) Boolean.valueOf(cell.getBooleanCellValue());
			}
		}else if(CellType.ERROR.equals(cellTypeEnum)){
			return null;
		}else if(CellType.FORMULA.equals(cellTypeEnum)){
			return null;
		}else if(CellType.NUMERIC.equals(cellTypeEnum)){
			double value = cell.getNumericCellValue();
			if(type.isAssignableFrom(Integer.class)){
				return (T) Integer.valueOf((int)value);
			}else if(type.isAssignableFrom(Double.class)){
				return (T) Double.valueOf(value);
			}else if(type.isAssignableFrom(Long.class)){
				return (T) Long.valueOf((long)value);
			}else if(type.isAssignableFrom(Byte.class)){
				return (T) Byte.valueOf((byte)value);
			}else if(type.isAssignableFrom(BigDecimal.class)){
				return (T) new BigDecimal(value);
			}else if(type.isAssignableFrom(String.class)){
				String format = new DecimalFormat("#").format(value);
				return (T) format;
			}
		}else if(CellType.STRING.equals(cellTypeEnum)){
			if(type.isAssignableFrom(String.class)){
				String trim = cell.getStringCellValue().trim();
				if(StringUtil.isBlank(trim)){
					return null ;
				}else{
					return (T) trim;
				}
			}
		}
		throw new ExcelCellException(row.getRowNum(),index);
	}
	
	
	private static ExcelImportProperty getColumnName(Field field){
		
		return field.getAnnotation(ExcelImportProperty.class);
	}
	
	/**
	 * 从xls中获取rows
	 * @param file
	 * @param start
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private static List<Row> getRows(File file,int start) throws IOException{
		FileInputStream inputStream = FileUtils.openInputStream(file);
		
		Workbook wb =  null;
		try{
			wb = new HSSFWorkbook(inputStream);
		}catch(OfficeXmlFileException e){
			wb = new XSSFWorkbook(inputStream);
		}
		
		Sheet sheet = wb.getSheetAt(0);
		List<Row> rows = getRows(sheet, start);
		
		if(rows.size()>MAX_ROW+1){
			throw new ExcelException("excel.too.large", MAX_ROW);
		}
		
		return rows.stream().filter(x->{
			for(Cell cell : x){
				CellType cellTypeEnum = cell.getCellTypeEnum();
				if(cellTypeEnum.equals(CellType.STRING)){
					if(cell!=null&&!StringUtil.isEmpty(cell.getStringCellValue())){
						return true;
					}
				}else{
					return true;
				}
			}
			
			return false;
		}).collect(Collectors.toList());
	}
	
	private static List<Row> getRows(Sheet sheet,int start){
		
		List<Row> rows = new ArrayList<>();
		while(sheet.getRow(start-1)!= null){
			rows.add(sheet.getRow(start-1));
			start++;
		}
		return rows.stream().filter(x->{
			AtomicBoolean b = new AtomicBoolean(false);
			x.forEach(y->{
				if(y!= null&&(y.getCellTypeEnum() != CellType.BLANK)){
//					log.debug("值:{},类型:{}",y.toString(),y.getCellTypeEnum());
					b.set(true);
				}
			});
			return b.get();
		}).collect(Collectors.toList());
	}
	
//	public static void main(String[] args) throws IOException, IllegalAccessException {
//		File f = new File("E:\\temp\\幼儿信息导入模板.xls");
//		List<ChildExcel> list = excelUpload(f, ChildExcel.class, 3);
//		log.debug("list={}",list);
//	}

	@SuppressWarnings("static-access")
	@PostConstruct
	public void afterPropertiesSet() {
		this.validator2 = validator;
	}

	public static void main(String[] args) {
		 NumberFormat.getInstance().format(3214567890d);
		 String format =new DecimalFormat("#").format(3214567890d);
//		 String format =DecimalFormat.getInstance().format(3214567890d);
		System.out.println(format);
		
	}
}
