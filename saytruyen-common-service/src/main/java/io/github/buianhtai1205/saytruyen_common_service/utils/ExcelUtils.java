package io.github.buianhtai1205.saytruyen_common_service.utils;

import io.github.buianhtai1205.saytruyen_common_service.constant.CommonConstants;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Excel utils.
 */
public class ExcelUtils {

    /**
     * The constant FILE_EXTENSION.
     */
    public static final String FILE_EXTENSION = ".xlsx";

    /**
     * Read excel xssf workbook.
     *
     * @param file the file
     * @return the xssf workbook
     * @throws IOException the io exception
     */
    public static XSSFWorkbook readExcel(MultipartFile file) throws IOException {
        return new XSSFWorkbook(file.getInputStream());
    }

    /**
     * Read excel xssf workbook.
     *
     * @param fileInputStream the file input stream
     * @return the xssf workbook
     * @throws IOException the io exception
     */
    public static XSSFWorkbook readExcel(FileInputStream fileInputStream) throws IOException {
        return new XSSFWorkbook(fileInputStream);
    }

    /**
     * Read excel xssf workbook.
     *
     * @param filePath the file path
     * @return the xssf workbook
     * @throws Exception the exception
     */
    public static XSSFWorkbook readExcel(String filePath) throws Exception {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return new XSSFWorkbook(fis);
        } catch (Exception e) {
            throw new Exception("Failed to read excel file", e);
        }
    }

    /**
     * Read sheet names list.
     *
     * @param workbook the workbook
     * @return the list
     */
    public static List<String> readSheetNames(Workbook workbook) {
        List<String> sheetNames = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetNames.add(workbook.getSheetName(i));
        }
        return sheetNames;
    }

    /**
     * Read sheet data list.
     *
     * @param workbook  the workbook
     * @param sheetName the sheet name
     * @return the list
     */
    public static List<List<String>> readSheetData(Workbook workbook, String sheetName) {
        XSSFSheet sheet = (XSSFSheet) workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        List<List<String>> sheetData = new ArrayList<>();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> rowData = new ArrayList<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    rowData.add(CommonConstants.BLANK);
                    continue;
                }
                rowData.add(getCellValue(cell));
            }
            sheetData.add(rowData);
        }
        return sheetData;
    }

    private static String getCellValue(XSSFCell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (int) numericValue) {
                    yield String.valueOf((int) numericValue);
                } else {
                    yield String.valueOf(numericValue);
                }
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
