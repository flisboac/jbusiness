package flisboac.jbusiness.util.navigables;

public enum NavigationReference {

    CURRENT,            // Keeps it on the current place/page.
    FIRST,              // Rewinds to the first element/page.
    LAST,               // Advances to the last element/page.
    NEXT,               // Advances to the next element based on the default increment.
    PREVIOUS,           // Rewinds to a previous element based on the default increment.
    RELATIVE,           // Advances to the next X elements/pages.
    ABSOLUTE;           // Chooses the element by absolute position, starting from the first.
}
