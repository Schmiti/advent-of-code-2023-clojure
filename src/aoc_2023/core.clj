(ns aoc-2023.core
  (:require [clojure.java.io :as io]
            [aoc-2023.day1]))

(defn- read-input [file-name]
  (slurp (io/resource file-name)))

(defn -main
  "Used to dispatch tasks from the command line.
  
  lein run d01.p1"
  [part]
  (case part
    "d01.p1" (println (aoc-2023.day1/part1 (read-input "day1.txt")))
    "d01.p2" (println (aoc-2023.day1/part2 (read-input "day1.txt")))))
