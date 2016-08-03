package com.common.io.unit;

import org.junit.Test;

import com.common.io.DeleteFileUtil;

public class DeleteFileUtilUnit {

	@Test
	public void deleteFileTest() {
		// 删除单个文件
		String file = "D:/temp/temp0/temp1/temp.txt";
		DeleteFileUtil.deleteFile(file);
	}

	@Test
	public void deleteDirectoryTest() {
		// 删除一个目录
		String dir = "D:/temp/temp0/temp1";
		DeleteFileUtil.deleteDirectory(dir);
	}

	@Test
	public void deleteTest() {
		// 删除文件
		String dir = "D:/temp/temp0";
		DeleteFileUtil.delete(dir);
	}
}
