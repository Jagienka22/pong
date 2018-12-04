package pong;

public class MyThread extends Thread {
    private Controller con;

    MyThread(Controller con) {
        this.con = con;
    }

    public void run() {
        while (true) {
            con.draw();
            try {
                Thread.sleep(100);//lub 500
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
