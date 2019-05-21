package com.github.maojx0630.common.utils.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaEntity{

	private String publicKey;

	private String privateKey;

	RsaEntity(PublicKey publicKey, PrivateKey privateKey) {
		super();
		this.publicKey = Base64.encode(publicKey.getEncoded());
		this.privateKey = Base64.encode(privateKey.getEncoded());
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}
	
}
