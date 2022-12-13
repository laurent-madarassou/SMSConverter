package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import async.ConvertInBackground;
import async.LoadFontPDFJetInBackground;
import async.SendInBackground;
import re.devboxx.smsconverter.BuildConfig;
import tools.ConnexionTools;
import tools.ConvertTools;
import tools.CryptTools;
import tools.FileTools;
import tools.ImageProcessing;
import utils.UtilsDialog;

import static tools.FileTools.noConversions;
import re.devboxx.smsconverter.R;


public class ThreadActivity extends AppCompatActivity {
    // Load Settings



    //TODO Orientation Droite Gauche selon les langues qui s'écrivent par la droite ex: hebrew arabic
    //TODO https://developer.android.com/training/basics/supporting-devices/languages.html
    //TODO https://phraseapp.com/blog/posts/fundamental-android-app-localization-guide/


    // General variables
    private String ID;
    private String nom;
    private String tel;
    private String photo;
    private String total;
    private String buildFlavor ;
    // Configuration variables
    private int UNINSTALL_REQUEST_CODE = 1;
    public  Context context;
    private Activity activity;
    private MenuItem item_favorite;
    // UI variables
    //private FloatingActionsMenu fab;

    private ProgressDialog progressDiaglog ;
    public static AlertDialog alertDialog ;
    private RelativeLayout mRelativeLayout;

    private PopupWindow popUp;


    public static String aEffacerCodeCountry ;

    private FileTools fileTools ;
    private BroadcastReceiver mIRNetwork = null;
    private IntentFilter mIFNetwork = null;
    private boolean wifiState ;
    private boolean connexionState ;
    public static boolean IsConversionRunning =false;
    public static boolean IsActivityRunning = true;



    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    AutoCompleteTextView autoComplete;
    public ArrayList<String> MailAddressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_detail2);

        this.context = this;
        this.activity = (Activity) context;
        this.IsActivityRunning = true;

        getInitialConfiguration();
        setInitialConfiguration();
        setScreenElements();
        setListnerOpeningApplication();
        //LoadFontForPDFConversion();

        //TODO Hack pour test PDFJet A ENLEVER !
        //new ConvertInBackground(context,  "66", "Jane",null, "null").execute();
    }

    private void getInitialConfiguration() {
        ID = getIntent().getStringExtra("ID");
        nom = getIntent().getStringExtra("nom");
        tel = getIntent().getStringExtra("tel");
        photo = getIntent().getStringExtra("photo");
        total = getIntent().getStringExtra("total");
        buildFlavor = BuildConfig.FLAVOR;

        /*ID = "66";
        nom = "Jane";
                tel = "035388 (0692)";
        total = "258" ;
*/
        int i = 0;
        ConnexionTools connexiontools = new ConnexionTools(this);

        try
        {
            wifiState = connexiontools.getWifi().isConnected();
            connexionState = connexiontools.getConnexion().isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileTools = new FileTools(context, ID, nom);
        try
        {
            fileTools.ReadFileMailAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MailAddressList= fileTools.MailAddressList;

    }

    private void setInitialConfiguration() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null ) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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



        autoComplete = (AutoCompleteTextView)findViewById(R.id.autoCompleteMail);
        ArrayList<String> emailS = new ArrayList<String>();
        try {

            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,MailAddressList);
            autoComplete.setThreshold(1);
            autoComplete.setAdapter(adapter);

        } catch (Exception err)
        {
            Log.e("",err.getLocalizedMessage());
        }

        //TODO a enlever
        autoComplete.setText(context.getResources().getString(R.string.enterMail));
        //autoComplete.requestFocus();
        autoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 if((autoComplete.getText().toString().equals(context.getResources().getString(R.string.enterMail))) ) {
                     autoComplete.setText("");
                 }

            }
        });
        autoComplete.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                                && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            if((autoComplete.getText().toString().equals(context.getResources().getString(R.string.enterMail))) ) {
                                autoComplete.setText("");
                            }
                            return true;
                        } else
                            if((autoComplete.getText().toString().equals(context.getResources().getString(R.string.enterMail))) ) {
                                autoComplete.setText("");
                            }
                            return false;
                    }
                    }
            );
        TextView sendDetail = (TextView) findViewById(R.id.send_detail);

        //RelativeLayout linearLayout = findViewById(R.id.cardLinearLayout);
        CardView sent_card = (CardView) findViewById(R.id.sent_card);
        CardView convert_card = (CardView) findViewById(R.id.convert_card);
        CardView files_card = (CardView) findViewById(R.id.files_card);


        TextView header = (TextView) findViewById(R.id.header);
        ImageView photoView = (ImageView) findViewById(R.id.layout_photo);
        TextView nomView = (TextView) findViewById(R.id.layout_nom);
        TextView telView = (TextView) findViewById(R.id.layout_tel);
        /*-- Cards */

        convert_card.requestFocus();


        if (!wifiState)
        {
            if(!connexionState)
            {
                sent_card.setEnabled(false);
                sendDetail.setVisibility(View.VISIBLE);
                sendDetail.setText(context.getResources().getString(R.string.ConnexionNotOk));
                autoComplete.setVisibility(View.INVISIBLE);
            } else
            {
                //TODO Message pas de connexion WIFI : Envoi ?

                sendDetail.setVisibility(View.INVISIBLE);
                autoComplete.setVisibility(View.VISIBLE);
            }
        }
        //fab = (FloatingActionsMenu) findViewById(R.id.fab);
        /* -- Récup Photo */
        Bitmap bitMap = null;
        if (photo != null) {
            ImageProcessing imageProcessing = new ImageProcessing();
            String imageUri = photo;
            try {
                bitMap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(imageUri));
                photoView.setImageBitmap(bitMap);
                imageProcessing.scaleImageConstraint(photoView, this.context);
            } catch (Exception e) {
                photoView.setImageResource(R.drawable.nouser);
                imageProcessing.scaleImageConstraint(photoView, this.context);
            }
        } else
        {
            ImageProcessing imageProcessing = new ImageProcessing();
            //TODO A VOIR POUR LOGO PAS DE CORRESPONDANCE photoView.setImageResource(R.drawable.ic_android);
            photoView.setImageResource(R.drawable.nouser);
            imageProcessing.scaleImageConstraint(photoView, this.context);

        }


        /* ------- */

        nomView.setText(nom);
        telView.setText(tel);
        header.setBackgroundColor(getResources().getColor(R.color.PurpleSMSConverter));


       /* --------------------------------------------------------------------------------------------------------------*/
        /* -------------- Conversion ------------------------------------------------------------------------------------*/
        /* --------------------------------------------------------------------------------------------------------------*/
        convert_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*alertDialog = UtilsDialog.showTitleContent(context
                        , getResources().getString(R.string.conversionRunning)
                        , getResources().getString(R.string.conversionRunningDetail));
                 */
                if (!IsConversionRunning )
                {
                    UtilsDialog.showSnackbar(
                            context,
                            view,
                            context.getResources().getString(R.string.conversionRunningWaitForNotification),
                            null,
                            null,
                            2,5000)
                            .show();

                    new ConvertInBackground(context,  ID, nom , view, photo).execute();
                } else
                {

                   UtilsDialog.showSnackbar(
                           context,
                            view,
                            context.getResources().getString(R.string.aConversionIsRunningWaitForNotification),
                            null,
                            null,
                            2,3000)
                            .show();
                }



            }
        });
       /* --------------------------------------------------------------------------------------------------------------*/
        /* -------------- Envoi ------------------------------------------------------------------------------------*/
        /* --------------------------------------------------------------------------------------------------------------*/
        sent_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if (!noConversions(fileTools.currentZipToSend)) {
                 String email = autoComplete.getText().toString();
                 if (!isMailWellFormatted(email)) {
                     Toast.makeText(context, context.getResources().getString(R.string.errorMailNotFormatted), Toast.LENGTH_LONG).show();
                     autoComplete.requestFocus();
                 } else {
                     if (!isMailInFile(email)) {

                         try {

                             fileTools.PutFileMailAddress(email);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }

                     String h = new String(CryptTools.decryptX(CryptTools.encryptX(email)));
                     String sujet = getResources().getString(R.string.body) + " : " + nom + " - " + tel;
                     String titre = getResources().getString(R.string.mailtitle) + " : " + nom;
                     String param = sujet + ";" + titre;


                     //sizeCryptedEmail
                     byte[] paramCrypted = CryptTools.encryptX(param);
                     byte[] mailCrypted  = CryptTools.encryptX(email);

                     fileTools.createParamFile(paramCrypted);
                     fileTools.MailPutInAFile(mailCrypted);

                     new SendInBackground(context , fileTools.currentZipToSend , fileTools.currentMailInFileToSend, fileTools.currentParamToSend, view, nom , paramCrypted.length, mailCrypted.length).execute();

                     UtilsDialog.showSnackbar(
                             context,
                             view,
                             context.getResources().getString(R.string.sendingRunning) + " - " +
                                     getResources().getString(R.string.sendingRunningDetail)
                             ,
                             null,
                             null,
                             2, 5000)
                             .show();


                 }
             } else // pas de conversion en cours
             {
                 UtilsDialog.showSnackbar(
                         context,
                         view,
                         context.getResources().getString(R.string.makeConversionFirst)
                         ,
                         null,
                         null,
                         2, 5000)
                         .show();
             }
            }
        });
    }

    private void setListnerOpeningApplication()
    {
        ImageView pdfView = (ImageView) findViewById(R.id.showFilePDF);
        ImageView xmlView = (ImageView) findViewById(R.id.showFileXML);
        ImageView csvView = (ImageView) findViewById(R.id.showFileCSV);
        ImageView htmlView = (ImageView) findViewById(R.id.showFileHTML);
        ImageView txtView = (ImageView) findViewById(R.id.showFileTXT);
        ImageView jsonView = (ImageView) findViewById(R.id.showFileJSON);

        boolean gratuit = true;
        String type = "";

        if (buildFlavor.equals("payant") )  gratuit = false;

        String f = fileTools.PDFdirectoryName + fileTools.UserDirectory + ".pdf";
        setListnerForImageView(pdfView, f, gratuit,"pdf");

        f = fileTools.CSVdirectoryName + fileTools.UserDirectory + ".csv";
        setListnerForImageView(csvView, f, gratuit,"csv");

        f = fileTools.XMLdirectoryName + fileTools.UserDirectory + ".xml";
        setListnerForImageView(xmlView, f, gratuit,"xml");

        f = fileTools.HTMLdirectoryName + fileTools.UserDirectory + "-bubble.html";
        setListnerForImageView(htmlView, f, gratuit,"html");

        f = fileTools.JSONdirectoryName + fileTools.UserDirectory + ".json";
        setListnerForImageView(jsonView, f, gratuit,"json");

        f = fileTools.TXTdirectoryName + fileTools.UserDirectory + ".txt";
        setListnerForImageView(txtView, f, true,"txt");


    }

    private void setListnerForImageView(ImageView iV,  String fichier, boolean gratuit, String type)
    {
        final String fichierFinal = fichier ;
        final String typeFinal = type;
        final ImageView iVFinal = iV;
        final boolean gratuitFinal = gratuit;

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (noConversions(fileTools.currentZipToSend)) {
                        showNoConversion(view);
                    } else
                    {
                        if (!gratuitFinal)
                        {
                            LanceApplicationAssociee(fichierFinal);

                        } else
                        {
                            if (!typeFinal.equals("txt"))
                            {
                                createPopUp();
                            } else
                            {
                                LanceApplicationAssociee(fichierFinal);
                            }

                        }
                    }
            }
        });

    }

    private void createPopUp()
    {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.popuplayout, null);
            popUp = new PopupWindow(
                    customView,
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );



            try {
                mRelativeLayout =  customView.findViewById(R.id.rl_custom_layout);
            } catch (Exception e)
            {
                String error = e.getLocalizedMessage() ;
            }


            // Set an elevation value for popup window
            // Call requires API level 21
            if (Build.VERSION.SDK_INT >= 21) {
                popUp.setElevation(5.0f);
            }
            ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

            // Set a click listener for the popup window close button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    popUp.dismiss();
                }
            });
            popUp.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);


    }

    private void showNoConversion(View view)
    {
        UtilsDialog.showSnackbar(
                context,
                view,
                context.getResources().getString(R.string.makeConversionFirst)
                ,
                null,
                null,
                2, 5000)
                .show();
    }

    private void LoadFontForPDFConversion()
    {
        //TODO pré-Chargement de 'la' Font pour la Conversion PDF
        String pdfPath = fileTools.PDFdirectoryName  + fileTools.UserDirectory + ".pdf";
        LoadFontPDFJetInBackground lfpdfjib = new LoadFontPDFJetInBackground(context, pdfPath);
        lfpdfjib.execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.i("App", "OK");
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("App", "CANCEL");
            }
        }
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
            overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_navigation, menu); //TODO O AZAR
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // item_favorite = menu.findItem(R.id.action_favorite);
        int i = 0 ;
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            //case R.id.action_favorite:

               // return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private boolean isMailWellFormatted(String email)
    {

        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    private boolean isMailInFile(String email)
    {

        boolean result = false;
        if(MailAddressList != null)
        {

            for(int i =0 ; i < MailAddressList.size() ; i++)
            {
                if (MailAddressList.get(i).equals(email)) result = true;
            }
        }
        return result;
    }

    @Override
    public void onStop()
    {
        super.onStop();
        IsActivityRunning = false;
    }
    @Override
    public void onResume()
    {
        super.onResume();


        mIRNetwork = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {

                updateSendCard();
            }
        };

        mIFNetwork = new IntentFilter();
        mIFNetwork.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION); //"android.net.conn.CONNECTIVITY_CHANGE"
        registerReceiver(mIRNetwork, mIFNetwork);

    }
    @Override
    public void onPause()
    {
        super.onPause();
        unregisterReceiver(mIRNetwork);

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v instanceof EditText) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            // calculate the relative position of the clicking position against the position of the view
            float x = event.getRawX() - scrcoords[0];
            float y = event.getRawY() - scrcoords[1];

            // check whether action is up and the clicking position is outside of the view
            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < 0 || x > v.getRight() - v.getLeft()
                    || y < 0 || y > v.getBottom() - v.getTop())) {
                if (v.getOnFocusChangeListener() != null) {
                    v.getOnFocusChangeListener().onFocusChange(v, false);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    private void updateSendCard()
    {
        ConnexionTools connexiontools = new ConnexionTools(context);
        wifiState = connexiontools.getWifi().isConnected();
        connexionState =  connexiontools.getConnexion().isConnected();
        TextView sendDetail = (TextView) findViewById(R.id.send_detail);
        CardView sent_card = (CardView) findViewById(R.id.sent_card);
        if (!wifiState && !connexionState)
        {
            sent_card.setEnabled(false);
            sendDetail.setVisibility(View.VISIBLE);
            sendDetail.setText(context.getResources().getString(R.string.ConnexionNotOk));
            autoComplete.setVisibility(View.INVISIBLE);
        } else
        {
            sendDetail.setVisibility(View.INVISIBLE);
            sent_card.setEnabled(true);
            autoComplete.setVisibility(View.VISIBLE);
            //TODO a enlever
            autoComplete.setText(context.getResources().getString(R.string.enterMail));
            autoComplete.requestFocus();

        }




    }

    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }

    private void LanceApplicationAssociee(String getFile) {

        File F = new File(getFile);
        String mimeType ="";
        MimeTypeMap myMime = MimeTypeMap.getSingleton();

        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        String extension =fileExt(getFile);//.substring(1) ;

        //TODO Hack mime type for json
        if (extension.toString().equals("json"))
        {
            mimeType = "text/plain";
        }
        else
        {
              mimeType = myMime.getMimeTypeFromExtension(extension);
        }

        newIntent.setDataAndType(Uri.fromFile(F), mimeType);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(newIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, context.getResources().getString(R.string.noHandler), Toast.LENGTH_LONG).show();
        }

    }
    public static void updateAlertDialog(String mess)
    {
        alertDialog.setMessage(mess);
    }
}
