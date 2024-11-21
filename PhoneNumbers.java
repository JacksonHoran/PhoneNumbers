import java.util.Scanner;

public class PhoneNumbers {
    public static Scanner input = new Scanner(System.in);

    /*
     * Main calls showPhonenumbers first to display the current directory to the
     * user, then calls the updateNumbers method and after that is done executing it
     * displays the updated phone directory.
     */
    public static void main(String[] args) {

        long[] numbers = { 9876543210L, 0, 3129152000L, 9094567890L, 3034061234L, 0, 0, 8133774578L };
        String[] names = { "Adam Smith", "George Washington", "Alexander Hamilton", "Thomas Payne",
                "Betsy Ross", "Martha Washington", "Deborah Sampson", "Patience Wright" };

        showPhoneNumbers(numbers, names);
        updateNumbers(numbers, names);
        System.out.println("Here is your updayed phone directory:");
        showPhoneNumbers(numbers, names);
    }

    /**
     * This method updateNumbers asks the user for a list of names theyd like to
     * change, if the the user does not want to chang any names tehy simply type
     * *done*.
     * the method takes the raw string and formats it such that it is able to check
     * for string equality with the names that are already in the phone directory.
     * if a match is found
     * the user is promted to enter that persons phone number. If no match is found
     * the user is notified
     * and that element is skipped. The method utilizes a while loop so that, it
     * continues asking the user for
     * names untill they type *done*.
     *
     * @param phoneNumbers - array of phone numbers in order matching names (0
     *                     indicates number unknown)
     * @param people       - array of people names in same order as phone numbers
     * @return boolean true if any numbers were changed; false if no numbers changed
     *         successfully (for
     * 
     */
    public static boolean updateNumbers(long[] phoneNumbers, String[] people) {
        boolean updated = false;

        while (true) {
            System.out.println(
                    "Enter the names of those who you would like to update their numbers. Type a comma seperated list");
            System.out.println("or type *Done* if nothing needs to be updated.");

            String namesRaw = input.nextLine();

            if (namesRaw.equalsIgnoreCase("*Done*")) {
                break;
            }

            String[] names1 = namesRaw.split(",");
            String[] names = namesRaw.trim().split(",");
            for (int i = 0; i < names.length; i++) {
                String formattedName = names[i].trim().replaceAll("\\s+", "").toLowerCase();
                boolean found = false;

                for (int j = 0; j < people.length; j++) {
                    if (people[j].replaceAll("\\s+", "").equalsIgnoreCase(formattedName)) {

                        System.out.println("Enter the new number for " + people[j] + ".");

                        while (!input.hasNextLong()) {
                            System.out.println("Invalid phone number please try again.");
                            input.next();
                        }
                        phoneNumbers[j] = input.nextLong();
                        input.nextLine();
                        System.out.println("Phone Number Successfully Chnged.");
                        updated = true;
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println(names1[i] + " not found, skipping.");
                }
            }
        }
        return updated;
    }

    /**
     * this method prints out a nicely formatted index of names and numbers, it
     * uses pipes and padding to ensure a rectangualr shape. The method utilizes for
     * loops vto achieve this task
     *
     * @param phoneNumbers- array of phone numbers in order matching names (0
     *                      indicates number unknown)
     * @param people-       array of people names in same order as phone numbers
     *
     * 
     */
    public static void showPhoneNumbers(long[] phoneNumbers, String[] people) {
        System.out.println();
        System.out.println("Current Phone Directory:");
        System.out.println("--------------------------------------");
        String longestString = "";
        for (int i = 0; i < people.length; i++) {
            if (people[i].length() > longestString.length()) {
                longestString = people[i];
            }
        }
        int width = 4 + longestString.length();

        int[] longLengths = new int[phoneNumbers.length];

        for (int i = 0; i < phoneNumbers.length; i++) {
            int numDigits = String.valueOf(phoneNumbers[i]).length();
            longLengths[i] = numDigits;
        }

        for (int i = 0; i < phoneNumbers.length; i++) {
            String phoneNumber = (phoneNumbers[i] == 0) ? "*Unknown*" : String.valueOf(phoneNumbers[i]);
            int namePadding = width - people[i].length();
            int phonePadding = 11 - phoneNumber.length();

            System.out.println(
                    "| " + people[i] + " ".repeat(namePadding) + "| " + phoneNumber + " ".repeat(phonePadding) + "|");
        }
        System.out.println("--------------------------------------");
        System.out.println();
    }

}
