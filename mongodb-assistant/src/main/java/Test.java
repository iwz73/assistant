public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		 System.err.println(test.isPrime(50));
		 int gcd = test.gcd(10, 4);
		 System.err.println(gcd);
		 System.err.println(test.lcm(10, 4, gcd));
		 int[] iArr = new int[]{69,81,30,38,9,2,47,61,32,79};
		 test.order(iArr);
		 for(int i = 0, size = iArr.length; i < size; ++i) {
		 System.err.println(iArr[i]);
		 }
		printStars();
	}

	private static void printStars() {
		for(int i = 0; i < 7; ++i) {
			printSpace(i);
			printStar(i);
			System.out.println();
		}
	}
	
	private static void printSpace(int lineNum) {
		int spaceAmt = Math.abs(3 - lineNum);
		for(int i = 0; i < spaceAmt; ++i) {
			System.out.print(" ");
		}
	}

	private static void printStar(int lineNum) {
		int middle = 3;
		int totalLine = 6;
		int starAmt;
		if(lineNum <= middle) {
			starAmt = 1 + lineNum * 2;
		} else {
			starAmt = 1 + (totalLine  - lineNum) * 2;
		}
		for(int i = 0; i < starAmt; ++i) {
			System.out.print("*");
		}
	}
	
	private boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		if (num % 2 == 0) {
			return false;
		}
		Double d = Math.sqrt(num);
		int sqrt = d.intValue();
		for (int i = 3; i <= sqrt; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private int gcd(int n, int m) {
		if ((n == 0) || (m == 0)) {
			throw new RuntimeException("Wrong parameter !!!");
		}
		int bigger;
		int smaller;
		if (n > m) {
			bigger = n;
			smaller = m;
		} else {
			bigger = m;
			smaller = n;
		}
		return getGcd(bigger, smaller);
	}

	private int getGcd(int bigger, int smaller) {
		int mod = bigger % smaller;
		if (mod == 0) {
			return smaller;
		}
		return getGcd(smaller, mod);
	}

	private int lcm(int m, int n, int gcd) {
		return m * n / gcd;
	}

	private void factor(int m) {
		if (m == 0) {
			return;
		}
		if ((m == 1) || (m == -1)) {
			System.err.println(m);
			return;
		}
		Double sqrt = Math.sqrt(m);
		int bound = sqrt.intValue();
		for (int i = 2; i <= bound; ++i) {
			if (m % i == 0) {
				System.err.println(i);
			}

		}
	}

	private void order(int[] iArr) {
		int size = iArr.length;
		int temp;
		for (int i = size - 1; i >= 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (iArr[j] > iArr[j + 1]) {
					temp = iArr[j];
					iArr[j] = iArr[j + 1];
					iArr[j + 1] = temp;
				}
			}
		}
	}
}
