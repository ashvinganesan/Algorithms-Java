// Ashvin Ganesan
// Algorithms-H
// Mr. Paige
// HW #4
// Tuesday September 15th 2020
public class State {

    private String name;
    private String code;
    private City capital;
    private State[] neighbors;

    public State(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String name() { return this.name; }
    public String code() { return this.code; }
    public City capital() { return this.capital; }
    public State[] neighbors() { return this.neighbors; }

    public void capital(City capital) {
        this.capital = capital;
    }

    public void neighbors(State[] neighbors) {
        this.neighbors = neighbors;
    }
@Override
    public String toString() {
        return this.name;
    }
}
