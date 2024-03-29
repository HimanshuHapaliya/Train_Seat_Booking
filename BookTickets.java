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
        int a[][]=new int[NoTrains][4];

        while(j<train.length)
        {
            // System.out.println("Enter First Line detail");
            // sc.nextLine();
            String trainDetails=sc.nextLine();
            String arr1[]=trainDetails.split(" ");
            int dist=0;
            if(arr1.length>2)
            {
            dist=Integer.parseInt(arr1[2].substring(arr1[2].indexOf("-")+1,arr1[2].length()));
            arr1[2]=arr1[2].substring(0,arr1[2].indexOf("-"));
            arr1[1]=arr1[1].substring(0,arr1[1].indexOf("-"));
            }
            
            // System.out.println("Enter Second Line detail");
            String coachDetails=sc.nextLine();
            String[] arr2=coachDetails.split(" ");
            for(int i=1;i<arr2.length;i++)
            {
                char ch1=arr2[i].charAt(0);
                switch(ch1)
                {
                    case 'A':
                    a[j][0]+=Integer.parseInt(arr2[i].substring(3));
                    break;
                    case 'B':
                    a[j][1]+=Integer.parseInt(arr2[i].substring(3));
                    break;
                    case 'H':
                    a[j][2]+=Integer.parseInt(arr2[i].substring(3));
                    break;
                    case 'S':
                    a[j][3]+=Integer.parseInt(arr2[i].substring(3));
                    break;
                }
            }
            train[j]=new Train(Integer.parseInt(arr1[0]),arr1[1],arr1[2],dist,a);
            j++;
            NoTrains--;
        }
        List<Customer> customerList=new ArrayList<>();
        int pnr=100000000;
        while(true)
        {
            // System.out.println("Enter ticket booking details");
            String customerDetails=sc.nextLine();
            String customerData[]=customerDetails.split(" ");
            for(int i=0;i<train.length;i++)
            {
                if(train[i].getpickPoint().equals(customerData[0]) && train[i].getdropPoint().equals(customerData[1]))
                {
                    // System.out.println("Inserted");
                    System.out.println(train[i].getSeats(customerData[3],i,customerData[4],a));
                    if(train[i].getSeats(customerData[3],i,customerData[4],a)>=0)
                    {
                        pnr++;
                        int c=calculateCharge(customerData[3], train[0].getDistance(), Integer.parseInt(customerData[4]));
                        customerList.add(new Customer(customerData[0], customerData[1], customerData[2], customerData[3], customerData[4], pnr, c));
                        System.out.print(pnr+" ");
                        System.out.println(c);
                    }
                    else
                    {
                        System.out.println("No seats Available");
                    }  
                }
            }
        
        }
    }
    static int calculateCharge(String cat,int kms,int noPassenger)
    {
        int sum=0;
        switch(cat)
        {
            case "SL":
                sum=kms*1;
            break;   
            case "3A":
                sum=kms*2;
            break;
            case "2A":
                sum=kms*3;
            break;
            case "1A":
                sum=kms*4;
            break;
        }
        return sum*noPassenger;
    }
}

class Train
{
    private int trainNo;
    private String pickPoint;
    private String dropPoint;
    private int distance;
    private int[][] coaches;
    
    public Train()
    {

    }
    public Train(int trainNo,String pickPoint,String dropPoint,int distance,int[][] coaches)
    {
        this.trainNo=trainNo;
        this.pickPoint=pickPoint;
        this.dropPoint=dropPoint;
        this.distance=distance;
        this.coaches=coaches;
    }
    public int getSeats(String ca,int g,String pass,int mat[][])
    {
        if(ca.equals("1A"))
        {
            if(mat[g][2]-Integer.parseInt(pass)>0)            
            mat[g][2]-=Integer.parseInt(pass);
            return mat[g][2];
        }
        else if(ca.equals("2A"))
        {
            if(mat[g][0]-Integer.parseInt(pass)>0)
            mat[g][0]-=Integer.parseInt(pass);
            return mat[g][0];
        }
        else if(ca.equals("3A"))
        {
            if(mat[g][1]-Integer.parseInt(pass)>0)
            mat[g][1]-=Integer.parseInt(pass);
            return mat[g][1];
        }
        else if(ca.equals("SL"))
        {
            System.out.println(mat[g][3]);
            if(mat[g][3]-Integer.parseInt(pass)>0)
            mat[g][3]-=Integer.parseInt(pass);
            return mat[g][3];
        }
        return -1;
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