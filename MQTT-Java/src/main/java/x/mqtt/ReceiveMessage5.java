package x.mqtt;

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;

public class ReceiveMessage5 {

  private final Mqtt5Publish m;
  private long tstamp = 0;

  public ReceiveMessage5(Mqtt5Publish m) {
    this.m = m;
    this.tstamp = System.currentTimeMillis();
  }

  public Mqtt5Publish getM() {
    return m;
  }

  public long getTstamp() {
    return tstamp;
  }

}
