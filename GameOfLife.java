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
 * This class creates everything needed to run Conway's game of life.           *
 *                                                                              *
 * CREDITS:                                                                     *
 * The following sources were used in completion with this program.             *
 *      PROGRAM:     Peach                                                      *
 *      PROGRAMMER:  Bucky                                                      *
 *      LOCATION:    https://www.youtube.com/watch?v=2l5-5PMUc5Y                *
 *      MODIFICATIONS:                                                          *
 *          I didn't copy any of his code directly. I mainly used his video as  *
 *          a source to understand the Graphics object and how it could be      *
 *          used. Oracle's documentation really wasn't great.                   *
 *                                                                              *
 * COPYRIGHT:                                                                   *
 *This program is copyright (c) 2018 Eric Lipe and Dean Zeller. This is         *
 * original work, making use of the above sources.                              *
 *******************************************************************************/


 import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GameOfLife {
    //Attributes
    private int rows;
    private int cols;
    private JPanel panel;
    private JFrame frame;
    private Container contentPane;
    private int[][] grid;
    Random rand = new Random();

     /******************************************************************
     * METHOD:      GameofLife                                         *
     * PARAMETERS:                                                     *
     *     N/A                                                         *
     * DESCRIPTION: This is the constructor that should be used when   *
     *              not creating your own array of inputs. I.E use     *
     *              this constructor when you aren't using the         *
     *              "RunMyOwnGOL" method.                              *
     *                                                                 *
     ******************************************************************/
    public GameOfLife() {
        this.rows = 30;
        this.cols = 30;
        this.panel = new JPanel();
        this.frame = new JFrame();
        this.frame.setSize(325, 350);
        this.contentPane = frame.getContentPane();
        this.contentPane.add(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.show();
    }

     /******************************************************************
     * METHOD:      GameofLife                                         *
     * PARAMETERS:                                                     *
     *     Parameter1 - int array[][]                                  *
     * DESCRIPTION: This is the constructor that should be used when   *
     *              you are using the "RunMyOwnGOL" method.            *
     *                                                                 *
     ******************************************************************/
    public GameOfLife(int array[][]) {
        this.grid = array;
        this.rows = array[0].length;
        this.cols = array.length;
        this.panel = new JPanel();
        this.frame = new JFrame();
        this.frame.setSize(array[0].length * 11, array.length * 12);
        this.contentPane = frame.getContentPane();
        this.contentPane.add(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.show();
    }

     /******************************************************************
     * METHOD:      generate2DArray                                    *
     * PARAMETERS:                                                     *
     *     Parameter1 - int rows                                       *
     *    Parameter2 - int cols                                        *
     * DESCRIPTION: This Method is a quick way to generate a 2D array  *
     *              that is then randomly filled with 1's and 0's.     *
     *                                                                 *
     ******************************************************************/
    public int[][] generate2DArray(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                array[i][j] = rand.nextInt(2);
            }
        }
        return array;
    }

     /******************************************************************
     * METHOD:      countNeighbors                                     *
     * PARAMETERS:                                                     *
     *     Parameter1 - int array[][]                                  *
     *     Parameter2 - int x                                          *
     *     Parameter3 - int y                                          *
     * DESCRIPTION: This Method is the algorithm that checks each live *
     *              and dead cells neighbors to determine if they      *
     *              will die or be born. Also acts as if the board is  *
     *              infinite. I.E. the cells on the edges are also     *
     *              neighbors with thoughs on the opposite edge.       *
     *                                                                 *
     ******************************************************************/
    public int countNeighbors(int[][] array, int x, int y){
        int sum = 0;
        //creating 'infinite' board. wrapping board so that edges are neighbors with opposite edges.
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                int col = (x + i + this.cols) % this.cols;
                int row = (y + j + this.rows) % this.rows;
                sum += array[col][row];
            }
        }
        sum -= grid[x][y];
        return sum;
    }

     /******************************************************************
     * METHOD:      Graphics                                           *
     * PARAMETERS:                                                     *
     *     Parameter1 - int array[][]                                  *
     *     Parameter2 - Graphics g                                     *
     * DESCRIPTION: This Method is what is used to 'animate' the       *
     *              JFrame.                                            *
     *                                                                 *
     ******************************************************************/
    public static void Draw(int[][] array, Graphics g) {
        int rect_dim = 10;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                g.drawRect(i * rect_dim, j * rect_dim, 10, 10);
                if (array[i][j] == 0) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * rect_dim, j * rect_dim, 10, 10);
                }
                if (array[i][j] == 1) {
                    g.setColor(Color.CYAN);
                    g.fillRect(i * rect_dim, j * rect_dim, 10, 10);
                }
            }
        }

    }

     /******************************************************************
     * METHOD:      RunMyOwnRandomGOL                                  *
     * PARAMETERS:                                                     *
     *     N/A                                                         *
     * DESCRIPTION: This Method runs the Game of Life with an array    *
     *              of inputs determined by the user.                  *
     *                                                                 *
     ******************************************************************/
    public void RunMyOwnRandomGOL(){
        int[][] gridnew = new int[this.grid.length][this.grid[0].length];
        while (true) {
            for (int i = 0; i <= this.grid.length - 1; i++) {
                for (int j = 0; j <= this.grid[0].length - 1; j++) {
                    int state = this.grid[i][j];
                    int neighbors = countNeighbors(grid, i, j);
                    if (state == 0 && neighbors == 3){
                        gridnew[i][j] = 1;
                    }
                    else if (state == 1 && (neighbors < 2 || neighbors > 3)){
                        gridnew[i][j] = 0;
                    }
                    else {
                        gridnew[i][j] = state;
                    }
                }
            }

            Graphics g = panel.getGraphics();
            Draw(this.grid, g);
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[0].length; j++) {
                    this.grid[i][j] = gridnew[i][j];
                    gridnew[i][j] = 0;
                }
            }
            g.dispose();
        }
    }

     /******************************************************************
     * METHOD:      RunRandomGOL                                       *
     * PARAMETERS:                                                     *
     *     N/A                                                         *
     * DESCRIPTION: This Method runs the Game of Life with a random    *
     *              dispersion of live and dead cells.                 *
     *                                                                 *
     ******************************************************************/
    public void RunRandomGOL(){
        this.grid = generate2DArray(this.rows, this.cols);
        int[][] gridnew = new int[this.grid.length][this.grid[0].length];
        while (true) {
            for (int i = 0; i <= this.grid.length - 1; i++) {
                for (int j = 0; j <= this.grid[0].length - 1; j++) {
                    int state = this.grid[i][j];
                    int neighbors = countNeighbors(grid, i, j);
                    if (state == 0 && neighbors == 3){
                        gridnew[i][j] = 1;
                    }
                    else if (state == 1 && (neighbors < 2 || neighbors > 3)){
                        gridnew[i][j] = 0;
                    }
                    else {
                        gridnew[i][j] = state;
                    }
                }
            }

            Graphics g = panel.getGraphics();
            Draw(this.grid, g);
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[0].length; j++) {
                    this.grid[i][j] = gridnew[i][j];
                    gridnew[i][j] = 0;
                }
            }
            g.dispose();
        }
    }

     /******************************************************************
     * METHOD:      RunGliderFormation                                 *
     * PARAMETERS:                                                     *
     *     N/A                                                         *
     * DESCRIPTION: This Method runs the Game of Life with the popular *
     *              glider pre-set to run.                             *
     *                                                                 *
     ******************************************************************/
    public void RunGliderFormation(){
        int[][] grid_ = new int[30][30];
        grid_[2][1] = 1;
        grid_[3][2] = 1;
        grid_[1][3] = 1;
        grid_[2][3] = 1;
        grid_[3][3] = 1;
        this.grid = grid_;
        int[][] gridnew = new int[this.grid.length][this.grid[0].length];
        while (true) {
            for (int i = 0; i <= this.grid.length - 1; i++) {
                for (int j = 0; j <= this.grid[0].length - 1; j++) {
                    int state = this.grid[i][j];
                    int neighbors = countNeighbors(grid, i, j);
                    if (state == 0 && neighbors == 3){
                        gridnew[i][j] = 1;
                    }
                    else if (state == 1 && (neighbors < 2 || neighbors > 3)){
                        gridnew[i][j] = 0;
                    }
                    else {
                        gridnew[i][j] = state;
                    }
                }
            }

            Graphics g = panel.getGraphics();
            Draw(this.grid, g);
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[0].length; j++) {
                    this.grid[i][j] = gridnew[i][j];
                    gridnew[i][j] = 0;
                }
            }
            g.dispose();
        }
    }


     /******************************************************************
     * METHOD:      RunPulsarFormation                                 *
     * PARAMETERS:                                                     *
     *     N/A                                                         *
     * DESCRIPTION: This Method runs the Game of Life with the popular *
     *              pulsar pre-set to run.                             *
     *                                                                 *
     ******************************************************************/
    public void RunPulsarFormation(){
        int[][] grid_ = new int[30][30];
        //first row
        grid_[10][6] = 1;
        grid_[11][6] = 1;
        grid_[12][6] = 1;
        grid_[10][6] = 1;
        grid_[16][6] = 1;
        grid_[17][6] = 1;
        grid_[18][6] = 1;

        //second row
        grid_[8][8] = 1;
        grid_[13][8] = 1;
        grid_[15][8] = 1;
        grid_[20][8] = 1;

        //third row
        grid_[8][9] = 1;
        grid_[13][9] = 1;
        grid_[15][9] = 1;
        grid_[20][9] = 1;

        //fourth row
        grid_[8][10] = 1;
        grid_[13][10] = 1;
        grid_[15][10] = 1;
        grid_[20][10] = 1;

        //fifth row
        grid_[10][11] = 1;
        grid_[11][11] = 1;
        grid_[12][11] = 1;
        grid_[10][11] = 1;
        grid_[16][11] = 1;
        grid_[17][11] = 1;
        grid_[18][11] = 1;

        //sixth row
        grid_[10][13] = 1;
        grid_[11][13] = 1;
        grid_[12][13] = 1;
        grid_[10][13] = 1;
        grid_[16][13] = 1;
        grid_[17][13] = 1;
        grid_[18][13] = 1;

        //seventh row
        grid_[8][14] = 1;
        grid_[13][14] = 1;
        grid_[15][14] = 1;
        grid_[20][14] = 1;

        //eighth row
        grid_[8][14] = 1;
        grid_[13][14] = 1;
        grid_[15][14] = 1;
        grid_[20][14] = 1;

        //ninth row
        grid_[8][15] = 1;
        grid_[13][15] = 1;
        grid_[15][15] = 1;
        grid_[20][15] = 1;

        //tenth row
        grid_[8][16] = 1;
        grid_[13][16] = 1;
        grid_[15][16] = 1;
        grid_[20][16] = 1;

        //eleventh row
        grid_[10][18] = 1;
        grid_[11][18] = 1;
        grid_[12][18] = 1;
        grid_[10][18] = 1;
        grid_[16][18] = 1;
        grid_[17][18] = 1;
        grid_[18][18] = 1;

        this.grid = grid_;
        int[][] gridnew = new int[this.grid.length][this.grid[0].length];
        while (true) {
            for (int i = 0; i <= this.grid.length - 1; i++) {
                for (int j = 0; j <= this.grid[0].length - 1; j++) {
                    int state = this.grid[i][j];
                    int neighbors = countNeighbors(grid, i, j);
                    if (state == 0 && neighbors == 3){
                        gridnew[i][j] = 1;
                    }
                    else if (state == 1 && (neighbors < 2 || neighbors > 3)){
                        gridnew[i][j] = 0;
                    }
                    else {
                        gridnew[i][j] = state;
                    }
                }
            }

            Graphics g = panel.getGraphics();
            Draw(this.grid, g);
            for (int i = 0; i < this.grid.length; i++) {
                for (int j = 0; j < this.grid[0].length; j++) {
                    this.grid[i][j] = gridnew[i][j];
                    gridnew[i][j] = 0;
                }
            }
            g.dispose();
        }
    }

}
