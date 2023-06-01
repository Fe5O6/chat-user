package com.h.uils.common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class StartJFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JFrame jframe= new JFrame("窗体生成");
	JPanel jpanel=null;
	JPanel imagePanel = null;
	BufferedImage image= null;
	JLabel label3 = new JLabel(); 
	ImageIcon background = new ImageIcon();
	JTextArea jtext = new JTextArea();
    JButton jbutton1=new JButton("开始"); 
    JButton jbutton2=new JButton("暂停");
    JButton jbutton3=new JButton("确定");
    String strPath = "D:\\workspace_bk\\abc.txt";
    public static boolean flag = true;//判断开始按钮是否被点过
    private static Thread t;
    private int count = 0;
    
    public StartJFrame(){  
    	//添加背景图片
    	try {
			image=ImageIO.read(new File("E:\\tmp\\heima.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	background = new ImageIcon(image);
    	//把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		//把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0,0,450,400);
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明       
        imagePanel = (JPanel)getContentPane();
        imagePanel.setOpaque(false);
        //把背景图片添加到分层窗格的最底层作为背景       
        getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		
        //添加文字
        jpanel = (JPanel)this.getContentPane();//每次添加必须要加的语句
		JLabel label2 = new JLabel("请输入TXT文本路径：");
		Font font = new Font("",Font.BOLD,30);
		label2.setFont(font);
		label2.setForeground(Color.yellow);
		label2.setBounds(20,50,450,100);
		jpanel.add(label2);
        
        //添加按钮
        jpanel=(JPanel)this.getContentPane();  
        jpanel.setLayout(null);
        //（左，上，宽，高）
        jbutton3.setBounds(new Rectangle(330,180,60,20));
        jbutton3.addActionListener(new TextValue(this));
        jpanel.add(jbutton3);
        
        //添加文本框（左，上，宽，高）
        jtext.setBounds(40, 180, 260, 20);
        jpanel.add(jtext);
        
    }
    /**
     * 重写构造器
     */
    public StartJFrame(String str){  
    	//将路径传入开始按钮
    	strPath = str;
        
    	//添加背景图片
    	try {
			image=ImageIO.read(new File("E:\\tmp\\heima.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	background = new ImageIcon(image);
    	//把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		//把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0,0,450,400);
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明       
        imagePanel = (JPanel)getContentPane();
        imagePanel.setOpaque(false);
        //把背景图片添加到分层窗格的最底层作为背景       
        getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        
        //添加提示文字
        jpanel = (JPanel)this.getContentPane();//每次添加必须要加的语句
		JLabel label2 = new JLabel("点名开始啦");
		Font font = new Font("",Font.BOLD,20);
		label2.setFont(font);
		label2.setForeground(Color.yellow);
		label2.setBounds(100,20,450,100);
		jpanel.add(label2);
		
		//显示名字信息
		label3.setBounds(150,120,450,100);
	    //设置字体颜色
		label3.setForeground(Color.yellow);
        
        //添加按钮
        jpanel=(JPanel)this.getContentPane();  
        jpanel.setLayout(null);   
        jbutton1.setBounds(new Rectangle(100,300,75,25));  
        jpanel.add(jbutton1);  
        jbutton1.addActionListener(new Action(this));
        jbutton2.setBounds(new Rectangle(250,300,75,25));  
        jpanel.add(jbutton2);  
        jbutton2.addActionListener(new Stop(this));
        
    }
    
	/**
	 * 从控制台输入路径
	 */
	public static String InputPath(){
		String str ="";
		System.out.println("请输入TXT文本路径：");
		Scanner sc= new Scanner(System.in);
		str = sc.nextLine();
		return str;
	}
	/**
	 * 读取文档数据
	 * @param filePath
	 * @return
	 */
	public static String ReadFile(String filePath){
		String str = "";
		try {
		String encoding="UTF-8";
		File file = new File(filePath);
		if(file.isFile()&&file.exists()){
				InputStreamReader reader = 
					new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String lineTxt = "";
					while((lineTxt = bufferedReader.readLine()) != null){
						str+=lineTxt+";\n";
					}
                 reader.close();
			}else{
	            System.out.println("找不到指定的文件");
	        } 
		}catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		return str;
	}
	/**
	 * 将字符串转换为String数组
	 */
	public static String[] ChangeType(String str){
		ArrayList<String> list=new ArrayList<String>();
		String[] string = str.split(";");
		return string;
	}
	/**
	 * main方法
	 * @param args
	 */
	public static void main(String args[]){
		StartJFrame jframe=new StartJFrame();
		jframe.setTitle("黑马程序员点名器");
		jframe.setSize(450,400);  
	    jframe.setVisible(true);  
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setResizable(false);
	    jframe.setLocationRelativeTo(null);
		System.out.println();
	}
	/**
     * 点击确定按钮后的方法
     */
    public void chooseValue(ActionEvent e){
    	String str = "";
    	str = jtext.getText();
    	if(str != "" || str != null){
    	StartJFrame jframe = new StartJFrame(str);
    	jframe.setTitle("黑马程序员点名器");
		jframe.setSize(450,400);  
	    jframe.setVisible(true);  
	    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setResizable(false);
	    jframe.setLocationRelativeTo(null);
		System.out.println(str);
    	}
    }
    /**
     * 点击开始按钮后的方法
     */
    public void actionRun(ActionEvent e){
    	if(flag){
		//线程开始
    	t = new Thread(new Runnable(){
    		public void run(){//
                while(count<=10000){
                	//文件路径
                	String strTest = strPath;
                	//开始读取数据
                    String strRead = ReadFile(strTest);
                	//将读取到的数据变为数组
                	String[] strc = ChangeType(strRead);
                	//获取随机的姓名
                	Random random = new Random();
                	int a = 0;
                	a = random.nextInt(strc.length-1);
                	String str = strc[a];
                	System.out.println("输出名字为："+str);
                	label3.setFont(new Font(str,1,60));
                	//设置名字标签的文字
            	    label3.setText(str);
                    try{
                        t.sleep(20);//使线程休眠50毫秒
                    }catch(Exception e){
                    e.printStackTrace();
                    }
                    count+=1;//显示次数
                }
            }
    	});
    	t.start();
    	//设置字体颜色
		jpanel.add(label3);
		flag = false;
    	}
    	flag = false;
    }
    /**
     * 点击暂停按钮后的方法
     */
    public void stopRun(ActionEvent e){
    	if(!flag){
    	t.stop();
    	flag = true;
    	}
    	flag = true;
    }
}

/**
 *确定按键监控类
 */
class TextValue implements ActionListener {  
    private StartJFrame startJFrame;  
    TextValue(StartJFrame startJFrame) {  
        this.startJFrame = startJFrame;  
   }
    public void actionPerformed(ActionEvent e) {  
    	startJFrame.chooseValue(e); 
    	startJFrame.setVisible(false);
    }
}

/**
 *开始按键监控类
 */
class Action implements ActionListener {  
    private StartJFrame jFrameIng;  
    Action(StartJFrame jFrameIng) {  
        this.jFrameIng = jFrameIng;  
   }  
	public void actionPerformed(ActionEvent e) {
		jFrameIng.actionRun(e);
		
	}
}

/**
 *暂停按键监控类
 */
class Stop implements ActionListener {  
    private StartJFrame jFrameIng;  
    Stop(StartJFrame jFrameIng) {  
        this.jFrameIng = jFrameIng;  
   }  
	public void actionPerformed(ActionEvent e) {
		jFrameIng.stopRun(e);
    }  
}