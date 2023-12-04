(ns aoc-2023.day3
  (:require [clojure.string]))

(defn- digit? [c]
  (and (some? c)
       (<= (int \0) (int c) (int \9))))

(defn- is-symbol? [c]
  (and (some? c)
       (not (digit? c))
       (not (= \. c))))

(defn- adjacent-to-symbol? [row-idx col-idx matrix] ;; Scan all around the position to look for a symbol
  (some is-symbol? [(get-in matrix [(dec row-idx) (dec col-idx)])
                    (get-in matrix [(dec row-idx) col-idx])
                    (get-in matrix [(dec row-idx) (inc col-idx)])
                    (get-in matrix [row-idx (dec col-idx)])
                    (get-in matrix [row-idx (inc col-idx)])
                    (get-in matrix [(inc row-idx) (dec col-idx)])
                    (get-in matrix [(inc row-idx) col-idx])
                    (get-in matrix [(inc row-idx) (inc col-idx)])]))

(defn extract-part-numbers [line line-idx matrix]
  (loop [[line-char & line-chars] (seq line) col-idx 0 adjacent? false char-acc "" number-acc []]
    (let [is-digit? (digit? line-char)
          is-adjacent? (adjacent-to-symbol? line-idx col-idx matrix)]
      (cond
        (nil? line-char) number-acc
        :else (recur
               line-chars
               (inc col-idx)
               (if is-digit? (or adjacent? is-adjacent?) false)
               (if is-digit? (str char-acc line-char) "")
               (if (and adjacent? (not (digit? line-char))) (conj number-acc (Integer/parseInt char-acc)) number-acc))))))

(defn- generate-matrix [input]
  (->> input
       (clojure.string/split-lines)
       (map #(str % "."))
       (map vec)
       vec))

(defn part1 [input]
  (let [matrix (generate-matrix input)
        part-numbers (map-indexed #(extract-part-numbers %2 %1 matrix) matrix)]
    (println part-numbers)
    (->> part-numbers
         (map #(apply + %))
         (apply +))))

(def regex  #"(\d+)|([^\.\d]+)")

(defn- parse-line [row-idx line]
  (let [matcher (re-matcher regex line)]
    (loop [results []]
      (if-let [[_ number symbol] (re-find matcher)]
        (recur (conj results
                     (cond-> {:coords
                              (mapv (fn [x] {:y row-idx :x x}) (range (.start matcher) (.end matcher)))}
                       number (assoc :number (parse-long number))
                       symbol (assoc :symbol symbol))))
        results))))

(defn- parse [input]
  (->> input
       clojure.string/split-lines
       (map-indexed parse-line)
       (apply concat)))

(defn neighbourhood [{:keys [y x]}]
  (for [dy [-1 0 1]
        dx [-1 0 1]
        :when (not (= dx dy 0))]
    {:y (+ y dy)
     :x (+ x dx)}))

(defn part2 [input]
  (let [parsed (parse input)

        coordinate->part
        (->> parsed
             (filter :number)
             (mapcat (fn [part] (map (fn [coord] [coord part]) (:coords part))))
             (into {}))

        gear? (comp #{"*"} :symbol)

        gear-ratios (->> parsed
                         (filter gear?)
                         (keep (fn [{:keys [coords]}]
                                 (let [gear-coord (first coords) ; gears always just 1 coordinate
                                       adjacent-parts (->> gear-coord
                                                           neighbourhood
                                                           (keep coordinate->part)
                                                           distinct)]
                                   (when (= 2 (count adjacent-parts))
                                     (apply * (map :number adjacent-parts)))))))]
    (reduce + 0 gear-ratios)))
