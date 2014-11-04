package qr;

import matrix.Matrix;
import matrix.UMatrix;
import matrix.vector.Vector;

public class QR {
	
	private Matrix q;
	private UMatrix r;
	
	public static QR QRDecomposition(Matrix m) throws QRException {
		return HouseholderQRDecomposition(m);
	}
	
	public static QR HouseholderQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		QR qr = new QR(row, col);
		
		return qr;
	}

	public static QR GivensQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		QR qr = new QR(row, col);

		return qr;
	}

	public static QR SchmidtQRDecomposition(Matrix m) throws QRException {
		int row = m.getRowSize();
		int col = m.getColumnSize();
		QR qr = new QR(row, col);
		for (int i = 0; i < col; i++) {
			Vector v = new Vector(row);
			for (int k = 0; k < row; k++) {
				v.set(k, m.get(k, i));
			}

			for (int j = 0; j < i; j++) {
				double inner = 0;
				for (int k = 0; k < row; k++) {
					inner += qr.q.get(k, j) * v.get(k);
				}
				qr.r.set(j, i, inner);
				for (int k = 0; k < row; k++) {
					v.set(k, v.get(k) - inner * qr.q.get(k, j));
				}
			}

			double length = v.length();
			qr.r.set(i, i, length);
			for (int k = 0; k < row; k++) {
				qr.q.set(k, i, v.get(k) / length);
			}
		}
		return qr;
	}

	private QR(int m, int n) {
		this.q = new Matrix(n, n);
		this.r = new UMatrix(m, n);
	}
	
	public Matrix getQ() {
		return this.q;
	}

	public UMatrix getR() {
		return this.r;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No Input File Of Matrix Given");
			return;
		}
		Matrix m = Matrix.loadMatrix(args[0]);	
		m.print();
		try {
			QR qr = QR.SchmidtQRDecomposition(m);
			qr.getQ().print();
			qr.getR().print();
		} catch (QRException ex) {

		}
	}
}
