package edu.iu.habahram.DinerPancakeHouseMerge.model;

public record Customer(String username,
                       String password,
                       String email) {
    public String toLine() {
        return String.format("%1$s,%2$s,%3$s", username(), password(), email());
    }
    public String toLine(String username) {
        return String.format("%1$s,%2$s,%3$s", username, password(), email());
    }

    public static Customer fromLine(String line) {
        String[] tokens = line.split(",");
        return new Customer(tokens[0], tokens[1], tokens[2]);
    }


}

