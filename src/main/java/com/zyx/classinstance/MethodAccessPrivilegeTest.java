package com.zyx.classinstance;

/**
 * 方法可以访问所属类的所有对象的私有数据
 */
public class MethodAccessPrivilegeTest {

	public static void main(String[] args) {
		
		Count count = new Count(1);
		Count otherCount = new Count(2);
		int sum = count.getSum(otherCount);
		System.out.println(sum);
	}

}

class Count {
	
	private int count;
	
	public Count(int count){
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * getSum方法不仅可以访问当前对象的count私有属性，还可
	 * 访问otherCount的私有属性count
	 * otherCount.count
	 * */
	public int getSum(Count otherCount){
		int sum;
		sum = count + otherCount.count;
		return sum;
	}
}
