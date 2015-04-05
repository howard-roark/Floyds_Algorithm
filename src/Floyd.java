import java.util.Scanner;

/**
 * @author Matthew McGuire
 *
 *         CS 4050 Floyd Exercise
 */
public class Floyd {

  private static void displayProcessedGraphMap(Cell[][] processedGraph, int iterationVal) {
    System.out
        .println("\n\n\t\t\u001B[34mVALUE\u001B[0m Graph at iteration: " + (iterationVal + 1));
    for (int row = 0; row < processedGraph.length; row++) {
      for (int col = 0; col < processedGraph[row].length; col++) {
        int val = processedGraph[row][col].getValue();
        System.out.print("\t\t");
        System.out.printf("%-12s", (val == Integer.MAX_VALUE) ? "-" : val);
      }
      System.out.println();
    }

    System.out.println(
        "\n\n\t\t\u001B[34mITERATION\u001B[0m Values per cell at iteration: " + (iterationVal + 1));
    for (int row = 0; row < processedGraph.length; row++) {
      for (int col = 0; col < processedGraph[row].length; col++) {
        System.out.print("\t\t");
        System.out.printf("%-12d", processedGraph[row][col].getIteration() + 1);
      }
      System.out.println();
    }
    System.out.println("\n\t\t------------------------");
  }

  private static void processGraphs(Cell[][] initialCellMatrix) {
    Cell[][] processGraphMatrix = initialCellMatrix;
    for (int specRowAndCol = 0; specRowAndCol < processGraphMatrix.length; specRowAndCol++) {
      for (int row = 0; row < processGraphMatrix.length; row++) {
        for (int col = 0; col < processGraphMatrix.length; col++) {
          if ((row != col) && (row != specRowAndCol) && (col != specRowAndCol)) {
            Cell cell = processGraphMatrix[row][col];
            int initialValue = cell.getValue();

            Cell compareValueOneCell = processGraphMatrix[specRowAndCol][col];
            int compareValueOne = compareValueOneCell.getValue();

            Cell compareValueTwoCell = processGraphMatrix[row][specRowAndCol];
            int compareValueTwo = compareValueTwoCell.getValue();

            int specRowColCompareVal = compareValueOne + compareValueTwo;
            if (initialValue > (specRowColCompareVal < 0 ? Integer.MAX_VALUE
                                                         : specRowColCompareVal)) {
              cell.setValue(compareValueOne + compareValueTwo);
              cell.setIteration(specRowAndCol);
            }
          }
        }
      }
      displayProcessedGraphMap(processGraphMatrix, specRowAndCol);
    }
  }

  public static void main(String[] args) {
    processGraphs(UserData.getInitialGraph());
  }
}

/**
 * Get data from user to test Floyd's algorithm.
 */
class UserData {

  static Cell[][] getInitialGraph() {
    Scanner in = new Scanner(System.in);
    System.out.println("Floyd's Algorithm:\nWhen prompted please enter" +
                       " the number of vertices in your graph followed by the" +
                       "weights.\n\nWhen entering the weights please enter them row" +
                       " by row and column by column, using -1 to imply no path.\n\n");

    System.out.println("\tPlease enter the number of vertices:");
    int numOfVertices = -9;
    while (!in.hasNextInt()) {
      System.err.println("Please enter a valid integer for the amount " +
                         "of vertices in your graph.");
      try {
        numOfVertices = Integer.getInteger(in.next());
      } catch (Exception e) {
      }
    }
    if (numOfVertices == -9) {
      numOfVertices = in.nextInt();
    }

    Cell[][] initialCellMatrix = new Cell[numOfVertices][numOfVertices];

    for (int i = 0; i < numOfVertices; i++) {//Row loop
      for (int j = 0; j < numOfVertices; j++) {//Column loop
        if (i == j) {
          Cell diagCell = new Cell(0, 0);

          initialCellMatrix[i][j] = diagCell;
        } else {
          System.out.println("\tPlease enter the weights for the path " +
                             "from V:" + (i + 1) + " and V:" + (j + 1));
          int weight = Integer.MIN_VALUE;

          while (!in.hasNextInt()) {
            System.err.println("Please enter a valid integer for " +
                               "the path weight for V:" + (i + 1) + " to V:" + (j + 1));
            try {
              weight = Integer.getInteger(in.next());
            } catch (Exception e) {
            }
          }

          if (weight == Integer.MIN_VALUE) {
            weight = in.nextInt();
          }

          if (weight == -1) {
            weight = Integer.MAX_VALUE;
          }

          System.out.println("To get from V:" + (i + 1) + " to V:" + (j + 1) +
                             " weighs: " + weight);
          Cell cell = new Cell(weight, 0);
          initialCellMatrix[i][j] = cell;
        }
      }
    }
    return initialCellMatrix;
  }
}

/**
 * This class will hold the value of the distance between 2 points and the iteration of the
 * algorithm in which that value was derived, which is also the point which is added between two
 * points to make the distance shorter.
 */
class Cell {

  private int value = -1;
  private int iteration = -1;

  public Cell(int val, int iteration) {
    this.value = val;
    this.iteration = iteration;
  }

  protected void setValue(int value) {
    this.value = value;
  }

  protected int getValue() {
    return this.value;
  }

  protected void setIteration(int iteration) {
    this.iteration = iteration;
  }

  protected int getIteration() {
    return this.iteration;
  }
}