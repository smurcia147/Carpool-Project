import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Mock {
	protected static JFrame frame;
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		frame = new JFrame("Car pool");
		ManageRequestPanel p = new ManageRequestPanel();
		frame.add(p);
		frame.setSize(new Dimension(500,500));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
