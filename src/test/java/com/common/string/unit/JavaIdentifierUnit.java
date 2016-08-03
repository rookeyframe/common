package com.common.string.unit;

import org.junit.Test;

import com.common.string.JavaIdentifier;

public class JavaIdentifierUnit {

	@Test
	public void javaIdentifierTest(){
		System.out.println("\"my_var\" is an identifier? "
				+ JavaIdentifier.isJavaIdentifier("int"));
		System.out.println("\"my_var.1\" is an identifier? "
				+ JavaIdentifier.isJavaIdentifier("my_var.1"));
		System.out.println("\"$my_var\" is an identifier? "
				+ JavaIdentifier.isJavaIdentifier("$my_var"));
		System.out.println("\"\u0391var\" is an identifier? "
				+ JavaIdentifier.isJavaIdentifier("\u0391var"));
		System.out.println("\"\1$my_var\" is an identifier? "
				+ JavaIdentifier.isJavaIdentifier("\1$my_var"));
	}
}
