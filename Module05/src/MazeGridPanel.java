/* Personal Notes
• Finish implementing solveMaze() method to perform a depth-first search using a stack
• Research "stack" a bit more
• Lookup depth first search
• If you run into a dead end, backup until you find a new route to explore
	- To-do this we use a stack
		➤ push start position on top of stack
		➤ while maze exploration is not done and stack isn't empty
			➤ peak to get our current position
			➤ if we can go north and haven't visited there yet
				➤ push the location to the north on the stack
				➤ mark the current location as visited
			➤ else if we can go south...
			➤ repeat for east and west
			➤ else
				➤ we can't go anywhere so we are at a dead end
				➤ mark current as a dead end
				➤ pop off the stack
• Work on solveMaze()
• genDFSMaze(): Extra credit
• solvedMazeQueue(): solves the maze using breadth-first search
• visted(): Check if the cell at row or col has been visited by looking at the color
• genNWMaze(): Creates the maze
• Each cell has a boolean for each of the four possible walls
• setBackground:  Used to change the color of a cell
• getBackground(): Used to retrieve the color of a cell
• getDSFMaze(): Extra Credit, builds maze by using depth-first search.
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//homework
	public void solveMaze() {
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		stack.push(start);

		while(!stack.empty()) {
			Cell current = stack.peek();
			if (current.getBackground() == Color.RED) {
				break;
			}
			if (!current.northWall && !visited(current.row-1, current.col)) {
				stack.push(maze[current.row-1][current.col]);
				current.setBackground(Color.BLUE);
			}
			else if (!current.westWall && !visited(current.row, current.col-1)) {
				stack.push(maze[current.row][current.col-1]);
				current.setBackground(Color.BLUE);
			}
			else if (!current.southWall && !visited(current.row+1, current.col)) {
				stack.push(maze[current.row + 1][current.col]);
				current.setBackground(Color.BLUE);
			}
			else if (!current.eastWall && !visited(current.row, current.col+1)) {
				stack.push(maze[current.row][current.col+1]);
				current.setBackground(Color.BLUE);
			}
			else {
				current.setBackground(Color.MAGENTA);
				stack.pop();
			}
		}
		start.setBackground(Color.GREEN);
		finish.setBackground(Color.RED);
	}


	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  ) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}
