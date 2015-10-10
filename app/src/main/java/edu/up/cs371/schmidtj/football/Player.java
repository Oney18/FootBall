package edu.up.cs371.schmidtj.football;

import java.io.Serializable;

/**
 * Created by schmidtj on 9/23/2015.
 */
public class Player implements Serializable{

    private String playersFullName; //the player's name
    private int Goals; //# of goals made by player
    private int Assists; //# of assists made by player
    private String imageID; //the name of the image used for picture

    /**
     * Player
     *
     * Creates a new player
     *
     * @param name the playes name
     * @param GoalsStat the amount of goals made
     * @param AssistsStat the amount of assists made
     */
    protected Player(String name, int GoalsStat, int AssistsStat)
    {
        this.playersFullName=name;
        this.Goals=GoalsStat;
        this.Assists=AssistsStat;

        this.setImageID("green_cran"); //default image
    }

    /**
     * setImageID
     *
     * sets the players image
     *
     * @param imageName the image name to be set
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
     * getIMageID
     *
     * returns the image id for the player
     *
     * @return the image id
     */
    public String getImageID()
    {
        return this.imageID;
    }

    /**
     * setGoals
     *
     * sets the number of goald made by player
     *
     * @param GoalsStat new amount of goals made
     */
    public int setGoals(int GoalsStat)
    {
        try
        {
            this.Goals+=GoalsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * setGoals
     *
     * copy of method in case a string is passed
     *
     * @param GoalsStat new amount of goals made by player
     */
    public int setGoals(String GoalsStat)
    {
        try
        {
            this.Goals+=Integer.parseInt( GoalsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getGoals
     *
     * returns the amount of goals made by player
     *
     * @return the number of goals made
     */
    public int getGoals()
    {
        return this.Goals;
    }

    /**
     * setAssists
     *
     * sets the number of assists made by player
     *
     * @param AssistsStat the number of assists made by player
     */
    public int setAssists(int AssistsStat)
    {
        try
        {
            this.Assists+=AssistsStat;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * setAssists
     *
     * copy of method in case a string is passed
     *
     * @param AssistsStat the number of assists made by player
     */
    public int setAssists(String AssistsStat)
    {
        try
        {
            this.Assists+=Integer.parseInt( AssistsStat );
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getAssists
     *
     * returns the number of assists made by player
     *
     * @return the number of assists made
     */
    public int getAssistsStat()
    {
        return this.Assists;
    }

    /**
     * setPlayersFullName
     *
     * sets the players name
     *
     * @param Name player's name
     */
    public int setPlayersFullName(String Name)
    {
        try
        {
            this.playersFullName=Name;
            return 1;
        }
        finally
        {
            return 0;
        }
    }

    /**
     * getPlayerFullName
     *
     * returns the player's name
     *
     * @return the player's name
     */
    public String getPlayersFullName()
    {
        return this.playersFullName;
    }

}
