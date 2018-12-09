package pong;

public class MyThread extends Thread {
    private Controller con;

    MyThread(Controller con) {
        this.con = con;
    }

    public void run() {
        while (true) {
            con.draw();
            con.iterate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
