package multithreading.reentrant_lock_test;

public class Test {
    public static void main(String[] args) {

        BankAccount myAccount=new BankAccount();

        Runnable task= new Runnable() {
            @Override
            public void run(){
                myAccount.withdraw(50);
            }
        };
        Thread t1=new Thread(task,"Thread-1");
        Thread t2=new Thread(task,"Thread-2");
        Thread t3=new Thread(task,"Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
