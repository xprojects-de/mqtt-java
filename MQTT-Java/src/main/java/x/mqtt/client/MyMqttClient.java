package x.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import x.mqtt.SslUtil;

public class MyMqttClient {

  private MqttConnectOptions connOpts;
  private MqttClient mqttClient = null;

  public static final int QOS = 1;

  public void connect(String clientId, String host, int port, String user, char[] password, boolean ssl_tls) throws Exception {
    MqttConnectOptions connOpts = getConnOpts();
    if (connOpts == null) {
      connOpts = new MqttConnectOptions();
    }
    connOpts.setUserName(user);
    connOpts.setPassword(password);
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
        mqttClient.disconnect();
      } catch (MqttException me) {
        me.printStackTrace();
      }
    }
  }

  public void publish(String topic, String msg) {
    try {
      mqttClient.publish(topic, msg.getBytes(), QOS, true);
    } catch (MqttException me) {
      me.printStackTrace();
    }
  }

  public void publish(String topic, byte[] msg) {
    try {
      mqttClient.publish(topic, msg, QOS, true);
    } catch (MqttException me) {
      me.printStackTrace();
    }
  }

  public void publishFile(String topic, String path) throws IOException {
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

  public void subscribe(String topic, IMqttMessageListener ml) {
    try {
      this.mqttClient.subscribe(topic, ml);
    } catch (MqttException me) {
      me.printStackTrace();
    }

  }

  public void unsubscribe(String topic) {
    try {
      this.mqttClient.unsubscribe(topic);
    } catch (MqttException me) {
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
