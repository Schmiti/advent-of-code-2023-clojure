(ns aoc-2023.day4
  (:require
   [clojure.math :as math]
   [clojure.set]
   [clojure.string]))

(defn- parse [line]
  (let [cols (-> line
                 (clojure.string/split #":")
                 second
                 (clojure.string/split #"\|"))]
    (->> cols
         (mapv #(re-seq #"\d+" %))
         (mapv #(mapv (fn [x] (Integer/parseInt x)) %)))))

(defn- count-matches [[winning-numbers discovered-numbers]]
  (let [matches (clojure.set/intersection (set winning-numbers) (set discovered-numbers))]
    (count matches)))

(defn- count-points [parsed]
  (let [nb-matches (count-matches parsed)]
    (cond
      (> nb-matches 0)  (int (math/pow 2 (dec nb-matches)))
      :else 0)))

(defn part1 [input]
  (->> input
       clojure.string/split-lines
       (map parse)
       (map count-points)
       (apply +)))

(defn- init-game-count [parsed]
  (->> parsed
       count
       range
       (map #(hash-map % 1))
       (apply merge)))

(defn- apply-copies [game-id nb-matches game-count]
  (let [game-nb-matches (get game-count game-id)]
    (reduce #(assoc %1 %2 (+ game-nb-matches (get %1 %2))) game-count (range (inc game-id) (+ game-id nb-matches 1)))))

(defn- count-games [parsed]
  (loop [[game & games] parsed game-count (init-game-count parsed) game-id 0]
    (let [nb-matches (count-matches game)]
      (cond
        (empty? game) game-count
        :else (recur games (apply-copies game-id nb-matches game-count) (inc game-id))))))

(defn part2 [input]
  (let [parsed
        (->> input
             clojure.string/split-lines
             (mapv parse))

        game-count (count-games parsed)]
    (apply + (vals game-count))))
