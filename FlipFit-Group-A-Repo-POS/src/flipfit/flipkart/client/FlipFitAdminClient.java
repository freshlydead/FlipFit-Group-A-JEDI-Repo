package flipfit.flipkart.client;

import flipfit.flipkart.constant.ColourConstants;

import java.util.Scanner;

public class FlipFitAdminClient {

    public static void approveUser() {
        System.out.println(ColourConstants.PASTEL_BLUE + "Approved User details" + ColourConstants.RESET);
    }

    public static void validateGym() {
        System.out.println(ColourConstants.PASTEL_BLUE + "Gym validated" + ColourConstants.RESET);
    }

    public static void validateSlot() {
        System.out.println(ColourConstants.PASTEL_BLUE + "Slot validated" + ColourConstants.RESET);
    }

    public boolean showMenu() {
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------Welcome to FlipFit Admin Client-----------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "1. Approve user" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "2. Validate Gym" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "3. Validate Slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "4. Approve Slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "5. Logout" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);

        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                approveUser();
                break;
            case 2:
                validateGym();
                break;
            case 3:
                validateSlot();
                break;
            default:
                System.out.println(ColourConstants.PASTEL_PINK + "Invalid choice" + ColourConstants.RESET);
        }
        return false;
    }
}
