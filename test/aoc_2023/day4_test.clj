(ns aoc-2023.day4-test
  (:require [clojure.test :refer [deftest is]]
            [aoc-2023.core]
            [aoc-2023.day4]))

(def input (aoc-2023.core/read-input "day4-sample.txt"))

(deftest test-part1
  (is (= 13 (aoc-2023.day4/part1 input))))

(deftest test-part2
  (is (= 30 (aoc-2023.day4/part2 input))))
