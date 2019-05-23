package com.github.maojx0630.user.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 16:27
 * @description:
 */
@org.apache.ibatis.annotations.Mapper
public interface Mapper {

	Integer update(@Param("name") String name, @Param("id") String id);
}
