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
 * 5、java虚拟机增加了对Java应用程序进行监控和管理的支持。它允许利用虚拟机中的代理装置跟踪内存消耗、线程使用、类加载等情况。这个功能对于像应用程序服务器这样
 * 大型的、长时间运行的Java程序来说特别重要。JDK加载了一个称为jconsole的图形工具，可以用于显示虚拟机性能的统计结果。
 */
package com.zyx.debug;