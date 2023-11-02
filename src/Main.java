public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args)
    {

        showBoard();
    }

    /**
     * clears board and sets all board elements to a space
     */
    private static void clearBoard() // sets all the board elements to a space
    {
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " "; // make this cell a space
            }
        }
    }

    // write a method to show the board similar to clear the board here

    private static void showBoard()
    {
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
    }

    /**
     * checks for a valid move
     * @param row
     * @param col
     * @return
     */

    private static boolean isValidMove(int row, int col) // checks for a valid move
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))  // is it a space?
            retVal = true;
        return retVal;
    }

    /**
     * Determines if there is a win using the isRowWin, isColWin, and isDiagonalWin methods
     * @param player
     * @return is win
     */
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        else if (board[0][3].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        else
        {
            return false; // no diagonal win
        }
    }

    private static boolean isRowWin(String player)
    {
        for(int row = 0; row < ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isColWin(String player)
    {
        for(int col = 0; col < COL; col++)
        {
            if(board[col][0].equals(player) && board[col][1].equals(player) && board[col][2].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

// check for a tie: a do while loop with a cascaded if that checks each row, col, and diagonal. If a r/c/d has both an X and an O in it, it is impossible to win
    // If all rows are impossible to win, it's a tie!

}