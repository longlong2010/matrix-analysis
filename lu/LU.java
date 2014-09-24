public class LU {
	
	private LMatrix l;
	private UMatrix u;

	public static LU LUDecomposition(Matrix m) {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		LU lu = new LU(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = i; j < col; j++) {
				lu.u.set(i, j, m.get(i, j));
			}
		}
		for (int k = 0; k < row; k++) {
			lu.l.set(k, k, 1);

			for (int i = k + 1; i < row; i++) {
				lu.l.set(i, k, m.get(i, k) / m.get(k, k));

				for (int j = k + 1; j < col; j++) {
					lu.u.set(i, j, lu.u.get(i, j) - lu.l.get(i, k) * lu.u.get(k, j));
				}
			}
		}
		return lu;
	}

	public LMatrix getL() {
		return this.l;
	}

	public UMatrix getU() {
		return this.u;
	}

	private LU(int m, int n) {
		this.l = new LMatrix(m, n);
		this.u = new UMatrix(m, n);
	}

	public static void main(String[] args) {
		Matrix m = new Matrix(2, 3);
		m.set(0, 0, 2);
		m.set(1, 0, 1);
		m.set(0, 1, 1);
		m.set(1, 1, 1);
		m.print();
		LU lu = LU.LUDecomposition(m);
		lu.getL().print();
		lu.getU().print();
	}
}
