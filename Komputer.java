import java.util.InputMismatchException;
import java.util.Scanner;

public class Komputer
{
    static Scanner masuk = new Scanner(System.in); 

    public static int PilihPapan()
    {
        while(true)
        {
            try {                
                return masuk.nextInt();                       
            } catch(InputMismatchException e){                
                System.out.print("Error, Masukkan 1 atau 2 atau 3 atau 4 : ");                
                masuk.next();                
            }
        }
    }

    public static int PilihAngka()
    {
        while(true)
        {
            try {                
                return masuk.nextInt();                       
            } catch(InputMismatchException e){                
                System.out.print("Error, Masukkan Angka Positif : ");                
                masuk.next();                
            }
        }
    }

    public Komputer()
    {    
        int papan, ukuran;
        boolean cek = true;               
        boolean angka = true;
        System.out.print("\033[H\033[2J");

        do
        {
            System.out.println("===== Game SOS Player vs Komputer =====\n");
            System.out.println("1. Papan 3 x 3");
            System.out.println("2. Papan 5 x 5");
            System.out.println("3. Papan 7 x 7");
            System.out.println("4. Papan Custom");
            System.out.print("\nPilih Ukuran Papan : ");
            papan = PilihPapan();            

            if(papan == 1){
                Papanx3ai main = new Papanx3ai();                             
                cek = false;
            } else if(papan == 2){
                Papanx5ai main = new Papanx5ai();
                cek = false;
            } else if(papan == 3){
                Papanx7ai main = new Papanx7ai();
                cek = false;
            } else if(papan == 4){
                System.out.println();

                do
                {                                        
                    System.out.print("Masukkan Angka Positif : ");
                    ukuran = PilihAngka();

                    if(-1 < ukuran){
                        angka = false;
                    } else{
                        System.out.print("Error, ");
                    }
                } while(angka);
                
                PapanxNai main = new PapanxNai(ukuran);
                cek = false;
            } else{                
                System.out.print("\033[H\033[2J");
                System.out.println("Error, Pilihan Papan Tidak Ada !!!\n");
            }               
        } while(cek);    

        masuk.close();
    }
}