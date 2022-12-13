package tools;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;

import activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static tools.ConversationSmsContactProvider.uri;

public class ConversationSmsContactDatabase extends  SQLiteOpenHelper   {

    private static final    String          DATABASE_CONTACTS = "Contacts.db";
    public  static          Context         context = null;
    private                 List<String>    donnee = new ArrayList<String>();
    private static          long            _id = 0;
    public                  Cursor          conversation = null;
    private                 MatrixCursor    matrixCursor;
    private                 String          address;


    public ConversationSmsContactDatabase(Context context) {
        super( context, DATABASE_CONTACTS, null, 1);
        ConversationSmsContactDatabase.context = context ;
        //TODO Mis en commentaire 15/01/2018 : conversation = ConversationSmsContactDatabase.context.getContentResolver().query(uri, null, null, null, "date desc");
        //conversation = ConversationSmsContactDatabase.context.getContentResolver().query(uri, null, null, null, "date desc");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        int y = 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int y = 0;

    }



    public    Cursor getContact(Context context,String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

           try {
             matrixCursor  =  new MatrixCursor(new String[] { "_id" ,  "thread_id", "total", "nom","tel","photo" });
            } catch (Error e)
            {
                Log.d("ERREUR ---> MatrixCur :" , e.toString());
            }

        conversation = ConversationSmsContactDatabase.context.getContentResolver().query(uri, null, null, null, "date desc");
        //DatabaseUtils.dumpCursor(cursor);
        if ( conversation != null) {

            while (conversation.moveToNext()) {

                donnee.clear();
                try {
                    for (int k = 0; k < 5; k++) {
                        donnee.add(k, "____");
                    }

                } catch (Error e)
                {
                    Log.d("ERREUR ---> Donnée :" , e.toString());
                }
                String msg_count = conversation.getString(conversation.getColumnIndexOrThrow("msg_count")).toString();
                donnee.set(0,msg_count );
                int IdThread = Integer.parseInt(conversation.getString(conversation.getColumnIndexOrThrow("thread_id")).toString());

                //TODO ------------------------------- A EFFACER ------------------------------------------------------- */

                Uri mSmsinboxQueryUri = Uri.parse("content://sms/inbox");
                mSmsinboxQueryUri = Uri.parse("content://sms");
                String where = "thread_id="+ IdThread;
                Cursor cursor1 = context.getContentResolver().query(mSmsinboxQueryUri,new String[] { "_id", "thread_id", "address", "person", "date","body", "type" }, where, null, null);
                //DatabaseUtils.dumpCursor(cursor1);
                String[] columns = new String[] { "address", "person", "date", "body","type" };
                if (cursor1.getCount() > 0) {
                    String count = Integer.toString(cursor1.getCount());
                    while (cursor1.moveToNext()){
                          address = cursor1.getString(cursor1.getColumnIndex(columns[0]));
                          cursor1.moveToLast();
                    }
                }

                //TODO FIN -------------------------------------------------------------------------------------------------------*/
               // getContactFromNumber(IdThread);
                donnee.set(1,address );
               // getContactName(address);
                if (!"".equals(donnee.get(1)) && donnee.get(1) != null )
                {
                    GetNameEtPhoto(donnee.get(1) );
                }
                String[] resultString = new String []{String.valueOf(_id), String.valueOf(IdThread),donnee.get(0), donnee.get(2),donnee.get(1),donnee.get(3) };
                matrixCursor.addRow(resultString);
                _id = _id + 1 ;
                donnee.clear();

            }
        }
        if (conversation != null ) conversation.close();
        donnee.clear();

       // DatabaseUtils.dumpCursor(matrixCursor);
        MainActivity.matrixCursorThread = matrixCursor;
        return null;
    }




      /* ---------------------------------------------------------------------------------------------------------------- */
      public String GetNameEtPhoto(String phoneNumber) {
          Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
          String name = "__";
          String id = "__";
          String pathToPhoto = "__";

          ContentResolver contentResolver = context.getContentResolver();
          Cursor contact = contentResolver.query(uri,
                                                 new String[] {BaseColumns._ID,ContactsContract.PhoneLookup.DISPLAY_NAME,ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI },
                                                 null, null, null);
          //DatabaseUtils.dumpCursor(contact);

          try {
              if (contact != null && contact.getCount() > 0) {
                  contact.moveToNext();
                  name = contact.getString(contact.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                  id = contact.getString(contact.getColumnIndex(ContactsContract.Data._ID));

                  pathToPhoto = contact.getString(contact.getColumnIndex(ContactsContract.Data.PHOTO_THUMBNAIL_URI));
              }
          } finally {
              if (contact != null) {
                 //DatabaseUtils.dumpCursor(contact);
                  contact.close();
              }

          }
          donnee.set(2,name);
          donnee.set(3,pathToPhoto);
          donnee.set(4,id);
          return name;
      }




}


























































