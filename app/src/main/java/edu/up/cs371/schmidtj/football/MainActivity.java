package edu.up.cs371.schmidtj.football;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    public Button clearStat; //button to clear the stats fields shown for the team
    public Button addAnotherTeam; //button to create new team based on text fields
    public ImageButton clickToTeamRoster; //button to move to the team roster layout

    public EditText teamWins; //text field to display the # of wins
    public EditText teamLoses; //text field to display # of loses
    public EditText teamName; //text field to display team name

    public ArrayList<String> listOfTeams; //the list of team names used for the teamSpinner
    public ArrayList<String> listImageSelector; //the list of image names used for the imageSpinner

    public Spinner teamSpinner; //displays the list of team names, allows to select a team to show
    public Spinner imageTeamSelector; //displays the names of all images able to be chosen for the team

    public HashMap<String,Team> Teams; //the list of team objects mapped to team names


    /**
     *  onCreate
     *
     *  This method initializes the layout, setting the attributes and setting the
     *  default team to display on start up
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //set the attributes
        clearStat= (Button) findViewById(R.id.clearStat);
        addAnotherTeam= (Button) findViewById(R.id.createTeam);
        clickToTeamRoster= (ImageButton) findViewById(R.id.to_new_activity);

        clearStat.setOnClickListener(this);
        addAnotherTeam.setOnClickListener(this);
        clickToTeamRoster.setOnClickListener(this);

        teamWins= (EditText) findViewById(R.id.teamWins);
        teamLoses= (EditText) findViewById(R.id.teamLoses);
        teamName= (EditText) findViewById(R.id.teamName);


        //create premade teams
        Team defualtTeam1 = new Team("Dragons",0,0);
        defualtTeam1.setImageID("blue_dragons");

        Team defualtTeam2 = new Team("Butterfly",0,0);
        defualtTeam2.setImageID("orange_butterfly");

        Teams=new HashMap<String,Team>();

        Teams.put("Dragons",defualtTeam1);
        Teams.put("Butterfly",defualtTeam2);


        //set up the spinner lists
        listOfTeams = new ArrayList<String>();
        listOfTeams.add("Dragons");
        listOfTeams.add("Butterfly");

        listImageSelector = new ArrayList<String>();
        listImageSelector.add("orange_butterfly");
        listImageSelector.add("pink_butterfly");
        listImageSelector.add("green_cran");
        listImageSelector.add("blue_dragons");
        listImageSelector.add("green_dragons");
        listImageSelector.add("red_dragons");
        listImageSelector.add("orange_fish");
        listImageSelector.add("blue_pegasus");


        //set up the spinners
        teamSpinner = (Spinner) findViewById(R.id.teamSpinner);
        ArrayAdapter<String> teamSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listOfTeams);
        teamSpinner.setAdapter(teamSpinnerAdapter);

        imageTeamSelector = (Spinner) findViewById(R.id.imageTeamSelector);
        ArrayAdapter<String> imageTeamSelectorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listImageSelector);
        imageTeamSelector.setAdapter(imageTeamSelectorAdapter);

        teamSpinner.setOnItemSelectedListener(this);
        imageTeamSelector.setOnItemSelectedListener(this);

        int index = listImageSelector.indexOf(Teams.get("Dragons").getImageID());
        imageTeamSelector.setSelection( index );
    }


    /**
     * onClick
     *
     * Is called whenever a button is clicked, performs the needed action
     * depending on which button is passed as the param (clicked)
     *
     * @param view the clicked button
     */
    @Override
    public void onClick(View view) {

        if(view == clearStat) //empties the text fields
        {
            teamWins.setText("");
            teamLoses.setText("");
            teamName.setText("");
            teamSpinner.setSelection(0);
        }

        if(view == clickToTeamRoster) //opens the TeamBoard activity with the selected team
        {

            Intent intent = new Intent(this, TeamBoard.class);
            intent.putExtra("aTeam", Teams.get( teamSpinner.getSelectedItem().toString() ) );
            //intent.putStringArrayListExtra("Player",listOfTeams);

            //intent.putExtra("hi", Teams);

            startActivityForResult(intent,100);
            //startActivity(intent);
        }

        if(view == addAnotherTeam) //creates new team based on the textfields
        {

            if(String.valueOf(teamWins.getText()).isEmpty() || String.valueOf(teamLoses.getText()).isEmpty() || String.valueOf(teamName.getText()).isEmpty())
                return; //empty fields

            if( listOfTeams.indexOf( String.valueOf(teamName.getText()) ) == -1 ) //creates team
            {
                Team pTemp = new Team(String.valueOf(teamName.getText()), Integer.parseInt(String.valueOf(teamWins.getText())), Integer.parseInt(String.valueOf(teamLoses.getText())));
                pTemp.setImageID( imageTeamSelector.getSelectedItem().toString() );

                Teams.put(String.valueOf(teamName.getText()), pTemp);

                listOfTeams.add(String.valueOf(teamName.getText()));
            }
            else //updates team
            {
                Teams.get(String.valueOf(teamName.getText())).setLoses(String.valueOf(teamLoses.getText()));
                Teams.get(String.valueOf(teamName.getText())).setWins(String.valueOf(teamWins.getText()));
                Teams.get(String.valueOf(teamName.getText())).setImageID( imageTeamSelector.getSelectedItem().toString() );
            }

            teamSpinner.setSelection( listOfTeams.indexOf( String.valueOf(teamName.getText()) ));

        }

    }

    /**
     * onActivityResult
     *
     * Receives the altered team from the TeamBoard activity, updates the hashmap
     *
     * @param requestCode the code passed when the new activity started
     * @param resultCode the code sent back by the new activity on close
     * @param data the intent passed back by the child activity
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100)
        {
            if (resultCode == 1)
            {
                Team tTemp = (Team)  data.getSerializableExtra("returnATeam");
                Teams.put( tTemp.getTeamFullName().toString(), tTemp);
            }
        }
    }


    /**
     * onItemSelected
     *
     * Depending on which spinner is used, sets the related attributes
     * based on what item is selected in the spinner
     *
     * @param adapterView the spinner adapter
     * @param view the spinner selected within
     * @param i the index of the item chosen
     * @param l the row ID of the item
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView == teamSpinner) //sets the team stats fields
        {
            teamLoses.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getLoses()));
            teamName.setText(teamSpinner.getSelectedItem().toString());
            teamWins.setText(String.valueOf(Teams.get(teamSpinner.getSelectedItem().toString()).getWin()));

            int index = listImageSelector.indexOf(Teams.get(teamSpinner.getSelectedItem().toString()).getImageID());
            imageTeamSelector.setSelection( index );
        }
        if(adapterView == imageTeamSelector) //sets image button
        {
            int id = getResources().getIdentifier(this.getPackageName() + ":drawable/" + imageTeamSelector.getSelectedItem().toString(), null, null);
            clickToTeamRoster.setImageResource(id);
        }

    }

    /**
     * onNothingSelected
     *
     * does nothign if nothign is selected
     *
     * @param adapterView the spinner adapter used
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
