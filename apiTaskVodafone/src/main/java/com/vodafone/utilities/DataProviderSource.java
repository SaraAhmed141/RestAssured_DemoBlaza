package com.vodafone.utilities;

import com.rits.cloning.Cloner;
import com.vodafone.dataproviderobjects.excel.*;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderSource {

    /***************************Login Users Data****************************************/

    @DataProvider(name = "BooksDataFeed")
    public Iterator<Object> booksDataFeed(Method method) {
        String testCaseName = method.getName();
        BooksData booksData = new BooksData();
        return fetchDataFromSheet(booksData, Constants.BOOKS_WORKBOOK, Constants.BOOKS_SHEET,
                testCaseName);
    }


    /***************************Fetch Data From Excel Sheet****************************************/
    public Iterator<Object> fetchDataFromSheet(Object obj, String workBookName, String sheetName, String testCaseName) {
        ExcelUtils config = new ExcelUtils();
        config.setTestDataExcelPath("src/main/resources/testdata/" + workBookName);
        config.setExcelFileSheet(sheetName);
        int row = config.getLastRow();
        List<Object> recordsList = new ArrayList<>();
        Cloner cloner = new Cloner();

        for (int i = 1; i < row; i++) {
            Object tempObj;
            if (config.getCellData(i, 0).contains(testCaseName)) {
                int j = 1;
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(CustomAnnotations.ExcelColumn.class)) {
                        CustomAnnotations.ExcelColumn excelColumn = field.getAnnotation(CustomAnnotations.ExcelColumn.class);
                        field.setAccessible(true); // should work on private fields
                        try {
                            if (excelColumn.value() == j) {
                                field.set(obj, config.getCellData(i, j++));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                tempObj = cloner.deepClone(obj);
                recordsList.add(tempObj);
            }
        }
        return recordsList.iterator();
    }

}
