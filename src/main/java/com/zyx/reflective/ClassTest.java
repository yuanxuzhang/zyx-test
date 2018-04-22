package com.zyx.reflective;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * Class类测试
 * 在程序运行期间，Java运行时系统始终为【所有的对象】维护【一个】被称为【运行时的【类】型标识】。这个信息跟踪这每个对象所属的类。
 * 【虚拟机利用运行时类信息【选择相应的方法】执行】。
 * 可以通过专门的Java类访问这些信息。保存这些信息的类被称为Class。Object类中的getClass方法将返回一个Class类型的实例。
 * 一个Class对象表示一个特定类的属性。
 */
public class ClassTest {
	
	private String name;

	public static void main(String[] args) {
		
		ClassTest classTest = new ClassTest();
		
		// 最常用的Class方法时getName，这个方法将返回类的名字 com.zyx.reflective.ClassTest
		System.out.println(classTest.getClass().getName());
		
		/*
		 * 可以调用forName获取【类名】对应的Class对象
		 * */
		String className = "java.util.Date";
		try {
			// java5后Class类的参数化
			Class<?> cl2 = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// NOP
		}
		
		// 获取Class对象的第三种方法
		Class cl3 = Date.class;
		
		// 一个Class对象实际上表示的是一个【类型】，而这个类型未必一定是一种【类】
		Class cl = int.class;
		
		// 历史原因getName应用于数组类型时返回奇怪的名字
		System.out.println(int[].class.getName());//[I
		System.out.println(Double[].class.getName());//[Ljava.lang.Double;
		
		/*
		 * newInstance方法可以用来快速创建一个类的实例，newInstance方法调用默认的构造器（无参）初始化新创建的对象，如没有默认
		 * 构造器,就会抛出异常。 可实现根据类型创建类对象。
		 */
		try {
			classTest.getClass().newInstance();
		} catch (InstantiationException e) {
			// NOP
		} catch (IllegalAccessException e) {
			// NOP
		}
		
		
		/*
		 * 利用反射分析类的能力
		 * 
		 * 检查类的结构
		 * java.lang.reflect包中有三个类Field、Method和Constructor分别用于描述类的域、方法和构造器。
		 * 该包还存在Modifier类用于分析上边类的getModeifiers返回值。
		 * */
		
		/*
		 * getFields、getMethods和getConstructors分别返回【public】域、方法和构造器【数组】,包括超类的公有成员。
		 * */
		Field[] fields = classTest.getClass().getFields();
		Method[] methods = classTest.getClass().getMethods();
		Constructor<?>[] constructors = classTest.getClass().getConstructors();
		/*
		 *getDeclareFields、getDeclareMethods和getDeclareConstructors方法将分别返回类中声明的【全部】域、方法
		 *和构造器，其中包括私有和受保护成员，但【不包括超类的成员】。 
		 * */
		Field[] declareFields = classTest.getClass().getDeclaredFields();
		Method[] declareMethods = classTest.getClass().getDeclaredMethods();
		Constructor<?>[] declareConstructors = classTest.getClass().getDeclaredConstructors();
		/*
		 * 还可得到类加载器、注解和包名等
		 * */
		classTest.getClass().getClassLoader();
		classTest.getClass().getAnnotations();
		classTest.getClass().getPackage();
		classTest.getClass().getSigners();
		
		
		/*
		 * 在运行时使用反射分析对象
		 * 
		 * 在编写程序时，如果知道想要查看的域名和类型，查看指定的域是一件很容易的事情。而利用反射机制可以查看在编译时还不清楚的类对象域
		 * 查看对象域的关键方法是Field类中的get方法。如果f是一个Field类型的对象，obj是某个包含f域的类的对象，f.get（obj）将
		 * 返回一个对象，其值为obj域的当前值
		 * 
		 * 【同理，调用f.set(obj, value)可以将obj对象的f域设置成新值】。
		 * */
		ClassTest instance = new ClassTest();
		Class<? extends ClassTest> classTestClass = instance.getClass();
		Field field = null;
		try {
			field = classTestClass.getDeclaredField("name");
		} catch (NoSuchFieldException e) {
			// NOP
		} catch (SecurityException e) {
			// NOP
		}
		/* name是一个私有域，所以get方法将会抛出一个IlleageAccessException,只有利用get方法才能得到可访问域的值，除非有
		 * 访问权限，否则Java安全机制只允许查看任意对象有哪些域，而不允许读取它们的值。
		 */
		field.setAccessible(true);
		try {
			Object nameValue = field.get(instance);
		} catch (IllegalArgumentException e) {
			// NOP
		} catch (IllegalAccessException e) {
			// NOP
		}
		
		
		/*
		 * 使用反射编写泛型数组代码
		 * 
		 * java.lang.reflect包中的Array类允许动态地创建数组。
		 * 
		 * */
		// 暂时设个新值
		int newLenth = 200;
		// 判定原实例是否为数组对象
		if(!classTestClass.isArray())
			return;
		// 获得原对象的数组元素类型
		Class<?> componentType = classTestClass.getComponentType();
		// 原数组对象的长度
		int length = Array.getLength(classTestClass);
		// 关键方法，创建新数组
		Object newArray = Array.newInstance(componentType, newLenth);
		System.arraycopy(instance, 0, newArray, 0, Math.min(length, newLenth));
		
		
		/*
		 * 调用任意方法
		 * 
		 * 在Method类中由一个invoke方法，它允许调用包装在当前Method对象中的方法，方法签名为：
		 * Object invoke(Object obj, Object...args)
		 * 第一个参数是隐式参数，其余的对象提供了显示参数，对于静态方法，第一个参数可以被忽略，即，将他设置为null。
		 * 
		 * 【如果返回类型是基本类型，invoke方法返回其包装类型，装箱操作】
		 * 
		 * 总结：使用Method对象调用方法
		 * 1、程序设计风格不太简便，出错可能性也比较大。
		 * 2、invoke的参数和返回值必须是Object类型的。这意味着必须进行多次的类型转换，这样做会使编译器错过检查代码的机会，等到测试
		 * 阶段才会发现错误
		 * 3、使用反射获得方法指针的代码要比仅仅直接调用方法明显慢一些。
		 * 
		 * 
		 * */
		// NOP
	}

}
