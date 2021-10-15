import java.io.File ;
import java.io.InputStream ;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.DataInputStream;
import java.io.FileInputStream ;
import java.util.Scanner;
//查，改，删

class Tst{
    public static void main(String[] args) throws Exception {
        char[] b = new char[600];

        FileInputStream fis = new FileInputStream("data.txt");
        FileReader fr = new FileReader("data.txt");
        Scanner sc =new Scanner(System.in);
        System.out.println("Entry the time you want query(like 13:55):");
        String hm=sc.next();
        fr.skip(50);
        String time= "2021/10/8 "+hm;
        fr.read(b);
        search(time,b);
        fr.close();

    }


    static void search (String time,char[] b) throws Exception {
        char c[] = new char[15];
        char[] e = new char[52];
        int k = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 15; j++) {
                c[j] = b[k + j];
            }//search time

            for (int j = 0; j < 52; j++) {
                e[j] = b[k + j];
            }//one time info

            String cstr = new String(c);
            if (time.equals(cstr)) {
                System.out.println("\t\t\t\t time\topen    high    low     close   volume");
                System.out.print("Result:" + new String(e));
                break;
            }
            else if(i==8) System.out.print("Wrong time");

            k += 52;

        }

    }
    static void fix(String[] data,String time,String num) throws Exception{

    }
}

