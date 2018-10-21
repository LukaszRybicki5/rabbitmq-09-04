package sender.programController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import receiver.messageObject.MessageObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageObjectSender {
    public void sendingMessageObject(MessageObject messageObject) throws IOException, TimeoutException{

        String EXCHANGE_NAME = "TopicDurable";
        String QUEUE_NAME = "DummyLoad";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String ROUTING_KEY = messageObject.getFileName();
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out, messageObject);

        channel.basicPublish("", QUEUE_NAME, null, out.toByteArray());

        System.out.println("Wysłano plik JSON!\nRouting key to: " + ROUTING_KEY.toString());

        channel.close();
        connection.close();
    }
}
