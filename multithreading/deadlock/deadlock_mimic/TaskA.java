package multithreading.deadlock.deadlock_mimic;

public class TaskA implements Runnable{
    private Paper paper;
    private Pen pen;

    public TaskA(Paper paper,Pen pen){
        this.paper=paper;
        this.pen=pen;
    }

    @Override
    public void run() {
        /*
        This will lead to a deadlock as TaskA has already acquired the lock of pen and,
        passed the refernce of paper and upon that refernce it will try to acquire,
        the lock on paper to run the finishedWriting method of the paper but it mostly ,
        Won't get because that lock is acquired by the TaskB, Since both methods are synchronised,
        So complete Object is locked.
        As I have shared same pen and paper objct to both TaskA & TaskB So, This will lead to Deadlock.
        */

        // pen.writeWithPenAndPaper(paper);


        /*
        To Avoid Deadlock 
        1. Use Reentrant Locks
        2. Lock should apply in the same direction either pen->paper or paper->pen
           So, To achieve this I will just try to acquire the paper lock first in TaskA,
           Then I will try to accquire The Lock on pen & then calling the method of pen which
           will call the finishWriting method of Paper and As I aready have lock on paper so 
           deadlock wont happen. 

        
        3.
            synchronized(paper){
                synchronized(pen){
                    pen.writeWithPenAndPaper(paper);
              }
            }
            Acquire the lock  in same fashion on both Tasks ,
            Then dedlock won't happen.
        
        */
        //  I have applied method 2.
        
        synchronized(paper){
            pen.writeWithPenAndPaper(paper);
        }
    }
}
