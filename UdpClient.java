import java.net.*;

public class UdpClient {
    DatagramSocket socket;
    InetAddress address;

    byte[] buffer = new byte[1024];

    public UdpClient() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String encrypt(String message) throws Exception {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 8888);
        socket.send(packet);
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);
        return new String(response.getData(), 0, response.getLength());
    }

    public String decrypt(String message) throws Exception {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 8889);
        socket.send(packet);
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);
        return new String(response.getData(), 0, response.getLength());
    }

    public void close() {
        socket.close();
    }
    public static void main(String[] args) throws Exception {
        UdpClient client = new UdpClient();
        String response = client.encrypt("Hallo Moin");
        System.out.println(response);
        String back = client.decrypt(response);
        System.out.println(back);
        client.close();
    }
}
