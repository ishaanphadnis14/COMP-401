package a7;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePuzzleMain {
	
	public static void main(String[] args) throws IOException{
		Picture pic = A7Helper.readFromURL("https://3j0grh44ocny4a6kcn288izx-wpengine.netdna-ssl.com/wp-content/uploads/2018/07/aruba-1-950x530.jpg");
		FramePuzzleWidget puzzle = new FramePuzzleWidget(pic, 5, 5);

		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Frame Puzzle");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(puzzle, BorderLayout.CENTER);
		mainFrame.setContentPane(topPanel);

		mainFrame.pack();
		mainFrame.setVisible(true);

	}
}
