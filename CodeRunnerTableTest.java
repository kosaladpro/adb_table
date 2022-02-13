public class CodeRunnerTableTest {

    public static void main(String[] args) {
        CodeRunnerTableTest tst = new CodeRunnerTableTest();

        if (tst.testEquiJoin()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Equi Join\" method is incomplete or wrong.");
        }

        if (tst.testProject()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Project\" method is incomplete or wrong.");
        }

        if (tst.testSelect()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Select\" method is incomplete or wrong.");
        }

        if (tst.testUnion()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Union\" method is incomplete or wrong.");
        }

        if (tst.testMinus()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Minus\" method is incomplete or wrong.");
        }

        if (tst.testNaturalJoin()) {
            System.out.println("true");
        } else {
            System.out.println("Your \"Natural Join\" method is incomplete or wrong.");
        }
    }

    /**
     * Generates a movie table for testing
     *
     * @return a sample movie table
     */
    public Table createMovieTable() {
        Table movie = new Table("movie", "title year length genre studioName producerNo",
                "String Integer Integer String String Integer", "title year");
        Comparable[] film1 = {"Star_Wars", 1977, 124, "sciFi", "Fox", 12345};
        Comparable[] film2 = {"Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345};
        Comparable[] film3 = {"Rocky", 1985, 200, "action", "Universal", 12125};
        Comparable[] film4 = {"Rambo", 1978, 100, "action", "Universal", 32355};
        movie.insert(film1);
        movie.insert(film2);
        movie.insert(film3);
        movie.insert(film4);
        return movie;
    }

    /**
     * Generates a producer table for testing
     *
     * @return a sample producer table
     */
    public Table createProducerTable() {
        Table prod = new Table("producer", "producerNo year producerName",
                "Integer Integer String", "producerNo");
        Comparable[] prod1 = {12345, 1977, "Producer_1"};
        Comparable[] prod2 = {12125, 1985, "Producer_2"};
        Comparable[] prod3 = {32356, 1978, "Producer_3"};
        prod.insert(prod1);
        prod.insert(prod2);
        prod.insert(prod3);
        return prod;
    }

    /**
     * Generates a studio table for testing
     *
     * @return a sample studio table
     */
    public Table createStudioTable() {
        Table studio = new Table("studio", "name address presNo",
                "String String Integer", "name");
        Comparable[] studio1 = {"Fox", "Los_Angeles", 7777};
        Comparable[] studio2 = {"Universal", "Universal_City", 8888};
        Comparable[] studio3 = {"DreamWorks", "Universal_City", 9999};
        studio.insert(studio1);
        studio.insert(studio2);
        studio.insert(studio3);
        return studio;
    }

    /**
     * Tests the equi join method.
     */
    public boolean testEquiJoin() {
        System.out.println("\n\ntestEquiJoin\n************************************************************************************************************************\n");
        Table movie = this.createMovieTable();
        Table studio = this.createStudioTable();
        Table eJoin = movie.equiJoin("studioName", "name", studio);

        Comparable studioName = eJoin.tuples.get(0)[eJoin.col("studioName")];
        Comparable name = eJoin.tuples.get(0)[eJoin.col("name")];

        System.out.println(movie);
        System.out.println(studio);
        System.out.println(eJoin);

        return studioName.equals(name);
    }

    /**
     * Tests the project method.
     */
    public boolean testProject() {
        System.out.println("\n\ntestProject\n************************************************************************************************************************\n");
//      Table movie = new Table("movie", "title year length genre studioName producerNo",
//      "String Integer Integer String String Integer", "title year");
        Table movie = this.createMovieTable();
        Table proj1 = movie.project("title year");
        System.out.println(proj1);

        Table proj2 = movie.project("studioName");
        System.out.println(proj2);
        return true;
    }

    /**
     * Tests the select method.
     */
    public boolean testSelect() {
        System.out.println("\n\ntestSelect\n************************************************************************************************************************\n");
//      Table movie = new Table("movie", "title year length genre studioName producerNo",
//      "String Integer Integer String String Integer", "title year");
        Table movie = this.createMovieTable();
        KeyType keyVal = new KeyType("Star_Wars", 1977);
        Table selection = movie.select(keyVal);

        if (!(selection.tuples.size() == 1)) return false;
        if (!selection.tuples.get(0)[selection.col("title")].equals("Star_Wars")) return false;
        if (!selection.tuples.get(0)[selection.col("year")].equals(1977)) return false;
        if (!selection.tuples.get(0)[selection.col("length")].equals(124)) return false;

        System.out.println(selection.toString());

        return true;
    }

    /**
     * Tests the union method.
     */
    public boolean testUnion() {
        System.out.println("\n\ntestUnion\n************************************************************************************************************************\n");
        Table movie = this.createMovieTable();
        Table movie2 = this.createMovieTable();

        Table union1 = movie.union(movie2);

        System.out.println("movie\n" + movie);
        System.out.println("union1\n" + union1);

        return true;
    }

    /**
     * Tests the minus method.
     */
    public boolean testMinus() {
        System.out.println("\n\ntestMinus\n************************************************************************************************************************\n");

        Table movie = this.createMovieTable();
        Table movie2 = this.createMovieTable();

        Table minus1 = movie.minus(movie2);

        System.out.println("movie\n" + movie);
        System.out.println("minus1\n" + minus1);

        return true;
    }

    /**
     * Tests the natural join method.
     */
    public boolean testNaturalJoin() {
        System.out.println("\n\ntestNaturalJoin\n************************************************************************************************************************\n");
        Table movie = this.createMovieTable();
        Table studio = this.createStudioTable();
        Table producer = this.createProducerTable();
        

        System.out.println(movie);
        System.out.println(studio);
        System.out.println(producer);

        Table join1 = movie.naturalJoin(studio);
        System.out.println(join1);

        Table join2 = movie.naturalJoin(producer);
        System.out.println(join2);

        return true;
    }
}
