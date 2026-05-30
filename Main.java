
/*
Name: Joshua Coquia
Date: May 18, 2026
Program Description: Run a short quiz, sourcing questions from a .csv file.
*/
// AI was NOT used in this assignment.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // 1. Config Variables
    // Edit these to change the number of questions
    // and the number of answer choices per question.
    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final int NUMBER_OF_CHOICES = 4;

    public static void main(String[] args) {
        // 2. These arrays store the which questions are will be presented to the user,
        // what answer options will be presented to the user for each question,
        // and finally the correct answer to each question.
        String[] questions = new String[NUMBER_OF_QUESTIONS];
        String[][] answers = new String[NUMBER_OF_QUESTIONS][NUMBER_OF_CHOICES];
        int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

        // 3. Abstracts away the logic for reading quiz questions from a file.
        // NOTE: Go to method definition for next bullet points in numeric order.
        readQuizFile(questions, answers, correctAnswers);

        Scanner input = new Scanner(System.in);

        // 13. Before the quiz starts, initalize and set the user's score to 0.
        int score = 0;

        // 14. Introduce the user to the game.
        System.out.println("Welcome to the AI Quiz Game!");
        System.out.println("Choose the correct answer by entering 1, 2, 3, or 4.\n");

        // 15. When quizzing, iterate over every question in the questions array.
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            // 16. For each question, print out each possible answer.
            for (int j = 0; j < answers[i].length; j++) {
                System.out.println((j + 1) + ". " + answers[i][j]);
            }

            // 17. Give the user the opportunity to answer.
            System.out.print("Your answer: ");
            int userAnswer = input.nextInt() - 1;

            // 18. If the user is correct, tell them and increase their score.
            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                // 19. If incorrect, tell the user the correct answer.
                System.out.println("Incorrect.");
                System.out.println("The correct answer was: " + answers[i][correctAnswers[i]] + "\n");
            }
        }

        // 20. When the quiz ends, tell the user their final score.
        System.out.println("Quiz complete!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);

        input.close();
    }

    // 4. Declaration for a method that populates the
    // questions, answers, and correctAnswers arrays
    // based on a given CSV file.
    public static void readQuizFile(String[] questions, String[][] answers, int[] correctAnswers) {
        try {
            // 5. Grab a file with the specific name mentioned in the quotes.
            // This file should be in the same directory as this Main.java file.
            File file = new File("ai_quiz_questions.csv");
            Scanner fileReader = new Scanner(file);

            // 6. Go to first line of file.
            // This is to skip the CSV headers present in the first line of the file
            // when the file actually gets read over in the while loop below.
            fileReader.nextLine();
            int index = 0;

            // 7. Iterate over the quiz questions file, stopping when either
            // the CSV file ends or when the questions array is fully populated.
            while (fileReader.hasNextLine() && index < questions.length) {
                // 8. Parse the CSV file into lines and comma separated values (hence CSV).
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                // 9. Set the current question to the question in the current line.
                questions[index] = data[0];

                // 10. For the number of choices set by the config var at the top,
                // add answers into the answers array based on what's in the CSV file.
                for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
                    answers[index][i] = data[i + 1];
                }

                // 11. Always set the correct answer for each question to the first answer.
                correctAnswers[index] = 0;
                index++;
            }

            fileReader.close();

            // 12. If the CSV file specified near the beginning of the method declaration
            // doesn't exist, state in STDOUT that it cannot be found.
        } catch (FileNotFoundException e) {
            System.out.println("The quiz file could not be found.");
        }
    }
}
