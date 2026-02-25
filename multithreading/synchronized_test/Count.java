package multithreading.synchronized_test;

public class Count {
    private Long cnt=0L;

    public synchronized void increament(){
        cnt++;
    }
    public Long getCount(){
        return cnt;
    } 

}
