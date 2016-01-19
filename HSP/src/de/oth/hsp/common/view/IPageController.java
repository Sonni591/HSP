package de.oth.hsp.common.view;

public interface IPageController {

    /**
     * does handle certain actions, when the current page is left; implemented
     * in the pageIndex-Listner
     */
    public void outEvent();

    /**
     * does handle certain actions, when the current page is entered;
     * implemented in the pageIndex-Listner
     */
    public void inEvent();

    /**
     * This method returns true, if the input in the current page is correct.
     * This method should be called in the outEvent Method
     * 
     * @return
     */
    public boolean checkInput();
}
