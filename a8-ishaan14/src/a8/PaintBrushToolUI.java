package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private PictureView color_preview;
	

	private JSlider sizeSlider;
	private int sizeValue;
	private JSlider opacitySlider;
	private PaintBrushTool tool;
	
	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		
		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Size: ");
		sizePanel.setLayout(new BorderLayout());
		sizePanel.add(sizeLabel, BorderLayout.WEST);
		sizeSlider = new JSlider(0,25,5);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setMajorTickSpacing(5);
		sizeSlider.addChangeListener(this);
		sizePanel.add(sizeSlider, BorderLayout.CENTER);

		
		JPanel opacityPanel = new JPanel();
		JLabel opacityLabel = new JLabel("Opacity: ");
		opacityPanel.setLayout(new BorderLayout());
		opacityPanel.add(opacityLabel, BorderLayout.WEST);
		opacitySlider = new JSlider(0,100,100);
		opacitySlider.setPaintTicks(true);
		opacitySlider.setPaintLabels(true);
		opacitySlider.setMajorTickSpacing(20);
		opacitySlider.addChangeListener(this);
		opacityPanel.add(opacitySlider, BorderLayout.CENTER);
		

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		
		sizeLabel.setPreferredSize(d);
		opacityLabel.setPreferredSize(d);
		
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		
		slider_panel.add(sizePanel);
		slider_panel.add(opacityPanel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new PictureView(Picture.createSolidPicture(50, 50, Pixel.WHITE, true).createObservable());
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
		green_slider.getValue()/100.0,
		blue_slider.getValue()/100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.paint(0, 0, preview_frame.getWidth()-1, preview_frame.getHeight()-1, p);
		
		sizeValue = sizeSlider.getValue();
	}
	
	
	public Pixel getBrushColor() {
		return this.getOpacity();
	}
	
	public int getBrushSize() {
		return sizeSlider.getValue();
	}
	
	public JSlider getOpacitySlider(){
		return opacitySlider;
		
	}
	
	public int getOpacityValue(){
		return opacitySlider.getValue();
	}
	
	double currentRed;
	double currentBlue;
	double currentGreen;

	public void getOpacityColor(double currentRed, double currentBlue, double currentGreen ) {

		this.currentRed= currentRed;
		this.currentBlue = currentBlue;
		this.currentGreen =currentGreen;
	}
	
	public Pixel getOpacity(){
		
		double red = red_slider.getValue() / 100.0 * opacitySlider.getValue() / 100.0 + (currentRed*(1 - opacitySlider.getValue() / 100.0));
		double green = green_slider.getValue()/100.0 * opacitySlider.getValue() / 100.0 + (currentGreen*(1 - opacitySlider.getValue() / 100.0));
		double blue = blue_slider.getValue()/100.0 * opacitySlider.getValue() / 100.0 + (currentBlue*(1 - opacitySlider.getValue() / 100.0));
		
		Pixel pic = new ColorPixel(red,green,blue);
		
		return pic;
		
	}
	
	public void sliderSetter(int red, int green, int blue) {
		
		red_slider.setValue(red);
		green_slider.setValue(green);
		blue_slider.setValue(blue);
		
	}
	


}
