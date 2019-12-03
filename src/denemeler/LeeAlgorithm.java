package denemeler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Mapping.GridEngel;
import Ortak.OrtakMetotlar;

public class LeeAlgorithm {

	private final int matrixWidth = OrtakMetotlar.getGridYsayisi(), matrixHeight = OrtakMetotlar.getGridXsayisi();
	private int matrix[][] = new int[matrixWidth][matrixHeight];
	private boolean matrixVisited[][] = new boolean[matrixWidth][matrixHeight];
	private ArrayList<Dugum> DugumList = new ArrayList<Dugum>();
	private final int MAXITERATIONS = 100000;
	private final int OBSTACLE = -1;

	/*
	 * find the shortest path between start and goal
	 * 
	 */

	public LeeAlgorithm() {
		// matrix[4][1]=OBSTACLE; matrixVisited[4][1]=true;
		// matrix[3][1]=OBSTACLE; matrixVisited[3][1]=true;
		// matrix[2][3]=OBSTACLE; matrixVisited[2][3]=true;
		// matrix[4][3]=OBSTACLE; matrixVisited[4][3]=true;
		// matrix[5][3]=OBSTACLE; matrixVisited[5][3]=true;
		// //matrix[1][0]=OBSTACLE; matrixVisited[1][0]=true; no path
		// matrix[0][1]=OBSTACLE; matrixVisited[0][1]=true;
	}

	public LeeAlgorithm(ArrayList<GridEngel> engelListesi) {
		System.out.println("ENGEL LISTESI SIZE "+engelListesi.size());
		for (int i = 0; i < engelListesi.size(); i++) {
			for(int j=0;j<engelListesi.get(i).getNoktalar().size();j++){
				System.out.println("X "+engelListesi.get(i).getEngelIndeks()+" Engel Noktalari X "+engelListesi.get(i).getNoktalar().get(j).getX()+" y "+engelListesi.get(i).getNoktalar().get(j).getY());
				
			matrix[engelListesi.get(i).getNoktalar().get(j).getX()][engelListesi.get(i).getNoktalar().get(j).getY()] = OBSTACLE;
			if (engelListesi.get(i).getNoktalar().get(j).getX() != 0) {
				matrix[engelListesi.get(i).getNoktalar().get(j).getX() - 1][engelListesi.get(i).getNoktalar().get(j).getY()] = OBSTACLE;
				matrixVisited[engelListesi.get(i).getNoktalar().get(j).getX() - 1][engelListesi.get(i).getNoktalar().get(j).getY()] = true;
			}
			if (engelListesi.get(i).getNoktalar().get(j).getY() != 0) {
				matrix[engelListesi.get(i).getNoktalar().get(j).getX()][engelListesi.get(i).getNoktalar().get(j).getY() - 1] = OBSTACLE;
				matrixVisited[engelListesi.get(i).getNoktalar().get(j).getX()][engelListesi.get(i).getNoktalar().get(j).getY() - 1] = true;
			}

			matrixVisited[engelListesi.get(i).getNoktalar().get(j).getX()][engelListesi.get(i).getNoktalar().get(j).getY()] = true;
			}
		}
	}

	public ArrayList<Dugum> findPath(Dugum start, Dugum goal) {
		// for(int i=0;i<matrixWidth;i++){
		// for(int j=0;j<matrixHeight;j++){
		// System.out.print(matrixVisited[i][j]);
		// }
		// System.out.println();
		// }
		boolean sonuc = true;
		if (DugumList.isEmpty()) {
			DugumList.add(start);
			matrixVisited[start.getX()][start.getY()] = true;
		}

		for (int i = 1; i < MAXITERATIONS; i++) {

			DugumList = markNeighbors(DugumList, i);

			if (matrix[goal.getX()][goal.getY()] != 0) {
				System.out.println("Path exists");
				break;
			}

			if (i == MAXITERATIONS - 1) {
				System.out.println("No Path exists");
				// System.exit(0);
				sonuc = false;
			}
		}
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		//
		// for(int i=0;i<matrixWidth;i++){
		// for(int j=0;j<matrixHeight;j++){
		// System.out.print(matrixVisited[i][j]);
		// }
		// System.out.println();
		// }

		if (sonuc) {
			ArrayList<Dugum> pathList = backtraceFromGoal(goal, start);
			return pathList;
		} else {
			return null;
		}

	}

	/*
	 * First step
	 * 
	 * mark all unlabeled neighbors of points which are marked with i, with i+1
	 */

	private ArrayList<Dugum> markNeighbors(ArrayList<Dugum> neighborList, int iteration) {

		ArrayList<Dugum> neighbors = new ArrayList<Dugum>();
		// System.out.println("Deneme");
		for (Dugum Dugum : neighborList) {
			System.out.println("Dugum X:" + Dugum.getX() + " Y :" + Dugum.getY());

			if (Dugum.getY() + 1 < matrixHeight && matrixVisited[Dugum.getX()][Dugum.getY() + 1] == false) {

				Dugum Dugum1 = new Dugum(Dugum.getX(), Dugum.getY() + 1);
				neighbors.add(Dugum1);
				matrix[Dugum.getX()][Dugum.getY() + 1] = iteration;
				matrixVisited[Dugum.getX()][Dugum.getY() + 1] = true;
			}

			if (Dugum.getY() >= 1 && matrixVisited[Dugum.getX()][Dugum.getY() - 1] == false) {

				Dugum Dugum2 = new Dugum(Dugum.getX(), Dugum.getY() - 1);
				neighbors.add(Dugum2);
				matrix[Dugum.getX()][Dugum.getY() - 1] = iteration;
				matrixVisited[Dugum.getX()][Dugum.getY() - 1] = true;
			}

			if (Dugum.getX() + 1 < matrixWidth && matrixVisited[Dugum.getX() + 1][Dugum.getY()] == false) {

				Dugum Dugum3 = new Dugum(Dugum.getX() + 1, Dugum.getY());
				neighbors.add(Dugum3);
				matrix[Dugum.getX() + 1][Dugum.getY()] = iteration;
				matrixVisited[Dugum.getX() + 1][Dugum.getY()] = true;
			}

			if (Dugum.getX() >= 1 && matrixVisited[Dugum.getX() - 1][Dugum.getY()] == false) {

				Dugum Dugum4 = new Dugum(Dugum.getX() - 1, Dugum.getY());
				neighbors.add(Dugum4);
				matrix[Dugum.getX() - 1][Dugum.getY()] = iteration;
				matrixVisited[Dugum.getX() - 1][Dugum.getY()] = true;
			}
		}
		return neighbors;
	}

	/*
	 * Second step
	 * 
	 * from goal Dugum go to next Dugum that has a lower mark than the current
	 * Dugum add this Dugum to path until start Dugum is reached
	 */

	private ArrayList<Dugum> backtraceFromGoal(Dugum fromGoal, Dugum toStart) {

		ArrayList<Dugum> pathList = new ArrayList<Dugum>();

		pathList.add(fromGoal);
		Dugum currentDugum = null;

		// while (!pathList.get(pathList.size() - 1).equals(toStart)) {
		while (!(pathList.get(pathList.size() - 1).getX() == toStart.getX()
				&& pathList.get(pathList.size() - 1).getY() == toStart.getY())) {
			currentDugum = pathList.get(pathList.size() - 1);
			Dugum n = getNeighbor(currentDugum);
			pathList.add(n);
			n = currentDugum;
		}
		return pathList;
	}

	/*
	 * get Neighbor of Dugum with smallest matrix value, todo shuffle
	 */

	private Dugum getNeighbor(Dugum Dugum) {

		ArrayList<Dugum> possibleNeighbors = new ArrayList<Dugum>();

		if (Dugum.getY() + 1 < matrixHeight && matrixVisited[Dugum.getX()][Dugum.getY() + 1] == true
				&& matrix[Dugum.getX()][Dugum.getY() + 1] != OBSTACLE) {

			Dugum n = new Dugum(Dugum.getX(), Dugum.getY() + 1, matrix[Dugum.getX()][Dugum.getY() + 1]);
			possibleNeighbors.add(n);
		}

		if (Dugum.getY() >= 1 && matrixVisited[Dugum.getX()][Dugum.getY() - 1] == true
				&& matrix[Dugum.getX()][Dugum.getY() - 1] != OBSTACLE) {

			Dugum n = new Dugum(Dugum.getX(), Dugum.getY() - 1, matrix[Dugum.getX()][Dugum.getY() - 1]);
			possibleNeighbors.add(n);
		}

		if (Dugum.getX() + 1 < matrixWidth && matrixVisited[Dugum.getX() + 1][Dugum.getY()] == true
				&& matrix[Dugum.getX() + 1][Dugum.getY()] != OBSTACLE) {

			Dugum n = new Dugum(Dugum.getX() + 1, Dugum.getY(), matrix[Dugum.getX() + 1][Dugum.getY()]);
			possibleNeighbors.add(n);
		}

		if (Dugum.getX() >= 1 && matrixVisited[Dugum.getX() - 1][Dugum.getY()] == true
				&& matrix[Dugum.getX() - 1][Dugum.getY()] != OBSTACLE) {

			Dugum n = new Dugum(Dugum.getX() - 1, Dugum.getY(), matrix[Dugum.getX() - 1][Dugum.getY()]);
			possibleNeighbors.add(n);
		}

		Collections.sort(possibleNeighbors, new Comparator<Dugum>() {
			@Override
			public int compare(Dugum first, Dugum second) {
				return first.getValue() - second.getValue();
			}
		});

		Dugum n = possibleNeighbors.remove(0);

		return n;
	}

	public void printSolution(ArrayList<Dugum> output) {

		System.out.println("Shortest Path:");
		for (Dugum n : output) {
			int x = n.getX();
			int y = n.getY();
			System.out.println(n.toString());
			matrix[x][y] = 0;
		}

		System.out.println("");

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {

				if (matrix[i][j] != 0 && matrix[i][j] != OBSTACLE) {
					matrix[i][j] = 1;
				}

				if (matrixVisited[i][j] == false) {
					matrix[i][j] = 1;
				}

				if (matrix[i][j] == OBSTACLE) {
					System.out.print("O ");
				}

				else {

					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		LeeAlgorithm l = new LeeAlgorithm();
		ArrayList<Dugum> output = l.findPath(new Dugum(0, 0), new Dugum(5, 4));

		l.printSolution(output);

	}
}
