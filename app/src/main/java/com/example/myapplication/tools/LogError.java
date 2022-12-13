package tools;


public class LogError {


    private String error_message;
    private Boolean Status ;






    public void setError_message(String mess)

    {
        error_message = mess;
    }

    public void setError_status(Boolean status)

    {
        Status = status;
    }

    public String getError_message()

    {
       return error_message ;
    }

    public Boolean getError_status()

    {
        return Status ;
    }


}
