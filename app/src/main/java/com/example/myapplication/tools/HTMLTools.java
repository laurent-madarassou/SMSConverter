package tools;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import re.devboxx.smsconverter.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by dayvead on 24/11/13.
 */
public class HTMLTools {
    private String htmlPathBubble;
    private String htmlFileNameBubble ;
    private String htmlPathClassic;
    private String htmlFileNameClassic ;
    private FileTools fileTools;
    private String cssPathBubble;
    private String cssPathClassic;

    private Context context;
    private String ID;
    private String nom;
    private ThreadSMS threadSMS;


    String loopHtml="";
    String footerHtml="";
    int comptThread=0;
    int comptSMS=0;
    int MaxLettre = 1500 ; //TODO A REMETTRE 53


    public HTMLTools(Context context , ThreadSMS threadsms, String ids, String noms,FileTools filetools ) throws Exception
    {
        this.context =   context;
        this.ID = ids;
        this.nom = noms ;
        this.fileTools = filetools ;

        String SMSConverterDirectoryPathLocal = fileTools.CurrentDirectory    + fileTools.HTMLdirectoryName;

        htmlFileNameBubble =  fileTools.HTMLdirectoryName + fileTools.UserDirectory + "-bubble.html";
        htmlPathBubble =   htmlFileNameBubble;


        htmlFileNameClassic= fileTools.HTMLdirectoryName + fileTools.UserDirectory + "-classic.html";
        htmlPathClassic =  htmlFileNameClassic;


        cssPathBubble =  fileTools.HTMLdirectoryName   + "style-bubble.css";
        cssPathClassic = fileTools.HTMLdirectoryName   + "style-classic.css";

        this.threadSMS = threadsms ;
        



    }



    public void createHTML() throws Exception
    {
        String ret = "\n";
        /*boolean  bubbleStyle = CONVERTTools.bubbleStyle;
        boolean  classicStyle = CONVERTTools.classicStyle;
       */
        boolean  bubbleStyle = true;
        boolean  classicStyle =true;

        FileOutputStream fosHTMLBubble = null;
        FileOutputStream fosHTMLClassic = null;
        FileOutputStream fosCSSBubble = null;
        FileOutputStream fosCSSClassic = null;

        if (bubbleStyle) {
             fosHTMLBubble = new FileOutputStream(htmlPathBubble);
             try {
                 fosCSSBubble = new FileOutputStream(cssPathBubble);
             }
             catch (Exception e )
             {
                 int o = 45646546 ;
             }


        }
        if (classicStyle) {
              fosHTMLClassic = new FileOutputStream(htmlPathClassic);
              //fosCSSClassic= new FileOutputStream(cssPathClassic);
            try {
                fosCSSClassic = new FileOutputStream(cssPathClassic);
            }
            catch (Exception e )
            {
                int o = 45646546 ;
            }
        }




        ThreadSMS thSms = null;
        List<SMS> listSMS = null;
        SMS         sms= null;


        String adresse   = null;
        String name   = null;
        String body = null;
        int type = 0;
        String date = "";
        Bitmap mmsImage = null;

        String titre = context.getResources().getString(R.string.app_name);
        //titre = titre.replaceAll(" ", "");
        String thread = context.getResources().getString(R.string.thread);
        String nameSender = context.getResources().getString(R.string.nameSender);
        String addressSender = context.getResources().getString(R.string.addressSender);
        String bodySender = context.getResources().getString(R.string.bodySender);
        String typeSender = context.getResources().getString(R.string.typeSender);
        String dateSender = context.getResources().getString(R.string.dateSender);
        String textSender = context.getResources().getString(R.string.textSender);

        String typeSenderR = context.getResources().getString(R.string.typeSenderR);
        String typeSenderS = context.getResources().getString(R.string.typeSenderS);

        String numberOfThread = context.getResources().getString(R.string.numberOfThread);
        String numberOfSMS = context.getResources().getString(R.string.numberOfSMS);




       // String htmlString = (String) context.getText(R.string.htmlsource);
       // String cssString = createCSS();//(String) context.getText(R.string.cssSource);
        /* --------- Récup des tempalte et css */

        InputStream htmlStreamBubble = context.getResources().getAssets().open("html/page.html");
        //InputStream htmlStreamClassic = context.getResources().getAssets().open("html/page.html");

        InputStream cssStreamBubble = null;
        InputStream cssStreamClassic = null;
        String cssStringClassic = "";
        String cssStringBubble = "";
        if (bubbleStyle)
          {
                  cssStreamBubble  = context.getResources().getAssets().open("css/style-bubble.css");
                  int sizeN = cssStreamBubble.available();
                  byte[] cssBuffer = new byte[sizeN]; //declare the size of the byte array with size of the file
                  cssStreamBubble.read(cssBuffer); //read file
                  cssStreamBubble.close();
              cssStringBubble = new String(cssBuffer);
          }
        if (classicStyle)
        {
                cssStreamClassic= context.getResources().getAssets().open("css/style-classic.css");
                int sizeF = cssStreamClassic.available();
                byte[] cssBuffer = new byte[sizeF]; //declare the size of the byte array with size of the file
                cssStreamClassic.read(cssBuffer); //read file
                cssStreamClassic.close();
            cssStringClassic = new String(cssBuffer);
        }


        int size = htmlStreamBubble.available();
        byte[] htmlBuffer = new byte[size]; //declare the size of the byte array with size of the file
        htmlStreamBubble.read(htmlBuffer); //read file
        htmlStreamBubble.close();
        String htmlString = new String(htmlBuffer);


        /*-------------------------------------------------------------------------------------*/


        /* Le titre de la page */
        htmlString = htmlString.replace("$titreFichier$" ,titre );

        htmlString = htmlString.replace("$titre$" ,titre );



                thSms         =    threadSMS;
                adresse       =     thSms.getAddress();
                name          =     thSms.getSenderName();
                if (name == null)
                {
                    name = context.getResources().getString(R.string.johndoe);
                }
                name = name.replace(" ","");
                Bitmap avatar =     thSms.getBitMap();
                if (avatar == null)
                {
                      avatar = BitmapFactory.decodeResource(context.getResources(), R.drawable.nouser);
                }
                /*  create png file */
                String imageDirectory =   fileTools.HTMLImagesdirectoryName;

                File file = new File(imageDirectory, name  + ".png");

                if (!file.exists()) {
                    FileOutputStream fOutBitmap = new FileOutputStream(file);
                    avatar.compress(Bitmap.CompressFormat.PNG, 85, fOutBitmap);
                }



                comptThread++;
                loopHtml = loopHtml + "<div class=\"blocThread\">";
                loopHtml = loopHtml + "<table>" + ret;
                loopHtml = loopHtml + "<tr class=\"tdTop\">";
                loopHtml = loopHtml + "<td >" +    "<img src=\"" +  "images"    + "/" + name + ".png" + "\" alt=\"" +   name + "-" + adresse   +  "\" height=\"96\" width=\"96\"></td>"+ ret;
                loopHtml = loopHtml + "<td> " + TextUtils.htmlEncode(nameSender)  + ":   <h2>"+ TextUtils.htmlEncode(name)  + "</h2> </td>"+ ret;
                loopHtml = loopHtml + "<td> " + TextUtils.htmlEncode(addressSender)  + ":   <h2>"+ TextUtils.htmlEncode(adresse)  +  "</h2> </td>"+ ret;

                loopHtml = loopHtml + "</tr>"+ ret;
                loopHtml = loopHtml + "</table>"+ ret;
                loopHtml = loopHtml + "<div class=\"sepThread\"></div>"+ ret;




                listSMS = thSms.getSmsList();
                for (int s = 0 ; s < listSMS.size(); s++)
                {
                    sms = listSMS.get(s);
                    body = sms.getBody();

                    String fileNameMmsImage = null;

                    mmsImage = sms.getmmsImage();
                    if(mmsImage != null)
                    {
                         fileNameMmsImage  =  saveMmsImageToDirectory(name,mmsImage);
                    }
                    if (body == null)  body = " ";
                    body = TextUtils.htmlEncode(body);
                    type = sms.getType();  /* 1 --> reception  & 2 --> emission */
                    date = sms.getDate();
                    comptSMS++;

                    if (type == 1)
                    {

                        loopHtml = loopHtml + "<div class=\"bubble-right\">"+ ret;
                       // if (classicStyle)loopHtml = loopHtml + "<div>"+ ret;
                        loopHtml = loopHtml + "<div class =\"innerTextInfo\"  >" + date+ "<h4>" + typeSenderR  + "</h4>  </div>"+ ret;
                        loopHtml = loopHtml + "<div class =\"innerText\"  >"+ ret;
                        //MMS Image
                        if(fileNameMmsImage != null)
                        {
                            loopHtml = loopHtml + "" +    "<img src=\"" +  fileTools.HTMLImagesdirectoryName    + "/" + fileNameMmsImage + ".png" + "\" alt=\"" +   fileNameMmsImage + "-" + adresse   +  "\" height=\"96\" width=\"96\"></td>"+ ret;
                        }

                        int taille = body.length();

                        double divide =  0.0;
                        if ( taille > MaxLettre )
                        {
                            divide = ((double)body.length() / 53);
                            double res  = Math.ceil(divide);
                            int start = 0;
                            int end   = 0;
                            String debug ="";
                            for (int tiembo=1; tiembo <= res; tiembo++)
                            {

                                end = end + 53-1;
                                if (tiembo < res)
                                {
                                    loopHtml = loopHtml + "<h3>" + body.substring(start,end) +  "</h3></div></div>"+ ret;
                                    //loopHtml = loopHtml + "<h3>" + body.substring(start,end) +  "</h3>"+ ret;
                                } else
                                {
                                    int g = body.length();//-start;
                                    loopHtml = loopHtml + "<h3>" + body.substring(start, g) +  "</h3></div></div>"+ ret;
                                    //loopHtml = loopHtml + "<h3>" + body.substring(start, g) +  "</h3>"+ ret;
                                }

                                start = end + 1;
                            }

                        } else
                        {
                            loopHtml = loopHtml + "<h3>" + body +  "</h3></div></div>"+ ret;
                        }


                    }
                    if (type == 2)
                    {
                        loopHtml = loopHtml + "<div class=\"bubble-left\">"+ ret;
                        //if (classicStyle)loopHtml = loopHtml + "<div>"+ ret;
                        loopHtml = loopHtml + "<div class =\"innerTextInfo\"  >" + date + "<h4>" + typeSenderS  + "</h4>  </div>"+ ret;
                        loopHtml = loopHtml + "<div class =\"innerText\"  >"+ ret;
                        //MMS Image
                        if(fileNameMmsImage != null)
                        {
                            loopHtml = loopHtml + "" +    "<img src=\"" +  fileTools.HTMLImagesdirectoryName    + "/" + fileNameMmsImage + ".png" + "\" alt=\"" +   fileNameMmsImage + "-" + adresse   +  "\" height=\"96\" width=\"96\"></td>"+ ret;
                        }

                        int taille = body.length();
                        //loopHtml ="";
                        double divide =  0.0;
                        if ( taille > MaxLettre )
                        {
                            divide = ((double)body.length() / 53);
                            double res  = Math.ceil(divide);
                            int start = 0;
                            int end   = 0;
                            String debug ="";
                            for (int tiembo=1; tiembo <= res; tiembo++)
                            {

                                end = end + 53-1;
                                if (tiembo < res)
                                {
                                    loopHtml = loopHtml + "<h3>" + body.substring(start,end) +  "</h3></div></div>"+ ret;
                                    //loopHtml = loopHtml + "<h3>" + body.substring(start,end) +  "</h3>"+ ret;
                                } else
                                {
                                    int g = body.length();//-start;
                                    loopHtml = loopHtml + "<h3>" + body.substring(start, g) +  "</h3></div></div>"+ ret;
                                    //loopHtml = loopHtml + "<h3>" + body.substring(start, g) +  "</h3>"+ ret;
                                }

                                start = end + 1;
                            }

                        } else {
                            loopHtml = loopHtml + "<h3>" + body + "</h3></div></div>" + ret;
                        }
                    }
                }
                loopHtml = loopHtml + "</div><br>"+ ret;//"<div class=\"sepThread\"></div>";
               // Fin liste ___SMS

        loopHtml = loopHtml.substring(0,loopHtml.length() - 12);
        /* rajout de limage de fond pour la pge html */

        Bitmap bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.bghtml);
                /*  create png file */
         imageDirectory =   fileTools.HTMLImagesdirectoryName;
         file = new File(imageDirectory , "bg.png");

        if (!file.exists()) {
            FileOutputStream fOutBitmap = new FileOutputStream(file);
            bg.compress(Bitmap.CompressFormat.PNG, 85, fOutBitmap);
        }


        htmlString = htmlString.replace("$loop$" ,loopHtml);
        //
        footerHtml = footerHtml +  numberOfSMS + " :  <b>" +  comptSMS + "</b>"+ ret;
        htmlString = htmlString.replace("$footer$" ,"<p>" +  footerHtml + "</p>");

        String htmlStringBubble = htmlString;
        String htmlStringClassic = htmlString;

        //if (bubbleStyle) {
            htmlStringBubble = htmlStringBubble.replace("style-classic", "style-*****");
            OutputStreamWriter oswHTML = new OutputStreamWriter(fosHTMLBubble);
            oswHTML.write(htmlStringBubble);
            oswHTML.flush();
            oswHTML.close();
            fosHTMLBubble.close();

            OutputStreamWriter oswCSS = new OutputStreamWriter(fosCSSBubble);
            oswCSS.write(cssStringBubble);
            oswCSS.flush();
            oswCSS.close();
       // }
       // if (classicStyle) {
            oswHTML = new OutputStreamWriter(fosHTMLClassic);
            htmlStringClassic = htmlStringClassic.replace("class=\"bubble-right\"", "");
            htmlStringClassic = htmlStringClassic.replace("class=\"bubble-left\"", "");
            htmlStringClassic = htmlStringClassic.replace("class =\"innerTextInfo\"", "class =\"innerTextInfoClassic\"");
            htmlStringClassic = htmlStringClassic.replace("class =\"innerText\"", "class =\"innerTextClassic\"");


            oswHTML.write(htmlStringClassic);
            oswHTML.flush();
            oswHTML.close();
            fosHTMLClassic.close();

            oswCSS = new OutputStreamWriter(fosCSSClassic);
            oswCSS.write(cssStringClassic);
            oswCSS.flush();
            oswCSS.close();

       // }






    }


    private String createCSS() throws Exception
    {

        Properties p;
        AssetManager assetManager = context.getAssets();
        /**
         * Open an asset using ACCESS_STREAMING mode. This
         */
        InputStream inputStream = assetManager.open("styleCss.properties");
        /**
         * Loads properties from the specified InputStream,
         */
        p = new Properties();
        p.load(inputStream);
       return  p.getProperty("css");
    }
    private String saveMmsImageToDirectory(String name, Bitmap mmsImage) throws Exception
    {
        final String alphabet = "ABCDEFGHIJKLMPOQRSTUVWXYZ0123456789";
        final int N = alphabet.length();
        Random r = new Random();
        String fileNameMmsImage = name;
        /* 3 caractere aléatoire ajouté au nom fichier */
        char randomChar = alphabet.charAt(r.nextInt(N));
        fileNameMmsImage = fileNameMmsImage + randomChar;

        randomChar = alphabet.charAt(r.nextInt(N));
        fileNameMmsImage = fileNameMmsImage + randomChar;

        randomChar = alphabet.charAt(r.nextInt(N));
        fileNameMmsImage = fileNameMmsImage + randomChar;

        String imageDirectory = fileTools.CurrentDirectory + fileTools.HTMLdirectoryName  + File.separator +  fileTools.HTMLImagesdirectoryName;

        File file = new File(imageDirectory, fileNameMmsImage  + ".png");

        if (!file.exists()) {
            FileOutputStream fOutBitmap = new FileOutputStream(file);
            mmsImage.compress(Bitmap.CompressFormat.PNG, 85, fOutBitmap);
        }
        return fileNameMmsImage ;
    }

}
