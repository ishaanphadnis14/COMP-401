package a7;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener, ChangeListener {
	
	private int width;
	private int height;
	private int xCounter;
	private int yCounter;
	private Picture blankTile;
	private PictureView blankTileView;
	private PictureView[][] pictureView;

	public FramePuzzleWidget(Picture picture, int width, int height) {
		this.width = width;
		this.height = height;

		yCounter = (height - 1);
		xCounter = (width - 1);

		setLayout(new GridLayout(width, height, 1, 1));

		int newWidth = picture.getWidth() / width;
		int newHeight = picture.getHeight() / height;

		pictureView = new PictureView[width][height];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if ((i == width - 1) && (j == height - 1)) {

					Pixel[][] clone_array = new Pixel[newWidth][newHeight];
					for (int x = 0; x < newWidth; x++) {
						for (int y = 0; y < newHeight; y++) {
							clone_array[x][y] = picture.getPixel(x, y);
						}
					}

					blankTile = new MutablePixelArrayPicture(clone_array, "");

					for (int x = 0; x < newWidth; x++) {
						for (int y = 0; y < newHeight; y++) {
							blankTile.paint(x, y, new ColorPixel(1, 1, 1));
						}
					}

					blankTileView = new PictureView(blankTile.createObservable());
					blankTileView.addKeyListener(this);
					blankTileView.addMouseListener(this);

					add(blankTileView);

					pictureView[i][j] = blankTileView;
				} else {

					SubPicture tile = picture.extract(i * newWidth, j * newHeight, newWidth, newHeight);
					pictureView[i][j] = new PictureView(tile.createObservable());
					pictureView[i][j].addMouseListener(this);
					pictureView[i][j].addKeyListener(this);

					add(pictureView[i][j]);
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		blankTileView = pictureView[xCounter][yCounter];

		if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (xCounter > 0)) {

			PictureView temp = pictureView[(xCounter - 1)][yCounter];
			Picture pic = blankTileView.getPicture();
			blankTileView.setPicture(temp.getPicture().createObservable());
			temp.setPicture(pic.createObservable());

			xCounter -= 1;
		}

		if ((e.getKeyCode() == KeyEvent.VK_UP) && (yCounter > 0) && (yCounter <= height)) {
			PictureView temp = pictureView[xCounter][(yCounter - 1)];

			Picture pic = blankTileView.getPicture();
			blankTileView.setPicture(temp.getPicture().createObservable());
			temp.setPicture(pic.createObservable());

			yCounter -= 1;
		}

		if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (xCounter >= 0) && (xCounter < width - 1)) {
			PictureView temp = pictureView[(xCounter + 1)][yCounter];

			Picture pic = blankTileView.getPicture();
			blankTileView.setPicture(temp.getPicture().createObservable());
			temp.setPicture(pic.createObservable());

			xCounter += 1;
		}

		if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (yCounter >= 0) && (yCounter < height - 1)) {
			PictureView temp = pictureView[xCounter][(yCounter + 1)];

			Picture p = blankTileView.getPicture();
			blankTileView.setPicture(temp.getPicture().createObservable());
			temp.setPicture(p.createObservable());

			yCounter += 1;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (e.getSource() == pictureView[i][j]) {
					
					int clickxCounter = i;
					int clickyCounter = j;

					if ((clickxCounter <= xCounter) && (clickyCounter == yCounter)) {
						int distance = xCounter - clickxCounter;
						for (int x = 0; x < distance; x++) {
							PictureView blank = pictureView[xCounter][yCounter];
							PictureView temp = pictureView[(xCounter - 1)][yCounter];
							Picture p = blank.getPicture();
							blank.setPicture(temp.getPicture());
							temp.setPicture(p.createObservable());

							xCounter -= 1;
						}
					}

					if ((clickyCounter <= yCounter) && (clickxCounter == xCounter)) {
						int distance = yCounter - clickyCounter;
						for (int x = 0; x < distance; x++) {
							PictureView blank = pictureView[xCounter][yCounter];
							PictureView temp = pictureView[xCounter][(yCounter - 1)];
							Picture p = blank.getPicture();
							blank.setPicture(temp.getPicture());
							temp.setPicture(p.createObservable());

							yCounter -= 1;

						}
					}

					if ((clickxCounter >= xCounter) && (clickyCounter == yCounter)) {
						int distance = clickxCounter - xCounter;
						for (int x = 0; x < distance; x++) {
							PictureView blank = pictureView[xCounter][yCounter];
							PictureView temp = pictureView[(xCounter + 1)][yCounter];
							Picture p = blank.getPicture();
							blank.setPicture(temp.getPicture());
							temp.setPicture(p.createObservable());

							xCounter += 1;
						}
					}

					if ((clickyCounter >= yCounter) && (clickxCounter == xCounter)) {
						int distance = clickyCounter - yCounter;
						for (int x = 0; x < distance; x++) {
							PictureView blank = pictureView[xCounter][yCounter];
							PictureView temp = pictureView[xCounter][(yCounter + 1)];
							Picture p = blank.getPicture();
							blank.setPicture(temp.getPicture());
							temp.setPicture(p.createObservable());

							yCounter += 1;
						}
					}
				}
			}
		}
	}

	//unused methods	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	}
}
