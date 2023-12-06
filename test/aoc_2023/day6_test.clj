(ns aoc-2023.day6-test
  (:require [aoc-2023.core]
            [aoc-2023.day6]
            [clojure.test :refer [deftest is]]))

(def input (aoc-2023.core/read-input "day6-sample.txt"))

(deftest test-part1
  (is (= 288 (aoc-2023.day6/part1 input))))

(deftest test-part2
  (is (= 71503 (aoc-2023.day6/part2 input))))
