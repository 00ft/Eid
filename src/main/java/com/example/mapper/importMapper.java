package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface importMapper {
    public int validate(String eid);

    public int importEid(Map<String,Object> map);

    public List<String> validateEpc(Map<String,Object> map);



}
