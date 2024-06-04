package homework3;

public class Entry implements Comparable<Entry>{
    private String surname;
    private String name;
    private String streetAddress;
    private String city;
    private String postcode;
    private String country;
    private String phoneNumber;

    public Entry(String fullName, String streetAddress, String city, String postcode, String country, String phoneNumber) {

        String[] names = fullName.split(", ");

        this.surname = names[0];

        this.name = names.length > 1 ? names[1] : "";

        this.streetAddress = streetAddress;

        this.city = city;

        this.postcode = postcode;

        this.country = country;

        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public int compareTo(Entry other) {

        int surnameCompare = this.surname.compareTo(other.surname);
        if (surnameCompare != 0) {
            return surnameCompare;

        } else {
            return this.name.compareTo(other.name);
        }
    }
}
