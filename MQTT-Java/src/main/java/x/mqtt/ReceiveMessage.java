package x.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ReceiveMessage {

  private final MqttMessage m;
  private long tstamp = 0;

  public ReceiveMessage(MqttMessage m) {
    this.m = m;
    this.tstamp = System.currentTimeMillis();
  }

  public MqttMessage getM() {
    return m;
  }

  public long getTstamp() {
    return tstamp;
  }

}
