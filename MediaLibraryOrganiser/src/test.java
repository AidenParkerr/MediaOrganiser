import java.awt.Color;
import java.awt.*;

import javax.swing.*;

public class test extends JFrame{

	test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setBackground(Color.red);
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.blue);
//		panel.setSize(300, 300);
		c.anchor = GridBagConstraints.NORTH;
		panel.add(new JLabel("hey"),c);
		panel.add(new JTextField());
		
		
		add(panel);
//		pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new test();
	}


}