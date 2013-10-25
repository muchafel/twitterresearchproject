package datamining;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GUI extends JFrame {
	private Container contP;
	private Container inputC;
	private JList list;
	private JScrollPane scrollPane;
	private JButton inputButton;
	private JButton inputButton2;
	private JButton inputButton3;
	private JTextPane inputPane;
	
	//View --- simple GUI
public GUI (String name)
{
	super(name);
	contP = this.getContentPane();
	contP.setLayout(new BorderLayout());
	
	inputButton = new JButton("REST - short");
	inputButton2 = new JButton("REST - long");
	inputButton3 = new JButton("STREAM to file");
	inputPane = new JTextPane();
	inputPane.setPreferredSize(new Dimension(200,20));
	
	inputC = new Container();
	inputC.setLayout(new FlowLayout());
	inputC.add(inputPane);
	inputC.add(inputButton);
	inputC.add(inputButton2);
	inputC.add(inputButton3);
	
	list = new JList();
	scrollPane = new JScrollPane(list);
	
	contP.add(scrollPane,BorderLayout.CENTER);
	contP.add(inputC,BorderLayout.SOUTH);
			
	this.pack();
	this.setVisible(true);
    }
public String getInput(){
	return this.inputPane.getText();
}

public void addActionListener(ActionListener a){
	inputButton.addActionListener(a);
}
public void addActionListener2(ActionListener a){
	inputButton2.addActionListener(a);
}
public void addActionListener3(ActionListener a){
	inputButton3.addActionListener(a);
}
public JList getList(){
	return this.list;
}

}

