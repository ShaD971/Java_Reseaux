import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by shad on 23/04/16.
 */
public class SocketClient {
    public static void main(String[] args){
        try {

            Socket sock = new Socket("fr.wikipedia.org", 80);


           // les \r\n sont OBLIGATOIRES
            String request = "GET /wiki/Digital_Learning HTTP/1.1\r\n";
            request += "Host: fr.wikipedia.org\r\n";
            request += "\r\n";

            // flux en écriture
            BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());

            // requête
            bos.write(request.getBytes());
            bos.flush();

            // réponse du serveur
            BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());


            String content = "";
            int stream;
            byte[] b = new byte[1024];
            while((stream = bis.read(b)) != -1){
                content += new String(b, 0, stream);
            }

            // affiche la page
            Browser browser = new Browser("fr.wikipedia.org", content);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
