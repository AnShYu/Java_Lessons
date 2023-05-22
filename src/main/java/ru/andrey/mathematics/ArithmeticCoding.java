package ru.andrey.mathematics;

import java.util.*;
import java.util.function.Predicate;

public class ArithmeticCoding {

    public static class Interval {
        double startingValue;
        double endingValue;

        public Interval(double startingValue, double endingValue) {
            this.startingValue = startingValue;
            this.endingValue = endingValue;
        }

        public double getStartingValue() {
            return startingValue;
        }

        public double getEndingValue() {
            return endingValue;
        }

        public void setStartingValue(double startingValue) {
            this.startingValue = startingValue;
        }

        public void setEndingValue(double endingValue) {
            this.endingValue = endingValue;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "startingValue=" + startingValue +
                    ", endingValue=" + endingValue +
                    '}';
        }
    }

    public static class CharProbability implements Comparable<CharProbability> {
        char ch;
        double probability;

        public CharProbability(char ch, double probability) {
            this.ch = ch;
            this.probability = probability;
        }

        public char getCh() {
            return ch;
        }

        public double getProbability() {
            return probability;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CharProbability that = (CharProbability) o;
            return ch == that.ch && Double.compare(that.probability, probability) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ch, probability);
        }

        @Override
        public String toString() {
            return "CharProbability{" +
                    "ch=" + ch +
                    ", probability=" + probability +
                    '}';
        }

        @Override
        public int compareTo(CharProbability o) {
            if (this.getProbability() > o.getProbability()) {return -1;
            } else if (this.getProbability() < o.getProbability()) {return 1;
            } else return 0;
        }
    }

    public static void main(String[] args) {
        Interval resultingInterval = new Interval(0, 1);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение для кодирования:");
        String inputData = scanner.nextLine();

        char[] chars = inputData.toCharArray();
        List<CharProbability> probabilities = new ArrayList<>();
        Predicate<Character> charAlreadyInList = (ch) -> {
            boolean isInList = false;
            for (CharProbability charProbability: probabilities) {
                if (ch.equals(charProbability.getCh())) {
                    isInList = true;
                }
            }
            return isInList;
        };

        for (Character ch: chars) {
            if (!charAlreadyInList.test(ch)) {
                int counter = 0;
                for (int i = 0; i<chars.length; i++) {
                    if (ch.equals(chars[i])) {
                        counter++;
                    }
                }
                double probability = (double) counter / chars.length;
                CharProbability charProbability = new CharProbability(ch, probability);
                probabilities.add(charProbability);
            }
        }
//        System.out.println(probabilities);
        Collections.sort(probabilities);
//        System.out.println(probabilities);

        for (int i = 0; i<chars.length; i++) {
            for (int j = 0; j<probabilities.size(); j++) {
                if (chars[i] == probabilities.get(j).getCh()) {
                    double resultingIntervalLength = resultingInterval.getEndingValue() - resultingInterval.getStartingValue();
                    double startingValueIncrementCoefficient = 0;
                    for (int c = 0; c <= j-1; c++) {
                        startingValueIncrementCoefficient = startingValueIncrementCoefficient + probabilities.get(c).getProbability();
                    }
                    double resultingIntervalStartingValue = resultingInterval.getStartingValue() + resultingIntervalLength*startingValueIncrementCoefficient;
                    resultingInterval.setStartingValue(resultingIntervalStartingValue);

                    double endingValueIncrementCoefficient = 0;
                    for (int c = 0; c <= j; c++) {
                        endingValueIncrementCoefficient = endingValueIncrementCoefficient + probabilities.get(c).getProbability();
                    }
                    double resultingIntervalEndingValue = resultingInterval.getStartingValue() + resultingIntervalLength*probabilities.get(j).getProbability();
                    resultingInterval.setEndingValue(resultingIntervalEndingValue);
                }
            }
        }
        System.out.println(resultingInterval);
        double resultingPoint = resultingInterval.getStartingValue() + (resultingInterval.getEndingValue() - resultingInterval.getStartingValue()) / 2;
        System.out.println(resultingPoint);

//    Decoding follows
        List<Character> decodedLine = new ArrayList<>();
        Interval decodingInterval = new Interval(0, 1);


        for (int k = 0; k<chars.length; k++) {
            for (int i = 0; i < probabilities.size(); i++) {
                double lineSegment = decodingInterval.getEndingValue() - decodingInterval.getStartingValue();
                Interval charsInterval = new Interval(0, 0);
                double startingValue = 0;
                for (int j = 0; j < i; j++) {
                    startingValue = startingValue + probabilities.get(j).getProbability();
                }
                startingValue = decodingInterval.getStartingValue() + lineSegment * startingValue;
                double endingValue = startingValue + lineSegment * probabilities.get(i).getProbability();

                charsInterval.setStartingValue(startingValue);
                charsInterval.setEndingValue(endingValue);
                if (isWithinInterval(resultingPoint, charsInterval)) {
                    decodedLine.add(probabilities.get(i).getCh());
                    decodingInterval.setStartingValue(startingValue);
                    decodingInterval.setEndingValue(endingValue);
                    break;
                }
            }

        }
        System.out.println(decodedLine);

    }

    public static boolean isWithinInterval (double point, Interval interval) {
        if (point > interval.getStartingValue() && point <= interval.getEndingValue()) return true;
        return false;
    }


}
