package datafix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.util.List;

public class DataFixStep2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemResourceAsStream("Datefix(step2).txt")));
        String line = null;
        Map<String, List<Temp>> tempContain = new HashMap<String, List<Temp>>();
        while ((line = reader.readLine()) != null) {
            Temp temp = new Temp().getInstance(line);
            List<Temp> tempList = tempContain.get(temp.getBirthDay() + temp.getFirstName().toUpperCase()
                    + temp.getLastName().toUpperCase());
            if (tempList == null) {
                tempList = new ArrayList<Temp>();
            }
            tempList.add(temp);
            tempContain.put(temp.getBirthDay() + temp.getFirstName().toUpperCase() + temp.getLastName().toUpperCase(),
                    tempList);
        }
        reader.close();

        Set<Entry<String, List<Temp>>> tempSet = tempContain.entrySet();
        Iterator<Entry<String, List<Temp>>> it = tempSet.iterator();
        while (it.hasNext()) {
            Entry<String, List<Temp>> entry = it.next();
            List<Temp> list = entry.getValue();
            Collections.sort(list);
            System.out.print("update membership_service.dbo.memberships set enterprise_person_id='");
            System.out.print(list.get(0).getEnterprisePersonId() + "',person_id_ref='"+list.get(0).getPersonId());
            System.out.print("' where enterprise_person_id in (");
            for (int i = 1; i < list.size(); i++) {
                System.out.print( "'"+list.get(i).getEnterprisePersonId() + "'");
                if(i<list.size()-1){
                    System.out.print( ", ");
                }
            }
            System.out.println(");");
            
            System.out.print("update membership_service.dbo.add_members set enterprise_person_id='");
            System.out.print(list.get(0).getEnterprisePersonId() + "' where enterprise_person_id in (");
            for (int i = 1; i < list.size(); i++) {
                System.out.print( "'"+list.get(i).getEnterprisePersonId() + "'");
                if(i<list.size()-1){
                    System.out.print( ", ");
                }
            }
            System.out.println(");");
            
            System.out.print("update membership_service.dbo.membership_group_members set enterprise_person_id='");
            System.out.print(list.get(0).getEnterprisePersonId() + "' where enterprise_person_id in (");
            for (int i = 1; i < list.size(); i++) {
                System.out.print( "'"+list.get(i).getEnterprisePersonId() + "'");
                if(i<list.size()-1){
                    System.out.print( ", ");
                }
            }
            System.out.println(");");
            
        }
        
        System.out.print("select * from membership_service.dbo.memberships where enterprise_person_id in (");
        it = tempSet.iterator();
        while (it.hasNext()) {
            Entry<String, List<Temp>> entry = it.next();
            List<Temp> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                System.out.print( "'"+list.get(i).getEnterprisePersonId() + "'");
                
                    System.out.print( ", ");
                
            }
        }
        System.out.println(");");

    }
}

class Temp implements Comparable<Temp> {
    private String birthDay;
    private String enterprisePersonId;
    private String firstName;
    private String lastName;
    private String createDate;
    private String personId;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEnterprisePersonId() {
        return enterprisePersonId;
    }

    public void setEnterprisePersonId(String enterprisePersonId) {
        this.enterprisePersonId = enterprisePersonId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Temp))
            return false;
        Temp temp = (Temp) obj;
        if (this.birthDay.equals(temp.getBirthDay())
                && this.lastName.toUpperCase().equals(temp.getLastName().toUpperCase())
                && this.firstName.toUpperCase().equals(temp.getFirstName().toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(Temp obj) {
//        System.out.println(this.createDate);
//        System.out.println(obj.createDate);
//        System.out.println(this.createDate.compareTo(obj.getCreateDate()));
        return this.createDate.compareTo(obj.getCreateDate());
    }

    @Override
    public int hashCode() {
        return (this.birthDay + this.firstName + this.lastName).hashCode();
    }

    public Temp getInstance(String line) {
        String[] cols = line.split("\t");
        this.setBirthDay(cols[0]);
        this.setEnterprisePersonId(cols[1]);
        this.setFirstName(cols[2]);
        this.setLastName(cols[3]);
        this.setCreateDate(cols[4]);
        this.setPersonId(cols[5]);
        return this;

    }
}
