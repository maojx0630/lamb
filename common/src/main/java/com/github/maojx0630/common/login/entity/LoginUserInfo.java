package com.github.maojx0630.common.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 11:12
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo {

	private String id;

	private String privateKey;

}
