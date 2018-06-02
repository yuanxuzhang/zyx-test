package com.zyx.collection;

import java.util.prefs.Preferences;

/**
 * Properties类能够简化读取和保存配置信息，但是，使用属性文件存在下列不足
 * 1、配置文件不能存放在用户的主目录中，附：某些操作系统没有主目录（win9X）。
 * 2、没有标准的未配置文件命名的主目录。当用户安装了多个Java应用程序时，会增加配置文件名冲突的可能性。
 * Preferences类提供了一个与平台无关的中心知识库。在window中Proferences类用来注册存储信息；Linux中，这些信息被存放在本地
 * 文件系统中。当然，对于使用Perferences类的程序员而言，中心知识库的实现是透明的。
 */
public class PreferencesTest {

	public static void main(String[] args) {

		//Preferences root = Preferences.userRoot();
		Preferences node = Preferences.userNodeForPackage(PreferencesTest.class);
		int left = node.getInt("left", 0);
		int top = node.getInt("top", 0);
	}

}
