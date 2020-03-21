package x.mqtt;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import x.mqtt.client.MyMqttClient;

public class MqttMain {

  private static final String MQTTHOST = "localhost";
  private static final int MQTTPORT = 1883;

  private final MyMqttClient mqttSender = new MyMqttClient();
  private final MyMqttClient mqttListener = new MyMqttClient();
  private final String testTopic = "topic/test";

  private void connectMQTT() throws Exception {
    if (mqttSender.isConnected() == false) {
      mqttSender.connect("spex_" + System.currentTimeMillis(), MQTTHOST, MQTTPORT);
    }
    if (mqttListener.isConnected() == false) {
      mqttListener.connect("spex1_" + System.currentTimeMillis(), MQTTHOST, MQTTPORT);
      mqttListener.subscribe(testTopic, (String topic, MqttMessage message) -> {
        String msg = new String(message.getPayload());
        System.out.println("Message arrived --> Topic: " + topic + " | Message: " + msg + "<>" + System.currentTimeMillis());
      });
    }
  }

  private void disconnectMQTT() throws Exception {
    if (mqttSender.isConnected()) {
      mqttSender.disconnect();
    }
    if (mqttListener.isConnected()) {
      mqttListener.unsubscribe(testTopic);
      mqttListener.disconnect();
    }

  }

  private void sendMessage(String msg) {
    mqttSender.publish(testTopic, msg);
  }

  public void start() throws Exception {
    connectMQTT();
    int loops = 1000;
    long start = System.currentTimeMillis();
    for (int u = 0; u < loops; u++) {
      sendMessage("Hallo Welt! => " + u + " | " + System.currentTimeMillis());
    }
    long stop = System.currentTimeMillis();
    System.out.println("elapsed = " + (stop - start) + " / " + ((stop - start) / loops));
    disconnectMQTT();
  }

  public static void main(String[] args) {
    MqttMain mm = new MqttMain();
    try {
      mm.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
