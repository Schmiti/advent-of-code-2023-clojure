(ns aoc-2023.day5
  (:require [clojure.string :as s]))

(defn- parse-map-input [map-name input]
  (->> (s/split-lines input)
       (drop-while #(not (s/starts-with? % map-name)))
       (rest)
       (take-while #(re-matches #"\d.*" %))
       (map #(re-seq #"\d+" %))
       (map (fn [[destination source range]] (hash-map :destination (parse-long destination) :source (parse-long source) :range (parse-long range))))))

(defn- parse-seeds-input [input]
  (->> (s/split-lines input)
       (first)
       (re-seq #"\d+")
       (map #(parse-long %))))

(defn- parse [input]
  (let [extractors (juxt
                    parse-seeds-input
                    (partial parse-map-input "seed-to-soil")
                    (partial parse-map-input "soil-to-fertilizer")
                    (partial parse-map-input "fertilizer-to-water")
                    (partial parse-map-input "water-to-light")
                    (partial parse-map-input "light-to-temperature")
                    (partial parse-map-input "temperature-to-humidity")
                    (partial parse-map-input "humidity-to-location"))

        extracted (extractors input)]
    {:seeds (extracted 0)
     :seed-to-soil (extracted 1)
     :soil-to-fertilizer (extracted 2)
     :fertilizer-to-water (extracted 3)
     :water-to-light (extracted 4)
     :light-to-temperature (extracted 5)
     :temperature-to-humidity (extracted 6)
     :humidity-to-location (extracted 7)}))

(defn part1 [input]
  (parse input))
