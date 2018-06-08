/**
 * Thread 总结
 * 【并发执行】的进程数目并不是由CPU数目决定的，操作系统将CPU的时间片分配给每一个进程，给人【并行】处理的感觉。
 * 多线程程序在较低的层次上扩展了多任务的概念：一个程序同时执行多个任务，通常，每一个任务称为一个线程（thread），它是线程控制的简称。可以
 * 同时运行一个以上线程的程序称为多线程程序（multithreaded）。
 * 
 * 多进程和多线程：
 * 本质在于每个进程拥有自己的一整套变量，而线程则共享数据。
 * 1）共享变量使线程之间的通信比进程之间的通信更有效、更容易。
 * 2）在操作系统中，与进程相比，线程更“轻量级”，创建、撤销一个线程比启动新进程的开销要小得多。
 * 3）共享数据可能存在风险，需要加以控制。
 * 
 * 2、线程中断
 * 线程终止的情况：
 * 1）run方法执行到方法体的最后一条语句，并经由执行return语句返回时。
 * 2）出现了在方法中没有捕获的异常时，线程将终止。
 * 3）已弃用的stop方法。
 * 中断：        中断的线程不应定是要被终止的。
 * interrupt方法可以用来请求终止线程，当一个线程调用interrupt方法时，线程中的【中断状态】将被置空。这是每一个线程都具有的boolean
 * 标志。每个线程都不应该不时地检查这个标志，以判断线程是否被终端。     当一个被阻塞的线程（sleep或wait）上调用interrupt方法时，阻塞调用
 * 将 会InterruptException异常中断
 * 
 * 3、线程的六种状态
 * 1）New（新创建）
 * new操作符创建新线程到start方法调用之前的阶段（程序还没有开始运行线程代码，做一些运行之前定的基础工作）
 * 2）Runnable（可运行）
 * 一旦调用start方法，线程处于runnable状态，一个可运行的线程可能正在运行也可能没有运行，这取决于操作系统给线程提供运行的时间。
 * 3）Blocked（被阻塞）------------内部锁
 * 4）Waiting（等待）--------------wait
 * 5）Timed waiting（计时等待）
 * 当线程处于被阻塞或等待状态时，它暂时不活动，它不运行任何代码且消耗最少的资源，直到【线程调度器】重新激活它。
 * 当一个线程试图获取一个【内部的对象锁】（非concurrent库中的锁，而该锁被其他线程持有，则线程进入阻塞状态），当所有其他线程释放该锁，并且
 * 线程调度器允许本线程持有它的时候，该线程将变成非阻塞状态。
 * 当线程等待另一个线程通知调度器一个条件时，它自己进入等待状态。
 * 在调用Object.wait、Thread.join、concurrnt中的Lock或Condtion时，就会出现等待状态。
 * 6）Terminated（被终止）
 * run方法退出
 * run方法体异常
 * 
 * 4、线程属性
 * 每一个线程有一个优先级。默认情况下，一个线程继承它的父线程的优先级
 * setPriority（）--MIN_PRIORTY（1）---NORM_PIORITY（5）---MAX_PRIORITY（10）
 * 每当线程调度器有机会选择新线程时，他首先选择具有较高优先级的线程。但是，线程优先级是高度依赖于系统的。当虚拟机依赖于宿主机平台的线程实现
 * 机制时，Java线程的优先级被映射到宿主机平台的优先级上，优先级个数也许更多，也许更少。
 * 【不要将程序构建为功能的正确性依赖于优先级，多个高优先级的线程没有进入非活动状态，低优先级的线程可能永远也不能执行】
 * yield():导致当前执行线程处于让步状态，如果有其他的可运行线程具有至少与此线程同样高的优先级，那么这些线程接下来会被调控。
 * 守护线程（Daemon）：setDaemon（true）；
 * 守护线程的唯一用途是为其他线程提供服务，当只剩下守护线程时，虚拟机就退出，由于如果只剩下守护线程，就没必要继续运行程序了。
 * 守护线程应该永远不去访问固有资源（文件、数据库等），他会在任何时候甚至在一个操作的中间发生中断。
 * 
 * 异常：线程的run方法不能抛出任何被检测的异常，但是，不被检测的异常会导致线程终止，这种情况下，线程就死亡了。
 * 不需要任何catch子句来处理可以被传播的异常，相反，就在线程死亡之前，异常被传递到一个用于捕获异常的处理器。
 * 可以使用setUncaughtExceptionHandler方法为任何线程安装一个处理器。也可以用Thread类的静态方法setUncaughtExceptionHandler
 * 为所有线程安装一个默认的处理器。替换处理器可以使用日志API发送未捕获异常的报告到日志文件。
 * 如果不安装默认的处理器，默认的处理器为空。但是，如果不为独立的线程安装处理器，此时的处理器就是该相乘的ThreadGroup对象。
 * 处理逻辑：
 * 有父线程组则掉父线程组的uncaughtException方法
 * 否则，Thread.getDefaultExceptionHandler返回非空处理器，则调用。
 * 否则，Throwable时ThreadDeath的一个实例，什么都不做。
 * 否则，线程的名字以及Throwable的栈踪迹被输出到System.err上。
 * 
 * 5、同步
 * 竞争条件（race condition）：两个或两个以上的线程需要共享对同一数据的存取。如果两个线程存取相同的对象，并且每一个线程都调用了一个
 * 修改对象状态的方法的情形。
 * 锁一般加到要共享修改的变量对象上，如果两个线程试图访问同一个对象，那么所以串行方式提供服务，但是两个线程访问不同的对象实例，每一个线程
 * 得到不同的锁，两个线程不会发生阻塞。
 * 锁是可重入的，因为线程可以重复地获得已持有的锁。锁保持一个持有计数（hold count）来跟踪lock方法的嵌套调用。线程在每一次调用lock
 * 都要调用unlock来释放锁，由于这一特性，被一个锁保护的代码可以调用另一个使用相同的锁的方法。
 * 条件对象（条件变量conditional variable）：通常，【线程进入临界区，却发现在某一条件满足之后他才能执行】，要是用一个 条件对象来管理
 * 那些已经获得了一个锁但是却不能做有用工作的线程。
 * 【一定是先锁住，亦进入了临界区之后再判断条件，要是在临界区之外，很可能条件成立之后再次被中断回来后条件不成立了】
 * 一个锁对象可以有一个或多个相关的条件对象，可以使用newCondition方法获得一个条件对象。
 * 条件对象命名应当知名见意。
 * *******************************************************************************************
 * 等待获得锁的线程和调用await方法的线程存在本质上的不同。一旦一个线程调用了await方法，它进入【条件的等待集】，。当锁可用时该线程不能马上
 * 解除阻塞。相反，它处于阻塞状态直到另一个线程调用同一个条件上的signalAll方法时为止。需要等通知。
 * signalAll重新激活因为这一条件而等待的所有线程。当这些线程从等待集当中移除时，他们再次称为可运行的，调度器将再次激活它们。同时，它们试图
 * 重新进入该对象。一旦锁称为可用的，它们中的某个将从await调用返回，获得该锁并从被阻塞的地方继续执行。
 * **************************此时，线程应该再次测试该条件。由于无法确保该条件被满足---signalAll方法仅仅是通知正在等待的线程
 * ：此时有可能已经满足条件，值得再次去 * 检测该条件。
 * *******************************************************************************************
 * 如果没有其他线程来重新激活等待的线程，它就永远不在运行，这将导致死锁现象。
 * 条用signalALl时机：在对象的状态有利于等待线程的方向改变时调用
 * signalAll不会立即激活一个等待线程，他仅仅解除等待线程的阻塞，以便这些线程可以在当前线程退出同步方法之后，通过竞争实现对对象的访问
 * signal是随机解除等待集中某个线程的阻塞状态，这比基础所有的阻塞更加有效，但也存在危险。如果随机选择的线程发现自己仍然不能运行，那么
 * 他再次被阻塞。如果没有其他线程再次调用signal，那么系统就死锁了。
 * 
 * Synchronized Java中的每一个对象都有一个内部锁。如果一个方法用synchronized关键字声明，那么对象的所将保护整个方法，也就是说，
 * 要调用该方法，线程必须获得内部的对象锁。
 * 声明方法VS锁住特定对象
 * 内部对象锁只有一个相关条件。wait方法添加一个线程到等待集中，notifyAll/notify方法解除等待线程的阻塞状态。
 * 将静态方法声明为sychronized也是合法的，如果调用这个方法，该方法获得相关类对象的内部锁。
 * 
 * 内部锁和条件存在一些局限：
 * 1）不能中断一个正在试图获得锁的进程。
 * 2）试图获得锁时不能设定超时。
 * 3）每个锁仅有单一的条件，可能是不够的。
 * 在代码中应该使用哪一种？lock和condition对象还是同步方法？：
 * 1）最好既不使用Lock/Condition也不使用sysnchronized关键字。在许多情况下可以使用java.util.concurrent包中的一种机制
 * 它会为你处理所有的加锁------阻塞队列
 * 2）如果synchronized关键字适合程序，那么尽量使用它，这样可以减少编写的代码数量，减少出错的几率
 * 3）如果特别需要Lock/Condition结构提供的独有特性时，才使用Lock/Condition。
 * 
 * 客户端锁定（clientside locking）：使用一个对象的锁来实现额外的原子操作。
 * 
 * Voilatile：应对仅仅为了读写一个或两个实例域就是用同步的过度开销。原因有二：
 * 1）多处理器的计算能够暂时在【寄存器】或【本地内存缓冲区】中保存内存的值，结果是，运行在不同处理器上的线程可能在同一个内存位置取到不同的值。
 * 2）【编译器】可以改变指令执行顺序以使吞吐量最大化。这种顺序上的变化不会改变代码语义，但是编译器假定内存的值仅仅在代码中显示的修改指令时才会
 * 改变，然而，内存的值可以被另一个线程改变。
 * 解释：三条连续指令：d1 将变量A存入寄存器M，d2 将变量B存入寄存器M， d3使用A的值，  这样就可以优化为：d1---d3---d2
 * 锁机制可以实现：处理器被要求通过在必要的时候刷新本地缓存来保持所得效应，编译器不能不正当地重新排序指令。
 * 如果域被声明为volatile那么【编译器】和【虚拟机】就知道该域可能被另一个线程并发更新。
 * 【Voiatile变量不能提供原子性】
 * 
 * final：可以安全地访问一个共享域,其他线程只会在构造幻术完成构造之后才能看到final的域（非静态模式）
 * 
 * 原子性：假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量申明为volatile。
 * 另外java.util.concurrent.atomic包中有很多类使用了很高效的机器指令（不是锁）来保证其操作的原子性，AtomicInteger...
 * 
 */
package com.zyx.thread;