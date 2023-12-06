(ns aoc-2023.core
  (:require [clojure.java.io :as io]
            [aoc-2023.day1]
            [aoc-2023.day2]
            [aoc-2023.day3]
            [aoc-2023.day4]
            [aoc-2023.day6]))

(defn read-input [file-name]
  (slurp (io/resource file-name)))

(defn -main
  "Used to dispatch tasks from the command line.
  
  lein run d01.p1"
  [part]
  (case part
    "d01.p1" (println (aoc-2023.day1/part1 (read-input "day1.txt")))
    "d01.p2" (println (aoc-2023.day1/part2 (read-input "day1.txt")))
    "d02.p1" (println (aoc-2023.day2/part1 (read-input "day2.txt")))
    "d02.p2" (println (aoc-2023.day2/part2 (read-input "day2.txt")))
    "d03.p1" (println (aoc-2023.day3/part1 (read-input "day3.txt")))
    "d03.p2" (println (aoc-2023.day3/part2 (read-input "day3.txt")))
    "d04.p1" (println (aoc-2023.day4/part1 (read-input "day4.txt")))
    "d04.p2" (println (aoc-2023.day4/part2 (read-input "day4.txt")))
    "d06.p1" (println (aoc-2023.day6/part1 (read-input "day6.txt")))
    "d06.p2" (println (aoc-2023.day6/part2 (read-input "day6.txt")))))
