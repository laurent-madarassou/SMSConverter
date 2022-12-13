package tools;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;



public class SmsTools   {

	public static  List<SMS> smsList;
	public static  List<ThreadSMS> threadSMSList;
    private static String number;
    private static String ID;


	private Bitmap localBitMapContact; 
	private  Context context;

    public   ThreadSMS threadSMS;
    public static MatrixCursor mxCursorThread = null;

    public SmsTools(Context context, String id, String nom ){
        this.localBitMapContact = null;
        this.smsList = null;
        this.context = context;
        this.ID = id ;
        this.threadSMS = new ThreadSMS(ID,0,null,nom,null);
        int i = 0 ;
    }

	public   ThreadSMS addSmsList()
	{

		smsList = new ArrayList<SMS>();

        String where = "thread_id="+ ID; // "thread_id=1" ;
        Bitmap bitmap = null;
        String body ="";
        byte[] bodyBytes;

        String  date = null;
        int type = 0;
        String ContactId ="";
        String name="";
        SMS sms = null;

/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        	Uri uriSms = Uri.parse("content://sms");

	        Cursor smsCursor= context.getContentResolver().query(uriSms, null, where ,null,null);
	        if(smsCursor != null)
            {
                DatabaseUtils.dumpCursor(smsCursor);
                if (smsCursor.moveToFirst())
                {
                   do {

                        // DatabaseUtils.dumpCursor(smsCursor);

                        type = smsCursor.getInt(smsCursor.getColumnIndex("type"));
                        if (type != 3) {
                            body = smsCursor.getString(smsCursor.getColumnIndexOrThrow("body"));  //### body
                            number = smsCursor.getString(smsCursor.getColumnIndexOrThrow("address"));
                            String SmsDate = smsCursor.getString(smsCursor.getColumnIndexOrThrow("date"));
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                            String dateString = formatter.format(new Date(Long.parseLong(SmsDate)));
                            String SmsTime = smsCursor.getString(smsCursor.getColumnIndexOrThrow("date"));
                            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
                            String timeString = formatterTime.format(new Date(Long.parseLong(SmsTime)));
                            date = dateString + " - " + timeString;
                            body = body.replaceAll("\\\\n", " ");
                            sms = new SMS(null, number, body, null, null, date, type, null);
                            smsList.add(sms);
                        }

                    }   while (smsCursor.moveToNext());// fin WHILE
                }//Fin de MoveTofirst
	        } // fin IF cur != null


        threadSMS.setSmsList(smsList);
        threadSMS.setAddress(number);

        threadSMS.setSenderName(context,completeThread(number));
        threadSMS.setBitMap(localBitMapContact );

        return threadSMS;

		
	}

    public String completeThread(String number) {





        long contactId =  getContactIDFromNumber(number);


            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
            Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            Cursor cursor = context.getContentResolver().query(photoUri,
                    new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
            if (cursor == null) {
                return null;
            }
            try {
                if (cursor.moveToFirst()) {
                    byte[] data = cursor.getBlob(0);
                    if (data != null) {
                        localBitMapContact =  BitmapFactory.decodeStream(new ByteArrayInputStream(data));
                    }
                }
            } finally {
                cursor.close();
            }



        contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        final String[] contactProjection = new String[] {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.DISPLAY_NAME};

        Cursor c = context.getContentResolver().query(contactUri, contactProjection, null, null, null);


        try {
            if (c.moveToFirst()) {

                 return c.getString(c.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));



            }
        } catch (Exception e) {
            //Log.d(TAG, "Unable to retrieve contact with phone number " + phoneNumber);
        } finally {
            c.close();
        }
        return "";

    }

    public  long getContactIDFromNumber(String contactNumber) {
        String UriContactNumber = Uri.encode(contactNumber);
        long phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, UriContactNumber),
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getLong(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }


	
	public static Bitmap loadContactPhoto(ContentResolver cr, long  id) {
		    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
		    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
		    if (input == null) {

		        return null;
		    }
		    return BitmapFactory.decodeStream(input);
		}
  




    
}