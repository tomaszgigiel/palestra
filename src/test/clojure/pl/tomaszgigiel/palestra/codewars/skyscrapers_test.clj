(ns pl.tomaszgigiel.palestra.codewars.skyscrapers-test
  (:use [clojure.test])
  (:require [pl.tomaszgigiel.palestra.common :as my-common])
  (:require [pl.tomaszgigiel.palestra.my-test-config :as my-test])
  (:require [pl.tomaszgigiel.palestra.codewars.skyscrapers :as skyscrapers])
  (:require [pl.tomaszgigiel.palestra.codewars.skyscrapers-const :as const]))

(use-fixtures :once my-test/once-fixture)
(use-fixtures :each my-test/each-fixture)

(comment

(deftest skyscrapers-permutations-test
  (is (= (skyscrapers/permutations [1 2 3]) [[1 3 2] [1 2 3] [2 1 3] [2 3 1] [3 1 2] [3 2 1]]))
)

(deftest skyscrapers-paired-clues-test
"
  11 12 13 14
44           21
43           22
42           23
41           24
  34 33 32 31

  11 12 13 14 15 16
46                 21
45                 22
44                 23
43                 24
42                 25
41                 26
  36 35 34 33 32 31

  11 12 13 14 15 16 17
47                    21
46                    22
45                    23
44                    24
43                    25
42                    26
41                    27
  37 36 35 34 33 32 31
"
  (is (= (skyscrapers/paired-clues [11 12 13 14          21 22 23 24          31 32 33 34          41 42 43 44         ]) [[11 34] [12 33] [13 32] [14 31]                                                 [44 21] [43 22] [42 23] [41 24]]))
  (is (= (skyscrapers/paired-clues [11 12 13 14 15 16    21 22 23 24 25 26    31 32 33 34 35 36    41 42 43 44 45 46   ]) [[11 36] [12 35] [13 34] [14 33] [15 32] [16 31]                 [46 21] [45 22] [44 23] [43 24] [42 25] [41 26]]))
  (is (= (skyscrapers/paired-clues [11 12 13 14 15 16 17 21 22 23 24 25 26 27 31 32 33 34 35 36 37 41 42 43 44 45 46 47]) [[11 37] [12 36] [13 35] [14 34] [15 33] [16 32] [17 31] [47 21] [46 22] [45 23] [44 24] [43 25] [42 26] [41 27]]))
)

(deftest skyscrapers-clue-test
  (is (= (skyscrapers/clue [1 2 3]) [3 1]))
  (is (= (skyscrapers/clue [1 3 2]) [2 2]))
  (is (= (skyscrapers/clue [2 1 3]) [2 1]))
  (is (= (skyscrapers/clue [2 3 1]) [2 2]))
  (is (= (skyscrapers/clue [3 1 2]) [1 2]))
  (is (= (skyscrapers/clue [3 2 1]) [1 3]))
)

; street-ok?
(deftest skyscrapers-street-ok?-test
  (is (= (skyscrapers/street-ok? [1 4 3 2] 0 [[[1 3 4 2] [1 2 4 3] [2 3 4 1]] [[4 1 2 3] [4 2 1 3]] [[1 4 3 2] [2 4 3 1] [3 4 2 1]] [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]) true))
  (is (= (skyscrapers/street-ok? [2 4 3 1] 0 [[[1 3 4 2] [1 2 4 3] [2 3 4 1]] [[4 1 2 3] [4 2 1 3]] [[1 4 3 2] [2 4 3 1] [3 4 2 1]] [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]) true))
  (is (= (skyscrapers/street-ok? [3 4 2 1] 0 [[[1 3 4 2] [1 2 4 3] [2 3 4 1]] [[4 1 2 3] [4 2 1 3]] [[1 4 3 2] [2 4 3 1] [3 4 2 1]] [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]) false))
  (is (= (skyscrapers/street-ok? [9 9 9 9] 0 [[[1 3 4 2] [1 2 4 3] [2 3 4 1]] [[4 1 2 3] [4 2 1 3]] [[1 4 3 2] [2 4 3 1] [3 4 2 1]] [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]) false))

  (is (= (skyscrapers/street-ok? [2 3 4 1] 0 [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]) false))
  (is (= (skyscrapers/street-ok? [2 3 4 1] 1 [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]) false))
  (is (= (skyscrapers/street-ok? [2 3 4 1] 2 [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]) false))
  (is (= (skyscrapers/street-ok? [2 3 4 1] 3 [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]) false))

  (is (= (skyscrapers/street-ok? [2 3 4 1] 0 [[[1 4 3 2] [2 4 3 1]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]) false))
  (is (= (skyscrapers/street-ok? [2 4 3 1] 0 [[[1 3 4 2] [2 3 4 1]] [[4 2 1 3]] [[3 4 2 1]] [[2 1 3 4]]]) false))
)

; filter-cross
(deftest skyscrapers-filter-cross-test
  (is (= (skyscrapers/filter-cross
           [
            [[1 4 3 2] [2 4 3 1] [3 4 2 1]]
            [[1 4 2 3] [2 1 4 3] [2 4 1 3] [3 1 4 2] [3 4 1 2] [3 2 4 1]]
            [[4 1 2 3] [4 2 1 3]]
            [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]
           [
            [[1 3 4 2] [1 2 4 3] [2 3 4 1]]
            [[4 1 2 3] [4 2 1 3]]
            [[1 4 3 2] [2 4 3 1] [3 4 2 1]]
            [[1 3 2 4] [2 1 3 4] [2 3 1 4]]])
         [
          [[1 4 3 2] [2 4 3 1]]
          [[2 1 4 3] [3 2 4 1]]
          [[4 1 2 3]]
         [[1 3 2 4] [2 3 1 4]]]))
  (is (= (skyscrapers/filter-cross
    [
     [[1 3 4 2] [1 2 4 3] [2 3 4 1]]
            [[4 1 2 3] [4 2 1 3]]
            [[1 4 3 2] [2 4 3 1] [3 4 2 1]]
            [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]
    [
     [[1 4 3 2] [2 4 3 1]]
            [[2 1 4 3] [3 2 4 1]]
            [[4 1 2 3]]
            [[1 3 2 4] [2 3 1 4]]])
         [
          [[1 3 4 2] [2 3 4 1]]
          [[4 2 1 3]]
          [[3 4 2 1]]
          [[2 1 3 4]]]))
  (is (= (skyscrapers/filter-cross
    [
     [[1 4 3 2] [2 4 3 1]]
            [[2 1 4 3] [3 2 4 1]]
            [[4 1 2 3]]
            [[1 3 2 4] [2 3 1 4]]]
    [
     [[1 3 4 2] [2 3 4 1]]
            [[4 2 1 3]]
            [[3 4 2 1]]
            [[2 1 3 4]]])
  [
   [[1 4 3 2]]
          [[3 2 4 1]]
          [[4 1 2 3]]
          [[2 3 1 4]]]))
  (is (= (skyscrapers/filter-cross
    [
     [[1 3 4 2] [2 3 4 1]]
     [[4 2 1 3]]
     [[3 4 2 1]]
     [[2 1 3 4]]]
    [
     [[1 4 3 2]]
     [[3 2 4 1]]
     [[4 1 2 3]]
     [[2 3 1 4]]])
  [
   [[1 3 4 2]]
   [[4 2 1 3]]
   [[3 4 2 1]]
   [[2 1 3 4]]]))
  (is (= (skyscrapers/filter-cross
     [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]
     [[[1 3 4 2]] [[4 2 1 3]] [[3 4 2 1]] [[2 1 3 4]]])
   [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]]))
  (is (= (skyscrapers/filter-cross
           [[[1 3 4 2]] [[4 2 1 3]] [[3 4 2 1]] [[2 1 3 4]]]
           [[[1 4 3 2]] [[3 2 4 1]] [[4 1 2 3]] [[2 3 1 4]]])
         [[[1 3 4 2]] [[4 2 1 3]] [[3 4 2 1]] [[2 1 3 4]]]))
)

; filter-cross
(deftest skyscrapers-filter-cross-4x4-test
  (is (= (skyscrapers/filter-cross
           [[[1 4 3 2] [2 4 3 1] [3 4 2 1]]
            [[1 4 2 3] [2 1 4 3] [2 4 1 3] [3 1 4 2] [3 4 1 2] [3 2 4 1]]
            [[4 1 2 3] [4 2 1 3]]
            [[1 3 2 4] [2 1 3 4] [2 3 1 4]]]
           [[[1 3 4 2] [1 2 4 3] [2 3 4 1]]
            [[4 1 2 3] [4 2 1 3]]
            [[1 4 3 2] [2 4 3 1] [3 4 2 1]]
            [[1 3 2 4] [2 1 3 4] [2 3 1 4]]])
         [
          [[1 4 3 2] [2 4 3 1]]
          [[2 1 4 3] [3 2 4 1]]
          [[4 1 2 3]]
          [[1 3 2 4] [2 3 1 4]]]))
)

; 4x4
(deftest skyscrapers-4x4-example-test1
  (let [clues '(2 2 1 3 2 2 3 1 1 2 2 3 3 2 1 3)
        actual (skyscrapers/solve-puzzle clues)]
  (is (= (count actual) 4))
  (is (= (nth actual 0) [1 3 4 2]))
  (is (= (nth actual 1) [4 2 1 3]))
  (is (= (nth actual 2) [3 4 2 1]))
  (is (= (nth actual 3) [2 1 3 4]))))

(deftest skyscrapers-4x4-example-test2
  (let [clues '(0 0 1 2 0 2 0 0 0 3 0 0 0 1 0 0)
        actual (skyscrapers/solve-puzzle clues)]
  (is (= (count actual) 4))
  (is (= (nth actual 0) '[2 1 4 3]))
  (is (= (nth actual 1) '[3 4 1 2]))
  (is (= (nth actual 2) '[4 2 3 1]))
  (is (= (nth actual 3) '[1 3 2 4]))))

; 6x6
(deftest skyscrapers-6x6-example-test1
  (is (= (skyscrapers/solve-puzzle '(3 2 2 3 2 1 1 2 3 3 2 2 5 1 2 2 4 3 3 2 1 2 2 4))
         '[[2 1 4 3 5 6] [1 6 3 2 4 5] [4 3 6 5 1 2] [6 5 2 1 3 4] [5 4 1 6 2 3] [3 2 5 4 6 1]])))
  
(deftest skyscrapers-6x6-example-test2
  (is (= (skyscrapers/solve-puzzle '(0 0 0 2 2 0 0 0 0 6 3 0 0 4 0 0 0 0 4 4 0 3 0 0))
         '[[5 6 1 4 3 2] [4 1 3 2 6 5] [2 3 6 1 5 4] [6 5 4 3 2 1] [1 2 5 6 4 3] [3 4 2 5 1 6]])))
  
(deftest skyscrapers-6x6-example-test3
  (is (= (skyscrapers/solve-puzzle '(0 3 0 5 3 4 0 0 0 0 0 1 0 3 0 3 2 3 3 2 0 3 1 0)) 
         '[[5 2 6 1 4 3] [6 4 3 2 5 1] [3 1 5 4 6 2] [2 6 1 5 3 4] [4 3 2 6 1 5] [1 5 4 3 2 6]])))

; 7x7
(deftest skyscrapers-7x7-example-test1
  (is (= (skyscrapers/solve-puzzle '(7 0 0 0 2 2 3 0 0 3 0 0 0 0 3 0 3 0 0 5 0 0 0 0 0 5 0 4))
         '[[1 5 6 7 4 3 2]
           [2 7 4 5 3 1 6]
           [3 4 5 6 7 2 1]
           [4 6 3 1 2 7 5]
           [5 3 1 2 6 4 7]
           [6 2 7 3 1 5 4]
           [7 1 2 4 5 6 3]])))
            
(deftest skyscrapers-7x7-example-test2
  (is (= (skyscrapers/solve-puzzle '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 5 2 2 2 2 4 1)) ;for a _very_ hard puzzle, replace the last 7 values with zeroes
         '[[7 6 2 1 5 4 3] 
           [1 3 5 4 2 7 6] 
           [6 5 4 7 3 2 1] 
           [5 1 7 6 4 3 2] 
           [4 2 1 3 7 6 5] 
           [3 7 6 2 1 5 4] 
           [2 4 3 5 6 1 7]])))

(deftest skyscrapers-7x7-example-test3
  (is (= (skyscrapers/solve-puzzle '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 0 0 0 0 0 0 0)) ;for a _very_ hard puzzle, replace the last 7 values with zeroes
         '[[7 6 2 1 5 4 3] 
           [1 3 5 4 2 7 6] 
           [6 5 4 7 3 2 1] 
           [5 1 7 6 4 3 2] 
           [4 2 1 3 7 6 5] 
           [3 7 6 2 1 5 4] 
           [2 4 3 5 6 1 7]])))
)
(deftest skyscrapers-7x7-example-test4
  (is (= (skyscrapers/solve-puzzle '(3 3 2 1 2 2 3 4 3 2 4 1 4 2 2 4 1 4 5 3 2 3 1 4 2 5 2 3)) ;medved
         '[[2 1 4 7 6 5 3]
           [6 4 7 3 5 1 2]
           [1 2 3 6 4 7 5]
           [5 7 6 2 3 4 1]
           [4 3 5 1 2 6 7]
           [7 6 2 5 1 3 4]
           [3 5 1 4 7 2 6]])))

(deftest speed-pair-test
  (let [skyscraper06-7x7-clues '(7 0 0 0 2 2 3 0 0 3 0 0 0 0 3 0 3 0 0 5 0 0 0 0 0 5 0 4)
        skyscraper07-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 5 2 2 2 2 4 1)
        skyscraper08-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 0 0 0 0 0 0 0)]
    (is (< (+ (my-common/only-time (count (skyscrapers/pair skyscraper06-7x7-clues)))
              (my-common/only-time (count (skyscrapers/pair skyscraper07-7x7-clues)))
              (my-common/only-time (count (skyscrapers/pair skyscraper08-7x7-clues)))) 2))))

(deftest speed-permutations-test
  (let [skyscraper01-7x7-range (->> 7 inc (range 1))]
    (is (< (+ (my-common/only-time (count (skyscrapers/permutations skyscraper01-7x7-range)))
              (my-common/only-time (count (skyscrapers/permutations skyscraper01-7x7-range)))
              (my-common/only-time (count (skyscrapers/permutations skyscraper01-7x7-range)))) 200))))

(deftest speed-test
  (let [skyscraper01-4x4-clues '(2 2 1 3 2 2 3 1 1 2 2 3 3 2 1 3)
        skyscraper02-4x4-clues '(0 0 1 2 0 2 0 0 0 3 0 0 0 1 0 0)
        skyscraper03-6x6-clues '(3 2 2 3 2 1 1 2 3 3 2 2 5 1 2 2 4 3 3 2 1 2 2 4)
        skyscraper04-6x6-clues '(0 0 0 2 2 0 0 0 0 6 3 0 0 4 0 0 0 0 4 4 0 3 0 0)
        skyscraper05-6x6-clues '(0 3 0 5 3 4 0 0 0 0 0 1 0 3 0 3 2 3 3 2 0 3 1 0) 
        skyscraper06-7x7-clues '(7 0 0 0 2 2 3 0 0 3 0 0 0 0 3 0 3 0 0 5 0 0 0 0 0 5 0 4)
        skyscraper07-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 5 2 2 2 2 4 1)
        skyscraper08-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 0 0 0 0 0 0 0)]
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper01-4x4-clues))) 2))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper02-4x4-clues))) 3))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper03-6x6-clues))) 30))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper04-6x6-clues))) 100))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper05-6x6-clues))) 100))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper06-7x7-clues))) 500))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper07-7x7-clues))) 500))
    (is (< (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper08-7x7-clues))) 1000))))

(deftest speed-all-test
  (let [skyscraper01-4x4-clues '(2 2 1 3 2 2 3 1 1 2 2 3 3 2 1 3)
        skyscraper02-4x4-clues '(0 0 1 2 0 2 0 0 0 3 0 0 0 1 0 0)
        skyscraper03-6x6-clues '(3 2 2 3 2 1 1 2 3 3 2 2 5 1 2 2 4 3 3 2 1 2 2 4)
        skyscraper04-6x6-clues '(0 0 0 2 2 0 0 0 0 6 3 0 0 4 0 0 0 0 4 4 0 3 0 0)
        skyscraper05-6x6-clues '(0 3 0 5 3 4 0 0 0 0 0 1 0 3 0 3 2 3 3 2 0 3 1 0) 
        skyscraper06-7x7-clues '(7 0 0 0 2 2 3 0 0 3 0 0 0 0 3 0 3 0 0 5 0 0 0 0 0 5 0 4)
        skyscraper07-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 5 2 2 2 2 4 1)
        skyscraper08-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 0 0 0 0 0 0 0)]
    (is (< (+ (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper01-4x4-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper02-4x4-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper03-6x6-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper04-6x6-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper05-6x6-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper06-7x7-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper07-7x7-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper08-7x7-clues)))) 2000))))

(deftest speed-7x7-test
  (let [skyscraper06-7x7-clues '(7 0 0 0 2 2 3 0 0 3 0 0 0 0 3 0 3 0 0 5 0 0 0 0 0 5 0 4)
        skyscraper07-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 5 2 2 2 2 4 1)
        skyscraper08-7x7-clues '(0 2 3 0 2 0 0 5 0 4 5 0 4 0 0 4 2 0 0 0 6 0 0 0 0 0 0 0)]
    (is (< (+ (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper06-7x7-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper07-7x7-clues)))
              (my-common/only-time (count (skyscrapers/solve-puzzle skyscraper08-7x7-clues)))) 1200))))
