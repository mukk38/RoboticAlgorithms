package denemeler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Mapping.Nokta;

public class Dfs {
	private List<Nokta> path;
	private static int[][] DIRECTIONS 
	  = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	
	public List<Nokta> solve(Labirent maze,int startH_,int startW_) {
	  path   = new ArrayList<>();
	    if (
	      explore(
	        maze, 
	        startH_,
	        startW_,
	        path
	      )
	      ) {
	        return path;
	    }
	    return Collections.emptyList();
	}
	private Nokta getNextCoordinate(
			  int row, int col, int i, int j) {
			    return new Nokta(row + i, col + j);
			}
	private boolean explore(
			  Labirent maze, int row, int col, List<Nokta> path) {
			    if (
			      !maze.isValidLocation(row, col) 
			      || maze.isWall(row, col) 
			      || maze.isExplored(row, col)
			    ) {
			        return false;
			    }
		//	 System.out.println("Eklenen Path "+row+" "+col);
			    path.add(new Nokta(row, col));
			    maze.setVisited(row, col);
			 
			    if (maze.isExit(row, col)) {
			        return true;
			    }
			 
			    for (int[] direction : DIRECTIONS) {
			    	Nokta coordinate = getNextCoordinate(
			          row, col, direction[0], direction[1]);
			        if (
			          explore(
			            maze, 
			            coordinate.getX(), 
			            coordinate.getY(), 
			            path
			          )
			        ) {
			            return true;
			        }
			    }
			 
			    path.remove(path.size() - 1);
			    return false;
			}
	public List<Nokta> getPath() {
		return path;
	}
}
