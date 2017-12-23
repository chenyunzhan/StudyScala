package com.zhan.test;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	 public static void main(String[] args) throws IOException {
		 
		FileWriter fw = new FileWriter("BigLog.txt");

		long begin3 = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {

			fw.write("测试java 文件操作\r\n");

		}

		fw.close();

		long end3 = System.currentTimeMillis();

		System.out.println("FileWriter执行耗时:" + (end3 - begin3) + "豪秒");
	}

}
