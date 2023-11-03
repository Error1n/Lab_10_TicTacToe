import java.util.Scanner;
public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean playing = false;
        boolean donePlaying = false;
        String player = "X";
        int moveCount = 0;
        int row = -1;
        int col = -1;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;

        do
        {
            clearBoard();
            moveCount = 0;
            player = "X";
            playing = true;
            donePlaying = false;
            do // playing
            {

                do // getting valid input
                {
                    showBoard();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(scan, "Enter coordinates for row ", 1, 3);
                    col = SafeInput.getRangedInt(scan, "Enter coordinates for col ", 1, 3);
                    row--; // subtracting because board is 0-2
                    col--;
                }while(!isValidMove(row, col));

                board[row][col] = player; // putting the play on the board
                moveCount++;



                if(moveCount >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                        showBoard();
                        System.out.println("Player " + player + " won!");
                        playing = false;
                    }
                }
                if(moveCount >= MOVES_FOR_TIE)
                {
                    if(isTie(player))
                    {
                        System.out.println("It's a tie!");
                        playing = false;
                    }
                }

                if(player.equals("X"))
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }


            }while(playing);
            donePlaying = SafeInput.getYNConfirm(scan,"Are you done playing?");
        }while(!donePlaying);



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


    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
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


    private static boolean isTie(String player)
    {
        if(isRowOneTie(player) && isRowTwoTie(player) && isRowThreeTie(player) && isColOneTie(player) && isColTwoTie(player) && isColThreeTie(player) && isDiagnalOneTie(player) && isDiagnalTwoTie(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isDiagnalOneTie(String player)
    {
        boolean diagonalOneX = false;
        boolean diagonalOneO = false;
        if(board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X"))
        {
            diagonalOneX = true;
        }
        if(board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O"))
        {
            diagonalOneO = true;
        }
        return !(diagonalOneX && diagonalOneO);
    }
    private static boolean isDiagnalTwoTie(String player)
    {
        boolean diagonalTwoX = false;
        boolean diagonalTwoO = false;
        if(board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X"))
        {
            diagonalTwoX = true;
        }
        if(board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O"))
        {
            diagonalTwoO = true;
        }
        return !(diagonalTwoX && diagonalTwoO);
    }
    private static boolean isRowOneTie(String player)
    {
        boolean rowOneX = false;
        boolean rowOneO = false;

        for(int col = 0; col < COL; col++)
        {
            if(board[0][col].equals("X"))
            {
                rowOneX = true;
            }
            if(board[0][col].equals("O"))
            {
                rowOneO = true;
            }

        }
        return rowOneX && rowOneO;
    }
    private static boolean isRowTwoTie(String player)
    {
        boolean rowTwoX = false;
        boolean rowTwoO = false;

        for(int col = 0; col < COL; col++)
        {
            if(board[1][col].equals("X"))
            {
                rowTwoX = true;
            }
            if(board[1][col].equals("O"))
            {
                rowTwoO = true;
            }

        }
        return rowTwoX && rowTwoO;
    }
    private static boolean isRowThreeTie(String player)
    {
        boolean rowThreeX = false;
        boolean rowThreeO = false;

        for(int col = 0; col < COL; col++)
        {
            if(board[2][col].equals("X"))
            {
                rowThreeX = true;
            }
            if(board[2][col].equals("O"))
            {
                rowThreeO = true;
            }

        }
        return rowThreeX && rowThreeO;
    }
    private static boolean isColOneTie(String player)
    {
        boolean colOneX = false;
        boolean colOneO = false;

        for(int row = 0; row < ROW; row++)
        {
            if(board[row][0].equals("X"))
            {
                colOneX = true;
            }
            if(board[row][0].equals("O"))
            {
                colOneO = true;
            }

        }
        return colOneX && colOneO;
    }
    private static boolean isColTwoTie(String player)
    {
        boolean colTwoX = false;
        boolean colTwoO = false;

        for(int row = 0; row < ROW; row++)
        {
            if(board[row][1].equals("X"))
            {
                colTwoX = true;
            }
            if(board[row][1].equals("O"))
            {
                colTwoO = true;
            }

        }
        return colTwoX && colTwoO;
    }
    private static boolean isColThreeTie(String player)
    {
        boolean colThreeX = false;
        boolean colThreeO = false;

        for(int row = 0; row < ROW; row++)
        {
            if(board[row][2].equals("X"))
            {
                colThreeX = true;
            }
            if(board[row][2].equals("O"))
            {
                colThreeO = true;
            }

        }
        return colThreeX && colThreeO;
    }



}