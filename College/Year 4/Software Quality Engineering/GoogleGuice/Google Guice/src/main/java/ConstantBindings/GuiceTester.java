/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantBindings;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
/**
 *
 * @author Sean
 */
public class GuiceTester {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeConnection();
   } 
}
class TextEditor {
   private String dbUrl;
   
   @Inject
   public TextEditor(@Named("JDBC") String dbUrl) {
      this.dbUrl = dbUrl;
   }
   public void makeConnection() {
      System.out.println(dbUrl);
   } 
}

//Binding Module
class TextEditorModule extends AbstractModule {
   @Override
   
   protected void configure() {
      bind(String.class)
         .annotatedWith(Names.named("JDBC"))
         .toInstance("jdbc:mysql://localhost:5326/emp");
   } 
}
