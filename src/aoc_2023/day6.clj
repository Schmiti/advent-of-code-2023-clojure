(ns aoc-2023.day6
  (:require [clojure.string :as s]))

(defn- parse-line [line]
  (->> line
       (re-seq #"\d+")
       (map parse-long)))

(defn- parse [input]
  (let [lines (s/split-lines input)
        times (parse-line (first lines))
        distances (parse-line (second lines))]
    (map vector times distances)))

(defn- parse-2 [input]
  (let [lines (s/split-lines input)
        lines (map #(s/replace % #"\s" "") lines)
        times (parse-line (first lines))
        distances (parse-line (second lines))]
    (map vector times distances)))

(defn- calculate-distance [n x]
  (* x (- n x)))

(defn- find-records [[race-time record-distance]]
  (->> (range (inc race-time))
       (map #(calculate-distance race-time %))
       (filter #(> % record-distance))))

(defn part1 [input]
  (let [races (parse input)
        records (map #(find-records %) races)]
    (->> (map #(count %) records)
         (apply *))))

(defn part2 [input]
  (let [races (parse-2 input)
        records (map #(find-records %) races)]
    (->> (map #(count %) records)
         (apply *))))

