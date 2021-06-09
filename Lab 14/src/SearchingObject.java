import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SearchingObject {
    public void searchObject(String name) throws IOException {
        Person personRead;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\SANABIL\\IdeaProjects\\Lab 14\\person.dat");
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
            personRead = (Person) objectInputStream.readObject();
            if (personRead.name.equals(name)){
                System.out.println(personRead.name);
                System.out.println(personRead.age);
            }
            else {
                System.out.println("Name not found");
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SearchingObject searchingObject= new SearchingObject();
        searchingObject.searchObject("sail");
    }
}
