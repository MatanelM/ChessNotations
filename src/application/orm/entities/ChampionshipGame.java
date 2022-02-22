package application.orm.entities;

public class ChampionshipGame extends Game{

    private int gameNumber;
    
	public ChampionshipGame(int gameNumber, Game game) {
		super(game.getId(), game.getDate(), game.getWhiteResult(), game.getBlackResult(), game.getNotations());
		this.setGameNumber(gameNumber);
	}

	public ChampionshipGame() {
		super();
		this.setGameNumber(0);
	}
	
	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	@Override
	public String toString() {
		return "ChampionshipGame [gameNumber=" + gameNumber + " "+super.toString()+"]";
	}
    
}
