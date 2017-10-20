package GraphVizTest;

//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
//import java.awt.TextField;

import javax.swing.JFrame;
//import javax.swing.JLabel;

public class output extends JFrame {
  private static final long serialVersionUID = 1L;
  
  output(String a) {
    TextArea out = new TextArea(a);
    out.setFont(new Font("Herbit",Font.BOLD, 20));
    out.setBounds(0, 0, 1000, 800);
    add(out);
    setLayout(null);
    setLocation(800, 0);
    setVisible(true);
    setSize(1000,800);
    setDefaultCloseOperation(2);
  }
}