//change for lab3
package GraphVizTest;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	static gra a;
	int i=0;
	JButton show = new JButton("չʾͼ");
	JButton bridge = new JButton("�ŽӴ�");
	JButton creat = new JButton("���ı�");
	JButton shortest = new JButton("���·��");
	JButton creatrandom=new JButton("��������·��");
	JButton random = new JButton("��ʼ�������");
	JButton stop=new JButton("ֹͣ");
	String randompath=null;
	String randompathspalit[]=new String[50];
    MainFrame(gra b) 
    {
    	a=b;
    	show = new JButton("չʾͼ");
		bridge = new JButton("�ŽӴ�");
		creat = new JButton("���ı�");
		shortest = new JButton("���·��");
		creatrandom=new JButton("��������·��");
		random = new JButton("��ʼ�������");
		stop=new JButton("ֹͣ");
		setLayout(null);
		setVisible(true);
		setSize(1200, 1000);
		show.setBounds(30, 30, 120, 50);
		bridge.setBounds(30, 100, 120, 50);
		creat.setBounds(30, 170, 120, 50);
		shortest.setBounds(30, 240, 120, 50);
		creatrandom.setBounds(30, 310, 120, 50);
		random.setBounds(30, 380, 120, 50);
		stop.setBounds(30, 450, 120, 50);
		show.addActionListener(this);
		bridge.addActionListener(this);
		creat.addActionListener(this);
		shortest.addActionListener(this);
		creatrandom.addActionListener(this);
		random.addActionListener(this);
		stop.addActionListener(this);
		add(show);
	    add(bridge);
	    add(creat);
	    add(shortest);
	    add(creatrandom);
	    add(random);
	    add(stop);
	    setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
	}
    public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if (source.equals(show)) 
		{
			a.showDirectedGraph(a);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ImageIcon image = new ImageIcon("D:\\graph\\gif.gif");
			JLabel label = new JLabel(image);
			JScrollPane pane=new JScrollPane(label);
			pane.setBounds(160,10,1000, 900);
		    pane.setVisible(true);
			add(pane);
			revalidate();
		    repaint();
		}
		else if (source==bridge) 
		{
			String words=JOptionPane.showInputDialog("���������ѯ���ŽӴ�"); 
			String[] two = new String[2];
			String ans;
			String[] answer = new String[50];
			two = words.toLowerCase().split("[^a-z]{1,}");
			ans = gra.queryBridgeWords(a, two[0], two[1]);
			String output = null;
			if (ans == null) {
				output = ("No bridge words from " + "\"" + two[0] + "\"" + " to " + "\"" + two[1] + "\"" + "!");
			} 
			else 
			{
				answer = ans.toLowerCase().split("[^a-z]{1,}");
				if (answer.length == 0) {
					output = ("No " + "\"" + two[0] + "\"" + " or " + "\"" + two[1] + "\"" + " in the graph!");
				} else if (answer.length == 1) {
					output = ("The bridge words from " + "\"" + two[0] + "\"" + " to " + "\"" + two[1] + "\"" + " is: "
							+ answer[0] + ".");
				} else {
					StringBuffer buffer = new StringBuffer();
					int j = answer.length;
					int h;
					buffer.append(
							"The bridge words from " + "\"" + two[0] + "\"" + " to " + "\"" + two[1] + "\"" + " are: ");
					for (h = 0; h < j - 1; h++) {
						buffer.append(answer[h] + ",");
					}
					buffer.append("and " + answer[j - 1] + ".");
					output = buffer.toString();
				}
			}
			new output(output);
		}
		else if(source.equals(creat))
		{
			 String text=JOptionPane.showInputDialog("����������ɵ��ı�");
		     String ans=gra.generateNewText(a,text);
             new output(ans);
		}
		else if(source.equals(shortest))
		{
			 String word=JOptionPane.showInputDialog("�������ѯ���·���ĵ���");
			 String[] wordsplit=word.split("[^a-z]{1,}");
			 int len=wordsplit.length;
			 String answ=null;
			 if(len==1)
			 {
			    answ=gra.calcShortestPath(a,wordsplit[0]);
			    new output(answ);
			 }
			 else
			 {
				 answ=gra.calcShortestPath(a,wordsplit[0],wordsplit[1]);
				 new output(answ);
			 }
		}
		else if(source.equals(creatrandom))
		{
			try {
				randompath = gra.randomWalk(a);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			randompathspalit=randompath.split("[^a-z]{1,}");
		}
		else if(source.equals(random))
		{
			if (i < randompathspalit.length-1) {
				System.out.print(randompathspalit[i] + " ");
				i++;
			}
			else if(i==randompathspalit.length-1)
			{
				System.out.print(randompathspalit[i] + "   "+"������߽���\n"); // NOPMD by Veronique on 10/18/17 7:59 PM
				i=0;
			}
	    }
		else if(source.equals(stop))
		{
		   System.out.print("  "+"������߽���\n"); // NOPMD by Veronique on 10/18/17 7:59 PM
		   i=0;
		   randompath=null;
		}
	}
	public static void main(String[] args) 
	{
	   String pathandname=null;
 	   boolean i=true;
 	   String filename=new String(); // NOPMD by Veronique on 10/18/17 7:59 PM
 	   File  file=null;
 	   do{
 		    i=true;
            pathandname=JOptionPane.showInputDialog("�������ļ�·��������"); 
			filename = pathandname;
			file = new File(pathandname);
			if (!file.exists()) {
				i = false;
			} else {
				a = createDirectedGraph(filename);
			}
 	   }while(i==false);
       new MainFrame(a);
	}
	
	public static gra createDirectedGraph(String filename)
	{
		gra a=new gra();
		String str=null;
		String source1=null;
		File file=new File(filename);
		StringBuffer strbu=new StringBuffer("");
		FileInputStream in=null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader buff=new BufferedReader(new InputStreamReader(in));
		try {
			while((str=buff.readLine())!= null)
			{
				strbu.append(str);
				strbu.append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		source1=new String(strbu.toString().toLowerCase());
	    String[] source = source1.split("[^a-z]{1,}");
	    int count=source.length;
	    int i;
	    int j;
	    a.pointnum=0;
	    for(i=0;i<count;i++)
	    {
	    	for(j=0;j<i;j++)
	    	{
	    		if(source[i].equals(source[j]))
	    		{
	    			break;
	    		}
	    	}
	    	if(i==j)
	    	{
	    		a.pointnum++;
	    		a.point[a.pointnum-1]=new String(source[i]);
	    	}
	    }
	    int cou=a.pointnum;
	    int m;
	    int n;
	    for(i=0;i<count-1;i++)
	    {
	    	m=-1;
	    	n=-1;
	    	for(j=0;j<cou;j++)
	    	{
	    		if(source[i].equals(a.point[j]))
	    		{
	    			m=j;
	    		}
	    		if(source[i+1].equals(a.point[j]))
	    		{
	    			n=j;
	    		}
	    	}
	    	if(m!=-1&&n!=-1)
	    	{
	    		a.side[m][n]+=1;
	    	}
	    }
	    return a;
	}
}
