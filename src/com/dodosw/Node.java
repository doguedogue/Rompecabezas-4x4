package com.dodosw;

/**
 * 
 * @author RVILCHES
 *
 */

public class Node {
	private int n;
	private int node [][];//y, x
	private int f = 100;
	private int h = 100; 
	private int g = 0;
	private boolean isValid = false;
	
	Node(int [][]p_node){
		if (p_node != null) {
			isValid = true;
			n = p_node.length;
			node = p_node;
		}
	}
	
	Node(Node p_node){
		setNode(p_node);
	}
	
	public void setNode(Node p_node){
		if (p_node != null) {
			isValid = true;
			n = p_node.getN();
			node = p_node.getNode();
			f = p_node.getFScore();
			h = p_node.getHScore();
			g = p_node.getG();

		} else {
			isValid = false;
			n = 0;
			f = 100;
			h = 100;
			g = 0;
		}		
	}
	
	public int getG() {
		return g;
	}
	
	public int getN() {
		return n;
	}
	
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setG_score(int g){
		this.g = g;
	}
	
	private void calculateF() {
		f = h + g;
	}
	
	public int[][] getNode(){
		return clone();
	}
	
	private int getManhattan (int y1, int x1, int value, int [][] node_f) {
		x1++;y1++;
		int h = 0;
		for(int y2=0; y2 < n ; y2++) {
			for(int x2=0; x2 < n ; x2++) {
				if (node_f[y2][x2] == value) {
					h = Math.abs(x1 - (x2+1))+ Math.abs(y1-(y2+1));
				}
			}
		}
		return h;
	}
		
	private int calculateH(Node node_final) {
		int [][] node_f = node_final.getNode();
		int h = 0;
		
		
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (node[y][x] != 0 && node[y][x] != node_f[y][x]) {
					//int m = getManhattan(y, x, node[y][x], node_f);
					int m = 1; //Número de diferencias
		
					h+=m;
				}
			}
		}		
		return h;
	}
	
	public void evaluate (int g, Node node_final) {
		this.g = g;
		this.h = calculateH(node_final);
		calculateF();
		//System.out.println("g: "+this.g +" h: "+this.h+" f: "+this.f);
				
	}
	
	public void print () {
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (node[y][x] == 0)
					System.out.print("_\t");
				else 
					System.out.print(node[y][x] + "\t");
			}
			System.out.print("\n");
		}
	}

	static void print (int [][] node2) {
		for(int y=0; y < node2.length ; y++) {
			for(int x=0; x < node2[0].length ; x++) {
				if (node2[y][x] == 0)
					System.out.print("_ ");
				else 
					System.out.print(node2[y][x] + " ");
			}
			System.out.print("\n");
		}
	}

	public int[][] moveUp() {
		int x2, y2;
		int [][]nodeMoving = getNode(); 
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (nodeMoving[y][x] == 0) {
					x2 = x;
					y2 = y-1;
					//System.out.println("x= "+x+" y="+y+" x2= "+x2+" y2="+y2); 
					if (x2 < 0 || y2 < 0|| x2 >= n || y2 >= n)
						return null;
					//System.out.println("-------------- Move Up"); 
					nodeMoving[y][x] = nodeMoving[y2][x2];
					nodeMoving[y2][x2] = 0;
					return nodeMoving;
				}
			}
		}		
		return null;
	}
	public int[][] moveDown() {			
		int x2, y2;
		int [][]nodeMoving = getNode(); 
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (nodeMoving[y][x] == 0) {
					x2 = x;
					y2 = y+1;
					//System.out.println("x= "+x+" y="+y+" x2= "+x2+" y2="+y2); 
					if (x2 < 0 || y2 < 0|| x2 >= n || y2 >= n)
						return null;
					//System.out.println("-------------- Move Down"); 
					nodeMoving[y][x] = nodeMoving[y2][x2];
					nodeMoving[y2][x2] = 0;
					return nodeMoving;
				}
			}
		}		
		return null;
	}
	public int[][] moveLeft() {		
		int x2, y2;
		int [][]nodeMoving = getNode(); 
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (nodeMoving[y][x] == 0) {
					x2 = x-1;
					y2 = y;
					//System.out.println("x= "+x+" y="+y+" x2= "+x2+" y2="+y2); 
					if (x2 < 0 || y2 < 0|| x2 >= n || y2 >= n)
						return null;
					//System.out.println("-------------- Move Left"); 
					nodeMoving[y][x] = nodeMoving[y2][x2];
					nodeMoving[y2][x2] = 0;
					return nodeMoving;
				}
			}
		}		
		return null;
	}

	public int[][] moveRight() {	
		int x2, y2;
		int [][]nodeMoving = getNode();  
		for(int y=0; y < n ; y++) {
			for(int x=0; x < n ; x++) {
				if (nodeMoving[y][x] == 0) {
					x2 = x+1;
					y2 = y;
					//System.out.println("x= "+x+" y="+y+" x2= "+x2+" y2="+y2); 
					if (x2 < 0 || y2 < 0|| x2 >= n || y2 >= n)
						return null;
					//System.out.println("-------------- Move Right"); 	
					nodeMoving[y][x] = nodeMoving[y2][x2];
					nodeMoving[y2][x2] = 0;
					return nodeMoving;
				}
			}
		}		
		return null;
	}
	public int[][] clone(){
		int [][] clone = new int [n][n];
		
		for(int y=0; y < n ; y++)
			for(int x=0; x < n ; x++) 
					clone[y][x] = node[y][x];
		return clone;
	}

	public int getHScore() {
		return h;
	}
	public int getFScore() {
		return f;
	}

}
