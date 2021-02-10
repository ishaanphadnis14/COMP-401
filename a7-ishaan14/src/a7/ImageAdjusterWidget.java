package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.util.ArrayList;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {
	
	private PictureView pictureView;
	private JLabel blurL;
	private JLabel saturationL;
	private JLabel brightnessL;
	private Picture p;
	private JSlider blurS;
	private JSlider saturationS;
	private JSlider brightnessS;
	private Pixel[][] copyArray = null;
	ArrayList<Double> redPixels = new ArrayList<Double>();
	ArrayList<Double> greenPixels = new ArrayList<Double>();
	ArrayList<Double> bluePixels = new ArrayList<Double>();
	
	public ImageAdjusterWidget(Picture picture) {
		
		setLayout(new BorderLayout());
		pictureView = new PictureView(picture.createObservable());
		p = picture;

		add(pictureView, BorderLayout.CENTER);

		blurS = new JSlider(0, 5, 0);
		saturationS = new JSlider(-100, 100, 0);
		brightnessS = new JSlider(-100, 100, 0);

		blurS.setMajorTickSpacing(1);
		blurS.setPaintTicks(true);
		blurS.setPaintLabels(true);
		blurS.setSnapToTicks(true);
		blurS.addChangeListener(this);

		saturationS.setMajorTickSpacing(25);
		saturationS.setPaintTicks(true);
		saturationS.setPaintLabels(true);
		saturationS.addChangeListener(this);
		
		brightnessS.setMajorTickSpacing(25);
		brightnessS.setPaintTicks(true);
		brightnessS.setPaintLabels(true);
		brightnessS.addChangeListener(this);

		JPanel sliders = new JPanel();
		sliders.setLayout(new GridLayout(3, 2));
		blurL = new JLabel("Blur: ");
		sliders.add(blurL);
		sliders.add(blurS);
		saturationL = new JLabel("Saturation: ");
		sliders.add(saturationL);
		sliders.add(saturationS);
		brightnessL = new JLabel("Brightness: ");
		sliders.add(brightnessL);
		sliders.add(brightnessS);
		add(sliders, BorderLayout.SOUTH);

	}
	
	public void copyPic() {
		copyArray = new Pixel[p.getWidth()][p.getHeight()];
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {
				copyArray[i][j] = p.getPixel(i, j);
			}
		}
		
		if (blurS.getValue() != 0) {
			blurrer();
		}
		
		if (saturationS.getValue() != 0) {
			saturater();
		}
		
		if (brightnessS.getValue() != 0) {
			brightener();
		}
		
		Picture pic = new MutablePixelArrayPicture(copyArray, p.getCaption());
		ObservablePicture observpic = pic.createObservable();
		pictureView.setPicture(observpic);
	}
		
	public void blurrer() {
		int blurValue = blurS.getValue();
		double averageR = 0;
		double averageG = 0;
		double averageB = 0;		
		
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				for (int curX = (x - blurValue); curX < (x + blurValue); curX++) {
					for (int curY = (y - blurValue); curY < (y + blurValue); curY++) {
						try {
							if (copyArray[curX][curY] != null) {								
								redPixels.add(copyArray[curX][curY].getRed());
								greenPixels.add(copyArray[curX][curY].getGreen());
								bluePixels.add(copyArray[curX][curY].getBlue());
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							}		
						}
					}
				averageR = averageList(redPixels);
				averageG = averageList(greenPixels);
				averageB = averageList(bluePixels);
				
				copyArray[x][y] = new ColorPixel(averageR, averageG, averageB);
				
				redPixels.clear();
				greenPixels.clear();
				bluePixels.clear();
						
			}
		}
	}
	
	public double averageList(ArrayList<Double> pixels) {
		double total = 0;
		double average = 0;
		for (int i = 0; i < pixels.size(); i++) {
			total = total + pixels.get(i);
		}
		
		average = total/pixels.size();
		return average;
	}
	
	public void saturater() {
		int sValue = saturationS.getValue();
		int bValue = brightnessS.getValue();
		double newR = 0;
		double newG = 0;
		double newB = 0;
		double color = 0;
	
		
		if (sValue < 0) {
			for (int x = 0; x < p.getWidth(); x++) {
				for (int y = 0; y < p.getHeight(); y++) {
					newR = (copyArray[x][y].getRed()) * (1.0 + (sValue / 100.0) ) - (bValue * sValue / 100.0);
					newG = (copyArray[x][y].getGreen()) * (1.0 + (sValue / 100.0) ) - (bValue * sValue / 100.0);
					newB = (copyArray[x][y].getBlue()) * (1.0 + (sValue / 100.0) ) - (bValue * sValue / 100.0);
					
					if (newR > 1) {
						newR = 1;
					} else if (newR < 0) {
						newR = 0;
					}
					
					if (newG > 1) {
						newG = 1;
					} else if (newG < 0) {
						newG = 0;
					}
					
					if (newB > 1) {
						newB = 1;
					} else if (newB < 0) {
						newB = 0;
					}
					
					copyArray[x][y] = new ColorPixel(newR, newG, newB);
									}
			}
		} else if (sValue > 0) {
			
			for (int x = 0; x < p.getWidth(); x++) {
				for (int y = 0; y < p.getHeight(); y++) {
					
					if (copyArray[x][y].getRed() >= copyArray[x][y].getBlue() && copyArray[x][y].getRed() >= copyArray[x][y].getGreen()) {
						color = copyArray[x][y].getRed();
					} else if (copyArray[x][y].getGreen() >= copyArray[x][y].getRed() && copyArray[x][y].getGreen() >= copyArray[x][y].getBlue()) {
						color = copyArray[x][y].getGreen();
					} else if (copyArray[x][y].getBlue() >= copyArray[x][y].getRed() && copyArray[x][y].getBlue() >= copyArray[x][y].getGreen()) {
						color = copyArray[x][y].getBlue();
					}
					
					if (color > 0.01) {
						newR = (copyArray[x][y].getRed()) * ((color + ((1.0 - color) * (sValue / 100.0))) / color);
						newG = (copyArray[x][y].getGreen()) * ((color + ((1.0 - color) * (sValue / 100.0))) / color);
						newB = (copyArray[x][y].getBlue()) * ((color + ((1.0 - color) * (sValue / 100.0))) / color);
						
						if (newR > 1) {
							newR = 1;
						} else if (newR < 0) {
							newR = 0;
						}
						
						if (newG > 1) {
							newG = 1;
						} else if (newG < 0) {
							newG = 0;
						}
						
						if (newB > 1) {
							newB = 1;
						} else if (newB < 0) {
							newB = 0;
						}
						
						copyArray[x][y] = new ColorPixel(newR, newG, newB);
					} else {
						copyArray[x][y] = new ColorPixel(copyArray[x][y].getRed(), copyArray[x][y].getGreen(), copyArray[x][y].getBlue());
					}
				}
			}
		} else if (sValue == 0) {
			for (int x = 0; x < p.getWidth(); x++) {
				for (int y = 0; y < p.getHeight(); y++) {
					copyArray[x][y] = new ColorPixel(copyArray[x][y].getRed(), copyArray[x][y].getGreen(), copyArray[x][y].getBlue());
				}
			}	
		}
	}
	
	public void brightener() {
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {			
				if (brightnessS.getValue() >= 0) {
					copyArray[x][y] = copyArray[x][y].lighten(brightnessS.getValue()/100.0);
				}
				if (brightnessS.getValue() <= 0) {
				copyArray[x][y] = copyArray[x][y].darken(Math.abs(brightnessS.getValue())/100.0);
				}
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (!((JSlider) e.getSource()).getValueIsAdjusting());
		copyPic();
		
	}

}
