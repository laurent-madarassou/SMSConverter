package activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import re.devboxx.smsconverter.R;


public class AboutActivity extends AppCompatActivity {
    // Load Settings


    // About variables
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.context = this;

        setInitialConfiguration();
        setScreenElements();

    }

    private void setInitialConfiguration() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.action_about);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.PurpleSMSConverter));
            toolbar.setBackgroundColor(getResources().getColor(R.color.PurpleSMSConverter));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.PurpleSMSConverter));


        }

    }

    private void setScreenElements() {
        TextView header = (TextView) findViewById(R.id.header);
        TextView appNameVersion = (TextView) findViewById(R.id.app_name);
        CardView devboxx = (CardView) findViewById(R.id.devboxx);

       // CardView about_googleplay = (CardView) findViewById(R.id.about_googleplay);
        CardView about_googleplus = (CardView) findViewById(R.id.about_googleplus);

        //header.setBackgroundColor();
        appNameVersion.setText(getResources().getString(R.string.app_name)  );
        devboxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String url = "http://www.devboxx.re";
                 Intent i = new Intent(Intent.ACTION_VIEW);
                 i.setData(Uri.parse(url));
                 startActivity(i);
            }
        });


        about_googleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO  UtilsApp.goToGooglePlus(context, "communities/111960842500303983487");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);
    }

}
