import java.io.*;
import java.util.Scanner;

public class Menu {
    public SymbolTable symtable = new SymbolTable();

    public void menu() {
        System.out.println("options:");
        System.out.println("1:lookup");
        System.out.println("2:insert");
        System.out.println("3:load from file");
        System.out.println("4:load to symboltable");
        System.out.println(("0:exit"));
        System.out.println();
    }

    public void load() throws IOException {
        File file = new File("D:\\Faculate\\Compilare\\laborator\\github\\Formal-Languages-and-Compiler-Design\\Laborator\\src\\Problems\\p1");
        File tokensFile = new File("D:\\Faculate\\Compilare\\laborator\\Lab1\\Tokens");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st,prog = null;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
        {//System.out.println(st);
        prog=prog+st;}

        String[] tokens = prog.split("((?=\\()|(?<=\\())|((?=\\))|(?<=\\)))|((?=:)|(?<=:))|((?=,)|(?<=,))|((?=;)|(?<=;))| " );
        //String[] tokens = prog.split("[ ,:.;()]" );
        for (String t : tokens)
        {
            System.out.println(t);
            

        }

    }

    public void lookup() {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        boolean ok = symtable.identify(text);
        if (ok == true) {
            System.out.println(("exist"));
        } else System.out.println(("!exist"));


    }

    public void insert() {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        symtable.add(text);
    }

    public void start() throws IOException {
        boolean ok = true;
        Scanner scan = new Scanner(System.in);


        while (ok) {
            menu();
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    lookup();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    load();
                case 0:
                    ok = false;
                    break;

            }

        }
    }

}
