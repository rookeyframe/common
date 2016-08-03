package com.common.io.unit;

import org.junit.Test;

import com.common.io.CopyFileUtil;

public class CopyFileUtilUnit {
	
	@Test
	public void copyFileTest() {
		//复制单个文件，如果目标存在，则覆盖
		String srcPath = "D:/temp/tempfile0.txt";
		String destPath = "D:/temp_bak/tempfile0_bak.txt";
		CopyFileUtil.copyFile(srcPath, destPath, true);
		//如果目标存在，则不覆盖
		CopyFileUtil.copyFile(srcPath, destPath);
		System.out.println();
		//复制文件夹，如果目标存在，则覆盖
		String srcDir = "C:/logs";
		String destDir = "D:/logs";
		CopyFileUtil.copyDirectory(srcDir, destDir, true);
	}

}
