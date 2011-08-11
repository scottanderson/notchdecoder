package org.scotta.notchdecoder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.BitSet;

@SuppressWarnings("serial")
public class BitSetGraphic extends Component {
	private static final int SCALE = 3;

	private final int mHeight;
	private final int mWidth;
	private final BitSet mBits;

	public BitSetGraphic(int height, int width, BitSet bits) {
		mHeight = height;
		mWidth = width;
		mBits = bits;
	}

	@Override
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	public Dimension getMaximumSize() {
		return getMinimumSize();
	}

	@Override
	public int getHeight() {
		return mHeight * SCALE;
	}

	@Override
	public int getWidth() {
		return mWidth * SCALE;
	}

	@Override
	public void paint(Graphics g) {
		int i = 0;
		for(int y = 0; y < mHeight; y++) {
			for(int x = 0; x < mWidth; x++) {
				g.setColor(mBits.get(i++) ? Color.BLACK : Color.WHITE);
				g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
			}
		}
	}
}
