import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    private File file = new File("/Users/jbon/users.csv");

    public User find(String empId) {
        String record;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((record = br.readLine()) != null) {
                if (employeeIdMatches(record, empId)) {
                    return convertToDto(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean employeeIdMatches(String record, String empId) {
        // TODO: consider String#split()
        String parsedEmpId = record.substring(0, record.indexOf(','));
        return empId.equals(parsedEmpId);
    }

    private User convertToDto(String record) {
        User dto = new User();
        int commaIndex = record.indexOf(',');
        String empId = record.substring(0, commaIndex);
        record = record.substring(commaIndex + 1, record.length());

        commaIndex = record.indexOf(',');
        String first = record.substring(0, commaIndex);
        record = record.substring(commaIndex + 1, record.length());

        commaIndex = record.indexOf(',');
        String last = record.substring(0, commaIndex);
        record = record.substring(commaIndex + 1, record.length());

        String groups = record.substring(1, record.length() - 1);

        String[] groupArray = groups.split(",");

        dto.setEmpId(empId);
        dto.setFirst(first);
        dto.setLast(last);
        dto.setGroups(Arrays.asList(groupArray));
        return dto;
    }

    public static void main(String[] args) {
        User found = new CsvReader().find("123");
        System.out.println(found);
    }

}

class User {
    String first;
    String last;
    String empId;
    List<String> groups;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", empId='" + empId + '\'' +
                ", groups=" + groups +
                '}';
    }
}
