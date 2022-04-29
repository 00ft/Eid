package com.example.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface importService {
    public int importEid(Map<String,Object> map);

    public int validate(String eid);

    public int importExcel(MultipartFile file) throws IOException;

    public List<String> validateEpc(Map<String,Object> map);
}
