import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Statistics program implements an application that
 * reads a text file into a list, converts it to an array,
 * then calls different functions to determine the mean,
 * median, and mode.
 *
 * @author  Ina Tolo
 * @version 1.0
 * @since   2022-3-8
 */

class Statistics {
    /**
     * Calculates the mean.
     *
     * @param arrayOfInts is the only parameter
     * @return mean
     */
    public static double calcMean(Integer[] arrayOfInts) {
        // declaring variable
        int sum = 0;

        for (int counter = 0; counter < arrayOfInts.length; counter++) {
            sum += arrayOfInts[counter];
        }

        final double mean = (double) sum / arrayOfInts.length;

        // mean copied back to main
        return mean;
    }

    /**
     * Calculates the median.
     *
     * @param arrayOfInts is the only parameter
     * @return median
     */
    public static double calcMedian(Integer[] arrayOfInts) {
        // declaring variables
        double median = 0;
        int firstMidNum = 0;
        int secondMidNum = 0;

        Arrays.sort(arrayOfInts);

        if (arrayOfInts.length % 2 == 0) {
            firstMidNum = arrayOfInts.length / 2;

            secondMidNum = (arrayOfInts.length / 2) - 1;

            median = (arrayOfInts[firstMidNum] + arrayOfInts[secondMidNum]) / 2;
        } else {
            firstMidNum = arrayOfInts.length / 2;

            median = arrayOfInts[firstMidNum];
        }
        return median;
    }

    /**
     * Calculates the mode.
     *
     * @param arrayOfInts is the only parameter
     * @return mode
     */
    public static Integer[] calcMode(Integer[] arrayOfInts) {
        // declaring variables
        final ArrayList<Integer> modesList = new ArrayList();
        final ArrayList<Integer> modesListTemp = new ArrayList();
        int mostAppearances = 0;

        for (int counterOne = 0; counterOne
             < arrayOfInts.length; ++counterOne) {
            int appearances = 0;
            for (int counterTwo = 0; counterTwo
                 < arrayOfInts.length; ++counterTwo) {
                if (arrayOfInts[counterTwo] == arrayOfInts[counterOne]) {
                    ++appearances;
                } else if (appearances > mostAppearances) {
                    mostAppearances = appearances;
                    modesListTemp.clear();
                    modesListTemp.add(arrayOfInts[counterOne]);
                }
                if (appearances == mostAppearances) {
                    modesListTemp.add(arrayOfInts[counterOne]);
                }
            }
        }
        for (int multipleInstances : modesListTemp) {
            if (!modesList.contains(multipleInstances)) {
                modesList.add(multipleInstances);
            }
        }
        return modesList.toArray(new Integer[modesList.size()]);
    }

    /**
     * Main entry into program.
     *
     * @param args nothing passed in
     * @throws IOException is being thrown
     */
    public static void main(String[] args) throws IOException {
        // declaring variables
        final List<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = null;
        final double meanUser;
        final double medianUser;
        final Integer[] modeUser;

        try {
            if (null != args[0] && args[0].length()
                > 4 && args[0].endsWith(".txt")) {
                bf = new BufferedReader(new FileReader(args[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // reads each line in file
        String line = bf.readLine();

        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        // closes buffreader
        bf.close();

        final Integer[] arrayOfIntsFile =
                                  new Integer[listOfStrings.size()];

        // adds list values to array
        for (int counter = 0; counter
             < listOfStrings.size(); counter++) {
            arrayOfIntsFile[counter] =
                Integer.parseInt(listOfStrings.get(counter));
        }

        // function calls
        meanUser = calcMean(arrayOfIntsFile);
        medianUser = calcMedian(arrayOfIntsFile);
        modeUser = calcMode(arrayOfIntsFile);

        // displays mean, medium, and mode to the user
        System.out.println(listOfStrings);
        System.out.println("\nCalculating stats...");
        System.out.println("The mean is: " + meanUser);
        System.out.println("The median is: " + medianUser);
        System.out.println("The mode(s) is/are: " + Arrays.toString(modeUser));
    }
}
