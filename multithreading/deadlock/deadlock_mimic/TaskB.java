package multithreading.deadlock.deadlock_mimic;

public class TaskB implements Runnable {    
    private Paper paper;
    private Pen pen;

    public TaskB(Paper paper,Pen pen){
        this.paper=paper;
        this.pen=pen;
    }

    @Override
    public void run(){
         /*
        This will lead to a deadlock as TaskB has already acquired the lock of paper and,
        passed the refernce of pen and upon that refernce it will try to acquire,
        the lock on pen to run the finishedWriting method of the pen but it  mostly ,
        Won't get because that lock is acquired by the TaskA, Since both methods are synchronised
        So complete Object is locked.
        As I have shared same pen and paper objct to both TaskA & TaskB So, This will lead to Deadlock.
        */

        // Note: To overcome deadlock suggestion are described in class TaskA.
        paper.writeWithPaperAndPen(pen);
        
    }
}
