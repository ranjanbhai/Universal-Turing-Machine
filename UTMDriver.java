import java.io.*;
class UTMDriver{
   public static void main(String args[])throws Exception{
      UTM ob = new UTM();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader fr = new BufferedReader(new FileReader("Machines\\metadata.txt"));
      System.out.println("*** Present Machines available to load ***");
      String temp;
      while((temp=fr.readLine())!=null)
         System.out.println(temp);
      System.out.println("Enter your machine choice to load");
      System.out.println("1 for M1 (Machine 1), 2 for M2, etc..");
      System.out.print("Your choice :: ");
      int m = Integer.parseInt(br.readLine());
      if(m<1 || m>3){
         System.out.println("Wrong choice! Try Again.");
         System.exit(0);   
      }
      System.out.print("Provide test string: ");
      String test = br.readLine();
      ob.loadMachine(m,test);
      ob.testMachine();
   }
}
