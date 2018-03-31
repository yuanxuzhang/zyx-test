package com.zyx.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 检测测试IO的默认路径
 */
public class DefaultAddressTest {

	public static void main(String[] args) {
		
		/**
		 * 所有在java.io中的类都将相对路径名解释为用户工作目录开始，
		 * 可以通过System.getProperty("user.dir")来获取这信息
		 * 
		 * 附：文件分隔符，由于反斜杠字符在Java字符串中是转义字符，因此要确保在
		 * Windows风格的了路径中使用\\。在Windows中，还可以使用单斜杠字符，
		 * 因为大部分Windows文件处理的系统调用都会将斜杠解释成文件分隔符。但是
		 * 并不推荐这样做，因为Windows系统函数的行为会因与时俱进而发生变化。
		 * 因此，对于可移植的程序来说，应该使用程序所运行平台的文件分隔符，我们
		 * 可以通过常亮字符串Java.io.File.separator获得它。
		 * */
		FileInputStream in = null;
		
		try {
			in = new FileInputStream("pom.xml");
			
			// 检查当前可以读取的字节数，防止阻塞发生
			int bytesAvailable = in.available();
			if(bytesAvailable > 0){
				byte[] data = new byte[bytesAvailable];
				in.read(data);
				System.out.println(new String(data, 0, bytesAvailable));
			}
		} catch (FileNotFoundException e) {
			// NOP
			System.err.println("XXX");
		} catch (IOException e) {
			// NOP
			System.err.println("XXX");
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// NOP
				}
			}
		}
		
		System.out.println(System.getProperty("user.dir"));
	
	}

}
