package a8;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurImageTool implements Tool{
	
	private BlurImageToolUI ui;
	private ImageEditorModel model;


	public BlurImageTool(ImageEditorModel model) {
		this.model = model;
		ui = new BlurImageToolUI();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent p) {
		// TODO Auto-generated method stub
		int x =	p.getX();
		int y = p.getY();
		double redValue;
		double greenValue;
		double blueValue;
		double redAverage = 0;
		double greenAverage = 0;
		double blueAverage = 0;
	 	int i = 0;
	
		for(int hVal = -ui.getBlurSize(); hVal <= ui.getBlurSize(); hVal++){
			for(int vVal = -ui.getBlurSize(); vVal <= ui.getBlurSize(); vVal++){
						
				if(x+hVal >= 0 && y+vVal >= 0 && x+hVal <= model.getCurrent().getWidth() - 1 && y + vVal <= model.getCurrent().getHeight() - 1){
					redAverage = redAverage + model.getPixel(x + hVal, y + vVal).getRed();
					blueAverage = blueAverage + model.getPixel(x + hVal, y + vVal).getBlue();
					greenAverage = greenAverage + model.getPixel(x + hVal, y + vVal).getGreen();
				}
				else{
					redAverage=redAverage + 0;
					blueAverage=blueAverage + 0;
					greenAverage=greenAverage + 0;
					i = i + 1;
					}
				}
			}
				redValue=redAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize()*2 + 1) - i);
				blueValue=blueAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize()*2 + 1) - i);
				greenValue=greenAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize() * 2 + 1) - i);
				
				ColorPixel c = new ColorPixel(redValue, greenValue, blueValue);
				
				model.paintAt(p.getX(), p.getY(), c, ui.getBlurSize());
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent d) {
		// TODO Auto-generated method stub
		int x =	d.getX();
		int y = d.getY();
		double redValue;
		double greenValue;
		double blueValue;
		double redAverage = 0;
		double greenAverage = 0;
		double blueAverage = 0;
	 	int i = 0;
	
		for(int hVal = -ui.getBlurSize(); hVal <= ui.getBlurSize(); hVal++){
			for(int vVal = -ui.getBlurSize(); vVal <= ui.getBlurSize(); vVal++){
						
				if(x+hVal >= 0 && y + vVal >= 0 && x + hVal <= model.getCurrent().getWidth() - 1 && y + vVal <= model.getCurrent().getHeight() - 1){
					redAverage = redAverage + model.getPixel(x + hVal, y + vVal).getRed();
					blueAverage = blueAverage + model.getPixel(x + hVal, y + vVal).getBlue();
					greenAverage = greenAverage + model.getPixel(x + hVal, y + vVal).getGreen();
				}
				else{
					redAverage=redAverage + 0;
					blueAverage=blueAverage + 0;
					greenAverage=greenAverage + 0;
					i = i + 1;
					}
				}
			}
				redValue=redAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize()*2 + 1) - i);
				blueValue=blueAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize()*2 + 1) - i);
				greenValue=greenAverage/((ui.getBlurSize() * 2 + 1)*(ui.getBlurSize() * 2 + 1) - i);
				
				ColorPixel c = new ColorPixel(redValue, greenValue, blueValue);
				
				model.paintAt(d.getX(), d.getY(), c, ui.getBlurSize());
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Blur Image";
	}

	@Override
	public JPanel getUI() {
		// TODO Auto-generated method stub
		return ui;
	}

}
