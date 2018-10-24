# Java日志基础
## 日志组件  
Java日志API由以下三个核心组件组成：

* Loggers：Logger负责捕捉事件并将其发送给合适的Appender。
* Appenders：也被称为Handlers，负责将日志事件记录到目标位置。在将日志事件输出之前，Appenders使用Layouts来对事件进行格式化处理。
* Layouts：也被称为Formatters，它负责对日志事件中的数据进行转换和格式化。Layouts决定了数据在一条日志记录中的最终形式。  

当Logger记录一个事件时，它将事件转发给适当的Appender。然后Appender使用Layout来对日志记录进行格式化，并将其发送给控制台、文件或者其它目标位置。另外，Filters可以让你进一步指定一个Appender是否可以应用在一条特定的日志记录上。在日志配置中，Filters并不是必需的，但可以让你更灵活地控制日志消息的流动。  
## 日志框架  
在Java中，输出日志需要使用一个或者多个日志框架，这些框架提供了必要的对象、方法和配置来传输消息。Java在java.util.logging包中提供了一个默认的框架。除此之外，还有很多其它第三方框架，包括[Log4j](https://logging.apache.org/log4j/2.x/)、[Logback](https://logback.qos.ch/)以及[tinylog](http://www.tinylog.org/)。还有其它一些开发包，例如[SLF4J](https://www.slf4j.org/)和[Apache Commons Logging](http://commons.apache.org/proper/commons-logging/)，它们提供了一些抽象层，对你的代码和日志框架进行解耦，从而允许你在不同的日志框架中进行切换。

如何选择一个日志解决方案，这取决于你的日志需求的复杂度、和其它日志解决方案的兼容性、易用性以及个人喜好。Logback基于Log4j之前的版本开发（版本1），因此它们的功能集合都非常类似。然而，Log4j在最新版本（版本2）中引用了一些改进，例如支持多API，并提升了在用Disruptor库的性能。而tinylog，由于缺少了一些功能，运行特别快，非常适合小项目。

另外一个考虑因素是框架在基于Java的各种不同项目上的支持程度。例如Android程序只能使用Log4j、Logback或者第三方包来记录日志， Apache Tomcat可以使用
Log4j来记录内部消息，但只能使用版本1的Log4j。  
[组件](https://github.com/yuanxuzhang/zyx-test/blob/master/src/main/java/com/zyx/log/components.png)
## 抽象层  

诸如SLF4J这样的抽象层，会将你的应用程序从日志框架中解耦。应用程序可以在运行时选择绑定到一个特定的日志框架（例如java.util.logging、Log4j或者Logback），这通过在应用程序的类路径中添加对应的日志框架来实现。如果在类路径中配置的日志框架不可用，抽象层就会立刻取消调用日志的相应逻辑。抽象层可以让我们更加容易地改变项目现有的日志框架，或者集成那些使用了不同日志框架的项目。  

---  
# FROM  
[Java日志终极指南](http://www.importnew.com/16331.html)
