package edu.up.cs371.schmidtj.football;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Team
 *
 * Created by schmidtj on 9/23/2015.
 *
 * This class holds the attributes and methods relevant to a Team
 */
public class Team implements Serializable{

    private String teamsName; //name of team
    private int gamesWins; //# of games won
    private int gamesPlayed; //# of games played
    private int gamesLost; //# of games lost
    private String imageID; //ID for the team picture
    public ArrayList<String> playerList; //list of team's players' names
    private HashMap<String,Player> teamRoster; //list of team's Player objects based on name

    /**
     * Team
     *
     * Creates a new instance of the class
     *
     * @param name team's name
     * @param games_Wins team's # of wins
     * @param games_Lost teams's # of losses
     */
    protected Team(String name, int games_Wins, int games_Lost)
    {
        this.teamsName=name;

        if(games_Wins < 0)
            games_Wins = 0;
        this.gamesWins=games_Wins;

        if(games_Lost < 0)
            games_Lost = 0;
        this.gamesLost=games_Lost;

        this.gamesPlayed = this.gamesLost+this.gamesWins;

        this.setImageID("green_cran"); //default image for team

        playerList = new ArrayList<String>();
        teamRoster = new HashMap<String,Player>();

        //creates the default player 0 for the team
        Player teamManager = new Player("TheBigFish",0,0);
        teamManager.setImageID("orange_fish");

        this.addAPlayer(teamManager);
    }

    /**
     * addAPlayer
     *
     * Adds a new player to the team
     *
     * @param instPlayer player to add to team
     */
    public int addAPlayer(Player instPlayer)
    {
        try
        {
            teamRoster.put(instPlayer.getPlayersFullName(), instPlayer);
            if( playerList.indexOf(instPlayer.getPlayersFullName()) == -1)
                playerList.add(instPlayer.getPlayersFullName());
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getPlayer
     *
     * Gets the team's player based on their name
     *
     * @param playerName the player's name
     * @return the player
     */
    public Player getPlayer(String playerName)
    {
        return teamRoster.get(playerName);
    }

    /**
     * setImageID
     *
     * Sets the team's image ID
     *
     * @param imageName the image ID to set
     */
    public int setImageID(String imageName)
    {
        try
        {
            this.imageID= imageName;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getImageID
     *
     * Returns the team's image ID
     *
     * @return the team's image id
     */
    public String getImageID()
    {
        return this.imageID;
    }

    /**
     * updateWin
     *
     * Updates the team's stats if they win
     */
    public void updateWin()
    {
        this.gamesWins++;
        this.updatePlayed();
    }

    /**
     * getWin
     *
     * Returns the team's # of wins
     *
     * @return team's # of wins
     */
    public int getWin()
    {
        return this.gamesWins;
    }

    /**
     * updatePlayed
     *
     * Increments the team's # of games played
     */
    private void updatePlayed()
    {
        this.gamesPlayed++;
    }

    /**
     * setWins
     *
     * Set the number of times the team has won
     * @param games_Wins # of times the team has won
     */
    public int setWins(int games_Wins)
    {
        try
        {
            this.gamesWins=games_Wins;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * setWins
     *
     * Copy of method in case a string is passed
     * @param games_Wins # of times the team has won
     */
    public int setWins(String games_Wins)
    {
        try
        {
            this.gamesWins=Integer.parseInt(games_Wins);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getPlayed
     *
     * returns the # of games played
     *
     * @return # of games played
     */
    public int getPlayed()
    {
        return this.gamesPlayed;
    }

    /**
     * updateLosses
     *
     * Updates the team if it loses
     */
    public void updateLoses()
    {
        this.gamesLost++;
        this.updatePlayed();
    }

    /**
     * getLoses
     *
     * Returns the amount of games lost
     *
     * @return # of games lost
     */
    public int getLoses()
    {
        return this.gamesLost;
    }

    /**
     * setLoses
     *
     * sets the number of times the team has lost
     *
     * @param games_lost # of games lost
     */
    public int setLoses(int games_lost)
    {
        try
        {
            this.gamesLost=games_lost;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * setLoses
     *
     * sets the number of times the team has lost
     *
     * @param games_lost # of games lost
     */
    public int setLoses(String games_lost)
    {
        try
        {
            this.gamesLost=Integer.parseInt(games_lost);
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * setTeamFullName
     *
     * Sets the team's name
     *
     * @param Name name to set
     */
    public int setTeamFullName(String Name)
    {
        try
        {
            this.teamsName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getTeamFullName
     *
     * Returns the team's name
     *
     * @return team's name
     */
    public String getTeamFullName()
    {
        return this.teamsName;
    }

    /**
     * updatePlayers
     *
     * Updates a player on the team
     *
     * @param playerName the player's name
     * @param Assists # of assists made by player
     * @param Goals # of goals made by player
     */
    public int updatePlayers(String playerName, int Assists, int Goals)
    {
        if( teamRoster.get(playerName) == null)
            return 0;

        teamRoster.get(playerName).setAssists(Assists);
        teamRoster.get(playerName).setAssists(Goals);

        return 1;
    }

}
