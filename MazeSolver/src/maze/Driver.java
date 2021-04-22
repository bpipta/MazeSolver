package maze;

public class Driver {

	public static void main(String[] args) {
		Maze newMaze = new Maze(31, 31);
		newMaze.draw();
		newMaze.generateMaze();
		newMaze.solve();
		


	}

}
