/**
 * Java编程语言使用关键字native表示本地方法，关键字native提醒编译器该方法将在外部定义。当然，本地方法不包含Java编程语言的代码，而且方法
 * 标题后直接跟着一个表示终结的分号（看上去更像抽象方法声明）。
 * 
 * 将一个本地方法链接到Java程序中步骤：
 * 1）在Java类中声明一个本地方法。
 * 2）运行javah以获得包含该方法的C声明的头文件。
 * 3）用C实现该本地方法。
 * 4）将代码置于共享类库中。
 * 5）在Java程序中加载该类库。（System.loadLibrary()）
 * 
 * 类型对应：
 * Java平台上int类型总是32位，而C语言取决于平台，存在16位和32位两种情形             Java类型--------------jJava类型    例如 int-------jint（本地接口定义的）
 */
package com.zyx.native0;