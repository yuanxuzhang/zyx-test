package com.zyx.compile0;

import java.io.OutputStream;
import java.net.URL;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 编译Java代码的Java程序，有很多工具都需要调用Java编译器。例如：
 * 1）开发环境
 * 2）Java教学和辅助程序
 * 3）自动化的构建和测试工具
 * 4）处理Java代码段的模板工具（JavaServer Pages）
 * 5）开发动态程序
 */
public class CompileAPITest {

	public static void main(String[] args) {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		OutputStream outStream = null;
		OutputStream errStream = null;
		URL path = CompileAPITest.class.getResource("");
		System.out.println(path.getPath());
		/*
		 * 1.in → "standard" input; use System.in（键盘输入） if null
		   2.out → "standard" output; use System.out（输出到控制台） if null
		   3.err → "standard" error; use System.err if null
		   4.arguments → arguments to pass to the tool
		 */
		int result = compiler.run(null, outStream, errStream, "D:/myProject/workspace/zyx-test/src/main/java/com/zyx/compile0/CompileAPITest.java");
		// result为0表示编译成功
		System.out.println(result);
	}

}
