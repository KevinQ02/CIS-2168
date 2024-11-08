/*
Don't do back to back for loops cause we already know where we're at
 */
public class SUDOKU {

/*
main test func, used videos, github, coursehero and chatgpt to figure out
 */
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 9, 0, 0, 4, 2, 6, 0, 0},
                {4, 0, 0, 0, 5, 0, 0, 0, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 7, 2, 0, 0, 3, 4},
                {0, 0, 6, 0, 0, 0, 1, 0, 0},
                {7, 3, 0, 0, 9, 4, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 7, 0, 0, 0, 8},
                {0, 0, 9, 5, 3, 0, 0, 2, 0}
        };

        if (solve(board, 0, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
    private static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solve(int[][] board, int row, int col) {

        if(col == board.length){
            col = 0;
            row++;
        }
        if(row == board.length){
            return true;
        }
        if(board[row][col] != 0) {
            return solve(board, row, col +1);
        }


        for (int num = 1; num <= 9; num++) {
            if(isValid(board, row, col, num)){
                board[row][col] = num;
                if (solve(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < board.length; i++){
            if (board[row][i] == num) {
                return false;
            }
            if (board[i][col] == num) {
                return false;
            }
            if (board[3 * (row/3) + i/3][3 * (col/3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}


