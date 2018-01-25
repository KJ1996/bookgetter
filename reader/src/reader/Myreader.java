package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;


public class Myreader {
	public static int ok=0;
	public static int num=0;
	public static String path="";
	 public static void writebook(String str)
	    {
	        try
	        {
	     
	        File file=new File(path);
	        if(!file.exists())
	            file.createNewFile();
	        FileOutputStream out=new FileOutputStream(file,true); //如果追加方式用true        
	        StringBuffer sb=new StringBuffer();
	        sb.append("\n"+str+"\n");
	        out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
	        out.close();
	        System.out.println("ok");
	        }
	        catch(IOException ex)
	        {
	            System.out.println(ex.getStackTrace());
	        }
	    } 
	public static String getOneHtml(String urlString)throws Exception{
		  InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
		        // read contents into string buffer
		        StringBuilder input = new StringBuilder();
		        int ch;
		        while ((ch = in.read()) != -1) input.append((char) ch);
		        ok=0;
		       String chi="";
		       String temp=input.toString();
		       String pattern="next_page\\s+=\\s\"\\d+.html";
		       Pattern r = Pattern.compile(pattern);
		       Matcher m = r.matcher(temp);
		       String s2="";
		       if (m.find( )) {
		       String s1=m.group(0).substring(13);
		       num++;
		       s2=urlString.substring(0, urlString.length()-s1.length());
		       s2=s2+s1;
		       System.out.println("第"+num+"章下载完成"+"\n");
		       ok=1;
		       }
		       for(char s:temp.toCharArray()) {
		    	   if (Character.getType(s) == Character.OTHER_LETTER||s=='。'||s=='，') {
		             chi+=s;
		             
		           }
		       }
		       writebook(chi);
		       if(ok==1) {
		    	   getOneHtml(s2);
		       }
		  return temp;
		 }
	public static void main(String[] args) throws Exception {
		System.out.println("输入小说网站第一章url：");
		Scanner str = new Scanner(System.in);
		String s = str.nextLine();
		path="/home/hyj/mybook/test.txt";
		System.out.println("输入存储地址，默认为：/home/hyj/mybook/test.txt");
		Scanner str1 = new Scanner(System.in);
		String s1 = str.nextLine();
		path=s1;
         //String s="https://www.xuanshu.com/book/19659/5389878.html";
	    getOneHtml(s);

	    }
}
