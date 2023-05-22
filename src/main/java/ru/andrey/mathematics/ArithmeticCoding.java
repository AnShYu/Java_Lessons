package ru.andrey.mathematics;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class ArithmeticCoding {

    public static class Interval {
        BigDecimal startingValue;
        BigDecimal endingValue;

        public Interval(BigDecimal startingValue, BigDecimal endingValue) {
            this.startingValue = startingValue;
            this.endingValue = endingValue;
        }

        public BigDecimal getStartingValue() {
            return startingValue;
        }

        public BigDecimal getEndingValue() {
            return endingValue;
        }

        public void setStartingValue(BigDecimal startingValue) {
            this.startingValue = startingValue;
        }

        public void setEndingValue(BigDecimal endingValue) {
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
        BigDecimal probability;

        public CharProbability(char ch, BigDecimal probability) {
            this.ch = ch;
            this.probability = probability;
        }

        public char getCh() {
            return ch;
        }

        public BigDecimal getProbability() {
            return probability;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CharProbability that = (CharProbability) o;
            return ch == that.ch && Objects.equals(probability, that.probability);
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
            if (this.getProbability().compareTo(o.getProbability()) > 0) {return -1;
            } else if (this.getProbability().compareTo(o.getProbability()) < 0) {return 1;
            } else return 0;
        }
    }

    public static void main(String[] args) {
        Interval resultingInterval = new Interval(new BigDecimal(0), new BigDecimal(1));
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
                double prob = (double) counter / chars.length;
                BigDecimal probability = new BigDecimal(prob);
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
                    BigDecimal resultingIntervalLength = resultingInterval.getEndingValue().subtract(resultingInterval.getStartingValue());
                    BigDecimal startingValueIncrementCoefficient = new BigDecimal(0);
                    for (int c = 0; c < j; c++) {
                        startingValueIncrementCoefficient = startingValueIncrementCoefficient.add(probabilities.get(c).getProbability());
                    }
                    BigDecimal resultingIntervalStartingValue = resultingInterval.getStartingValue().add(resultingIntervalLength.multiply(startingValueIncrementCoefficient));
                    resultingInterval.setStartingValue(resultingIntervalStartingValue);

                    BigDecimal resultingIntervalEndingValue = resultingInterval.getStartingValue().add(resultingIntervalLength.multiply(probabilities.get(j).getProbability()));
                    resultingInterval.setEndingValue(resultingIntervalEndingValue);
                }
            }
        }
//        System.out.println(resultingInterval);
        BigDecimal resultingPointSub1 = resultingInterval.getEndingValue().subtract(resultingInterval.getStartingValue());
        BigDecimal resultingPointSub2 = resultingPointSub1.divide(new BigDecimal(2));
        BigDecimal resultingPoint = resultingInterval.getStartingValue().add(resultingPointSub2);
        System.out.println("Кодирующая точка: " + resultingPoint);

//    Decoding follows
        List<Character> decodedLine = new ArrayList<>();
        Interval decodingInterval = new Interval(new BigDecimal(0), new BigDecimal(1));


        for (int k = 0; k<chars.length; k++) {
            for (int i = 0; i < probabilities.size(); i++) {
                BigDecimal lineSegment = decodingInterval.getEndingValue().subtract(decodingInterval.getStartingValue());
                Interval charsInterval = new Interval(new BigDecimal(0), new BigDecimal(0));
                BigDecimal startingValue = new BigDecimal(0);
                for (int j = 0; j < i; j++) {
                    startingValue = startingValue.add(probabilities.get(j).getProbability());
                }
                startingValue = decodingInterval.getStartingValue().add(lineSegment.multiply(startingValue));
                BigDecimal endingValue = startingValue.add(lineSegment.multiply(probabilities.get(i).getProbability()));

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
        System.out.println("Декодировка: " + decodedLine);

    }

    public static boolean isWithinInterval (BigDecimal point, Interval interval) {
        if (point.compareTo(interval.getStartingValue()) > 0 && point.compareTo(interval.getEndingValue()) <= 0) return true;
        return false;
    }


}
