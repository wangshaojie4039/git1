package cn.imexue.ec.web.service.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.web.model.bo.ChildCardExcelBO;
import cn.imexue.ec.web.model.bo.ChildExcelBO;
import cn.imexue.ec.web.model.bo.TeacherExcelBO;
import cn.imexue.ec.web.service.FileServiceImpl;

/**
 * excel导入导出等接口服务实现
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since 2017年2月17日
 * @author lijianfeng
 * @version 1.0
 */
@Service
@Transactional(readOnly=true)
public class ExcelService extends FileServiceImpl{
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Value("${app.excel.upload.path.child}")
	private String excelUploadPathChild;
	
	@Value("${app.excel.upload.path.teacher}")
	private String excelUploadPathTeacher;
	
	@Value("${app.excel.upload.path.card}")
	private String excelUploadPathCard;
	
	/**
	 * 从上传的excel文件中提取幼儿信息
	 * @param file 上传的excel文件
	 * @return 幼儿列表
	 */
	@SuppressWarnings("resource")
	public List<ChildExcelBO> getChildListFromExcel(MultipartFile file) throws Exception {
		List<ChildExcelBO> list = new ArrayList<ChildExcelBO>();

		FileInputStream fileIn = null;
		Workbook workbook = null;
		File filePath = null;

		try {
			// 开始上传
			if (StringUtil.isNotEmpty(file.getOriginalFilename())) {
				filePath = uploadFile(file,excelUploadPathChild);

				// 解析excel
				fileIn = new FileInputStream(filePath);
				// 根据指定的文件输入流导入Excel从而产生Workbook对象
				workbook = new HSSFWorkbook(fileIn);
				// 获取Excel文档中的第一个表单
				Sheet sheet = workbook.getSheetAt(0);
				// 对Sheet中的每一行进行迭代
				for (Row row : sheet) {
					// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
					if (row.getRowNum() < 3) {
						continue;
					}
					// 创建实体类
					ChildExcelBO bo = new ChildExcelBO();
					// 取出当前行第1个单元格数据，并封装在info实体stuName属性上
					Cell cell0 = row.getCell(0);
					Cell cell1 = row.getCell(1);
					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					Cell cell4 = row.getCell(4);
					Cell cell5 = row.getCell(5);
					Cell cell6 = row.getCell(6);
					Cell cell7 = row.getCell(7);
					Cell cell8 = row.getCell(8);
					Cell cell9 = row.getCell(9);
					Cell cell10 = row.getCell(10);
					Cell cell11 = row.getCell(11);
					Cell cell12 = row.getCell(12);
					if(cell0!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell1!=null){
						cell1.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell3!=null){
						cell3.setCellType(CellType.STRING);
					}
					if(cell4!=null){
						cell4.setCellType(CellType.STRING);
					}
					if(cell5!=null){
						cell5.setCellType(CellType.STRING);
					}
					if(cell6!=null){
						cell6.setCellType(CellType.STRING);
					}
					if(cell7!=null){
						cell7.setCellType(CellType.STRING);
					}
					if(cell8!=null){
						cell8.setCellType(CellType.STRING);
					}
					if(cell9!=null){
						cell9.setCellType(CellType.STRING);
					}
					if(cell10!=null){
						cell10.setCellType(CellType.STRING);
					}
					if(cell11!=null){
						cell11.setCellType(CellType.STRING);
					}
					if(cell12!=null){
						cell12.setCellType(CellType.STRING);
					}
					if ((cell0 == null || (cell0 != null && StringUtil.isEmpty(cell0.getStringCellValue())))
							&& (cell1 == null || (cell1 != null && StringUtil.isEmpty(cell1.getStringCellValue())))
							&& (cell2 == null || (cell2 != null && StringUtil.isEmpty(cell2.getStringCellValue())))
							&& (cell3 == null || (cell3 != null && StringUtil.isEmpty(cell3.getStringCellValue())))
							&& (cell4 == null || (cell4 != null && StringUtil.isEmpty(cell4.getStringCellValue())))
							&& (cell5 == null || (cell5 != null && StringUtil.isEmpty(cell5.getStringCellValue())))
							&& (cell6 == null || (cell6 != null && StringUtil.isEmpty(cell6.getStringCellValue())))
							&& (cell7 == null || (cell7 != null && StringUtil.isEmpty(cell7.getStringCellValue())))
							&& (cell8 == null || (cell8 != null && StringUtil.isEmpty(cell8.getStringCellValue())))
							&& (cell9 == null || (cell9 != null && StringUtil.isEmpty(cell9.getStringCellValue())))
							&& (cell10 == null || (cell10 != null && StringUtil.isEmpty(cell10.getStringCellValue())))
							&& (cell11 == null || (cell11 != null && StringUtil.isEmpty(cell11.getStringCellValue())))
							&& (cell12 == null || (cell12 != null && StringUtil.isEmpty(cell12.getStringCellValue())))) {
						break;
					} else {
						if ((cell0 != null && StringUtil.isNotEmpty(cell0.getStringCellValue()))
								&& (cell1 != null && StringUtil.isNotEmpty(cell1.getStringCellValue())) 
								&& (cell4 != null && StringUtil.isNotEmpty(cell4.getStringCellValue()))) {
							if (cell0 != null) {
								bo.setName(cell0.getStringCellValue());
							}
							if (cell1 != null) {
								bo.setSex(cell1.getStringCellValue());
							}
							if (cell2 != null) {
								bo.setBirthday(cell2.getStringCellValue());
							}
							if (cell3 != null) {
								bo.setIdCardNo(cell3.getStringCellValue());
							}
							if (cell4 != null) {
								bo.setClassName(cell4.getStringCellValue());
							}
							if (cell5 != null) {
								bo.setFatherName(cell5.getStringCellValue());
							}
							if (cell6 != null) {
								bo.setFatherMobile(cell6.getStringCellValue());
							}
							if ((cell5==null||StringUtil.isEmpty(cell5.getStringCellValue())) &&(cell6!=null&& StringUtil.isNotEmpty(cell6.getStringCellValue()))
									|| (cell5!=null&&StringUtil.isNotEmpty(cell5.getStringCellValue())) &&(cell6==null|| StringUtil.isEmpty(cell6.getStringCellValue()))) {
								throw new AppChkException(1007,"child.excel.parent.allEmpty",row.getRowNum() +1,"父亲");
							}
							if (cell7 != null) {
								bo.setMotherName(cell7.getStringCellValue());
							}
							if (cell8 != null) {
								bo.setMotherMobile(cell8.getStringCellValue());
							}
							if ((cell7==null||StringUtil.isEmpty(cell7.getStringCellValue())) &&(cell8!=null&& StringUtil.isNotEmpty(cell8.getStringCellValue()))
									|| (cell7!=null&&StringUtil.isNotEmpty(cell7.getStringCellValue())) &&(cell8==null|| StringUtil.isEmpty(cell8.getStringCellValue()))) {
								throw new AppChkException(1007,"child.excel.parent.allEmpty",row.getRowNum() +1,"母亲");
							}
							if (cell9 != null) {
								bo.setCardNo1(cell9.getStringCellValue());
							}
							if (cell10 != null) {
								bo.setCardNo2(cell10.getStringCellValue());
							}
							if (cell11 != null) {
								bo.setCardNo3(cell11.getStringCellValue());
							}
							if (cell12 != null) {
								bo.setCardNo4(cell12.getStringCellValue());
							}
							list.add(bo);
						} else {
							throw new AppChkException(1007,"child.excel.parent.anyEmpty",row.getRowNum() + 1);
						}
					}
				}
			}
		}finally{
			IOUtils.closeQuietly(fileIn);
			IOUtils.closeQuietly(workbook);
			FileUtils.deleteQuietly(filePath);
		}
		return list;
	}
	/**
	 * 获得导入的老师列表
	 * @param file 上传的excel文件
	 * @return 老师列表
	 * @throws AppChkException 
	 */
	@SuppressWarnings("resource")
	public List<TeacherExcelBO> getTeacherListFromExcel(MultipartFile file) throws Exception {
		List<TeacherExcelBO> list = new ArrayList<TeacherExcelBO>();
		
		FileInputStream fileIn = null;
		Workbook workbook = null;
		File filePath = null;
		try {
			// 开始上传
			if (StringUtil.isNotEmpty(file.getOriginalFilename())) {
				filePath = uploadFile(file,excelUploadPathTeacher);

				// 解析excel
				fileIn = new FileInputStream(filePath);
				// 根据指定的文件输入流导入Excel从而产生Workbook对象
				workbook = new HSSFWorkbook(fileIn);
				// 获取Excel文档中的第一个表单
				Sheet sheet = workbook.getSheetAt(0);
				// 对Sheet中的每一行进行迭代
				for (Row row : sheet) {
					// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
					if (row.getRowNum() < 3) {
						continue;
					}
					
					// 创建实体类
					TeacherExcelBO bo = new TeacherExcelBO();
					// 取出当前行第1个单元格数据，并封装在info实体stuName属性上
					Cell cell0 = row.getCell(0);
					Cell cell1 = row.getCell(1);
					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					Cell cell4 = row.getCell(4);
					Cell cell5 = row.getCell(5);
					Cell cell6 = row.getCell(6);
					Cell cell7 = row.getCell(7);
					if(cell0!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell1!=null){
						cell1.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell3!=null){
						cell3.setCellType(CellType.STRING);
					}
					if(cell4!=null){
						cell4.setCellType(CellType.STRING);
					}
					if(cell5!=null){
						cell5.setCellType(CellType.STRING);
					}
					if(cell6!=null){
						cell6.setCellType(CellType.STRING);
					}
					if(cell7!=null){
						cell7.setCellType(CellType.STRING);
					}
					if ((cell0 == null || (cell0 != null && StringUtil.isEmpty(cell0.getStringCellValue())))
							&& (cell1 == null || (cell1 != null && StringUtil.isEmpty(cell1.getStringCellValue())))
							&& (cell2 == null || (cell2 != null && StringUtil.isEmpty(cell2.getStringCellValue())))
							&& (cell3 == null || (cell3 != null && StringUtil.isEmpty(cell3.getStringCellValue())))
							&& (cell4 == null || (cell4 != null && StringUtil.isEmpty(cell4.getStringCellValue())))
							&& (cell5 == null || (cell5 != null && StringUtil.isEmpty(cell5.getStringCellValue())))
							&& (cell6 == null || (cell6 != null && StringUtil.isEmpty(cell6.getStringCellValue())))
							&& (cell7 == null || (cell7 != null && StringUtil.isEmpty(cell7.getStringCellValue())))) {
						break;
					}else{
						if((cell0!=null&&StringUtil.isNotEmpty(cell0.getStringCellValue()))
								&&(cell1!=null&&StringUtil.isNotEmpty(cell1.getStringCellValue()))
								&&(cell2!=null&&StringUtil.isNotEmpty(cell2.getStringCellValue()))
								){
							bo.setName(cell0.getStringCellValue());
							bo.setMobile(String.valueOf(cell1.getStringCellValue()));
							bo.setRole((String.valueOf(cell2.getStringCellValue())));
							if(cell3!=null){
								bo.setClassName(cell3.getStringCellValue());
							}
							if(cell4!=null){
								bo.setIsMaster(cell4.getStringCellValue());
							}
							if(cell5!=null){
								bo.setSex(cell5.getStringCellValue());
							}
							if(cell6!=null){
								bo.setBirthday(cell6.getStringCellValue());
							}
							if(cell7!=null){
								bo.setCardNo(String.valueOf(cell7.getStringCellValue()));
							}
							
							list.add(bo);
						}else {
							throw new AppChkException(1007,"child.excel.parent.anyEmpty",row.getRowNum() + 1);
						}
						
					}
				}
			}
		} finally {
			IOUtils.closeQuietly(fileIn);
			IOUtils.closeQuietly(workbook);
			FileUtils.deleteQuietly(filePath);
		}
		return list;
	}
	@SuppressWarnings("resource")
	public List<ChildCardExcelBO> getChildCardListFromExcel(MultipartFile file) throws Exception {
		List<ChildCardExcelBO> list = new ArrayList<ChildCardExcelBO>();
		
		FileInputStream fileIn = null;
		Workbook workbook = null;
		File filePath = null;
		try {
			// 开始上传
			if (StringUtil.isNotEmpty(file.getOriginalFilename())) {
				filePath = uploadFile(file,excelUploadPathCard);

				// 解析excel
				fileIn = new FileInputStream(filePath);
				// 根据指定的文件输入流导入Excel从而产生Workbook对象
				workbook = new HSSFWorkbook(fileIn);
				// 获取Excel文档中的第一个表单
				Sheet sheet = workbook.getSheetAt(0);
				// 对Sheet中的每一行进行迭代
				for (Row row : sheet) {
					// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
					if (row.getRowNum() < 3) {
						continue;
					}
					
					// 创建实体类
					ChildCardExcelBO bo = new ChildCardExcelBO();
					// 取出当前行第1个单元格数据，并封装在info实体stuName属性上
					Cell cell0 = row.getCell(0);
					Cell cell1 = row.getCell(1);
					Cell cell2 = row.getCell(2);
					Cell cell3 = row.getCell(3);
					if(cell0!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell1!=null){
						cell1.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell2!=null){
						cell0.setCellType(CellType.STRING);
					}
					if(cell3!=null){
						cell3.setCellType(CellType.STRING);
					}
					if ((cell0 == null || (cell0 != null && StringUtil.isEmpty(cell0.getStringCellValue())))
							&& (cell1 == null || (cell1 != null && StringUtil.isEmpty(cell1.getStringCellValue())))
							&& (cell2 == null || (cell2 != null && StringUtil.isEmpty(cell2.getStringCellValue())))
							&& (cell3 == null || (cell3 != null && StringUtil.isEmpty(cell3.getStringCellValue())))) {
						break;
					}else{
						if((cell0!=null&&StringUtil.isNotEmpty(cell0.getStringCellValue()))
								&&(cell1!=null&&StringUtil.isNotEmpty(cell1.getStringCellValue()))
								&&(cell2!=null&&StringUtil.isNotEmpty(cell2.getStringCellValue()))
								){
							bo.setCardNo(cell0.getStringCellValue());
							bo.setClassName(cell1.getStringCellValue());
							bo.setChildName((cell2.getStringCellValue()));
							if(cell3!=null){
								bo.setIdCardNo(cell3.getStringCellValue());
							
							}
							list.add(bo);
					}else {
						throw new AppChkException(1007,"child.excel.parent.anyEmpty",row.getRowNum() + 1);
					}
					}

					
				}
			}
		} finally {
			IOUtils.closeQuietly(fileIn);
			IOUtils.closeQuietly(workbook);
			FileUtils.deleteQuietly(filePath);
		}

		return list;
	}
}
