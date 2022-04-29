package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.Service.importService;
import com.example.util.ReturnObject;
import com.example.util.ReturnTid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class importController {
    @Autowired
    importService importEidimplService;
    @RequestMapping("/import")
    public String importEid(@RequestBody Map<String,Object> map){
        try {
            int i = importEidimplService.importEid(map);
            if(i>0)
            return JSON.toJSONString(new ReturnObject(true,"成功"));
            else if(i==-1){
                return JSON.toJSONString(new ReturnObject(false,"eid不能为空"));
            }
            else return JSON.toJSONString(new ReturnObject(false,"已存在"));

        }catch (Exception e){
            return JSON.toJSONString(new ReturnObject(false,"已存在"));
        }
    }

    @PostMapping("/validate/validate")
    public ReturnObject validate(@RequestBody Map<String,Object> map1){
        String rfid= (String) map1.get("rfid");
        System.out.println(rfid);

        int count=importEidimplService.validate(rfid);
        if(count==0){
            return new ReturnObject(false,"当前rfid不在系统中");
        }else{
            return new ReturnObject(true,"");
        }
    }



    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file){
        int i = 0;
        try {
            i = importEidimplService.importExcel(file);
        } catch (IOException e) {
            return JSON.toJSONString(new ReturnObject(false,"系统异常"));
        }

            return JSON.toJSONString(new ReturnObject(true,"成功"));
    }


    @RequestMapping("validateEpc")
    public ReturnTid validateEpc(@RequestBody List<Map<String,Object>> list) {
        List<Map<String, Object>> listresult = new ArrayList<>();
        Map<String, Object> resultmap = null;
        try{
            for (Map<String, Object> map : list) {
                resultmap=new HashMap<>();
                List<String> eidList = importEidimplService.validateEpc(map);
                resultmap.put("epc", map.get("epc"));
                resultmap.put("tidList", eidList);
                listresult.add(resultmap);
            }
            return new ReturnTid(1,"成功",listresult);
        }catch (Exception e){
            return new ReturnTid(0,"系统异常",listresult);
        }




    }
}
