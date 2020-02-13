(ns pl.tomaszgigiel.palestra.codewars.others
  (:gen-class))

; SOURCE: https://stackoverflow.com/questions/5057047/how-to-do-exponentiation-in-clojure
(defn exp [x n]
  (reduce * (repeat n x)))

; SOURCE: https://stackoverflow.com/questions/26076077/clojure-list-all-permutations-of-a-list
(defn permutations [xs]
  (if (= 1 (count xs))
    (list xs)
    (for [head xs
          tail (permutations (disj (set xs) head))]
      (into [head] tail))))
