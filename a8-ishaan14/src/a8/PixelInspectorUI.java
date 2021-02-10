package a8;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PixelInspectorUI extends JPanel {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	
	private JButton copyColor;

	
	public int redColor;
	public int blueColor;
	public int greenColor;
	
	private ImageEditorView view;
	private ImageEditorModel model;
	private Tool currentTool;
	private BlurImageTool blurTool;
	
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");

		setLayout(new GridLayout(3,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		

		copyColor = new JButton("Set PaintBrush Color");
		add(copyColor);
		
	}
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
	}
	
	public JButton getButton(){
		return copyColor;
	}
   
}
