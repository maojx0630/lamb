package com.github.maojx0630.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 16:27
 * @description:
 */
@org.apache.ibatis.annotations.Mapper
public interface Mapper {

	@Select("update test set name=#{name} where id =#{id}")
	int update(@Param("name") String name, @Param("id") String id);
}
