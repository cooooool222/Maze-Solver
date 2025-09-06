import java.io.*;
import java.util.*;
public class Main {
    public static char[][] maze;
    public static int rows, cols, beginR, beginC;
    public static boolean solve = false;

    public static void main(String[] args) {
        boolean load = loadmaze("maze.dat");
        if (load == false) { 
            System.out.println("Failed to load Maze"); 
            return;
        }
        maze[beginR][beginC] = ' ';
        if (solverMaze(beginR, beginC)) { 
            System.out.println("Congrats you beat the Maze!");
        }
        else {
            System.out.println("Not found");
        }
        pMaze(); 

    }

    public static boolean loadmaze(String filename) {
         File file = new File(filename);
        try(Scanner scan = new Scanner(file)) {
            rows = scan.nextInt();
            cols = scan.nextInt();
            scan.nextLine(); 

            maze = new char[rows][cols]; 
            for(int i = 0; i < rows; i++) {
                String lines = scan.nextLine();
                for(int l = 0; l < cols; l++) {
                    maze[i][l] = lines.charAt(l);
                    if(maze[i][l] == '+') {
                        beginR = i; 
                        beginC = l;
                    }
                }
        }
        return true; 
        } catch(FileNotFoundException e) {
            System.out.println("Error in loading file");
        }
          catch(Exception e) {
            System.out.println("Error in read maze");
          }
          return false;
        }
    private static boolean solverMaze(int row, int column) {
    if (row < 0 || column < 0 || row >= rows || column >= cols || maze[row][column] == 'X' || maze[row][column] == '.' || maze[row][column] == '+') {
        return false; 
    }

 
    if (maze[row][column] == '-') {
        solve = true;
        return true; 
    }

    maze[row][column] = '+';

    boolean foundPath = solverMaze(row + 1, column) | solverMaze(row - 1, column) | solverMaze(row, column + 1) | solverMaze(row, column - 1);

    if (!foundPath) {
        maze[row][column] = '.';
    }

    return foundPath; 
}

      private static void pMaze() {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }
}

    


         
    


    


