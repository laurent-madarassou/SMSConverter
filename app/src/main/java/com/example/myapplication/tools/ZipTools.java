package tools;

import android.content.Context;
import android.util.Log;

import re.devboxx.smsconverter.BuildConfig;
import re.devboxx.smsconverter.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dayvead on 30/11/13.
 */
public class ZipTools {

    private Context context = null;
    private String ID;
    private String nom;

    private String pdfPath;
    private String htmlPath;
    private String jsonPath;
    private String xmlPath;
    private String csvPath;
    private String txtPath;

    private final int BUFFER = 1024;

    public  String SMSConverterDirectoryPath ="";
    public  String zipFileName ;
    public  String lastZipFileName ;
    private String buildFlavor ;

    private List<String> arrayFiles;
    public  String directoryName = "";
    public  String directoryApplicationName = "";
    public  String HTMLExtentionName = "html";
    public  String HTMLImagesExtentionName = "images";
    public  String PDFExtentionName = "pdf";
    public  String XMLExtentionName = "xml";
    public  String JSONExtentionName = "json";
    public  String CSVExtentionName = "csv";
    public  String TXTExtentionName = "txt";
    public  String TokenName = "";


    public  String CSSBubbleFileName = "style-bubble.css";
    public  String CSSClassicFileName = "style-classic.css";
    public  String htmlBubblePath ;
    public  String htmlClassicPath ;
    public  String htmlCSSBubblePath ;
    public  String htmlCSSClassicPath ;
    public  String htmlImagesPath;



    public ZipTools(Context context, String ids, String noms,FileTools filetools)
    {
        this.context = context;
        this.nom = noms ;
        this.ID  = ids;
        TokenName =  filetools.TokenName;
        directoryName = context.getResources().getString(R.string.app_name);

        directoryApplicationName = context.getApplicationInfo().dataDir;
        directoryName = directoryName.replaceAll(" ", "");


        pdfPath = filetools.PDFdirectoryName;
        htmlPath =  filetools.HTMLdirectoryName;
        xmlPath =filetools.XMLdirectoryName;
        jsonPath =  filetools.JSONdirectoryName;
        csvPath = filetools.CSVdirectoryName;
        txtPath = filetools.TXTdirectoryName;

        String fileName = filetools.UserDirectory;
        zipFileName =filetools.CurrentDirectory +  fileName + ".zip";

        pdfPath =  pdfPath  +  fileName + "." + PDFExtentionName;
        xmlPath =  xmlPath  +  fileName + "." + XMLExtentionName;
        jsonPath =  jsonPath +  fileName + "." + JSONExtentionName;
        csvPath = csvPath +  fileName + "." + CSVExtentionName;
        txtPath = txtPath  +  fileName + "." + TXTExtentionName;

        htmlBubblePath =  htmlPath  +  fileName + "-bubble." + HTMLExtentionName;
        htmlClassicPath =  htmlPath  +  fileName + "-classic." + HTMLExtentionName;
        htmlCSSBubblePath =  htmlPath   + CSSBubbleFileName;
        htmlCSSClassicPath =  htmlPath   +CSSClassicFileName;
        htmlImagesPath =  htmlPath   +  HTMLImagesExtentionName;

        arrayFiles = new ArrayList<String>();
        arrayFiles.add(pdfPath);
        arrayFiles.add(xmlPath);
        arrayFiles.add(jsonPath);
        arrayFiles.add(csvPath);
        arrayFiles.add(txtPath);
        arrayFiles.add(htmlBubblePath);
        arrayFiles.add(htmlClassicPath);
        arrayFiles.add(htmlCSSBubblePath);
        arrayFiles.add(htmlCSSClassicPath);
        arrayFiles.add(htmlImagesPath);
        arrayFiles.add(TokenName);

        buildFlavor = BuildConfig.FLAVOR;


    }

    public void zipAll2() throws IOException
    {

        BufferedInputStream origin = null;
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
        // Création Fichier Token
        createTokenHiddenFile();

        try {
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < arrayFiles.size(); i++) {
                String currentFile =arrayFiles.get(i);



                File fileToZip = new File(currentFile);
                if (fileToZip.exists()) {
                    if(fileToZip.isDirectory())
                    {
                        zipSubFolder(out, fileToZip, fileToZip.getParent().length());
                    }
                    else
                    {
                        FileInputStream fi = new FileInputStream(currentFile);
                        origin = new BufferedInputStream(fi, BUFFER);
                        try {
                            ZipEntry entry = new ZipEntry(currentFile.substring(currentFile.lastIndexOf("/") + 1));
                            out.putNextEntry(entry);
                            int count;
                            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                                out.write(data, 0, count);
                            }
                        }
                        finally {
                            origin.close();
                        }
                    }
                }
            }
        }
        finally {
            out.close();
           // FileTools.currentZipToSendSize =  zipFileName;
            String  size = GetFileSize(zipFileName);
            lastZipFileName = zipFileName ;
           // FileTools.destroyFileParam();
           // FileTools.PutFileParam(lastZipFileName);
        }
    }


    public boolean zipFileAtPath(String sourcePath, String toLocation) {

        File sourceFile = new File(sourcePath);
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(toLocation);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            if (sourceFile.isDirectory()) {
                zipSubFolder(out, sourceFile, sourceFile.getParent().length());
            } else {
                byte data[] = new byte[BUFFER];
                FileInputStream fi = new FileInputStream(sourcePath);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private void zipSubFolder(ZipOutputStream out, File folder, int basePathLength) throws IOException {
        File[] fileList = folder.listFiles();
        BufferedInputStream origin = null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                zipSubFolder(out, file, basePathLength);
            } else {
                byte data[] = new byte[BUFFER];
                String unmodifiedFilePath = file.getPath();
                String relativePath = unmodifiedFilePath.substring(basePathLength);
                Log.i("ZIP SUBFOLDER", "Relative Path : " + relativePath);
                FileInputStream fi = new FileInputStream(unmodifiedFilePath);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(relativePath);
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
        }
    }

    public String getLastPathComponent(String filePath) {
        String[] segments = filePath.split("/");
        String lastPathComponent = segments[segments.length - 1];
        return lastPathComponent;
    }

    public String GetFileSize(String fileName)
    {
        File fSize = new File(fileName);
        long sizeL = fSize.length()/1024;
        String sizeS = String.valueOf(sizeL);
        if (sizeL > 1024)
        {
            sizeL = sizeL / 1024 ;
            sizeS = String.valueOf(sizeL) + " Mo";

        } else
        {
            sizeS = String.valueOf(sizeL) + " Ko";
        }
        //FileTools FileTools = new FileTools(context);

        //TODO filetools.currentZipToSendSize = sizeS ;


        return sizeS ;
    }

    public void createTokenHiddenFile()
    {

        File fTokken = new File(TokenName);
        if (!fTokken.exists())
        {
            try {
                fTokken.createNewFile();
            } catch (IOException e)
            {
                String err= e.getLocalizedMessage();

            }
        }
    }
}
