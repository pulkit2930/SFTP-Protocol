package com.onedirect.sftpshopperstop.service;

import com.onedirect.sftpshopperstop.DTO.ThirdPartyTicketInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpResponse;

@Service
@Slf4j
public class ReadingFile {

    public void readDataFromExcel() {

        String filePath = "/Users/Pulkityadav/Downloads/SS- Sample-Ticket Creation (1).xlsx";
        List<ThirdPartyTicketInputDTO> thirdPartyTicketInputDTOS = new ArrayList<>();
        try {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            XSSFWorkbook workbook = null;
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = 9;
            int cells = 25;
            List<String> columnNames = new ArrayList<>();
            XSSFRow startRow = (XSSFRow) sheet.getRow(0);
            for (int cell = 0; cell < cells; cell++) {
                XSSFCell cellValue = (XSSFCell) startRow.getCell(cell);
                columnNames.add(cellValue.getStringCellValue());
            }

            for (int row = 1; row <=rows; row++) {
                XSSFRow xssfRow = (XSSFRow) sheet.getRow(row);
                ThirdPartyTicketInputDTO thirdPartyTicketInputDTO = new ThirdPartyTicketInputDTO();
                for (int cell = 0; cell < cells; cell++) {
                    XSSFCell xssfCell = (XSSFCell) xssfRow.getCell(cell);
                    String cellColumn = columnNames.get(cell);

//                    switch (xssfCell.getCellType()) {
//                        case STRING:
//                            try {
//
//                                thirdPartyTicketInputDTO.getClass().getDeclaredField(cellColumn).set(thirdPartyTicketInputDTO,
//                                        xssfCell.getStringCellValue());
//                            } catch (NoSuchFieldException e) {
//                                throw new RuntimeException(e);
//                            } catch (IllegalAccessException e) {
//                                throw new RuntimeException(e);
//                            }
//
//                            break;
//
//                        case NUMERIC:
                            try {

                                thirdPartyTicketInputDTO.getClass().getDeclaredField(cellColumn).set(thirdPartyTicketInputDTO,
                                        xssfCell.getRawValue());
                            } catch (NoSuchFieldException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                           // break;

                    //}
                }
                log.info("TicketInput DTO :: {}", thirdPartyTicketInputDTO.toString());
                thirdPartyTicketInputDTOS.add(thirdPartyTicketInputDTO);


            }


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        //return null;
        System.out.println(thirdPartyTicketInputDTOS);



    }

}
