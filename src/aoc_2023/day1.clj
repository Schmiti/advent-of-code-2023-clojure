(ns aoc-2023.day1
  (:require [clojure.string]))

(defn- extract-calibration-value [line]
  (let [digits (re-seq #"\d" line)]
    (Integer/parseInt (str (first digits) (last digits)))))

(defn- replace-string-numbers [line]
  (-> line
      (clojure.string/replace "oneight" "18")
      (clojure.string/replace "twone" "21")
      (clojure.string/replace "threeight" "38")
      (clojure.string/replace "fiveight" "58")
      (clojure.string/replace "sevenine" "79")
      (clojure.string/replace "eightwo" "82")
      (clojure.string/replace "eighthree" "83")
      (clojure.string/replace "nineight" "98")
      (clojure.string/replace "one" "1")
      (clojure.string/replace "two" "2")
      (clojure.string/replace "three" "3")
      (clojure.string/replace "four" "4")
      (clojure.string/replace "five" "5")
      (clojure.string/replace "six" "6")
      (clojure.string/replace "seven" "7")
      (clojure.string/replace "eight" "8")
      (clojure.string/replace "nine" "9")))

(defn part1 [input]
  (->> input
       clojure.string/split-lines
       (map extract-calibration-value)
       (apply +)))

(defn part2 [input]
  (->> input
       clojure.string/split-lines
       (map replace-string-numbers)
       (map extract-calibration-value)
       (apply +)))
