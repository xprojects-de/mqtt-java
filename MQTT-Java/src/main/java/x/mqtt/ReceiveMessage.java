package x.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ReceiveMessage {

  private final MqttMessage m;
  private long tstamp = 0;
  private long deltatstamp = 0;
  private int msgCounter = 0;

  public ReceiveMessage(MqttMessage m, long delta, int msgCounter) {
    this.m = m;
    this.tstamp = System.currentTimeMillis();
    this.deltatstamp = this.tstamp - delta;
    this.msgCounter = msgCounter;
  }

  public MqttMessage getM() {
    return m;
  }

  public long getTstamp() {
    return tstamp;
  }

  public long getDeltatstamp() {
    return deltatstamp;
  }

  public void setDeltatstamp(long deltatstamp) {
    this.deltatstamp = deltatstamp;
  }

  public int getMsgCounter() {
    return msgCounter;
  }

}
