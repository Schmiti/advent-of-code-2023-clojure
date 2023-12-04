(ns aoc-2023.day3-test
  (:require [clojure.test :refer [deftest is]]
            [aoc-2023.core]
            [aoc-2023.day3]))

(def ^:private input
  (aoc-2023.core/read-input "day3-sample.txt"))

(deftest test-part1
  (is (= 4361 (aoc-2023.day3/part1 input))))

(deftest test-part2
  (is (= 467835 (aoc-2023.day3/part2 input))))
