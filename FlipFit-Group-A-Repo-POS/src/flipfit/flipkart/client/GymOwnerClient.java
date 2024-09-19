package flipfit.flipkart.client;

import flipfit.flipkart.constant.ColourConstants;

import java.util.Scanner;

public class GymOwnerClient {
    public boolean showMenu() {
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------Welcome to FlipFit Gym Owner Client------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "1. Add Gym" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "2. Update Gym" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "3. Delete Gym" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "4. Add Slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "5. Update Slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "6. Delete Slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "7. Logout" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter Gym Name: " + ColourConstants.RESET);
                String gymName = scanner.nextLine();
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter City: " + ColourConstants.RESET);
                String city = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Gym Created Successfully" + ColourConstants.RESET);
                break;
            case 2:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter Gym Id: " + ColourConstants.RESET);
                String gymId = scanner.nextLine();
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter Updated Gym Name: " + ColourConstants.RESET);
                String updatedGym = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Gym updated Successfully" + ColourConstants.RESET);
                break;
            case 3:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter Gym Id: " + ColourConstants.RESET);
                gymId = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Gym deleted Successfully" + ColourConstants.RESET);
                break;
            case 4:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter gymID: " + ColourConstants.RESET);
                gymId = scanner.nextLine();
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter new slot: " + ColourConstants.RESET);
                String slot = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Slot Added Successfully" + ColourConstants.RESET);
                break;
            case 5:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter Gym Id: " + ColourConstants.RESET);
                gymId = scanner.nextLine();
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter slotId: " + ColourConstants.RESET);
                String slotId = scanner.nextLine();
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter updated slot: " + ColourConstants.RESET);
                slot = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Slot updated Successfully" + ColourConstants.RESET);
                break;
            case 6:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter slotId: " + ColourConstants.RESET);
                slotId = scanner.nextLine();
                System.out.println(ColourConstants.PASTEL_BLUE + "Slot deleted Successfully" + ColourConstants.RESET);
                break;
            case 7:
                System.out.println(ColourConstants.PASTEL_ORANGE + "Logout Successfully" + ColourConstants.RESET);
                return true;
        }
        return false;
    }
}
