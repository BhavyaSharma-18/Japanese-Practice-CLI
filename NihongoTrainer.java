import java.io.*;
import java.util.*;

// CLASS 1: The "Card" (Represents a single flashcard)
class Flashcard {
    private String english;
    private String japanese;
    private int correctCount; // Tracks how well you know this word

    public Flashcard(String english, String japanese) {
        this.english = english;
        this.japanese = japanese;
        this.correctCount = 0;
    }

    public String getEnglish() { return english; }
    public String getJapanese() { return japanese; }
    public void incrementScore() { this.correctCount++; }
}

// CLASS 2: The "Brain" (Manages the logic)
public class NihongoTrainer {
    private static final String DATA_FILE = "vocab_data.txt";
    private List<Flashcard> deck;
    private Scanner scanner;

    public NihongoTrainer() {
        deck = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadDeck(); // Load words when app starts
    }

    // FEATURE: File I/O to load data
    private void loadDeck() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // Seed initial data if file doesn't exist
            deck.add(new Flashcard("Cat", "Neko"));
            deck.add(new Flashcard("Dog", "Inu"));
            deck.add(new Flashcard("Book", "Hon"));
            deck.add(new Flashcard("Water", "Mizu"));
            deck.add(new Flashcard("Student", "Gakusei"));
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    deck.add(new Flashcard(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading deck: " + e.getMessage());
        }
    }

    // FEATURE: Save progress (Data Persistence)
    private void saveDeck() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Flashcard card : deck) {
                bw.write(card.getEnglish() + ":" + card.getJapanese());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving deck.");
        }
    }

    // FEATURE: The "Quiz" Algorithm
    public void startSession() {
        System.out.println("\n=== NIHONGO TRAINER CLI v1.0 ===");
        System.out.println("Type 'EXIT' to quit and save.\n");

        Collections.shuffle(deck); // Randomization

        int score = 0;
        int total = 0;

        for (Flashcard card : deck) {
            System.out.print("Translate [" + card.getEnglish() + "] -> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) break;

            if (input.equalsIgnoreCase(card.getJapanese())) {
                System.out.println("✅ Correct! (Sugoi!)");
                card.incrementScore();
                score++;
            } else {
                System.out.println("❌ Wrong. Answer is: " + card.getJapanese());
            }
            total++;
            System.out.println("-------------------------------");
        }

        System.out.println("\nSession Over. Final Score: " + score + "/" + total);
        saveDeck(); // Save data before closing
        System.out.println("Progress saved to " + DATA_FILE);
    }

    public static void main(String[] args) {
        new NihongoTrainer().startSession();
    }
}
