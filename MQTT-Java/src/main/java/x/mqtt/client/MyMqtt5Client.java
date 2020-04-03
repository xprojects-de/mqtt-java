package x.mqtt.client;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;

public class MyMqtt5Client {

  private Mqtt5AsyncClient mqttClient = null;

  public static final MqttQos QOS_0 = MqttQos.AT_MOST_ONCE;
  public static final MqttQos QOS_1 = MqttQos.AT_LEAST_ONCE;
  public static final MqttQos QOS_2 = MqttQos.EXACTLY_ONCE;

  public void connect(String clientId, String host, int port, String user, String password, boolean ssl_tls) throws Exception {
    if (this.mqttClient == null) {
      this.mqttClient = MqttClient.builder()
              .identifier(clientId)
              .serverHost(host)
              .serverPort(port)
              .useMqttVersion5()
              .buildAsync();
      this.mqttClient.connectWith()
              .simpleAuth()
              .username(user)
              .password(password.getBytes())
              .applySimpleAuth()
              .send()
              .whenComplete((connAck, throwable) -> {
                System.out.println("connected");
              });
    }
  }

  public void disconnect() {
    try {
      mqttClient.disconnect();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, String msg, int QOS, boolean retained) {
    try {
      MqttQos QOS_final = MyMqtt5Client.QOS_0;
      if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_1;
      } else if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_2;
      }
      mqttClient.publishWith().topic(topic).qos(QOS_final).retain(retained).payload(msg.getBytes()).send();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, String msg, MqttQos QOS, boolean retained) {
    try {
      mqttClient.publishWith().topic(topic).qos(QOS).retain(retained).payload(msg.getBytes()).send();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, byte[] msg, int QOS, boolean retained) {
    try {
      MqttQos QOS_final = MyMqtt5Client.QOS_0;
      if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_1;
      } else if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_2;
      }
      mqttClient.publishWith().topic(topic).qos(QOS_final).retain(retained).payload(msg).send();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, byte[] msg, MqttQos QOS, boolean retained) {
    try {
      mqttClient.publishWith().topic(topic).qos(QOS).retain(retained).payload(msg).send();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void subscribe(String topic, int QOS, MyIMqtt5MessageListener ml) {
    try {
      MqttQos QOS_final = MyMqtt5Client.QOS_0;
      if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_1;
      } else if (QOS == 1) {
        QOS_final = MyMqtt5Client.QOS_2;
      }
      this.mqttClient.toAsync().subscribeWith()
              .topicFilter(topic)
              .qos(QOS_final)
              .callback(messageResult -> {
                try {
                  ml.messageArrived(messageResult);
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
              })
              .send();
    } catch (Exception me) {
      me.printStackTrace();
    }

  }

  public void unsubscribe(String topic) {
    try {
      this.mqttClient.unsubscribeWith().topicFilter(topic).send();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void setActive(String topic, boolean state) {

  }

  public boolean isConnected() {
    return false;
  }

}
