package tools;



import android.graphics.Bitmap;


public class SMS {
	
	private String senderName;
	private String senderNumber;
	private String body;
	private String contactID;
	private boolean hasContactId;
	private Bitmap bitMapContact;
    private int type;
    private String date ;
    private Bitmap mmsImage;

	
	

	 
	public SMS(String senderName, String senderNumber, String body, String contactId, Bitmap bitMapContact, String date , int type, Bitmap mmsImage )
	{
		setBitMap(bitMapContact);
		setBody(body);
		setsenderName(senderName);
		setcontactID(contactId);
		setsenderNumber(senderNumber);
        setType(type);
        setDate(date);
        setmmsImage(mmsImage);
		
		
		 
	}

	public boolean gethasContactId() {
	    return this.hasContactId;
	}
	 
	public void sethasContactId(boolean hasContactId) {
	    this.hasContactId = hasContactId;
	}
	
	public Bitmap getBitMap() {
	    return bitMapContact;
	}
	 
	public void setBitMap(Bitmap bitMapContact) {
	    this.bitMapContact = bitMapContact;
	}
	 
	public String getBody() {
	    return this.body;
	}
	 
	public void setBody(String body) {
	    this.body = body;
	}	

	public String getsenderName() {
	    return this.senderName;
	}
	 
	public void setsenderName(String senderName) {
	    this.senderName = senderName;
	}	
	public String getcontactID() {
	    return this.contactID;
	}
	 
	public void setcontactID(String contactID) {
	    this.contactID = contactID;
	}
	public String getsenderNumber() {
	    return this.senderNumber;
	}
	 
	public void setsenderNumber(String senderNumber) {
	    this.senderNumber = senderNumber;
	}

    public void setType(int type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getType() {
        return this.type;
    }
    public String getDate() {
        return this.date;
    }
    public void setmmsImage(Bitmap mmsimage) {
        this.mmsImage = mmsimage;
    }
    public Bitmap getmmsImage() {
        return this.mmsImage;
    }


}
