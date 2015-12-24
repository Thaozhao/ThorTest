package datafix;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 1.通过DataFix(child)SQL.txt来查询重名的人（主要获得生日）
 * 2.根据查询的结果，查询person表，获取对应的enterprise_person_id
 * @author tzhao
 *
 */
public class DataFixStep1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemResourceAsStream("DataFix(child)Result.txt")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
           String[] cols= line.split("\t");
           System.out.print("SELECT birth_dt,enterprise_person_id,first_name,last_name,created_dt,id FROM user_service.people.people with(nolock) where first_name = '");
           System.out.print(cols[1]+"' and last_name='");
           System.out.print(cols[2]+"' and birth_dt = '");
           System.out.print(cols[3]+"' and agency_id = 'E6DFD223-E5ED-4030-B0E4-06EA16E95512'");
           System.out.println();
           System.out.println("UNION");
        }
        reader.close();

    }
}
