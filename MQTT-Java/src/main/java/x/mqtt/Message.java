package x.mqtt;

public class Message {

  private String msg = "";
  private boolean error = true;
  private long timestamp = 0;
  private boolean looptest = false;
  private int loopcounter = 0;

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

  public long getTimestamp() {
    return timestamp;
  }

  public boolean isLooptest() {
    return looptest;
  }

  public void setLooptest(boolean looptest) {
    this.looptest = looptest;
  }

  public int getLoopcounter() {
    return loopcounter;
  }

  public void setLoopcounter(int loopcounter) {
    this.loopcounter = loopcounter;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
