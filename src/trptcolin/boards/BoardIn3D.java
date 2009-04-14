package trptcolin.boards;

import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 9, 2009
 * Time: 11:04:21 AM
 */
public class BoardIn3D extends Board
{
    private static int[][] winSetsIn3D = createWinSets();

    public BoardIn3D()
    {
        squares = new char[27];
        winner = 0;
        winSets = winSetsIn3D;
    }

    private static int[][] createWinSets()
    {
//        x has {1, 14, 20}
//        o has {...}

        return new int[][]{
                // depth-slice 1:
                {0,1,2}, {3,4,5}, {6,7,8},  // width
                {0,3,6}, {1,4,7}, {2,5,8},  // height
                {0,4,8}, {2,4,6},           // diagonal (wh)
                // depth-slice 2:
                {9,10,11}, {12,13,14}, {15,16,17}, // width
                {9,12,15}, {10,13,16}, {11,14,17}, // height
                {9,13,17}, {11,13,15},             // diagonal (wh)
                // depth-slice 3:
                {18,19,20}, {21,22,23}, {24,25,26}, // width
                {18,21,24}, {19,22,25}, {20,23,26}, // height
                {18,22,26}, {20,22,24},             // diagonal (wh)

                // width-slice 1:
                {0,9,18}, {3,12,21}, {6,15,24}, // depth
                {0,12,24}, {6,12,18},           // diagonal (dh)
                // width-slice 2:
                {1,10,19}, {4,13,22}, {7,16,25},  // depth
                {1,13,25}, {7,13,19},             // diagonal (dh)
                // width-slice 3:
                {2,11,20}, {5,14,23}, {8,17,26},  // depth
                {2,14,26}, {8,14,20},             // diagonal (dh)

                // height-slice 1:
                {0,10,20}, {2,10,18},   // diagonal (wd)
                // height-slice 2:
                {3,13,23}, {5,13,21},    // diagonal (wd)
                // height-slice 3:
                {6,16,26}, {8,16,24},    // diagonal (wd)


                // 3-D diagonals
                {0,13,26}, {6,13,20}, {2,13,24}, {8,13,18}
        };
    }

    public Board copy() throws Exception
    {
        Board newBoard = new BoardIn3D();
        for(int position = 0; position < squares.length; position++)
            newBoard.populateWithoutChecks(squares[position], position);

        return newBoard;
    }

    
}
