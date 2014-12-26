package classes;
import mainp.*;

public class namecity
{
	public static int pop= Main.populationN;
	protected static int[] 
	mb[]=new int[pop/2][pop];// массив местоположений городов
	private static int[]
	ma[]=new int[pop][pop],//массив расстояний между городами

	k=new int[2*pop];//массив качества популяций
	
	public static int getcity(int x,int y){
		if (((x>pop/2) | (y>pop)) | ((x<0) | (y<0))) {System.out.println("Out of range city");
		    return 0;} else
		    return mb[x][y];
	}
	
	public static int getway(int x,int y){
		if (((x>pop) | (y>pop)) | ((x<0) | (y<0))) {System.out.println("Out of range way"+x+"  "+y);
			return 0;} else
			return ma[x][y];
	}
	
	public static int getsort(int x){
		if ((x>2*pop) | (x<0)) {System.out.println("Out of range quality"+x);
			return 0;} else
			return k[x];
	}
	
	public static void setcity(int x,int y,int set){
		if (((x>pop/2) | (y>pop)) | ((x<0) | (y<0))) System.out.println("Out of range");
			 else
			mb[x][y]=set;
	}
	public static void setway(int x,int y,int set){
		if (((x>pop) | (y>pop)) | ((x<0) | (y<0))) System.out.println("Out of range");
		else
			ma[x][y]=set;
	}
	
	public static void setsort(int x,int y){
		if ((x>2*pop) | (x<0)) System.out.println("Out of range");
			else
			k[x]=y;
	}
} 
