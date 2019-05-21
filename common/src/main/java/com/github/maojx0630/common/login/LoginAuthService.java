package com.github.maojx0630.common.login;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.maojx0630.common.login.entity.LoginUserInfo;
import com.github.maojx0630.common.login.entity.TokenAndKey;
import com.github.maojx0630.common.response.exception.StateEnum;
import com.github.maojx0630.common.response.exception.StateException;
import com.github.maojx0630.common.utils.IdUtils;
import com.github.maojx0630.common.utils.rsa.RsaEntity;
import com.github.maojx0630.common.utils.rsa.RsaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 14:32
 * @description:
 */
@SuppressWarnings("all")
public class LoginAuthService {

	private static final String HEAD="LAMB_LOGIN_HEAD_";

	private static final String TOKEN_HEAD="LAMB_TOKEN_HEAD_";

	private static final long EXPIRES_DAY=15L;

	private static final int LENGTH=10;

	private ValueOperations<String,Object> valueOperations;

	private RedisTemplate redisTemplate;

	LoginAuthService(RedisTemplate redisTemplate){
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		valueOperations=redisTemplate.opsForValue();
		this.redisTemplate=redisTemplate;
	}


	public TokenAndKey login(String id) {
		return login(id,-1L);
	}

	public TokenAndKey login(String id, Long expiresSeconds) {
		return login(id,expiresSeconds,TimeUnit.SECONDS);
	}

	public TokenAndKey login(String id, Long expiresSeconds,TimeUnit timeUnit) {
		RsaEntity rsaEntity= RsaUtils.createKeyPair();
		LoginUserInfo loginUserInfo=new LoginUserInfo();
		loginUserInfo.setId(id);
		loginUserInfo.setPrivateKey(rsaEntity.getPrivateKey());
		String token=HEAD+ IdUtils.uuid()+IdUtils.randomString(LENGTH);
		token=token.toUpperCase();
		//确保不会与历史数据发生冲突,在高并发情况下依然可能会发生冲突,不过忽略该情况
		while (!redisTemplate.hasKey(token)){
			token=HEAD+ IdUtils.uuid();
		}
		if(expiresSeconds>0){
			valueOperations.set(token,loginUserInfo,expiresSeconds, timeUnit);
		}else{
			valueOperations.set(token,loginUserInfo,EXPIRES_DAY,TimeUnit.DAYS);
		}
		return new TokenAndKey(token,rsaEntity.getPublicKey());
	}

	/**
	 * token格式:  token:{rsa加密时间戳}
	 */
	public String verification(String requestToken){
		String token;
		String rsaToken;
		try {
			String[] arr=requestToken.split(",");
			token=arr[0];
			rsaToken=arr[1];
		}catch (Exception e){
			throw StateException.of(StateEnum.token_check_failure);
		}
		if(StringUtils.isBlank(token)||StringUtils.isBlank(rsaToken)){
			throw StateException.of(StateEnum.token_check_failure);
		}

		if(redisTemplate.hasKey(rsaToken)) {
			throw StateException.of(StateEnum.token_repeat);
		}
		Object object=valueOperations.get(token);
		if(Objects.isNull(object)|| !(object instanceof LoginUserInfo)){
			throw StateException.of(StateEnum.user_not_login);
		}
		LoginUserInfo loginUserInfo= (LoginUserInfo) object;
		long time;
		try {
			time= Long.parseLong(RsaUtils.decryptWithRSA(rsaToken,loginUserInfo.getPrivateKey()));
		}catch (Exception e){
			throw StateException.of(StateEnum.token_check_failure);
		}
		if(time<1||System.currentTimeMillis()-time>1000*10){
			throw StateException.of(StateEnum.token_timeout);
		}
		valueOperations.set(rsaToken,"1",10, TimeUnit.SECONDS);
		return loginUserInfo.getId();
	}
}
