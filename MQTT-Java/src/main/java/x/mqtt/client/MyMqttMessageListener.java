package x.mqtt.client;

import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttMessageListener {

  private IMqttMessageListener ml;
  private AtomicBoolean active = new AtomicBoolean(true);

  public MyMqttMessageListener(MyIMqttMessageListener myml) {
    ml = new IMqttMessageListener() {
      @Override
      public void messageArrived(String string, MqttMessage mm) throws Exception {
        if (active.get() == true) {
          myml.messageArrived(string, mm);
        }
      }
    };
  }

  synchronized public void setActive(boolean active) {
    this.active.set(active);
  }

  public IMqttMessageListener getMl() {
    return ml;
  }

}
