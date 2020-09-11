package consoleCommander.interaction;

import processing.core.PApplet;

/**
 * Abstract class to define basic functionality of interactable objects.
 * 
 * @author Lumme
 * @version 0.1
 * @since 26-05-2018
 */

public abstract class Interactable {
	private PApplet p;
	private int x, y, w, h;
	private boolean drawn = true;
	private double transparency = 1.0;

	/**
	 * Protected constructor. The inputs are assigned to the x, y, w and h
	 * variables.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param p
	 */

	protected Interactable(int x, int y, int w, int h, PApplet p) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.p = p;
		this.drawn = true;
	}

	// IS MOUSE OVER

	/**
	 * This method returns true if the mouse is inside the boundary that its x, y, w
	 * and h variables create, otherwise it returns false.
	 * 
	 * @return boolean This is the result of the boundary test.
	 */

	public boolean isMouseOver() {
		boolean over = false;

		if (p.mouseX > x && p.mouseX < (x + w) && p.mouseY > y && p.mouseY < (y + h)) {
			over = true;
		}
		return over;
	}

	// ABSTRACT METHODS

	/**
	 * This abstract method is for drawing the interactable.
	 */

	public void draw() {
		if (drawn) {
			internalDraw();
		}
	}

	/**
	 * This abstract method is for drawing the interactable.
	 */

	protected abstract void internalDraw();

	// INTERACTION METHODS

	/**
	 * This method is for when the mouse is clicked. Some interactables may not use
	 * it, which is why it is defined, but contains no logic.
	 */

	public void mouseClicked() {
		// Normally; Do nothing
	}

	/**
	 * This method is for when a key is pressed. Some interactables may not use it,
	 * which is why it is defined, but contains no logic.
	 */

	public void keyPressed() {
		// Normally; Do nothing
	}

	/**
	 * This method is for when a key is typed. Some interactables may not use it,
	 * which is why it is defined, but contains no logic.
	 */

	public void keyTyped() {
		// Normally; Do nothing
	}

	// GETTERS AND SETTERS

	/**
	 * @return int
	 */

	public int getX() {
		return x;
	}

	/**
	 * @param x
	 */

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return int
	 */

	public int getY() {
		return y;
	}

	/**
	 * @param y
	 */

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return int
	 */

	public int getW() {
		return w;
	}

	/**
	 * @param w
	 */

	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return int
	 */

	public int getH() {
		return h;
	}

	/**
	 * @param h
	 */

	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return boolean
	 */

	public boolean isDrawn() {
		return drawn;
	}

	/**
	 * @param drawn
	 */

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}

	/**
	 * @return PApplet
	 */

	public PApplet getP() {
		return p;
	}

	/**
	 * @param p
	 */

	public void setP(PApplet p) {
		this.p = p;
	}

	/**
	 * @return double
	 */

	public double getTransparency() {
		return transparency;
	}

	/**
	 * @param transparency
	 */

	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}

}
