(ns aoc-2023.day1-test
  (:require [aoc-2023.core]
            [aoc-2023.day1]
            [clojure.test :refer [is deftest]]))

(def input1 (aoc-2023.core/read-input "day1-sample1.txt"))
(def input2 (aoc-2023.core/read-input "day1-sample2.txt"))

(deftest test-part1
  (is (= 142 (aoc-2023.day1/part1 input1))))

(deftest test-part2
  (is (= 281 (aoc-2023.day1/part2 input2))))
