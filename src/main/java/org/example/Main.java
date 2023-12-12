package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class Main {
    private static final List<String> emailBodies = new ArrayList<>();// List to store email bodies
    private static final double SPAM_THRESHOLD = 0.5; // Threshold to determine if an email is spam

    /**
     * The main method initializes the spam detection process. It simulates a simple
     * scenario by adding predefined email bodies and then calculates their spam probability.
     * This method demonstrates how the spam detector could be used in a real application,
     * serving as a basic example of its functionality.
     */
    public static void main(String[] args) {

        System.out.println("Spam Detector is running");

        addEmailBody("I want to play everyday");
        addEmailBody("i sleep everyday at night");
        addEmailBody("I will sleep at night everyday");

        calculateSpamProbability();
    }
    /**
     * This method adds an email body to the list for analysis. It's essential for building
     * the dataset that the spam detection algorithm will analyze. By adding emails to this list,
     * we can simulate a real-world scenario where emails are continuously received and need
     * to be analyzed for spam.
     *
     * @param emailBody The content of the email to be added.
     */
    private static void addEmailBody(String emailBody) {

        emailBodies.add(emailBody);
    }
    /**
     * Calculates the similarity between two email bodies as a part of the spam detection logic.
     * This method is based on the idea that spam emails often have similar content. By calculating
     * the similarity between emails, we can identify potential spam. The similarity score helps in
     * determining if emails are likely to be spam when they share a significant amount of common content.
     *
     * @param emailBody1 The first email body.
     * @param emailBody2 The second email body.
     * @return A double representing the similarity score (0 to 1).
     */
    private static double calculateSimilarity(String emailBody1, String emailBody2) {

        Set<String> words1 = new HashSet<> (Arrays.asList(emailBody1.split(" ")));
        Set<String> words2 = new HashSet<>(Arrays.asList(emailBody2.split(" ")));

        Set<String> commonWords = new HashSet<>(words1);
        commonWords.retainAll(words2);

        return (double) commonWords.size() / (words1.size() + words2.size() - commonWords.size());
    }
    /**
     * Analyzes each email's content and calculates the probability of it being spam.
     * This method leverages the similarity scores between emails to make this determination,
     * based on the assumption that high similarity among email bodies is a potential indicator of spam.
     * This method is crucial in the spam detection process as it ultimately decides which emails are
     * flagged as spam, demonstrating a basic approach to spam filtering.
     */
    private static void calculateSpamProbability() {
        for (int i = 0; i < emailBodies.size(); i++) {
            double totalSimilarity = 0.0;// Total similarity score
            for (int j = 0; j < emailBodies.size(); j++) {
                if (i != j) {
                    // Calculate similarity with other emails, excluding itself
                    totalSimilarity += calculateSimilarity(emailBodies.get(i), emailBodies.get(j));
                }
            }
            double averageSimilarity = totalSimilarity / (emailBodies.size() - 1);// Calculate average similarity
            String spamStatus = averageSimilarity > SPAM_THRESHOLD ? "Likely Spam" : "Unlikely Spam";// Determine email is likely to be spam based on average similarity

            // Print the spam status and probability for each email
            System.out.println("Email " + (i + 1) + " is " + spamStatus + " (Spam Probability: " + averageSimilarity + ")");
        }
    }
}