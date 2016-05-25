package model;

import java.util.ArrayList;
import controller.*;

public class Election
{
	private boolean active;
	private ArrayList<Position> positions;
	
	private Controller controller;
	
	public Election(Controller controller){
		active=false;
		positions=new ArrayList<>();
		this.controller=controller;
	}
	
	public void fillPositions(Position pos){
		positions.add(pos);
	}
	
	public void empty(){
		positions=null;
	}
	
	public Position getPosition(String name){
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).getName().equals(name)){
				return positions.get(i);
			}
		}
		return null;
	}
	
	public Position getPosition(int index){
		return positions.get(index);
	}
	
	public Position getPosition(Position pos){
		for (int i = 0; i < positions.size(); i++) {
			if(positions.get(i).equals(pos)){
				return positions.get(i);
			}
		}
		return null;
	}
	
	public void startElection(){
		controller.startEletion();
	}
	
	public void closeEection(){
		active=false;
	}

}
