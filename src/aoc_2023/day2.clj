(ns aoc-2023.day2
  (:require [clojure.string]))

(defn- extract-game-id [line]
  (->> line
       (re-seq #"\d+")
       first
       Integer/parseInt))

(defn- extract-color [line re]
  (Integer/parseInt (or (second (first (re-seq re line))) "0")))

(defn- extract-red [line]
  (extract-color line #"(\d+) red"))

(defn- extract-green [line]
  (extract-color line #"(\d+) gree"))

(defn- extract-blue [line]
  (extract-color line #"(\d+) blue"))

(defn- extract-subset [line]
  {:red (extract-red line) :green (extract-green line) :blue (extract-blue line)})

(defn- extract-subsets [line]
  (as-> line l
    (clojure.string/split l #":")
    (second  l)
    (clojure.string/split l #";")
    (map extract-subset l)))

(defn- parse [line]
  (let [game-id (extract-game-id line)
        subsets (extract-subsets line)]
    {:id game-id :subsets subsets}))

(defn- subset-in-limit [subset]
  (and
   (<= (:red subset) 12)
   (<= (:green subset) 13)
   (<= (:blue subset) 14)))

(defn- game-in-limit [game]
  (every? subset-in-limit (:subsets game)))

(defn part1 [input]
  (->> input
       clojure.string/split-lines
       (map parse)
       (filter game-in-limit)
       (map #(:id %))
       (apply +)))

(defn- max-color [color game]
  (apply max (map color (:subsets game))))

(defn- count-fewer-cubes [game]
  {:red (max-color :red game) :blue (max-color :blue game) :green (max-color :green game)})

(defn- power [subset]
  (* (:red subset) (:green subset) (:blue subset)))

(defn part2 [input]
  (->> input
       clojure.string/split-lines
       (map parse)
       (map count-fewer-cubes)
       (map power)
       (apply +)))

