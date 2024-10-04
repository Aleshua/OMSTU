public class MyThread extends Thread {
    private boolean doWork = true;

    public void Run() {
        while (doWork) {
            System.out.println("Hello world");
            try{
                Thread.currentThread().sleep(1000);
            } catch () {

            }
        }
    }
}
