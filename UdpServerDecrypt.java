import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServerDecrypt extends Thread{
    DatagramSocket socket;
    byte[] buffer = new byte[1024];

    public UdpServerDecrypt() throws SocketException {
        socket = new DatagramSocket(8889);
    }

    @Override
    public void run() {
        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());
            Krypto krypto = new Krypto();
            //krypto.caesar(received, -5);
            response.setData(krypto.caesar(received, -5).getBytes());
            try {
                socket.send(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            socket.close();
        }
    }

    public static void main(String[] args) throws SocketException {
        UdpServerDecrypt server = new UdpServerDecrypt();
        server.start();
    }
}
