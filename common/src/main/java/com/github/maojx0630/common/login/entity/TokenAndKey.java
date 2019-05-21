package com.github.maojx0630.common.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 15:07
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenAndKey {

	private String Token;

	private String publicKey;
}
