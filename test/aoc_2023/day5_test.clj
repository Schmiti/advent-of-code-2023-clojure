(ns aoc-2023.day5-test
  (:require [aoc-2023.core]
            [aoc-2023.day5]
            [clojure.test :refer [deftest is]]))

(def input (aoc-2023.core/read-input "day5-sample.txt"))

(deftest test-part1
  (is (= 35 (aoc-2023.day5/part1 input))))
