# Nihongo CLI Trainer 🇯🇵

A lightweight, terminal-based vocabulary training engine built in **Java**. Designed to assist in language acquisition through randomized testing and persistent progress tracking.

## 🚀 Overview
This project solves the problem of "passive learning" by forcing active recall. Unlike static lists, **Nihongo Trainer** uses a randomized algorithm to test the user on a dataset of English-Japanese word pairs, tracking session scores to measure retention.

## 🛠 Technical Highlights
* **Language:** Java (JDK 17+)
* **Data Persistence:** Implemented custom **File I/O** logic (`BufferedReader`/`BufferedWriter`) to read/write vocabulary datasets from an external text file.
* **Data Structures:** Utilizes `ArrayList` for dynamic storage and `HashMap` logic for O(1) key-value association.
* **OOP Principles:** clean separation of concerns between the Data Model (`Flashcard` class) and Business Logic (`NihongoTrainer` class).
* **Randomization:** Uses `Collections.shuffle()` to ensure unique test sequences every session to prevent rote memorization of patterns.

## ⚙️ How to Run
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/nihongo-cli-trainer.git](https://github.com/yourusername/nihongo-cli-trainer.git)
    cd nihongo-cli-trainer
    ```

2.  **Compile the source**
    ```bash
    javac NihongoTrainer.java
    ```

3.  **Run the application**
    ```bash
    java NihongoTrainer
    ```

4.  **Add your own words:**
    * Open `vocab_data.txt` and add words in the format `English:Japanese`.
    * Example: `Computer:Konpyuta`

## 🔮 Future Improvements
* Implement a **Spaced Repetition System (SRS)** algorithm to prioritize frequently missed words.
* Migrate data storage from Text Files to **SQLite** or **JSON** for better scalability.
* Add a Swing or JavaFX **GUI** for a visual interface.
