package projects.FailDetect;

public class Counts {
    public static int rounds = 0;
    public static int received = 0;
    public static int sent = 0;
    public static int faults_node = 0;
    public static int restore_node = 0;

    public synchronized static void countRound(){
        rounds++;
    }
    public synchronized static void countReceived(){
        received++;
    }
    public synchronized static void countSent(){
        sent++;
    }
    public synchronized static void countFaults(){
        faults_node++;
    }
    public synchronized static void countRestore(){
        restore_node++;
    }
}
