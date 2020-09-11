package consoleCommander.entities;

import lumCode.utils.ExMath;

public class Color {
	private int r, g, b, a;

	public Color(int grey) {
		r = grey;
		g = grey;
		b = grey;
		a = 255;
	}

	public Color(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		a = 255;
	}

	public Color(int grey, int a) {
		r = grey;
		g = grey;
		b = grey;
		this.a = a;
	}

	public Color(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	// UTILITIES

	public Color getMultiplied(double scalar) {
		return new Color((int) (r * scalar), (int) (g * scalar), (int) (b * scalar), a);
	}

	public Color copy() {
		return new Color(r, g, b, a);
	}

	public static Color mix(Color c1, Color c2, double percentage) {
		int r = (int) ((c1.getR() * (1.0 - percentage)) + (c2.getR() * percentage));
		int g = (int) ((c1.getG() * (1.0 - percentage)) + (c2.getG() * percentage));
		int b = (int) ((c1.getB() * (1.0 - percentage)) + (c2.getB() * percentage));
		int a = (int) ((c1.getA() * (1.0 - percentage)) + (c2.getA() * percentage));
		return new Color(r, g, b, a);
	}

	// GETTERS AND SETTERS

	public int getR() {
		return ExMath.clamp(r, 0, 255);
	}

	public int getTrueR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return ExMath.clamp(g, 0, 255);
	}

	public int getTrueG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return ExMath.clamp(b, 0, 255);
	}

	public int getTrueB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getA() {
		return ExMath.clamp(a, 0, 255);
	}

	public int getTrueA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}
