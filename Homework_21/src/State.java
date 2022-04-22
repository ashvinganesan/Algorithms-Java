public class State {

    private String name;
    private String code;
    private State[] neighbors;

    public State(String code, String name) {
        this.name = name;
        this.code = code;
    }
    

    public String name() { return this.name; }
    public String code() { return this.code; }
    public State[] neighbors() { return this.neighbors; }

    public void neighbors(State[] neighbors) {
        this.neighbors = neighbors;
    }

    public String toString() {
        return this.name;
    }
}
