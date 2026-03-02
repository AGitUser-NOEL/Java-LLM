import java.io.*;
import java.util.Scanner;

public class JavaChatbot {
    private static final String FILE_PATH = "logs.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot: Hello! Ask me anything (type 'exit' to quit).");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equalsIgnoreCase("exit")) break;

            String answer = findAnswer(userInput);

            if (answer != null) {
                System.out.println("Chatbot: " + answer);
            } else {
                System.out.println("Chatbot: I don't know that one. What is the answer?");
                System.out.print("You (providing answer): ");
                String newAnswer = scanner.nextLine().trim();
                saveKnowledge(userInput, newAnswer);
                System.out.println("Chatbot: Thanks! I've learned something new.");
            }
        }
        scanner.close();
    }

    /**
     * Performs a linear search through the file to find the matching question.
     */
    private static String findAnswer(String question) {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into Question and Answer parts using "; A: " as the divider
            String[] parts = line.split("; A: ");
            
            if (parts.length == 2) {
                String storedQuestion = parts[0].replace("Q: ", "").trim();
                String storedAnswer = parts[1].replace(";", "").trim();

                // Check for an exact match (ignoring case)
                if (storedQuestion.equalsIgnoreCase(question)) {
                    return storedAnswer;
                }
            }
        }
    } catch (IOException e) {
        return null;
    }
    return null;
}

    /**
     * Appends the new Q&A pair to the logs.txt file.
     */
    private static void saveKnowledge(String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Q: " + question + "; A: " + answer + ";");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
