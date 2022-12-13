package tools;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

public class ThreadSMS {
	
	private String Id;
	private int count;
	private List<SMS> listSMS;
	private String address;
	private boolean checked;
	private String senderName;
	private Bitmap bitMapContact; 
	
	public ThreadSMS(String id, int count, String address, String sendername, List<SMS> listsms)
	{
		this.Id = id;
		this.count = count;
		this.address = address;
		this.listSMS = listsms;
		this.senderName = sendername;
		this.checked = false;
	}
	
	public String getId()
	{	
		return this.Id;
	}
	public void setId(String id)
	{	
		this.Id = id;
	}
	
	public int getCount()
	{	
		return this.count;
	}

    public void setCount(int count)
	{	
		this.count = count;
	}
	
	public String getSenderName()
	{	
		return this.senderName;
	}

    public void setSenderName(Context context, String SenderName)
	{
		if (SenderName.equals("?"))
		{
			//TODO A décommenter 2017 : SenderName = context.getResources().getString(R.string.jhonDoe);
		}
		this.senderName = SenderName;
	}
	
	public String getAddress()
	{	
		return this.address;
	}

    public void setAddress(String address)
	{	
		this.address = address;
	}

    public Bitmap getBitMap() {
	    return bitMapContact;
	}
	 
	public void setBitMap(Bitmap bitMapContact) {
	    this.bitMapContact = bitMapContact;
	}

    public List<SMS>  getSmsList()
	{	
		return this.listSMS;
	}

    public void setSmsList(List<SMS>  listSms)
	{	
		this.listSMS = listSms;
	}

}
