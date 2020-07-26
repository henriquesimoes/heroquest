package br.unicamp.ic.mc322.heroquest.walker.skills;

/**
 * Defines the way a list of targets is shown to the user.
 */
public enum DisplayTargetsMode {
    /**
     * List all options
     */
    SHOW_OPTIONS,
    /**
     * Custom way to select a map coordinate, to avoid an extensive coordinate listing.
     */
    GET_COORDINATE
}
