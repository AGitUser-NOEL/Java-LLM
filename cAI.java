import java.io.*;
import java.util.Scanner;
import org.jsoup.Jsoup;
import com.google.genai.Client; // Ensure you have the Gemini SDK JAR

public class AIChatBot {
    private static final String FILE_PATH = "logs.txt";
    private static final String API_KEY = "YOUR_GEMINI_API_KEY"; // Replace this!

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AI Online. Commands: 'read [url]' or just ask a question.");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            if (input.startsWith("read ")) {
                // Feature: Read a website
                String url = input.substring(5);
                System.out.println("Reading " + url + "...");
                String webContent = scrapeWebsite(url);
                String aiResponse = askAI("Summarize this: " + webContent);
                System.out.println("AI Summary: " + aiResponse);
            } else {
                // Feature: Normal Chat
                String aiResponse = askAI(input);
                System.out.println("AI: " + aiResponse);
            }
        }
        scanner.close();
    }

    public static String scrapeWebsite(String url) {
        try {
            return Jsoup.connect(url).get().text().substring(0, 2000); // Limit text for AI
        } catch (Exception e) {
            return "Error reading site: " + e.getMessage();
        }
    }

    public static String askAI(String prompt) {
        try {
            Client client = new Client();
            // In a real setup, you'd use: client.setApiKey(API_KEY);
            var response = client.models.generateContent("gemini-1.5-flash", prompt, null);
            return response.text();
        } catch (Exception e) {
            return "AI Error: Check your API key or connection.";
        }
    }
}
