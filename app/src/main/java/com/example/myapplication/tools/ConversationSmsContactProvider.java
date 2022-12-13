package tools;

/**
 * Created by user on 22/06/2016.
 */


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;



/*---------------------------------------*/
//  Class permettant d'extraire les contacts
//  associés à une conversation
/*---------------------------------------*/

public  class ConversationSmsContactProvider extends ContentProvider {



    private static final String PROVIDER_NAME = "tools.ConversationSmsContactProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "");
    private static final int CONTACT = 1;
    private static final int IMAGE_ID = 2;
    private static final UriMatcher uriMatcher = getUriMatcher();
    public static Context context = null;
    private static ConversationSmsContactDatabase conversationSmsContactDatabase = null;

    public static boolean isConverstionLoadingFinished = false;
    public static  Cursor conversation = null;

    final static String CONVERSTATION_ALL = "content://sms/conversations/";
    static Uri uri = Uri.parse(CONVERSTATION_ALL);



    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contact", CONTACT);
        //uriMatcher.addURI(PROVIDER_NAME, "images/#", IMAGE_ID);
        return uriMatcher;
    }

    @Override
    public String getType(Uri uri) {

        return "";
    }

    @Override
    public boolean onCreate() {


        context  = getContext();
        ConversationSmsContactDatabase.context = context;

        return true;

    }




    //--------------------------------------------------

    //---------------------------------------
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        ConversationSmsContactDatabase conversationSmsContactDatabase = new ConversationSmsContactDatabase(context);
        return conversationSmsContactDatabase.getContact(context,null, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }



}