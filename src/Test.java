
public class Test {
	public static void main(String[] args) {
		testComplexAdd();
		testComplexMultiply();
		testComplexExponentiation();
		testComplexEquals();
	}
	
	public static void testComplexAdd() {
		Complex num1 = new Complex(3,4);
		Complex num2 = new Complex(2,0);
		Complex sum = num1.add(num2);
		assert(num1.getReal() == 3);
		assert(num1.getImaginary() == 4);
		assert(num2.getReal() == 2);
		assert(num2.getImaginary() == 0);
		assert(sum.getReal() == 5);
		assert(sum.getImaginary() == 4);
		System.out.println("finished testComplexAdd");
	}
	
	public static void testComplexMultiply() {
		Complex num1 = new Complex(3,4);
		Complex num2 = new Complex(2,0);
		Complex product = num1.multiply(num2);
		assert(num1.getReal() == 3);
		assert(num1.getImaginary() == 4);
		assert(num2.getReal() == 2);
		assert(num2.getImaginary() == 0);
		assert(product.getReal() == 6);
		assert(product.getImaginary() == 8);
		System.out.println("finished testComplexMultiply");
	}
	
	public static void testComplexEquals() {
		Complex num1 = new Complex(3,4);
		Complex num2 = new Complex(3,4);
		Complex num3 = new Complex(2,0);
		assert(num1.equals(num2));
		assert(!num1.equals(num3));
		assert(num2.getReal() != 2);
		assert(num2.getImaginary() != 0);
		System.out.println("finished testComplexEquals");
	}
	
	public static void testComplexExponentiation() {
		Complex num1 = new Complex(3,4);
		Complex num2 = new Complex(2,0);
		Complex result = num1.exp(2);
		assert(result.equals(num1.multiply(num1)));
		System.out.println("real: " + result.getReal() + "imaginary: " + result.getImaginary());
		assert(result.getReal() == -7);
		assert(result.getImaginary() == 24);
		result = num1.exp(3);
		assert(result.getReal() == -117);
		assert(result.getImaginary() == 44);
		assert(num1.getImaginary() == 4);
		assert(num2.getReal() == 2);
		assert(num2.getImaginary() == 0);
		System.out.println("finished testComplexExp");
	}
}
