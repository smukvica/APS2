import java.util.Scanner;


class Matrix {


	private int[][] m;

	public int n; //only square matrices

	public Matrix(int n){

		this.n = n;

		m = new int[n][n];

	}


    //set value at i,j
	public void setV(int i, int j, int val){

		m[i][j] = val;

	}


    // get value at index i,j
	public int v(int i, int j){

		return m[i][j];

	}


    // return a square submatrix from this
	public Matrix getSubmatrix(int startRow, int startCol, int dim){

		Matrix subM = new Matrix(dim);

		for (int i = 0; i<dim ; i++ )

			for (int j=0;j<dim ; j++ )

				subM.setV(i,j, m[startRow+i][startCol+j]);



		return subM;

	}


    // write this matrix as a submatrix from b (useful for the result of multiplication)
	public void putSubmatrix(int startRow, int startCol, Matrix b){

		for (int i = 0; i<b.n ; i++ )

			for (int j=0;j<b.n ; j++ )

				setV(startRow+i,startCol+j, b.v(i,j));

	}


    // matrix addition
	public Matrix sum(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]+b.v(i, j));

			}

		}

		return c;

	}





    // matrix subtraction
	public Matrix sub(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]-b.v(i, j));

			}

		}

		return c;

	}



	//simple multiplication
	public Matrix mult(Matrix b){
		Matrix r = new Matrix(this.n);
		for(int i = 0; i < this.n; i++){
			for(int j = 0; j < this.n; j++){
				int x = 0;
				for(int k = 0; k < this.n; k++){
					x += this.v(i, k) * b.v(k, j);
				}
				r.setV(i, j, x);
			}
		}
		return r;
	}

	private int sumMatrix(Matrix a){
		int x = 0;

		for(int i = 0; i < a.n; i++){
			for(int k = 0; k < a.n; k++){
				x += a.v(i, k);
			}
		}
		return x;
	}

    // Strassen multiplication
	public Matrix multStrassen(Matrix b, int cutoff){
        if(this.n == cutoff){
			Matrix r = this.mult(b);
			return r;
		}else{
			Matrix a11 = this.getSubmatrix(0, 0, this.n/2);
			Matrix a12 = this.getSubmatrix(0, this.n/2 , this.n/2);
			Matrix a21 = this.getSubmatrix(this.n/2, 0, this.n/2);
			Matrix a22 = this.getSubmatrix(this.n/2, this.n/2, this.n/2);

			Matrix b11 = b.getSubmatrix(0, 0, this.n/2);
			Matrix b12 = b.getSubmatrix(0, this.n/2 , this.n/2);
			Matrix b21 = b.getSubmatrix(this.n/2, 0, this.n/2);
			Matrix b22 = b.getSubmatrix(this.n/2, this.n/2, this.n/2);

			Matrix m11 = a11.sum(a22);
			Matrix m12 = b11.sum(b22);
			Matrix m1 = m11.multStrassen(m12, cutoff);

			Matrix m21 = a21.sum(a22);
			Matrix m2 = m21.multStrassen(b11, cutoff);

			Matrix m3 = a11.multStrassen(b12.sub(b22), cutoff);

			Matrix m4 = a22.multStrassen(b21.sub(b11), cutoff);

			Matrix m51 = a11.sum(a12);
			Matrix m5 = m51.multStrassen(b22, cutoff);

			Matrix m61 = a21.sub(a11);
			Matrix m62 = b11.sum(b12);
			Matrix m6 = m61.multStrassen(m62, cutoff);

			Matrix m71 = a12.sub(a22);
			Matrix m72 = b21.sum(b22);
			Matrix m7 = m71.multStrassen(m72, cutoff);

			Matrix t1 = m1.sum(m4);
			Matrix t2 = t1.sub(m5);
			Matrix c11 = m1.sum(m4).sub(m5).sum(m7);

			Matrix c12 = m3.sum(m5);

			Matrix c21 = m2.sum(m4);

			t1 = m1.sub(m2);
			t2 = t1.sum(m3);
			Matrix c22 = m1.sub(m2).sum(m3).sum(m6);

			Matrix c = new Matrix(this.n);
			c.putSubmatrix(0, 0, c11);
			c.putSubmatrix(0, this.n/2, c12);
			c.putSubmatrix(this.n/2, 0, c21);
			c.putSubmatrix(this.n/2, this.n/2, c22);

			System.out.printf("m1: %d \n", sumMatrix(m1));
			System.out.printf("m2: %d \n", sumMatrix(m2));
			System.out.printf("m3: %d \n", sumMatrix(m3));
			System.out.printf("m4: %d \n", sumMatrix(m4));
			System.out.printf("m5: %d \n", sumMatrix(m5));
			System.out.printf("m6: %d \n", sumMatrix(m6));
			System.out.printf("m7: %d \n", sumMatrix(m7));

			return c;
		}
	}


}




public class Izziv6{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int dimenzije = sc.nextInt();
		int mejnaVrednost = sc.nextInt();

		Matrix a = new Matrix(dimenzije);
		Matrix b = new Matrix(dimenzije);

		for(int i = 0; i < dimenzije; i++){
		    for(int k = 0; k < dimenzije; k++){
		        a.setV(i, k, sc.nextInt());
		    }
		}

        for(int i = 0; i < dimenzije; i++){
		    for(int k = 0; k < dimenzije; k++){
		        b.setV(i, k, sc.nextInt());
		    }
		}
		Matrix c = a.multStrassen(b, mejnaVrednost);

		for(int i = 0; i < dimenzije; i++){
			for(int k = 0; k < dimenzije; k++){
				System.out.printf("%d ", c.v(i, k));
			}
			System.out.printf("\n");
		}
	}



}
