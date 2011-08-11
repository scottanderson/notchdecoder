package org.scotta.notchdecoder;
import java.util.BitSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		BitSet bits = BaseImage.getImage();

		// The second image is the left half of the image xor the
		// right half. It's also scaled down to half dimensions
		BitSet bits2 = new BitSet(32*64);
		for(int i = 0; i < 128*128; i += 128*2) {
			BitSet row = bits.get(i, i+63);
			row.xor(bits.get(i+64, i+127));

			for(int k = 0; k < 128; k++)
				bits2.set(i/8 + k, row.get(k*2));
		}

		// The third image is the bottom section of the second,
		// with the header removed, and a different width
		BitSet bits3 = bits2.get(32*6, bits2.size());

		// Pretty pictures
		Box b = new Box(BoxLayout.Y_AXIS);
		b.add(new BitSetGraphic(128, 128, bits));
		b.add(new BitSetGraphic(64, 32, bits2));
		b.add(new BitSetGraphic(8, 265, bits3));

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(b);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
