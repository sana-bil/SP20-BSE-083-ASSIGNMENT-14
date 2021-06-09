import java.io.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements Serializable {
    public String accountNumber;
    public String accountTitle;
    public String accountBalance;
    static int currentAccountIndex = 0;
    //constructors
    public Account(String accountNumber, String accountTitle, String accountBalance)
    {
        this.accountNumber = accountNumber;
        this.accountTitle = accountTitle;
        this.accountBalance = accountBalance;
    }
    //setters
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    public void setAccountTitle(String accountTitle)
    {
        this.accountTitle = accountTitle;
    }
    public void setAccountBalance(String accountBalance)
    {
        this.accountBalance = accountBalance;
    }
    //getters
    public String getAccountNumber()
    {
        return accountNumber;
    }
    public String getAccountTitle()
    {
        return accountTitle;
    }
    public String getAccountBalance()
    {
        return accountBalance;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account[] accounts = new Account[]
                {
                        new Account("1","Saira","1500"),
                        new Account("2","Samra","2400"),
                        new Account("3","Zainab","3100"),
                        new Account("4","Zara","4050"),
                        new Account("5","Eman","5500"),
                        new Account("6","Anoosha","6700"),
                        new Account("7","Sabrina","7900"),
                        new Account("8","Neha","8100"),
                        new Account("9","Alisha","3000"),
                        new Account("10","Maryam","95000"), };
        ArrayList<Account> accountsList = new ArrayList<Account>();
        for(int i=0; i < accounts.length; i++)
        {
            accountsList.add(accounts[i]);
        }
        writeObjectToFile(accountsList);
        System.out.println("The Banking system");
        while(true)
        {
            int opt;
            System.out.println("Enter 1 to deposit.\nEnter 2 to withdraw.\nEnter 3 to transfer.\nEnter 4 to check balance.\nEnter 0 to exit.");
                    System.out.println("Enter your choice");
            opt = input.nextInt();
            switch(opt)
            {
                case 1:
                {
                    input.nextLine();
                    System.out.println("Enter the account no: ");
                    String account = input.nextLine();
                    System.out.println("Enter the amount you want to deposit: ");
                    String amount = input.nextLine();
                    deposit(account, amount);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter account no: ");
                    String account = input.nextLine();
                    System.out.println("Enter the amount you want to withdraw: ");
                    String amount = input.nextLine();
                    withdraw(account, amount);
                    break;
                }
                case 3:
                {
                    System.out.println("Enter account no of sender: ");
                    String account = input.nextLine();
                    System.out.println("Enter account no of receiver: ");
                    String account1 = input.nextLine();
                    System.out.println("Enter the amount you want to transfer: ");
                    String amount = input.nextLine();
                    transfer(account, account1, amount);
                    break;
                }
                case 4:
                {
                    System.out.println("Enter account no to check balance: ");
                    String balance = input.nextLine();
                    balanceInquiry(balance);
                    break;
                }
            }
            if(opt == 0)
            {
                System.out.println("Exiting");
                break;
            }
        }
    }
    public static void writeObjectToFile(ArrayList<Account> accounts)
    {
        try {
            File f = new File("Account.txt");

            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accounts);
            objectOutputStream.close();
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static ArrayList<Account> readObjectFromFile()
    {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream("C:\\Users\\SANABIL\\IdeaProjects\\Lab 14\\Account.txt");
            java.io.ObjectInputStream objectInputStream= new java.io.ObjectInputStream(fileInputStream);

            accounts = (ArrayList<Account>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return accounts;
    }
    public static ArrayList<Account> getAccountIndex(String accountNumber)
    {
        ArrayList<Account> accounts = readObjectFromFile();
        for(int i=0; i < accounts.size(); i++)
        {
            Account temp = accounts.get(i);
            if(temp.getAccountNumber().equals(accountNumber))
            {
                currentAccountIndex = i;
                break;
            }
        }
        return accounts;
    }
    public static void balanceInquiry(String accountNumber)
    {
        ArrayList<Account> accounts = getAccountIndex(accountNumber);
        System.out.println("Account Number: " +
                accounts.get(currentAccountIndex).getAccountNumber());
        System.out.println("Account Title: " +
                accounts.get(currentAccountIndex).getAccountTitle());
        System.out.println("Account Balance: " +
                accounts.get(currentAccountIndex).getAccountBalance());
    }
    public static void withdraw(String accountNumber, String amount)
    {
        ArrayList<Account> accounts = getAccountIndex(accountNumber);
        Account account = accounts.get(currentAccountIndex);
        double balance = Double.parseDouble(account.getAccountBalance());
        double amount_d = Double.parseDouble(amount);
        if(amount_d > balance)
        {
            System.out.println("Transaction cannot be completed, Your balance is not enough!!!");
            return;
        }
        balance = balance - amount_d;
        account.setAccountBalance(String.valueOf(balance));
        System.out.println("Amount: "+ amount + " is being withdrawn from your account.");
                System.out.println("Your new balance is: "+ balance);
        writeObjectToFile(accounts);
    } public static void deposit(String accountNumber, String amount)
    {
        ArrayList<Account> accounts = getAccountIndex(accountNumber);
        Account account = accounts.get(currentAccountIndex);
        double balance = Double.parseDouble(account.getAccountBalance());
        double amount_d = Double.parseDouble(amount);
        balance = balance + amount_d;
        account.setAccountBalance(String.valueOf(balance));
        System.out.println("Amount: "+ amount + " is being deposited into your account.");
                System.out.println("Your new balance is: "+ balance);
        writeObjectToFile(accounts); }
    public static Account getAccount(ArrayList<Account> accounts, String accountNumber)
    { for(int i=0; i < accounts.size(); i++)
    {
        Account temp = accounts.get(i);
        if(temp.getAccountNumber().equals(accountNumber))
        {
            return temp;
        } }
        return null;
    }
    public static void transfer(String fromAccountNumber,String toAccountNumber, String
            amount)
    { ArrayList<Account> accounts = readObjectFromFile();
        Account fromAccount = getAccount(accounts,fromAccountNumber);
        Account toAccount = getAccount(accounts,toAccountNumber);
        if(fromAccount == null)
        {
            System.out.println("From account number is not found");
            return;
        }
        if(toAccount == null) {
            System.out.println("To account number is not found");
            return;
        }
        double amount_d = Double.parseDouble(amount);
        double fromBalance = Double.parseDouble(fromAccount.getAccountBalance());
        double toBalance = Double.parseDouble(toAccount.getAccountBalance());
        if(amount_d > fromBalance)
        {
            System.out.println("Transaction not possible");
            return;
        }
        fromBalance = fromBalance - amount_d;
        toBalance = toBalance + amount_d;
        fromAccount.setAccountBalance(String.valueOf(fromBalance));
        toAccount.setAccountBalance(String.valueOf(toBalance));
        System.out.println("Amount: "+ amount + " is being transfered from account number: "+fromAccountNumber+" to: "+toAccountNumber);
        writeObjectToFile(accounts);
    }



}


