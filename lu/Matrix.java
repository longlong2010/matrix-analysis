public class Matrix {

	protected double[][] elements;

	public void set(int i, int j, double v) {
		this.elements[i][j] = v;
	}

	public double get(int i, int j) {
		return this.elements[i][j];
	}

	public int getColumSize() {
		return this.elements[0].length;
	}

	public int getRowSize() {
		return this.elements.length;
	}

	public void print() {
		int row = this.getRowSize();
		int col = this.getColumSize();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.printf("%g\t", this.get(i, j));
			}
			System.out.println();
		}
	}
	
	public Matrix() {
	
	}

	public Matrix(int m, int n) {
		this.elements = new double[m][];
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = new double[n];
		}
	}
}
