package multithreading.synchronized_test;

public class MyClass extends Thread {
    private Count count;

    public MyClass(Count count){
        this.count=count;
    }

    @Override
   public  void run(){
          for(int i=0;i<1000;i++){
            count.increament();
        }
   }
   
}
