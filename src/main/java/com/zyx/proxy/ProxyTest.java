package com.zyx.proxy;

/**
 * 代理 测试
 * 
 * 代理（proxy），是Java SE1.3新增加的特性。利用代理可以在运行期创建一个实现了一组给定接口的新类。
 * 这种功能只有在【编译时无法确定要实现哪个【接口】】时才有必要使用，不能确定的类可以使用反射机制实例化。
 * 不能在运行时定义这些方法的新代码。而是要提供一个调用处理器（invocateion handler）。调用处理器时实现了InvocationHandler
 * 接口的类对象，这个接口只有一个方法： Object invoke（Object proxy，Method method， Object[] args）。
 * 无论何时调用代理对象的方法，调用处理器的invoke方法都会被调用，并向其传递Method对象和原始的调用参数。调用处理器必须给出处理调用方式。
 * 使用场景：
 * 路由对远程服务器的方法调用
 * 在程序运行期间，将用户接口事件与动作关联起来
 * 为调试，跟踪方法调用
 *
 * 为何动态代理只能实现接口的代理：生成的根据接口生成的Class类文件继承了Proxy类，实现了代理的接口，由于java不能多继承，这里已经继承了Proxy类了，不能再继承其他的类，
 * 所以JDK的动态代理不支持对实现类的代理，只支持接口的代理。【动态代理详解】https://blog.csdn.net/tyrroo/article/details/79072796 
 */
public class ProxyTest {

	public static void main(String[] args) {
		
	}

}
