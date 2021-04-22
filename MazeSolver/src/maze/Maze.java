package maze;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.introcs.StdDraw;

public class Maze {
	
	enum CellValue {WALL, OPEN, EXPLORED};
	int width;
	int height;
	Cell[][] maze;
	
	public Maze(int w, int h) {
		
		StdDraw.enableDoubleBuffering();
		width = w;
		height = h;
		
		StdDraw.setCanvasSize(500, 500);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		
		maze = new Cell[height][width];
		for(int i = 0; i < maze.length; i++) {
			for(int r = 0; r < maze[i].length; r++) {
				maze[i][r] = new Cell(i, r);
			}
		}
		
	}
	
	public void draw() {
		StdDraw.clear();
		
		for(int i = 0; i < maze.length; i++ ) {
			for(int r = 0; r < maze[i].length; r++) {
				if(maze[i][r].value == CellValue.WALL) {
					StdDraw.setPenColor(StdDraw.BLACK);
				}
				if(maze[i][r].value == CellValue.OPEN) {
					StdDraw.setPenColor(StdDraw.WHITE);
				}
				if(maze[i][r].value == CellValue.EXPLORED) {
					StdDraw.setPenColor(StdDraw.RED);
				}
				StdDraw.filledSquare(0.5+i, 0.5+r, 0.5);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.square(0.5+i, 0.5+r, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(10);
		
	}
	
	public void generateMaze() {
		StackLinked<Cell> stack = new StackLinked<>();

		Cell current = maze[1][1];
		current.value = CellValue.OPEN;
		stack.push(current);
		
		while(!stack.isEmpty()) {
		draw();
		
			current = stack.pop();
			ArrayList<Cell>neighbors = new ArrayList<Cell>();
			
			//North
			if(current.row+2 < height) {
				Cell north = maze[current.row+2][current.col];
				if(north.value == CellValue.WALL) {
					maze[current.row+1][current.col].value = CellValue.OPEN;
					north.value = CellValue.OPEN;
					neighbors.add(north);
					
				}
			}

			
			//South
			if(current.row-2 > 0) {
				Cell south = maze[current.row-2][ current.col];
				if(south.value == CellValue.WALL) {
					maze[current.row-1][current.col].value = CellValue.OPEN;
					south.value = CellValue.OPEN;
					neighbors.add(south);
					
				}
			}

			
			//East
			if(current.col+2 < width) {
				Cell east = maze[current.row][ current.col+2];
				if(east.value == CellValue.WALL) {
					maze[current.row][current.col+1].value = CellValue.OPEN;
					east.value = CellValue.OPEN;
					neighbors.add(east);
					
				}
			}

			
			//West
			if(current.col-2 > 0) {
				Cell west = maze[current.row][ current.col-2];
				if(west.value == CellValue.WALL) {
					maze[current.row][current.col-1].value = CellValue.OPEN;
					west.value = CellValue.OPEN;
					neighbors.add(west);
					
				}
			}
			Collections.shuffle(neighbors);

			for(Cell c : neighbors) {
				stack.push(c);
			
			}
			StdDraw.show();
			
			
		}
		
		
	}
	
	public void solve() {
		QueueLinked<Cell> queue = new QueueLinked<>();
		
		Cell start = maze[1][1];
		Cell goal = maze[height-2][ width-2];
		Cell current = start; //this will be the starting point of solving the maze
		
		current.value = CellValue.EXPLORED;
		queue.enqueue(current);
		while(!queue.isEmpty()) {
			draw();
			current = queue.dequeue();
		     if(current.row == goal.row && current.col == goal.col) {
		    	 System.out.println("Solved the maze");
		    	 break;
		     }
		     
		     if(current.row+1 < height) {
					Cell north = maze[current.row+1][current.col];
					if(north.value == CellValue.OPEN) {
						north.value = CellValue.EXPLORED;
						queue.enqueue(north);
							
					}
				}

				if(current.row-1 > 0) {
					Cell south = maze[current.row-1][ current.col];
					if(south.value == CellValue.OPEN) {		
						south.value = CellValue.EXPLORED;
						queue.enqueue(south);
								
					}
				}

				if(current.col+1 < width) {
					Cell east = maze[current.row][ current.col+1];
					if(east.value == CellValue.OPEN) {
						east.value = CellValue.EXPLORED;
						queue.enqueue(east);
						
					}
				}

				if(current.col-1 > 0) {
					Cell west = maze[current.row][current.col-1];
					if(west.value == CellValue.OPEN) {
						west.value = CellValue.EXPLORED;
						queue.enqueue(west);
					}
				}
				StdDraw.show();
		  
		}
				
	}
	
	class Cell {
		private CellValue value;
		private int col;
		private int row;
		
		public Cell(int r, int c) {
			value = CellValue.WALL;
			this.col = c;
			this.row = r;
		}
		
		public String toString() {
			return value.toString();
		}
	}

}
