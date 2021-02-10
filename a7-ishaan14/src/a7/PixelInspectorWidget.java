package a7;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JLabel implements MouseListener {
	
	private PictureView pictureView;
	private JLabel coordinate;
	private Picture pic;
	DecimalFormat form = new DecimalFormat("##.00");
	
	public PixelInspectorWidget(Picture picture, String title) {
		setLayout(new BorderLayout());

		pictureView = new PictureView(picture.createObservable());
		pictureView.addMouseListener(this);
		add(pictureView, BorderLayout.CENTER);

		coordinate = new JLabel(title);
		add(coordinate, BorderLayout.WEST);

		pic = picture;

	}
	
	//unused methods
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		coordinate.setText("<html>X: " + e.getX() + "<br> Y: " + e.getY() + "<br> R: " + form.format(pic.getPixel(e.getX(), e.getY()).getRed()) + "<br> G: " + form.format(pic.getPixel(e.getX(), e.getY()).getGreen()) + "<br> B: " + form.format(pic.getPixel(e.getX(), e.getY()).getBlue()) + "<br> Brightness: "+ form.format(pic.getPixel(e.getX(), e.getY()).getIntensity())+"</html>");
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
