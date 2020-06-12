package sn.diotali.rapido_plus_usager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class NavBarActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_IMAGE = 100;
    //TextView nom_prenom, adresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        ButterKnife.bind(this);

        //nom_prenom = findViewById(R.id.nom_prenom);
        //adresse = findViewById(R.id.adresse);
        //photo_profil = findViewById(R.id.img_profil);

        //nom_prenom.setText(Constants.newUser.getFirstName() + " " + Constants.newUser.getLastName());
        //adresse.setText(Constants.newUser.getAddress());


        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.menu_deconnect).setOnClickListener(this);
        findViewById(R.id.menu_compte).setOnClickListener(this);
        findViewById(R.id.menu_pwd).setOnClickListener(this);
        findViewById(R.id.menu_aide).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.menu_deconnect:
                Intent intent = new Intent(getApplicationContext(), DiotaliLogin.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.menu_compte:
                intent = new Intent(getApplicationContext(), InfoCompteActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.menu_aide:
                intent = new Intent(getApplicationContext(), AideActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.menu_pwd:
                intent = new Intent(getApplicationContext(), ModifierPwdActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }


}
