package set;

import java.util.Random;
import java.util.Scanner;

public class Maze extends DisjSets{

	Cell [][] cell;
	private int mazeRow;
	private int mazeCol;
	private int totalCells; 
	
	public Maze(int rows, int columns) {
		super(rows*columns);
		
		mazeRow = rows;	//set the rows
		mazeCol = columns;	//set the columns
		totalCells = rows*columns; //set total size of number of cells
		
		initializeCells(mazeRow, mazeCol); //initialize array of cells to print
		createMaze();	//create the maze
		print();	//print the maze
	}
	
	private void initializeCells(int rows, int columns) {
		int count  = 0;
		cell = new Cell[rows][columns];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cell[i][j] = new Cell(count);	//create array of cells
				count++;
			}
		}
		
	}

	private void createMaze(){
		
		int unionCounter = 0; //lets us know when maze is completed at n-1
		Random rand = new Random();
		int unionCell = 0;	//shows cells to be merged
		
		while (unionCounter < totalCells - 1){
			int breakWall = rand.nextInt(2);	//random wall to break down
			if(unionCounter != 0)
				unionCell = rand.nextInt(totalCells  ); //random cell to be chosen
			
			//find the exact location of the cell
			int findr = unionCell/mazeRow;	//find what row the cell is in
			int findc = unionCell%mazeCol;  //find what column the cell is in
			
			//remove the bottom wall if 0, if 1 remove the right wall
			if(breakWall == 1 && findr != mazeRow-1){
				
				if(find(cell[findr][findc].value) != find(cell[findr+1][findc].value)){
					union(find(cell[findr][findc].value), find(cell[findr+1][findc].value)); //union values
					cell[findr][findc].b = false; //remove wall
					unionCounter++;	//union was made
				}
			}
			else if(breakWall == 0 && findc != mazeCol-1){
				
				if(find(cell[findr][findc].value) != find(cell[findr][findc+1].value)){
					union(find(cell[findr][findc].value), find(cell[findr][findc+1].value)); //union values
					cell[findr][findc].r = false; //remove wall
					unionCounter++;	//union was made
				}
				
			}
				
		}
	}

	private void print(){
		
		for(int i = -1; i < mazeRow; i++){
			for(int j = 0; j < mazeCol; j++){
				if(j == 0 && i != -1){
					if(i > 0)
						System.out.print("|");	//print left wall
					else
						System.out.print(" ");
				}
				if(i != -1){
					if(cell[i][j].b)
						System.out.print("__");	//print walls
					else
						System.out.print("  ");

					
					if(cell[i][j].r)
						System.out.print("|");	//print wall
					else
						System.out.print(" ");
				}
				else
					System.out.print(" __");	//print top wall
			}
			System.out.println();
		}	
		
	}
	
	private class Cell{
		protected boolean r;	//top wall
		protected boolean b;	//bottom wall
		int value;
		
		Cell(int digit){
			value = digit;
			if(digit == totalCells-1)
				r = false;	//set up right wall
			else
				r = true;
			b = true;	//set up bottom wall
			
		}
	}
	
	public static void main(String[] arg){
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the dimensions for the maze:");
		System.out.print("Enter rows: ");
		int rows = in.nextInt();
		System.out.print("Enter columns: ");
		int columns = in.nextInt();
		
		Maze m = new Maze(rows, columns);	//create maze
		
		in.close();
	}
}
