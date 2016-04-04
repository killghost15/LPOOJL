package maze.logic;
/**
 * IMazeBuilder.java
 * interface do gerador de labirinto
 *
 */
public interface IMazeBuilder {
	/**
	 * 
	 * @param size do labirinto
	 * @param numD numero de dragões
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public char[][] buildMaze(int size,int numD) throws IllegalArgumentException;
}
























