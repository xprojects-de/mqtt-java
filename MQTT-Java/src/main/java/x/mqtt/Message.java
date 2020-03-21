package x.mqtt;

public class Message {

  private String msg = "";
  private boolean error = true;

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public String getMsg() {
    return msg;
  }

  public boolean isError() {
    return error;
  }

}
