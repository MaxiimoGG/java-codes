import java.io.Console;

public class LoginSystem {
    public static void main(String[] args) {
        // Predefined correct credentials
        String correctUsername = "admin";
        String correctPassword = "password123";

        // System console for password masking
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available. Run from terminal.");
            return;
        }

        int attempts = 3;

        // Loop to allow 3 attempts
        for (int i = 1; i <= attempts; i++) {
            System.out.println("Attempt " + i + " of " + attempts);

            // Prompt for username
            String inputUsername = console.readLine("Enter username: ");

            // Prompt for password with masking
            char[] passwordChars = console.readPassword("Enter password: ");
            String inputPassword = new String(passwordChars);

            // Check credentials
            if (inputUsername.equals(correctUsername) && inputPassword.equals(correctPassword)) {
                System.out.println("Login successful! Welcome, " + inputUsername + ".");
                return;
            } else {
                System.out.println("Incorrect username or password.\n");
            }
        }

        System.out.println("Too many failed attempts. Access denied.");
    }
}
