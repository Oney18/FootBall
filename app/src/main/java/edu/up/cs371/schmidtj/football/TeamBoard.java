package edu.up.cs371.schmidtj.football;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class TeamBoard extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{


    Intent intent; //intent passed by parent activity
    ImageView playerImage; //the player's image

    public EditText playerGoals; //# of goals scored by player
    public EditText playerAssists; //# of assists made by player
    public EditText playerName; //player's name

    public Button addAnotherPlayer; //button to add a new player

    public Team theTeam; //team passed in by parent activityvia intent

    public Spinner playerSpinner; //spinner to display all players
    public Spinner imagePlayerSelector; //spinenr to select a player's image

    public ArrayList<String> listImageSelector; //list of all image ID's possible

    /**
     * onCreate
     *
     * Initializes the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_board);

        //set all attributes
        addAnotherPlayer= (Button) findViewById(R.id.createPlayer);
        addAnotherPlayer.setOnClickListener(this);

        playerGoals= (EditText) findViewById(R.id.playerGoals);
        playerAssists= (EditText) findViewById(R.id.playerAssists);
        playerName= (EditText) findViewById(R.id.playerName);

        intent = getIntent();

        playerImage = (ImageView) findViewById( R.id.playerImage );

        theTeam = (Team)  intent.getSerializableExtra("aTeam");

        //sets default player to show
        playerGoals.setText( theTeam.getPlayer("TheBigFish").getPlayersFullName() );
        playerAssists.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getGoals()) );
        playerName.setText( String.valueOf(theTeam.getPlayer("TheBigFish").getAssistsStat()) );

        int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + theTeam.getPlayer("TheBigFish").getImageID(), null, null);
        playerImage.setImageResource(id);

        //sets the image spinner's contents
        listImageSelector = new ArrayList<String>();
        listImageSelector.add("orange_butterfly");
        listImageSelector.add("pink_butterfly");
        listImageSelector.add("green_cran");
        listImageSelector.add("blue_dragons");
        listImageSelector.add("green_dragons");
        listImageSelector.add("red_dragons");
        listImageSelector.add("orange_fish");
        listImageSelector.add("blue_pegasus");

        //sets up the spinners
        playerSpinner = (Spinner) findViewById(R.id.playerSpinner);
        ArrayAdapter<String> playerSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, theTeam.playerList);
        playerSpinner.setAdapter(playerSpinnerAdapter);

        imagePlayerSelector = (Spinner) findViewById(R.id.imagePlayerSelector);
        ArrayAdapter<String> imagePlayerSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imagePlayerSelector.setAdapter(imagePlayerSelectorAdapter);

        playerSpinner.setOnItemSelectedListener(this);
        imagePlayerSelector.setOnItemSelectedListener(this);
    }

    /**
     * return_back_click
     *
     * Finishes the activity, passing the new team back to the parent
     *
     * @param view the button clicked to return
     */
    public void return_back_click(View view)
    {
        intent.putExtra("returnATeam",theTeam);
        setResult(1, intent);
        finish();
    }

    /**
     * onClick
     *
     * Runs whenever a button is clicked, does actions based on which button is clicked
     *
     * @param view the button clicked
     */
    @Override
    public void onClick(View view) {

        if(view == addAnotherPlayer) //adds or updates team's players
        {

            if(String.valueOf(playerGoals.getText()).isEmpty() || String.valueOf(playerAssists.getText()).isEmpty() || String.valueOf(playerName.getText()).isEmpty())
                return; //empty fields

            if(theTeam.playerList.indexOf(String.valueOf(playerName.getText())) == -1 ) //creates new player
            {
                Player pTemp = new Player(String.valueOf(playerName.getText()), Integer.parseInt(String.valueOf(playerGoals.getText())), Integer.parseInt(String.valueOf(playerAssists.getText())));
                pTemp.setImageID( imagePlayerSelector.getSelectedItem().toString() );

                theTeam.addAPlayer(pTemp);

            }
            else //updates existing player
            {
                theTeam.getPlayer(String.valueOf(playerName.getText())).setGoals( Integer.parseInt(String.valueOf(playerGoals.getText())) );
                theTeam.getPlayer(String.valueOf(playerName.getText())).setAssists(Integer.parseInt(String.valueOf(playerAssists.getText())) );
                theTeam.getPlayer(String.valueOf(playerName.getText())).setImageID( imagePlayerSelector.getSelectedItem().toString() );
            }

            playerSpinner.setSelection( theTeam.playerList.indexOf(String.valueOf(playerName.getText())) );

        }

    }

    /**
     * onItemSelected
     *
     * Updates the activity based on what is selected in the spinner
     *
     * @param adapterView the spinner adapter
     * @param view the spinner selected
     * @param i item's index in spinner
     * @param l item's row position
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView == playerSpinner) //a player is selected, the screen shows that player's stats
        {
            playerGoals.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getGoals()));
            playerName.setText(playerSpinner.getSelectedItem().toString());
            playerAssists.setText(String.valueOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getAssistsStat()));

            int index = listImageSelector.indexOf(theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID());
            imagePlayerSelector.setSelection( index );

            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  theTeam.getPlayer(playerSpinner.getSelectedItem().toString()).getImageID(), null, null);
            playerImage.setImageResource(id);
        }
        if(adapterView == imagePlayerSelector) //an image is selected, displays that image and sets it to player
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" +  imagePlayerSelector.getSelectedItem().toString(), null, null);
            playerImage.setImageResource(id);

        }
    }

    /**
     * onNothingSelected
     *
     * Does nothign if nothign is selected in the spinner
     *
     * @param adapterView spinner adapter
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //ABSOLUTELY POSITIVELY NOTHING IS DONE
    }
}
