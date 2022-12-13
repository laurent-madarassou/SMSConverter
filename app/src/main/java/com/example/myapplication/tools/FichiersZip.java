package tools;

/**
 * Created by user on 27/11/16.
 */

public class FichiersZip {

    String id ;
    String nameSender;
    String PathZIP;
    String Zip;
    String Size;
    String date;
    Boolean last;

    public FichiersZip(String Id , String namesender , String listzip, String zip, String size, String Date)
    {

        id  = Id;
        nameSender = namesender;
        PathZIP = listzip;
        Zip = zip;
        Size = size;
        date = Date ;
        last = false;
    }

    public String getId() { return id ;}
    public void setId(String ids) { id = ids  ;}

    public String getNameSender()
    {
        return nameSender;
    }
    public String getPathZIP()
    {
        return PathZIP;
    }
    public String getZIP()
    {
        return Zip;
    }
    public String getSize()
    {
        return Size;
    }
    public String getDate()
    {
        return date;
    }
    public boolean getLast()
    {
        return last;
    }
    public void setLast(boolean l)
    {
        last = l;
    }


}
