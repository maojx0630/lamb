package com.github.maojx0630.common.qiniu;

import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 16:57
 * @description:
 */
class QiniuManager {

	private Auth auth;

	private UploadManager uploadManager;

	private BucketManager bucketManager;

	public QiniuManager(QiniuConfig qiniuConfig,Zone zone) {
		auth = Auth.create(qiniuConfig.getAccessKey(),qiniuConfig.getSecretKey());
		uploadManager = new UploadManager(new Configuration(zone));
		bucketManager = new BucketManager(auth, new Configuration(zone));

	}

	public BucketManager getBucketManager() {
		return bucketManager;
	}
	public UploadManager getUploadManager() {
		return uploadManager;
	}
	public String getUpToken(String space){
		return auth.uploadToken(space);
	}
	public StringMap getAuthorization(String url){
		return auth.authorization(url);
	}
	public String getPrivateFile(String publicUrl,long expireInSeconds){
		return auth.privateDownloadUrl(publicUrl, expireInSeconds);
	}

}
