(ns pl.tomaszgigiel.palestra.codewars.movie
  (:gen-class))

;System A : 15 * 3 = 45
;System B : 500 
; + 15 * 0.90 [13.5]
; + (15 * 0.90) * 0.90 [12.15] 
; + (15 * 0.90 * 0.90) * 0.90 [10.935]
; ( = 536.5849999999999, no rounding for each ticket)

(defn movie-a [card ticket perc]
  (loop [n 1
         part (* ticket perc)
         sum card]
    (if (> (* n ticket) (Math/ceil (+ sum part)))
      n
      (recur (inc n) (* part perc) (+ sum part))
      )))

(defn movie [card ticket perc]
  (loop [n 1
         a ticket
         part (* ticket perc)
         b card]
    (if (> a (Math/ceil (+ b part)))
      n
      (recur (inc n) (+ a ticket) (* part perc) (+ b part)))))
