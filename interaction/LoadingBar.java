package consoleCommander.interaction;

import consoleCommander.core.DrawingHelper;
import consoleCommander.core.Settings;
import consoleCommander.entities.Area;
import consoleCommander.entities.Color;
import consoleCommander.interaction.enums.Direction;
import lumCode.utils.ExMath;
import processing.core.PApplet;

public class LoadingBar extends Interactable implements MultiOverColorable {

	private Color line = Settings.DEFAULT_LINE_COLOR;
	private Color over[] = { Settings.DEFAULT_OVER_COLOR };
	private Color back = Settings.DEFAULT_BACK_COLOR;
	private Color sep = Settings.DEFAULT_LINE_COLOR;
	private Direction direction;
	private double progress;
	private int separators;

	public LoadingBar(int x, int y, int w, int h, PApplet p, Direction direction) {
		super(x, y, w, h, p);
		this.direction = direction;
		progress = 0.0;
		separators = 0;
	}

	@Override
	public void internalDraw() {
		getP().fill(back);
		getP().noStroke();
		getP().rect(getX(), getY(), getW(), getH());

		Area full = new Area(getX(), getY(), getW(), getH(), -1);
		switch (direction) {
		case DOWN:
			full.setH((int) ExMath.map(progress, 0.0, 1.0, 0.0, getH()));
			break;
		case LEFT:
			full.setX((int) (getX() + getW() - ExMath.map(progress, 0.0, 1.0, 0.0, getW())) + 1);
			full.setW((int) ExMath.map(progress, 0.0, 1.0, 0.0, getW()));
			break;
		case RIGHT:
			full.setW((int) ExMath.map(progress, 0.0, 1.0, 0.0, getW()));
			break;
		case UP:
			full.setY((int) (getY() + getH() - ExMath.map(progress, 0.0, 1.0, 0.0, getH())) + 1);
			full.setH((int) ExMath.map(progress, 0.0, 1.0, 0.0, getH()));
			break;
		}
		getP().fill((over.length > 1 ? calcBarColor() : over[0]));
		getP().rect(full.getX(), full.getY(), full.getW(), full.getH());

		getP().noFill();
		if (separators > 0) {
			getP().stroke(sep);
			if (direction == Direction.DOWN || direction == Direction.UP) {
				for (int i = 0; i < separators; i++) {
					float l = (getH() / (separators + 1.0f)) * (i + 1.0f);
					getP().line(getX(), getY() + l, getX() + getW(), getY() + l);
				}
			} else {
				for (int i = 0; i < separators; i++) {
					float l = (getW() / (separators + 1.0f)) * (i + 1.0f);
					getP().line(getX() + l, getY(), getX() + l, getY() + getH());
				}
			}
		}

		getP().stroke(line);
		getP().rect(getX(), getY(), getW(), getH());
	}

	@Override
	public Color getLine() {
		return line;
	}

	@Override
	public void setLine(Color line) {
		this.line = line;
	}

	public Color calcBarColor() {
		double colPart = 1.0 / (over.length - 1);

		double m = progress % colPart;
		int d = (int) Math.floor(progress / colPart);

		Color a = over[d];
		Color b = a;
		if (d < over.length - 1) {
			b = over[d + 1];
		}

		double pro = (over.length - 1) * m;

		Color out = Color.mix(a, b, pro);
		return out;
	}

	@Override
	public Color[] getOver() {
		return over;
	}

	@Override
	public void setOver(Color[] over) {
		this.over = over;
	}

	@Override
	public Color getBack() {
		return back;
	}

	@Override
	public void setBack(Color back) {
		this.back = back;
	}

	public Color getSep() {
		return sep;
	}

	public void setSep(Color sep) {
		this.sep = sep;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = ExMath.clamp(progress, 0.000, 1.000);
	}

	public int getSeparators() {
		return separators;
	}

	public void setSeparators(int separators) {
		this.separators = separators;
	}
}
