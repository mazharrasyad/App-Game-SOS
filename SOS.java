import java.util.InputMismatchException;
import java.util.Scanner;

public class SOS
{
    static Scanner masuk = new Scanner(System.in); 

    public static int PilihLawan()
    {
        while(true)
        {
            try {                
                return masuk.nextInt();                       
            } catch(InputMismatchException e){                
                System.out.print("Error, Masukkan 1 atau 2 : ");                
                masuk.next();                
            }
        }
    }

    public static void main(String args[])
    {    
        int lawan;
        boolean cek = true;        
        System.out.print("\033[H\033[2J");
        
        do
        {
            System.out.println("===== Game SOS =====\n");
            System.out.println("1. Vs Komputer");
            System.out.println("2. Vs Player");
            System.out.print("\nPilih Lawan : ");
            lawan = PilihLawan();            

            if(lawan == 1){
                Komputer join = new Komputer();                             
                cek = false;
            } else if(lawan == 2){
                Player join = new Player();
                cek = false;
            } else{                
                System.out.print("\033[H\033[2J");
                System.out.println("Error, Pilihan Lawan Tidak Ada !!!\n");
            }               
        } while(cek);    

        masuk.close();
    }
}