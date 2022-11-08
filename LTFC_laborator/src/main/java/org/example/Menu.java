package org.example;

import java.io.*;
import java.util.*;
//Statement: Implement a scanner (lexical analyzer): Implement the scanning algorithm and use ST from lab 2 for the symbol table.

//Java program to implement in-built pair classes

public class Menu {
    File tokensFile = new File("D:\\Faculate\\Compilare\\laborator\\github\\LTFC_laborator\\src\\main\\java\\org\\example\\Tokens");
    public SymbolTable symtable = new SymbolTable();
    //public Map<String, Integer> pif = new MultiValueMap<>();
    //public MultiValuedMap<String, Integer> pif = new ArrayListValuedHashMap<>();
    //Dictionary<String,Integer> pif = new Dictionary<String, Integer>() {

    //Pair<Integer, String> pair = new Pair<>(1, "One");
    List<Pair<String, Integer>> pif = new ArrayList<>();


    public void menu() {
        System.out.println("options:");
        System.out.println("1:lookup");
        System.out.println("2:insert");
        System.out.println("3:load from file");
        System.out.println("4:load to symboltable");
        System.out.println(("0:exit"));
        System.out.println();
    }

    public void perline(String prog,Integer line) throws IOException {
        String[] tokens = new String[0];
        if (prog != null) {
            tokens = prog.toString().split("((?=\\()|(?<=\\())|" +
                    "((?=\\))|(?<=\\)))|" +
                    "((?=:)|(?<=:))|" +
                    "((?=;)|(?<=;))|" +
                    "((?=\\+)|(?<=\\+))|" +
                    "((?=,)|(?<=,))|" +
                    "((?=})|(?<=}))|" +
                    "((?=\\{)|(?<=\\{))|" +
                    "((?=<-)|(?<=<-))+" +
                    "| |  |   |   " +
                    "\n");
        }
        //String[] tokens = prog.split("[ ,:.;()]" );
        for (String t : tokens) {
            //System.out.println(t);
            boolean ok = true;
            BufferedReader brtokens = new BufferedReader(new FileReader(tokensFile));
            String tok;
            while ((tok = brtokens.readLine()) != null) {
                if (t.equals(tok)) {  //if it is a token put it in pif with value -1
                    System.out.println("token is " + t + " and we put it in pif with value -1");
                    //this.pif.put(t, -1);
                    this.pif.add(new Pair<>(t,-1));
                    ok = false;
                }
            }
            if (ok) {
                if (this.symtable.identify(t)) {
                    System.out.println("token is " + t + " and we put it in pif with position to ST " + this.symtable.retPos(t));
                    if( t.chars().allMatch( Character::isDigit ))
                    {
                        Pair<String, Integer> p = new Pair<>("constant", this.symtable.retPos(t));
                        this.pif.add(p);
                    }
                    Pair<String, Integer> p = new Pair<>("id", this.symtable.retPos(t));
                    this.pif.add(p);

                } else {
                    this.insert(t);
                    if( t.chars().allMatch( Character::isDigit )  || t.matches("false")  || t.matches("true"))
                    {
                        this.pif.add(new Pair<>("constant",this.symtable.retPos(t)));
                        System.out.println("token is " + t + " and we put it in pif with position to ST " + this.symtable.retPos(t));
                        continue;
                    }
                    if(t.matches("^[a-zA-Z][a-zA-Z0-9]*$"))
                    {
                        this.pif.add(new Pair<>("id",this.symtable.retPos(t)));
                        System.out.println("token is " + t + " and we put it in pif with position to ST " + this.symtable.retPos(t));
                        continue;
                    }

                        System.out.println("Lexical Error at line "+ line + " token: " +t);

                }
            }


        }
    }

    public void load() throws IOException {
        File file = new File("D:\\Faculate\\Compilare\\laborator\\github\\LTFC_laborator\\src\\main\\java\\Problems\\p2");


        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));


        // Declaring a string variable
        String st;
        String prog = "";
        // Condition holds true till
        // there is character in a string
        int line =1;
        while ((st = br.readLine()) != null) {//System.out.println(st);
            prog = st;
            perline(prog,line++);
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

        //System.out.println();

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

        try {
            File myObj = new File("ST.out");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                FileWriter myWriter = new FileWriter("ST.out");
                myWriter.write(this.symtable.toString());
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
