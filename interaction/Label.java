package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * Class to define a label.
 * 
 * @author Lumme
 * @version 0.1
 * @since 03-07-2018
 */

public class Label extends Interactable implements Textable {
	private String text = "";
	private int textSize = Settings.DEFAULT_TEXT_SIZE;
	private Color txtc = Settings.DEFAULT_TXTC_COLOR;
	private float lineSpacing = Settings.LABEL_LINE_SPACING;
	private int horAlign = Settings.LABEL_HOR_ALIGN;
	private int vertAlign = Settings.LABEL_VERT_ALIGN;
	private PFont font;

	/**
	 * Constructor. The inputs x and y are assigned to its super constructor. The
	 * input text is assigned to the variable. The variable p is set to the public
	 * variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */

	public Label(int x, int y, PApplet p, String text, PFont font) {
		super(x, y, 0, 0, p);
		this.text = text;
		this.font = font;
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws the
	 * text in the text variable with the PFont object as the font, textSize as the
	 * text size and the default text colour from the Settings class as the colour.
	 * The text is centred both vertically and horizontally.
	 */

	@Override
	public void internalDraw() {
		if (isDrawn()) {
			getP().textFont(font);
			getP().textSize(textSize);
			getP().textAlign(horAlign, vertAlign);
			if (lineSpacing != -1) {
				getP().textLeading(lineSpacing);
			}
			getP().fill(txtc);
			getP().text(text, getX(), getY());
		}
	}

	// GETTERS AND SETTERS

	/**
	 * @return String
	 */

	@Override
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */

	@Override
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return int
	 */

	@Override
	public int getTextSize() {
		return textSize;
	}

	/**
	 * @param textSize
	 */

	@Override
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getTextColor() {
		return txtc;
	}

	/**
	 * @param textColor
	 */

	@Override
	public void setTextColor(Color txtc) {
		this.txtc = txtc;
	}

	/**
	 * @return float
	 */

	public float getLineSpacing() {
		return lineSpacing;
	}

	/**
	 * @param lineSpacing
	 */

	public void setLineSpacing(float lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	/**
	 * @return int
	 */

	public int getHorAlign() {
		return horAlign;
	}

	/**
	 * @param horAlign
	 */

	public void setHorAlign(int horAlign) {
		this.horAlign = horAlign;
	}

	/**
	 * @return int
	 */

	public int getVertAlign() {
		return vertAlign;
	}

	/**
	 * @param vertAlign
	 */

	public void setVertAlign(int vertAlign) {
		this.vertAlign = vertAlign;
	}

	@Override
	public PFont getFont() {
		return font;
	}

	@Override
	public void setFont(PFont font) {
		this.font = font;
	}
}
