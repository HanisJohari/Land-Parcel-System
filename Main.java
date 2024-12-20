import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.io.FileWriter;

public class Main {
    public static void main(String arg[])  throws java.io.IOException {
        File inputFile = new File("LandData.txt");
        Queue parcelQueue = new Queue();
        Scanner input = new Scanner(System.in);

        //1. Store the information parcel’s owner in the system

       try {
            Scanner inFile = new Scanner(inputFile);
            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                String delimiter = ";";
                StringTokenizer st = new StringTokenizer(s, delimiter);

                String parcelID = st.nextToken();
                String ownerName = st.nextToken();
                double landSizeSquareMeters = Double.parseDouble(st.nextToken());
                char landType = st.nextToken().charAt(0);
                String landLocation = st.nextToken();
                double landPrice = Double.parseDouble(st.nextToken());

                LandParcel parcel = new LandParcel(parcelID, ownerName, landSizeSquareMeters, landType, landLocation, landPrice);
                parcelQueue.enqueue(parcel);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Queue qTemp = new Queue();
        boolean found = false;

        int choice;
        do{

            System.out.println("-------------------------LAND PARCEL SYSTEM MENU-----------------------------------------------");
            System.out.println("+---+--------------------------------------------------------------------------------------+---+");
            System.out.println("| 1 |  Display all parcel                                                                       ");
            System.out.println("| 2 |  Updates information and  price for an existing land parcel                               ");
            System.out.println("| 3 |  Searches for a land parcel by its ID and displays its details                            ");
            System.out.println("| 4 |  Count the total land based on land’s type                                                ");
            System.out.println("| 5 |  Calculating total price per square meter of each land                                    ");
            System.out.println("| 6 | Split the record into types of land between agricultural, residential, or commercial      ");
            System.out.println("| 7 | Exit                                                                                      ");
            System.out.println("+---+--------------------------------------------------------------------------------------+---+");

            System.out.print("Enter choice:");
            choice=input.nextInt();

            switch(choice){
                case 1 :
                    System.out.println("\nAll Land Parcels:");
                    System.out.println("=================");

                    while (!parcelQueue.isEmpty()) {
                        Object obj = parcelQueue.dequeue();
                        LandParcel parcel = (LandParcel) obj;
                        qTemp.enqueue(parcel);
                        System.out.println(parcel.toString() + "\n");
                    }

                    while (!qTemp.isEmpty()) {
                        Object obj = qTemp.dequeue();
                        parcelQueue.enqueue(obj);
                    }
                    break;

                case 2 :
                        // 2. Updates all information for an existing land parcel.
                        input.nextLine();
                   
                        System.out.print("Enter land ID: ");
                        String landID = input.nextLine();


                        while (!parcelQueue.isEmpty()) {
                            Object obj = parcelQueue.dequeue();
                            LandParcel parcel = (LandParcel) obj;
                            qTemp.enqueue(parcel);

                            if (parcel.getParcelID().equalsIgnoreCase(landID)) {
                                found = true;
                                System.out.print("Enter new owner name: ");
                                String newOwner=input.nextLine();
                                parcel.setOwnerName(newOwner);

                                System.out.print("Enter new land size per square meters:");
                                double landSizeSquareMeters=input.nextDouble();
                                parcel.setLandSizeSquareMeters(landSizeSquareMeters);

                                System.out.print("Enter land type(A-agriculture/C-Commercial/R-residential):");
                                char newType=input.next().charAt(0);
                                parcel.setLandType(newType);
                                input.nextLine();

                                System.out.print("Enter new land location: ");
                                String newLocation=input.nextLine();
                                parcel.setLandLocation(newLocation);

                                System.out.print("Enter new price per square meter: ");
                                double newPrice = input.nextDouble();
                                input.nextLine();
                                parcel.setLandPrice(newPrice);

                                System.out.println("/nUpdated Land Parcel: " + parcel.toString());
                                break;
                            }


                        }
                        
                            if (!found) {
                                System.out.println("Land parcel not found");
                            }
                            
    
                            while (!qTemp.isEmpty()) {
                                Object obj = qTemp.dequeue();
                                parcelQueue.enqueue(obj);
                            }
                            
                        break;

                case 3:
                        // 3. Searches for a land parcel by its ID and displays its details.
                        input.nextLine();
                        System.out.print("Enter land ID to search: ");
                        landID = input.nextLine();
    
                        while (!parcelQueue.isEmpty()) {
                            Object obj = parcelQueue.dequeue();
                            LandParcel parcel = (LandParcel) obj;
                            qTemp.enqueue(parcel);
    
                            if (parcel.getParcelID().equalsIgnoreCase(landID)) {
                                found = true;
                                System.out.println("Land Parcel Found: " + parcel.toString());
                                break;
                            }
                        }
    
                        if (!found) {
                            System.out.println("Land parcel not found.");
                        }

                        while (!qTemp.isEmpty()) {
                            Object obj = qTemp.dequeue();
                            parcelQueue.enqueue(obj);
                        }
                        break;

                case 4 :
                        //4. Count the total land based on land’s type
                        int countResidential = 0;
                        int countAgricultural=0;
                        int countCommercial=0;
    
                        while (!parcelQueue.isEmpty()) {
                            Object obj = parcelQueue.dequeue();
                            LandParcel parcel = (LandParcel) obj;
                            qTemp.enqueue(parcel);
    
                            if (parcel.getLandType() == 'R' || parcel.getLandType()=='r') {
                                countResidential++;
                            }
    
                            else if(parcel.getLandType()=='A' || parcel.getLandType()=='a')
                            {
                                countAgricultural++;
                            }
    
                            else if(parcel.getLandType()=='C' || parcel.getLandType()=='c')
                            {
                                countCommercial++;
                            }
    
    
                        }
    
                        while (!qTemp.isEmpty()) {
                            Object obj = qTemp.dequeue();
                            parcelQueue.enqueue(obj);
                        }
    
                        System.out.println("The number of residential parcels: " + countResidential);
                        System.out.println("The number of agricultural parcels: " + countAgricultural);
                        System.out.println("The number of commercial parcels: " + countCommercial);
                        break;

                case 5 :
                        //5.Calculating total price per square meter of each land
    
                        double totalAgriculturalPrice = 0.0;
                        double totalResidentialPrice = 0.0;
                        double totalCommercialPrice = 0.0;
    
                        while (!parcelQueue.isEmpty()) {
                            Object obj = parcelQueue.dequeue();
                            LandParcel parcel = (LandParcel) obj;
                            qTemp.enqueue(parcel);
    
    
                            if (parcel.getLandType() == 'A' || parcel.getLandType() == 'a')
                                totalAgriculturalPrice += parcel.calculateTotalPrice();
    
                            if (parcel.getLandType() == 'R' || parcel.getLandType()=='r')
                                totalResidentialPrice+=parcel.calculateTotalPrice();
    
                            else if(parcel.getLandType()=='C' || parcel.getLandType()=='c')
                                totalCommercialPrice+= parcel.calculateTotalPrice();
    
                        }
    
                        while (!qTemp.isEmpty()) {
                            Object obj = qTemp.dequeue();
                            parcelQueue.enqueue(obj);
                        }

                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        System.out.println("Total price for agricultural land: RM" + df.format(totalAgriculturalPrice));
                        System.out.println("Total price for residential land: RM" + df.format(totalResidentialPrice));
                        System.out.println("Total price for commercial land: RM" + df.format(totalCommercialPrice));
                        break;

                case 6 :
                        //6. Split the record into types of land between agricultural, residential, or commercial
                        //using remove operation
                        Queue agriculturQueue=new Queue();
                        Queue residentialQueue=new Queue();
                        Queue commercialQueue=new Queue();
    
    
                        while (!parcelQueue.isEmpty()) {
                            Object obj = parcelQueue.dequeue();
                            LandParcel parcel = (LandParcel) obj;
    
                            if(parcel.getLandType()=='A' || parcel.getLandType() == 'a') {
                                agriculturQueue.enqueue(obj);
                            }
                            else if(parcel.getLandType()=='R' || parcel.getLandType() == 'r')
                                residentialQueue.enqueue(obj);
    
                            else if(parcel.getLandType()=='C' || parcel.getLandType() == 'c')
                                commercialQueue.enqueue(obj);
                            else
                                qTemp.enqueue(obj);
                        }
    
                        while (!qTemp.isEmpty()) {
                            parcelQueue.enqueue(qTemp.dequeue());
                        }
    
    
                        System.out.println("\nAgricultural Land Parcels:");
                        System.out.println("========================");
    
                        while (!agriculturQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) agriculturQueue.dequeue();
                            System.out.println(parcel.toString() + "\n");
                            qTemp.enqueue(parcel);
                        }
                         while (!qTemp.isEmpty()) {
                            agriculturQueue.enqueue(qTemp.dequeue());
                        }
    
                        System.out.println("\nResidential Land Parcels:");
                        System.out.println("=========================");
    
                        while (!residentialQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) residentialQueue.dequeue();
                            System.out.println(parcel.toString() + "\n");
                            qTemp.enqueue(parcel);
                        }
                        while (!qTemp.isEmpty()) {
                            residentialQueue.enqueue(qTemp.dequeue());
                        }
                        
                        System.out.println("\nCommercial Land Parcels:");
                        System.out.println("========================");
    
                        while (!commercialQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) commercialQueue.dequeue();
                            System.out.println(parcel.toString() + "\n");
                            qTemp.enqueue(parcel);
                        }
                         while (!qTemp.isEmpty()) {
                            commercialQueue.enqueue(qTemp.dequeue());
                        }
                        
                        //print to file
                        PrintWriter pwAgricultural = new PrintWriter(new FileWriter("AgriculturalLandData.txt"));
                        while (!agriculturQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) agriculturQueue.dequeue();
                            pwAgricultural.write(parcel.toStringToFile());
                        }
                        pwAgricultural.close();
                
                        PrintWriter pwResidential = new PrintWriter(new FileWriter("ResidentialLandData.txt"));
                        while (!residentialQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) residentialQueue.dequeue();
                            pwResidential.write(parcel.toStringToFile());
                        }
                        pwResidential.close();
                
                        PrintWriter pwCommercial = new PrintWriter(new FileWriter("CommercialLandData.txt"));
                        while (!commercialQueue.isEmpty()) {
                            LandParcel parcel = (LandParcel) commercialQueue.dequeue();
                            pwCommercial.write(parcel.toStringToFile());
                        }
                        pwCommercial.close();
                                        break;
                            }
                        }while(choice!=7);


       
       

    }
}