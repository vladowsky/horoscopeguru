package hr.horoskop.horoskop.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.LoveCalculator;
import hr.horoskop.horoskop.model.Love;
import hr.horoskop.horoskop.utils.CustomProgress;
import hr.horoskop.horoskop.utils.Utils;

public class LoveCalculatorResultActivity extends AppCompatActivity {

    private String firstName, secondName;
    private Love love;
    private CustomProgress pbLove, pbGoals, pbFidelity, pbInterests, pbCommunication, pbFriendship,
    pbFun, pbPhysical, pbSex, pbBreak, pbSincerity;

    private TextView tvTitle, tvPercentage, tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_calculator_result);

        initUI();

        if(getIntent().getExtras().getString("firstName") != null){
            firstName = getIntent().getExtras().getString("firstName");
            secondName = getIntent().getExtras().getString("secondName");

            LoveCalculator loveCalculator = new LoveCalculator(getApplicationContext());
            loveCalculator.initLove();;
            love = loveCalculator.calculateLove();

            setLoveData(love);

        }

    }

    private void initUI() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_love_result);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ljubavni kalkulator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tv_love_calc_title);
        tvPercentage = (TextView) findViewById(R.id.tv_love_calc_percentage);
        tvText = (TextView) findViewById(R.id.tv_love_calc_text);

        pbLove = (CustomProgress) findViewById(R.id.pb_calc_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));
        pbGoals = (CustomProgress) findViewById(R.id.pb_calc_goals);
        pbGoals.setProgressColor(getResources().getColor(R.color.purple));
        pbFidelity = (CustomProgress) findViewById(R.id.pb_calc_fidelity);
        pbFidelity.setProgressColor(getResources().getColor(R.color.blue));
        pbInterests = (CustomProgress) findViewById(R.id.pb_calc_interests);
        pbInterests.setProgressColor(getResources().getColor(R.color.green));
        pbCommunication = (CustomProgress) findViewById(R.id.pb_calc_comunication);
        pbCommunication.setProgressColor(getResources().getColor(R.color.orange));
        pbFriendship = (CustomProgress) findViewById(R.id.pb_calc_friendship);
        pbFriendship.setProgressColor(getResources().getColor(R.color.purple));
        pbFun = (CustomProgress) findViewById(R.id.pb_calc_fun);
        pbFun.setProgressColor(getResources().getColor(R.color.blue));
        pbPhysical = (CustomProgress) findViewById(R.id.pb_calc_physical);
        pbPhysical.setProgressColor(getResources().getColor(R.color.green));
        pbSex = (CustomProgress) findViewById(R.id.pb_calc_sex);
        pbSex.setProgressColor(getResources().getColor(R.color.orange));
        pbBreak = (CustomProgress) findViewById(R.id.pb_calc_break);
        pbBreak.setProgressColor(getResources().getColor(R.color.purple));
        pbSincerity = (CustomProgress) findViewById(R.id.pb_calc_sincerity);
        pbSincerity.setProgressColor(getResources().getColor(R.color.blue));
    }

    private void setLoveData(Love love) {
        tvTitle.setText(firstName + " i " + secondName);
        tvPercentage.setText(String.valueOf(love.getPercentage()) + "%");
        tvText.setText(love.getText());
        pbLove.setMaximumPercentage(getSliderPercentage());
        pbGoals.setMaximumPercentage(getSliderPercentage());
        pbFidelity.setMaximumPercentage(getSliderPercentage());
        pbInterests.setMaximumPercentage(getSliderPercentage());
        pbCommunication.setMaximumPercentage(getSliderPercentage());
        pbFriendship.setMaximumPercentage(getSliderPercentage());
        pbFun.setMaximumPercentage(getSliderPercentage());
        pbPhysical.setMaximumPercentage(getSliderPercentage());
        pbSex.setMaximumPercentage(getSliderPercentage());
        pbBreak.setMaximumPercentage(getSliderPercentage());
        pbSincerity.setMaximumPercentage(getSliderPercentage());

    }

    private float getSliderPercentage(){
        float percentage = Utils.randIntForLoveCalculator(love.getPercentage());
        percentage = percentage/100;
        return percentage;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //

                onBackPressed();
                //NavUtils.navigateUpTo(this, new Intent(this,DetailsActivity.class));
                break;



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }


    }
}
