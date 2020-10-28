package com.example.demojwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IndividualMapper {

	@Select("select firstname from individual where individual_id = #{id}")
	String getIndividualId(@Param("id") Long id);
}
