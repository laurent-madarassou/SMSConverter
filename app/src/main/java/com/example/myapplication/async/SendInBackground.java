package async;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import activities.MainActivity;
import re.devboxx.smsconverter.R;

import java.util.ArrayList;

import tools.FileTools;
import tools.LogError;
import tools.SendTools;
import utils.UtilsDialog;

import static android.content.Context.NOTIFICATION_SERVICE;

public class SendInBackground extends AsyncTask<Void, String, Boolean> {
    private Context context;
    private Activity activity;

    private SendTools sentTools ;

    private ArrayList<LogError> listLogError ;
    private LogError logError;



    private String ID = "";
    private String nom = "";
    private String pathZip= "";
    private String pathMailFile = "";
    private String paramFile = "";
    private int sizeParam;
    private int sizeMail ;
    private View view;

    private String mailAddress ="";

    public SendInBackground(Context context,  String pathzip , String pathemailfile, String pathparamfile, View views, String noms, int sizeparam, int sizemail) {
        this.activity = (Activity) context;
        this.context = context;
        this.pathZip = pathzip ;
        this.pathMailFile = pathemailfile ;//mailadress;
        this.paramFile = pathparamfile;
        this.nom = noms;
        this.sizeMail = sizemail;
        this.sizeParam = sizeparam;
        this.logError = new LogError();
        this.view = views ;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

       sentTools = new SendTools(context);

       listLogError = sentTools.sendFile(FileTools.currentZipToSend, FileTools.currentZipCryptedToSend, pathMailFile, paramFile, sizeMail, sizeParam);

        Dump();
        return anyError();
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);


        if (status) {
            UtilsDialog.showSnackbar(
                    context,
                    view,
                    String.format(context.getResources().getString(R.string.sendingEnded) + "\n" + context.getResources().getString(R.string.checkSPAM),
                            nom,
                            nom),
                    null,
                    null,
                    2,4000)
           .show();
        } else {
            UtilsDialog.showSnackbar(
                    context,
                    view,
                    String.format(context.getResources().getString(R.string.sendingFailed),
                            nom,
                            nom),
                    null,
                    null,
                    2,4000)
                    .show();


        }
        createNotification();
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
    { for (int dayvead=0 ; dayvead < listLogError.size(); dayvead++)
        {
            Log.e("SENDING-" + dayvead, listLogError.get(dayvead).getError_message());
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
                .setContentText(String.format(context.getResources().getString(R.string.sendingEnded),
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