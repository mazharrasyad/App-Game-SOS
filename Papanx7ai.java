import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Papanx7ai
{
    Scanner masuk = new Scanner(System.in);

    public int PilihHuruf()
    {
        while(true)
        {
            try {
                return masuk.nextInt();
            } catch(InputMismatchException e){                
                System.out.print("Error, Pilih Huruf S tekan 1 atau O tekan 2 : ");                
                masuk.next();                                
            }
        }
    }

    public int PilihPosisi()
    {
        while(true)
        {
            try {
                return masuk.nextInt();
            } catch(InputMismatchException e){                
                System.out.print("Error, Pilih Posisi [0 - 7] : ");
                masuk.next();                                
            }
        }
    }

    public Papanx7ai()
    {
        // Membuat Tampilan 3 x 3
        System.out.print("\033[H\033[2J");
        int i, j;        
        String posisi[][] = new String[7][7];   
        Set<String> Posisi = new HashSet<>();     

        for(i = 0; i < 7; i++){                  
            for(j = 0; j < 7; j++){                
                posisi[i][j] = "*";
                Posisi.add("" + (i-1) + (j-1) + (i-1) + (j) + (i-1) + (j+1));                
                Posisi.add("" + (i-1) + (j-1) + (i) + (j) + (i+1) + (j+1));                
                Posisi.add("" + (i-1) + (j-1) + (i) + (j-1) + (i+1) + (j-1));                
                Posisi.add("" + (i-1) + (j) + (i) + (j) + (i+1) + (j));                
                Posisi.add("" + (i-1) + (j+1) + (i) + (j+1) + (i+1) + (j+1));                
                Posisi.add("" + (i-1) + (j+1) + (i) + (j) + (i+1) + (j-1));                
                Posisi.add("" + (i) + (j-1) + (i) + (j) + (i) + (j+1));                
                Posisi.add("" + (i+1) + (j-1) + (i+1) + (j) + (i+1) + (j+1));                         
            }
        }

        // Mulai Game
        int pilih;   
        String huruf = null;     
        boolean cek = true;
        int giliran = 0;
        int skor_player = 0;
        int skor_komputer = 0;
        int hitung = 0;
        int baris = 0;
        int kolom = 0;  
        int poin = 0;
        String sos = null;

        do
        {                  
            // Cek Kotak            
            hitung = 0;            
            System.out.println("Papan 3 x 3\n");
            for(i = 0; i < 7; i++){   
                System.out.print("\t" + i);
            }
    
            System.out.println();
            for(i = 0; i < 7; i++){   
                System.out.print(i);                     
                for(j = 0; j < 7; j++){                                             
                    System.out.print("\t" + posisi[i][j]);
                    if(posisi[i][j] == "*"){
                        hitung++;
                    }
                }
                System.out.println();
            }

            // Berhenti Jika Penuh SOS
            if(hitung == 0){
                break;
            }

            // Skor
            System.out.println();
            System.out.println("Skor Player : " + skor_player);
            System.out.println("Skor Komputer : " + skor_komputer);

            // Giliran Main
            if(giliran == 0){
                System.out.println("\n===== Giliran Player =====");                
            } else{
                System.out.println("\n===== Giliran Komputer =====");                
            }
            
            // Mengisi Huruf S atau O
            if(giliran == 0)
            {            
                pilih = 0;
                cek = true;
                System.out.println();
                
                do
                {                            
                    System.out.print("Pilih Huruf S tekan 1 atau O tekan 2 : ");            
                    pilih = PilihHuruf();

                    if(pilih == 1){
                        huruf = "S";
                        cek = false;
                    } else if(pilih == 2){
                        huruf = "O";
                        cek = false;            
                    } else{
                        System.out.print("Error, ");
                    }
                } while(cek);

                // Pilih Baris
                cek = true;
                System.out.println();            
                
                do
                {                            
                    System.out.print("Pilih Baris [0 - 7] : ");
                    baris = PilihPosisi();

                    if(0 <= baris && baris < 7){
                        cek = false;
                    } else{
                        System.out.print("Error, ");
                    }
                } while(cek);

                // Pilih Kolom
                cek = true;
                System.out.println();

                do
                {                            
                    System.out.print("Pilih Kolom [0 - 7] : ");
                    kolom = PilihPosisi();

                    if(0 <= kolom && kolom < 7){
                        cek = false;
                    } else{
                        System.out.print("Error, ");
                    }
                } while(cek);         
            } 
            // Komputer Running
            else if(giliran == 1)
            {
                Random acak = new Random();

                pilih = 1 + acak.nextInt(2);

                if(pilih == 1){
                    huruf = "S";
                } else if(pilih == 2){
                    huruf = "O";         
                }

                baris = acak.nextInt(7);
                kolom = acak.nextInt(7);                
            }

            // Cek Isi
            if(posisi[baris][kolom].equals("S") || posisi[baris][kolom].equals("O")){
                System.out.print("\033[H\033[2J");
                System.out.print("Error, Posisi Sudah Ada\n\n");
            } 
            else
            {
                posisi[baris][kolom] = huruf;                    

                // Cek SOS
                for(i = 0; i < 7; i++){
                    for(j = 0; j < 7; j++){
                        try
                        {                              
                            // posisi[i-1][j-1] posisi[i-1][j] posisi[i-1][j+1]                   
                            // posisi[i][j-1]   posisi[i][j]   posisi[i][j+1]
                            // posisi[i+1][j-1] posisi[i+1][j] posisi[i+1][j+1]

                            // S O S
                            // * * *
                            // * * *
                            if((posisi[i-1][j-1] + posisi[i-1][j] + posisi[i-1][j+1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j-1) + (i-1) + (j) + (i-1) + (j+1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }             

                            // S * * 
                            // * O * 
                            // * * S =
                            if((posisi[i-1][j-1] + posisi[i][j] + posisi[i+1][j+1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j-1) + (i) + (j) + (i+1) + (j+1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }

                            // S * *
                            // O * *
                            // S * *
                            if((posisi[i-1][j-1] + posisi[i][j-1] + posisi[i+1][j-1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j-1) + (i) + (j-1) + (i+1) + (j-1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }       

                            // * S *
                            // * O *
                            // * S *
                            if((posisi[i-1][j] + posisi[i][j] + posisi[i+1][j]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j) + (i) + (j) + (i+1) + (j))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }       

                            // * * S
                            // * * O
                            // * * S                            
                            if((posisi[i-1][j+1] + posisi[i][j+1] + posisi[i+1][j+1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j+1) + (i) + (j+1) + (i+1) + (j+1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);                            
                            }                        

                            // * * S
                            // * O *
                            // S * *
                            if((posisi[i-1][j+1] + posisi[i][j] + posisi[i+1][j-1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i-1) + (j+1) + (i) + (j) + (i+1) + (j-1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }       

                            // * * *
                            // S O S
                            // * * *
                            if((posisi[i][j-1] + posisi[i][j] + posisi[i][j+1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i) + (j-1) + (i) + (j) + (i) + (j+1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);                            
                            }       

                            // * * *
                            // * * *
                            // S O S
                            if((posisi[i+1][j-1] + posisi[i+1][j] + posisi[i+1][j+1]).equals("SOS")){
                                for(String kode : Posisi){
                                    if(kode.equals("" + (i+1) + (j-1) + (i+1) + (j) + (i+1) + (j+1))){                                        
                                        sos = kode;                                        
                                        poin++;                                              
                                    }                    
                                }   
                                Posisi.remove(sos);
                            }    
                        } catch(ArrayIndexOutOfBoundsException e){
                            // Kosong
                        }
                    }
                }

                // Mendapat Skor            
                if(0 < poin){
                    System.out.println();
                    if(giliran == 0){
                        // Clear Screen
                        System.out.print("\033[H\033[2J");
                        System.out.println(poin + " Poin Untuk Player\n");
                        skor_player += poin;
                        poin = 0;
                    } else if(giliran == 1){
                        // Clear Screen
                        System.out.print("\033[H\033[2J");
                        System.out.println(poin + " Poin Untuk Komputer\n");
                        skor_komputer += poin;
                        poin = 0;
                    }
                } else{
                    // Clear Screen
                    System.out.print("\033[H\033[2J");
                    System.out.println("Belum Ada Poin\n");
                }

                // Ganti Pemain
                if(giliran == 0){
                    giliran = 1;
                } else{
                    giliran = 0;
                }                                            
            }                              
        } while(hitung != 0);    

        // Skor Akhir
        System.out.println();
        System.out.println("Skor Akhir Player : " + skor_player);
        System.out.println("Skor Akhir Komputer : " + skor_komputer);

        // Pemenang
        System.out.println();
        if(skor_komputer == skor_player){
            System.out.println("Pemenang : Draw");
        } else if(skor_komputer < skor_player){
            System.out.println("Pemenang : Player");
        } else if(skor_player < skor_komputer){
            System.out.println("Pemenang : Komputer");
        }
        
        // Tutup Scanner
        masuk.close();
    }
}