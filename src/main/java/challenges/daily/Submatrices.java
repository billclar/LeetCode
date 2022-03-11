package challenges.daily;

/**
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 * 
 * Given an m x n binary matrix mat, return the number of submatrices that have all ones.
 * 
 * @author bill.clar
 *
 */
public class Submatrices
{
	/**
	 * Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
	 * 
	 *  1  0  1
	 *  1  1  0
	 *  1  1  0
	 *  
	 * Output: 13
	 * Explanation: 
	 *   There are 6 rectangles of side 1x1.
	 *   There are 2 rectangles of side 1x2.
	 *   There are 3 rectangles of side 2x1.
	 *   There is 1 rectangle of side 2x2. 
	 *   There is 1 rectangle of side 3x1.
	 *   Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
	 * 
	 * @param matrix
	 * @return
	 */
	public static int getSubmatrices(int[][] matrix)
	{
		int rows = matrix.length;
		int columns = matrix[0].length;
		int result = 0;

		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < columns; col++)
			{
				if (matrix[row][col] == 0)
				{
					continue;
				}

				for (int k = 0; k <= row; k++)
				{
					for (int l = 0; l <= col; l++)
					{
						if (matrix[row - k][col - l] == 0)
						{
							break;
						}
						result++;
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args)
	{
		int[][] matrix = {
			{1, 0, 1},
			{1, 1, 0},
			{1, 1, 0}
		};
		
		System.out.println(getSubmatrices(matrix));
	}
}
