package tools;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import re.devboxx.smsconverter.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by dayvead on 24/11/13.
 */
public class FileTools  {

    private Context context = null;
    private String nom = "";
    private String ID = "";


    public  String SMSConverterDirectoryPath ="";


    public  String directoryName = "";
    public  String directoryApplicationName = "";

    public  String CurrentDirectory ="";
    public  String HTMLdirectoryName = "html";
    public  String HTMLImagesdirectoryName = "images";
    public  String PDFdirectoryName = "pdf";
    public  String XMLdirectoryName = "xml";
    public  String JSONdirectoryName = "json";
    public  String RTFdirectoryName = "doc";
    public  String CSVdirectoryName = "csv";
    public  String TXTdirectoryName = "txt";
    public  String FileName = "";
    public  String TokenName = ".token.hidden";
    public  String currentZipToSendSize;

    /*--------- ZIP Files *-----------------*/
    public  String zipFileName;
    public  ArrayList<FichiersZip> zipList;
    public static  String currentZipToSend;
    public static  String currentZipCryptedToSend;
    public static  String currentMailInFileToSend;
    public static  String currentParamToSend;


    public  String currentZipPathToSend;
    public  String NameSender = "";
    //public  int Id = 0;
    public  String UserDirectory = "";

    public  String FileMailAddressName = "MarieMonique.dear";
    public  String FileParamName = "Lea.dear";
    public  String FileParamNameFull = "";

    public  File FileMailAddress;
    public  File FileParam;

    public  ArrayList<String> MailAddressList;

    public void setAllPaths()
    {
        directoryName = context.getResources().getString(R.string.app_name);

        UserDirectory = FileName = nom + "-" + ID ;

        directoryApplicationName = context.getApplicationInfo().dataDir;
        //directoryApplicationName = context.getFilesDir().getAbsolutePath();

        FileMailAddressName ="mmemonique.dear";
        FileMailAddressName = directoryApplicationName + File.separatorChar + FileMailAddressName  ;

        FileParamNameFull = "";
        FileParamNameFull = directoryApplicationName + File.separatorChar + FileParamName;

        MailAddressList = new ArrayList<String>();

        directoryName = directoryName.replaceAll(" ", "");




        CurrentDirectory = SMSConverterDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + directoryName + File.separatorChar + UserDirectory + File.separatorChar;
        CurrentDirectory.replaceAll("\\s","");

        PDFdirectoryName = CurrentDirectory + "pdf" + File.separatorChar ;
        HTMLdirectoryName = CurrentDirectory + "html" + File.separatorChar ;
        HTMLImagesdirectoryName = "images";
        HTMLImagesdirectoryName = HTMLdirectoryName + HTMLImagesdirectoryName + File.separatorChar;

        XMLdirectoryName = CurrentDirectory + "xml" + File.separatorChar ;
        JSONdirectoryName = CurrentDirectory + "json" + File.separatorChar ;
        RTFdirectoryName = CurrentDirectory + "rtf" + File.separatorChar ;
        TXTdirectoryName = CurrentDirectory + "txt" + File.separatorChar ;
        CSVdirectoryName = CurrentDirectory + "csv" + File.separatorChar ;
        currentZipToSend = CurrentDirectory + UserDirectory + ".zip";
        currentZipCryptedToSend = CurrentDirectory + UserDirectory + "-Crypted.zip";
        currentMailInFileToSend =CurrentDirectory + UserDirectory + ".mail";
        currentParamToSend =CurrentDirectory + UserDirectory + ".param";
        TokenName =  CurrentDirectory + TokenName;

        createDirectorySMSConverter();
        //destroyFileMailAddress();
        createFileMailAddress();
        try {
            ReadFileMailAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileTools(Context contexts) {

        context = contexts ;
        setAllPaths();
    }


    public FileTools(Context contexts , String Id , String nom_)
    {
        context = contexts ;
        ID = Id ;
        nom = nom_;
        setAllPaths();



    }


    public  Boolean createDirectorySMSConverter()
    {

        File folder = new File(SMSConverterDirectoryPath);

        boolean success = true;
        if (!folder.exists()) {

            try {
                return  folder.mkdirs();
            }
            catch  (Exception e) {
                e.printStackTrace();
                return false;
            }
        }





        String pathTemp = "";

        /*pdf directory */

        folder = new File(PDFdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        } else
        {
           // suppressPreviousConvert(PDFdirectoryName);
        }
        /*html directory */

        folder = new File(HTMLdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        } else
        {
           // suppressPreviousConvert(HTMLdirectoryName);
        }

        folder = new File(HTMLImagesdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        } else
        {
           // suppressPreviousConvert(HTMLImagesdirectoryName);
        }

        /*JSON directory */

        folder = new File(JSONdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        } else
        {
           // suppressPreviousConvert(JSONdirectoryName);
        }
        /*XML directory */

        folder = new File(XMLdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        } else
        {
           // suppressPreviousConvert(XMLdirectoryName);
        }

         /*XML directory */

        folder = new File(CSVdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        } else
        {
           // suppressPreviousConvert(CSVdirectoryName);
        }
             /* TXT directory */

        folder = new File(TXTdirectoryName);
        success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        } else
        {
           // suppressPreviousConvert(TXTdirectoryName);
        }
      return success;
    }


    public static void suppressPreviousConvert(String filePath)
    {
        File file = new File(filePath);

        File[] listfileIn  = file.listFiles();
        for(int i = 0; i < listfileIn.length; i++)
        {
            if (listfileIn[i].isFile()) listfileIn[i].delete();
        }

    }

    public   void createFileMailAddress()
    {
        String path =  FileMailAddressName;
        FileMailAddress = new File(path);

        if (!FileMailAddress.exists()) {
            try
            {
                FileMailAddress.createNewFile();
            } catch (IOException ioE)
            {
                // TODO Erreur
            }
        }

    }


    public   void createFileParam()
    {

        FileParam = new File(FileParamNameFull);

        if (!FileParam.exists()) {
            try
            {
                FileParam.createNewFile();
            } catch (IOException ioE)
            {
                // TODO Erreur
            }
        }

    }



   // ------------------------------------------------------- LOIU------------------------------------------------------------
   public void MailPutInAFile(byte[] email)
    {
        File f = new File(currentMailInFileToSend);
        FileOutputStream stream  = null ;

        try {
            stream = new FileOutputStream(currentMailInFileToSend);
            stream.write(email);
            stream.close();
        } catch ( IOException e )
        {
        }
    }

    public void createParamFile(byte[] param)
    {
        File f = new File(currentParamToSend);
        FileOutputStream stream  = null ;

        try {
            stream = new FileOutputStream(currentParamToSend);
            stream.write(param);
            stream.close();
        } catch ( IOException e )
        {
        }
    }
    // ----------------------------------------------------------FLOIU---------------------------------------------------------
    public  void  ReadFileMailAddress() throws IOException {
        boolean ret = false;
        //createFileMailAddress();
        FileInputStream fis = null;
        //TODO Rajouté 2016
        MailAddressList = new ArrayList<String>();
        MailAddressList.clear();
        String path =  FileMailAddressName;
        FileMailAddress = new File(path);
        if (FileMailAddress.exists()) {
            try {
              //  in = new BufferedInputStream(new FileInputStream(FileMailAddress));
                fis = new FileInputStream(FileMailAddress) ;
                InputStreamReader isr = new InputStreamReader( fis ) ;
                BufferedReader buffreader = new BufferedReader( isr ) ;

                String readString = buffreader.readLine ( ) ;
                while ( readString != null ) {
                    MailAddressList.add(readString) ;
                    readString = buffreader.readLine ( ) ;
                }

                isr.close ( ) ;



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }

    public  void  PutFileMailAddress(String newAddress) throws IOException {

        createFileMailAddress();
        String path =  FileMailAddressName;//TODO vérifier si le mail nexiste pas au préalable
        FileMailAddress = new File(path);
       // FileOutputStream fOut= null;
        BufferedWriter bW= null;
        if (FileMailAddress.exists()) {
          try {


              bW = new BufferedWriter(new FileWriter(FileMailAddress, true));
              bW.newLine();
              bW.write(newAddress);




            } catch (IOException e) {
             //TODO ERROR
          }

          finally {
              if (bW != null) {
                  try {
                      bW.flush();
                      bW.close();
                  } catch (IOException e) {
                      //TODO ERROR
                  }
              }
          }
        }//IF
    }

    public static boolean noConversions(String currentZipToSend)
    {
        File f    = new File(currentZipToSend);

        if (!f.exists()) {
            return true;
        } else
        {
            return false;
        }


    }


    public static File[] listFontSystemDirectory()
    {
        String path = Environment.getRootDirectory().getAbsolutePath() + File.separator + "fonts";//getExternalStorageDirectory().toString()+"/Pictures";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }
        return files;
    }
}
