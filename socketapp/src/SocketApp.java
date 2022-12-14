import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketApp {
  public static void main(String[] args){ //String[] args must be in a main to be a valid main in order to run. It takes whatever we take, and split whatever you type via space

    String usage = """
    Usage: Server 
    <program> <server> <port> <cookie-file.txt>

    Usage: Client
    <program> <client> <host><port>
    """; //create a multi line string

    if((args.length==0)){
      System.out.println("Incorrect Inputs. Please check the following usage.");
      System.out.println(usage);
      return;
    }
    
    
    
    String type = args[0];
    if(type.equalsIgnoreCase("server")){
      int port = Integer.parseInt(args[1]); //int port = args[1] --> args is a String, cannot assign to an integer
      String fileName = args[2];
      StartServer(port, fileName);
    } /*else if (type.equalsIgnoreCase("multiserver")){ //add a multi-server option
      StartMultiServer(port,fileName);
    }*/
    else if(type.equalsIgnoreCase("client")){
      String hostName = args[1];
      int port = Integer.parseInt(args[2]);
      StartClient(hostName, port);
    }else {
      System.out.println("Incorrect Argenets!!");
    }
    
  }

  public static void StartServer(int port, String filename){
    ServerSocket server;
    try{
      server = new ServerSocket(port);
      Socket socket = server.accept();

      //In (get input from Client)
      DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

      //Out (send msg to Client)
      DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
      
      while(true){
        String fromClient = dis.readUTF();

        if(fromClient.equalsIgnoreCase("exit")){
          System.out.println("Exiting");
          break;
        }
        System.out.println("LOG: msg from client: " + fromClient);
        if(fromClient.equalsIgnoreCase("get-cookie")){
          // //Send a random cookie from the file
          dos.writeUTF("Dummy cookie");
          dos.flush();
          //Implement this class
          //doc.writeUTF(new CookieFile().getRandomCookie())
          //dos.writeUTF(CookieFile().getRandomCookie());
        } else{
          //Send a msg, "invalid command from server"
          dos.writeUTF("From Server: Invalid Command");
          dos.flush();
        }
      }
      socket.close();
      
    } catch (IOException e){
      e.printStackTrace();
    }

  }

  public static void StartClient(String host,int port){
    try{
      Socket socket = new Socket (host,port);
      //In (get input from Server)
      DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

      //Out (send msg to Server)
      DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

      Scanner sc = new Scanner(System.in);
      boolean stop = false;

      while (!stop){
        String line = sc.nextLine();
        if(line.equalsIgnoreCase("exit")){
          dos.writeUTF("exit");
          stop = true;
          break;

        }
        //pass whatever we make to 
        dos.writeUTF(line);
        dos.flush();

        /* 
        if (line.equalsIgnoreCase("get-cookie")){
          //Send a request to server for a cookie
          dos.writeUTF("get-cookie");
          dos.flush();
        } else{
          System.out.println("Invalid Command: " + line);
        }*/

        
        String fromServer = dis.readUTF();
        System.out.println(("Response from server! "+fromServer));

      }

    } catch (UnknownHostException e){
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    }
  
    /*public static String() CookieFile(){
      String readFile = "src/CookieFile.txt";

    }*/

  }
}

