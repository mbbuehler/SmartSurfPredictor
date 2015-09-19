package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SSPPanel extends JPanel {

	public SSPPanel(GridLayout gridLayout) {
		super(gridLayout);
	}

	public SSPPanel(FlowLayout flowLayout) {
		super(flowLayout);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {

			Image bgImage = ImageIO.read(getClass().getResource(
					"/images/bgImage_small.jpeg"));
			g.drawImage(bgImage, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
