/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ProviderClass;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
/**
 *
 * @author Sean
 */
public class GuiceTester {
   public static void main(String[] args) {
      Injector injector = Guice.createInjector(new TextEditorModule());
      TextEditor editor = injector.getInstance(TextEditor.class);
      editor.makeSpellCheck();
   } 
}
class TextEditor {
   private SpellChecker spellChecker;
   
   @Inject
   public TextEditor( SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }
   public void makeSpellCheck() {
      spellChecker.checkSpelling();
   } 
}

//Binding Module
class TextEditorModule extends AbstractModule {
   @Override
   
   protected void configure() {
      bind(SpellChecker.class).toProvider(SpellCheckerProvider.class);
   } 
}

//spell checker interface
interface SpellChecker {
   public void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

   private String dbUrl;
   private String user;
   private Integer timeout;

   @Inject
   public SpellCheckerImpl(String dbUrl, 
      String user, 
      Integer timeout) {
      this.dbUrl = dbUrl;
      this.user = user;
      this.timeout = timeout;
   } 
   @Override
   public void checkSpelling() { 
      System.out.println("Inside checkSpelling." );
      System.out.println(dbUrl);
      System.out.println(user);
      System.out.println(timeout);
   }
}
class SpellCheckerProvider implements Provider<SpellChecker> {
   @Override
   
   public SpellChecker get() {
      String dbUrl = "jdbc:mysql://localhost:5326/emp";
      String user = "user";
      int timeout = 100;

      SpellChecker SpellChecker = new SpellCheckerImpl(dbUrl, user, timeout);
      return SpellChecker;
   }
}
