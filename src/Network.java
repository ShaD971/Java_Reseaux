import java.net.*;
import java.util.Enumeration;

/**
 * Created by shad on 03/04/16.
 */
public class Network {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces();

            while(list.hasMoreElements()){

                NetworkInterface ni = list.nextElement();
                Enumeration<InetAddress> listAddress = ni.getInetAddresses();

                while(listAddress.hasMoreElements()){
                    InetAddress address = listAddress.nextElement();
                    showInformations(address);
                }

            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
        //info Ip DNS
        try {
            //Nous appelons une méthode statique de cet objet pour en récupérer une instance
            InetAddress address = InetAddress.getByName("openclassrooms.com");
            System.out.println("L'adresse IP de " + address.getHostName() + " est : " + address.getHostAddress());


            //Certains sites Internet peuvent avoir plusieurs adresses IP
            //C'est le cas de Google
            InetAddress[] addresses = InetAddress.getAllByName("google.fr");
            System.out.println("\nToutes les adresses IP de google.fr : ");
            for(InetAddress ad : addresses)
                System.out.println(" - " + ad.getHostAddress());

            addresses = InetAddress.getAllByName("google.com");
            System.out.println("\nToutes les adresses IP de google.com : ");
            for(InetAddress ad : addresses)
                System.out.println(" - " + ad.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //url
        System.out.println("\n infos a partir de l'url");
        try {

            URL url = new URL("http://www.jeuxvideo.com");

            System.out.println("\nAuthority : " + url.getAuthority());
            System.out.println("Default port : " + url.getDefaultPort());
            System.out.println("Host : " + url.getHost());
            System.out.println("Port : " + url.getPort());
            System.out.println("Protocol : " + url.getProtocol());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void showInformations(InetAddress address){
        System.out.println("-----------------------------------------------");
        System.out.println("Nom  : " + address.getHostName());
        System.out.println("Adresse : " + address.getHostAddress());
        System.out.println("Nom canonique : " + address.getCanonicalHostName());
    }
}
