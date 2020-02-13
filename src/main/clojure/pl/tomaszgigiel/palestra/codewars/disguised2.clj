(ns pl.tomaszgigiel.palestra.codewars.disguised2
  (:gen-class))

; https://www.codewars.com/kata/disguised-sequences-ii/train/clojure
; Let us define two sums u(n, p) and v(n, p):
; \large u(n, p) = \sum_{k=0}^{n}{(-1)^k}*p*{4^{n-k}}*\binom{2n-k+1}{k}
; \large v(n, p) = \sum_{k=0}^{n}{(-1)^k}*p*{4^{n-k}}*\binom{2n-k}{k}

; Task:
; 1) Calculate u(n, p) and v(n, p) with two brute-force functions u1(n, p) and v1(n, p).
; 2) Try u1(n, p) and v1(n, p) for small values of n and p and guess the results of u(n, p) and v(n, p)
; 3) Using 2) write u_eff(n, p) and v_eff(n, p) (or uEff(n, p) and vEff(n, p) or u-eff(n, p) and v-eff(n, p)) to efficiently calculate u and v for bigger values of n and p

; (This third part is not tested in
; JS, CS, TS, C++, C, PHP, Crystal, Rust, Swift, R, Nim, Fortran, NASM
; since there you don't have big integers to control your guess in part 2. See note below for "Shell").

; Examples:
; v1(12, 70) --> 1750
; u1(13, 18) --> 252
; Extra points:
; For the mathy ones: find a relation between v(n, p), v(n-1, p) and u(n-1, p) :-)

; Notes
; Shell: only v1(n, p)is tested (use the solution you find for v_eff(n, p).
; If you have found u_eff(n, p) and v_eff(n, p) you can use them to calculate u(n, p) and v(n, p).
; You could see: https://en.wikipedia.org/wiki/Binomial_coefficient for a refresh about binomial coefficients.

(defn exp [x n]
  (reduce *' (repeat n x)))

(defn binomial-coefficient [n k]
  (let [rprod (fn [a b] (reduce *' (range a (inc b))))]
    (/ (rprod (-' n k -1) n) (rprod 1 k))))

(defn element-u1 [n p k]
  (*' (exp -1 k) p (exp 4 (- n k)) (binomial-coefficient (- (*' 2 n) k -1) k)))

(defn element-v1 [n p k]
  (*' (exp -1 k) p (exp 4 (- n k)) (binomial-coefficient (- (*' 2 n) k) k)))

(defn u1 [n p]
  (apply + (for [k (range (inc n))] (element-u1 n p k))))

(defn v1 [n p]
  (apply + (for [k (range (inc n))] (element-v1 n p k))))

(defn u-eff [n p]
  (apply + (for [k (range (inc n))] (element-u1 n p k))))

(defn v-eff [n p]
  (apply + (for [k (range (inc n))] (element-v1 n p k))))

; donaldsebleung
(defn u1 [n p] (* p (+ n 1)))
(defn v1 [n p] (* p (+ (* 2 n) 1)))
(defn u-eff [n p] (* p (+ n 1)))
(defn v-eff [n p] (* p (+ (* 2 n) 1)))
