(ns pl.tomaszgigiel.palestra.common
  (:gen-class))

(defn ok [] (str "ok"))

; https://github.com/clojure/clojure/blob/clojure-1.9.0/src/clj/clojure/core.clj#L3850
(defmacro only-time
  [expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     (/ (double (- (. System (nanoTime)) start#)) 1000000.0)))
