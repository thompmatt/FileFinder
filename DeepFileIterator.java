//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Deep File Iterator
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
 * This class creates a DeepFileIterator, implementing Iterator<File>, which goes through a
 * specified directory and returns the files and sub-directories within that directory. However, it
 * goes into its sub-directories and retrieves all the files in there, creating a new
 * DeepFileIterator called contentsIterator for every new folder found. Contains the following
 * methods:
 * 
 * - DeepFileIterator constructor
 *
 * - hasNext()
 * 
 * - next()
 * 
 * @author Matt Thompson
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DeepFileIterator implements Iterator<File> {
  private File[] folderContents; // File array with the contents of specific directory
  private int nextIndex; // Tracks nextIndex in the folderContents array
  private DeepFileIterator contentsIterator; // DeepFileIterator for folders within the iterator

  /**
   * Constructs a DeepFileIterator, which splits the specified file's contents into the
   * folderContents array, and sorts it. Then, sets nextIndex to 0, and contentsIterator to null.
   * 
   * @param file The specific file/directory to iterate through
   * @throws FileNotFoundException if file/directory does not exist
   */
  public DeepFileIterator(File file) throws FileNotFoundException {
    if (!file.exists()) { // If file does not exist, throws FileNotFoundException
      throw new FileNotFoundException("The specified file/directory does not exist.");
    }

    this.folderContents = file.listFiles();
    Arrays.sort(folderContents);
    this.nextIndex = 0;
    this.contentsIterator = null;
  }

  /**
   * Checks if there is a next element in the folderContents array by comparing nextIndex to the
   * length of the folderContents array. Also checks if contentsIterator is not null and if it has a
   * next.
   * 
   * @return true if there is a next element, and contentsIterator has a next and is not null, false
   *         otherwise
   */
  @Override
  public boolean hasNext() {
    if (nextIndex < folderContents.length) {
      return true;
    } else if (contentsIterator != null && contentsIterator.hasNext()) {
      return true;
    }

    return false;
  }

  /**
   * Checks if hasNext returns false, meaning there is no next index and no contentsIterator. If
   * there is a next, it first checks if there is a contentsIterator and if it has a next, returning
   * those files first. If not returns the current file of the folderContents, and checks if that
   * current file is a directory. If it is a directory, it creates a new DeepFileIterator to run
   * through that directory. Increments nextIndex and returns the current file.
   * 
   * @return the file at current index, or the next file in the contentsIterator.
   */
  @Override
  public File next() {
    if (!hasNext()) { // If there is no next element, throws NoSuchElementException
      throw new NoSuchElementException("There is no next element.");
    }

    // If contentsIterator exists and it has a next, return the next element in that
    // contentsIterator. Else, make contentsIterator equal to null.
    if (contentsIterator != null && contentsIterator.hasNext()) {
      return contentsIterator.next();
    } else {
      contentsIterator = null;
    }

    File current = this.folderContents[nextIndex];

    // Checks if the current file is a directory. If so, creates a new DeepFileIterator and assigns
    // it to contentsIterator
    if (current.isDirectory()) {
      try {
        contentsIterator = new DeepFileIterator(current);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    this.nextIndex++;
    return current;
  }
}
