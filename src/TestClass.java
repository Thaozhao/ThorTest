import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestClass {
    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(20);
        pool.execute(new Runnable(){

            public void run() {
                int i = 0;
                while (true) {
                    if (i<100) {
                        System.out.println("121"+Thread.currentThread().getName());
                    }else{
                        break;
                    }
                }
                
            }
            
        });
        
    }
}
