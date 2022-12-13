package activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.appcompat.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import re.devboxx.smsconverter.R;

import async.LoadFontPDFJetInBackground;
import tools.FileTools;
import utils.UtilsApp;
import utils.UtilsDialog;


public class StartActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_READ = 1;

    // Load Settings
    private boolean permissionGranted = false;

    // Configuration variables
    private Boolean doubleBackToExitPressedOnce = false;
    private Toolbar toolbar;
    private Activity activity;
    private Context context;
    private ImageView imageviewLogo;
    private TextView infoStartPermission;
    private Button btnStart;
    private boolean telephonyEnable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitystart);
        context = this;
        activity = this;
        telephonyEnable = true; //  checkTelephonyEnable();


        btnStart = (Button) findViewById(R.id.btnStart);
        imageviewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        infoStartPermission = (TextView) findViewById(R.id.infoStartPermission);

        permissionGranted = checkAndAddPermissions(activity);
        showButtonStartMainActivity(permissionGranted);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            if (telephonyEnable)
            {
                if (!permissionGranted) {


                    if (Build.VERSION.SDK_INT >= 11) {
                        recreate();
                    } else {
                        Intent intentStartActivity = getIntent();
                        intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);

                        startActivity(intentStartActivity);
                        overridePendingTransition(0, 0);
                    }

                } else
                {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);

                }
            } else
            {
                UtilsDialog.showSnackbar(
                        context,
                        view,
                        context.getResources().getString(R.string.sorryNoTelephony),
                        null,
                        null,
                        2,5000)
                        .show();
            }

            }
        });


        imageviewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (telephonyEnable) {
                    if (!permissionGranted) {


                        if (Build.VERSION.SDK_INT >= 11) {
                            recreate();
                        } else {
                            Intent intentStartActivity = getIntent();
                            intentStartActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();
                            overridePendingTransition(0, 0);

                            startActivity(intentStartActivity);
                            overridePendingTransition(0, 0);
                        }

                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);

                    }
                } else
                {
                    UtilsDialog.showSnackbar(
                            context,
                            view,
                            context.getResources().getString(R.string.sorryNoTelephony),
                            null,
                            null,
                            2,5000)
                            .show();
                }
            }
        });

    }

    private boolean checkAndAddPermissions(Activity activity) {
        return UtilsApp.checkPermissions(activity);

    }
    private void showButtonStartMainActivity(Boolean permissionGranted)
    {

        if (permissionGranted)
        {
            infoStartPermission.setVisibility(View.INVISIBLE);
            btnStart.setText(context.getResources().getString(R.string.start_main));
            btnStart.setVisibility(View.INVISIBLE);
        }else
        {
            //infoStartPermission.setVisibility(View.VISIBLE);
            btnStart.setText(context.getResources().getString(R.string.start_application));
            btnStart.setVisibility(View.INVISIBLE);
        }

    }
    /* -------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionGranted = true;

        if (permissions.length == 0) {
            return;
        }

        if (grantResults.length > 0) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = false;
                    break;
                }
            }
        }
        if (!permissionGranted) {
            boolean somePermissionsForeverDenied = false;
            for (String permission : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    //denied
                    Log.e("denied", permission);
                } else {
                    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                        //allowed
                        Log.e("allowed", permission);
                    } else {
                        //set to never ask again
                        Log.e("set to never ask again", permission);
                        somePermissionsForeverDenied = true;
                    }
                }
            }
            if (somePermissionsForeverDenied) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle(context.getResources().getString(R.string.permissionRequiredTitle))
                        .setMessage(context.getResources().getString(R.string.permissionRequired))
                        .setPositiveButton(context.getResources().getString(R.string.permissionRequiredParametre), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            }
        } else {
            switch (requestCode) {
                //act according to the request code used while requesting the permission(s).
            }

        }


        showButtonStartMainActivity(permissionGranted);


        /*for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Log.d("-> Permissions", "Permission Granted: " + permissions[i]);
            } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                Log.d("-> Permissions", "Permission Denied: " + permissions[i]);
                permissionGranted = false;
            }
        }

        for(String permission: permissions){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
                //denied
                permissionGranted = false;
            }else{
                if(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
                    //allowed
                    int h = 0 ;
                } else{
                    //set to never ask again
                    permissionGranted = false;
                    Log.e("set to never ask again", permission);
                }
            }
        }
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.tap_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
    private boolean checkTelephonyEnable() {
        PackageManager mgr = context.getPackageManager();
        return  mgr.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);

    }
}
