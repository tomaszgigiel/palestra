(ns pl.tomaszgigiel.palestra.codewars.skyscrapers
  (:require [pl.tomaszgigiel.palestra.codewars.skyscrapers-const :as const])
  (:gen-class))

; TODO: multithreading, thread, future
; TODO: https://clojuredocs.org/clojure.core/transient

; SOURCE: https://stackoverflow.com/questions/26076077/clojure-list-all-permutations-of-a-list
(defn permutations [xs]
  (if (= 1 (count xs))
    (list xs)
    (for [head xs
          tail (permutations (disj (set xs) head))]
      (into [head] tail))))

; SOURCE: https://stackoverflow.com/questions/16264813/clojure-idiomatic-way-to-call-contains-on-a-lazy-sequence
(defn lazy-contains? [col key]
  (= (some #{key} col) key))

(defn pair [cs]
  (let [v (vec cs)
        s (count v)
        s2 (/ s 2)
        s4 (/ s 4)]
    (for [x (range 0 s2)] (if (< x s4) [(nth v x) (nth v (- s s4 x 1))] [(nth v (+ (- s x 1) s4)) (nth v x)]))))

(defn clue-x [v]
  (count (reduce (fn [coll a]
                   (if (empty? coll) [a]
                     (if (> a (last coll)) (conj coll a) coll)))
                 [] v)))

(defn clue-y [v] (clue-x (reverse v)))

(defn clue [v] [(clue-x v) (clue-y v)])

(defn clue-and-permutation-vec [permutations] (map (fn [p] {(clue p) [p]}) permutations))

(defn clue-and-permutation-map [v] (reduce #(merge-with into %1 %2) v))

(defn clues-cmp [clue-a clue-b]
  (let [[ax ay] clue-a
        [bx by] clue-b]
    (and (or (= ax 0) (= bx 0) (= ax bx))
         (or (= ay 0) (= by 0) (= ay by)))))

(defn possibilities-by-clue [cap clue]
  (apply concat
         (vals
           (filter #(clues-cmp clue (first %1)) cap))))

(defn pattern-for-street [idx patterns]
  (mapv (fn [p] (distinct (mapv #(nth %1 idx) p))) patterns))

(defn street-ok? [street pattern]
  (every? true? (map-indexed #(.contains (nth pattern %1) %2) street)))

(defn street-ok? [street pattern]
  (every? true? (map-indexed #(lazy-contains? (nth pattern %1) %2) street)))

(defn filter-streets [idx streets pattern]
  (filter #(street-ok? %1 pattern) streets))

; square -> streets -> street
(defn filter-square [square patterns]
  (map-indexed #(filter-streets %1 %2 (pattern-for-street %1 patterns)) square))

(defn filter-possibilities [possibilities level repeat]
  (loop [a (subvec possibilities 0 level)
         b (subvec possibilities level)
         counter 0]
    (let [c (filter-square a b)]
      (if (> counter repeat) (concat b c) (recur b c (inc counter))))))

(defn pickup [rows pos]
  (let [[h & tail] rows] (vec (concat [[(nth h pos)]] tail))))

(defn filter-pickup [possibilities level repeat]
  (loop [i 0]
    (let [result (filter-possibilities (vec (pickup possibilities i)) level repeat)
          empty (empty? (first result))]
      (if empty (recur (inc i)) result))))

(defn solve-puzzle [clues]
  (let [level (/ (count clues) 4)
        ; cap (if (= level 7) const/cap7 (clue-and-permutation-map (clue-and-permutation-vec (permutations (range 1 (inc level))))))
        cap (clue-and-permutation-map (clue-and-permutation-vec (permutations (range 1 (inc level)))))
        paired (pair clues)
        possibilities (mapv #(possibilities-by-clue cap %1) paired)
        filtered (filter-possibilities possibilities level 11)
        semi-result (apply concat filtered)
        count (count semi-result)
        result (apply concat (take-last level (if (> count level) (filter-pickup filtered level 9) semi-result)))]
    result))
