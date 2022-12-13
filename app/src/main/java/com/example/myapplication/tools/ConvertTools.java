package tools;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import activities.ThreadActivity;
import re.devboxx.smsconverter.BuildConfig;

import java.util.ArrayList;

/**
 * Created by dayvead on 22/11/13.
 */
public class ConvertTools   {



    //private ActivityConvert contexActivityConvert;
    private Context context;
    private ThreadSMS threadSMS;
    public int numberOfSMSForConvertProgress;
    private FileTools fileTools;
    private String progressState = "" ;
    private final String numberOfProgressState = "6";
    private String ID;
    private String nom;
    private LogError logError;
    private int delay = 0 ;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private ThreadActivity threadActivity;

    public static String xml = "";
    public static String json = "";

    public static boolean pdfFormat = false;
    public static boolean rtfFormat = false;
    public static boolean htmlFormat = false;
    public static boolean xmlFormat = false;
    public static boolean jsonFormat = false;
    public static boolean csvFormat = false;
    public static boolean SDCardDestination = false;
    public static boolean MailDestination = false;
    public static boolean conversionStarted = false;
    private       String buildFlavor;



    public ConvertTools(Context context, String ID, String _nom )
    {
         this.threadActivity = (ThreadActivity)context;
         this.context = context ;
         this.ID  = ID ;
         this.nom = _nom;
         buildFlavor = BuildConfig.FLAVOR;
    }

    /*------------------------------------------------------------------------------------------------------------*/

    public ArrayList<LogError>  createConversions(int numberOfThreadForConvertProgress)
    {

        int index = 0 ;

        if (numberOfThreadForConvertProgress >1000)
        {
            //delay = 100;
        } else
        {
            //delay = 1500;
        }

        ArrayList<LogError> listLogError  = new ArrayList<>();
        String newContentForDialog = "";
        boolean success = true;
        fileTools = new FileTools(context,ID, nom);

        try {


            FileTools.suppressPreviousConvert(fileTools.TXTdirectoryName);
            TXTTools txtTools = new TXTTools(this.context, threadSMS, ID, nom,fileTools);
            txtTools.createTxt();

        } catch (Exception e) {
            // TODO Auto-generated catch block

            success = false;
            logError = new LogError();
            logError.setError_message("TXTTools   : " + e.getMessage());
            logError.setError_status(success);
            listLogError.add(index,logError);
            index++;
        }


        if (buildFlavor.equals("payant"))//TODO on converti que si c'est la version payante

        {


            /* CREATION DU XML & JSON */
            try {

                FileTools.suppressPreviousConvert(fileTools.XMLdirectoryName);
                FileTools.suppressPreviousConvert(fileTools.JSONdirectoryName);
                XMLTools xmlTools = new XMLTools(this.context, threadSMS, ID, nom, fileTools);
                xmlTools.createXMLTemplate();

            } catch (Exception e) {
                success = false;
                logError = new LogError();
                logError.setError_message("XMLTools   :" + e.getMessage());
                logError.setError_status(success);
                listLogError.add(index, logError);
                index++;
            }


            // ------------- PDF  --------------------------*/

            try {


                FileTools.suppressPreviousConvert(fileTools.PDFdirectoryName);
                PDFJetTools3Cols pdfJetTools = new PDFJetTools3Cols(this.context, threadSMS, ID, nom, fileTools);

            } catch (Exception e) {
                success = false;
                logError = new LogError();
                logError.setError_message("PDFTools   :" + e.getMessage());
                logError.setError_status(success);
                listLogError.add(index, logError);
                index++;
            }

            /* ------------- RTF  --------------------------*/

            try {

            } catch (Exception e) {
                success = false;
                logError = new LogError();
                logError.setError_message("RTFTools  :" + e.getMessage());
                logError.setError_status(success);
                listLogError.add(index, logError);
                index++;
            }

            /* ------------- HTML  --------------------------*/

            try {

                FileTools.suppressPreviousConvert(fileTools.HTMLdirectoryName);
                HTMLTools html = new HTMLTools(this.context, threadSMS, ID, nom, fileTools);
                html.createHTML();

            } catch (Exception e) {
                success = false;
                logError = new LogError();
                logError.setError_message("HTMLTools :" + e.getMessage());
                logError.setError_status(success);
                listLogError.add(index, logError);
                index++;
            }


            /* ------------- CSV  --------------------------*/

            try {

                FileTools.suppressPreviousConvert(fileTools.CSVdirectoryName);
                CSVTools csv = new CSVTools(this.context, threadSMS, ID, nom, fileTools);
                csv.createCSV();

            } catch (Exception e) {
                success = false;
                logError = new LogError();
                logError.setError_message("CSVTools :" + e.getMessage());
                logError.setError_status(success);
                listLogError.add(index, logError);
                index++;
            }

        } // Fin buildFlavor.equal("payant")

        // --------------  On Zippe Le Tout -------------*/
        try {

            ZipTools ziptools = new ZipTools(context, ID, nom,fileTools );
            ziptools.zipAll2(); //TODO Rajouté hidden TOken
        }catch (Exception e) {
            success = false;
            logError = new LogError();
            logError.setError_message("ZIPTools  :" + e.getMessage());
            logError.setError_status(success);
            listLogError.add(index,logError);
            index++;
        }



        return listLogError;
    }


    /* ------------------------------------------------------------------------------------------------------------------*/


    public int chargeConversation()
    {

        SmsTools smsTools  =  new SmsTools(this.context, ID, nom);
        threadSMS = smsTools.addSmsList();
        numberOfSMSForConvertProgress = threadSMS.getSmsList().size();

        return numberOfSMSForConvertProgress;

    }
}
