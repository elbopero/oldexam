public class TableTennisRacket extends Equipment{

    private boolean needsShifting;

    public TableTennisRacket(int id, String locker, boolean needsReplacement, boolean needsShifting) {
        super(id, locker, needsReplacement);
        this.needsShifting = needsShifting;
    }

    public boolean isNeedsShifting() {
        return needsShifting;
    }

    public void setNeedsShifting(boolean needsShifting) {
        this.needsShifting = needsShifting;
    }

    @Override
    public String toString() {
        return "TableTennisRacket id: " + getId() + ", Location: " + getLocker()
                + ", Needs replacement: " + isNeedsReplacement() + ", Needs shifting: " + needsShifting ;
    }
}
