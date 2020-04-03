/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package x.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import x.mqtt.client.MyIMqtt5MessageListener;
import x.mqtt.client.MyIMqttMessageListener;
import x.mqtt.client.MyMqtt5Client;
import x.mqtt.client.MyMqttClient;

/**
 *
 * @author prokist
 */
public class TestFrame extends javax.swing.JFrame implements Runnable {

  private static final String USERNAME = "xprojects";
  private static final String PW = "xprojects";
  private static final boolean SSL = false;

  private final MyMqtt5Client mqttClient = new MyMqtt5Client();
  //private final MyMqttClient mqttClient = new MyMqttClient();
  private MyIMqttMessageListener ml;
  private MyIMqtt5MessageListener ml5;
  public static BlockingQueue queue = new ArrayBlockingQueue(10000);
  public static Object mutex = new Object();
  private Thread consumer = new Thread(this);

  /**
   * Creates new form TestFrame
   */
  public TestFrame() {
    initComponents();
    consumer.start();
    ml = new MyIMqttMessageListener() {
      @Override
      public void messageArrived(String topic, MqttMessage message) throws Exception {
        TestFrame.queue.put(new ReceiveMessage(message));
      }
    };
    ml5 = new MyIMqtt5MessageListener() {
      @Override
      public void messageArrived(Mqtt5Publish message) throws Exception {
        TestFrame.queue.put(new ReceiveMessage5(message));
      }
    };
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    jTextFieldMessage = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jButtonSend = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextAreaLog = new javax.swing.JTextArea();
    jButtonConnect = new javax.swing.JButton();
    jButtonDisconnect = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldTopic = new javax.swing.JTextField();
    jButtonTopicSubscribe = new javax.swing.JButton();
    jButtonTopicUnsubscribe = new javax.swing.JButton();
    jButtonTopicDisable = new javax.swing.JButton();
    jButtonTopicEnable = new javax.swing.JButton();
    jButtonClear = new javax.swing.JButton();
    jButtonLoopTest = new javax.swing.JButton();
    jTextFieldLoopTestLoops = new javax.swing.JTextField();
    jTextFieldLoopTestDelay = new javax.swing.JTextField();
    jComboBoxQoSSubscribe = new javax.swing.JComboBox<>();
    jComboBoxQoSLoop = new javax.swing.JComboBox<>();
    jComboBoxQoSSend = new javax.swing.JComboBox<>();
    jTextFieldBytesSize = new javax.swing.JTextField();
    jCheckBoxBinary = new javax.swing.JCheckBox();
    jTextFieldHost = new javax.swing.JTextField();
    jTextFieldPort = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Message:");

    jButtonSend.setText("send");
    jButtonSend.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonSendActionPerformed(evt);
      }
    });

    jTextAreaLog.setColumns(20);
    jTextAreaLog.setRows(5);
    jScrollPane1.setViewportView(jTextAreaLog);

    jButtonConnect.setText("connect");
    jButtonConnect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonConnectActionPerformed(evt);
      }
    });

    jButtonDisconnect.setText("disconnect");
    jButtonDisconnect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonDisconnectActionPerformed(evt);
      }
    });

    jLabel2.setText("Topic:");

    jButtonTopicSubscribe.setText("subscribe");
    jButtonTopicSubscribe.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonTopicSubscribeActionPerformed(evt);
      }
    });

    jButtonTopicUnsubscribe.setText("unsubscribe");
    jButtonTopicUnsubscribe.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonTopicUnsubscribeActionPerformed(evt);
      }
    });

    jButtonTopicDisable.setText("disable");
    jButtonTopicDisable.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonTopicDisableActionPerformed(evt);
      }
    });

    jButtonTopicEnable.setText("enable");
    jButtonTopicEnable.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonTopicEnableActionPerformed(evt);
      }
    });

    jButtonClear.setText("clear");
    jButtonClear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonClearActionPerformed(evt);
      }
    });

    jButtonLoopTest.setText("loop");
    jButtonLoopTest.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonLoopTestActionPerformed(evt);
      }
    });

    jTextFieldLoopTestLoops.setText("1000");

    jTextFieldLoopTestDelay.setText("5");

    jComboBoxQoSSubscribe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QoS_0", "QoS_1", "QoS_2" }));

    jComboBoxQoSLoop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QoS_0", "QoS_1", "QoS_2" }));

    jComboBoxQoSSend.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QoS_0", "QoS_1", "QoS_2" }));

    jTextFieldBytesSize.setText("1024");
    jTextFieldBytesSize.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldBytesSizeActionPerformed(evt);
      }
    });

    jCheckBoxBinary.setText("binary");

    jTextFieldHost.setText("localhost");
    jTextFieldHost.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldHostActionPerformed(evt);
      }
    });

    jTextFieldPort.setText("1883");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCheckBoxBinary)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTextFieldBytesSize, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBoxQoSLoop, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextFieldLoopTestLoops, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(13, 13, 13)
            .addComponent(jTextFieldLoopTestDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonLoopTest))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxQoSSend, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jButtonClear))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPort))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jTextFieldTopic)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBoxQoSSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTopicSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTopicUnsubscribe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTopicDisable))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDisconnect)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonTopicEnable)))
        .addContainerGap())
      .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
              .addComponent(jLabel1)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jTextFieldMessage))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
              .addComponent(jLabel2)
              .addGap(0, 757, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
              .addGap(0, 0, Short.MAX_VALUE)
              .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addContainerGap()))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jButtonConnect)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButtonDisconnect)))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonTopicEnable)
          .addComponent(jButtonTopicDisable)
          .addComponent(jButtonTopicUnsubscribe)
          .addComponent(jButtonTopicSubscribe)
          .addComponent(jTextFieldTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jComboBoxQoSSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(50, 50, 50)
        .addComponent(jComboBoxQoSSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButtonLoopTest)
          .addComponent(jTextFieldLoopTestLoops, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldLoopTestDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jComboBoxQoSLoop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldBytesSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jCheckBoxBinary))
        .addGap(18, 18, 18)
        .addComponent(jButtonClear)
        .addGap(17, 17, 17)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(45, 45, 45))
      .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
          .addGap(58, 58, 58)
          .addComponent(jLabel2)
          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1))
          .addGap(18, 18, 18)
          .addComponent(jButtonSend)
          .addGap(354, 354, 354)))
    );

    jTabbedPane1.addTab("MQTT", jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(15, 15, 15)
        .addComponent(jTabbedPane1)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  public void connectMQTT() throws Exception {
    if (mqttClient.isConnected() == false) {
      mqttClient.connect("spex_" + System.currentTimeMillis(), jTextFieldHost.getText(), Integer.parseInt(jTextFieldPort.getText()), USERNAME, PW, SSL);
      //mqttClient.connect("spex_" + System.currentTimeMillis(), MQTTHOST, (SSL == true ? MQTTPORTTLS : MQTTPORT), SSL);
    }
  }

  public void disconnectMQTT() throws Exception {
    if (mqttClient.isConnected()) {
      mqttClient.disconnect();
    }

  }

  public void sendMessage(String topic, int qos, String msg) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Message m = new Message();
    m.setError(false);
    m.setMsg(msg);
    m.setLooptest(false);
    m.setTimestamp(System.currentTimeMillis());
    var jsonInString = mapper.writeValueAsString(m);
    mqttClient.publish(topic, jsonInString, qos, true);
  }

  public void sendMessageLoop(String topic, int qos, String msg, int counter, byte[] t) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Message m = new Message();
    m.setError(false);
    m.setMsg(msg);
    m.setLooptest(true);
    m.setLoopcounter(counter);
    m.setTimestamp(System.currentTimeMillis());
    m.setPayload(t);
    var jsonInString = mapper.writeValueAsString(m);
    mqttClient.publish(topic, jsonInString, qos, false);
  }

  public static byte[] longToBytes(long l) {
    byte[] result = new byte[8];
    for (int i = 7; i >= 0; i--) {
      result[i] = (byte) (l & 0xFF);
      l >>= 8;
    }
    return result;
  }

  public static long bytesToLong(final byte[] bytes, final int offset) {
    long result = 0;
    for (int i = offset; i < Long.BYTES + offset; i++) {
      result <<= Long.BYTES;
      result |= (bytes[i] & 0xFF);
    }
    return result;
  }

  public void sendMessageBinary(String topic, int qos, byte[] t) throws Exception {
    t[0] = (byte) 0xAC;
    t[1] = (byte) 0xDC;
    byte[] tstamp = TestFrame.longToBytes(System.currentTimeMillis());
    System.arraycopy(tstamp, 0, t, 2, tstamp.length);
    mqttClient.publish(topic, t, qos, false);
  }

  private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
    try {
      int qos = getQoS((String) jComboBoxQoSSend.getSelectedItem());
      sendMessage(jTextFieldTopic.getText(), qos, jTextFieldMessage.getText());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//GEN-LAST:event_jButtonSendActionPerformed

  private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnectActionPerformed
    try {
      connectMQTT();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//GEN-LAST:event_jButtonConnectActionPerformed

  private void jButtonDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisconnectActionPerformed
    try {
      disconnectMQTT();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//GEN-LAST:event_jButtonDisconnectActionPerformed

  private void jButtonTopicSubscribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTopicSubscribeActionPerformed
    int qos = getQoS((String) jComboBoxQoSSubscribe.getSelectedItem());
    //mqttClient.subscribe(jTextFieldTopic.getText(), qos, this.ml);
    mqttClient.subscribe(jTextFieldTopic.getText(), qos, this.ml5);
  }//GEN-LAST:event_jButtonTopicSubscribeActionPerformed

  private void jButtonTopicUnsubscribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTopicUnsubscribeActionPerformed
    mqttClient.unsubscribe(jTextFieldTopic.getText());
  }//GEN-LAST:event_jButtonTopicUnsubscribeActionPerformed

  private void jButtonTopicDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTopicDisableActionPerformed
    mqttClient.setActive(jTextFieldTopic.getText(), false);
  }//GEN-LAST:event_jButtonTopicDisableActionPerformed

  private void jButtonTopicEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTopicEnableActionPerformed
    mqttClient.setActive(jTextFieldTopic.getText(), true);
  }//GEN-LAST:event_jButtonTopicEnableActionPerformed

  private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
    jTextAreaLog.setText("");
  }//GEN-LAST:event_jButtonClearActionPerformed

  private void jButtonLoopTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoopTestActionPerformed
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          int loops = Integer.parseInt(jTextFieldLoopTestLoops.getText());
          int delay = Integer.parseInt(jTextFieldLoopTestDelay.getText());
          String topic = jTextFieldTopic.getText();
          int qos = getQoS((String) jComboBoxQoSLoop.getSelectedItem());
          byte[] t = new byte[Integer.parseInt(jTextFieldBytesSize.getText())];
          boolean binary = jCheckBoxBinary.isSelected();
          for (int k = 0; k < loops; k++) {
            if (binary) {
              sendMessageBinary(topic, qos, t);
            } else {
              sendMessageLoop(topic, qos, ("Looptest " + k), (k + 1), t);
            }
            if (delay > 0) {
              Thread.sleep(delay);
            }
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    t.start();
  }//GEN-LAST:event_jButtonLoopTestActionPerformed

  private void jTextFieldBytesSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBytesSizeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jTextFieldBytesSizeActionPerformed

  private void jTextFieldHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHostActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jTextFieldHostActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TestFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonClear;
  private javax.swing.JButton jButtonConnect;
  private javax.swing.JButton jButtonDisconnect;
  private javax.swing.JButton jButtonLoopTest;
  private javax.swing.JButton jButtonSend;
  private javax.swing.JButton jButtonTopicDisable;
  private javax.swing.JButton jButtonTopicEnable;
  private javax.swing.JButton jButtonTopicSubscribe;
  private javax.swing.JButton jButtonTopicUnsubscribe;
  private javax.swing.JCheckBox jCheckBoxBinary;
  private javax.swing.JComboBox<String> jComboBoxQoSLoop;
  private javax.swing.JComboBox<String> jComboBoxQoSSend;
  private javax.swing.JComboBox<String> jComboBoxQoSSubscribe;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JTextArea jTextAreaLog;
  private javax.swing.JTextField jTextFieldBytesSize;
  private javax.swing.JTextField jTextFieldHost;
  private javax.swing.JTextField jTextFieldLoopTestDelay;
  private javax.swing.JTextField jTextFieldLoopTestLoops;
  private javax.swing.JTextField jTextFieldMessage;
  private javax.swing.JTextField jTextFieldPort;
  private javax.swing.JTextField jTextFieldTopic;
  // End of variables declaration//GEN-END:variables

  @Override
  public void run() {
    boolean loop = true;
    while (loop) {
      try {
        Object rm = (Object) TestFrame.queue.take();
        if (rm instanceof ReceiveMessage) {
          byte[] t = ((ReceiveMessage) rm).getM().getPayload();
          if (t[0] == (byte) 0xac && t[1] == (byte) 0xdc) {
            long elapsedTime = ((ReceiveMessage) rm).getTstamp() - TestFrame.bytesToLong(t, 2);
            System.out.println("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage) rm).getM().isRetained() + " Bytes: " + t.length);
          } else {
            String msg = new String(t);
            ObjectMapper mapper = new ObjectMapper();
            Message m = mapper.readValue(msg, Message.class);
            long elapsedTime = ((ReceiveMessage) rm).getTstamp() - m.getTimestamp();
            System.out.println("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage) rm).getM().isRetained() + " Error: " + m.isError() + " Bytes: " + msg.getBytes().length + " Msg: " + m.getMsg());
            if (m.isLooptest() == false) {
              jTextAreaLog.append("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage) rm).getM().isRetained() + " Error: " + m.isError() + " Bytes: " + msg.getBytes().length + " Msg: " + m.getMsg() + "\n");
            }
          }
        } else if (rm instanceof ReceiveMessage5) {
          byte[] t = ((ReceiveMessage5) rm).getM().getPayloadAsBytes();
          if (t[0] == (byte) 0xac && t[1] == (byte) 0xdc) {
            long elapsedTime = ((ReceiveMessage5) rm).getTstamp() - TestFrame.bytesToLong(t, 2);
            System.out.println("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage5) rm).getM().isRetain() + " Bytes: " + t.length);
          } else {
            String msg = new String(t);
            ObjectMapper mapper = new ObjectMapper();
            Message m = mapper.readValue(msg, Message.class);
            long elapsedTime = ((ReceiveMessage5) rm).getTstamp() - m.getTimestamp();
            System.out.println("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage5) rm).getM().isRetain() + " Error: " + m.isError() + " Bytes: " + msg.getBytes().length + " Msg: " + m.getMsg());
            if (m.isLooptest() == false) {
              jTextAreaLog.append("Elapsed: " + elapsedTime + " Retained: " + ((ReceiveMessage5) rm).getM().isRetain() + " Error: " + m.isError() + " Bytes: " + msg.getBytes().length + " Msg: " + m.getMsg() + "\n");
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  private int getQoS(String t) {
    int v = MyMqttClient.QOS_1;
    switch (t) {
      case "QoS_0":
        v = MyMqttClient.QOS_0;
        break;
      case "QoS_2":
        v = MyMqttClient.QOS_2;
        break;
      default:
        v = MyMqttClient.QOS_1;
        break;
    }

    return v;
  }

}
