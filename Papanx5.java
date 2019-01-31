import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;

public class Papanx5
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
                System.out.print("Error, Pilih Posisi [0 - 5] : ");
                masuk.next();                                
            }
        }
    }

    public Papanx5()
    {
        // Membuat Tampilan 5 x 5
        System.out.print("\033[H\033[2J");
        int i, j;        
        String posisi[][] = new String[5][5];   
        Set<String> Posisi = new HashSet<>();     

        for(i = 0; i < 5; i++){                  
            for(j = 0; j < 5; j++){                
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
        int skor_player1 = 0;
        int skor_player2 = 0;
        int hitung = 0;
        int baris, kolom;  
        int poin = 0;
        String sos = null;

        do
        {                  
            // Cek Kotak            
            hitung = 0;            
            System.out.println("Papan 5 x 5\n");
            for(i = 0; i < 5; i++){   
                System.out.print("\t" + i);
            }
    
            System.out.println();
            for(i = 0; i < 5; i++){   
                System.out.print(i);                     
                for(j = 0; j < 5; j++){                                             
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
            System.out.println("Skor Player 1 : " + skor_player1);
            System.out.println("Skor Player 2 : " + skor_player2);

            // Giliran Main
            if(giliran == 0){
                System.out.println("\n===== Giliran Player 1 =====");                
            } else{
                System.out.println("\n===== Giliran Player 2 =====");                
            }

            // Mengisi Huruf S atau O
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
                System.out.print("Pilih Baris [0 - 5] : ");
                baris = PilihPosisi();

                if(0 <= baris && baris < 5){
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
                System.out.print("Pilih Kolom [0 - 5] : ");
                kolom = PilihPosisi();

                if(0 <= kolom && kolom < 5){
                    cek = false;
                } else{
                    System.out.print("Error, ");
                }
            } while(cek);                             

            // Cek Isi
            if(posisi[baris][kolom].equals("S") || posisi[baris][kolom].equals("O")){
                System.out.print("\033[H\033[2J");
                System.out.print("Error, Posisi Sudah Ada\n\n");
            } 
            else
            {
                posisi[baris][kolom] = huruf;                    

                // Cek SOS
                for(i = 0; i < 5; i++){
                    for(j = 0; j < 5; j++){
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
                        System.out.println(poin + " Poin Untuk Player 1\n");
                        skor_player1 += poin;
                        poin = 0;
                    } else if(giliran == 1){
                        // Clear Screen
                        System.out.print("\033[H\033[2J");
                        System.out.println(poin + " Poin Untuk Player 2\n");
                        skor_player2 += poin;
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
        System.out.println("Skor Akhir Player 1 : " + skor_player1);
        System.out.println("Skor Akhir Player 2 : " + skor_player2);

        // Pemenang
        System.out.println();
        if(skor_player2 == skor_player1){
            System.out.println("Pemenang : Draw");
        } else if(skor_player2 < skor_player1){
            System.out.println("Pemenang : Player 1");
        } else if(skor_player1 < skor_player2){
            System.out.println("Pemenang : Player 2");
        }
        
        // Tutup Scanner
        masuk.close();
    }
}