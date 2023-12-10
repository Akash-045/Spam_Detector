package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class Main {
    private static final List<String> emailBodies = new ArrayList<>();// List to store email bodies
    private static final double SPAM_THRESHOLD = 0.5; // Threshold to determine if an email is spam


    public static void main(String[] args) {
        // Main method - program execution starts here
        System.out.println("Spam Detector is running");

        addEmailBody("I want to play everyday");
        addEmailBody("i sleep everyday at night");
        addEmailBody("I will sleep at night everyday");

        calculateSpamProbability();
    }

    private static void addEmailBody(String emailBody) {
        emailBodies.add(emailBody);// Adds an email body to the list
    }
    // Calculates similarity between two email bodies
    private static double calculateSimilarity(String emailBody1, String emailBody2) {
        Set<String> words1 = new HashSet<> (Arrays.asList(emailBody1.split(" ")));// Create a set of words from the first email
        Set<String> words2 = new HashSet<>(Arrays.asList(emailBody2.split(" ")));// Create a set of words from the second email

        Set<String> commonWords = new HashSet<>(words1);
        commonWords.retainAll(words2);// Find common words between both sets

        return (double) commonWords.size() / (words1.size() + words2.size() - commonWords.size());// Calculate and return the similarity score
    }
    // Calculates spam probability for each email
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