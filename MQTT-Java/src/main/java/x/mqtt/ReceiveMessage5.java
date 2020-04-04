package x.mqtt;

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;

public class ReceiveMessage5 {

  private final Mqtt5Publish m;
  private long tstamp = 0;
  private long deltatstamp = 0;
  private int msgCounter = 0;

  public ReceiveMessage5(Mqtt5Publish m, long delta, int msgCounter) {
    this.m = m;
    this.tstamp = System.currentTimeMillis();
    this.deltatstamp = this.tstamp - delta;
    this.msgCounter = msgCounter;
  }

  public Mqtt5Publish getM() {
    return m;
  }

  public long getTstamp() {
    return tstamp;
  }

  public long getDeltatstamp() {
    return deltatstamp;
  }

  public int getMsgCounter() {
    return msgCounter;
  }

}
