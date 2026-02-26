package multithreading.deadlock.deadlock_mimic;

public class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+" has pen and, "+this.getClass()+" is trying to user paper: "+paper);
            paper.finishWriting();
    }

    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+" finished writing using pen: "+ this.getClass());
    }

}
