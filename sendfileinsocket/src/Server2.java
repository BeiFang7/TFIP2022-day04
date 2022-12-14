import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
  public static void main(String[] args){
    int PORT = 12000;

    try{
      ServerSocket server = new ServerSocket(PORT);
      Socket sc = server.accept();

      DataInputStream dis = new DataInputStream(new BufferedInputStream(sc.getInputStream()));
      //to read a file need to read the end of the file EOF, not like previous time end with "close"
      String line = dis.readUTF();
      while (!line.equalsIgnoreCase("EOF") && (line != null)){
        System.out.println("Got: -> " + line);
        // //If not using EOF identifier, then can use catch EOFException error
  
        try{
          line = dis.readUTF();
        } catch(EOFException e){ //if read file is EOF
          System.out.println("REACHED END of FILE");
          break;
        }
        // //read the next line
        // line = dis.readUTF();
      }
      sc.close();

    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
