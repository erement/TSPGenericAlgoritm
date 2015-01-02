package mainp;

import java.util.*;
import java.io.*;
import classes.*;

public class Main
{

	public static Scanner in = new Scanner(System.in);
	public static PrintStream out = System.out;
	
	public static final int populationN=40;
	protected static namecity mop= new namecity();
	
	private static int[]
	mc[]= new int[2*populationN][populationN],//�������� ������ ���������
	mg[]=new int[2*populationN][populationN],//������ ���������� �������� ���������
	
	ideal=new int[populationN];//������ � ������ ������(���������)
	private static void parseWay(){
		//����������� ��������� ������� � ���������� ����� ����.
		int h,g,z,z1=-1,z2=0;
		for(int i=0; i<populationN/2; i++)
			for(int j=0; j<populationN; j++)
				if (mop.getcity(i,j)>0){
				z1++;z2=0;
                for(h=0; h<populationN/2; h++)
				    for(g=0; g<populationN; g++)
					    if (mop.getcity(h,g)>0){
						  z=Math.abs(i-h)+Math.abs(j-g);
						  mop.setway(z1,z2,z);
						  if(z2<populationN-1) 
						  z2++;
					}}}
				
	private static void orderLiness(){
		//����������� ������� ���������� �� �������
		int x=1;
		for(int i=0;i<populationN/2; i++)
		    for(int j=0;j<populationN; j++)
			if (mop.getcity(i,j)>0){
				mop.setcity(i,j,x); x++;
		}}
	
	private static int[][] initGert(){
		//����� ����� #1. ������������ ��������� 
		//��������� ���������
		int w=0,r=0,h=0,
		x[]= new int[populationN],
		b[][]=new int[2*populationN][populationN];
		
		for(int i=0;i<populationN/2; i++)
		for(int j=0;j<populationN; j++)
				if (mop.getcity(i,j)>0){
					x[w]=mop.getcity(i,j);w++;}
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
	
	private static int obzor(int[][] c,int f,int[] ff){
		//����� #2. ���������� ��������� ���������
		int h=0;
		for(int i=0; i<populationN; i++)
			for(int j=0; j<populationN-1; j++) {h+=mop.getway(c[i][j]-1,c[i][j+1]-1);
				mop.setsort(i,h);
			}
			h=0;
		for(int i=populationN; i<2*populationN; i++)
			for(int j=0; j<populationN-1; j++) {h+=mop.getway(c[i-populationN][j]-1,c[i-populationN][j+1]-1);
				mop.setsort(i,h);
			}
			
			for(int i=0; i<2*populationN; i++)if ((mop.getsort(i)>0)&(mop.getsort(i)<f)) {f=mop.getsort(i);
					for(int j=0; j<populationN; j++)
			ff[j]=c[i][j];}
	return f;
			}

	private static int[][] sel(int[][] a,int[][] b,int g){
		//����������� ���� ��������� � ��������� ���� �������� 
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
		//������� ���������� �������� ����� �������������
		//���� ��������� ������� � ���
		int l,k=(int)(Math.random()*populationN-2)+1,
		w=(int)(Math.random()*populationN);
		if ((k<1)&(k>populationN-4)) {}else
		if (k+w>populationN-2){}else
		{
		l=a[k];
		w=k+w;
		a[k]=a[w];a[w]=l;}
	}
	
	private static void injection(int[][]a,int[]c){
		int r=0;
		for(int i=0; i<2*populationN; i++)
		if (mop.getsort(i)>mop.getsort(r)) r=i;
		for(int i=0; i<populationN; i++)
	    a[r][i]=c[i];
		}

	public static void main(String[] args)
	{//������.���������� ���������� �������� �� ���������,
	//���������� ���������,������ �������, � �.�.
		int M,r=0,x=0,y=0;
		//���������� ��������
		M=in.nextInt();
		
		for ( x=0;x<populationN;x++)
			for (y=0;y<5;y++){if (r>=populationN) break;
				mop.setcity(x,y,1);
			r++;
			}r=0;
		//�����,����� ������� �������
		parseWay();
		orderLiness();
		out.println();
		mc=initGert();
		//������ ��������� ������� �������
		for(int i=0; i<populationN/2; i++){out.println();
			for(int j=0; j<populationN; j++)
				out.print(mop.getcity(i,j)+" ");}
		int w=Integer.MAX_VALUE,s;
		w=obzor(mc,w,ideal);
		r=-1;y=w;
		out.println(w);
		//�������� ������� ������� ���������
		for(int j=0; j<2*populationN; j++) mop.setsort(j,0);
		//������ ��������� ���������
		for(int l=0; l<M; l++) 
		{
			//������ �������� ���������
			for(s=0; s<populationN; s++) 
			{
				r=s*2;
				if (r<2*populationN)
				mg=sel(mc,mg,r);
			}
			mc=mg;
			//������ ������� ���������
			for(int i=0;i<populationN/4;i++){
			s=(int)(Math.random()*2*populationN);
			mutation(mc[s]);}
			for(int j=0; j<2*populationN; j++) mop.setsort(j,0);
			w=obzor(mc,w,ideal);
			r=-1;
			if (l % 9==0) {injection(mc,ideal);
			if (w==y); else {out.println(w+" "+l);
			y=w;
			}}}
		out.println();
	    out.print("MinWay= "+w+" into ");
		for(int i=0; i<populationN; i++)
		out.print(ideal[i]);
}}