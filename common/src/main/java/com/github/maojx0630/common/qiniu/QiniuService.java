package com.github.maojx0630.common.qiniu;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.UrlSafeBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-21 17:00
 * @description:
 */
@SuppressWarnings("all")
public class QiniuService {

	private QiniuManager qiniuManager;

	public QiniuService(QiniuManager qiniuManager) {
		this.qiniuManager = qiniuManager;
	}

	/**
	 *
	 * @param request HttpServletRequest
	 * @param labelName 标签name
	 * @param space 上传到那个空间
	 */

	public List<DefaultPutRet> uploadByRequest(HttpServletRequest request, String labelName, String space) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles(labelName);
		List<DefaultPutRet> list = new ArrayList<>();
		for (MultipartFile multipartFile : files) {
			try {
				Response response = qiniuManager.getUploadManager().put(multipartFile.getInputStream(), null,
						qiniuManager.getUpToken(space),null,null);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				list.add(putRet);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	/**
	 *
	 * @param localFilePath 上传文件本地路径
	 * @param space 上传到指定空间
	 */
	public DefaultPutRet uploadByFilePath(String localFilePath,String space){
		DefaultPutRet putRet=null;
		try {
			Response response = qiniuManager.getUploadManager().put(localFilePath, null, qiniuManager.getUpToken(space));
			//解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		} catch (QiniuException ex) {
			ex.printStackTrace();
		}
		return putRet;
	}
	/**
	 *
	 * @param Base64 数组 上传文件对应base64字符串数组
	 * @param space 对应上传空间
	 */
	public List<DefaultPutRet> uploadByBase64List(String[] Base64,String space){
		ArrayList<DefaultPutRet> list = new ArrayList<>();
		for (String base64 : Base64) {
			list.add(uploadByBase64(base64,space));
		}
		return list;
	}
	/**
	 *
	 * @param base64 上传文件对应base64字符串
	 * @param space 对应上传空间
	 */
	public DefaultPutRet uploadByBase64(String base64,String space){
		DefaultPutRet putRet=null;
		try {
			//Base64解码
			byte[] b = new BASE64Decoder().decodeBuffer(base64);
			for(int i=0;i<b.length;++i){
				if(b[i]<0){//调整异常数据
					b[i]+=256;
				}
			}
			Response response = qiniuManager.getUploadManager().put(b, null, qiniuManager.getUpToken(space));
			//解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return putRet;
	}
	/**
	 *
	 * @param uploadBytes 上传文件的字符数组
	 * @param space 对应上传空间
	 */
	public DefaultPutRet uploadByByteArray(byte[] uploadBytes,String space){
		DefaultPutRet putRet=null;
		try {
			Response response = qiniuManager.getUploadManager().put(uploadBytes, null, qiniuManager.getUpToken(space));
			//解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		} catch (QiniuException ex) {
			ex.printStackTrace();
		}
		return putRet;
	}
	/**
	 * 通过输入流上传文件
	 * @param inputStream 上传文件的字符数组输入流
	 * @param space 对应上传空间
	 */
	public DefaultPutRet uploadByInputStream(InputStream inputStream, String space){
		DefaultPutRet putRet=null;
		try {
			Response response = qiniuManager.getUploadManager().
					put(inputStream, null, qiniuManager.getUpToken(space),null,
					null);
			//解析上传成功的结果
			putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		} catch (QiniuException ex) {
			ex.printStackTrace();
		}
		return putRet;
	}
	/**
	 * 获取私有空间访问签名
	 * @param key 文件key
	 * @param expireInSeconds 设置过期时间(秒)
	 */
	public String getPrivateFile(String key,String spaceUrl,long expireInSeconds){
		return qiniuManager.getPrivateFile(spaceUrl+"/"+key, expireInSeconds);
	}
	/**
	 * 获取文件信息
	 */
	private FileInfo getFileInfo(String key, String space) {
		FileInfo fileInfo=null;
		try {
			fileInfo = qiniuManager.getBucketManager().stat(space, key);
		} catch (QiniuException ex) {
			ex.printStackTrace();
		}
		return fileInfo;
	}
	/**
	 * 判断该key是否存在
	 */
	public boolean ifExistence(String key,String space){
		return getFileInfo(key, space) != null;

	}
	/**
	 * fetch
	 * uri 需要抓取的图片网络路径
	 * bucket 目标空间名称
	 */
	public String fetch(String uri,String bucket) {
		if (StringUtils.isBlank(uri)) {
			return null;
		}
		String key=null;
		try {
			String url="https://iovip.qbox.me/fetch/"+ UrlSafeBase64.encodeToString(
					uri)+"/to/"+UrlSafeBase64.encodeToString(bucket);
			Client client=new Client();
			Response rs= client.post(url, new byte[0], qiniuManager.getAuthorization(url));
			key=(String) ((Map) JSON.parse(rs.bodyString())).get("key");
		} catch (Exception ignored) {
		}
		return key;
	}
}
