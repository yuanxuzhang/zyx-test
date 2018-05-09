package com.zyx.debug;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class PrintStackTraceTest {

	public static void main(String[] args) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(out);
		// 重点
		new Throwable("XXX").printStackTrace(writer);
		writer.flush();
		String description = out.toString();
		System.out.println(description);
	}

}
