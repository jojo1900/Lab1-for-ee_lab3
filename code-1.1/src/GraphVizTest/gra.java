//change for lab3

package GraphVizTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class gra {
    int pointnum;
    String[] point=new String[60];
    int side[][]=new int[60][60];
    void showDirectedGraph(gra a)
    {
    	GraphViz gviz=new GraphViz("D:\\graph","C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
    	gviz.start_graph();
    	int i,j;
    	for(i=0;i<a.pointnum;i++)
    	{
    		for(j=0;j<a.pointnum;j++)
    		{
    			if(a.side[i][j]!=0&&a.side[i][j]!=Integer.MAX_VALUE)
    			{
    				gviz.addln(a.point[i]+"->"+a.point[j]+" [label=\""+a.side[i][j]+"\""+",fontcolor=red,color=blue,fontsize=20]"+";");
    			}
    		}
    	}
    	gviz.end_graph();
    	gviz.run();
    	/*JFrame frame=new JFrame();
    	ImageIcon image=new ImageIcon("D:\\graph\\gif.gif");
    	JLabel label=new JLabel(image);
    	frame.setLocation(800,0);;
    	frame.add(label);
    	frame.setVisible(true);
    	frame.setSize(800, 800);
    	frame.setDefaultCloseOperation(2);*/
    }
    
    static String queryBridgeWords(gra a, String word1, String word2)
    {
    	int i,m = 0,n = 0;
    	boolean findthefirst=false,findthesecond=false;
    	String output=null;
    	String bridgeword[]=new String[50];
    	for(i=0;i<a.pointnum;i++)
    	{
    		if(word1.equals(a.point[i]))
    		{
    			findthefirst=true;
    			m=i;
    		}
    		if(word2.equals(a.point[i]))
    		{
    			findthesecond=true;
    			n=i;
    		}
    		if(findthefirst==true&&findthesecond==true)
    		{
    			break;
    		}
    	}
    	if(findthefirst==false||findthesecond==false)
    	{
    	     output="-1";
    	     return output;
    	}
    	int position=0;
    	for(i=0;i<a.pointnum;i++)
    	{
    		if(a.side[m][i]!=0)
    		{
    			if(a.side[i][n]!=0)
    			{
    				bridgeword[position]=new String(a.point[i]);
    				position++;
    			}
    		}
    	}
    	if(position<=0)
    	{
    		output=null;
    		return output;
    	}
    	else{
    		StringBuffer out=new StringBuffer("");
    		if(position==1)
    		{
    			out.append(bridgeword[0]);
    			output=out.toString();
    		}
    		else
    		{
				for (i = 0; i < position; i++) {
					out.append(bridgeword[i]);
					out.append(" ");
				}
				output=out.toString();
			}
    		return output;
    	}
    }
    static String generateNewText(gra a, String inputText)
    {
    	String[] inputtext =inputText.toLowerCase().split("[^a-z]{1,}");
    	int length=inputtext.length;
		String bridgeWords=null;
		String[] bridgeword=new String[50];
		StringBuffer output=new StringBuffer(""); 
    	for(int i=0;i<length-1;i++)
    	{
    		bridgeWords=queryBridgeWords(a, inputtext[i],inputtext[i+1]);
    		if((bridgeWords!=null)&&!bridgeWords.equals("-1"))
    		{
    			bridgeword=bridgeWords.toLowerCase().split("[^a-z]{1,}");
        		Random r=new Random();
        		int m=r.nextInt(bridgeword.length);
    			output.append(inputtext[i]+" " +bridgeword[m]+" ");
    		}
    		else
    		{
    			output.append(inputtext[i]+" ");
    		}
    	}
    	output.append(inputtext[length-1]);
    	return output.toString();
    }
    
	static String calcShortestPath(gra g, String word1, String word2)
    {
        final int INF=Integer.MAX_VALUE;
    	String[][]  path = new String[60][60];
    	StringBuffer result=new StringBuffer();
    	String noPath;
    	int[][] dist = new int[60][60];
    	int n=g.pointnum;
    	int word1Index = 0;
    	int word2Index = 0;
    	//初始化邻接矩阵；
    	for(int i=0;i<n;i++){
    		for(int j=0;j<n;j++){
    			if(g.side[i][j]==0)
    			{
    				dist[i][j]=INF;
    			}
    			else{
        			dist[i][j]=g.side[i][j];
    			}
    			path[i][j]=g.point[j];
    		}
    	}
    	for(int k=0;k<n;k++){
    		for(int i=0;i<n;i++){
    			for(int j=0;j<n;j++)
    			{
    				 int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
    				 if(dist[i][j]>tmp){
    					 dist[i][j]=tmp;
    					 path[i][j]=path[i][k];
    				 }
    			}
    		}
    	
    }
    	result.append("输出" + word1 + "到" + word2 + "的最短路径：");
    	result.append(word1);
    	for(int i=0;i<n;i++){
    		if(g.point[i].equals(word1)){
    			word1Index=i;
    			break;
    		}
    	}
    	for(int i=0;i<n;i++){
    		if(g.point[i].equals(word2)){
        			word2Index=i;
        			break;
        		}	
    	}
    	
		int k = dist[word1Index][word2Index];
        String kk=path[word1Index][word2Index];
        if (k == INF) {
            noPath="没有"+word1+"到"+word2+"的最短路径\n";
            return noPath;
        } 
        else {
        result.append(" " + kk);
        }
        int kkIndex=0;
        while (!(kk.equals(g.point[word2Index]))) {
        	
        	for(int i=0;i<n;i++){
        		if(g.point[i].equals(kk)){
        			kkIndex=i;
            		break;
            	}	
        	}
            kk = path[kkIndex][word2Index];
            result.append(" " + kk);
            }
        result.append('\n');
        return result.toString();
    }
	
	static String calcShortestPath(gra g,String word){
		int n=g.pointnum;
		StringBuffer thepathwordtoothers=new StringBuffer();
		for(int i=0;i<n;i++){
			if(!(g.point[i].equals(word))){
				String onepath=null;
				onepath=gra.calcShortestPath(g, word, g.point[i]);
				thepathwordtoothers.append(onepath);
			}
		}
		return thepathwordtoothers.toString();
	}
	
    static String randomWalk(gra a) throws IOException
    {
    	int i;
    	int num=a.pointnum;
    	Random m=new Random();
    	int n=m.nextInt(a.pointnum);
    	int[] outpoint=new int[a.pointnum];
    	StringBuffer  randompath=new StringBuffer("");
    	boolean[][] matrix=new boolean[a.pointnum][a.pointnum];
    	while(n<num)
    	{
    		randompath.append(a.point[n]+" ");
    		int pointoutnum=0;
    		for(i=0;i<a.pointnum;i++)
    		{
    			if(a.side[n][i]!=0)
    			{
    				outpoint[pointoutnum]=i;
    				pointoutnum++;
    			}
    		}
    		if(pointoutnum==0)
    		{
    			return randompath.toString();
    		}
    		Random x=new Random();
        	int y=x.nextInt(pointoutnum);
        	if(matrix[n][outpoint[y]]==true)
        	{
        		randompath.append(a.point[outpoint[y]]+" ");
        		return randompath.toString();
        	}
        	else
            {
        		matrix[n][outpoint[y]]=true;
        	    n=outpoint[y];
            }
    	}
    	return randompath.toString();
    }
}
