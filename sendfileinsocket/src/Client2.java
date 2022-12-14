import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//Client2 to read from file input2.txt and print to Server2.

public class Client2 {
  public static void main(String[] args){
    int PORT = 12000;

    try{
      Socket cs = new Socket("localhost",PORT);

      DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
     
      //Read a file
      FileReader fr = new FileReader("src/input2.txt");
      BufferedReader bfr = new BufferedReader(fr);
      String line;
      while(null != (line = bfr.readLine())){
        dos.writeUTF(line);
        dos.flush();
      }
      //If not using EOF identifier, can use EOFException on Server2 side.
      // //Send an EOF identifier to server
      // dos.writeUTF("EOF");
      // dos.flush();

      bfr.close(); //close the file
      cs.close();
      
    } catch (UnknownHostException e){
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
