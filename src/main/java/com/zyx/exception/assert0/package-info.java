/**
 * 断言（assert）测试
 * 
 * 【只用于在测试阶段确定程序内部的错误位置。】
 * 
 * 警告：
 * 断言失败是致命的、不可恢复的错误
 * 断言检查只用于开发和测试阶段（这种做法有时被戏称“在靠近海岸是穿上救生衣，但在海中央时把救生衣抛掉吧”）
 * 
 * 在一个具有自我保护能力的程序中，断言很常用。假设【确定某个属性符合要求】，并且代码的执行依赖于这个属性，在测试阶段我们还是希望检测这个属性，
 * 这里就可以使用断言机制。
 * 断言机制允许在测试期间向代码中插入一些检查语句。但【代码发布时，这些插入的检测语句将会自动地被移走】。
 * 语法：
 * assert 条件；
 * assert 条件：表达式；
 * 这两种形式都会对条件进行检测，如果结果为【false】，则抛出一个AssertionError异常。在第二种形式中，表达式将被传入AssertError的
 * 构造器，并转换成一个消息字符串。
 * 
 * 断言启用禁用：
 * 默认情况下，断言被禁用。可以在运行程序时用-enableassertions或者-ea选项启用它。
 * java -enableassertions MyApp
 * 注意：启用禁用断言时不必重新编译程序。启用和禁用断言是类加载器（class loader）的功能。当断言被禁用时，类加载器将跳过断言代码，因此，不会
 * 降低程序运行速度。
 * 
 * 使用场景:
 * if(i%3 == 0){
 *  ...
 * }
 * else if(i%3 == 1){
 *  ...
 * }
 * else//(i%3 == 2){
 * 	...
 * }
 * 
 * 使用断言
 * 默认i应该是大于零的
 * assert i >=0;
 *if(i%3 == 0){
 *  ...
 * }
 * else if(i%3 == 1){
 *  ...
 * }
 * else{
 * 	// 只有三种结果 0、1 、2
 *  assert i%3 == 2 
 * 	...
 * }
 */
package com.zyx.exception.assert0;