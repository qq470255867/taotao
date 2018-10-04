import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	@Test
	public void testftpClient() throws Exception{
		//创建一个ftpclient对象
		FTPClient ftpClient = new FTPClient();		
		//创建ftp链接
		ftpClient.connect("192.168.19.131", 21);
		//登录ftp服务器,使用用户名和密码
		ftpClient.login("ftpuser","1234");
		//上传文件
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//读取本地文件
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\ky\\Desktop\\123.JPG"));
		ftpClient.storeFile("hello.jpg", fileInputStream);//第一个参数是服务器端文件名
		//第二个参数是inputstream
		//关闭连接		
		ftpClient.logout();
	}

}
