import java.util.Scanner;

public class Menu {
    public  SymbolTable symtable = new SymbolTable();

    public  void  menu()
    {
        System.out.println("options:");
        System.out.println("1:lookup");
        System.out.println("2:insert");
        System.out.println(("0:exit"));
        System.out.println();


    }

    public  void lookup()
    {
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
        boolean ok  = symtable.identify(text);
        if( ok == true )
        {
            System.out.println(("exist"));
        }
        else   System.out.println(("!exist"));


    }
    public  void insert()
    {
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
        symtable.add(text);
    }

    public void start()
    {boolean ok = true;
        Scanner scan= new Scanner(System.in);


        while( ok )
        {
            menu();
            int option= scan.nextInt();
            switch (option){
                case 1: lookup();
                    break;
                case 2: insert();
                    break;
                case 0: ok = false;
                    break;

            }

        }}

}
