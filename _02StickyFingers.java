package Exam25June2022;

import java.util.Scanner;

public class _02StickyFingers {
    private static int dillingerRowPosition = -1;
    private static int dillingerColPosition = -1;
    private static boolean catchFromPolice = false;
    private static boolean dillingerIsInField = true;
    private static int money = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] matrix = new char[n][n];
        for (int row = 0; row < n; row++) {
            String[] arr = scanner.nextLine().split(" ");
            for (int col = 0; col < arr.length; col++) {
                char currentSymbol = arr[col].charAt(0);
                if (currentSymbol == 'D') {
                    dillingerRowPosition = row;
                    dillingerColPosition = col;
                }
                matrix[row][col] = currentSymbol;
            }
        }

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            if (command.equals("up")) {
                move(matrix, -1, 0);
                if (catchFromPolice) {
                    break;
                }
            } else if (command.equals("down")) {
                move(matrix, +1, 0);
                if (catchFromPolice) {
                    break;
                }
            } else if (command.equals("left")) {
                move(matrix, 0, -1);
                if (catchFromPolice) {
                    break;
                }
            } else if (command.equals("right")) {
                move(matrix, 0, +1);
                if (catchFromPolice) {
                    break;
                }
            }
        }

        if (!catchFromPolice) {
            System.out.println("Your last theft has finished successfully with " + money + "$ in your pocket.");
        } else {
            System.out.println("You got caught with " + money + "$, and you are going to jail.");
        }

        printMatrix(matrix);

    }

    private static void move(char[][] matrix, int rowPosition, int colPosition) {
        int nextRow = dillingerRowPosition + rowPosition;
        int nextCol = dillingerColPosition + colPosition;

        if (!isInBounds(matrix, nextRow, nextCol)) {
            matrix[dillingerRowPosition][dillingerColPosition] = '+';
            dillingerIsInField = false;
            System.out.println("You cannot leave the town, there is police outside!");
            return;
        }

        if (matrix[nextRow][nextCol] == '$') {
            int stolenMoney = nextRow * nextCol;
            money += stolenMoney;
            matrix[dillingerRowPosition][dillingerColPosition] = '+';
            matrix[nextRow][nextCol] = 'D';
            dillingerRowPosition = nextRow;
            dillingerColPosition = nextCol;
            System.out.println("You successfully stole " + stolenMoney + "$.");
        } else if (matrix[nextRow][nextCol] == 'P') {
            catchFromPolice = true;
            matrix[dillingerRowPosition][dillingerColPosition] = '+';
            matrix[nextRow][nextCol] = '#';
            return;
        }

        matrix[dillingerRowPosition][dillingerColPosition] = '+';
        matrix[nextRow][nextCol] = 'D';
        dillingerRowPosition = nextRow;
        dillingerColPosition = nextCol;

    }

    private static boolean isInBounds(char[][] matrix, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[nextRow].length;
    }

    private static void printMatrix(char[][] matrix) {

        for (char[] chars : matrix) {
            for (int i = 0; i < chars.length; i++) {
                if (i == chars.length - 1) {
                    System.out.print(chars[i]);
                } else {
                    System.out.print(chars[i] + " ");
                }

            }
            System.out.println();
        }
    }
}
