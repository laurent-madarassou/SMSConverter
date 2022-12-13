package async;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import activities.MainActivity;
import re.devboxx.smsconverter.R;
import activities.ThreadActivity;

import java.util.ArrayList;

import tools.ConvertTools;
import tools.LogError;
import utils.UtilsDialog;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ConvertInBackground extends AsyncTask<Void, String, Boolean> {
    private Context context;
    private Activity activity;
    private AlertDialog dialog;
    private View view;

    private ArrayList<LogError> listLogError ;
    private LogError logError;

    private static ProgressDialog progressDialog;

    private String ID = "";
    private String nom = "";
    Bitmap bitMap = null;



    public ConvertInBackground(Context context,  String ID , String nom, View v, String photo) {
        this.activity = (Activity) context;
        this.context = context;

        if (photo != null) {
            //ImageProcessing imageProcessing = new ImageProcessing();
            String imageUri = photo;
            try {
                bitMap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(imageUri));
            } catch (Exception e) {

            }
        }

        this.nom = nom;
        this.ID = ID;
        this.logError = new LogError();
        this.view = v;
    }


    public ConvertInBackground(Context context, ProgressDialog progressdialog, String ID , String nom, View v) {
        this.activity = (Activity) context;
        this.context = context;
        this.progressDialog = progressdialog;
        this.nom = nom;
        this.ID = ID;
        this.logError = new LogError();
        this.view = v;

    }

    public ConvertInBackground(Context context, String ID , String nom) {
        this.activity = (Activity) context;
        this.context = context;

        this.nom = nom;
        this.ID = ID;
        this.logError = new LogError();

    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        //ThreadActivity.alertDialog.show();

        ThreadActivity.IsConversionRunning = true;

        ConvertTools convertTools = new ConvertTools(context, this.ID, nom);
        int numberOfThreadForConvertProgress = convertTools.chargeConversation();
        listLogError = convertTools.createConversions(numberOfThreadForConvertProgress);

/*
          progressDialog =
           UtilsDialog.showConversionWithProgress
            (context,"Titre","Contenu", numberOfThreadForConvertProgress);



          /*----- TODO ??????????
        final Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDialog.incrementProgressBy(10);
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog
                            .getMax()) {
                        Thread.sleep(200);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog
                                .getMax()) {
                            progressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



        /*----- FIN TODO ??????????
          progressDialog.show();
  */



        //publishProgress("");

        Dump();
        return anyError();
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);
        ThreadActivity.IsConversionRunning = false;

        if (ThreadActivity.IsActivityRunning)
        {
            if (status) {
                UtilsDialog.showSnackbar(
                        context,
                        view,
                        String.format(context.getResources().getString(R.string.conversionEnded),
                                nom,
                                nom),
                        null,
                        null,
                        2, 4000)
                        .show();



            } else {
                UtilsDialog.showSnackbar(
                        context,
                        view,
                        String.format(context.getResources().getString(R.string.conversionFailed),
                                nom,
                                nom),
                        null,
                        null,
                        2, 4000)
                        .show();
            }
        }
        /*------------------------------------------------------------------------------------------*/
        /*---------------------------Notification --------------------------------------------------*/
        /*------------------------------------------------------------------------------------------*/

        createNotification();


    }

    @Override
    protected void onProgressUpdate(String... values) { //TODO a effacer 03/2018
        super.onProgressUpdate(values);
        if (values != null && values.length > 0) {
            //Your View attribute object in the activity
            // already initialized in the onCreate!
            String v = values[0].toString();
            int gvf= 5;
        }
    }

    public boolean anyError()
    {
        boolean retour = true;

        for (int dayvead=0 ; dayvead < listLogError.size(); dayvead++)
        {
            retour = listLogError.get(dayvead).getError_status();
        }
        return retour;
    }
    public void Dump()
    {
        for (int dayvead=0 ; dayvead < listLogError.size(); dayvead++)
        {Log.e("CONVERSION-" + dayvead, listLogError.get(dayvead).getError_message());
        }
    }

    public void createNotification() {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(context)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(String.format(context.getResources().getString(R.string.conversionEnded),
                        nom,
                        nom))
                .setSmallIcon(R.drawable.logosmall)
                .setContentIntent(pIntent)
                .addAction(R.drawable.logosmall, "Call", pIntent)
                //.addAction(R.drawable.logosmall, "More", pIntent)
                //.addAction(R.drawable.logosmall, "And more", pIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }
}