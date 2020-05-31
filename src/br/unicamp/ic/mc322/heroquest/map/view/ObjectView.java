package br.unicamp.ic.mc322.heroquest.map.view;

public class ObjectView {
    private String representation;

    public ObjectView(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
