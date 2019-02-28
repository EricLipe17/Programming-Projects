 /*******************************************************************************
 *                  Assignment Final: John Conway's Game of Life                *
 *                                                                              *
 * PROGRAMMER:       Eric Lipe                                                  *
 * CLASS:            CS200                                                      *
 * ASSIGNMENT:       Assignment Final                                           *
 * INSTRUCTOR:       Dean Zeller                                                *
 * TA:               Robert Carver                                              *
 * SUBMISSION DATE:  12/1/18                                                    *
 *                                                                              *
 * DESCRIPTION:                                                                 *
 * This class is the tester for the methods implemented in the GameofLife class *
 *                                                                              *
 * COPYRIGHT:                                                                   *
 * This program is copyright (c) 2018 Eric Lipe and Dean Zeller. This is        *
 * original work, without use of outside sources.                               *
 *******************************************************************************/
public class RunGOL {
    public static void main(String[] args){
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.RunRandomGOL();
////
//        int[][] arr = new int[30][30];
//        arr[2][1] = 1;
//        arr[3][2] = 1;
//        arr[1][3] = 1;
//        arr[2][3] = 1;
//        arr[3][3] = 1;
 //       GameOfLife game2 = new GameOfLife(arr);
//
//       gameOfLife.RunGliderFormation();
//
//       gameOfLife.RunPulsarFormation();
//
//        game2.RunMyOwnRandomGOL();
    }
}
