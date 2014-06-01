package com.Drexel.TAllaghi.snoozemuze;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CreditsActivity extends Activity {

	private TextView mCreditsParaOne;
	private String creditsParaOne;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		
		mCreditsParaOne=(TextView)findViewById(R.id.credits_para_1);
		
		creditsParaOne="I would like to credit the numerous websites"
				+ " that have helped me create this app as well as"
				+ " give credit to the websites where I recieved the"
				+ " various sounds and picture assets from.\n \n"
				+ " First off I would like to thank The Big Nerd Ranch"
				+ " and their Android Programming book which helped me learn the "
				+ " basics of the platform. And secondly StackOverflow.com"
				+ " for when I couldn't figure out where to go next.\n \n"
				+ " As for assets, I used freesound.org to get the sounds"
				+ " in the noise machine, and I will cite them individually. \n \n"
				+ " Rain and Thunder 4 by FlatHill \n https://www.freesound.org/people/FlatHill/sounds/237729/ \n \n"
				+ "Public Food Court by rivernile7 \n https://www.freesound.org/people/rivernile7/sounds/237685/ \n \n"
				+ "birds forest lake atitlan guatemala arka by molicki \n https://www.freesound.org/people/molicki/sounds/237735/ \n \n"
				+ "calm waves sandy coast 02 140429_0185 by klankbeeld \n https://www.freesound.org/people/klankbeeld/sounds/238061/ \n \n"
				+ " I used WikiMedia Commons for the image assets, of which I will also cite individually below."
				+ " Muir Wood10 by Urban \n http://commons.wikimedia.org/wiki/Forest#mediaviewer/File:Muir_Wood10.JPG \n \n"
				+ "Here comes rain again by Juni \n http://commons.wikimedia.org/wiki/Rain#mediaviewer/File:Here_comes_rain_again.jpg \n \n"
				+ "Gentle waves come in at a sandy beach by Johntex \n http://commons.wikimedia.org/wiki/Waves#mediaviewer/File:Gentle_waves_come_in_at_a_sandy_beach.JPG \n \n"
				+ "White Rose food court by Mtaylor848 \n http://commons.wikimedia.org/wiki/File:White_Rose_food_court.jpg \n \n";
				


		mCreditsParaOne.setText(creditsParaOne);
		
	}
}
