/**
 * 调试技巧
 * 
 * 1、使用下面的方法打印或记录任意变量的值
 * System.out.println("x=" + x);
 * Logger.getGlobal().info("x=" + x);
 * x会被转成字符串
 * 2、在每一个类中放置一个main方法，这样可以对每一个类进行单元测试
 * 3、利用Throwable类提供的printStackTrace方法，打印异常对象中的堆栈情况
 * 还可以使用Thread.dumpStack()方法
 * 4、一般来说，堆栈跟踪显示在System.err上。也可以利用printStackTrace（PrintWrite s）方法将他发送到一个文件中，参见PrintStackTraceTest
 */
package com.zyx.debug;