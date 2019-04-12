/**
 * Java集合
 * 1、迭代器
 * 目的： 为了依次访问集合中的所有元素。
 * Java迭代器查找操作与位置变更是紧密相连的。查找一个元素的唯一方法是调用next，而在执行查找操作的同时，迭代器的位置随之向前移动。因此，
 * 应将Java迭代器认为是位于两个元素之间。当调用next时，迭代器就越过下一个元素，并返回刚刚越过的那个元素的引用。
 * 类似于：（指针操作）
 * (迭代器)-element1-（迭代器）-element2-（迭代器）-element3...
 * 可以将Iterator.next与InputStream.read看做为等效的。从数据流中读取一个字节，就会自动地“消耗掉”这个字节。下一次调用read
 * 将会消耗并返回输入的下一个字节。用同样的方式，反复地调用next就可以读取集合中所有元素。
 * ArrayList的实现：
 * int cursor;       // index of next element to return
   int lastRet = -1; // index of last element returned; -1 if no such
   public E next() {
        checkForComodification();
        // 迭代迭代器里边的【游标】，根据逻辑存储数组下标获取元素
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
   }
 * LinkedList实现：
 * public E next() {
        checkForComodification();
        if (!hasNext())
            throw new NoSuchElementException();

        lastReturned = next;
        next = next.next;
        nextIndex++;
        return lastReturned.item;
   }
 * remove方法：删除上次访问的对象，这个【方法必须紧跟在访问一个元素之后执行】。如果上次访问之后，集合已经发生了变化，这个方法将会抛出IllegalStateException。
 * Arraylist删除实现：【存在一个lastRet索引】
 * public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
 * 【iterable先next在调用方法的原因：都是使用上位“游标”做逻辑操作，而不使用当前“游标”】
 * 
 * 
 * 2、集合
 * 除了以Map结尾的类之外，其他类都实现了Collection接口，而以Map结尾的类实现了Map接口。
 * 具体集合：
 * ArrayList       可以动态增长和缩减的【索引序列】。
 * LinkedList      可以在任何位置进行高效地插入和删除操作的有序序列。
 * ArrayDeque      用 循环数组实现的双端队列。
 * HashSet         没有重复元素的无序集合。
 * TreeSet         有序集合。
 * EnumSet         包含枚举类型值得集。
 * LinkedHashSet   可以记住元素插入次序的集。
 * PriorityQueue   允许高效删除最小元素的集合。
 * HashMap         存储键/值关联关系的数据结构。
 * TreeMap		         键值有序排列的映射表。
 * EnumMap		         键值属于枚举类型的映射表。
 * LinkedHashMap   可以记住键/值项添加次序的映射表。
 * WeakHashMap	         其值无用武之地后可以被垃圾回收器回收的映射表。
 * IndentityHashMap用==而不是用equals比较键值的映射表。
 * 1）链表
 * Java设计语言中所有链表实际上都是双向链接（doubly linked）--即每个节点还存放着指向前驱节点的引用。 
 * 从链表中间删除一个元素是很轻松的操作，集需要对被删除元素附近的节点更新一下即可。
 * 2）数组列表
 * List用于描述一个有序集合，并且集合中每个元素的位置十分重要。有两种访问元素的协议：一种是用迭代器；另一种是用get和set方法随机访问每
 * 个元素。后者不适用于链表，但对数组却很有用。ArrayList封装了一个动态再分配的对象数组。
 * 3）散列集
 * 散列表为每个对象计算一个整数，称为散列码（hash code）。散列码是由对象的实例域产生的一个整数，具有不同实例域的对象产生不同的散列码。
 * 散列表可以用于实现几个重要的数据结构。其中最为简单的是set类型。set是没有重复元素的集合。set的dd方法首先在集中查找要添加的对象，如果不存在，就将这个对象添加进去。
 * Hashset只在某个筒中查找元素，而不必查看集合中的所有元素。
 * 4）树集（treeSet）:
 * 树集是一个有序集合（sorted collection）,可以以任意顺序将元素插入到集合中，在对集合进行遍历时，每个值将自动地按照排序后的顺序呈现。
 * 排序是用树结构完成的（红黑树），每次将一个元素添加到树中时，都被放置在正确的排序位置上，因此，迭代器总是已拍好序的顺序访问每个元素。
 * 将一个元素添加到树中要比添加到散列表中慢，但是，与将元素添加到数组或链表的正确位置上相比还是快很多。
 * treeset假定插入的元素实现了Comparable接口，如果要插入自定义的对象，就必须通过实现Comparable接口自定义排序顺序。
 * 【在Object类中，没有提供任何compareTo接口的默认实现】
 * 还可通过将Comparator对象传递给Treeset构造器来告诉树集使用不同的比较方法。
 * 5）队列
 * JavaSE6中引入了Deque接口，并由ArrayDeque和LinkList类实现。这两个类都提供了双端队列，而且在必要时可以增加队列长度。
 * 区别是一个是顺序实现，一个是链式实现。
 * Queue队列------Deque双端队列
 * 6）优先级队列（priority queue）  堆
 * 元素可以按照任意顺序插入，却总是按照排序的顺序进行检索。也就是说，无论和是调用remove方法，总会获得当前优先级队列中最小的元素。
 * 然而，优先级队列并没有对所有的元素进行排序。如果用迭代的方式处理这些元素，并不需要对它们进行排序，优先级队列使用了一个优雅且高效
 * 的数据结构：堆（heap）。堆是一个可以自我调整的二叉树，对树执行添加和删除操作，可以让最小的元素移动到根，而不必话费事件对元素排序。
 * 优先级队列可以保存实现了Comparable接口的类对象，包括在构造器中传入比较器。
 * 应用：任务调度，每个任务有一个优先级，任务以随机顺序添加到队列中，每当启动一个新的任务时，都将优先级高的任务取出。
 * 7）映射表（Map）
 * 集是一个集合，它可以快速地查找现有的元素。但是，要查看一个元素，需要有要查找元素的精确副本。这不是一种非常通用的查找方式。通常，我们
 * 知道某些键的信息，并想要查找与之对应的元素。映射表（map）数据结构就是为此设计的。
 * HashMap：散列映射表对键进行散列
 * TreeMap：树映射表用键的整体顺序对元素进行排序，并将其组织成搜索树。
 * ********************************【散列或比较函数只能作用于键】*********************************
 * 键必须是唯一的，不能在同一个键存放两个值。如果对同一个键两次调用put方法。第二个就会取代第一个值，实际上，put将返回用这个参数
 * 存储的第一个值。
 * 集合框架并没有将映射表本身视为一个集合，然而，可以获得映射表的视图，这是一组实现了Collection接口的对象，或者他的子接口的视图。
 * 三个视图：健集、值集合和键/值对集 
 * 8）专用集和映射表类
 * 弱散列映射集：映射表对象是活动的，其中的所有桶也是活动的，它们不能被回收。因此需要由程序员负责从长期存活的映射表中删除那些无用的值。
 * 或者使用WeakHashMap完成这件事。当键的唯一引用来自散列表条目时，这一数据结构将与垃圾回收器协同工作一起删除键/值对。
 * 协同工作如下：
 * WeakHashMap使用弱引用（weak references）保存键。WeakReference对象将引用保存到另外一个对象中，在这里，就是散列
 * 标键。对于这种类型的对象，垃圾回收器用一种特有的方式进行处理。通常，如果垃圾回收器发现某个特定的对象已经没有他人引用，就将其回收
 * 然而，如果某个对象只能由WeakReference引用，垃圾回收器任然回收它，但要【将引用这个对象得弱引用放入队列中】，WeakhashMap
 * 将周期地检查队列，以便找出新添加的弱引用，一个弱引用进入队列意味着这个键不再被他人使用，并且已经被收集起来。于是，WeakHashMap
 * 将删除对应的条目  
 [关于Java中的WeakReference](https://www.jianshu.com/p/964fbc30151a)
 [Java 的强引用、弱引用、软引用、虚引用](http://www.cnblogs.com/gudi/p/6403953.html)。  
 
 * 链接散列集和链接映射表：LinkedHashSet和LinkedHashMap:详见测试案例
 * 用来记住插入元素项的顺序。这样就可以避免在散列表中的项从表面上看是随机排列的，当条目插入时，就会并入到双向链表中。
 * 枚举集与映射表：EnumSet和EnumMap
 * 是一个枚举类型元素集的高效实现，由于枚举类型只有有限个实例，所以EnumSet内部用位序列实现。如果对应的值在集中，则相应的位置为1.
 * EnumMap是一个键类型为枚举类型的映射表。他可以直接且高效地用一个值数组实现。
 * 标识散列映射表：IdenitityHashMap
 * 这个类中，键的散列值不是用hashCode函数计算的，而是用System.identityHashCode方法计算的。这是Object.hashCode
 * 方法根据对象的内存地址来计算散列码时所使用的的方式。而且，在对两个对象进行比较时IndentityHashMap类使用==，而不是equals。
 * 不同的键对象，即使内容相同，也被视为不同的对象。在实现对象遍历算法（对象序列化）时，这个类非常有用，可以用来跟踪每个对象的遍历状况。
 * 
 * 3、集合和框架
 * List接口在提供随机访问get(index),remove(index),add(index)时，并不管它们对某种特定的实现是否高效。为了避免执行
 * 成本较高的随机访问操作，JavaSE1.4引入了一个【标记接口】RandomAccess，这个接口没有任何方法，但可以检测一个特定的集合是否
 * 支持高效的随机访问。
 * 视图包装器：映射表类的keySet方法看似创建了一个新集，并将映射表中的所有键都填进去，然后返回这个集。但是，情况并非如此。取而代之
 * 的是：keySet方法返回一个实现了Set接口的类对象，这个方法对映射表进行操作，这种集合称为【视图】。
 * 【包装器实现视图模式】
 */
package com.zyx.collection;
