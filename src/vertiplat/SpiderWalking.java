package vertiplat;

/**
 *
 * @author stefan
 */
public class SpiderWalking implements Runnable{
    private Thread t;
    private Spider spider;
    
    public SpiderWalking(Spider spider) {
        t = new Thread(this, "SpiderWalking");
        this.spider = spider;
        t.start();
    }
    
    @Override
    public void run() {
        try {
            while(spider.getHP() > 0) {
                spider.walk();
                t.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Thread " + t.toString() + " interrupted.");
        }
    }
}
