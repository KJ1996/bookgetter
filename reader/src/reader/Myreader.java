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
	//从html中提取纯文本  
    public static String Html2Text(String inputString) {  
        String htmlStr = inputString; // 含html标签的字符串  
        String textStr = "";  
        java.util.regex.Pattern p_script;  
        java.util.regex.Matcher m_script;  
        java.util.regex.Pattern p_style;  
        java.util.regex.Matcher m_style;  
        java.util.regex.Pattern p_html;  
        java.util.regex.Matcher m_html;  
        try {  
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>  
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>  
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
            m_script = p_script.matcher(htmlStr);  
            htmlStr = m_script.replaceAll(""); // 过滤script标签  
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
            m_style = p_style.matcher(htmlStr);  
            htmlStr = m_style.replaceAll(""); // 过滤style标签  
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
            m_html = p_html.matcher(htmlStr);  
            htmlStr = m_html.replaceAll(""); // 过滤html标签  
            textStr = htmlStr;  
        } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }  
        //剔除空格行  
        textStr=textStr.replaceAll("[ ]+", " ");  
        textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");  
        return textStr;// 返回文本字符串  
    } 
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
               chi=Html2Text(temp);
               Pattern p = Pattern.compile("&[a-z][a-z][a-z][a-z];");
               Matcher m2 = p.matcher(chi); 
               chi= m2.replaceAll("");
		       writebook(Html2Text(chi));
		       if(ok==1) {
		    	   getOneHtml(s2);
		       }
		  return temp;
		 }
	public static void main(String[] args) throws Exception {
		System.out.println("输入小说网站第一章url：");
		Scanner str = new Scanner(System.in);
		String s = str.nextLine();
		String s2="";
		String s1="/home/hyj/mybook/";
		System.out.println("是否修改存储地址（y/n)");
		Scanner str3 = new Scanner(System.in);
		String s3 = str3.nextLine();
		if(s3.equals("y")) {
		System.out.println("输入存储地址，默认为：/home/hyj/mybook/");
		Scanner str1 = new Scanner(System.in);
		s1 = str.nextLine();
		path=s1;
		}
		else
			path="/home/hyj/mybook/";
		System.out.println("输入书名，默认为：test");
		Scanner str2 = new Scanner(System.in);
		s2 = str.nextLine();
		if(!s2.equals("")){
			path=path+s2+".txt";
			//System.out.println(path);
	}
		else {
			path="/home/hyj/mybook/test.txt";
		}
         //String s="https://www.xuanshu.com/book/19659/5389878.html";
		System.out.println(path);
	    getOneHtml(s);

	    }
}
