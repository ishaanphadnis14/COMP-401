package a7;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelInspectorMain {

	public static void main(String[] args) throws IOException {
	Picture pic = A7Helper.readFromURL("https://3j0grh44ocny4a6kcn288izx-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/aruba-1-950x530.jpg");
	PixelInspectorWidget simpleWidget = new PixelInspectorWidget(pic, "<html>X: <br> Y: <br> R: <br> G: <br> B: <br> Brightness: </html>");
	
	JFrame mainFrame = new JFrame();
	mainFrame.setTitle("Pixel Inspector");
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BorderLayout());
	topPanel.add(simpleWidget, BorderLayout.CENTER);
	mainFrame.setContentPane(topPanel);

	mainFrame.pack();
	mainFrame.setVisible(true);
	}
}
