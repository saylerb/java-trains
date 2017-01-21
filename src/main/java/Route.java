public class Route {
  private Character start;
  private Character end;
  private Integer limit;

  public Route(Character start, Character end, Integer limit) {
    this.start = start;
    this.end = end;
    this.limit = limit;
  }

  public Character getStart() {
    return this.start;
  }

  public Character getEnd() {
    return this.end;
  }

  public Integer getLimit() {
    return this.limit;
  }
}
