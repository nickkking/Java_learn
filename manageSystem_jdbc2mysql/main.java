import java.sql.*;
import java.util.Scanner;
//主要问题1选择功能编码混乱2 ord=sc.nextInt;sc.nextLine();//换行 读入第一个数字再读下一个数据时用的这种方式换行？

public class tst{
    //initialize mysql 
    static final String Drv="com.mysql.cj.jdbc.Driver";
    static final String Dburl="jdbc:mysql://localhost:3306/tst?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String Nm="root";
    static final String pw="123";
    
    public static void main(String[] args){
        float open,high,low,close,vl;
        Scanner sc=new Scanner(System.in);
        int ord=0;
        //functions
        while (ord!=-1) {
            System.out.println("#####################1.add 2.search 3.delete 4.update -1.exit#####################");
            ord=sc.nextInt();
            String time;

            switch (ord) {
                case 0:
                    addsp();
                    break;
                case 1:
                    System.out.println("update some klines,entry the information below");
                    sc.nextLine();//换行
                    System.out.println("enter open high low close vl time:");
                    String[] arr=sc.nextLine().split(" ");
                    if(arr.length==7){
                        add(arr);
                    }
                    break;
                case 2:
                    System.out.println("search time:    like2021/10/8 12:54");
                    sc.nextLine();//换行
                    time=sc.nextLine();
                    search(time);
                    break;
                case 3:
                    sc.nextLine();//换行
                    System.out.println("enter del time:    like2021/10/8 12:54");
                    time=sc.nextLine();
                    del(time);
                    break;
                case 4:
                    sc.nextLine();//换行
                    System.out.println("update some klines,entry the information below");
                    System.out.println("enter open high low close vl time:");
                    arr=sc.nextLine().split(" ");
                    if(arr.length==7){
                    update(arr);
                    }
                    break;

                default:
                    System.out.println("Another num");
                    break;
            }
        }

    }
    //update data更改数据
    static void update(String[] d){
        try {
            Class.forName(Drv);
            Connection con =DriverManager.getConnection(Dburl,Nm,pw);
            String sql ="update klines set open=?,high=?,low=?,close=?,vl=? where time =?";
            PreparedStatement pdst =con.prepareStatement(sql);
            pdst.setFloat(1,Float.parseFloat(d[0]));
            pdst.setFloat(2,Float.parseFloat(d[1]));
            pdst.setFloat(3,Float.parseFloat(d[2]));
            pdst.setFloat(4,Float.parseFloat(d[3]));
            pdst.setFloat(5,Float.parseFloat(d[4]));
            pdst.setString(6,(d[5]+" "+d[6]));

            boolean num_u=pdst.execute();
            System.out.println("update"+' '+"!!!!"+num_u+"!!!!");

            pdst.close();
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    //添加数据
    static void add(String[] d){
        try {
            Class.forName(Drv);
            Connection con =DriverManager.getConnection(Dburl,Nm,pw);
            String sql ="INSERT INTO klines (open,high,low,close,vl,time) VALUES (?,?,?,?,?,?)";
            PreparedStatement pdst =con.prepareStatement(sql);
            pdst.setFloat(1,Float.parseFloat(d[0]));
            pdst.setFloat(2,Float.parseFloat(d[1]));
            pdst.setFloat(3,Float.parseFloat(d[2]));
            pdst.setFloat(4,Float.parseFloat(d[3]));
            pdst.setFloat(5,Float.parseFloat(d[4]));
            pdst.setString(6,(d[5]+" "+d[6]));

            boolean num_u=pdst.execute();
            System.out.println("insert"+' '+"!!!!"+num_u+"!!!!");

            pdst.close();
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    //delete data删数据
    static void del(String time){
        try {
            Class.forName(Drv);
            Connection con =DriverManager.getConnection(Dburl,Nm,pw);
            System.out.println("delete some klines,entry the information below");

            String sql ="delete from klines where time =?";
            PreparedStatement pdst =con.prepareStatement(sql);
            pdst.setString(1,time);
            int num_del=pdst.executeUpdate();
            System.out.println(String.format("del %d lines!",num_del));
            pdst.close();
            con.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    //添加样例数据
    static void addsp(){
        try {
            Class.forName(Drv);
            Connection con =DriverManager.getConnection(Dburl,Nm,pw);
            System.out.println("Add a kline,entry the information below");
            //
            String sql ="INSERT INTO klines (time,open,high,low,close,vl) VALUES (\"2021/10/8 12:54\",53066.4,53084.4,54366.4,53073.8,341)";
            PreparedStatement pdst =con.prepareStatement(sql);
            pdst.executeUpdate();
            pdst.close();
            con.close();
            System.out.println("Add Successfully!");


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //find data根据时间搜索数据
    static void search(String time) {
        try {
            Class.forName(Drv);
            Connection con =DriverManager.getConnection(Dburl,Nm,pw);
            //System.out.println("To search a kline,entry the time below:");
            //
            String sql = "SELECT * FROM klines WHERE time=?";
            PreparedStatement pdst =con.prepareStatement(sql);
            pdst.setString(1,time);
            ResultSet rs = pdst.executeQuery();
            while (rs.next()){
                Float open=rs.getFloat(2);
                Float high=rs.getFloat(3);
                Float low= rs.getFloat(4);
                Float close= rs.getFloat(5);
                Float vl= rs.getFloat(6);
                System.out.println(String.format("open:%f high:%f low:%f close:%f vl:%f",open,high,low,close,vl));//!!!!!!!!!!!!!!!!!!!!!
            }
            rs.close();
            pdst.close();
            con.close();
            System.out.println("Over");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
