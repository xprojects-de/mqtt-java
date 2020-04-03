package x.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import x.mqtt.SslUtil;

public class MyMqttClient {

  private MqttConnectOptions connOpts;
  private MqttClient mqttClient = null;
  private final HashMap<String, MyMqttMessageListener> topicListeners = new HashMap<>();

  public static final int QOS_0 = 0;
  public static final int QOS_1 = 1;
  public static final int QOS_2 = 2;

  public void connect(String clientId, String host, int port, String user, String password, boolean ssl_tls) throws Exception {
    MqttConnectOptions connOpts = getConnOpts();
    if (connOpts == null) {
      connOpts = new MqttConnectOptions();
    }
    connOpts.setUserName(user);
    connOpts.setPassword(password.toCharArray());
    setConnOpts(connOpts);
    connect(clientId, host, port, ssl_tls);
  }

  public void connect(String clientId, String host, int port, boolean ssl_tls) throws Exception {
    if (this.mqttClient != null) {
      if (this.mqttClient.isConnected()) {
        try {
          this.mqttClient.disconnect();
        } catch (MqttException me) {
          me.printStackTrace();
        }
      }
    }

    MqttConnectOptions connOpts = getConnOpts();
    if (connOpts == null) {
      connOpts = new MqttConnectOptions();
    }
    String broker = "tcp://" + host + ":" + port;
    if (ssl_tls == true) {
      broker = "ssl://" + host + ":" + port;
      String caFilePath = "/Users/xprojects/Documents/Github/mqtt-java/MQTT-Java/hivemq/certs/server.pem";
      String clientCrtFilePath = "/Users/xprojects/Documents/Github/mqtt-java/MQTT-Java/hivemq/certs/mqtt-client-cert.pem";
      String clientKeyFilePath = "/Users/xprojects/Documents/Github/mqtt-java/MQTT-Java/hivemq/certs/mqtt-client-key.key";
      String pw = "mypassword";
      connOpts.setSocketFactory(SslUtil.getSocketFactory(caFilePath, clientCrtFilePath, clientKeyFilePath, pw));
    }
    connOpts.setCleanSession(true);

    MemoryPersistence persistence = new MemoryPersistence();
    this.mqttClient = new MqttClient(broker, clientId, persistence);
    try {
      mqttClient.connect(connOpts);
    } catch (MqttException me) {
      throw me;
    }
  }

  public boolean isConnected() {
    if (mqttClient != null) {
      return mqttClient.isConnected();
    }
    return false;
  }

  public void disconnect() {
    if (isConnected()) {
      try {
        this.unsubscribeAll();
        mqttClient.disconnect();
      } catch (MqttException me) {
        me.printStackTrace();
      }
    }
  }

  public void publish(String topic, String msg, int QOS, boolean retained) {
    try {
      mqttClient.publish(topic, msg.getBytes(), QOS, retained);
    } catch (MqttException me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, byte[] msg, int QOS, boolean retained) {
    try {
      mqttClient.publish(topic, msg, QOS, retained);
    } catch (MqttException me) {
      me.printStackTrace();
    }
  }

  public void publishFile(String topic, String path, int QOS, boolean retained) throws IOException {
    byte[] fileContent;
    try {
      fileContent = Files.readAllBytes(Paths.get(path));
    } catch (IOException e) {
      throw e;
    }

    try {
      mqttClient.publish(topic, fileContent, QOS, true);
    } catch (MqttException me) {
      me.printStackTrace();
    }
  }

  public void subscribe(String topic, int QOS, MyIMqttMessageListener ml) {
    try {
      if (!this.topicListeners.containsKey(topic)) {
        MyMqttMessageListener l = new MyMqttMessageListener(ml);
        this.topicListeners.put(topic, l);
        this.mqttClient.subscribe(topic, QOS, l.getMl());
      }
    } catch (Exception me) {
      me.printStackTrace();
    }

  }

  public void unsubscribe(String topic) {
    try {
      if (this.topicListeners.containsKey(topic)) {
        this.mqttClient.unsubscribe(topic);
        this.topicListeners.remove(topic);
      }
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void unsubscribeAll() {
    try {
      for (Map.Entry me : this.topicListeners.entrySet()) {
        this.mqttClient.unsubscribe((String) me.getKey());
      }
      this.topicListeners.clear();
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void setActive(String topic, boolean state) {
    try {
      if (this.topicListeners.containsKey(topic)) {
        this.topicListeners.get(topic).setActive(state);
      }
    } catch (Exception me) {
      me.printStackTrace();
    }
  }

  public void setConnOpts(MqttConnectOptions connOpts) {
    this.connOpts = connOpts;
  }

  public MqttConnectOptions getConnOpts() {
    return this.connOpts;
  }

  public String generateClientId() {
    return MqttClient.generateClientId();
  }

}
