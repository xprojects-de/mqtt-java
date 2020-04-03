package x.mqtt.client;

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;

public interface MyIMqtt5MessageListener {

  public void messageArrived(Mqtt5Publish mm) throws Exception;
}
