package de.oth.hsp.common.view;

public interface IPageController {

    public void outEvent();

    public void inEvent();

    /**
     * This method returns true, if the input in the current page is correct.
     * This method should be called in the outEvent Method
     * 
     * @return
     */
    public boolean checkInput();
}
