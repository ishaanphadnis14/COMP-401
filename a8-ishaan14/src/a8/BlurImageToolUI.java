package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurImageToolUI extends JPanel implements ChangeListener {
	
	private JSlider sizeSlider;
	private PictureView colorPreview;
	
	public BlurImageToolUI() {

		setLayout(new GridLayout(0,1));
	
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BorderLayout());
	
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(0,1));
	
		JPanel sizePanel = new JPanel();
		JLabel redLabel = new JLabel("Red:");
		sizePanel.setLayout(new BorderLayout());
		sizePanel.add(redLabel, BorderLayout.WEST);
		sizeSlider = new JSlider(0,25);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setMajorTickSpacing(5);
		sizeSlider.addChangeListener(this);
		sizePanel.add(sizeSlider, BorderLayout.CENTER);
	
	
		Dimension sliderDimensions = new Dimension(140,45);
		redLabel.setPreferredSize(sliderDimensions);
		sliderPanel.add(sizePanel);
	
		colorPanel.add(sliderPanel, BorderLayout.CENTER);
		add(colorPanel);
	
	}
	
	public int getBlurSize() {
		
		int size = sizeSlider.getValue();
		return size;
	}

	public Pixel getBlurColor() {
		
		return colorPreview.getPicture().getPixel(0,0);
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

}
