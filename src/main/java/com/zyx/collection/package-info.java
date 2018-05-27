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
 */
package com.zyx.collection;
