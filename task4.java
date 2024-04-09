import java.util.Scanner;

public class task4 {
    static final int N = 9;

    public static void main(String[] args) {
        int[][] grid = new int[N][N];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku grid (9x9) with 0 for empty cells:");
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                grid[row][col] = scanner.nextInt();

        System.out.println("Sudoku Puzzle:");
        printGrid(grid);

        if (solveSudoku(grid)) {
            System.out.println("\nSolved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku.");
        }
    }

    static void printGrid(int[][] grid) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++)
                System.out.print(grid[row][col] + " ");
            System.out.println();
        }
    }

    static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < N; x++)
            if (grid[row][x] == num || grid[x][col] == num)
                return false;

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    static boolean findEmptyCell(int[][] grid, int[] position) {
        for (position[0] = 0; position[0] < N; position[0]++)
            for (position[1] = 0; position[1] < N; position[1]++)
                if (grid[position[0]][position[1]] == 0)
                    return true;
        return false;
    }

    static boolean solveSudoku(int[][] grid) {
        int[] position = new int[2];

        if (!findEmptyCell(grid, position))
            return true;

        int row = position[0];
        int col = position[1];

        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid))
                    return true;

                grid[row][col] = 0;
            }
        }

        return false;
    }
}
