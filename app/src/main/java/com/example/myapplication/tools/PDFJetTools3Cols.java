package tools;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import re.devboxx.smsconverter.R;
import activities.ThreadActivity;
import com.pdfjet.*;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*------------------------------------------------------ */
//  Page A4
//  X = 0 Y = 0 en haut coin gauche
//
//
/*- -----------------------------------------------------*/
public class PDFJetTools3Cols {


    private String pdfPath;
    private static Context context;
    private String ID;
    private static String nom;
    FileTools fileTools;
    public static Font fontCountry;
    public static boolean isFontLoaded =false;
    public static PDF pdf ;
    public static Font fontForConversion;
    public static Font LibelleSystemFonts;

    public float sizeFont = 2.0f;

    private ThreadSMS threadSMS;
    private AssetManager assetManager = null;
    private Image imageLogoxxl;
    private Image imageUser;
    private Image imageOut;
    private Image imageIn;
    private Page page;
    private static TextLine textLine;
    private  int X = 15;
    private  int Y = 15;
    private  int currentColonne = 1;
    private  int pagenumber = 1 ;
    private static int Ycurrent = 0 ;
    private static int Xcurrent = 0 ;
    private static int XColonne2 = 207 ;
    private static int XColonne3 = 411 ;
    private static int YBasdePage = 795 ;
    private static int YHautdePage = 49 ;
    private static int XFinPage = 595 ;
    private int limitBasDePageSMS = 780;
    private int MaxLettre = 33;



    private int imgWidth = 41;
    private int imgHeight = 44;
    Date date;
    //private  String fontString = "fonts/Symbola_hint.ttf";
    String titre ;

    String thread ;
    String nameSender ;
    String addressSender ;
    String pdfPageNumber ;
    DateFormat formatter;
    String dateLib;
    String today;
    String recu ;
    String envoye;
    String titrePage;
    String InfoVersion;
    List<SMS> listSMS;




    private int numberPage = 1;


    public PDFJetTools3Cols(Context contexts, ThreadSMS threadsms,  String ids,String noms, FileTools filetools) throws Exception  {

        context = contexts;
        ID = ids;
        nom = noms;
        fileTools = filetools;
        pdfPath = fileTools.PDFdirectoryName + fileTools.FileName + ".pdf";
        this.threadSMS = threadsms;
        FileOutputStream fos = new FileOutputStream(pdfPath);

        titre = context.getResources().getString(R.string.app_name);

        thread = context.getResources().getString(R.string.thread);
        nameSender = context.getResources().getString(R.string.nameSender);
        addressSender = context.getResources().getString(R.string.addressSender);
        pdfPageNumber = context.getResources().getString(R.string.PageNumber);
        dateLib = context.getResources().getString(R.string.dateLib);
        date = Calendar.getInstance().getTime();
        InfoVersion = context.getResources().getString(R.string.InfoVersion);

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        today = formatter.format(date);
        recu = context.getResources().getString(R.string.typeSenderR);
        envoye = context.getResources().getString(R.string.typeSenderS);


        //if (pdf == null ) {
            pdf = new PDF(fos);
        //}

        LibelleSystemFonts = new Font(pdf, CoreFont.HELVETICA_BOLD_OBLIQUE);

        listSMS = threadSMS.getSmsList();

        Ycurrent = Y + 59;
        Xcurrent = X;

        isFontLoaded = false;

        if (!isFontLoaded)
        {
            selectFontByCountry(pdf);
        }
        createEnteteAndBasPage();
        addSmsToPDF();

        pdf.flush();
        fos.close();

    }

    private void createEnteteAndBasPage()  throws Exception {


        textLine = new TextLine(fontForConversion,"");
        NouvellePage();
        textLine.setColor(context.getResources().getColor(R.color.PurpleSMSConverter));

        assetManager = context.getAssets();
        InputStream imageStream = assetManager.open("images" + File.separator + "render" + File.separator + "logo.png");

        BufferedInputStream bis1 =
                new BufferedInputStream(imageStream);
        imageLogoxxl = new Image(pdf, bis1, ImageType.PNG);


        imageLogoxxl.setPosition(X -5,Y - 10);
        imageLogoxxl.scaleBy(0.14);
        imageLogoxxl.drawOn(page);

        // Titre  -------------------------------------------------------------------------*/

        fontForConversion.setSize(sizeFont + 23.0f);

        writeLine( X + 150 ,Y + 9 ,titre );

        // photo Thumbnail -------------------------------------------------------------------------*/
        Bitmap bt = threadSMS.getBitMap();
        if (bt == null) {
            bt = BitmapFactory.decodeResource(context.getResources(), R.drawable.nouser);
        }
        bt = Bitmap.createScaledBitmap(bt, imgWidth, imgHeight, false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bt.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        InputStream is = new ByteArrayInputStream(bos.toByteArray());
        imageUser = new Image(pdf, is, ImageType.PNG);
        imageUser.setPosition(X+ 530 , Y - 5);
        imageUser.drawOn(page);

        bos.close();

        //Nom Lib -------------------------------------------------------------------------*/
        fontForConversion.setSize(sizeFont + 5.0f);
        writeLine(X + 150 ,Y + 30 , nameSender+" : " );

        //Nom-------------------------------------------------------------------------*/

        fontForConversion.setSize(sizeFont + 5.0f);
        writeLine( X + 190 ,Y + 30 , nom );

        //Numéro Lib-------------------------------------------------------------------------*/
         fontForConversion.setSize(sizeFont + 5.0f);
        writeLine( X + 150 ,Y + 40 , addressSender+ " : " );

        //Numéro -------------------------------------------------------------------------*/
         fontForConversion.setSize(sizeFont + 5.0f);
        writeLine( X + 210 ,Y + 40 , threadSMS.getAddress());

        //Date Lib -------------------------------------------------------------------------*/

        fontForConversion.setSize(sizeFont + 4.0f);
        writeLine( X - 2  ,Y + 45 , dateLib);

        //Dat -------------------------------------------------------------------------*/
        fontForConversion.setSize(sizeFont + 4.0f);
        writeLine(X - 2 + dateLib.length() + 15 ,Y + 45 , today);

        // Line Séparatrice Haute -------------------------------------------------------------------------*/
        Path pathHaute = new Path();
        drawLinesSeparatrices(pathHaute, 0, YHautdePage + Y, XFinPage, YHautdePage + Y);
        // Line Séparatrice Basse-------------------------------------------------------------------------*/
        Path pathBasse = new Path();
        drawLinesSeparatrices(pathBasse, 0, YBasdePage + Y, XFinPage, YBasdePage + Y);
        //Line Séparatrice Colonne 2-------------------------------------------------------------------------*/
        Path pathCol2 = new Path();
        drawLinesSeparatrices(pathCol2, XColonne2, Y + YHautdePage, XColonne2, YBasdePage + Y);

        //Line Séparatrice Colonne 3-------------------------------------------------------------------------*/
        Path pathCol3 = new Path();
        drawLinesSeparatrices(pathCol3, XColonne3, Y + YHautdePage, XColonne3, YBasdePage + Y);

        // Version à coté Titre Document (nom application) --------------------------------------------------------------------------------------------*/
        fontForConversion.setSize(sizeFont);
        writeLine(X + 345 ,Y + 9 ,  InfoVersion + 4.6f);

        // indications sens des fléches reçu/envoyé  ---------------------------------------------------------*/
        drawArrow( 1,XColonne3 + 5, Y + YBasdePage + 8,0.07) ;
        writeLine(XColonne3 + 5 + 10  ,Y + YBasdePage + 12 ,  ":   " + recu);

        drawArrow( 2,XColonne3 + 5, Y + YBasdePage + 16,0.07) ;
        writeLine(XColonne3 + 5 + 10  ,Y + YBasdePage + 20 ,  ":   " + envoye);
        //
        fontForConversion.setSize(sizeFont + 4.5f); // pour addSmsToPDF

    }
    private void writeLine( int x, float y, String Line ) {


        double line = 1;

        try {

            textLine.setText(Line);
            textLine.setPosition(x, y);
            textLine.drawOn(page);

        } catch (Exception e) {

            e.getLocalizedMessage();

        }


    }
    private void addSmsToPDF()  {

        //listSMS = threadSMS.getSmsList();
        SMS sms;
        String body;

        int type = 0;
        String date = "";
        Ycurrent = Y + 59;
        Xcurrent = X;



            for (int s = 0; s < listSMS.size(); s++) {


                sms = listSMS.get(s);
                body = sms.getBody();
                String[] bodies2 = null;

                    if (body.length() > MaxLettre) {
                        try
                        {
                            bodies2 = breakLines(body, MaxLettre).split("\n");
                        }catch (Exception e)
                                {
                                    e.getLocalizedMessage();
                                    int hhi = 0 ;

                                }

                    } else {
                        bodies2 = new String[]{body};
                    }

                date = sms.getDate();

                type = sms.getType();

                int h = 0;
                String twentySpaces = "                                    ";

                if (type == 1) {
                    setFontcolor(context.getResources().getColor(R.color.bluePdfSms));
                }
                if (type == 2) {
                    setFontcolor(context.getResources().getColor(R.color.greenPdfSms));
                }


                for (int l = 0; l < bodies2.length; l++) {
                        try {
                            if (bodies2.length >= 1) {
                                String bod = bodies2[l];
                                if (h == 0) {
                                    drawArrow(type, Xcurrent - 5, Ycurrent - 4, 0.07);
                                    bod = date + " " + bod;
                                    writeLine(Xcurrent + 5, Ycurrent, bod);
                                    h++;
                                } else {
                                    bod = twentySpaces + bod;
                                    writeLine(Xcurrent + 5, Ycurrent, bod);
                                }
                                Ycurrent = Ycurrent + 8;
                                checkFinColonneAndFindePage();
                            }
                        }catch (Exception e)
                        {
                            e.getLocalizedMessage();
                            int hhi = 0 ;

                        }
                }

            }


    }
    private  ArrayList<String> TruncBodyInParagraph(String body) {


        ArrayList<String> retour = new ArrayList<String>();
        String ret = body;
        if (body != null) {
            int size = body.length();
            //int size = test.length();
            int startIndex = 0;
            for (int i = 0; i < size; i++) {
                int f = i % MaxLettre;

                if (f == 0 && i != 0) {

                    int k = i;
                    char s = ret.charAt(k);
                    while ((!Character.toString(s).contains(" ")) && k < size) {

                        s = ret.charAt(k);
                        k++;
                    }
                    int j = 0;

                    retour.add(ret.substring(startIndex, k));
                    startIndex = k;
                    String finalString = ret.substring(k, body.length());

                    int lenghtFinalString = finalString.length();
                    if (lenghtFinalString <= MaxLettre) retour.add(ret.substring(k, body.length()));


                }
            }

        }

        return retour;
    }
    private File createFileFromInputStream(InputStream inputStream) {

        try {
            OutputStream outputStream = new FileOutputStream(pdfPath);
            byte buffer[] = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            File result = new File(pdfPath);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static  void selectFontByCountry(PDF pdf )
    {




        String countryCode = getCountry();
        //countryCode = "RUS";
        InputStream inputStream ;
        GetFontByCountry getFontByCountry = new GetFontByCountry();
        String path = getFontByCountry.getFontPath(countryCode);
        FileTools.listFontSystemDirectory();
        inputStream =  ClassLoader.getSystemClassLoader().getResourceAsStream(path);



            try {
                fontForConversion = new Font(pdf, inputStream, CodePage.UNICODE, Embed.YES);


            } catch (Exception e)
            {
                String l = e.getLocalizedMessage();

            }



    }
    public static String getCountry()
    {
        return   context.getResources().getConfiguration().locale. getISO3Country();
    }
    private  void  NouvellePage()
    {

        try {

            page = new Page(pdf, A4.PORTRAIT);
            // Page Number ---------------------------------------------------------------------------------------*/
            String s =context.getResources().getString(R.string.pageNumber) + " " + Integer.toString(pagenumber);
            setFontcolor(context.getResources().getColor(R.color.PurpleSMSConverter));
            fontForConversion.setSize(sizeFont + 5.0f);
            writeLine(X  ,Y + YBasdePage + 15 ,  s);
            pagenumber = pagenumber  + 1 ;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setFontcolor(int color)
    {
        textLine.setColor(color);
    }
    public static String[] Decompose(String adecompos,String regex, int SizeMax){

        String[] temp = adecompos.split(regex);
        int nmbrDepaseSize = 0;

        for(int i=0;i <temp.length;i++){

            nmbrDepaseSize= nmbrDepaseSize + (temp[i].length()/SizeMax);
            if(temp[i].length()%SizeMax==0){nmbrDepaseSize--;}
            System.out.println(nmbrDepaseSize);
        }

        String[] retour = new String[temp.length+nmbrDepaseSize];
        int j=0;
        int tempo;
        for(int i=0;i <temp.length;i++){

            while(temp[i].length()>0){
                tempo = SizeMax;
                if(temp[i].length()<SizeMax){tempo =temp[i].length();}

                retour[j]= temp[i].substring(0,tempo);
                temp[i] = temp[i].substring(tempo);
                j++;
            }
        }

        return retour;

    }
    private void checkFinColonneAndFindePage() throws Exception
    {
        if (Ycurrent >= Y + limitBasDePageSMS) // Dépasse au bas de page
        {
            Ycurrent = Y + 59 ; // on replace en haut de page
            switch(currentColonne)
            {
                case 1: currentColonne = currentColonne + 1; Xcurrent =  XColonne2 + 8 ; break;
                case 2: currentColonne = currentColonne + 1; Xcurrent =  XColonne3 + 8 ; break;
                case 3: currentColonne = 1 ;createEnteteAndBasPage(); Xcurrent = X; break;
            }


        }
    }
    private void drawLinesSeparatrices(Path p ,int x1 , int y1 , int x2 , int y2) throws Exception
    {
        p.add(new Point(x1,  y1));
        p.add(new Point(x2,  y2));
        p.setClosePath(true);
        p.setColor(context.getResources().getColor(R.color.PurpleSMSConverter));
        p.setFillShape(true);
        p.setWidth(15);
        p.drawOn(page);
    }
    private void drawArrow(int type, int x , int y, double scale) throws Exception
    {

        assetManager = context.getAssets();
        InputStream imageStreamArrowIn  = assetManager.open("images" + File.separator + "render" + File.separator + "arrowIn2.png");
        InputStream imageStreamArrowOut = assetManager.open("images" + File.separator + "render" + File.separator + "arrowOut2.png");

        BufferedInputStream bisArrowIn =
                new BufferedInputStream(imageStreamArrowIn);

        BufferedInputStream bisArrowOut =
                new BufferedInputStream(imageStreamArrowOut);

        /* --------------- Image SMS In Out   ---------------------------------------- */

        imageOut = new Image(pdf, bisArrowOut, ImageType.PNG);

        imageIn = new Image(pdf, bisArrowIn, ImageType.PNG);


        if (type == 2)
        {
            imageOut.setPosition(x , y );
            imageOut.scaleBy(scale);
            imageOut.drawOn(page);
        }

        if (type == 1)
        {
            imageIn.setPosition(x , y );
            imageIn.scaleBy(scale);
            imageIn.drawOn(page);
        }






    }
    public String formatString(String lines) {
        final int NB_MAX = MaxLettre;
        StringBuilder maChaine = new StringBuilder(lines);
        int index = 0;

        do {
            index = maChaine.indexOf(" ", index + NB_MAX);
            if (index < 0) {
                break;
            }
            maChaine.setCharAt(index, '\n');
        }
        while (true);
        return maChaine.toString();

    }

    public String[] splitIntoLine(String input, int maxCharInLine){

        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        try {

            int lineLen = 0;
            while (tok.hasMoreTokens()) {
                String word = tok.nextToken();

                while (word.length() > maxCharInLine) {
                    output.append(word.substring(0, maxCharInLine - lineLen) + "\n");
                    word = word.substring(maxCharInLine - lineLen);
                    lineLen = 0;
                }

                if (lineLen + word.length() > maxCharInLine) {
                    output.append("\n");
                    lineLen = 0;
                }
                //            output.append(word + " ");
                output.append(word + " ").append(" ");

                lineLen += word.length() + 1;
            }
            // output.split();
            // return output.toString();
        } catch (Exception e)
        {
            String error = e.getLocalizedMessage();
            int klgmdf=63;
        }
                return output.toString().split("\n");
    }

    public  String breakLines(String input, int maxLineLength) {

        char NEWLINE = '\n';
        String SPACE_SEPARATOR = " ";
        //if text has \n, \r or \t symbols it's better to split by \s+
        String SPLIT_REGEXP= "\\s+";
        String[] tokens = input.split(SPLIT_REGEXP);

        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            if (lineLen + (SPACE_SEPARATOR + word).length() > maxLineLength) {
                if (i > 0) {
                    output.append(NEWLINE);
                }
                lineLen = 0;
            }
            if (i < tokens.length - 1 && (lineLen + (word + SPACE_SEPARATOR).length() + tokens[i + 1].length() <=
                    maxLineLength)) {
                word += SPACE_SEPARATOR;
            }

            if (lineLen + (SPACE_SEPARATOR + word).length() > maxLineLength) {

                 String[] ret =   splitToNChar(word, maxLineLength);
                 for (int piment=0; piment < ret.length; piment++)
                 {
                     output.append(ret[piment]+ NEWLINE);
                 }


            } else
            {
                output.append(word);
            }


            lineLen += word.length();
        }
        return output.toString();
    }

    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }

}










	

