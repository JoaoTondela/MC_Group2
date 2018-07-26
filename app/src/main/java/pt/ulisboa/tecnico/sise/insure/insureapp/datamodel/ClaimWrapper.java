package pt.ulisboa.tecnico.sise.insure.insureapp.datamodel;

public class ClaimWrapper {

    public int _id;
    public String _title;
    public String _occurrenceDate;
    public String _plate;
    public String _description;

    public ClaimWrapper( int id, String title, String occurrenceDate, String plate, String description ) {
        _id = id;
        _title = title;
        _occurrenceDate = occurrenceDate;
        _plate = plate;
        _description = description;

    }

}
