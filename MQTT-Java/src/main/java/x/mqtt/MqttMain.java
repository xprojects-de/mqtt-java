package x.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import x.mqtt.client.MyMqttClient;

public class MqttMain {

  private static final String MQTTHOST = "localhost";
  private static final int MQTTPORT = 1883;
  private static final int MQTTPORTTLS = 8883;

  private final MyMqttClient mqttSender = new MyMqttClient();
  private final MyMqttClient mqttListener = new MyMqttClient();

  private final String testTopic = "topic/test";

  private void connectMQTT() throws Exception {
    if (mqttSender.isConnected() == false) {
      mqttSender.connect("spex_" + System.currentTimeMillis(), MQTTHOST, MQTTPORTTLS, true);
    }
    if (mqttListener.isConnected() == false) {
      mqttListener.connect("spex1_" + System.currentTimeMillis(), MQTTHOST, MQTTPORTTLS, true);
      mqttListener.subscribe(testTopic, (String topic, MqttMessage message) -> {
        System.out.println("---------------------------------------");
        String msg = new String(message.getPayload());
        System.out.println("Message arrived --> Topic: " + topic + " | Message: " + msg + "<>" + System.currentTimeMillis());
        try {
          ObjectMapper mapper = new ObjectMapper();
          Message m = mapper.readValue(msg, Message.class);
          System.out.println("Error: " + m.isError() + " Msg: " + m.getMsg());
        } catch (Exception e) {
          e.printStackTrace();
        }
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

  private void sendMessage(String msg) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Message m = new Message();
    m.setError(false);
    m.setMsg(msg);
    var jsonInString = mapper.writeValueAsString(m);
    mqttSender.publish(testTopic, jsonInString);
  }

  public void start() throws Exception {
    connectMQTT();
    int loops = 10;
    long start = System.currentTimeMillis();
    for (int u = 0; u < loops; u++) {
      sendMessage("Hallo Welt! => " + u + " | " + System.currentTimeMillis());
      Thread.sleep(5);
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
