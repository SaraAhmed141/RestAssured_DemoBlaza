package com.vodafone.dataproviderobjects.excel;

import com.vodafone.utilities.CustomAnnotations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooksData {

    @CustomAnnotations.ExcelColumn(1)
    String testCaseName;

    @CustomAnnotations.ExcelColumn(2)
    String statusCode;

}

