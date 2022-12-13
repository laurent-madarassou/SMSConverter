package tools;

import android.content.Context;

import re.devboxx.smsconverter.R;

import java.io.FileWriter;
import java.util.List;

/**
 * Created by dayvead on 24/11/13.
 */
public class TXTTools {

    private Context context;

    private static String fileName ;
    private static FileWriter writer ;
    private ThreadSMS threadSMS;
    private FileTools fileTools ;
    private String ID;
    private String nom;

    public TXTTools( Context context, ThreadSMS threadsms, String ids , String noms ,FileTools filetools) throws Exception
    {
        this.context = context;
        this.ID = ids;
        this.nom = noms ;
        fileTools  =filetools;

        fileName =  fileTools.TXTdirectoryName   +  fileTools.FileName    + ".txt";
        threadSMS  = threadsms;
    }



    public void createTxt () throws Exception
    {

        ThreadSMS thSms = null;
        List<SMS> listSMS = null;
        SMS         sms= null;
        String adresse   = null;
        String name   = null;
        String body = null;
        int type = 0;
        String date = "";
        String titre = context.getResources().getString(R.string.app_name);
        titre = titre.replaceAll(" ", "");
        String thread = context.getResources().getString(R.string.thread);
        String nameSender = context.getResources().getString(R.string.nameSender);
        String addressSender = context.getResources().getString(R.string.addressSender);
        String bodySender = context.getResources().getString(R.string.bodySender);
        String typeSender = context.getResources().getString(R.string.typeSender);
        String dateSender = context.getResources().getString(R.string.dateSender);
        String textSender = context.getResources().getString(R.string.textSender);
        String typeSenderR = context.getResources().getString(R.string.typeSenderR);
        String typeSenderS = context.getResources().getString(R.string.typeSenderS);
        String pv ="    ";
        String line ="";
                thSms        =   threadSMS;
                adresse      =   thSms.getAddress();
                name         =   thSms.getSenderName();
                //rtfLoop = RtfText.text(true, name + "  " + adresse) ;

                writer = new FileWriter(fileName);

                listSMS = thSms.getSmsList();
                for (int s = 0 ; s < listSMS.size(); s++)
                {
                    sms = listSMS.get(s);
                    body = sms.getBody();
                    body = body.replaceAll(" ", "-");
                    type = sms.getType();

                    date = sms.getDate();


                    switch (type)

                    {
                        case 1 :    line =  typeSenderR + pv +date + pv + body + "\n" ;
                                    writeTofile(line);
                                    break;
                        case 2 :    line =   typeSenderS + pv +date + pv + body + "\n" ;
                                    writeTofile(line);
                                    break;
                        case 3 : break;

                    }



                } // Fin FOR liste ___SMS

        writer.close();


    }

    private void writeTofile(  String line)
    {
            try {
                writer.append(line);
                writer.flush();

            }
              catch (Exception e) {
                e.printStackTrace();
            }
             finally {


            }

    }
}
