import java.util.*;
class BookTickets
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        // System.out.println("Enter No of trains");
        int NoTrains=Integer.parseInt(sc.nextLine());
        
        Train train[]=new Train[NoTrains];
        int j=0;
        while(j<train.length)
        {
            // System.out.println("Enter First Line detail");
            // sc.nextLine();
            String trainDetails=sc.nextLine();
            String arr1[]=trainDetails.split(" ");
            int dist=0;
            if(arr1.length>2)
            {
            dist=Integer.parseInt(arr1[2].substring(arr1[2].indexOf("-"),arr1[2].length()));
            arr1[2]=arr1[2].substring(0,arr1[2].indexOf("-"));
            }
            
            // System.out.println("Enter Second Line detail");
            String coachDetails=sc.nextLine();
            String[] arr2=coachDetails.split(" ");
            List<String> l=new ArrayList<>();
            for(int i=1;i<arr2.length;i++)
            l.add(arr2[i]);
            train[j]=new Train(Integer.parseInt(arr1[0]),arr1[1],arr1[2],dist,l);
            j++;
            NoTrains--;
        }
        List<Customer> customerList=new ArrayList<>();
        int pnr=100000000;
        while(true)
        {
            String customerDetails=sc.nextLine();
            String customerData[]=customerDetails.split(" ");
            for(int i=0;i<train.length;i++)
            {
                if(train[0].getpickPoint().equals(customerData[0]) && train[1].getdropPoint().equals(customerData[1]))
                {
                    if(isSlotAvailable())
                    {
                        pnr++;
                        int c=calculateCharge(customerData[3], train[0].getDistance(), Integer.parseInt(customerData[4]));
                        customerList.add(new Customer(customerData[0], customerData[1], customerData[2], customerData[3], customerData[4], pnr, c));
                        System.out.print(pnr+" ");
                        System.out.println(c);
                        break;
                    }
                    else
                    {
                        System.out.println("No seats Available");
                    }  
                }
            }
            sc.close();
        }
    }
    static boolean isSlotAvailable()
    {

        return true;
    }
    static int calculateCharge(String cat,int kms,int noPassenger)
    {
        int sum=0;
        switch(cat)
        {
            case "SL":
                sum=kms*1;
            case "3A":
                sum=kms*2;
            case "2A":
                sum=kms*3;
            case "1A":
                sum=kms*4;
        }
        return sum*noPassenger;
    }
}
class Coaches
{
    private int trainId;
    private String s[];
    Coaches(int trainId,String s[])
    {
        this.trainId=trainId;
        this.s=s;
    }
}

class Train
{
    private int trainNo;
    private String pickPoint;
    private String dropPoint;
    private int distance;
    List<String> list=new ArrayList<>();
    public Train()
    {

    }
    public Train(int trainNo,String pickPoint,String dropPoint,int distance,List<String> list)
    {
        this.trainNo=trainNo;
        this.pickPoint=pickPoint;
        this.dropPoint=dropPoint;
        this.distance=distance;
        this.list=list;
    }
    
    public String getpickPoint()
    {
        return pickPoint;
    }
    public String getdropPoint()
    {
        return dropPoint;
    }
    public int getDistance()
    {
        return distance;
    }
}
class Customer
{
    private String source;
    private String lastdestination;
    private String date;
    private String category;
    private int noSeats;
    private int PNR;
    private int charge;

    public Customer(String source,String lastdestination,String date,String category,String noSeats,int PNR,int charge)
    {

    }
    public String getCategory()
    {
        return category;
    }
}