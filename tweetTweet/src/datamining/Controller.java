package datamining;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import datamining.GUI;



// for short REST
public class Controller implements ActionListener {

	private GUI view;
	private Tweet tweet;
	public Controller( GUI view, Tweet tweet){
		this.view=view;
		this.tweet=tweet;
		view.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String input= view.getInput();
		List<String> list= tweet.getTweets(input);
		String a= "Anzahl der gesammelten tweets";
//		for(String l : list){
//			System.out.println(l);
//			}
		a=a+": "+ String.valueOf(list.size());
		list.add(a);
		view.getList().setListData(list.toArray());
		}
	}


