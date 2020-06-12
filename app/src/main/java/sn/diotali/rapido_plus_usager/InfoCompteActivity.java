package sn.diotali.rapido_plus_usager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import sn.diotali.rapido_plus_usager.services.ServiceResult;
import sn.diotali.rapido_plus_usager.types.User;
import sn.diotali.rapido_plus_usager.utils.Constants;

public class InfoCompteActivity extends DiotaliMain implements View.OnClickListener{

    public static final int REQUEST_IMAGE = 100;
    EditText txt_cni, txt_nom, txt_prenom, txt_tel, txt_adresse, txt_email;
    TextView txt_error;
    SharedPreferences sharedPreferences;

    @BindView(R.id.img_profil)
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_compte);
        ButterKnife.bind(this);

        loadProfileDefault();

        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_modifier).setOnClickListener(this);

        txt_error = findViewById(R.id.txt_v_error);
        txt_error.setVisibility(View.INVISIBLE);

        txt_cni = findViewById(R.id.txt_cni);
        txt_nom = findViewById(R.id.txt_nom);
        txt_prenom = findViewById(R.id.txt_prenom);
        txt_adresse = findViewById(R.id.txt_adresse);
        txt_tel = findViewById(R.id.txt_tel);
        txt_email = findViewById(R.id.txt_email);

        txt_cni.setText(Constants.newUser.getNin());
        txt_nom.setText(Constants.newUser.getLastName());
        txt_prenom.setText(Constants.newUser.getFirstName());
        txt_adresse.setText(Constants.newUser.getAddress());
        txt_tel.setText(Constants.newUser.getPhone());
        txt_email.setText(Constants.newUser.getEmail());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btn_modifier:
                String cni = txt_cni.getText().toString();
                String nom = txt_nom.getText().toString();
                String prenom = txt_prenom.getText().toString();
                String email = txt_email.getText().toString();
                String tel = txt_tel.getText().toString();
                String adresse = txt_adresse.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String regex = " ";
                String replacement = "";

                if (cni.isEmpty() || cni.replaceAll(regex, replacement).isEmpty()) {
                    txt_cni.setError("CNI");
                } else if (cni.length() != 13) {
                    txt_cni.setError("Veuillez saisir un NIN valide !");
                } else if (nom.isEmpty() || nom.replaceAll(regex, replacement).isEmpty()) {
                    txt_nom.setError("Nom");
                } else if (prenom.isEmpty() || prenom.replaceAll(regex, replacement).isEmpty()) {
                    txt_prenom.setError("Prénom");
                } else if (email.isEmpty() || email.replaceAll(regex, replacement).isEmpty()) {
                    txt_email.setError("Email");
                }else if (!email.matches(emailPattern)) {
                    txt_email.setError("Email invalide");
                } else if (tel.isEmpty() || tel.replaceAll(regex, replacement).isEmpty()) {
                    txt_tel.setError("Téléphone");
                } else if (tel.length() < 9 || tel.length() > 14) {
                    txt_tel.setError("Veuillez saisir un numéro valide !");
                } else if (adresse.isEmpty() || adresse.replaceAll(regex, replacement).isEmpty()) {
                    txt_adresse.setError("Adresse");
                }else {
                    User response = new User();
                    response.setFirstName(prenom);
                    response.setLastName(nom);
                    response.setAddress(adresse);
                    response.setPhone(tel);
                    response.setNin(cni);
                    response.setEmail(email);
                    Constants.newUser = response;

                    sharedPreferences = getSharedPreferences("MyConfigMarchand", MODE_PRIVATE);
                    sharedPreferences.edit().putString("FirstNameStorage", response.getFirstName()).commit();
                    sharedPreferences.edit().putString("LastNameStorage", response.getLastName()).commit();
                    sharedPreferences.edit().putString("AddressStorage", response.getAddress()).commit();
                    sharedPreferences.edit().putString("PhoneStorage", response.getPhone()).commit();
                    sharedPreferences.edit().putString("NinStorage", response.getNin()).commit();
                    sharedPreferences.edit().putString("EmailStorage", response.getEmail()).commit();

                    txt_error.setTextColor(Color.GREEN);
                    txt_error.setText("Informations modifié avec succés");
                    txt_error.setVisibility(View.VISIBLE);
                }
        }
    }

    @Override
    public void sendTaskResponse(ServiceResult serviceResult) {
        try {
            Log.d("DIOTALI LOGIN", "receiving response");
            if (Constants.Methods.UPDATE_INFO.equals(serviceResult.getMethod())) {
                if (Constants.ResponseStatus.OK == serviceResult.getStatus()) {
                    User response = (User) serviceResult;
                    Log.d(this.getClass().getName(), "update sendTaskResponse 1");
                    sharedPreferences = getSharedPreferences("MyConfigMarchand", MODE_PRIVATE);
                    sharedPreferences.edit().putString("FirstNameStorage", response.getFirstName()).commit();
                    sharedPreferences.edit().putString("LastNameStorage", response.getLastName()).commit();
                    sharedPreferences.edit().putString("AddressStorage", response.getAddress()).commit();
                    sharedPreferences.edit().putString("PhoneStorage", response.getPhone()).commit();
                    sharedPreferences.edit().putString("NinStorage", response.getNin()).commit();
                    sharedPreferences.edit().putString("EmailStorage", response.getEmail()).commit();
                    Log.d(this.getClass().getName(), "update sendTaskResponse 2");
                    Constants.newUser.setEmail(response.getEmail());
                    Constants.newUser.setFirstName(response.getFirstName());
                    Constants.newUser.setLastName(response.getLastName());
                    Constants.newUser.setAddress(response.getAddress());
                    Constants.newUser.setPhone(response.getPhone());
                    Constants.newUser.setNin(response.getNin());
                    Log.d(this.getClass().getName(), "update sendTaskResponse 3");
                    txt_error.setTextColor(Color.GREEN);
                    txt_error.setText("Informations modifié avec succés");
                    txt_error.setVisibility(View.VISIBLE);
                    Log.d(this.getClass().getName(), "update sendTaskResponse 4");
                } else {
                    Log.d(this.getClass().getName(), "sendTaskResponse error");
                    txt_error.setText(serviceResult.getMessage());
                    txt_error.setVisibility(View.VISIBLE);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProfile(String url) {
        Log.d("InfoCompteActivity", "Image cache path: " + url);
        Constants.PROFILE = url;
        GlideApp.with(this).load(Constants.PROFILE)
                .into(imgProfile);
        imgProfile.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void loadProfileDefault() {
        if (Constants.PROFILE.equals("")) {
            GlideApp.with(this).load(R.drawable.icone_user)
                    .into(imgProfile);
        }else {
            GlideApp.with(this).load(Constants.PROFILE)
                    .into(imgProfile);

        }

        imgProfile.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    @OnClick({R.id.img_plus, R.id.img_profil})
    void onProfileImageClick() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(InfoCompteActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(InfoCompteActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoCompteActivity.this);
        builder.setTitle("Grant Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GO TO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}
