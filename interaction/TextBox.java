package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Color;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * Class to define a text box that may or may not be editable.
 * 
 * @author Lumme
 * @version 0.1
 * @since 19-08-2018
 */

public class TextBox extends Interactable implements LockTextable, OverColorable {
	private String text;
	private int textSize = Settings.DEFAULT_TEXT_SIZE;
	private PFont font;

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over = Settings.DEFAULT_OVER_COLOR;
	private Color back = Settings.DEFAULT_BACK_COLOR;
	private Color txtc = Settings.DEFAULT_TXTC_COLOR;
	private Color darkTxtc = Settings.DEFAULT_DARK_TXTC_COLOR;
	private Color darkBack = Settings.DEFAULT_DARK_BACK_COLOR;
	private Color darkLine = Settings.DEFAULT_DARK_LINE_COLOR;

	private float lineSpacing = Settings.TEXTBOX_LINE_SPACING;
	private int horAlign = Settings.TEXTBOX_HOR_ALIGN;
	private int vertAlign = Settings.TEXTBOX_VERT_ALIGN;
	private int frameSpace = Settings.TEXTBOX_FRAME_SPACE;

	private boolean editable = true;
	private boolean alwaysType = false;
	private boolean numbersOnly = false;
	private boolean singleLine = false;
	private int limit = -1;

	private boolean locked = false;
	private boolean disabled = false;

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The variable p is set to the public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */

	public TextBox(int x, int y, int w, int h, PApplet p, PFont font) {
		super(x, y, w, h, p);
		this.font = font;
		text = "";
	}

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The variable editable is assigned to the variable. The variable p is set to
	 * the public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param editable
	 */

	public TextBox(int x, int y, int w, int h, PApplet p, boolean editable, PFont font) {
		super(x, y, w, h, p);
		this.editable = editable;
		this.font = font;
		text = "";
	}

	/**
	 * Constructor. The inputs x, y, w and h are assigned to the super constructor.
	 * The variables editable and alwaysType is assigned directly. The variable p is
	 * set to the public variable instance in the Main class.
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param editable
	 * @param alwaysType
	 */

	public TextBox(int x, int y, int w, int h, PApplet p, boolean editable, boolean alwaysType, PFont font) {
		super(x, y, w, h, p);
		this.editable = editable;
		this.alwaysType = alwaysType;
		this.font = font;
		text = "";
	}

	// DRAW

	/**
	 * Draws interactable when the super class variable active is true. It draws a
	 * rectangle using the x, y, w and h variables from the super object. It is uses
	 * the default colors from the Settings class for outline, mouse over, and
	 * background. If the editable variable is false, it does not change color on
	 * mouse over. It draws the text in the text variable with mainFont from the
	 * Main class as the font, textSize as the text size and the default text color
	 * from the Settings class as the color. The text is centered both vertically
	 * and horizontally.
	 */

	@Override
	public void internalDraw() {
		if (isDrawn()) {
			if (!locked && !disabled) {
				getP().stroke(line);
				if (isMouseOver() && editable) {
					getP().fill(over);
				} else {
					getP().fill(back.getR(), back.getG(), back.getB(), (float) (back.getA() * getTransparency()));
				}
			} else {
				getP().stroke(darkLine);
				getP().fill(darkBack);
			}

			getP().rect(getX(), getY(), getW(), getH());

			if (text != null && !text.equals("")) {
				getP().textFont(font);
				getP().textSize(textSize);
				getP().textLeading(lineSpacing);
				getP().textAlign(horAlign, vertAlign);
				if (!locked && !disabled) {
					getP().fill(txtc);
				} else {
					getP().fill(darkTxtc);
				}

				if (singleLine) {
					getP().text(text, getX() + (getW() / 2), getY() + (getH() / 2) - 1);
				} else {
					getP().text(text, getX() + frameSpace, getY() + frameSpace - 1, getW() - (2 * frameSpace),
							getH() - (2 * frameSpace));
				}
			}
		}
	}

	// ON UPDATE

	/**
	 * Called when updating the text box. Override when constructing to use.
	 */

	public void onUpdate() {
	}

	// INTERACTIONS

	/**
	 * This method removes the last character from the text variable only if the
	 * isMouseOver method is true, the editable variable is true, the isActive
	 * method is true and the key being pressed down is backspace.
	 */

	@Override
	public void keyPressed() {
		if ((isMouseOver() || alwaysType) && editable && isDrawn() && !locked && !disabled) {
			if (getP().key == PConstants.BACKSPACE) {
				if (text.length() > 0) {
					text = text.substring(0, text.length() - 1);
					onUpdate();
				}
			}
		}
	}

	/**
	 * This method adds the typed character to the text variable only if the
	 * isMouseOver method is true, the editable variable is true, the isActive
	 * method is true and the ascii value of the key being pressed down is between
	 * 31 and 127, both exclusive. The values approved are letters from the English
	 * alphabet, the Arabic numbers and most normal symbols.
	 */

	@Override
	public void keyTyped() {
		if ((isMouseOver() || alwaysType) && editable && isDrawn() && !locked && !disabled) {
			if (getP().key > 31 && getP().key < 127) {
				if (limit == -1 || text.length() < limit) {
					text += getP().key;
					if (numbersOnly) {
						text = text.replaceAll("[^0-9]", "");

						while (text.length() > 1) {
							if (text.charAt(0) == '0') {
								text = text.substring(1, text.length());
							} else {
								break;
							}
						}
					} else if (text.length() == 1) {
						text = text.toUpperCase();
					}

					onUpdate();
				}
			}
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
		onUpdate();
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
	 * @return boolean
	 */

	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable
	 */

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return boolean
	 */

	public boolean isAlwaysType() {
		return alwaysType;
	}

	/**
	 * @param alwaysType
	 */

	public void setAlwaysType(boolean alwaysType) {
		this.alwaysType = alwaysType;
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

	/**
	 * @return boolean
	 */

	public boolean isNumbersOnly() {
		return numbersOnly;
	}

	/**
	 * @param numbers
	 */

	public void setNumbersOnly(boolean numbersOnly) {
		this.numbersOnly = numbersOnly;
	}

	/**
	 * @return Color
	 */

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

	/**
	 * @return Color
	 */

	@Override
	public Color getTextColor() {
		return txtc;
	}

	/**
	 * @param txtc
	 */

	@Override
	public void setTextColor(Color txtc) {
		this.txtc = txtc;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkTextColor() {
		return darkTxtc;
	}

	/**
	 * @param darkTxtc
	 */

	@Override
	public void setDarkTextColor(Color darkTxtc) {
		this.darkTxtc = darkTxtc;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkBack() {
		return darkBack;
	}

	/**
	 * @param darkBack
	 */

	@Override
	public void setDarkBack(Color darkBack) {
		this.darkBack = darkBack;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getDarkLine() {
		return darkLine;
	}

	/**
	 * @param darkLine
	 */

	@Override
	public void setDarkLine(Color darkLine) {
		this.darkLine = darkLine;
	}

	/**
	 * @return int
	 */

	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 */

	public void setLimit(int limit) {
		this.limit = limit;
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

	/**
	 * @return boolean
	 */

	public boolean isSingleLine() {
		return singleLine;
	}

	/**
	 * @param singleLine
	 */

	public void setSingleLine(boolean singleLine) {
		this.singleLine = singleLine;
	}

	/**
	 * @return PFont
	 */

	@Override
	public PFont getFont() {
		return font;
	}

	/**
	 * @param font
	 */

	@Override
	public void setFont(PFont font) {
		this.font = font;
	}
}
