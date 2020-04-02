package x.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MyIMqttMessageListener {

  public void messageArrived(String string, MqttMessage mm) throws Exception;
}
