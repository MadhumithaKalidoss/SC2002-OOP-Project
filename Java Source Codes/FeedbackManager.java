/**
 * The {@code FeedbackManager} abstract class provides a common interface for managing feedback. It defines abstract methods
 * for submitting, deleting, editing, and viewing feedback. Subclasses are expected to implement these methods to handle
 * feedback-specific operations based on the type of feedback they manage.
 *
 * <p>Concrete implementations of this class are intended to be used by various types of feedback, such as suggestions,
 * enquiries, or general feedback. By extending this class, specific feedback managers can define their own logic for
 * handling the feedback operations.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 * {@code
 * // Example of implementing a FeedbackManager for Suggestions
 * public class SuggestionManager extends FeedbackManager {
 *     // Implementation details...
 * }
 *
 * SuggestionManager suggestionManager = new SuggestionManager();
 * suggestionManager.submit(student, campList);
 * suggestionManager.view(student);
 * }
 * </pre>
 *
 * @see Student
 * @see CampList
 */
public abstract class FeedbackManager {
    /**
     * Submits feedback for a specific student and camp.
     *
     * @param student   The student submitting the feedback.
     * @param campList  The list of camps to which the feedback is related.
     */
    // Submitting feedback
    public abstract void submit(Student student, CampList campList);

    /**
     * Deletes feedback for a specific student.
     *
     * @param student The student whose feedback is to be deleted.
     */
    // Deleting feedback
    public abstract void delete(Student student);

    /**
     * Edits feedback for a specific student.
     *
     * @param student The student whose feedback is to be edited.
     */
    // Editing feedback
    public abstract void edit(Student student);

    /**
     * Views feedback for a specific student.
     *
     * @param student The student for whom the feedback is to be viewed.
     */
    // Viewing feedback
    public abstract void view(Student student);
}
