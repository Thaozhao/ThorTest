import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Comp {
    public static void main(String[] args) throws IOException {
//        compAccount();
        createBackUpSQL();
    }

    public static void createBackUpSQL() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(new File("realAccount.txt")));
        String str = null;
        while ((str = reader.readLine()) != null) {
            String[] s=str.split("\t");
//            System.out.print(s[2]);
//            System.out.print("    ");
//            System.out.println(s[3]);
            System.out.println("update user_service.account.accounts set password = '"+s[3]+"' where username = '"+s[2]+"';");
        }
    }


    private static void compAccount() throws FileNotFoundException, IOException {
        Comp p = new Comp();
        BufferedReader reader = new BufferedReader(new FileReader(new File("Account.txt")));
        String str = null;
        ArrayList<Row> list = new ArrayList<Row>();
        ArrayList<String> firstlist = new ArrayList<String>();
        ArrayList<String> secondlist = new ArrayList<String>();
        while ((str = reader.readLine()) != null) {
//            System.out.println(str);
            list.add(p.new Row(str.trim().toLowerCase()));
            if(firstlist.contains(str.trim().toLowerCase())){
                secondlist.add(str.trim().toLowerCase());
            }else{
                firstlist.add(str.trim().toLowerCase());
            };
        }
        
        System.out.println(secondlist);

        reader = new BufferedReader(new FileReader(new File("EAccount.txt")));
        str = null;
        while ((str = reader.readLine()) != null) {
            Iterator<Row> it = list.iterator();
            while (it.hasNext()) {
                Row email = it.next();
                if (email.getEmail().equals(str.trim().toLowerCase())) {
                    email.setExist(true);
                }
            }
        }
        
        System.out.println("---------------------------------------------------------------------------------------------------");
        Iterator<Row> it = list.iterator();
        while (it.hasNext()) {
            Row email = it.next();
            System.out.println(email.getEmail()+"\t"+email.isExist());
        }
    }
    
    
    

    class Row {
        private String email;
        private boolean exist;

        public Row(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }
    }
}
