package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import lumCode.utils.ExMath;
import processing.core.PApplet;

public class Slider extends Interactable implements Lockable, OverColorable {
	private double minValue = 0.0;
	private double maxValue = 10.0;
	private double value = 0.0;

	private boolean locking = false;
	private boolean drawLines = true;
	private int tallLines = 5;
	private boolean vertical = false;

	private boolean locked = false;
	private boolean disabled = false;

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over = Settings.DEFAULT_OVER_COLOR;
	private Color back = Settings.DEFAULT_BACK_COLOR;
	private Color fore = Settings.DEFAULT_FORE_COLOR;
	private Color darkLine = Settings.DEFAULT_DARK_LINE_COLOR;
	private Color darkBack = Settings.DEFAULT_DARK_BACK_COLOR;
	private Color darkFore = Settings.DEFAULT_DARK_FORE_COLOR;

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The variable p is set to the public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */

	public Slider(int x, int y, int w, int h, PApplet p) {
		super(x, y, w, h, p);
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws a
	 * rectangle using the x, y, w and h variables from the super object. It draws
	 * the slider with the line and back colors, and the slider button with line,
	 * over and fore colors.
	 */

	@Override
	public void internalDraw() {
		if (isDrawn()) {
			if (!locked && !disabled) {
				getP().stroke(line);
				getP().fill(back.getR(), back.getG(), back.getB(), (float) (back.getA() * getTransparency()));
			} else {
				getP().stroke(darkLine);
				getP().fill(darkBack);
			}

			getP().rect(getX(), getY(), getW(), getH());

			// Lines
			if (drawLines == true) {
				for (int i = 0; i < Math.abs(minValue) + Math.abs(maxValue); i++) {
					if (vertical == false) {
						int xCalc = (int) (getW() / (Math.abs(minValue) + Math.abs(maxValue)) * i + getX());

						if (i % tallLines == 0) {
							getP().line(xCalc, getY() + getH(), xCalc, getY() + (getH() / 2));
						} else {
							getP().line(xCalc, getY() + getH(), xCalc, getY() + (getH() * 3 / 4));
						}
					} else {
						int yCalc = (int) (getH() / (Math.abs(minValue) + Math.abs(maxValue)) * i + getY());

						if (i % tallLines == 0) {
							getP().line(getX() + getW(), yCalc, getX() + (getW() / 2), yCalc);
						} else {
							getP().line(getX() + getW(), yCalc, getX() + (getW() * 3 / 4), yCalc);
						}
					}
				}
			}

			// Button
			if (!locked && !disabled) {
				if (isMouseOver()) {
					getP().fill(over);
				} else {
					getP().fill(fore);
				}
			} else {
				getP().fill(darkFore);
			}

			if (vertical == false) {
				double calc2 = getW() / (Math.abs(minValue) + Math.abs(maxValue)) * (value + Math.abs(minValue))
						+ getX();
				getP().rect((float) calc2 - 5, getY(), 10, getH());
			} else {
				double calc2 = getH() / (Math.abs(minValue) + Math.abs(maxValue)) * (value + Math.abs(minValue))
						+ getY();
				getP().rect(getX(), (float) calc2 - 5, getW(), 10);
			}

			// Check mouse
			if (getP().mousePressed) {
				mouseClicked();
			}
		}
	}

	// ON UPDATE

	/**
	 * Called when updating the slider. Override when constructing to use.
	 */

	public void onUpdate() {
	}

	// INTERACTIONS

	/**
	 * Method for behaviour when mouse button is clicked.
	 */

	@Override
	public void mouseClicked() {
		if (isMouseOver() && !locked && !disabled) {
			float input, low, high;

			if (vertical == false) {
				input = getP().mouseX;
				low = getX();
				high = getX() + getW();
			} else {
				input = getP().mouseY;
				low = getY();
				high = getY() + getH();
			}

			value = ExMath.map(input, low, high, minValue, maxValue);

			if (locking == true) {
				value = Math.round(value);
			}

			onUpdate();
		}
	}

	// GETTERS AND SETTERS

	/**
	 * @return double
	 */

	public double getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 */

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return double
	 */

	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 */

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return double
	 */

	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 */

	public void setValue(double value) {
		this.value = ExMath.clamp(value, minValue, maxValue);
	}

	/**
	 * @return Color
	 */

	public Color getNormal() {
		return fore;
	}

	/**
	 * @param normal
	 */

	public void setNormal(Color normal) {
		this.fore = normal;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getOver() {
		return over;
	}

	/**
	 * @param over
	 */

	@Override
	public void setOver(Color over) {
		this.over = over;
	}

	@Override
	public Color getLine() {
		return line;
	}

	/**
	 * @param line
	 */

	@Override
	public void setLine(Color line) {
		this.line = line;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getBack() {
		return back;
	}

	/**
	 * @param back
	 */

	@Override
	public void setBack(Color back) {
		this.back = back;
	}

	public Color getFore() {
		return fore;
	}

	public void setFore(Color fore) {
		this.fore = fore;
	}

	@Override
	public Color getDarkLine() {
		return darkLine;
	}

	@Override
	public void setDarkLine(Color darkLine) {
		this.darkLine = darkLine;
	}

	@Override
	public Color getDarkBack() {
		return darkBack;
	}

	@Override
	public void setDarkBack(Color darkBack) {
		this.darkBack = darkBack;
	}

	public Color getDarkFore() {
		return darkFore;
	}

	public void setDarkFore(Color darkFore) {
		this.darkFore = darkFore;
	}

	/**
	 * @return boolean
	 */

	public boolean isLocking() {
		return locking;
	}

	/**
	 * @param locking
	 */

	public void setLocking(boolean locking) {
		this.locking = locking;
	}

	/**
	 * @return boolean
	 */

	public boolean isDrawLines() {
		return drawLines;
	}

	/**
	 * @param drawLines
	 */

	public void setDrawLines(boolean drawLines) {
		this.drawLines = drawLines;
	}

	/**
	 * @return int
	 */

	public int getTallLines() {
		return tallLines;
	}

	/**
	 * @param tallLines
	 */

	public void setTallLines(int tallLines) {
		this.tallLines = tallLines;
	}

	/**
	 * @return boolean
	 */

	public boolean isVertical() {
		return vertical;
	}

	/**
	 * @param vertical
	 */

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 */

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 */

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
