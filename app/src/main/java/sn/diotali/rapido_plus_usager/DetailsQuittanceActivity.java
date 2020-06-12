package sn.diotali.rapido_plus_usager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import sn.diotali.rapido_plus_usager.utils.Constants;

public class DetailsQuittanceActivity extends AppCompatActivity {

    ImageView btnSuivant;
    ImageView menu_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_quittance);

        getEllementsById();

        btnSuivant.setOnClickListener(new DetailsButton());
        menu_bar = findViewById(R.id.menu_bar);

        menu_bar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

    }

    private class DetailsButton implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.detail_btn_suivant : {
                    Log.d(this.getClass().getName(),"DetailsButton implements Button.OnClickListener");
                    Intent intent = new Intent();
                    setResult(Constants.ResponseActivty.OK,intent);
                    finish();
                    break;
                }

            }
        }
    }

    public void getEllementsById(){
        btnSuivant = findViewById(R.id.detail_btn_suivant);

    }

}
