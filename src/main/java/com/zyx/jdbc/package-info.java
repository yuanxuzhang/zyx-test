/**
 * JDBC 
 * 设计思想：     
 * *************************程序-------驱动管理器------驱动管理器------实际数据库*******************************
 * 根据API编写的程序都可以与驱动管理器进行通信，而驱动管理器则通过驱动程序与实际的数据库通信。
 * 三点：
 * 1）一套“纯”JavaAPI
 * 2）一个驱动管理器
 * 3）一套简单机制，以使的第三方驱动程序可以向驱动管理器注册
 * 驱动器工作原理：
 * 一种是：使用一种与具体数据库无关的协议将数据库请求发给服务器构件，然后该构件再将数据库请求翻译成数据库相关的协议，简化部署，平台相关代码只位于服务器端。
 * 一种是：将JDBC请求直接翻译成数据库相关的协议。
 * 优点：
 * 通过使用标准的SQL语句，甚至是专门的SQL扩展，程序员就可以利用Java语言开发数据库的应用，同时还依旧遵循Java语言的相关约定。
 * 数据库供应商和数据库工具开发商可以提供底层的驱动程序。因此，他们可以优化各自数据库产品的驱动程序。
 * 
 * JDBC配置
 * 1）URL：
 * jdbc：subprotocal：other stuff
 * subprotocal用于选择连接数据库的具体驱动程序,驱动器管理器（DriverManager）遍历所有注册过的驱动器，以便找到一个能够使用数据库URL中指定的子协
 * 议的驱动程序。
 * other stuff参数的格式随所使用的subprotocal不同而不同。
 * 2）驱动程序文件
 * 3）注册驱动器类
 * 一种方式是在Java程序中加载驱动器类   Class.forname()
 * 一种方式是设置jdbc.driver属性      java Djdbc.driver="XXX.XXXX.XXX" 
 * 
 * 执行SQL语句：
 * 1）获取【连接】Connection对象，通过驱动管理器DriverManager.getConnection。
 * *************Connection对象的close方法【立即】关闭当前链接以及释放由它所创建的JDBC资源*****************************
 * 2)创建【语句】Statement对象（不带参数）或PrepareStatement，Connection对象的createStatement方法。
 * executeUodate方法既可以执行DML（INSERT、UPDATE和DELETE）之类的操作，也可以执行DDL（ALERT、CREATE和DROP等）数据定义的操作
 * 他返回SQL命令影响的行数。
 * executeQuery方法执行SELECT查询，返回ResultSet类型对象，可以通过它来每次一行地迭代遍历所有查询结果。
 * ResultSet接口的迭代协议与java.util.Iterator接口稍有不同。对于ResultSet接口，迭代器初始化时被设置在第一行之前的位置，必须调用next
 * 方法将它移动到第一行。另外，他没有hasNext方法，我们需要不断地调用next，直到该方法返回false。
 * 与数组的索引不同，数据库的列序号是从1开始计算的。
 * executeQuery方法执行任意的SQL语句，通常只用于用户提供的交互式查询。可能产生多个结果集和更新计数。
 * **********************Statement对象的close方法用于关闭Statement对象以及他所对应的结果集。*************************
 * ******************************ResultSet对象的close方法【立即】关闭当前的结果集*********************************
 * 
 * 连接、语句和结果集之间关系：
 * 每个连接（Connection）对象都可以创建一个或多个Statment对象。同一个Statement对象可以用于多个不相关的命令和查询。但是，一个Statement
 * 对象最多只能有一个打开的结果集。如果需要执行多个查询操作，且需要同时分析查询结果，那么必须创建多个Statement对象。通常不需要同时处理多个结果集。
 * 如果结果集相互关联，可以使用组合查询，这样就只需要分析一个结果集。【对数据库进行组合查询比使用java程序遍历多个结果集要高效的多】。
 * 使用完ResultSet、Statement和Connection对象后，应立即调用close方法，这些对象都是用了【规模较大的数据结构】和【数据库存储器上的有限资
 * 源】。
 * 如果Statement对象上有一个打开的结果集，那么调用close方法将自动关闭该结果集。同样的，调用Connection类的close方法将关闭该连接上的所有语句。
 * 在Statement是上调用closeOnCompletion方法，在其所有结果集都被关闭后，该语句会立即被自动关闭。
 * 如果连接都是在暂时的，那么无需考虑关闭语句和结果集。只需要close语句放在带try语句中，以便确保最终连接对象不可能继续保持打开状态。
 * 
 * 预备语句（prepare statement）
 * ****每当数据库执行一个查询时，他总是首先通过计算来确定【查询策略】，以便高效地执行查询操作。通过事先准备好查询并多次重用它，我们就可以确保：***
 * *********************************查询所需的准备步骤只被执行一次********************************************
 * PreparedStatement的第一次执行消耗很高，它的性能体现在后边的重复执行，现产生一个优化查询，而后才会执行下旬请求。外加批量执行
 * Statement只有一次请求，适用于不重复执行的情况。
 * 在预备查询语句中，每个宿主变量都用“？”来表示。如果如果存在一个以上的变量，那么在设置变量值时必须注意“？”的位置。在执行预备语句之前，必须使用set
 * 方法将变量绑定到实际的值上，Set方法接受两个参数，参数一指定宿主变量的位置，第二个参数指定赋予宿主变量的值。
 * 如果想要重用已执行过的预备查询语句，那么除非使用set方法或调用clearParameters方法，否则所有宿主变量的绑定都不会改变，即，从一个查询到另一个
 * 查询到另一个查询的过程中，只需要使用setXxx方法重新绑定那些需要改变的变量即可。
 * 字符串拼接缺点：
 * 1）需要格外注意特殊字符串，容易出错。
 * 2）存在注入的风险。
 * 在相关的Connection对象关闭之后，PreparedStatement对象也就变得无效了。不过，许多数据库通常都会自动缓存预备语句。如果相同的查询被预备两
 * 此，数据库通常会直接重用查询策略。因此，无需过多考虑调用prepareStatement的开销。
 * 
 * 相对于大量的迭代多个结果集，使用数据库的复杂语句要比使用Java程序好得多----这是数据库的一个重要优点，一般而言，可以使用SQL解决的问题，就不要使用
 * Java程序。
 * 
 * LOB(large Object)
 * 除了数字、字符串和日期之外，许多数据库还可以存储大对象，例如图片或其他数据。在SQL中，二进制大对象称为BLOB，字符型大对象称为CLOB。
 * 要读取【LOB】，需要执行SELECT语句，然后在ResultSet上调用getBlob或getClob方法，这样就可以获得Blob或Clob类型的对象。
 * 要从【Blob】中读取【二进制数据】，可以调用getBytes或getInputStream方法。
 * 要从【Clob】中读取【字符型数据】，可以调用getSubString或者getCharacterStream方法。
 * 将LOB置于数据库中，需要在Connection对象上调用createBlob或者createClob，然后获取一个用于该LOB的输出流或写出器，写出数据，并将该对象
 * 存储到数据库中。
 * 
 * SQL转义----------JDBC驱动程序负责翻译转义SQL到具体的数据库环境中
 * “转义”语法支持各种数据库普遍支持的特性，但是数据库使用的是与数据库相关的语法变体。
 * 将转义语法转义为特定数据库的语法是JDBC驱动程序的任务之一。
 * 转义主要用于下列特性：
 * 1）日期和时间【字面常量】
 * 日期和时间字面常量随数据库的不同而变化很大，要嵌入日期或时间字面常量，需要按照ISO8601格式指定它的值，之后驱动程序会将其翻译为本地格式，例如：
 * {d 'xxxx-xx-xx'}
 * {t 'xx:xx:xx'}
 * {ts 'xxxx-xx-xx xx:xx:xx.xxx}
 * 2)调用标量函数
 * 标量函数（scalar function）是指[仅返回一个值]的函数。在数据库中包含大量的函数，但是不同的数据库中这些函数名存在着差异。JDBC规范提供了标准
 * 的名字，并将其转义为数据库相关的名字，函数调用格式如下：
 * {fn left(?, 20)}
 * {fn user()}
 * 3)调用存储过程(stored procedure)
 * 是在数据库中执行的用数据库相关的语言编写的过程。
 * 要调用存储过程，需要使用call转义命令，其中在存储过程没有任何参数的时候，就不用加上括号，另外使用=来捕获存储过程的返回值。
 * {call PROC1(?,?)}
 * {call PROC2}
 * {call ? = PROC3(?)}
 * 4)外连接
 * 并非所有的数据库对于这些（outer join left right full）外连接都是用标准的写法，因此需要使用转义。
 * select * from { oj XXX LEFT OUTER JOIN XXXX ON XXX=XXX}
 * 5）在like子句中的转义字符
 * _和%在LIKE子句中具有特殊含义，用来匹配一个字符串或一个字符序列。目前并不存在任何在字面上使用它们的标准方式，所以如果想要匹配所有包含_字符的字符串
 * 就必须使用如下结构：
 * ...WHERE ? LIKE %!_%{escape '!'}
 * 说明：将！定义为转义字符，而！_组合表示字面常量下划线。
 * 
 * 多结果集
 * 在执行【存储过程】，或者在使用允许在【单个查询中提交多个SELECT】语句的数据库时，一个查询可能会返回多个结果集。
 * 1）执行execute方法执行SQL
 * 2）根据上边的返回结果：true结果连第一个是结果集，调用getResult方法获取结果集，false调取getUpdateCount获取更新计数。
 * 3）重复调用getMoreResult方法以【移动到下一个结果集】，还是使用getResultSet获取结果集。
 * 4）退出条件getUpdateCount返回-1。
 * 
 * 获取自动生成键
 * 大多数数据库都支持某种在数据库中对行自动计数的机制。但是，不同的提供商所提供的机制之间存在着很大的差异，而这些自动计数的值经常作为主键。尽管JDBC没有
 * 提供独立于提供商的自动生成键的解决方案，但是他提供了获取自动生成键的有效途径。
 * stmt.executUpdate(insertStatement, Statement.RETURN_GENERAtED_KEY);
 * ResultSet rs = stmt.getGeneratedKeys();
 * if(rs.next){
 * 		int key = rs.getInt(1);
 * }
 * 
 * 可滚动的结果集       ----可滚动的结果集有一个【游标】，用于指示当前位置-------
 * 默认情况下，结果集是不可滚动和不可更新的。为了从查询中获取可滚动的结果集，必须使用：conn.createStament（type，concurrency）
 * type种类：
 * ResultSet.TYPE_FORWORD_ONLY 不可滚动
 * ResultSet.TYPE_SCROLL_INSENSITIVE 结果集可滚动，但对--数据库变化--不敏感。
 * ResultSet.TYPE_SCROLL_SENITIVE        可滚动，且对--数据库变化--敏感。
 * 
 * ResultSet.CONCUR_READ_ONLY   结果集不能用于更新数据库
 * ResultSet.CONCUR_UPDATEBLE   结果集可以用于更新数据
 * 使用可滚动的结果集是非常简单的，----将查询数据放入缓存中----的复杂工作是由数据库驱动程序在后台完成的。
 * 
 * 可更新的结果集
 * 编辑的结果集，且数据的变化会自动反映到数据库中。
 * 可更新的的结果集并非必须是可滚动的，但一般是可滚动的。
 * 并非所有的查询都会返回可更新的结果集。如果查询涉及多个表的连接操作，那么它所产生的结果集将是不可更新的。
 * 如果查询只涉及到一个表，或者查询时使用主键连接多个表，那么它所产生的结果集将是可更新的结果集。
 * 可以调用ResultSet接口中的getConcurrency方法来确定结果集是否是可更新的。
 * 
 * 行集（RowSet）
 * 可滚动的结果集需要在交互过程中始终与数据保持连接，稀有的数据库连接资源被占用。
 * RowSet接口扩展自ResultSet接口，却无需始终保持与数据库的连接。
 * 行集的实现类：
 * CachedRowSet：被缓存的行集，允许在断开连接的情况下执行操作。
 * WebRowSet：        一个被缓存的行集，可以保存为XML文件，改文件可以移动到Web应用的其它层，只要在该层中使用WebRowSet对象重新打开。
 * FilteredRowSet和JoinRowSet：支持对行集的轻量级操作，等同于SQL中的SELECT和JOIN操作，它们操作的是存储在行集中的数据，运行时无需建立
 * 数据库连接。
 * JdbcRowSet：是ResultSet接口的一个瘦包装器。它从RowSet接口继承了均有用的get和set方法，从而将一个结果集转换成一个“bean”。
 * 构建方法：
 * RowSetFactory factory = RowSetProvider.newFactory();
 * CachedRowSet crs = factory.createCachedRowSet();
 * 一个被缓存的行集包含了一个结果集中所有的数据。
 * CachedRowSet是ResultSet接口的子接口，可以完全像使用结果集一样使用行集。
 * 行集在断开数据库连接后仍然可以使用行集，1打开数据库连接--2执行查询操作--3将查询结果放入被缓存的行集populate--4关闭数据连接。
 * 可以直接修改被缓存的行集中的数据，这些修改不会立即反馈到数据库中，必须发起一个显示的请求，以便让数据库真正接受所有修改，此时CachedRowSet类会重新
 * 连接到数据库，并通过执行SQL语句向数据库中写入所有修改后的数据。
 * 如果查询量非常大，不想将所有数据全部放入行集，请使用分页 cachedRowSet.setPageSize(number),获取下一页nextPage()。
 * 可以使用与结果集中相同的方法来查看和修改行集中的数据，如果修改了行集中的内容，nm必须使用acceptChanges([connection])将修改写会数据库。
 * 一个复杂化的问题：对于填充行集之后，数据库中的数据发生变化，而行集修改了变化的值，造成的不一致，【参考实现】将会检查行集的原始值和数据库的值是否一致，不一致
 * 将抛出SyncProviderException异常，且不向数据库写会任何值。
 * 
 * 元数据（MetaData）
 * JDBC提供了关于数据库以及表结构的详细信息，（获取数据库的所有表，获取表的列和数据类型）。
 * 特别适用于数据库工具开发人员。
 * 三类元数据：
 * 1）数据库的元数据
 * 使用数据库连接对象的getMetaData方法获取一个DatabaseMetaData对象
 * 2）结果集的元数据
 * ResultSetMetaData用于提供结果集的相关信息（例如列名称、类型和字段宽度等等）
 * 3）预备语句参数的元数据
 * 
 * 事务（Transaction）
 * 构建一组语句，当所有语句都顺利执行之后，事务可以被提交（commit），如果其中某个语句遇到错误，那么事务就被回滚（rollback），就好像没有任何语句被执行过一样。
 * 目的：保证数据库的完整性。
 * 默认情况下，数据库连接处于自动提交模式（AutoCommit Mode），每个SQL语句一旦被执行便被提交给数据库，一旦命令被提交，就无法对他进行回滚操作。
 * 保存点（Save Point）
 * 某些驱动程序，使用保存点更细粒度地控制回滚操作，创建保存点：connection.setSavepoint, connection.rollback("保存点")
 * 不再需要保存点时，必须释放它，connection。releaseSavepoint("保存点")
 * 
 * 批量更新（Batch Update）
 * 需要执行许多insert语句时，可以使用批量更新的方法来提高程序性能，一个【语句序列】作为一批操作同时被【收集】和【提交】。
 * DatabaseMetaData接口中的supportBatchUpdate方法可以知道数据库是否支持批量更新。
 * ------处于同一批中的语句可以使insert、update和delete等操作，也可以是数据库定义语句create、drop。但是，在批量语句中添加Select会抛出异常。-------
 * 执行过程：
 * 1）创建语句Statement对象。
 * 1.1）关闭自动提交模式。
 * 2）调用语句的addBatch方法添加命令。
 * 3）调用语句的executebatch方法提交批量更新。
 * 3.3）执行提交操作。
 * **********为了在批量模型下正确地处理错误，必须将批量执行的操作视为单个事务，如果批量更新的执行过程中失败，必须将它回滚到批量操作之前的状态。*********
 * 
 * 数据类型对照关系：
 *       SQL数据类型                                                                              Java数据类型
 *       INTEGER或INT	                    int
 *       SMALLINt							short
 *       NUMBER(m.n)DECIMAL(m,n)            java.math.BigDecimal
 *       FLOAT(n)							double
 *       REAL								float
 *       DOUBLE								double
 *       CHARCTER(n)或CHAR(n)				String
 *       VARCHAR(n)LONGVARCHAR				String
 *       BOOLEAN							boolean
 *       DATE								java.sql.Date
 *       TIME								java.sql.TIME
 *       TIMESTAMP							java.sql.Timestamp
 *       BLOB								java.sql.Blob
 *       CLOB								java.sql.Clob
 *       ARRAY								java.sql.Array
 *       NCHAR(n)NVARCHAR(n)LONGNVCHAR		String
 *       NCLOB								java.sql.NClob
 *       SQLXML								java.sql.SQLXML
 *       ROWID								java.sql.RowId
 * SQL ARRAY(SQL数组)指的是值得序列。getArray方法返回一个接口类型为java.sql.Array的对象。
 * 从数据库中获取一个LOB或数组并不等于获取它的实际内容，只有在访问具体的值时它们才会从数据库中被读取出来。
 * 某些数据库支持描述行位置的ROWID值，这样就可以非常快捷地获取某一行值
 * 
 * Drivermanager和JNDI（java名字和目录接口）
 * 在Web或企业中JNDI可用来定位(lookup)数据源,数据源就是一个能够提供简单的JDBC连接和更过高级服务的接口（比如，多库间的分布式事务控制）
 * 
 * 数据库连接池（Pool）
 * 数据库连接从池中获取，用完后返还连接池，连接在物理上并未被关闭，而是保留在一个队列上并被反复重用。
 * 连接池是一种非常重要的服务，JDBC规范为实现者提供了用以实现连接池服务的手段。不过，JDK本事并未实现这项服务，数据库供应商提供的JDBC驱动程序中通常也不包含这项
 * 服务，Web容器和应用服务器的开发商通常会提供连接池服务。
 * 连接池的使用对程序员来说是完全透明的，可以通过获取数据源并调用getConnection方法来得到连接池中的连接。使用玩连接之后，需要调用close方法。该方法并不在物理
 * 上关闭连接，而只是告诉连接池已经使用完该链接。连接池通常还会将池机制作用于预备语句上。
 */
package com.zyx.jdbc;