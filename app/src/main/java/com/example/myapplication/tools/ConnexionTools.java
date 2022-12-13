package tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//import static tools.ConnexionTools.ConversationSmsContactProvider.uri;

/**
 * Created by user on 30/11/16.
 */

public class ConnexionTools {

    private Context context;


    public ConnexionTools(Context c)
    {
        context = c;
    }


    public NetworkInfo getWifi(){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnectedOrConnecting())
        {
            String wifiInfo = mWifi.getExtraInfo();
            //TODO Debuggae  Toast.makeText(context,  " mWifi ConnectedOrConnecting", Toast.LENGTH_LONG).show();
        }
        return mWifi;
    }
    public NetworkInfo getConnexion(){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mConnexion = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            //if (mConnexion.isConnectedOrConnecting())
           // {
                //TODO Debuggae Toast.makeText(context,  " mConnexion ConnectedOrConnecting", Toast.LENGTH_LONG).show();
           // }
        return mConnexion;
    }


}



