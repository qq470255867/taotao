package com.taotao.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
/**
 * 上传图片处理服务实现类
 * <p>Title: PictureService</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年8月15日下午2:59:38
 * @version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;

	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile) {

		// 上传文件功能实现
		String path = savePicture(uploadFile);
		// 回显
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL + path);
		return result;
	}

	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			// 上传文件功能实现
			// 判断文件是否为空
			if (uploadFile.isEmpty())
				return null;
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
					+ new SimpleDateFormat("MM").format(new Date()) + "/"
					+ new SimpleDateFormat("dd").format(new Date());

			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			FtpUtil.uploadFile(FTP_SERVER_IP, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			result = filePath + "/" + newFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}