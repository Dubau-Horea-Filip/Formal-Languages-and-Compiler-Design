import java.io.*;
import java.util.*;
//Statement: Implement a scanner (lexical analyzer): Implement the scanning algorithm and use ST from lab 2 for the symbol table.

public class Menu {
    public SymbolTable symtable = new SymbolTable();
    public Map<String, Integer> pif = new HashMap<String, Integer>();

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
        File tokensFile = new File("D:\\Faculate\\Compilare\\laborator\\github\\Formal-Languages-and-Compiler-Design\\Laborator\\src\\Tokens");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));


        // Declaring a string variable
        String st;
        String tok;
        String prog = "";
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {//System.out.println(st);
            prog = prog + st;
        }

        String[] tokens = new String[0];
        if (prog != null) {
            tokens = prog.toString().split("((?=\\()|(?<=\\())|" +
                    "((?=\\))|(?<=\\)))|" +
                    "((?=:)|(?<=:))|" +
                    "((?=;)|(?<=;))|" +
                    "((?=,)|(?<=,))|" +
                    "((?=})|(?<=}))|" +
                    "((?=\\{)|(?<=\\{))|" +
                    "((?=<-)|(?<=<-))| |  |   |   |\n");
        }
        //String[] tokens = prog.split("[ ,:.;()]" );
        for (String t : tokens) {
            //System.out.println(t);
            boolean ok = true;
            BufferedReader brtokens = new BufferedReader(new FileReader(tokensFile));
            while ((tok = brtokens.readLine()) != null) {
                if (t.equals(tok)) {  //if it is a token put it in pif with value -1
                    System.out.println("token is " + t + " and we put it in pif with value -1");
                    this.pif.put(t, -1);
                    ok = false;
                }
            }
                    if (ok) {
                        if (this.symtable.identify(t)) {
                            System.out.println("token is " + t + " and we put it in pif with position to ST " + this.symtable.retPos(t));
                            this.pif.put("id", this.symtable.retPos(t));
                        } else {
                            this.insert(t);
                            this.pif.put("id", this.symtable.retPos(t));
                            System.out.println("token is " + t + " and we put it in pif with position to ST " + this.symtable.retPos(t));
                        }
                    }


        }


    }

    public void lookup() {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        boolean ok = symtable.identify(text);
        if (ok) {
            System.out.println(("exist"));
        } else System.out.println(("!exist"));


    }

    public void insert() {
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
        symtable.add(text);
    }

    public void insert(String s) {
        symtable.add(s);
    }

    public void generate() {

        System.out.println(this.symtable.toString());

        try {
            File myObj = new File("PIF.out");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                FileWriter myWriter = new FileWriter("PIF.out");
                myWriter.write(this.pif.toString());
                myWriter.close();

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void start() throws IOException {
        boolean ok = true;
        Scanner scan = new Scanner(System.in);
        while (ok) {
            menu();
            int option = scan.nextInt();
            switch (option) {
                case 1 -> lookup();
                case 2 -> insert();
                case 3 -> load();
                case 4 -> generate();
                case 0 -> ok = false;
            }

        }
    }

}
