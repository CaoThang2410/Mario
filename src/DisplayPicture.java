
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class DisplayPicture {
	JFrame window;
	Container con;
	JPanel picturePanel;
	JLabel pictureLabel;
	ImageIcon image;

	public static void main(String[] args) {
		new DisplayPicture();
	}

	public DisplayPicture() {
		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		con = window.getContentPane();

		picturePanel = new JPanel();
		picturePanel.setBounds(150, 100, 500, 300);
		picturePanel.setBackground(Color.blue);
		con.add(picturePanel);

		pictureLabel = new JLabel();

		image = new ImageIcon(".//res//map//game-over.jpg");
		pictureLabel.setIcon(image);
		picturePanel.add(pictureLabel);

		window.setVisible(true);

	}

}
