public class LU {
	
	private LMatrix l;
	private UMatrix u;
	private Matrix p;

	public static LU LUDecomposition(Matrix m) {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		Matrix s = new Matrix(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				s.set(i, j, m.get(i, j));
			}
		}

		LU lu = new LU(row, col);
		for (int i = 0; i < row; i++) {
			for (int j = i; j < col; j++) {
				lu.u.set(i, j, s.get(i, j));
			}
		}
		for (int k = 0; k < col; k++) {
			if (s.get(k, k) == 0) {
				int h = k;
				for (; h < row; h++) {
					if (s.get(h, h) != 0) {
						for (int l = 0; l < col; l++) {
							double v2 = s.get(k, l);
							s.set(k, l, s.get(h, l));
							lu.u.set(k, l, s.get(h, l));
							s.set(h, l, v2);
							lu.u.set(h, l, v2);
							if (l < row && lu.p.get(k, l) != lu.p.get(h, l)) {
								double v3 = lu.p.get(k, l);
								lu.p.set(k, l, lu.p.get(h, l));
								lu.p.set(h, l, v3);
							}
						}
						break;
					}
				}
				if (h == row - k) {
				
				}
			}

			lu.l.set(k, k, 1);

			for (int i = k + 1; i < row; i++) {
				s.set(i, k, s.get(i, k) / s.get(k, k));
				lu.l.set(i, k, s.get(i, k));

				for (int j = k + 1; j < col; j++) {
					s.set(i, j, s.get(i, j) - lu.l.get(i, k) * s.get(k, j));
					lu.u.set(i, j, s.get(i, j));
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

	public Matrix getP() {
		return this.p;
	}

	private LU(int m, int n) {
		this.l = new LMatrix(m, m);
		this.u = new UMatrix(m, n);
		this.p = new Matrix(m, m);
		for (int i = 0; i < m; i++) {
			this.p.set(i, i, 1);
		}
	}

	public static void main(String[] args) {
		Matrix m = Matrix.loadMatrix(args[0]);
		System.out.println("A:");
		m.print();
		LU lu = LU.LUDecomposition(m);
		System.out.println("L:");
		lu.getL().print();
		System.out.println("U:");
		lu.getU().print();
		System.out.println("P:");
		lu.getP().print();
	}
}
