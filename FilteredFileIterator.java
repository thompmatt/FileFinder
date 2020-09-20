//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Filtered File Iterator
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
 * This class creates a FilteredFileIterator, implementing Iterator<File>, which goes through a
 * specified directory and returns the files and sub-directories within that directory. Like the
 * DeepFileIterator, it also goes into its sub-directories, but only retrieves those files that
 * contain the specified search pattern. Contains the following methods:
 * 
 * - FilteredFileIterator constructor
 *
 * - hasNext()
 * 
 * - next()
 * 
 * - nextFile()
 * 
 * @author Matt Thompson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredFileIterator implements Iterator<File> {
  private DeepFileIterator fileIterator; // Iterates through all files
  private String searchPattern; // Filter to compare to name of files
  private File nextMatchingFile; // Tracks the next file that contains searchPattern

  /**
   * Constructs a FilteredFileIterator, constructing a DeepFileIterator to go through the files.
   * Then sets searchPattern as the filter, and nextMatchingFile to null. At the end, calls
   * nextFile() to initialize nextMatchingFile to the next matching file.
   * 
   * @param file   The specific file/directory to iterate through
   * @param filter The search pattern that files must contain
   * @throws FileNotFoundException if file/directory does not exist
   */
  public FilteredFileIterator(File file, String filter) throws FileNotFoundException {
    if (!file.exists()) { // If file does not exist, throws FileNotFoundException
      throw new FileNotFoundException("The specified file/directory does not exist.");
    }

    this.fileIterator = new DeepFileIterator(file);
    this.searchPattern = filter;
    this.nextMatchingFile = null;
    nextFile();
  }

  /**
   * Checks if the fileIterator has a next and if there is a next matching file.
   * 
   * @return true if fileIterator has next and nextMatchingFile is not null, false otherwise
   */
  @Override
  public boolean hasNext() {
    if (fileIterator.hasNext() && nextMatchingFile != null) {
      return true;
    }
    return false;
  }

  /**
   * Checks if hasNext returns false, meaning there is no next matching file. If there is a next,
   * sets the returned file to be nextMatchingFile, and calls nextFile() to see if there is another
   * matching file.
   * 
   * @return the current matching file.
   */
  @Override
  public File next() {
    if (!hasNext()) { // If there is no next element, throws NoSuchElementException
      throw new NoSuchElementException("There is no next element.");
    }

    File returned = nextMatchingFile; // File to be returned
    nextFile();

    return returned;
  }

  /**
   * Private helper method that runs a while loop that calls fileIterator.next() until
   * fileIterator.hasNext() returns false. Then, checks if the current file contains the
   * searchPattern. If it contains the searchPattern, it sets the current to be nextMatchingFile,
   * and returns. If there is no more matching results, sets nextMatchingFile to null and returns.
   */
  private void nextFile() {
    // Goes through all the files in the specified directory
    while (fileIterator.hasNext()) {
      File current = fileIterator.next();

      if (current.getName().contains(searchPattern)) {
        nextMatchingFile = current;
        return;
      }
    }

    nextMatchingFile = null;
    return;
  }
}
