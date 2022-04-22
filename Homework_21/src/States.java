public final class States {
    
    public final static State Alabama        = new State ("AL", "Alabama");
    public final static State Alaska         = new State ("AK", "Alaska");
    public final static State Arizona        = new State ("AZ", "Arizona");
    public final static State Arkansas       = new State ("AR", "Arkansas");
    public final static State California     = new State ("CA", "California");
    public final static State Colorado       = new State ("CO", "Colorado");
    public final static State Connecticut    = new State ("CT", "Connecticut");
    public final static State Delaware       = new State ("DE", "Delaware");
    public final static State DistOfColumbia = new State ("DC", "District of Columbia");
    public final static State Florida        = new State ("FL", "Florida");
    public final static State Georgia        = new State ("GA", "Georgia");
    public final static State Hawaii         = new State ("HI", "Hawaii");
    public final static State Idaho          = new State ("ID", "Idaho");
    public final static State Illinois       = new State ("IL", "Illinois");
    public final static State Indiana        = new State ("IN", "Indiana");
    public final static State Iowa           = new State ("IA", "Iowa");
    public final static State Kansas         = new State ("KS", "Kansas");
    public final static State Kentucky       = new State ("KY", "Kentucky");
    public final static State Louisiana      = new State ("LA", "Louisiana");
    public final static State Maine          = new State ("ME", "Maine");
    public final static State Maryland       = new State ("MD", "Maryland");
    public final static State Massachusettes = new State ("MA", "Massachusettes");
    public final static State Michigan       = new State ("MI", "Michigan");
    public final static State Minnesota      = new State ("MN", "Minnesota");
    public final static State Mississippi    = new State ("MS", "Mississippi");
    public final static State Missouri       = new State ("MO", "Missouri");
    public final static State Montana        = new State ("MT", "Montana");
    public final static State Nebraska       = new State ("NE", "Nebraska");
    public final static State Nevada         = new State ("NV", "Nevada");
    public final static State NewHampshire   = new State ("NH", "New Hampshire");
    public final static State NewJersey      = new State ("NJ", "New Jersey");
    public final static State NewMexico      = new State ("NM", "New Mexico");
    public final static State NewYork        = new State ("NY", "New York");
    public final static State NorthCarolina  = new State ("NC", "North Carolina");
    public final static State NorthDakota    = new State ("ND", "North Dakota");
    public final static State Ohio           = new State ("OH", "Ohio");
    public final static State Oklahoma       = new State ("OK", "Oklahoma");
    public final static State Oregon         = new State ("OR", "Oregon");
    public final static State Pennsylvania   = new State ("PA", "Pennsylvania");
    public final static State RhodeIsland    = new State ("RI", "Rhode Island");
    public final static State SouthCarolina  = new State ("SC", "South Carolina");
    public final static State SouthDakota    = new State ("SD", "South Dakota");
    public final static State Tennessee      = new State ("TN", "Tennessee");
    public final static State Texas          = new State ("TX", "Texas");
    public final static State Utah           = new State ("UT", "Utah");
    public final static State Vermont        = new State ("VT", "Vermont");
    public final static State Virginia       = new State ("VA", "Virginia");
    public final static State Washington     = new State ("WA", "Washington");
    public final static State WestVirginia   = new State ("WV", "West Virginia");
    public final static State Wisconsin      = new State ("WI", "Wisconsin");
    public final static State Wyoming        = new State ("WY", "Wyoming");

    // List of all the states.

    public final static State[] states = {
        Alabama,        Alaska,      Arizona,       Arkansas,     California,
        Colorado,       Connecticut, Delaware,      Florida,      Georgia,
        Hawaii,         Idaho,       Illinois,      Indiana,      Iowa,
        Kansas,         Kentucky,    Louisiana,     Maine,        Maryland,
        Massachusettes, Michigan,    Minnesota,     Mississippi,  Missouri,
        Montana,        Nebraska,    Nevada,        NewHampshire, NewJersey,
        NewMexico,      NewYork,     NorthCarolina, NorthDakota,  Ohio,
        Oklahoma,       Oregon,      Pennsylvania,  RhodeIsland,  SouthCarolina,
        SouthDakota,    Tennessee,   Texas,         Utah,         Vermont,
        Virginia,       Washington,  WestVirginia,  Wisconsin,    Wyoming,
        DistOfColumbia
    };

    public final static State[] continentalStates = {
        Alabama,                     Arizona,       Arkansas,     California,
        Colorado,       Connecticut, Delaware,      Florida,      Georgia,
                        Idaho,       Illinois,      Indiana,      Iowa,
        Kansas,         Kentucky,    Louisiana,     Maine,        Maryland,
        Massachusettes, Michigan,    Minnesota,     Mississippi,  Missouri,
        Montana,        Nebraska,    Nevada,        NewHampshire, NewJersey,
        NewMexico,      NewYork,     NorthCarolina, NorthDakota,  Ohio,
        Oklahoma,       Oregon,      Pennsylvania,  RhodeIsland,  SouthCarolina,
        SouthDakota,    Tennessee,   Texas,         Utah,         Vermont,
        Virginia,       Washington,  WestVirginia,  Wisconsin,    Wyoming,
        DistOfColumbia
    };

    // Abbreviations for the states.

    public final static State AL = Alabama;
    public final static State AK = Alaska;
    public final static State AZ = Arizona;
    public final static State AR = Arkansas;
    public final static State CA = California;
    public final static State CO = Colorado;
    public final static State CT = Connecticut;
    public final static State DE = Delaware;
    public final static State DC = DistOfColumbia;
    public final static State FL = Florida;
    public final static State GA = Georgia;
    public final static State HI = Hawaii;
    public final static State ID = Idaho;
    public final static State IL = Illinois;
    public final static State IN = Indiana;
    public final static State IA = Iowa;
    public final static State KS = Kansas;
    public final static State KY = Kentucky;
    public final static State LA = Louisiana;
    public final static State ME = Maine;
    public final static State MD = Maryland;
    public final static State MA = Massachusettes;
    public final static State MI = Michigan;
    public final static State MN = Minnesota;
    public final static State MS = Mississippi;
    public final static State MO = Missouri;
    public final static State MT = Montana;
    public final static State NE = Nebraska;
    public final static State NV = Nevada;
    public final static State NH = NewHampshire;
    public final static State NJ = NewJersey;
    public final static State NM = NewMexico;
    public final static State NY = NewYork;
    public final static State NC = NorthCarolina;
    public final static State ND = NorthDakota;
    public final static State OH = Ohio;
    public final static State OK = Oklahoma;
    public final static State OR = Oregon;
    public final static State PA = Pennsylvania;
    public final static State RI = RhodeIsland;
    public final static State SC = SouthCarolina;
    public final static State SD = SouthDakota;
    public final static State TN = Tennessee;
    public final static State TX = Texas;
    public final static State UT = Utah;
    public final static State VT = Vermont;
    public final static State VA = Virginia;
    public final static State WA = Washington;
    public final static State WV = WestVirginia;
    public final static State WI = Wisconsin;
    public final static State WY = Wyoming;

    // Set up the neighbors for each state.

    static {
        Alabama.neighbors(new State[] {
            Mississippi,
            Tennessee,
            Georgia,
            Florida
        });

        Alaska.neighbors(new State[] {
        });

        Arizona.neighbors(new State[] {
            California,
            Nevada,
            Utah,
            NewMexico
        });

        Arkansas.neighbors(new State[] {
            Texas,
            Oklahoma,
            Missouri,
            Tennessee,
            Mississippi,
            Louisiana,
        });

        California.neighbors(new State[] {
            Oregon,
            Nevada,
            Arizona
        });

        Colorado.neighbors(new State[] {
            Utah,
            Wyoming,
            Nebraska,
            Kansas,
            Oklahoma,
            NewMexico
        });

        Connecticut.neighbors(new State[] {
            NewYork,
            Massachusettes,
            RhodeIsland
        });

        Delaware.neighbors(new State[] {
            Maryland,
            Pennsylvania,
            NewJersey
        });

        DistOfColumbia.neighbors(new State[] {
            Maryland,
            Virginia
        });

        Florida.neighbors(new State[] {
            Alabama,
            Georgia
        });

    
        Georgia.neighbors(new State[] { 
             Alabama,
            Tennessee,
            NorthCarolina,
            SouthCarolina,
            Florida
        });

        Hawaii.neighbors(new State[] { 
        });

        Idaho.neighbors(new State[] {
            Oregon,
            Washington,
            Montana,
            Wyoming,
            Utah,
            Nevada
        });

        Illinois.neighbors(new State[] {
            Missouri,
            Iowa,
            Wisconsin,
            Indiana,
            Kentucky
        });

        Indiana.neighbors(new State[] {
            Illinois,
            Michigan,
            Ohio,
            Kentucky
        });

        Iowa.neighbors(new State[] {
            Nebraska,
            SouthDakota,
            Minnesota,
            Wisconsin,
            Illinois,
            Missouri
        });

        Kansas.neighbors(new State[] {
            Colorado,
            Nebraska,
            Missouri,
            Oklahoma
        });

        Kentucky.neighbors(new State[] {
            Missouri,
            Illinois,
            Indiana,
            Ohio,
            WestVirginia,
            Virginia,
            Tennessee
        });

        Louisiana.neighbors(new State[] {
            Texas,
            Arkansas,
            Mississippi
        });

        Maine.neighbors(new State[] {
            NewHampshire
        });

        Maryland.neighbors(new State[] {
            WestVirginia,
            Pennsylvania,
            Delaware,
            DistOfColumbia,
            Virginia
        });

        Massachusettes.neighbors(new State[] {
            NewYork,
            Vermont,
            NewHampshire,
            RhodeIsland,
            Connecticut
        });

        Michigan.neighbors(new State[] {
            Wisconsin,
            Ohio,
            Indiana
        });

        Minnesota.neighbors(new State[] {
            SouthDakota,
            NorthDakota,
            Wisconsin,
            Iowa
        });

        Mississippi.neighbors(new State[] {
            Louisiana,
            Arkansas,
            Tennessee,
            Alabama
        });

        Missouri.neighbors(new State[] {
            Oklahoma,
            Kansas,
            Nebraska,
            Iowa,
            Illinois,
            Kentucky,
            Tennessee,
            Arkansas
        });

        Montana.neighbors(new State[] {
            Idaho,
            NorthDakota,
            SouthDakota,
            Wyoming
        });

        Nebraska.neighbors(new State[] {
            Colorado,
            Wyoming,
            SouthDakota,
            Iowa,
            Missouri,
            Kansas
        });

        Nevada.neighbors(new State[] {
            California,
            Oregon,
            Idaho,
            Utah,
            Arizona
        });

        NewHampshire.neighbors(new State[] {
            Vermont,
            Maine,
            Massachusettes
        });

        NewJersey.neighbors(new State[] {
            Pennsylvania,
            NewYork,
            Delaware
        });

        NewMexico.neighbors(new State[] {
            Arizona,
            Colorado,
            Oklahoma,
            Texas
        });

        NewYork.neighbors(new State[] {
            Vermont,
            NewJersey,
            Pennsylvania,
            Connecticut,
            Massachusettes
        });

        NorthCarolina.neighbors(new State[] {
            Tennessee,
            Virginia,
            SouthCarolina,
            Georgia
        });

        NorthDakota.neighbors(new State[] {
            Montana,
            Minnesota,
            SouthDakota
        });

        Ohio.neighbors(new State[] {
            Indiana,
            Michigan,
            WestVirginia,
            Pennsylvania,
            Kentucky
        });

        Oklahoma.neighbors(new State[] {
            NewMexico,
            Colorado,
            Kansas,
            Missouri,
            Arkansas,
            Texas
        });

        Oregon.neighbors(new State[] {
            Washington,
            Idaho,
            Nevada,
            California
        });

        Pennsylvania.neighbors(new State[] {
            WestVirginia,
            Ohio,
            NewYork,
            NewJersey,
            Delaware,
            Maryland
        });

        RhodeIsland.neighbors(new State[] {
            Connecticut,
            Massachusettes
        });

        SouthCarolina.neighbors(new State[] {
            Georgia,
            NorthCarolina
        });

        SouthDakota.neighbors(new State[] {
            Wyoming,
            Montana,
            NorthDakota,
            Minnesota,
            Iowa,
            Nebraska
        });

        Tennessee.neighbors(new State[] {
            Arkansas,
            Missouri,
            Kentucky,
            Virginia,
            NorthCarolina,
            Georgia,
            Alabama,
            Mississippi
        });

        Texas.neighbors(new State[] {
            NewMexico,
            Oklahoma,
            Arkansas,
            Louisiana
        });

        Utah.neighbors(new State[] {
            Nevada,
            Idaho,
            Wyoming,
            Colorado,
            Arizona
        });

        Vermont.neighbors(new State[] {
            NewYork,
            NewHampshire,
            Massachusettes
        });

        Virginia.neighbors(new State[] {
            Kentucky,
            WestVirginia,
            Maryland,
            DistOfColumbia,
            NorthCarolina,
            Tennessee
        });

        Washington.neighbors(new State[] {
            Idaho,
            Oregon
        });

        WestVirginia.neighbors(new State[] {
            Kentucky,
            Ohio,
            Pennsylvania,
            Maryland,
            Virginia
        });

        Wisconsin.neighbors(new State[] {
            Iowa,
            Minnesota,
            Michigan,
            Illinois
        });

        Wyoming.neighbors(new State[] {
            Utah,
            Idaho,
            Montana,
            SouthDakota,
            Nebraska,
            Colorado
        });
    }


    public static State find(String name) {
        for (State state : states) {
            if (state.name().equals(name) || state.code().equals(name)) return state;
        }
        return null;
    }
}
