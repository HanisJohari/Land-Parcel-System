import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class MainLand
{
   public static void main(String [] args) throws IOException {
    
         File inputFile=new File("LandData.txt");
         LinkedList landParcels = new LinkedList();
    
        // Load parcels from a text file
        String filename = ("LandData.txt");
        loadParcelsFromFile(filename, landParcels);
    
         boolean found = false;
         
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("-------------------------LAND PARCEL SYSTEM MENU-----------------------------------------------");
            System.out.println("+---+--------------------------------------------------------------------------------------+---+");
            System.out.println("| 1 |  Display all parcel                                                                       ");
            System.out.println("| 2 |  Updates information and  price for an existing land parcel                               ");
            System.out.println("| 3 |  Searches for a land parcel by its ID and displays its details                            ");
            System.out.println("| 4 |  Count the total land based on land’s type                                                ");
            System.out.println("| 5 |  Calculating total price per square meter of each land                                    ");
            System.out.println("| 6 |  Split the record into types of land between agricultural, residential, or commercial      ");
            System.out.println("| 7 |  Exit                                                                                      ");
            System.out.println("+---+--------------------------------------------------------------------------------------+---+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Display all parcels
                    System.out.println("All Parcels:");
                    System.out.println("===============");

                    Object obj = landParcels.getFirst();
                    while(obj != null)
                    {
                        LandParcel d =  (LandParcel) obj;
                        System.out.println(d.toString() + "\n");
                        obj = landParcels.getNext();
                    }
                    break;
                    
                case 2:
                      // Update parcel information 
                    System.out.print("Enter parcel ID to update: ");
                    String updateID = scanner.next();
                    found = false;
                    obj = landParcels.getFirst();
                    while (obj != null) {
                        LandParcel parcel = (LandParcel) obj;
                        if (parcel.getParcelID().equalsIgnoreCase(updateID)) {
                            found = true;
                            System.out.print("Enter new owner name: ");
                            scanner.nextLine();  
                            String newOwnerName = scanner.nextLine();
                            parcel.setOwnerName(newOwnerName);

                            System.out.print("Enter new land size in square meters: ");
                            double newLandSize = scanner.nextDouble();
                            parcel.setLandSizeSquareMeters(newLandSize);

                            System.out.print("Enter new land type (A-agricultural/C-commercial/R-residential): ");
                            char newLandType = scanner.next().charAt(0);
                            parcel.setLandType(newLandType);

                            System.out.print("Enter new land location: ");
                            scanner.nextLine();  
                            String newLandLocation = scanner.nextLine();
                            parcel.setLandLocation(newLandLocation);

                            System.out.print("Enter new price per square meter: ");
                            double newLandPrice = scanner.nextDouble();
                            parcel.setLandPrice(newLandPrice);

                            System.out.println("\nUpdated Land Parcel: " + parcel.toString());
                            
                            break;
                        }
                        obj = landParcels.getNext();
                    }
                    if (!found) {
                        System.out.println("Land parcel not found.");
                    }
                    break;
                   
                    
                case 3:
                    // Search parcel by ID and display it's details
                    System.out.print("Enter parcel ID to search: ");
                    String searchID = scanner.next();
                    
                    obj=landParcels.getFirst();
                     while(obj!=null){
                        LandParcel pc =(LandParcel)obj;
                          if (pc.getParcelID().equalsIgnoreCase(searchID)) {
                                found = true;
                                System.out.println("Land Parcel Found: " + pc.toString());
                                break;
                            }
                            obj=landParcels.getNext();
                        }
                        
                     if (!found) {
                            System.out.println("Land parcel not found.");
                        }
                    break;
                    
                case 4:
                    //count land based on each types
                    int countAgricultural=0;
                    int countResidential=0;
                    int countCommercial=0;
                    
                    System.out.println("\n===============================");
                    System.out.println("Count Land base on each types");
                    System.out.println("===============================");
                     obj=landParcels.getFirst();

                    while(obj!=null){
                        LandParcel pc =(LandParcel)obj;

                        if(pc.getLandType()=='A'|| pc.getLandType()=='a'){
                            countAgricultural ++;


                        }

                        else if(pc.getLandType()=='R'|| pc.getLandType()=='r'){
                            countResidential++;


                        }

                        else{

                            countCommercial++;

                        }

                        obj=landParcels.getNext();
                    }
                    System.out.println("Total Land for Agricultural is  :"+ countAgricultural);
                    System.out.println("Total Land for Residential is   :"+ countResidential);
                    System.out.println("Total Land for Commercial is    :"+ countCommercial);
                    
                    break;
                case 5:
                    //calculate total price for each type of land

                    double totalAgricultural=0.0;
                    double totalResidential=0.0;
                    double totalCommercial=0.0;
                    
                    System.out.println("\n=========================================");
                    System.out.println("Count total land price base on each types");
                    System.out.println("==========================================");
                    obj=landParcels.getFirst();
                    while(obj!=null){

                        LandParcel pc=(LandParcel)obj;

                        if (pc.getLandType()=='A'|| pc.getLandType()=='a'){

                            totalAgricultural+=pc.calculateTotalPrice();

                        }

                        else if(pc.getLandType()=='R'|| pc.getLandType()=='r'){

                            totalResidential+=pc.calculateTotalPrice();

                        }

                        else{

                            totalCommercial+=pc.calculateTotalPrice();

                        }
                        obj=landParcels.getNext();
                    }

                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    System.out.println("Total price for agricultural land: RM" + df.format(totalAgricultural));
                    System.out.println("Total price for residential land: RM" + df.format(totalResidential));
                    System.out.println("Total price for commercial land: RM" + df.format(totalCommercial));
                    break;
                    
                case 6:
                    // split record using remove operation 

                    LinkedList AgriculturalLL= new LinkedList();
                    LinkedList ResidentialLL= new LinkedList();
                    LinkedList CommercialLL= new LinkedList();

                
                    if(!landParcels.isEmpty()){

                        obj= landParcels.getFirst();
                        
                        
                        while(!landParcels.isEmpty()){
                            
                            obj=landParcels.removeFromFront();
                            LandParcel lp = (LandParcel)obj;
                            if(lp.getLandType()=='A' || lp.getLandType()=='a'){
                                AgriculturalLL.insertAtBack(obj);



                            }
                            else if(lp.getLandType()=='R' ||lp.getLandType()=='r' ){
                                ResidentialLL.insertAtBack(obj);


                            }
                            else if (lp.getLandType()=='C' || lp.getLandType()=='c'){

                                CommercialLL.insertAtBack(obj);


                            }
                        
                            
                            
                            
                            
                        }

                    }
                   
                    System.out.println("\n================================");
                    System.out.println("Land list in Agricultural");
                    System.out.println("================================");
                    obj=AgriculturalLL.getFirst();
                    while(obj!=null){
                        LandParcel lp = (LandParcel)obj;
                        System.out.print(lp.toString()+"\n");
                        obj=AgriculturalLL.getNext();


                    }
                   
                    
                    System.out.println("\n================================");
                    System.out.println("Land list in Residential");
                    System.out.println("================================");
                    obj=ResidentialLL.getFirst();
                    while(obj!=null){
                        LandParcel lp = (LandParcel)obj;
                        System.out.print(lp.toString()+"\n");
                        obj=ResidentialLL.getNext();


                    }
                    
                    System.out.println("\n================================");
                    System.out.println("Land list in Commercial");
                    System.out.println("================================");
                    obj=CommercialLL.getFirst();
                    while(obj!=null){
                        LandParcel lp = (LandParcel)obj;
                        System.out.print(lp.toString()+"\n");
                        obj=CommercialLL.getNext();


                    }
                    
                  
                 PrintWriter pwAgricultural = new PrintWriter(new FileWriter("AgriculturalListLandData.txt"));
                     obj = AgriculturalLL.getFirst();
                    while (obj != null) {
                        LandParcel parcel = (LandParcel) obj;
                        pwAgricultural.write(parcel.toStringToFile());
                        obj = AgriculturalLL.getNext();
                    }
                    pwAgricultural.close();
                    
                    
                    
                    PrintWriter pwResidential = new PrintWriter(new FileWriter("ResidentialListLandData.txt"));
                    obj = ResidentialLL.getFirst();
                    while (obj != null) {
                        LandParcel parcel = (LandParcel) obj;
                        pwResidential.write(parcel.toStringToFile());
                        obj = ResidentialLL.getNext();
                    }
                    pwResidential.close();
                    
                    
                    PrintWriter pwCommercial = new PrintWriter(new FileWriter("CommercialListLandData.txt"));
                    obj = CommercialLL.getFirst();
                    while (obj != null) {
                        LandParcel parcel = (LandParcel) obj;
                        pwCommercial.write(parcel.toStringToFile());
                        obj = CommercialLL.getNext();
                    }
                    pwCommercial.close();
                break;
            }
        } while (choice != 7);

        scanner.close();
    }

    // Load parcels from a file and add them to the linked list
    public static void loadParcelsFromFile(String filename, LinkedList landParcels) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() == 6) {
                    String parcelID = st.nextToken().trim();
                    String ownerName = st.nextToken().trim();
                    double landSizeSquareMeters = Double.parseDouble(st.nextToken().trim());
                    char landType = st.nextToken().trim().charAt(0);
                    String landLocation = st.nextToken().trim();
                    double landPrice = Double.parseDouble(st.nextToken().trim());

                    LandParcel parcel = new LandParcel(parcelID, ownerName, landSizeSquareMeters, landType, landLocation, landPrice);
                    landParcels.insertAtBack(parcel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

}
   
    }