public class SimpleThread extends Thread{
    
    //public SimpleThread(){}

    @Override
    public void run(){
      System.out.println("Thread started execution + " + Thread.currentThread().getName());
      try{
        Thread.sleep(4000); //wait for 4000 millis
      }catch (InterruptedException e){

      }//wait for some time
      System.out.println("Thread stop execution + " + Thread.currentThread().getName());
    }
}
