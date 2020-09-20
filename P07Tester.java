//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Tester
// Files: ShallowFileIterator.java, DeepFileIterator.java,
// FilteredFileIterator.java, P07Tester.java
// Course: CS 300, Spring 2020
//
// Author: Matt Thompson
// Email: mathompson23@wisc.edu
// Lecturer's Name: Hobbes LeGault
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements test methods to check the correctness of the ShallowFileIterator,
 * DeepFileIterator, and FilteredFileIterator classes defined in the CS300 Spring 2020 - P07 File
 * Finder programming assignment.
 * 
 * @author Matt Thompson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class P07Tester {
  /**
   * This method checks the correctness of the ShallowFileIterator class. Starts by creating a
   * ShallowFileIterator called shallowIT, checking the folder parameter. Then, appends the name of
   * the file to a String results. Finally checks if the results matches String expectedResults.
   * 
   * @param folder The folder to be iterated through
   * @return true when results matches expectedResults, false otherwise
   */
  public static boolean testShallowFileIterator(File folder) {
    String expectedResults =
        "assignments, exam preparation, lecture notes, " + "reading notes, todo.txt, ";
    String results = "";

    // Retrieves all file's names and compares it to expectedResults. If FileNotFoundException or
    // NoSuchElementException is caught, returns false.
    try {
      ShallowFileIterator shallowIT = new ShallowFileIterator(folder);

      while (shallowIT.hasNext()) {
        results += shallowIT.next().getName() + ", ";
      }

      if (!expectedResults.equals(results)) {
        System.out.println("Failed: Result not same as expected.");
        return false;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("No Such Element");
      return false;
    }

    return true;
  }

  /**
   * This method checks the correctness of the DeepFileIterator class. Starts by creating a
   * DeepFileIterator called deepIT, checking the folder parameter. Then, appends the name of the
   * file to a String results. Finally checks if the results matches String expectedResults.
   * 
   * @param folder The folder to be iterated through
   * @return true when results matches expectedResults, false otherwise
   */
  public static boolean testDeepFileIterator(File folder) {
    folder = new File(folder.getPath() + File.separator + "assignments");
    String expectedResults = "P01, PiggyBank.java, P02, CalendarPrinter.java, P03, "
        + "ElasticBank.java, P04, ExceptionalPiggyBank.java, P05, ExtendedVersion, "
        + "WinterCarnival.java, WinterCarnival.java, P06, AlphabetTrain.java, ";
    String results = "";

    // Retrieves all file's names and compares it to expectedResults. If FileNotFoundException or
    // NoSuchElementException is caught, returns false.
    try {
      DeepFileIterator deepIT = new DeepFileIterator(folder);

      while (deepIT.hasNext()) {
        results += deepIT.next().getName() + ", ";
      }

      if (!expectedResults.equals(results)) {
        System.out.println("Failed: Result not same as expected.");
        return false;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("No Such Element");
      return false;
    }

    return true;
  }

  /**
   * This method checks the correctness of the FilteredFileIterator class. Starts by creating a
   * FilteredileIterator called filteredIT, checking the folder parameter. Then, appends the name of
   * the file to a String results. Finally checks if the results matches String expectedResults.
   * 
   * @param folder The folder to be iterated through
   * @return true when results matches expectedResults, false otherwise
   */
  public static boolean testFilteredFileIterator(File folder) {
    String expectedResults = "PiggyBank.java, CalendarPrinter.java, ElasticBank.java, "
        + "ExceptionalPiggyBank.java, WinterCarnival.java, WinterCarnival.java, "
        + "AlphabetTrain.java, codeSamples.java, ";
    String results = "";

    // Retrieves all file's names and compares it to expectedResults. If FileNotFoundException or
    // NoSuchElementException is caught, returns false.
    try {
      FilteredFileIterator filteredIT = new FilteredFileIterator(folder, ".java");

      while (filteredIT.hasNext()) {
        results += filteredIT.next().getName() + ", ";
      }

      if (!expectedResults.equals(results)) {
        System.out.println("Failed: Result not same as expected.");
        return false;
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found.");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("No Such Element");
      return false;
    }

    return true;
  }

  /**
   * Driver method that runs all the tests cases.
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out
        .println("testShallowFileIterator: " + testShallowFileIterator(new File("./filesystem/")));
    System.out.println("testDeepFileIterator: " + testDeepFileIterator(new File("./filesystem/")));
    System.out.println(
        "testFilteredFileIterator: " + testFilteredFileIterator(new File("./filesystem/")));
  }
}
