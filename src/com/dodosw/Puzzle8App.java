package com.dodosw;

/**
 * 
 * @author RVILCHES
 * 
 * Implementación de algoritmo de Ajinkya Sonawane
 * (https://blog.goodaudience.com/solving-8-puzzle-using-a-algorithm-7b509c331288)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Puzzle8App {
	
	public static void main (String []args) {
		//Caso 1 - si funciona
		int[][] node_i = {{1, 2, 3}, {0, 4, 6}, {7, 5, 8}};
		int[][] node_f = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

		//Caso 2 - no funciona
//		int[][] node_i = {{14, 10, 3, 9}, {15, 5, 8, 1}, {2, 7, 6, 12}, {11, 13, 4, 0}};
//		int[][] node_f = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}}; //imposible
		
		//Caso 3 - no funciona
//		int[][] node_f = {{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 0, 15, 6}, {10, 9, 8, 7}};

		//Caso 4 - no funciona
//		int[][] node_i = {{4, 3, 1}, {8, 5, 6}, {7, 2, 0}};
//		int[][] node_f = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

		//Caso 4 - no funciona
//		int[][] node_i = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
//		int[][] node_f = {{2, 8, 1}, {0, 4, 3}, {7, 6, 5}};

		
		int iteraciones = 1000;

		int g;
		Node node_inicial = new Node(node_i);
		Node node_final = new Node(node_f);
		Node moveUp, moveDown, moveLeft, moveRight;
		ArrayList<Node> lista = new ArrayList<Node>();
		 
		System.out.println("----- Posición Inicial ----- ");
		node_inicial.print();
		
		System.out.println("\n----- Posición Final ----- ");
		node_final.print();
		
		
		System.out.println("\n--------------"); 
		node_inicial.evaluate(0, node_final);		
		lista.add(new Node(node_inicial));
		
		for(g = 1; g < iteraciones ; g++) {
			//System.out.println("-------------- g = "+g); 			
			
			//1. Hacer los 4 movimientos
			moveUp = new Node(node_inicial.moveUp());
			if (moveUp.isValid()) {
				moveUp.evaluate(g, node_final);
				//moveUp.print();
			}
			
			moveDown = new Node(node_inicial.moveDown());
			if (moveDown.isValid()) {
				moveDown.evaluate(g, node_final);
				//moveDown.print();
			}

			moveLeft = new Node(node_inicial.moveLeft());
			if (moveLeft.isValid()) {
				moveLeft.evaluate(g, node_final);
				//moveLeft.print();
			}
			
			moveRight = new Node(node_inicial.moveRight());
			if (moveRight.isValid()) {
				moveRight.evaluate(g, node_final);
				//moveRight.print();
			}	
							
			//2. Evaluar si f mas pequeña resetear el escenario
			int[] nums={moveUp.getFScore(), moveDown.getFScore(), moveLeft.getFScore(), moveRight.getFScore()};
			Arrays.sort(nums);
			
			
						
			if (moveUp.getFScore() == nums[0]) {
				node_inicial.setNode(moveUp);				
			} else if (moveDown.getFScore() == nums[0]) {
				node_inicial.setNode(moveDown);
			} else if (moveLeft.getFScore() == nums[0]) {
				node_inicial.setNode(moveLeft);
			} else if (moveRight.getFScore() == nums[0]) {
				node_inicial.setNode(moveRight);
			}
			//System.out.println("Numero menor = "+nums[0]);
			
			//System.out.println("----- Posición Actual ----- ");
			//node_inicial.print();			
			
			//3. agregar a la lista
			lista.add(new Node(node_inicial));			
			
			//4. Evaluar si h es igual a 0	termina
			if (node_inicial.getHScore() == 0) {
				//System.out.println("----- saliendo ");
				break;
			} 								
		}
		
		if (node_inicial.getHScore() != 0)
			System.out.println("Solución no encontrada para "+g+" iteraciones");
		else 
			for (Iterator<Node> iterator = lista.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				System.out.println("Paso: "+node.getG());
				node.print();
				System.out.println("--> \n");
			}
	}
}
