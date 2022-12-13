package async;

import android.content.Context;
import android.os.AsyncTask;

import com.pdfjet.PDF;

import java.io.FileOutputStream;

import tools.PDFJetTools3Cols;

public class LoadFontPDFJetInBackground extends AsyncTask<Void, String, Boolean> {
    private Context context;






    private String mailAddress ="";
    private String pdfPath  = "";

    public LoadFontPDFJetInBackground(Context context,String pdfpath) {

        this.context = context;
        this.pdfPath = pdfpath;

    }

    @Override
    protected Boolean doInBackground(Void... voids) {


        PDFJetTools3Cols.isFontLoaded = false ;
        String fontsSystem = "DroidSans-Bold.ttf";

        try {
            FileOutputStream fos = new FileOutputStream(pdfPath);
            PDFJetTools3Cols.pdf = new PDF(fos);
            //String country = context.getResources().getConfiguration().locale. getISO3Country();
            PDFJetTools3Cols.selectFontByCountry(PDFJetTools3Cols.pdf );
            PDFJetTools3Cols.isFontLoaded = true;

        } catch (Exception e)
        {
            e.getLocalizedMessage();
        }




        return PDFJetTools3Cols.isFontLoaded;
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);



    }



}