(ns pl.tomaszgigiel.palestra.codewars.mix
  (:gen-class))

; Given two strings s1 and s2, we want to visualize how different the two strings are. 
; We will only take into account the lowercase letters (a to z). 
; First let us count the frequency of each lowercase letters in s1 and s2.
; s1 = "A aaaa bb c"
; s2 = "& aaa bbb c d"
; s1 has 4 'a', 2 'b', 1 'c'
; s2 has 3 'a', 3 'b', 1 'c', 1 'd'
; So the maximum for 'a' in s1 and s2 is 4 from s1; the maximum for 'b' is 3 from s2. 
; In the following we will not consider letters when the maximum of their occurrences is less than or equal to 1.
; We can resume the differences between s1 and s2 in the following string: 
; "1:aaaa/2:bbb" where 1 in 1:aaaa stands for string s1 and aaaa because the maximum for a is 4. 
; In the same manner 2:bbb stands for string s2 and bbb because the maximum for b is 3.
; The task is to produce a string in which each lowercase letters of s1 or s2 appears as many times as its maximum 
; if this maximum is strictly greater than 1; 
; these letters will be prefixed by the number of the string where they appear with their maximum value and :. 
; If the maximum is in s1 as well as in s2 the prefix is =:.
; In the result, substrings (a substring is for example 2:nnnnn or 1:hhh; it contains the prefix) will be 
; in decreasing order of their length and when they have the same length sorted 
; in ascending lexicographic order (letters and digits - more precisely sorted by codepoint); 
; the different groups will be separated by '/'. See examples and "Example Tests".
; Hopefully other examples can make this clearer.
; s1 = "my&friend&Paul has heavy hats! &"
; s2 = "my friend John has many many friends &"
; mix(s1, s2) --> "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"
; s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
; s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
; mix(s1, s2) --> "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"
; s1="Are the kids at home? aaaaa fffff"
; s2="Yes they are here! aaaaa fffff"
; mix(s1, s2) --> "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"
; Note for Swift, R, PowerShell
; The prefix =: is replaced by E:
; s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
; s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
; mix(s1, s2) --> "1:mmmmmm/E:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/E:ee/E:ss"

(defn mix-transform-entry
  ""
  {:test #(do
            (assert (= (mix-transform-entry {} ["a" 3] "1") {"a" ["1" 3]}))
            (assert (= 1 1)))}
  ([col entry info]
    (into col {(first entry) [info  (last entry)]})))

(defn mix-transform-string
  ""
  {:test #(do
            (assert (= (mix-transform-string "1" "aaa bb c") {"a" ["1" 3], "b" ["1" 2]}))
            (assert (= 1 1)))}
  ([info s]
    (let [only-letters (re-seq #"[a-z]" s)
          letters-frequencies (frequencies only-letters)
          above-one (filter #(> (val %) 1) letters-frequencies)]
      (reduce (fn [col entry] (mix-transform-entry col entry info)) {} above-one))))

(defn mix-merge-entry-val
  ""
  {:test #(do
            (assert (= (mix-merge-entry-val ["1" 10] ["2" 20]) ["2" 20]))
            (assert (= 1 1)))}
  ([a b]
    (let [count-a (last a)
          count-b (last b)
          idx (cond (= count-a count-b) "=" (> count-a count-b) "1" (< count-a count-b) "2")]
      [idx (max count-a count-b)])))

(defn mix-entry-to-string
  ""
  {:test #(do
            (assert (= (mix-entry-to-string ["a" ["1" 3]]) "1:aaa"))
            (assert (= 1 1)))}
  ([a]
    (let [idx (first (last a))
          count (last (last a))
          letter (first a)
          letters (repeat count letter)]
      (apply str idx ":" letters))))

(defn mix-keyfn
  ""
  {:test #(do
            (assert (= (mix-keyfn ["a" ["1" 3]]) -3203))
            (assert (= 1 1)))}
  ([a]
    (let [idx (case (first (last a)) "=" -100 "1" -300 "2" -200)
          count (* (last (last a)) -1000)
          letter (int (ffirst a))]
      (+ idx count letter))))

(defn mix
  [s1 s2]
  (let [a (merge-with mix-merge-entry-val (mix-transform-string "1" s1) (mix-transform-string "2" s2))
        b (sort-by mix-keyfn a)
        c (map mix-entry-to-string b)]
    (clojure.string/join "/" c)))

; korvintorson
(defn mix [s1 s2]
  (let [stat (fn [s] (frequencies (filter #(<= 97 (int %) 122) s)))
        fs1 (stat s1) fs2 (stat s2)
        mark (fn [c] ([\1 \= \2] (inc (compare (fs2 c) (fs1 c))))) ]
    (->> (merge-with max fs1 fs2)
      (filter #(< 1 (% 1)))
      (map (fn [[c n]] (apply str (mark c) \: (repeat n c))))
      sort
      (sort-by count >)
      (interpose \/)
      flatten
      (apply str) )))
