import java.util.Scanner;

/**
 * Created by Matthew McGuire on 3/1/15.
 */
public class Floyd {

  private static void displayProcessedGraphMap(Cell[][] finalCellMatrix) {
    //TODO show processed graph
  }

  private static void processGraphs(Cell[][] initialCellMatrix) {
    Cell[][] processGraphMap = initialCellMatrix;
    for (int specRowAndCol = 0; specRowAndCol < processGraphMap.length; specRowAndCol++) {
      for (int row = 0; row < processGraphMap.length; row++) {
        for (int col = 0; col < processGraphMap.length; col++) {
          if ((row != col) && (row != specRowAndCol) && (col != specRowAndCol)) {
            Cell cell = processGraphMap[row][col];
            int initialValue = cell.getValue();

            Cell compareValueOneCell = processGraphMap[specRowAndCol][col];
            int compareValueOne = compareValueOneCell.getValue();

            Cell compareValueTwoCell = processGraphMap[row][specRowAndCol];
            int compareValueTwo = compareValueTwoCell.getValue();

            if (initialValue > (compareValueOne + compareValueTwo)) {
              
            }
          }
        }
      }
    }
    displayProcessedGraphMap(processGraphMap);
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

    Cell[][] initialCellMatrix = new Cell[][]{};

    for (int i = 0; i < numOfVertices; i++) {//Row loop
      for (int j = 0; j < numOfVertices; j++) {//Column loop
        if (i == j) {
          Cell diagCell = new Cell(0, 0);

          initialCellMatrix[i][j] = diagCell;
        } else {
          System.out.println("\tPlease enter the weights for the path " +
                             "from V:" + (i + 1) + " and V:" + (j + 1));
          int weight = -9;
          while (!in.hasNextInt()) {
            System.err.println("Please enter a valid integer for " +
                               "the path weight for V:" + (i + 1) + " to V:" + (j + 1));
            try {
              weight = Integer.getInteger(in.next());
            } catch (Exception e) {
            }
          }
          if (weight == -9) {
            weight = in.nextInt();
          }

          System.out.println("To get from V:" + (i + 1) + " to V: " + (j + 1) +
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
 * algorithm in which that value was derived.
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