package de.oth.hsp.common.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class Tab1Controller {

    // References for the FXML layout
	
    @FXML
    private Pagination myPagination;
  
    
   

    private RootLayoutController root;
    
    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Tab1Controller() {

    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
//    	myPagination.setPrefHeight(root.getTabPane().getHeight());
    	
    	//myPagination.setPrefSize(myPagination.getParent().getScene().getWidth(), myPagination.getParent().getScene().getHeight());
//    	System.out.println(myPagination.getCurrentPageIndex());
    	
    	myPagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
            	System.out.println(myPagination.getCurrentPageIndex());
                return createPage(pageIndex);
            }
        });
    	
    	
    	
    }
    
    public AnchorPane createPage(int index) {
    	AnchorPane myPane = new AnchorPane();
    	AnchorPane myPagePane1 = new AnchorPane();
    	FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource(
				"Page1.fxml"));
    	try {
			myPagePane1 = fxmlLoader1.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	AnchorPane myPagePane2 = new AnchorPane();
    	FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource(
				"Page2.fxml"));
    	try {
			myPagePane2 = fxmlLoader2.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	AnchorPane myPagePane3 = new AnchorPane();
    	AnchorPane myPagePane4 = new AnchorPane();
//    	FXMLLoader fxmlLoader = null;
    	
    	switch (index) {
		case 0:
			 myPane =  myPagePane1;
			break;

		case 1:
			myPane =  myPagePane2;
			break;
			
		default:
			break;
		}
    	return myPane;  
    	
    	
    	 
    	 
    	 
//    	if(index==0) {
//    		System.out.println("yay");
//    		
//    		
//    		 try {
//				myPane = fxmlLoader.load();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 
//    		 System.out.println(root.getTabPane().getHeight());
//    		 
////		 myPane.setPrefHeight(root.getTabPane().getHeight() - 80);
////    		 
////    		 myPane.setPrefWidth(myPagination.getScene().getWidth());
//    		 
//    		 return myPane;
//    	   
//			
//			
//    	}
//    	return new AnchorPane();
    }
    
    
	/**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
    	root = rootLayoutController;
    }


    
}
