package tools;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import re.devboxx.smsconverter.R;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.List;

public class XMLTools    {

    private Context context;
    private ThreadSMS threadSMS;
    private String xmlPath ;
    private String jsonPath ;
    private FileTools fileTools ;
    private String ID;
    private String nom;
    
    public  XMLTools(Context context, ThreadSMS threadsms, String ids , String noms ,FileTools filetools)
    {

        this.context = context;
        this.threadSMS = threadsms ;
        this.nom = noms;
        this.ID  = ids ;
        fileTools = filetools;

    }




    public void createXMLTemplate() throws Exception
    {
        String SMSConverterDirectoryPathLocal = fileTools.CurrentDirectory ;
        xmlPath =   fileTools.XMLdirectoryName +  fileTools.FileName + ".xml";
        FileOutputStream fosXML = new FileOutputStream(xmlPath);

        jsonPath =  fileTools.JSONdirectoryName +  fileTools.FileName  + ".json";
        FileOutputStream fosJSON = new FileOutputStream(jsonPath);



        ThreadSMS thSms = null;
        List<SMS> listSMS = null;
        SMS sms= null;
        String adresse   = null;
        String name   = null;
        String body = null;
        int type = 0;
        String date = "";
        String titre = context.getResources().getString(R.string.app_name);
        titre = titre.replaceAll(" ", "");
        String thread = context.getResources().getString(R.string.thread);
        String nameSender = context.getResources().getString(R.string.nameSender);
        String noName = context.getResources().getString(R.string.jhonDoe);
        String addressSender = context.getResources().getString(R.string.addressSender);
        String bodySender = context.getResources().getString(R.string.bodySender);
        String typeSender = context.getResources().getString(R.string.typeSender);
        String dateSender = context.getResources().getString(R.string.dateSender);
        String textSender = context.getResources().getString(R.string.textSender);


        String guillemet = "\"" ;
        String saut = "\n" ;
        String tab1 =  "\t";
        String tab2 =  "\t\t";
        String tab3 =  "\t\t\t\t";
        String tab4 =  "\t\t\t\t\t\t\t\t";

        String jsonSerializer = "{ " + guillemet +   titre + guillemet + " : " +  saut + tab2+  "{" +  saut  ; //ok

        jsonSerializer = jsonSerializer + tab2 + guillemet + thread + guillemet + " : [" + saut; // ok

               try
               {
                       XmlSerializer xmlSerializer = Xml.newSerializer();
                       StringWriter writer = new StringWriter();
                       xmlSerializer.setOutput(writer);
                       //Start Document
                       xmlSerializer.startDocument("UTF-8", true);
                       xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                       //Open Tag <file>
                       xmlSerializer.startTag("",titre );
                       XmlSerializer tempXml = Xml.newSerializer();
                       StringWriter tempWriter =  new StringWriter();

                               thSms        =   threadSMS;
                               adresse      =   thSms.getAddress();
                               name         =   thSms.getSenderName();




                               xmlSerializer.startTag("", thread);
                               /*--------------------------------*/
                               xmlSerializer.startTag("", nameSender);
                               if (name == null)
                               {
                                   xmlSerializer.text(noName);
                                   jsonSerializer = jsonSerializer + tab2 + " { " + saut + tab3 + guillemet + nameSender + guillemet + " : " + guillemet + "****" + guillemet + "," + saut;//ok
                                   jsonSerializer = jsonSerializer + tab3 +  guillemet + addressSender + guillemet + " : " + guillemet + adresse + guillemet + "," + saut;//ok
                               }else
                               {
                                   jsonSerializer = jsonSerializer  + tab2 + " { " + saut + tab3 + guillemet + nameSender + guillemet + " : " + guillemet + name + guillemet + "," + saut;//ok
                                   jsonSerializer = jsonSerializer  + tab3 + guillemet + addressSender + guillemet + " : " + guillemet + adresse + guillemet + "," + saut;//ok
                                   xmlSerializer.text(name);
                               }
                               xmlSerializer.endTag("", nameSender);
                               /*--------------------------*/
                               xmlSerializer.startTag("", addressSender);
                               xmlSerializer.text(adresse);
                               xmlSerializer.endTag("", addressSender);
                               /*----------------------------*/
                               jsonSerializer = jsonSerializer  + tab3 +  guillemet + bodySender + guillemet + " : ["  + saut;//ok
                            /* Récup des sms */
                               listSMS = thSms.getSmsList();
                               for (int s = 0 ; s < listSMS.size(); s++)
                               {
                                   sms = listSMS.get(s);
                                   body = sms.getBody();
                                   body = body.replaceAll(" ", "-");
                                   body = body.replaceAll("'", "-");
                                   body = body.replaceAll("\n", " ");
                                   type = sms.getType();
                                   date = sms.getDate();

                                  jsonSerializer = jsonSerializer + tab3 + " { " + saut;
                                   jsonSerializer = jsonSerializer  + tab4 +  guillemet + typeSender + guillemet + " : " + guillemet + type + guillemet + "," + saut;
                                   jsonSerializer = jsonSerializer  + tab4 +  guillemet + dateSender + guillemet + " : " + guillemet + date + guillemet + "," + saut;
                                   jsonSerializer = jsonSerializer  + tab4 +  guillemet + textSender + guillemet + " : " + guillemet + body + guillemet +  saut;
                                   jsonSerializer = jsonSerializer + tab3 +"}," + saut;//ok a voir

                                   xmlSerializer.startTag("", typeSender);
                                   xmlSerializer.text(String.valueOf(type));
                                   xmlSerializer.endTag("", typeSender);

                                   xmlSerializer.startTag("", dateSender);
                                   xmlSerializer.text(date);
                                   xmlSerializer.endTag("", dateSender);

                                   xmlSerializer.startTag("", bodySender);
                                   xmlSerializer.text(body);
                                   xmlSerializer.endTag("", bodySender);
                               } // Fin récup ___SMS
                               jsonSerializer = jsonSerializer.substring(0,jsonSerializer.length()-2);
                               //Log.i("--------------------------------****************jsonSerializer -2 ----> ", jsonSerializer);
                               jsonSerializer = jsonSerializer  + tab2 +   saut+  "] " + tab3 +   " }," + saut ;//fin de body
                               xmlSerializer.endTag("", thread);






                        jsonSerializer = jsonSerializer.substring(0,jsonSerializer.length()-2);
                       // Log.d("jsonSerializer -2 ----> ", jsonSerializer);
//
                        jsonSerializer = jsonSerializer + "]" + saut ; //ok thread
                        jsonSerializer = jsonSerializer + "}" + saut + "}"; //ok fin
                           //end tag <file>
                        xmlSerializer.endTag("",titre );
                        xmlSerializer.endDocument();

                        ConvertTools.xml = writer.toString();
                        ConvertTools.json = jsonSerializer;
                        int o = 0;

               }
               catch (IOException e)
               {
                   Log.d("TAG", "Création des fichiers XML & JSON : echec");

                   e.printStackTrace();
               }
            OutputStreamWriter oswXML = new OutputStreamWriter(fosXML);
            oswXML.write(ConvertTools.xml);
            oswXML.flush();
            oswXML.close();
            OutputStreamWriter oswJSON = new OutputStreamWriter(fosJSON);
            oswJSON.write(ConvertTools.json);
            oswJSON.flush();
            oswJSON.close();

        fosXML.close();
        fosJSON.close();


    }



}
