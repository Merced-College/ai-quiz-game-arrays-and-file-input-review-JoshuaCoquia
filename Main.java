
/*
Name: Joshua Coquia
Date: May 18, 2026
Program Description: Run a short quiz, sourcing questions from a .csv file.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final int NUMBER_OF_CHOICES = 4;

    public static void main(String[] args) {
        String[] questions = new String[NUMBER_OF_QUESTIONS];
        String[][] answers = new String[NUMBER_OF_QUESTIONS][NUMBER_OF_CHOICES];
        int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

        readQuizFile(questions, answers, correctAnswers);

        Scanner input = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to the AI Quiz Game!");
        System.out.println("Choose the correct answer by entering 1, 2, 3, or 4.\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            for (int j = 0; j < answers[i].length; j++) {
                System.out.println((j + 1) + ". " + answers[i][j]);
            }

            System.out.print("Your answer: ");
            int userAnswer = input.nextInt() - 1;

            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect.");
                System.out.println("The correct answer was: " + answers[i][correctAnswers[i]] + "\n");
            }
        }

        System.out.println("Quiz complete!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);

        input.close();
    }

    public static void readQuizFile(String[] questions, String[][] answers, int[] correctAnswers) {
        try {
            File file = new File("ai_quiz_questions.csv");
            Scanner fileReader = new Scanner(file);

            fileReader.nextLine();

            int index = 0;

            while (fileReader.hasNextLine() && index < questions.length) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                questions[index] = data[0];

                for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
                    answers[index][i] = data[i + 1];
                }

                correctAnswers[index] = 0;
                index++;
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The quiz file could not be found.");
        }
    }
}
