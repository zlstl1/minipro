package com.javaex.ex04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException{
		
		List<Person> list = new ArrayList<Person>();
		
		Scanner sc = new Scanner(System.in);
		Reader fr = new FileReader("phoneDB.txt");
		BufferedReader br = new BufferedReader(fr);	
		
		int op=0;
		String line = "";
		
		System.out.println("*******************************");
		System.out.println("*      전화번호 관리 프로그램              *");
		System.out.println("*******************************");		

		while(true) {
			line = br.readLine();
			if(line == null) {
				break;
			}
			String[] personInfo = line.split(",");
			
			Person person = new Person(personInfo[0],personInfo[1],personInfo[2]);
			list.add(person);
			
		}
		
		while(true) {
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("-----------------------------");
			System.out.print(">메뉴번호:");
			op = sc.nextInt();
			if(op==1) {
				System.out.println("<1.리스트>");								
				for(int i=0;i<list.size();i++) {
					System.out.println( (i+1) +"." + list.get(i).name + "," + list.get(i).hp + "," + list.get(i).company);
				}
				System.out.println("\n");
			}else if(op==2) {	
				Writer fw = new FileWriter("phoneDB.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				System.out.println("<2.등록>");
				System.out.print(">이름:");
				String name = sc.next();
				System.out.print(">휴대전화:");
				String hp = sc.next();
				System.out.print(">회사전화:");
				String company = sc.next();
				
				Person person = new Person(name,hp,company);
				list.add(person);
				for(int i=0;i<list.size();i++) {
					bw.write(list.get(i).name + "," + list.get(i).hp + "," + list.get(i).company);
					bw.newLine();
					bw.flush();
				}
				System.out.println("\n[등록되었습니다.]\n");
				bw.close();
			}else if(op==3) {
				Writer fw = new FileWriter("phoneDB.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				System.out.println("<3.삭제>");
				System.out.println(">번호:");
				int num = sc.nextInt();
				list.remove(num-1);
				
				for(int i=0;i<list.size();i++) {
					bw.write(list.get(i).name + "," + list.get(i).hp + "," + list.get(i).company);
					bw.newLine();
					bw.flush();
				}
				
				System.out.println("\n[삭제되었습니다.]\n");
				bw.close();
			}else if(op==4) {
				System.out.println("<4.검색>");	
				System.out.print("검색어>");
				String search = sc.next();
				
				for(int i=0; i<list.size();i++) {
					if(list.get(i).getName().indexOf(search) != -1) {
						System.out.println(list.get(i).getName());
					}
				}					
			}else if(op==5) {
				System.out.println("*******************************");
				System.out.println("*            감사합니다                  *");
				System.out.println("*******************************");
				break;
			}else {
				System.out.println("[다시입력해주세요]\n");
			}

		}
		br.close();		
		sc.close();

		
	}

}
