
import java.util.*;
import java.io.*;
import javax.sql.*;
public class Main
{

	public static Scanner in = new Scanner(System.in);
	public static PrintStream out = System.out;
	
	public static int populationN=30;
	
	protected static int[] 
	ma[]=new int[populationN][populationN],//массив расстояний между городами
	mb[]=new int[populationN][populationN],// массив местоположений городов
	mc[]= new int[2*populationN][populationN],//основной массив популяций
	mg[]=new int[2*populationN][populationN],//массив временного хранения популяций
	
	k=new int[2*populationN],//массив качества популяций
	ideal=new int[populationN];//массив с лучшей особью(маршрутом)
	private static void parseWay(int[][] a,int[][] b){
		//превращение координат городов в расстояние между ними.
		int h,g,z,z1=-1,z2=0;
		for(int i=0; i<populationN; i++)
			for(int j=0; j<populationN; j++)
				if (b[i][j]>0){
				z1++;z2=0;
                for(h=0; h<populationN; h++)
				    for(g=0; g<populationN; g++)
					    if (b[h][g]>0){
						  z=Math.abs(i-h)+Math.abs(j-g);
						  a[z1][z2]=z;
						  z2++;
					}}}
				
	private static void orderLiness(int[][] a){
		//выставление порядка следования по городам
		int x=1;
		for(int i=0;i<populationN; i++)
		    for(int j=0;j<populationN; j++)
			if (a[i][j]>0){
				a[i][j]=x; x++;
		}}
	
	private static int[][] initGert(int[][] a){
		//здесь жесть #1. Формирование начальной 
		//популяции маршрутов
		int w=0,r=0,h=0,
		x[]= new int[populationN],
		b[][]=new int[2*populationN][populationN];
		
		for(int i=0;i<populationN; i++)
		for(int j=0;j<populationN; j++)
		if (a[i][j]>0){
		x[w]=a[i][j];w++;}
		w=1;r=1;
		for(int i=0;i<populationN; i++){
		for(int j=0;j<populationN; j++)
			b[i][j]=x[j];
			
		    r=x[w];x[w]=x[w+1];x[w+1]=r;
			w++;
			if (w==populationN-2) w=1;
		}
        for(int i=0;i<populationN; i++){
		x[i]=populationN-i;
		}
		w=1;
		for(int i=populationN;i<2*populationN; i++){
			for(int j=0;j<populationN; j++)
				b[i][j]=x[j];
		    r=x[w];x[w]=x[w+1];x[w+1]=r;
			w++;
			if (w==populationN-2) w=1;
		}
		return b;
	}
	
	private static int obsbr(int[][] a,int[] b,int[][] c,int f,int[] ff){
		//жесть #2. вычисление дальности маршрутов
		for(int i=0; i<populationN; i++)
			for(int j=0; j<populationN-1; j++) {
				b[i]+=a[c[i][j]-1][c[i][j+1]-1];
			}
		for(int i=populationN; i<2*populationN; i++)
			for(int j=0; j<populationN-1; j++) 
			b[i]+=a[c[i-populationN][j]-1][c[i-populationN][j+1]-1];
			
			for(int i=0; i<2*populationN; i++)if (b[i]<f) {f=b[i];
					for(int j=0; j<populationN; j++)
			ff[j]=c[i][j];}
	return f;
			}

	private static int[][] sel(int[][] a,int[][] b,int g){
		//скрещивание двух родителей и получение двух потомков 
		int ol,lo;
		ol=(int)(Math.random()*2*populationN);
		lo=(int)(Math.random()*2*populationN);

		for(int i=0; i<populationN; i++)
		if (a[ol][i]==a[lo][i]){
			b[g][i]=a[ol][i];
			b[g+1][i]=a[lo][i];
		}else {
		b[g][i]=a[lo][i];
		b[g+1][i]=a[ol][i];
	}
		return b;
	}

	private static void mutation(int[] a){
		//мутация случайного маршрута путем переставления
		//двух случайных городов в нем
		int l,k=(int)(Math.random()*populationN-2)+1,
		w=(int)(Math.random()*populationN);
		if ((k<1)&(k>populationN-4)) {}else
		if (k+w>populationN-2){}else
		{
		l=a[k];
		w=k+w;
		a[k]=a[w];a[w]=l;}
	}
	
	private static void injection(int[][]a,int[] b,int[]c){
		int r=0;
		for(int i=0; i<2*populationN; i++)
		if (b[i]>b[r]) r=i;
		for(int i=0; i<populationN; i++)
	    a[r][i]=c[i];
		}

	public static void main(String[] args)
	{//основа.Считывание количества проходов по алгоритму,
	//считывание координат,вызовы функций, и т.д.
		int M,r=0,x=0,y=0;
		//считывание проходов
		M=10000000;
		out.println(M);
		//in.nextInt();
			y=populationN/3;
			x=0;
			mb[populationN/2+3][populationN/2+3]=1;
		for (int i=0;i<populationN/3;i++){
			mb[x][y]=1;
			x++;
			mb[x][y]=1;
			mb[Math.abs(y-populationN/3)][x]=1;
			y--;
		}
      
		
		//много,МНОГО вызовов функций
		parseWay(ma,mb);
		orderLiness(mb);
		out.println();
		mc=initGert(mb);
		//печать итогового массива городов
		for(int i=0; i<populationN; i++){out.println();
			for(int j=0; j<populationN; j++)
				out.print(mb[i][j]+" ");}
		int w=900,s;
		w=obsbr(ma,k,mc,w,ideal);
		r=-1;y=w;
		out.println(w);
		//зачистка массива качеств маршрутов
		for(int j=0; j<populationN; j++) k[j]=0;
		//начало основного алгоритма
		for(int l=0; l<M; l++) 
		{
			//запуск селекции маршрутов
			for(s=0; s<populationN; s++) 
			{
				r=s*2;
				mg=sel(mc,mg,r);
			}
			mc=mg;
			//запуск мутации маршрутов
			for(int i=0;i<populationN/4;i++){
			s=(int)(Math.random()*2*populationN);
			mutation(mc[s]);}
			for(int j=0; j<populationN; j++) k[j]=0;
			w=obsbr(ma,k,mc,w,ideal);
			r=-1;
			if (l % 10==0) {injection(mc,k,ideal);
			if (w==y); else {out.println(w+" "+l);
			y=w;
			}}}
		out.println();
	    out.print("MinWay= "+w+" into ");
		for(int i=0; i<populationN; i++)
		out.print(ideal[i]);
}}
