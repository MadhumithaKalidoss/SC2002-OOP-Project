import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * The {@code Filter} class provides filtering options for camps and attendees.
 * It allows setting various filters to include or exclude specific details when filtering.
 */
public class Filter {
    private boolean includeCampDetails;
    private boolean includeAttendeeDetails;
    private boolean includeCampName;
    private boolean includeStartDate;
    private boolean includeEndDate;
    private boolean includeAttendeeRole;
    private boolean includeAttendeeUserID;
    private String campNameAlphabetFilter;
    private String roleAlphabetFilter;
    private String attendeeNameFilter;
    private String attendeeNameAlphabetFilter;

    /**
     * Constructs a {@code Filter} object with default filter settings.
     * By default, all filter options are set to exclude details.
     */
    public Filter() {
        this.includeCampDetails = false;
        this.includeAttendeeDetails = false;
        this.includeCampName = false;
        this.includeStartDate = false;
        this.includeEndDate = false;
        this.includeAttendeeRole = false;
        this.includeAttendeeUserID = false;
        this.campNameAlphabetFilter = null;
        this.roleAlphabetFilter = null;
        this.attendeeNameFilter = null;
        this.attendeeNameAlphabetFilter = null;
    }


    /**
     * Sets the camp name alphabet filter for filtering camps by name alphabetically.
     *
     * @param campNameAlphabetFilter The filter for camp names alphabetically.
     */
    public void setCampNameAlphabetFilter(String campNameAlphabetFilter) {
        this.campNameAlphabetFilter = campNameAlphabetFilter;
    }

    /**
     * Sets the role alphabet filter for filtering by role alphabetically.
     *
     * @param roleAlphabetFilter The filter for roles alphabetically.
     */
    public void setRoleAlphabetFilter(String roleAlphabetFilter) {
        this.roleAlphabetFilter = roleAlphabetFilter;
    }

    /**
     * Sets the attendee name filter for filtering attendees by name.
     *
     * @param attendeeNameFilter The filter for attendee names.
     */
    public void setAttendeeNameFilter(String attendeeNameFilter) {
        this.attendeeNameFilter = attendeeNameFilter;
    }

    /**
     * Sets the attendee name alphabet filter for filtering attendees by name alphabetically.
     *
     * @param attendeeNameAlphabetFilter The filter for attendee names alphabetically.
     */
    public void setAttendeeNameAlphabetFilter(String attendeeNameAlphabetFilter) {
        this.attendeeNameAlphabetFilter = attendeeNameAlphabetFilter;
    }

    /**
     * Indicates whether camp details are included in the filter.
     *
     * @return {@code true} if camp details are included, {@code false} otherwise.
     */
    public boolean includeCampDetails() {
        return includeCampDetails;
    }

    /**
     * Sets whether to include camp details in the filter.
     *
     * @param includeCampDetails {@code true} to include camp details, {@code false} otherwise.
     */
    public void setIncludeCampDetails(boolean includeCampDetails) {
        this.includeCampDetails = includeCampDetails;
    }

    /**
     * Indicates whether attendee details are included in the filter.
     *
     * @return {@code true} if attendee details are included, {@code false} otherwise.
     */
    public boolean includeAttendeeDetails() {
        return includeAttendeeDetails;
    }

    /**
     * Sets whether to include attendee details in the filter.
     *
     * @param includeAttendeeDetails {@code true} to include attendee details, {@code false} otherwise.
     */
    public void setIncludeAttendeeDetails(boolean includeAttendeeDetails) {
        this.includeAttendeeDetails = includeAttendeeDetails;
    }


    /**
     * Indicates whether camp name is included in the filter.
     *
     * @return {@code true} if camp name is included, {@code false} otherwise.
     */
    public boolean includeCampName() {
        return includeCampName;
    }

    /**
     * Sets whether to include camp name in the filter.
     *
     * @param includeCampName {@code true} to include camp name, {@code false} otherwise.
     */
    public void setIncludeCampName(boolean includeCampName) {
        this.includeCampName = includeCampName;
    }

    /**
     * Indicates whether start date is included in the filter.
     *
     * @return {@code true} if start date is included, {@code false} otherwise.
     */
    public boolean includeStartDate() {
        return includeStartDate;
    }

    /**
     * Sets whether to include start date in the filter.
     *
     * @param includeStartDate {@code true} to include start date, {@code false} otherwise.
     */
    public void setIncludeStartDate(boolean includeStartDate) {
        this.includeStartDate = includeStartDate;
    }

    /**
     * Indicates whether end date is included in the filter.
     *
     * @return {@code true} if end date is included, {@code false} otherwise.
     */
    public boolean includeEndDate() {
        return includeEndDate;
    }

    /**
     * Sets whether to include end date in the filter.
     *
     * @param includeEndDate {@code true} to include end date, {@code false} otherwise.
     */
    public void setIncludeEndDate(boolean includeEndDate) {
        this.includeEndDate = includeEndDate;
    }

    /**
     * Indicates whether attendee role is included in the filter.
     *
     * @return {@code true} if attendee role is included, {@code false} otherwise.
     */
    public boolean includeAttendeeRole() {
        return includeAttendeeRole;
    }

    /**
     * Sets whether to include attendee role in the filter.
     *
     * @param includeAttendeeRole {@code true} to include attendee role, {@code false} otherwise.
     */
    public void setIncludeAttendeeRole(boolean includeAttendeeRole) {
        this.includeAttendeeRole = includeAttendeeRole;
    }

    /**
     * Sets whether to include attendee user ID in the filter.
     *
     * @param includeAttendeeUserID {@code true} to include attendee user ID, {@code false} otherwise.
     */
    public void setIncludeAttendeeUserID(boolean includeAttendeeUserID) {
        this.includeAttendeeUserID = includeAttendeeUserID;
    }
}
