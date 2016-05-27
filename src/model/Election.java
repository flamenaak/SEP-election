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
	
	public void addPosition(Position pos){
		positions.add(pos);
	}
	
	public void empty(){
		positions=null;
	}
	
	public Position getPosition(String name){
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).getPositionName().equals(name)){
				return positions.get(i);
			}
		}
		return null;
	}
	
	public Position getPosition(int index){
		return positions.get(index);
	}
	
	public void startElection(){
	   active = true;
	}
	
	public void closeEection(){
		active = false;
	}
	
	public String[] positionsToString()
	{
	   String[] positionArray = new String[positions.size()];
	   
      for(int i = 0; i < positions.size(); i++)
      {
         positionArray[i] = positions.get(i).getPositionName();
      }
      
      return positionArray;
	}
	
	public ArrayList<Position> getPositions()
	{
	   return positions;
	}

}
