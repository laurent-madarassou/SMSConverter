/*******************************************************
 Nom ......... : UtilsDialog
 Role ........ : Créer différents types de dialog
 Auteur ...... : dayvead
 Version ..... : V1.0 du 13/12/2013
 Licence ..... : Commercial
 ********************************************************/

package utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import re.devboxx.smsconverter.R;

import java.io.File;

public class UtilsDialog {


    private final static int snackDuration = 3000;
    public static AlertDialog  showTitleContent(Context context, String title, String content) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setPositiveButton(context.getResources().getString(android.R.string.ok), null);
        //builder.setNegativeButton(R.string.cancel, null);
        builder.setCancelable(false);
        return builder.show();

    }
    //TODO SOURCE http://alexzh.com/tutorials/material-style-for-dialogs-in-android-application/


    public static ProgressDialog showConversionWithProgress(Context context, String title, String content, int max) {


        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(max); // Progress Dialog Max Value
        progressDialog.setMessage(content); // Setting Message
        progressDialog.setTitle(title); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
        progressDialog.setCancelable(false);

        return progressDialog;



    }


   public static Snackbar showSnackbar(Context context ,View view, String text, @Nullable String buttonText, @Nullable final File file, Integer style , int dura) {
       Snackbar snackBar;

       if (dura != 0)
       {
           snackBar =  Snackbar.make(view, text, dura);
       }
       else
       {
           snackBar =  Snackbar.make(view, text, snackDuration);
       }
       snackBar.setActionTextColor(Color.WHITE);


       View snackbarView = snackBar.getView();
       TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
       textView.setTextColor(Color.WHITE);
       textView.setAllCaps(false);
       textView.setTextSize(12);

// styling for background of snackbar
      // View sbView = snackbarView;
       snackbarView.setBackgroundColor(context.getResources().getColor(R.color.PurpleSMSConverterDark));



        return snackBar;
    }
}
