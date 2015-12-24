import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParentTierShower {
    class Row {
        private String id;

        public String getId() {
            return id;
        }

        public String getParentTierId() {
            return parentTierId;
        }

        private String parentTierId;

        public Row(String id, String parentTierId) {
            this.id = id;
            this.parentTierId = parentTierId;
        }
    }
    
    public void printChild(Row row ,List<Row> list ,int num){
        String id = row.getId();
        String pid = row.getParentTierId();
        
        int i = 0;
        while(i<list.size()){
            Row rowChild = list.get(i);
            if(id.equals(rowChild.getParentTierId())){
                int j = 0;
                while(j<num+1){
                    System.out.print("\t");
                    j++;
                }
                
                System.out.print(rowChild.getId());
                printChild(rowChild,list,num+1);
            }
           
            i++;
            
        }
        System.out.println();
        
    }
    
    

    public static void main(String[] args) throws IOException {
        ParentTierShower p = new ParentTierShower();
        BufferedReader reader = new BufferedReader(new FileReader(new File("s.txt")));
        String str = null;
        ArrayList<Row> list = new ArrayList<Row>();
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
            String[] s = str.split("\t");
            list.add(p.new Row(s[0], s[1]));
        }
        int i = 0;
        System.out.println("-------------------------------");
        while(i< list.size()){
            
            Row row = list.get(i);
            if(row.getParentTierId().equals("NULL")){
                System.out.print(row.getId());
                p.printChild(row,list,0);
            }
            i++;
            
        }

    }
}
