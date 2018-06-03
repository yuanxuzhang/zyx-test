package com.zyx.collection;

import java.util.BitSet;

/**
 * Java平台的BitSet类用于存放一个位序列，如果需要高效的存储位序列（例如，标志）就可使用位集。由于位集包装在字节里，所以，使用位集要
 * 比使用Boolean对象的ArrayList更加高效
 * 
 * BitSet内部采用了long[]数组作为存储，分配的位数为n*64，采用【位运算】的设置和取位
 */
public class BitSetTest {

	public static void main(String[] args) {

		/**
		 * 判断素数的最小临界：一个质数的两个因数，至少有其中一个小于等于根号n。
		 * 
		 * 遍历一个拥有200万个位的位集，首先将所有的位置为“开”状态，然后，将已知素数的倍数所对应的的位都置为“关”状态。
		 * 经过这个操作保留下来的位所对应的就是素数了 
		 */
		int n = 2000000;
		long start = System.currentTimeMillis();
		BitSet b = new BitSet(n + 1);
		int count = 0;
		int i;
		for(i = 2; i <= n; i++){
			b.set(i);
		}
		
		/*
		 * 寻找2---n的开平方值得所有倍数的值，直到倍数的值达到n，这样就找到了n内的所有可由两个数相乘得到得数（非素数）。 
		 * 
		 * 将会查找最小临界区间内的所有数的累加倍数（+1阶），直到倍数至n为止
		 * */
		i = 2;
		while(i * i <= n){
			if(b.get(i)){
				count++;
				/*
				 * 初始化为一倍。
				 * */
				int k = 2*i;
				while(k <= n){
					b.clear(k);
					/*
					 * 每次增加一倍
					 */
					k += i;
				}
			}
			i++;
		}
		
		while(i <= n){
			if(b.get(i)) count++;
			i++;
		}
		
		long end = System.currentTimeMillis();
		System.out.println(count + "primes");
		System.out.println((end - start) + "millisecond");
		
	}

}
