import java.io.*;

public class ObjectOutputStreamExample {
    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.name = "sanabil";
        person.age = 90;
        File f = new File("person.dat");

        FileOutputStream fileOutputStream = new FileOutputStream(f);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("saved");
    }
}
