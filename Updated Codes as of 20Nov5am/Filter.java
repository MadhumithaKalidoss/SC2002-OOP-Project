public class Filter {
    private boolean includeCampDetails;
    private boolean includeAttendeeDetails;

    // Options for camp details
    private boolean includeCampName;
    private boolean includeStartDate;
    private boolean includeEndDate;


    public Filter() {
        this.includeCampDetails = false;
        this.includeAttendeeDetails = false;
        this.includeCampName = false;
        this.includeStartDate = false;
        this.includeEndDate = false;
    }

    public boolean includeCampDetails() {
        return includeCampDetails;
    }

    public void setIncludeCampDetails(boolean includeCampDetails) {
        this.includeCampDetails = includeCampDetails;
    }

    public boolean includeAttendeeDetails() {
        return includeAttendeeDetails;
    }

    public void setIncludeAttendeeDetails(boolean includeAttendeeDetails) {
        this.includeAttendeeDetails = includeAttendeeDetails;
    }

    // Getters and setters for camp details options
    public boolean includeCampName() {
        return includeCampName;
    }

    public void setIncludeCampName(boolean includeCampName) {
        this.includeCampName = includeCampName;
    }

    public boolean includeStartDate() {
        return includeStartDate;
    }

    public void setIncludeStartDate(boolean includeStartDate) {
        this.includeStartDate = includeStartDate;
    }

    public boolean includeEndDate() {
        return includeEndDate;
    }

    public void setIncludeEndDate(boolean includeEndDate) {
        this.includeEndDate = includeEndDate;
    }
    
}

