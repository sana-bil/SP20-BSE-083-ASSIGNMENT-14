import java.io.*;
import java.io.ObjectInputStream;

public class Book implements Serializable {
    public String bookName;
    public String publisher;
    public Person author;

    public Book(String bookName, String publisher, Person author) throws FileNotFoundException, IOException {
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
    }

    public void display() {
        try {

                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\SANABIL\\IdeaProjects\\Lab 14\\BookStore.txt");
                java.io.ObjectInputStream objectInputStream = new java.io.ObjectInputStream(fileInputStream);
                for (int i=1;i<6;i++){
                    Book obj = (Book) objectInputStream.readObject();
                    System.out.println("      Book " + i);
                    System.out.println("Name of the Book: " + obj.bookName);
                    System.out.println("Publisher of the Book: " + obj.publisher);
                    System.out.println("Author Name: " + obj.author.name);
                    System.out.println("Author's age: " + obj.author.age);
                    System.out.println();

                }

                fileInputStream.close();
                objectInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchBook(String bookName) {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\SANABIL\\IdeaProjects\\Lab 14\\BookStore.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println("Finding "+ bookName);
            for (int i=1; i<6; i++){
                Book book = (Book) objectInputStream.readObject();

                if (book.bookName.equals(bookName)) {

                    System.out.println(bookName + " found");
                    System.out.println("Book name: " + book.bookName);
                    System.out.println("Publisher name: " + book.publisher);
                    System.out.println("Author name: " + book.author.name);
                    System.out.println("Author age: " + book.author.age);
                    break;
                }
                continue;
            }
            System.out.println("not found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Person person = new Person();
        person.name = "Lucy mahone";
        person.age = 59;
        Book book = new Book("Anne with an E", "Belleck john", person);
        Person person1 = new Person();
        person1.name = "J.K Rowling";
        person1.age = 76;
        Book book1 = new Book("Harray Potter", "Micheal Schofield", person1);
        Person person2 = new Person();
        person2.name = "Dennis Lehane";
        person2.age = 29;
        Book book2 = new Book("Shutter Island", "Millie Bobby Brown", person2);
        Person person3 = new Person();
        person3.name = "Wintson Groom";
        person3.age = 77;
        Book book3 = new Book("Forrest Grump", "Erica Sinclair", person3);
        Person person4 = new Person();
        person4.name = "Jon Krakauer";
        person4.age = 45;
        Book book4 = new Book("Into the wild", "Vincenzo Cassano", person4);


    File file = new File("BookStore.txt");
        try

    {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(book);
        objectOutputStream.writeObject(book1);
        objectOutputStream.writeObject(book2);
        objectOutputStream.writeObject(book3);
        objectOutputStream.writeObject(book4);
        System.out.println("Books saved");
        objectOutputStream.close();
        fileOutputStream.close();

    } catch(
    FileNotFoundException e)

    {
        e.printStackTrace();
    } catch(IOException e)

    {
        e.printStackTrace();
    }
        System.out.println();
        System.out.println("                      Displaying books            ");
        System.out.println();

        book.display();
        book.searchBook("A E");
}
    }
