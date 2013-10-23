package tweet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import tweet.GUI;

// for REST-LONG
public class Controller2 implements ActionListener {

	private GUI view;
	private Tweet tweet;
	public Controller2( GUI view, Tweet tweet){
		this.view=view;
		this.tweet=tweet;
		view.addActionListener2(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String input= view.getInput();
		
		// gets tweets according to input
		List<String> list= tweet.getTweetsLong(input);
		String a= "Anzahl der gesammelten tweets";
//		for(String l : list){
//			System.out.println(l);
//			}
		a=a+": "+ String.valueOf(list.size());
		list.add(a);
		view.getList().setListData(list.toArray());
		}
	}