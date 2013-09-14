import java.io.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.*;

public class julia extends GraphicsProgram{
	private GLabel info;
	private GLabel info2;
	private GLabel button;
	public GRect rect = new GRect(0,PIXEL_HEIGHT-80,275,100);
	public GRect buttonBackground = new GRect(PIXEL_WIDTH-BUTTON_WIDTH,PIXEL_HEIGHT-BUTTON_HEIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
	public GRect zoomButton = new GRect(zoomButtonX,zoomButtonY,BUTTON_WIDTH,BUTTON_HEIGHT);
	private float julia_centerX;
	private float julia_centerY;
	public static final int THRESHOLD = 25;
	public static final int NUM_ITERATIONS = 200;
	public static float scaleX;
	public static float scaleY;
	public static final int PIXEL_WIDTH = 800;
	public static final int PIXEL_HEIGHT = 600;
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 60;
	public static final int zoomButtonX = PIXEL_WIDTH - 2*BUTTON_WIDTH;
	public static final int zoomButtonY = PIXEL_HEIGHT - BUTTON_HEIGHT;
	public static int pixel_centerX;
	public static int pixel_centerY;
	public static int exponent;
	public static Complex c = new Complex(-0.4, 0.6);
	public void run() {
		this.setSize(new Dimension(PIXEL_WIDTH, PIXEL_HEIGHT));
		addMouseListeners();
		reset();
		info = new GLabel(" ",20,PIXEL_HEIGHT - 60);
		info2 = new GLabel(" ",20,PIXEL_HEIGHT - 40);
		button = new GLabel("new function?",PIXEL_WIDTH - BUTTON_WIDTH + 10,PIXEL_HEIGHT - BUTTON_HEIGHT/2);
		rect.setFillColor(Color.white);
		rect.setFilled(true);
		add(rect);
		buttonBackground.setFillColor(Color.cyan);
		buttonBackground.setFilled(true);
		zoomButton.setFillColor(Color.BLUE);
		zoomButton.setFilled(true);
		add(zoomButton);
		add(buttonBackground);
		add(info);
		add(info2);
		add(button);
		getNewFunction();
		//createJulia(scaleX, scaleY, pixel_centerX, pixel_centerY, 0f, 0f, c,2);
	}
	
	public BufferedImage image() {
		BufferedImage image = new BufferedImage(PIXEL_WIDTH, PIXEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		return image;
	}
	
	public void reset() {
		scaleX = PIXEL_WIDTH / 2;
		scaleY = PIXEL_HEIGHT / 2;
		pixel_centerX = PIXEL_WIDTH / 2;
		pixel_centerY = PIXEL_HEIGHT / 2;
	}
	
	public void createJulia(float scaleX, float scaleY, int pixel_centerX, int pixel_centerY, float julia_centerX, float julia_centerY, Complex constant, int exponent) {
		BufferedImage image = image();
		int iter = 0;
		for (int pixelY = 0; pixelY < PIXEL_HEIGHT; pixelY++) {
			for (int pixelX = 0; pixelX < PIXEL_WIDTH; pixelX++) {
				float xComplexCoord = convertXToJulia(pixelX);
				float yComplexCoord = convertYToJulia(pixelY);
				Complex number = new Complex(xComplexCoord, yComplexCoord);
				for (iter = 0; iter < NUM_ITERATIONS; iter++) {
					number = number.exp(exponent).add(constant);
					if (number.getMagnitude() > THRESHOLD) {
						break;
					}
				}
				image.setRGB((int) (pixelX), (int) (pixelY), (int) (iter * Math.pow(255, 3) / (NUM_ITERATIONS)));
			}
		}
		
		GImage image1 = new GImage(image);
		add(image1);
		System.out.println("image created");
		add(rect);
		add(buttonBackground);
		add(zoomButton);
		add(info);
		add(info2);
		add(button);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getX() > PIXEL_WIDTH - BUTTON_WIDTH && e.getY() > PIXEL_HEIGHT - BUTTON_HEIGHT) {
			getNewFunction();
		} else if (e.getX() > zoomButtonX && e.getX() < zoomButtonX + BUTTON_WIDTH && e.getY() > zoomButtonY) {
			zoomOut(e.getX(), e.getY());
		} else {
			zoomIn(e.getX(), e.getY());
		}
		//System.out.println("mouse clicked at: " + e.getX() + ", " + e.getY() + " julia x: " + convertXToJulia(e.getX()) + " julia y: " + convertYToJulia(e.getY()));
		this.removeAll();
		createJulia(scaleX, scaleY, pixel_centerX, pixel_centerY, julia_centerX, julia_centerY, c, 2);
	}
	
	public void zoomIn(int x, int y) {
		julia_centerX =  convertXToJulia(x);
		julia_centerY =  convertYToJulia(y);
		System.out.println("zooming in");
		scaleX *= 2f;
		scaleY *= 2f;
		this.removeAll();
		createJulia(scaleX, scaleY, pixel_centerX, pixel_centerY, julia_centerX, julia_centerY, c, 2);
	}

	public void zoomOut(int x, int y) {
		julia_centerX =  convertXToJulia(x);
		julia_centerY =  convertYToJulia(y);
		System.out.println("zooming out");
		scaleX /= 2f;
		scaleY /= 2f;
		this.removeAll();
		createJulia(scaleX, scaleY, pixel_centerX, pixel_centerY, julia_centerX, julia_centerY, c, 2);
	}
	
	public void mouseMoved(MouseEvent e) {
		info.setLabel("mouseX: " + e.getX() + "mouseY: " + e.getY());
		info2.setLabel("julia x: " + convertXToJulia(e.getX()) + " julia y: " + convertYToJulia(e.getY()));
	}
	
	public float convertYToJulia(int mouseY) {
		float juliaY = (PIXEL_HEIGHT - mouseY - pixel_centerY) / scaleY + julia_centerY;
		return juliaY;
	}
	
	public float convertXToJulia(int mouseX) {
		float juliaX = (mouseX - pixel_centerX) / scaleX + julia_centerX;
		return juliaX;
	}
	
	public void getNewFunction() {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
		    BufferedReader br = new BufferedReader(isr);
		    System.out.println("c real part? ");
			String stringReal = br.readLine();
			System.out.println("c imaginary part? ");
			String stringImaginary = br.readLine();
			System.out.println("exponent?");
			String stringExponent = br.readLine();
			float cReal, cImaginary;
			int exponent;
			cReal = Float.parseFloat(stringReal);
			cImaginary = Float.parseFloat(stringImaginary);
			exponent = Integer.parseInt(stringExponent);
			//System.out.println("working - c = " + cReal + " + " + cImaginary + "i");
			c = new Complex(cReal, cImaginary);
			reset();
			createJulia(scaleX, scaleY, pixel_centerX, pixel_centerY, julia_centerX, julia_centerY, c, exponent);
		} catch(IOException e1) {
			System.out.println("I/O Error");
		}
	}
}