/***
UTM.java Copyright (C) 2015 Ritu Ranjan Shrivastwa
rituranjanshrivastwa@gmail.com

This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the
Free Software Foundation; either version 2 of the License, or (at your
option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

Keep this notice intact with this as well as in all the modified 
version(s) of this code
***/

import java.io.*;
import java.util.Arrays;
class UTM{
   static int[] sel_tape;     //machine select [100..] for Machine 0, [010..] for Machine 1, and so on
   static String[] input_tape;//taking the input string w to be tested on the selected machine for acc
   static String[] work_tape; //tape for performing tests (can be done on i/p tape also but we preserve it)
   static int head_pos;       //to be defined w.r.t the input string
   static String PS,IS,TS;    //present state - write 0 instead of q0
   static BufferedReader br;
   
   // PS = Present State
   // IS = Input Symbol that we read from Tape (WT)
   // TS = Tape Symbol to be written on the Tape before move
   
   // --Example Def of head_pos--
   // ---------------------------
   // head_pos = 1 w= aabacbb
   //                 ^
   // head_pos = 0 w= aabacbb
   //                ^
   // head_pos = 2 w= aabacbb
   //                  ^
   
   public UTM(){//default machine definition
      sel_tape   = new int[10];
      input_tape = new String[100];
      work_tape  = new String[1000];
      Arrays.fill(input_tape," ");
      Arrays.fill(work_tape," ");
      input_tape[0] = work_tape[0] = "*";
      head_pos   = 1;
      Arrays.fill(sel_tape, 0);
      PS = "0";
   }
   public UTM(int i_t_s, int w_t_s){//parameterized
      sel_tape   = new int[10];
      input_tape = new String[i_t_s];
      work_tape  = new String[w_t_s];
      Arrays.fill(sel_tape, 0);
      Arrays.fill(input_tape," ");
      Arrays.fill(work_tape," ");
      input_tape[0] = work_tape[0] = "*";
      PS = "0";
   } 
   public UTM(int head_position, int i_t_s, int w_t_s){//parameterized with head position
      head_pos   = head_position;
      sel_tape   = new int[10];
      input_tape = new String[i_t_s];
      work_tape  = new String[w_t_s];
      Arrays.fill(sel_tape, 0);
      Arrays.fill(input_tape," ");
      Arrays.fill(work_tape," ");
      input_tape[0] = work_tape[0] = "*";
      PS = "0";
   }
   public void bulkErase(){//erase input and work tape and bring head to default position
      Arrays.fill(input_tape," ");
      Arrays.fill(work_tape," ");
      input_tape[0] = work_tape[0] = "*";
      Arrays.fill(sel_tape, 0);
      head_pos = 1;
   }
   public void loadMachine(int M, String input){
      //M ranges from 1 to 10
      char c;int i;
      sel_tape[M-1] = 1;
      for (i=1;i<=input.length();i++){
         c = input.charAt(i-1);
         input_tape[i] = c+"";
         work_tape[i]  = c+"";
      }
      work_tape[i] = input_tape[i] = "*";
      PS = "0";
      head_pos = 1;
   }
   public void loadMachine(int M, String input, int head_position){
      char c;int i;
      sel_tape[M-1] = 1;
      for (i=1;i<=input.length();i++){
         c = input.charAt(i-1);
         input_tape[i] = c+"";
         work_tape[i]  = c+"";
      }
      work_tape[i] = input_tape[i] = "*";
      PS = "0";
      head_pos = head_position;
   }
   public void testMachine()throws Exception{
      int i;
      for(i=0;i<10;i++)
         if(sel_tape[i] == 1)
            break;
      i++;
      int seekpos = 0;
      String file = "Machines\\"+"M"+i+".txt";//M1 M2 etc
      br = new BufferedReader(new FileReader(file));
      String line = br.readLine();
      
      //local variable set
      String move, NS, temp[];
      temp = line.split(" ");
      NS = temp[4];
      move = temp[3];
      TS = temp[2];
      IS = work_tape[head_pos];
      
      while(true){
         //if(work_tape[head_pos] == "*")break;
         if(PS.equals(temp[0]) && IS.equals(temp[1])){
            //do work
            displayworktape();
            work_tape[head_pos] = TS;
            if(move.equals("L")){
               head_pos--;
               //System.out.println("Head Pos decremented");
            }
            else if(move.equals("R")){
               head_pos++;
               //System.out.println("Head Pos incremented");
            }
            PS = NS;
            if(NS.equalsIgnoreCase("YES") || NS.equalsIgnoreCase("NO"))break;
            //reset file pointer and load values for next computation
            br.close();
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            //System.out.println(line);
            temp = line.split(" ");
            NS = temp[4];
            move = temp[3];
            TS = temp[2];
            IS = work_tape[head_pos];
         }
         else{
            //System.out.println("Entered Else");
            line = br.readLine();
            //System.out.println(line);
            temp = line.split(" ");
            NS = temp[4];
            move = temp[3];
            TS = temp[2];
            IS = work_tape[head_pos];
         }
      }
      System.out.println("\n"+NS);
   }
   public void displayworktape(){
      int c = 1;
      while(true){
         System.out.print(work_tape[c++]+" ");
         if(work_tape[c]=="*")break;
      }System.out.println();
   }
}
