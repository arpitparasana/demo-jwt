package com.example.demojwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demojwt.model.MyUser;

@Mapper
public interface UserMapper {

	@Select("select username, password from user where username = #{username}")
	MyUser getUserByUsername(@Param("username") String username);
	
}
