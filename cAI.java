/**
 * Copyright Cougar Studios 2026
 * Noel Parivarthan Saraf
 * Java llm 0.1 beta
 */
import java.util.*;
public class CougarAI
{
    public static void main(String ar[])
    {
        int k=0;
        int i=0;
        boolean temp;
        Scanner sc = new Scanner(System.in);
        String st1="";
        String st2="";
        String store[][]=new String[1000][2];
        for(i=0;i<1000;i++)
        {
            store[i][0]="";
            store[i][1]="";
        }
        for(i=0;i<1000;i++)
        {
            int j;
            int z;
            System.out.print("Enter Input:");
            st1 =sc.nextLine();
            boolean x =false;
            for(z =0; z<10;z++)
            {
                if(st1.equals(store[z][0]))
                {
                    x=true;
                    break;
                }
            }
            if(x)
            {
                System.out.println("Answer:"+store[z][1]);
            }
            else
            {
                store[k][0]=st1;
                System.out.println("Enter Answer");
                st2=sc.nextLine();
                store[k][1]=st2;
                k++;
            }
        }
    }
}
