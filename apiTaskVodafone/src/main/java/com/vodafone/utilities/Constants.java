package com.vodafone.utilities;
import java.util.ResourceBundle;

public class Constants
{

    public static final String BOOKS_WORKBOOK = "BooksData.xlsx";
    public static final String BOOKS_SHEET = "Books";
    private static final ResourceBundle ENVIRONMENT_RN = ResourceBundle.getBundle("environment");
    public static final String ENVIRONMENT_NAME = ENVIRONMENT_RN.getString("env.name");
    public static final String APPLICATION_HOST = ENVIRONMENT_RN.getString("app.host");


}