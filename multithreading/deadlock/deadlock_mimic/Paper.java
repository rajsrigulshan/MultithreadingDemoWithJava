package multithreading.deadlock.deadlock_mimic;

public class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName()+" has paper and, "+this.getClass()+" is trying to use pen: "+pen);
        pen.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ "finished writing using paper "+this.getClass());
    }
}
