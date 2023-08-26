package tictactoe;

public class Player {
  public String name;
  public PlayingPiece p;
  
  Player(String name, PlayingPiece p){
	  this.name = name;
	  this.p=p;
  }
  
  public String getname() {
	  return name;
  }
  public void setname(String name) {
	  this.name = name;
  }
  public PlayingPiece getPlayingPiece(){
	 return p; 
  }
  public void setPlayingPiece(PlayingPiece p) {
	 this.p = p; 
  }
}
