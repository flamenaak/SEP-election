package controller;


import javax.swing.ComboBoxModel;

import model.*;

public class Controller implements IController{

	private User user;
	private Voter voter;
	private Admin admin;
	private Election election;
	private DBManager dbm;
	private Position position;
	
	public Controller() {
		user = new User();
		voter = new Voter();
		admin = new Admin();
		election = new Election();
	}
	
	public Controller(User user) {
		this.user = user;
	}
	
	public Controller(Admin admin) {
		this.admin = admin;
	}
	
	public Controller(Voter voter) {
		this.voter = voter;
	}
	
	@Override
	public void logIn(String name, String password) {
		
	}

	@Override
	public void logOut() {
		
	}
	
	public boolean checkName(String name) {
		return name.equals(user.getName());
	}

	@Override
	public boolean checkPassword(String password) {
		return password.equals(user.getPassword());
	}

	@Override
	public void changePassword(String password) {
		admin.setPassword(password);
	}

	@Override
	public void createElection() {
		
	}

	@Override
	public void startElection(Election election) {
		election.start();
	}
	
	@Override
	public void endElection(Election election) {
		election.end();
	}

	@Override
	public void addPosition(Election election, Position position) {
		election.addPosition(position);
	}

	@Override
	public void addCandidate(Election election, Position position, Candidate candidate) {
		election.getPosition(position).addCandidate(candidate);
	}

	@Override
	public ArrayList<Candidate> viewCandidatesAndPositions() {
		ArrayList<Candidate> candidates = new ArrayList<>();

		for (int i = 0; i < election.getPositionsLength(); i++) {
			for (int j = 0; j < array.length; j++) {
				candidates.add(election.getPosition(i).getCandidate(j));
			}
		}
		
		return candidates;
	}

	@Override
	public void viewResults(Election election, Position position) {

		for (int i = 0; i < election.positions.size(); i++) {
			if(positions.get(i))
		}
	}

   @Override
   public void viewCandidatesAndPositions()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void okPressed()
   {
      // TODO Auto-generated method stub
      
   }

   public String[] getCandidates()
   {
      return position.toStringArray();
   }

   public String[] getPositions()
   {
      return election.positionsToString();
   }

   @Override
   public void viewCandidatesAndPositions()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void backPressed()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void viewCandidatesAndPositions()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void vote(int candidateIndex)
   {
      // TODO Auto-generated method stub
      
   }


	

}
