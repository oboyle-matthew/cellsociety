package util;

import actions.IAction;
import actions.Nothing;
import org.w3c.dom.Document;

import java.util.HashMap;

/**
 * Class holding all the parameters for
 */
public class Config {
    /**
     * Enum of types of fill
     * Essential on render -> has to be public
     */
    public enum Fill {
        COLOR, SYMBOL, IMAGE;
    }

    private String  DEFAULT_TIME_UNIT = "seconds";
    private double  DEFAULT_STEP = 1.0;

    private boolean DEFAULT_FINITE = false;
    private double  DEFAULT_END_TIME = 1.0;

    // Ability to add several fills in sequence
    private Fill    DEFAULT_FILL[] = {Fill.COLOR};

    private Class<?extends IAction> DEFAULT_ACTION = Nothing.class;


    HashMap<Character, Class<?extends IAction>> cls;

    public Config(Document xml) {

    }

    public Cell fromString(char c) {
        return null;
    }
}
