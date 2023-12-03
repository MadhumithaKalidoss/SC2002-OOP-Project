import java.util.ArrayList;
/**
 * The {@code FeedbackList} interface outlines the common operations for managing feedback in a generic list. It defines
 * methods for adding, removing, updating, and retrieving feedback. The interface is designed to be generic, allowing
 * implementations for different types of feedback, such as suggestions, enquiries, or general feedback.
 *
 * <p>Implementing classes are expected to provide specific functionality for the generic operations based on the type
 * of feedback they manage.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * // Example of implementing the FeedbackList interface for Suggestions
 * public class SuggestionListImpl implements FeedbackList<Suggestion> {
 *     // Implementation details...
 * }
 *
 * SuggestionListImpl suggestionList = new SuggestionListImpl();
 * suggestionList.add("userID123", "CampNameA", "Provide more workshops");
 * suggestionList.add("userID456", "CampNameB", "Improve communication during the camp");
 * }
 * </pre>
 *
 * @param <T> The type of feedback (e.g., {@link Suggestion}, {@link Enquiry}) that the list will manage.
 */

public interface FeedbackList<T> {
    /**
     * Adds feedback to the list with the specified user ID, camp name, and content.
     *
     * @param userID    The user ID associated with the feedback.
     * @param campName  The name of the camp associated with the feedback.
     * @param content   The content of the feedback.
     */
    // Add feedback to the list
    void add(String userID, String campName, String content);

    /**
     * Removes the specified feedback from the list.
     *
     * @param feedback The feedback object to be removed.
     */
    // Remove feedback from the list
    void remove(T feedback);

    /**
     * Updates the content of the specified feedback with new content.
     *
     * @param feedback    The feedback object to be updated.
     * @param newContent  The new content to replace the existing content.
     */
    // Update feedback content
    void update(T feedback, String newContent);

    /**
     * Adds an answer to the specified feedback.
     *
     * @param feedback The feedback object to which the answer will be added.
     * @param answer   The answer to be added to the feedback.
     */
    // Add answer to feedback
    void addAnswer(T feedback, String answer);

    /**
     * Retrieves the list of feedback objects.
     *
     * @return An {@link ArrayList} containing the feedback objects.
     */
    // Get the list of feedback
    ArrayList<T> getList();

    /**
     * Checks if the feedback list is empty.
     *
     * @return {@code true} if the feedback list is empty, {@code false} otherwise.
     */
    // Check if the feedback list is empty
    boolean isEmpty();
}