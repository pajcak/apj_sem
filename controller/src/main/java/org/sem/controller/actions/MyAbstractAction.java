package org.sem.controller.actions;

import javax.swing.AbstractAction;
import org.sem.utils.MyAction;

/**
 *
 * @author Skarab
 */
public abstract class MyAbstractAction extends AbstractAction implements MyAction {

    private String menuName;

    public MyAbstractAction(String actionName, String menuName) {
        super(actionName);
        this.menuName = menuName;
    }

    @Override
    public void setEnabled() {
    }

    @Override
    public String getMenuName() {
        return menuName;
    }
}
