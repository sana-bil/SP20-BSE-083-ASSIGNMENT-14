import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ObjectInputStream {

    public static void main(String[] args) {
        Person person;
        try {
            FileInputStream fileInputStream= new FileInputStream("C:\\Users\\SANABIL\\IdeaProjects\\Lab 14\\person.dat");
            java.io.ObjectInputStream objectInputStream= new java.io.ObjectInputStream(fileInputStream);
            person= (Person) objectInputStream.readObject();
            System.out.println(person.name);
            System.out.println(person.age);

        }
        catch (FileNotFoundException ex){
            System.out.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


