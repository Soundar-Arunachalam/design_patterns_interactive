// Template method: defines the skeleton; subclasses fill in the steps that vary
public abstract class DataImporter {

    // The fixed pipeline — subclasses cannot reorder it
    public final void importData(String file) {
        parse(file);
        validate();
        save();
        notify();
    }

    protected abstract void parse(String file);  // varies per format
    protected abstract void notify();             // varies per format

    // Common steps — defined once here
    private void validate() { System.out.println("Validating rows..."); }
    private void save()     { System.out.println("Saving to database..."); }
}
