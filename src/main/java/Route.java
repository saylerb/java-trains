public class Route {
    private Character end;
    private Integer limit;

    public Route(Character start, Character end, Integer limit) {
        this.end = end;
        this.limit = limit;
    }

    public Character getEnd() {
        return this.end;
    }

    public Integer getLimit() {
        return this.limit;
    }

}
