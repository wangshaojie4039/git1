package cn.imexue.ec.web.web.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ExcelExportUtil {

	private static final Logger log = LoggerFactory.getLogger(ExcelExportUtil.class);
	
	
	public static <T> void export(String filename,List<T> list,HttpServletResponse response,Class<T> clazz){
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(filename.getBytes("GB2312"), "8859_1"));// 设定输出文件头
			response.setContentType("application/msexcel");
			export(list, response.getOutputStream(),clazz);
		} catch (IOException e) {
			log.error("无法打开文件流");
		}
	}
	
	private static <T> void export(List<T> list,OutputStream outputStream,Class<T> clazz){
		
		ExcelExportType exportType = clazz.getAnnotation(ExcelExportType.class);
		
		HSSFWorkbook workBook = null;
		HSSFSheet sheet = null;
		if(exportType==null){
			//非模板导出
			workBook =new HSSFWorkbook();
			sheet = workBook.createSheet();
			HSSFRow headRow = sheet.createRow(0);
			
			List<ExcelExportProperties> prop = getProp(clazz);
			List<String> heads = getHeads(prop);
			for(int i=0;i<heads.size();i++){
				HSSFCell cell = headRow.createCell(i);
				cell.setCellValue(heads.get(i));
			}
		}else{
			//模板导出
			try {
				Resource resource = new ClassPathResource(exportType.url());
				workBook = new HSSFWorkbook(resource.getInputStream());
				sheet = workBook.getSheetAt(0);
			} catch (IOException e) {
				log.error("无法找到文件:{}",exportType.url());
				throw new RuntimeException(e);
			}
			
		}
		
		//不想写注释了,烦
		for(int i=(exportType==null?0:exportType.firstLine()-1);i<(exportType==null?list.size():list.size()+exportType.firstLine()-1);i++){
			
			Map<Integer,Object> map = new HashMap<>();
			List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, ExcelExportProperties.class);
			List<Method> methods = MethodUtils.getMethodsListWithAnnotation(clazz, ExcelExportProperties.class);
			final int pp = i;
			fields.forEach(y->{
				ExcelExportProperties annotation = y.getAnnotation(ExcelExportProperties.class);
				if(annotation != null){
					try {
						Object field = FieldUtils.readField(list.get(exportType==null?pp:pp-exportType.firstLine()+1), y.getName(), true);
						Class<?> type = y.getType();
						if(type.isAssignableFrom(Date.class)){
							String dateFormat = annotation.dateFormat();
							if(dateFormat.equals("")){
								dateFormat = "yyyy-MM-dd";
							}
							Date date = (Date) field;
							DateFormat df = new SimpleDateFormat(dateFormat);
							map.put(annotation.order(), df.format(date));
						}else{
							map.put(annotation.order(), field);
						}
					} catch (Exception e) {
					}
				}
			});
			methods.forEach(y->{
				ExcelExportProperties annotation = y.getAnnotation(ExcelExportProperties.class);
				if(annotation != null){
					try {
						map.put(annotation.order(), MethodUtils.invokeMethod(list.get(exportType==null?pp:pp-exportType.firstLine()+1), true, y.getName()));
					} catch (Exception e) {
					}
				}
			});
			List<Entry<Integer,Object>> collect = map.entrySet().stream().collect(Collectors.toList());
			collect.sort((z,y)->z.getKey().compareTo(y.getKey()));
			
			HSSFRow row = sheet.createRow(i+(exportType==null?1:0));
			for(int j=0;j<collect.size();j++){
				HSSFCell cell = row.createCell(j);
				if(collect.get(j).getValue()==null){
					cell.setCellValue("");
				}else{
					cell.setCellValue(collect.get(j).getValue().toString());
				}
			}
		}
		try {
			workBook.write(outputStream);
		} catch (IOException e) {
			log.error("写入excel失败");
		}finally{
			IOUtils.closeQuietly(workBook);
		}
	}
	
	private static List<String> getHeads(List<ExcelExportProperties> list){
		return list.stream().map(x->{
			return x.columnName();
		}).collect(Collectors.toList());
		
	}
	
	private static <T> List<ExcelExportProperties> getProp(Class<T> clazz){
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, ExcelExportProperties.class);
		List<Method> list = MethodUtils.getMethodsListWithAnnotation(clazz, ExcelExportProperties.class);
		
		List<ExcelExportProperties> annons = new ArrayList<>();
		
		fields.stream().forEach(x->{
			ExcelExportProperties annotation = x.getAnnotation(ExcelExportProperties.class);
			if(annotation != null){
				annons.add(annotation);
			}
		});
		list.forEach(x->{
			ExcelExportProperties annotation = x.getAnnotation(ExcelExportProperties.class);
			if(annotation != null){
				annons.add(annotation);
			}
		});
		annons.sort((x,y)->x.order()-y.order());
		
		return annons;
	}
	
	
}
