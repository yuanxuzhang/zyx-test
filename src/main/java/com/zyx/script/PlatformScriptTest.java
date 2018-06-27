package com.zyx.script;

import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Java平台的脚本功能
 * 脚本语言是一种通过在运行时解释程序的文本，从而避免使用通常的编辑/编译/链接/运行循环的语言。
 * 优势如下：
 * 1)便于快速变更，鼓励不断试验。
 * 2)可以修改运行着的程序的行为。
 * 3）支持程序用户的定制化。
 * 缺陷：
 * 大多数脚本语言都缺乏可以使用编写复杂应用受益的特性。例如，强类型、封装和模块化。
 * 脚本API可实现将脚本语言和传统java语言的优势相结合。它支持在Java程序中对用javaScript、Groovy、Ruby，甚至是更加奇异的诸如Scheme
 * 和Haskell等语言编写的脚本进行调用。（至于另一个方向，即从脚本语言中访问Java则属于脚本语言提供商的职责，大多数运行在Java虚拟机上的脚本语
 * 言都具有这种能力）。
 * 
 * 脚本引擎（Script Engine）
 * 脚本引擎是一个可以执行用某种特定语言编写的脚本的【类库】。当虚拟机启动时，它会发现可用的脚本引擎。
 * JavaSE7包含Rhino的一个脚本，这是由Mozilla基金开发的一个JavaScript解释器。可以通过在类路径中提供必要的JAR文件来添加对额外的语言
 * 支持。一般需要两个JAR文件集合，脚本语言自身是由单个的JAR文件或一个JAR集合实现的，而将这种语言适配到哦脚本API的引擎通常还需要一个额外的
 * JAR
 * 
 * 脚本赋值与绑定
 * 一旦拥有了引擎，就可以通过eval（String script）直接调用脚本。
 * 如果脚本存储在文件中，那么需要先打开一个Reader，然后调用：eval（reader）
 * 
 * 调用脚本的函数和方法
 * 在使用许多脚本引擎时，都可以调用脚本语言的函数，而不必对处理实际的脚本代码的计算。
 * 提供这种功能的脚本引擎实现了Invovable接口，特别是，Rhnio引擎就是实现了Invocable接口
 * 更进一步，让脚本引擎去实现一个java接口，然后就可以用java方法调用的语法来调用脚本函数。
 * 
 * 编译脚本
 * 某些脚本引擎出于对执行效率的考虑，可以将脚本代码编译为某种中间格式。这些引擎实现了Compilable接口。
 */
public class PlatformScriptTest {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException {

		/*
		 * 打印所支持的引擎
		 */
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
		if(engineFactories != null){
			// print 引擎名：Mozilla Rhino。 脚本语言ECMAScript。
			for(ScriptEngineFactory engineFactory : engineFactories){
				System.out.println(String.format("引擎名：%s。 脚本语言%s。", engineFactory.getEngineName(), engineFactory.getLanguageName()));
			}
		}
		/*
		 * 可以通过直接使用名字获取引擎
		 * 在同一个引擎脚本上调用多个脚本。如果一个脚本定义了变量、函数或类,那么大多数引擎都会保留这些定义，以供将来使用。
		 */
		ScriptEngine jsEngine = scriptEngineManager.getEngineByName("JavaScript");
		jsEngine.eval("n = 1");
		Object result = jsEngine.eval("n + 1");
		System.out.println(result);
		
		/*
		 * 多线程中并发执行脚本安全性
		 * 1)null 并发执行不安全
		 * 2)MULTITHREADED 并发执行安全。一个线程的执行效果对另外的线程有可能是可视的。
		 * 3)THREAD-ISOLATED 除了MULTITHREADED之外，会为每个线程维护不同的变量绑定。
		 */
		if(engineFactories != null && engineFactories.size() > 0){
			// print MULTITHREADED
			System.out.println(engineFactories.get(0).getParameter("THREADING"));
		}
		
		//String k = "1";
		/*
		 * 向引擎中添加新的变量绑定。绑定由名字及关联的Java对象构成。
		 * 脚本diamante从“引擎作用域”中的绑定里读取k的定义。大多数脚本语言都可以访问java对象。
		 */
		//jsEngine.put(k, 2);
		//System.out.println(jsEngine.eval("k + 1"));
		
		/*
		 * 获取由脚本语句绑定的变量
		 */
		jsEngine.eval("n = 128");
		System.out.println(jsEngine.get("n"));
		
		/*
		 * 全局作用域，任何添加到ScriptEngineManager中邦定对所有引擎都是可视的
		 */
		//scriptEngineManager.put(m, 2);
		
		/*
		 * 调用脚本函数
		 * isNaN
		 */
		if(jsEngine instanceof Invocable){
			// print true
			System.out.println(((Invocable) jsEngine).invokeFunction("isNaN", "a"));
		}
		
		/*
		 * 脚本实现接口
		 */
		jsEngine.eval("function greet(x){return 'Hello,' + x + '!'}");
		
		if(jsEngine instanceof Invocable){
			Greeter g = ((Invocable) jsEngine).getInterface(Greeter.class);
			// print Hello,World!
			System.out.println(g.greet("World"));
			
			// print Hello,undefined!
			System.out.println(jsEngine.eval("greet()"));
		}
	}

	interface Greeter{
		String greet(String whom);
	}
}

