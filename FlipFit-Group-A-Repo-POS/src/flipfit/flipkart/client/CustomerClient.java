package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.constant.ColourConstants;

import java.util.Scanner;

public class CustomerClient {

    private FlipFitCustomer flipFitCustomer;

    public CustomerClient(FlipFitCustomer flipFitCustomer) {
        this.flipFitCustomer = flipFitCustomer;
    }

    public boolean showMenu() {
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------Welcome to FlipFit: Your Fitness Partner--------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "1. Book slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "2. View Gyms" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "3. View All Slots" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "4. Book Slots" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "5. View all booked slots" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "6. Cancel booked slot" + ColourConstants.RESET);
        System.out.println(ColourConstants.PASTEL_YELLOW + "7. Logout" + ColourConstants.RESET);
        System.out.println(ColourConstants.CYAN + "-----------------------------------------------------------------------------------" + ColourConstants.RESET);

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter slotId: " + ColourConstants.RESET);
                int slotId = Integer.parseInt(scanner.nextLine());
                FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerService();
                FlipFitSlot flipFitSlot = flipFitGymOwnerService.getSlot(slotId);
                System.out.println(ColourConstants.PASTEL_BLUE + "Pay " + flipFitSlot.getPrice() + ColourConstants.RESET);
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter the transactionId: " + ColourConstants.RESET);
                String transactionId = scanner.nextLine();
                FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
                flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), slotId, transactionId);
                break;
            case 2:
                System.out.println(ColourConstants.PASTEL_BLUE + "Displaying all booked slots" + ColourConstants.RESET);
                break;
            case 3:
                System.out.print(ColourConstants.PASTEL_GREEN + "Enter slotId: " + ColourConstants.RESET);
                slotId = Integer.parseInt(scanner.nextLine());
                System.out.println(ColourConstants.PASTEL_PINK + "Slot cancelled successfully" + ColourConstants.RESET);
                break;
            case 4:
                System.out.println(ColourConstants.PASTEL_ORANGE + "Logged out successfully" + ColourConstants.RESET);
                return true;
        }
        return false;
    }
}
