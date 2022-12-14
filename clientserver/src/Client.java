import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
  public static void main(String[] args){
    int PORT = 12345;
    try{
      
      Socket cs = new Socket("localhost", PORT);

      //Get the I/O stream
      OutputStream os = cs.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      DataOutputStream dos = new DataOutputStream(bos);

      
      // dos.writeUTF("hello");
      // dos.writeUTF("world");
      // dos.flush();
      // System.out.println("MESSAGE SENT TO SERVER");
      // cs.close();

      //make writing a loop
      //Create a scanner to take in user input
      Scanner inputSc = new Scanner(System.in);
      String line;
      //as long as have something in the terminal
      while ((line = inputSc.nextLine()) != null){
        if(line.equalsIgnoreCase("close")){ //if type close, will exit the loop.
          System.out.println("Exit from shell");
          dos.writeUTF("close");
          dos.flush();
          break;
        }
        dos.writeUTF(line);
        dos.flush();
        System.out.println("MSG sent to client: " +line);
      }
      cs.close(); //closing the socket from the client.
      inputSc.close(); //need to close the Scanner too.

    } catch (UnknownHostException e){
      System.out.println("Unable to reach the HOST");
    } catch (IOException e){
      System.out.println("IO Error");
    }
    
  }
}

//Client sends a message and Server receives and close the program
//Write 1 time from client --> should have Read 1 time from Server
//If write 1 time from client --> read 2 times from server, the read 2nd time will be blocked. Need to write 1 more time for server to read the second time.
//If write 2 times from client --> read 1 time from server, it will be waiting for server to read the 2nd time in order to display the 2nd message from client.
// can loop the write and loop the read to do continuously.