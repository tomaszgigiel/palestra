(ns pl.tomaszgigiel.palestra.codewars.findeven
  (:gen-class))

(defn slide [x]
  (let [i (:i x)
        arr (:arr x)
        result (:result x)
        a (:a x)
        b (:b x)
        result (if (= a b) i -1)]
    {:a (+ a (get arr i))
     :b (- b (get arr (+ i 1) 0))
     :i (+ i 1)
     :arr arr
     :result result}))

(defn find-even-index [arr]
  (let [r (first
            (filter #(>= (:result %) 0)
                    (take (+ (count arr) 1)
                          (iterate slide {:a 0 :b (apply + (rest arr)) :i 0 :arr arr :result -1}))))]
    (if (nil? r) -1 (:result r))))
