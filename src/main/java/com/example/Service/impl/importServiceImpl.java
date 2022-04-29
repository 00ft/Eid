package com.example.Service.impl;

import com.example.Service.importService;
import com.example.mapper.importMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class importServiceImpl implements importService {
    @Autowired
    importMapper importEid;
    @Override
    public int importEid(Map<String, Object> map) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String format = simpleDateFormat.format(date);
        map.put("create_time",format);
        if(map.get("eid_id")!=null&&map.get("eid_id")!=""){
            return importEid.importEid(map);
        }
        else{
            return -1;
        }
    }

    @Override
    public int validate(String rfid) {
        return importEid.validate(rfid);
    }



    @Transactional
    @Override
    public int importExcel(MultipartFile file) throws IOException {
        System.out.println(file.toString());
        ObjectMapper objectMapper=new ObjectMapper();
        InputStream inputStream=null;
            inputStream=file.getInputStream();
            String fileName=file.getOriginalFilename();
            Workbook workbook=null;
            String fileType=fileName.substring(fileName.lastIndexOf("."));
            if(".xls".equals(fileType)){
                workbook=new HSSFWorkbook(inputStream);
            }else if(".xlsx".equals(fileType)){
                workbook=new XSSFWorkbook(inputStream);
            }
            ArrayList list=new ArrayList();
            Sheet sheet;
            Row row;
            Cell cell;
            String epc=null;
            String eid=null;
            String ordernum=null;
            Map<String,Object> map=new HashMap<>();
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                sheet=workbook.getSheetAt(i);
                for(int j=sheet.getFirstRowNum()+1;j<=sheet.getLastRowNum();j++){
                    System.out.println(sheet.getLastRowNum());
                    row=sheet.getRow(j);
                    if(row.getCell(2).toString()==null||row.getCell(2).toString()==""){
                        break;
                    }
                    if(row!=null&&row.getFirstCellNum()!=j){
                        ArrayList tempList=new ArrayList();
                        for(int k=row.getFirstCellNum();k<row.getLastCellNum();k++){
                            System.out.println(row.getLastCellNum());
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date=new Date();
                            String format = simpleDateFormat.format(date);
                            cell=row.getCell(k);
                            if(k==1){
                                epc=cell.toString();
                            }else if(k==2){
                                eid=cell.toString();
                            }else if(k==3){
                                ordernum=cell.toString();
                            }
                            map.put("epc",epc);
                            map.put("eid_id",eid);
                            map.put("order_num",ordernum);
                            map.put("create_time",format);


                        }
                        importEid.importEid(map);




                    }
                }
            }
        inputStream.close();
        return 1;

    }

    @Override
    public List<String> validateEpc(Map<String, Object> map) {
        List<String> eids = importEid.validateEpc(map);
        return eids;

    }

}
