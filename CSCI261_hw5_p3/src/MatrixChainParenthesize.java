/**
 * MatrixChainParenthesize.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose:	Find the parenthesizing of a series of matrices that minimizes the
 * 			number of operations required to multiply them all.
 */

import java.util.Scanner;

public class MatrixChainParenthesize {

	// Attributes
	
	private int size;
	private int[] input;
	private int[][] S;
	private int[][] K;
	
	// Constructor
	
	/**
	 * Constructor for creating an instance of the algorithm solver,  Contains
	 * useful information for completing the algorithm such as, the number
	 * of matrices, the input from the user, the solution array, and the 'K'
	 * array used for reproducing the solution.
	 * 
	 * @param size	The number of matrices for the given problem.
	 * @param input	The input data given by the user.
	 */
	public MatrixChainParenthesize( int size, int[] input ) {
		this.size = size;
		this.input = input;
		this.S = new int[size][size];
		this.K = new int[size][size];
		
		for( int d = 0 ; d < size ; d++ ) {
			S[d][d] = 0;
			K[d][d] = 0;
		}//end for
	}//end MatrixChainParenthesize constructor
	
	// Methods
	
	/**
	 * The algorithm for solving the matrix chain multiplication problem, this
	 * simply fills in the solution array and the 'K' array.
	 */
	public void matrixChainMultiplication() {
		for( int l = 2 ; l <= size-1 ; l++ ) {
			for( int i = 1 ; i <= size-l ; i++ ) {
				int j = i+l-1;
				S[i][j] = Integer.MAX_VALUE;
				for( int k = i ; k <= j-1 ; k++ ) {
					int temp = S[i][k] + S[k+1][j] + input[i-1]*input[k]*input[j];
					if( temp < S[i][j] ) {
						S[i][j] = temp;
						K[i][j] = k;
					}//end if
				}//end for k
			}//end for i
		}//end for l
	}//end matrixChainMultiplication
	
	/**
	 * The recursive algorithm for reconstructing the solution, This method
	 * displays the result to the user.
	 * 
	 * @param row	The current row value in the recursion.
	 * @param col	The current column value in the recursion.
	 */
	public void matrixParenthesize( int row, int col ) {
		if( row == col ) {
			System.out.printf("A%d", row);
		}//end if
		else{
			int k = K[row][col];
			System.out.printf("( ");
			matrixParenthesize( row, k );
			System.out.printf(" x ");
			matrixParenthesize( k+1, col );
			System.out.printf(" )");
		}//end else
	}//end matrixParenthesize
	
	/**
	 * Main logic for the program,  Reads in the input from the user, Creates
	 * an object to hold the information needed to complete the problem, runs
	 * the initial solver algorithm, and then displays the results by running
	 * the solution reproduction algorithm.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		String input;
		input = sc.next();
		int numVals = Integer.parseInt( input )+1;
		int[] values = new int[numVals];
		int val;
		for( int i = 0 ; i < numVals ; i++ ) {
			input = sc.next();
			val = Integer.parseInt( input );
			values[i] = val;
		}//end for
		sc.close();
		MatrixChainParenthesize M = new MatrixChainParenthesize( numVals, values );
		M.matrixChainMultiplication();
		M.matrixParenthesize(1, numVals-1);
	}// end main
}//end MatrixChainParenthesize
