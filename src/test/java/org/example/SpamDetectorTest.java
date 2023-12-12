package org.example;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class SpamDetectorTest {
    @Test
    public void testCalculateSimilarityWithNoCommonWords() {
        double similarity = Main.calculateSimilarity("hello world", "goodbye moon");
        assertEquals(0.0, similarity, "The similarity of completely different sentences should be 0");
    }
    @Test
    public void testCalculateSimilarityWithSomeCommonWords() {
        double similarity = Main.calculateSimilarity("hello world", "hello moon");
        assertTrue(similarity > 0.0, "There should be some similarity with common words");
    }
    @Test
    public void testCalculateSimilarityWithSameWords() {
        double similarity = Main.calculateSimilarity("hello world", "hello world");
        assertEquals(1.0, similarity, "The similarity of identical sentences should be 1");
    }
    @Test
    public void testCalculateSimilarityWithEmptyStrings() {
        double similarity = Main.calculateSimilarity("", "");
        assertEquals(1.0, similarity, "The similarity of two empty strings should be 1");
    }
    @Test
    public void testAddEmailBody() {
        Main.addEmailBody("Test email body");
        assertFalse(Main.emailBodies.isEmpty(), "The email list should not be empty after adding an email");
        assertEquals("Test email body", Main.emailBodies.get(0), "The added email body should match what was added");
    }
    @Test
    public void testCalculateSpamProbabilityWithNoEmails() {
        Main.emailBodies.clear();
        Main.calculateSpamProbability();

        assertTrue(true, "Method should handle no emails gracefully");
    }

}
