package tools;


import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import javax.crypto.Cipher;

public class SendTools {



    //private ActivityConvert contexActivityConvert;
    private Context context;

    private String progressState = "" ;
    private final String numberOfProgressState = "6";

    private LogError logError;

    private HttpURLConnection httpConn;
    private static String servletURL = "http://5.196.67.118:8080/EmailFileFromSMSConverter/EFFSC?";
    private static String servletURLSSL = "https://5.196.67.118:8080/EmailFileFromSMSConverter/EFFSC?";


    private final int BUFFER_SIZE = 4096;






    public SendTools(Context context )
    {

         this.context = context ;
        //TODO a enlever DEBUG
         //servletURL = "http://192.168.1.50:8084/EmailFileFromSMSConverter/DAM?";//wifi
         //servletURL = "http://154.67.241.139:8084/EmailFileFromSMSConverter/EFFSC?";//NAT
    }

    /*------------------------------------------------------------------------------------------------------------*/

    public ArrayList<LogError>  sendFile(String pathzip,String pathzipcrypted, String pathMailFile , String ParamFile, int sizeMail, int sizeParam)
    {

        ArrayList<LogError> listLogError  = new ArrayList<>();
        boolean success = true;

        try {


            cryptZipFile(pathzip, pathzipcrypted);
            sendFilesToServlet(pathzipcrypted,pathMailFile,ParamFile, sizeMail, sizeParam);

        } catch (Exception e) {


            success = false;
            logError = new LogError();
            logError.setError_message("Sending Error   : " + e.getMessage());
            logError.setError_status(success);
            listLogError.add(0,logError);
        }

        return listLogError;
    }


    /* ------------------------------------------------------------------------------------------------------------------*/
    public void cryptZipFile(String path, String pathcrypt )
    {
        File zipFile = new File(path);
        File zipCryptedFile = new File(pathcrypt);
        CryptTools.EncryptDecryptFile(Cipher.ENCRYPT_MODE, zipFile,zipCryptedFile);
    }



    public void sendFilesToServlet(String cryptedFiles, String mailInFiles, String paramfile, int sizemail, int sizeparam)
    {

        String charset = "UTF-8";
        File cryptedFile = new File(cryptedFiles);
        File mailInFile = new File(mailInFiles);
        File paramFile = new File(paramfile);

        long size = mailInFile.length();
        size = paramfile.length();
        size =0;

        try {
            MultiPartUtility multipart = new MultiPartUtility(servletURL, charset);

            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            String sizeMailString = Integer.toString(sizemail);
            String sizeParamString = Integer.toString(sizeparam);

            multipart.addFormField("sizeMail", sizeMailString );
            multipart.addFormField("sizeParam", sizeParamString );

            multipart.addFilePart("cryptedFile", cryptedFile);
            multipart.addFilePart("mailInFile", mailInFile);
            multipart.addFilePart("paramFile", paramFile);

            String response = multipart.finish();
            int y = 0;

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

}
