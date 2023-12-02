(ns aoc-2023.day2-test
  (:require [aoc-2023.day2]
            [aoc-2023.core]
            [clojure.test :refer [is deftest]]))

(def input (aoc-2023.core/read-input "day2-sample.txt"))

(deftest test-part1
  (is (= 8 (aoc-2023.day2/part1 input))))

(deftest test-part2
  (is (= 2286 (aoc-2023.day2/part2 input))))
