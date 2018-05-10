package testing;

import entities.Address;
import entities.users.User;

import java.sql.Timestamp;
import java.util.Random;

public class UserFactory {

    private final String[] firstNames = { "Adam", "Alex", "Aaron",
            "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter"};

    private final String[] lastNames = { "Anderson", "Ashwoon",
            "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon",
            "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar", "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel"};

    private final String[] cities = {"Aberdeen", "Abilene", "Akron", "Albany",
            "Albuquerque", "Alexandria", "Allentown", "Amarillo", "Anaheim", "Anchorage", "Ann Arbor", "Antioch", "Apple Valley", "Appleton", "Arlington", "Arvada", "Asheville", "Athens", "Atlanta", "Atlantic City", "Augusta", "Aurora", "Austin", "Bakersfield", "Baltimore", "Barnstable", "Baton Rouge", "Beaumont", "Bel Air", "Bellevue", "Berkeley", "Bethlehem", "Billings", "Birmingham", "Bloomington", "Boise", "Boise City", "Bonita Springs", "Boston", "Boulder", "Bradenton", "Bremerton", "Bridgeport", "Brighton", "Brownsville", "Bryan", "Buffalo", "Burbank", "Burlington", "Cambridge", "Canton", "Cape Coral", "Carrollton", "Cary", "Cathedral City", "Cedar Rapids", "Champaign", "Chandler", "Charleston", "Charlotte", "Chattanooga", "Chesapeake", "Chicago", "Chula Vista", "Cincinnati", "Clarke County", "Clarksville", "Clearwater", "Cleveland", "College Station", "Colorado Springs"};

    private final String[] states = {"California,", "Alabama,", "Arkansas,", "Arizona,", "Alaska,", "Colorado,", "Connecticut,", "Delaware,", "Florida,", "Georgia,", "Hawaii,", "Idaho,", "Illinois,", "Indiana,", "Iowa,", "Kansas,", "Kentucky,", "Louisiana,", "Maine,", "Maryland,", "Massachusetts,", "Michigan,", "Minnesota,", "Mississippi,", "Missouri,", "Montana,", "Nebraska,", "Nevada,", "New Hampshire,", "New Jersey,", "New Mexico,", "New York,", "North Carolina,", "North Dakota,", "Ohio,", "Oklahoma,", "Oregon,", "Pennsylvania,", "Rhode Island,", "South Carolina,", "South Dakota,", "Tennessee,", "Texas,", "Utah,", "Vermont,", "Virginia,", "Washington,", "West Virginia,", "Wisconsin,", "Wyoming" };

    private final String[] countries = {"Afghanistan","Albania","Algeria",
            "Andorra","Angola","Anguilla","Antigua","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia","Botswana","Brazil","Virgin Islands","Brunei"};

    private final String[] emails = {"dartlife@sbcglobal.net",
            "goresky@yahoo.ca",
            "pemungkah@aol.com",
            "parkes@me.com",
            "jgmyers@verizon.net",
            "mlewan@msn.com",
            "pakaste@outlook.com",
            "eidac@yahoo.ca",
            "moxfulder@live.com",
            "pavel@yahoo.ca",
            "donev@mac.com",
            "denton@sbcglobal.net",
            "notaprguy@gmail.com",
            "malattia@aol.com",
            "hachi@yahoo.ca",
            "subir@sbcglobal.net",
            "crimsane@att.net",
            "jgoerzen@yahoo.com",
            "cfhsoft@optonline.net",
            "conteb@gmail.com",
            "calin@gmail.com",
            "donev@mac.com",
            "sumdumass@msn.com",
            "rattenbt@sbcglobal.net",
            "dawnsong@aol.com",
            "vmalik@aol.com",
            "johnbob@optonline.net",
            "lbecchi@hotmail.com",
            "afifi@me.com",
            "sartak@optonline.net",
            "aschmitz@aol.com",
            "weidai@msn.com",
            "jdhildeb@hotmail.com",
            "specprog@icloud.com",
            "dbrobins@msn.com",
            "rsmartin@verizon.net",
            "kildjean@gmail.com",
            "tmccarth@msn.com",
            "notaprguy@gmail.com",
            "mwandel@hotmail.com",
            "bmidd@me.com",
            "wkrebs@sbcglobal.net",
            "psharpe@icloud.com",
            "graham@gmail.com",
            "scitext@icloud.com",
            "jguyer@mac.com",
            "lstein@outlook.com",
            "dalamb@verizon.net",
            "mhanoh@sbcglobal.net",
            "nimaclea@mac.com",
            "arebenti@att.net",
            "alias@att.net",
            "nanop@msn.com",
            "dsowsy@optonline.net",
            "scato@comcast.net",
            "retoh@yahoo.com",
            "stern@me.com",
            "malvar@hotmail.com",
            "isorashi@att.net",
            "themer@att.net",
            "jespley@me.com",
            "kingma@aol.com",
            "richard@gmail.com",
            "flakeg@yahoo.ca",
            "gslondon@mac.com",
            "rhialto@comcast.net",
            "seanq@mac.com",
            "sopwith@msn.com",
            "bmidd@comcast.net",
            "karasik@msn.com"};

    private String getRandomStringFromArray(String[] array){
        int i = new Random().nextInt(array.length);
        return array[i];
    }

    public User getRandomUser(){
        User user = new User();
        user.setUserEmail(getRandomStringFromArray(emails));
        user.setUserPassword(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
        user.setUserEmailVerified(new Random().nextBoolean());
        user.setUserFax(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
        user.setUserFirstName(getRandomStringFromArray(firstNames));
        user.setUserLastName(getRandomStringFromArray(lastNames));
        user.setUserIp(String.valueOf(new Random().nextInt(255)) + "." + String.valueOf(new Random().nextInt(255)) + "." + String.valueOf(new Random().nextInt(255)) + "." + String.valueOf(new Random().nextInt(255)));
        user.setUserPhone("+1" + String.valueOf(new Random().nextInt(Integer
                .MAX_VALUE)));
        user.setUserVerificationCode(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
        user.setUserRegistrationDate(makeDate());
        user.setAddress(makeAddress());
        return user;
    }

    private Address makeAddress(){
        Address address = new Address();
        address.setCity((getRandomStringFromArray(cities)));
        address.setState(getRandomStringFromArray(states));
        address.setCountry(getRandomStringFromArray(countries));
        address.setZip(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
        address.setAddress(address.getCity() + " " + address.getState());
        return address;
    }

    private Timestamp makeDate(){
        long offset = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
}
