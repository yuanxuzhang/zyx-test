package com.zyx.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 测试序列化
 * 【序列化用序列号代替了内存地址】
 */
public class ObjectInputOutputTest {

	public static void main(String[] args) {

		Employee secretary = new Employee("秘书");
		Manager managerA = new Manager(1, "管理A");
		Manager managerB = new Manager(2, "管理B");
		managerA.setSecretary(secretary);
		managerB.setSecretary(secretary);
		
		Employee[] staff = {managerA, managerB, secretary};
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("employee.dat"));
		
			out.writeObject(staff);
		} catch (FileNotFoundException e) {
			//NOP
			e.printStackTrace();
		} catch (IOException e) {
			//NOP
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					//NOP
					e.printStackTrace();
				}
			}
		}
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("employee.dat"));
		
			try {
				Employee[] newStaff = (Employee[])in.readObject();
				
				for(Employee e : newStaff){
					System.out.println(e);
				}
				
			} catch (ClassNotFoundException e) {
				//NOP
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			//NOP
			e.printStackTrace();
		} catch (IOException e) {
			//NOP
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					//NOP
					e.printStackTrace();
				}
			}
		}
		
	}

}

class Employee implements Serializable {
	
	private static final long serialVersionUID = -8506678408187213550L;
	
	private String name;
	
	public Employee (String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return String.format("姓名：%s", name);
	}
	
}

class Manager extends Employee {

	private static final long serialVersionUID = 912071963501160465L;
	
	private int level;
	
	private Employee secretary;
	
	public Manager(int level, String name) {
		super(name);
		
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Employee getSecretary() {
		return secretary;
	}

	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}
	
	public String toString(){
		return String.format("姓名：%s, 职级：%s, 秘书:%s", super.getName(), level, secretary.getName());
	}
}
