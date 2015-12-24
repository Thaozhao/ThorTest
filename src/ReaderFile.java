import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


public class ReaderFile {
    
    
    public static void main(String[] args) throws IOException {
        
        
        BufferedReader reader = new BufferedReader(new FileReader(new File(
                "F:\\log\\MembershipManagementService-prod.log\\MembershipManagementService-prod.log")));
        // "MembershipManagementService-prod.log")));
        String str = null;
        int i = 0;
        ArrayList<LogString> list = new ArrayList<LogString>();
        while ((str = reader.readLine()) != null) {
            // if
            // (str.contains("MessageReceiverExecutor-6(queue@ProcessOrderQueue)-172"))
            // {
            // if (str.contains("new session id ")) {
            // System.out.println(str);
            // }
//            if (str.contains("new cookies ")) {
//                System.out.println(str);
//            }
//            if (str.contains("new session id ")) {
//                System.out.println(str);
//            }
            
            if (str.contains("sync.impl.MessageReceiverExecutor-5(queue@ProcessOrderQueue)-3129")) {
//                if(str.contains("prod-member-01w.aw.active.tan")){
                    System.out.println(str);
                    list.add(new LogString(str));
                    i++;
//                }
//              
          }
            
            
            
        }
        
        
        Collections.sort(list);
        
        System.out.println("---------------------------");
        Iterator<LogString> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }
}

class LogString implements Comparable{
    
    private String time;
    private String str;
    //prod-member-03w.aw.active.tan: 2015-11-01 20:46:33,973 INFO  [DeterminePerson][async.impl.MessageReceiverExecutor-5(queue@ProcessOrderQueue)-3129] [2369736298919250944] findPerson with DOB
    public LogString(String str){
        this.str= str;
        this.time= str.split(" ")[2];
    }

    public int compareTo(Object o) {
        return time.compareTo(((LogString)o).time);
    }
    
    public String toString(){
        return str;
        
    }
    
}

