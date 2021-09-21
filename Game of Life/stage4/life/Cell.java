package life;

public enum Cell {
    BLOB('O'), EMPTY(' ');

    private char value;

    public char getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == ' ';
    }

    public boolean isBlob() {
        return value == 'O';
    }

    Cell(char value) {
        this.value = value;
    }

}